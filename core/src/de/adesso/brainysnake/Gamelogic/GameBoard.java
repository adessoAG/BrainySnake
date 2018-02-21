package de.adesso.brainysnake.Gamelogic;

import com.badlogic.gdx.graphics.Color;
import de.adesso.brainysnake.Gamelogic.Player.TestPlayer.KeyBoardPlayer;
import de.adesso.brainysnake.playercommon.BrainySnakePlayer;
import de.adesso.brainysnake.sampleplayer.SamplePlayer;
import de.adesso.brainysnake.sampleplayer.YourPlayer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Serves as a repository of business meta-information of a game process. This includes,
 * for example, which players belong to a match, how many points they have, and there current State in the game.
 */
public class GameBoard {

    private static final GameBoard INSTANCE = new GameBoard();

    // Add all Agents here
    private Map<Long, PlayerBoard> brainySnakePlayers;

    private GameBoard() {
        List<PlayerBoard> playerlist = createBrainySnakePlayerList();
        brainySnakePlayers = createPlayerMap(playerlist, Utils.getShuffledPlayerColor(playerlist.size()));
    }

    public static GameBoard getINSTANCE() {
        return INSTANCE;
    }

    private List<PlayerBoard> createBrainySnakePlayerList() {

        //crate BrainySnakePlayer manualy
        BrainySnakePlayer playerOne = new KeyBoardPlayer();
        BrainySnakePlayer yourPlayer = new YourPlayer();
        BrainySnakePlayer playerTwo = new SamplePlayer() {
            @Override
            public String getPlayerName() {
                return "SamplePlayer Two";
            }
        };
        BrainySnakePlayer playerThree = new SamplePlayer() {

            @Override
            public String getPlayerName() {
                return "SamplePlayer Three";
            }
        };
        BrainySnakePlayer playerFour = new SamplePlayer() {

            @Override
            public String getPlayerName() {
                return "SamplePlayer Four";
            }
        };

        // Add agents to the game
        List<PlayerBoard> playerList = new ArrayList<>();
        playerList.add(new PlayerBoard(playerOne));
        playerList.add(new PlayerBoard(playerTwo));
        playerList.add(new PlayerBoard(playerFour));

        return playerList;
    }

    /**
     * Splits a list of colors on the players.
     *
     * @param brainySnakePlayers List of players
     * @param playerColors       A list of colors to be shared among the players
     * @return Map of {@link BrainySnakePlayer} mapped to Color of Player {@link Color}
     */
    //TODO rukl TESTS
    Map<Long, PlayerBoard> createPlayerMap(List<PlayerBoard> brainySnakePlayers, List<Color> playerColors) {

        if (brainySnakePlayers.size() > playerColors.size()) {
            throw new IllegalStateException("Not enough Playercolors available");
        }

        Map<Long, PlayerBoard> playerMap = new HashMap<>();
        for (int i = 0; i < brainySnakePlayers.size(); i++) {
            PlayerBoard tempPlayer = brainySnakePlayers.get(i);
            tempPlayer.setColor(playerColors.get(i));
            playerMap.put(Long.valueOf(i), tempPlayer);

        }
        return playerMap;
    }

    /**
     * @return List of {@link PlayerBoard}
     */
    public List<PlayerBoard> getPlayerBoards() {

        List<PlayerBoard> playerList = new ArrayList<>();
        for (PlayerBoard playerBoard : brainySnakePlayers.values()) {
            playerList.add(playerBoard);
        }
        return playerList;
    }

    public Map<Long, PlayerBoard> getBrainySnakePlayers() {
        return brainySnakePlayers;
    }

    //TODO rukl doc, test, impl
    public void reset() {
        throw new NotImplementedException();
    }
}