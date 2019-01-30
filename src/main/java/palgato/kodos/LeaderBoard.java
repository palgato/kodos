package palgato.kodos;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;

public class LeaderBoard
{
    private String filePath;
    private ArrayList<Player> boardPlayers;

    public LeaderBoard(String leaderBoardFilePath) {
        filePath = leaderBoardFilePath;
        boardPlayers = FileHandler.readFromFile(filePath);
    }

    public String getFilePath() {
        return filePath;
    }

    public ArrayList<Player> getBoardPlayers() {
        return boardPlayers;
    }

    /* Displays a sorted LeaderBoard of active Players in descending Wins value */
    public static ArrayList<Player> displayBoard(LeaderBoard leaderBoard) {
        //Add LeaderBoard Players to a List and sort them in descending order of Wins

        ArrayList<Player> activeBoardPlayers = new ArrayList<>(leaderBoard.getBoardPlayers());

        for (int i=0; i<activeBoardPlayers.size(); i++) {
            Player activePlayer = activeBoardPlayers.get(i);

            if (!activePlayer.getActive()) {
                activeBoardPlayers.remove(i);
            }
        }

        /*Map<String, Player> activeBoardPlayers = new HashMap<>(leaderBoard.getBoardPlayers());
        Iterator<Entry<String, Player>> it = activeBoardPlayers.
                //entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Player> entry = it.next();
            if (!entry.getValue().getActive()) {
                it.remove();
            }
        }*/

        //List<Entry<String, Player>> unsortedBoardPlayers = new ArrayList<>(activeBoardPlayers.entrySet());

        Collections.sort(activeBoardPlayers);

        /*Collections.sort(unsortedBoardPlayers, new Comparator<Entry<String, Player>>() {
            @Override
            public int compare(Entry<String, Player> o1, Entry<String, Player> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Map<String, Player> sortedBoardPlayers = new LinkedHashMap<>();
        for(Entry<String, Player> entry : unsortedBoardPlayers) {
            sortedBoardPlayers.put(entry.getKey(),entry.getValue());
        }*/

        return activeBoardPlayers;
    }

    /* Add a Player - Loads a LeaderBoard and adds a new Player to it */
    public Player addPlayer(String newPlayerName) {
        //Check if Player to add exists and create them
        if (findPlayer(newPlayerName) == null) {
            Player newPlayer = new Player(newPlayerName,0,true);
            boardPlayers.add(newPlayer);
            FileHandler.writeToFile(this);
            return newPlayer;
        /*if (!boardPlayers.contains(newPlayerName)) {
            //Create a new Player with 0 wins and set as active
            Player newPlayer = new Player(newPlayerName,0, true);

            //Add the player to the LeaderBoard HashMap and write to the CSV file
            boardPlayers.add(newPlayer);
            FileHandler.writeToFile(this);
            return newPlayer;*/
        } else {
            //If the Player already exists, return null
            return null;
        }
    }

    /* Update a Player status - takes a Player and updates their status in the LeaderBoard */
    public Player updatePlayerStatus(String updateName, boolean newStatus) {
        //Check if Player to update exists, update their status and write to the CSV file
        Player updatedPlayer = findPlayer(updateName);
        int playerIndex = boardPlayers.indexOf(updatedPlayer);

        updatedPlayer.updateStatus(newStatus);
        boardPlayers.set(playerIndex,updatedPlayer);
        FileHandler.writeToFile(this);
        return updatedPlayer;
/*
        if (boardPlayers.contains(updatedPlayer)) {
            Player updatedPlayer = boardPlayers.indexOf()
            updatedPlayer.updateStatus(newStatus);
            boardPlayers.replace(updateName, updatedPlayer);

            FileHandler.writeToFile(this);
            return updatedPlayer;
        } else {
            //If the Player doesn't exist, return null
            return null;
        }
*/
    }

    /* Add a win to a Player - takes a name and creates a player in the LeaderBoard */
    public Player playerWin(String winnerName) {
        //Check if winning Player exists, add their win and write to the CSV file
        Player winner = findPlayer(winnerName);
        int playerIndex = boardPlayers.indexOf(winner);
        winner.addWin();

        boardPlayers.set(playerIndex,winner);
        FileHandler.writeToFile(this);
        return winner;
        /*if (boardPlayers.containsKey(winnerName)) {
            Player winner = boardPlayers.get(winnerName);
            winner.addWin();
            boardPlayers.replace(winnerName, winner);

            FileHandler.writeToFile(this);
            return winner;
        } else {
            //If the Player doesn't exist, print out message saying so
            return null;
            //System.out.println("ERROR: Player " + winnerName + " does not exist");
        }*/
    }

    /* Show a Player - display a single Player and their relevant information */
/*    public Player showPlayer(String playerName) {
        //Check if winning Player exists and return the Player
        //If the Player doesn't exist, print out message saying so
        if (boardPlayers.containsKey(playerName)) {
            Player showPlayer = boardPlayers.get(playerName);
            return showPlayer;
        } else {
            System.out.println("Player: " + playerName + " does not exist");
            return null;
        }
    }*/

    public Player findPlayer(String playerName) {

        return boardPlayers.stream()
                .filter(foundPlayer1 -> playerName.equals(foundPlayer1.getName()))
                .findAny()
                .orElse(null);
    }
}