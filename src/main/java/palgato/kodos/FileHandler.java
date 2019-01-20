package palgato.kodos;

import java.io.*;
import java.util.*;

public class FileHandler {

    /* Read from File - reads a LeaderBoard file and returns a HashMap of Players and their wins */
    public static Map<String, Player> readFromFile(String filePath) {
        String line;
        Map<String, Player> loadedPlayers = new HashMap<>();
        File file = new File(filePath);

        try {
            file.createNewFile();
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                List<String> aList = new ArrayList<>(Arrays.asList(line.split(",")));
                String name = aList.get(0);
                int win = Integer.parseInt(aList.get(1));
                boolean act = Boolean.parseBoolean(aList.get(2));
                Player player = new Player(win, act);
                loadedPlayers.put(name, player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedPlayers;
    }

    /* Write to File - adds a Player and their number of wins to a LeaderBoard */
    public static void writeToFile(LeaderBoard leaderBoard) {

        String writeName;
        int writeWins;
        boolean writeActive;

        try {
            FileWriter fw = new FileWriter(leaderBoard.getFilePath());

            //For each item in the boardPlayers HashMap, write the data to the file as comma separated values
            for (String key : leaderBoard.getBoardPlayers().keySet()) {
                writeName = key;
                writeWins = leaderBoard.getBoardPlayers().get(key).getWins();
                writeActive = leaderBoard.getBoardPlayers().get(key).getActive();

                fw.write(writeName + ",");
                fw.write(Integer.toString(writeWins) + ",");
                fw.write(Boolean.toString(writeActive));
                fw.write("\n");
            }
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}