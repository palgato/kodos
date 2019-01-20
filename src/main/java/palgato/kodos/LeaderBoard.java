package palgato.kodos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LeaderBoard
{
    private String filePath;
    private Map<String, Player> boardPlayers;

    public LeaderBoard(String leaderBoardFilePath) {
        filePath = leaderBoardFilePath;
        boardPlayers = FileHandler.readFromFile(filePath);
    }

    public String getFilePath() {
        return filePath;
    }

    public Map<String, Player> getBoardPlayers() {
        return boardPlayers;
    }

    public static void main(String[] args) {
        //Create a new LeaderBoard with a specific file name and load any players in it to memory
        //LeaderBoard unoBoard = new LeaderBoard("unoBoard.csv");

        //Display the LeaderBoard, i.e. Players and their information
        //displayBoard(unoBoard);
    }

    /* Displays a sorted LeaderBoard of active Players in descending Wins value */
    public static List<Player> displayBoard(LeaderBoard leaderBoard) {
        //Add LeaderBoard Players to a List and sort them in descending order of Wins
        List<Player> sortedActiveBoardplayers = new ArrayList<>();
        for (int i = 0; i < leaderBoard.getBoardPlayers().size(); i++) {
            Player p = leaderBoard.getBoardPlayers().get(i);
            if (p.getActive()) {
                sortedActiveBoardplayers.add(p);
            }
        }
        Collections.sort(sortedActiveBoardplayers);

        return sortedActiveBoardplayers;
    }

    /* Returns the Name of a Player in a Map of Players where their name is the key */
    public static String getKey(Map<String, Player> map, Player player) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue().equals(player)) {
                return (String) entry.getKey();
            }
        }
        return null;
    }

    /* Add a Player - Loads a LeaderBoard and adds a new Player to it */
    public void addPlayer(String newPlayerName) {
        //Check if Player to add exists and create them
        if (!boardPlayers.containsKey(newPlayerName)) {
            //Create a new Player with 0 wins and set as active
            Player newPlayer = new Player(0, true);

            //Add the player to the LeaderBoard HashMap and write to the CSV file
            boardPlayers.put(newPlayerName,newPlayer);
            FileHandler.writeToFile(this);
        } else {
            //If the Player already exists, print out message saying so
            System.out.println("ERROR: Player " + newPlayerName + " already exists for this Leaderboard");
        }
    }

    /* Update a Player status - takes a Player and updates their status in the LeaderBoard */
    public void updatePlayerStatus(String updateName, boolean newStatus) {
        //Check if Player to update exists, update their status and write to the CSV file
        if (boardPlayers.containsKey(updateName)) {
            Player updatedPlayer = boardPlayers.get(updateName);
            updatedPlayer.updateStatus(newStatus);
            boardPlayers.replace(updateName, updatedPlayer);

            FileHandler.writeToFile(this);
        } else {
            //If the Player doesn't exist, print out message saying so
            System.out.println("ERROR: The Player " + updateName + " does not exist");
        }
    }

    /* Add a win to a Player - takes a name and creates a player in the LeaderBoard */
    public void playerWin(String winnerName) {
        //Check if winning Player exists, add their win and write to the CSV file
        if (boardPlayers.containsKey(winnerName)) {
            Player winner = boardPlayers.get(winnerName);
            winner.addWin();
            boardPlayers.replace(winnerName, winner);

            FileHandler.writeToFile(this);
        } else {
            //If the Player doesn't exist, print out message saying so
            System.out.println("ERROR: Player " + winnerName + " does not exist");
        }
    }

    /* Show a Player - display a single Player and their relevant information */
    public Player showPlayer(String playerName) {
        //Check if winning Player exists and return the Player
        //If the Player doesn't exist, print out message saying so
        if (boardPlayers.containsKey(playerName)) {
            Player showPlayer = boardPlayers.get(playerName);
            return showPlayer;
        } else {
            System.out.println("Player: " + playerName + " does not exist");
            return null;
        }
    }
}