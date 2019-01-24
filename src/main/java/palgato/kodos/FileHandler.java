package palgato.kodos;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class FileHandler {

    public static AmazonS3 accessBucket() {

        String accessKey = System.getenv("AWS_ACCESS_KEY_ID");
        String secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_WEST_2)
                .build();
        return s3client;

    }

    /* Read from File - reads a LeaderBoard file and returns a HashMap of Players and their wins */
    public static Map<String, Player> readFromFile(String filePath) {

        String s3Bucket = System.getenv("S3_BUCKET_NAME");

        AmazonS3 s3client = accessBucket();
        S3Object s3object = s3client.getObject(s3Bucket, filePath);
        S3ObjectInputStream inputStream = s3object.getObjectContent();

        String line;
        Map<String, Player> loadedPlayers = new HashMap<>();
        //File file = new File(filePath);

        try {
            //file.createNewFile();
            //FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));

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

        String s3Bucket = System.getenv("S3_BUCKET_NAME");

        String writeName;
        int writeWins;
        boolean writeActive;

        try {
            File file = new File(leaderBoard.getFilePath());
            FileWriter fw = new FileWriter(file);

            //For each item in the boardPlayers HashMap, write the data to the file as comma separated values
            for (String key : leaderBoard.getBoardPlayers().keySet()) {
                writeName = key;
                writeWins = leaderBoard.getBoardPlayers().get(key).getWins();
                writeActive = leaderBoard.getBoardPlayers().get(key).getActive();

                fw.write(writeName + ",");
                fw.write(writeWins + ",");
                fw.write(Boolean.toString(writeActive));
                fw.write("\n");
            }
            fw.flush();
            fw.close();

            AmazonS3 s3client = accessBucket();
            s3client.putObject(s3Bucket,leaderBoard.getFilePath(),file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}