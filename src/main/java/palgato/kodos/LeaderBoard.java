package palgato.kodos;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoard
{
    private String filePath;
    private String leaderBoardName;
    private BoardRound boardRound;
    private ArrayList<Player> boardPlayers;

    public LeaderBoard(String boardName, BoardRound round) {

        this.leaderBoardName = boardName;
        this.boardRound = round;
        filePath = leaderBoardName + "-" + boardRound.getRoundName() + ".csv";
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

        Collections.sort(activeBoardPlayers);

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

    }

    public Player findPlayer(String playerName) {

        return boardPlayers.stream()
                .filter(foundPlayer1 -> playerName.equals(foundPlayer1.getName()))
                .findAny()
                .orElse(null);
    }

    public void closeRound() {
        boardRound.updateRound();

    }
}