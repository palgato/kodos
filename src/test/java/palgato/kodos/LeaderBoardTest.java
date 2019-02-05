package palgato.kodos;

import com.amazonaws.services.s3.AmazonS3;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LeaderBoardTest {
    private BoardRound testBoardRound = new BoardRound(1);
    private LeaderBoard testBoard = new LeaderBoard("testBoard",testBoardRound);
    private Player dennis = new Player("Dennis",4,true);
    private Player mac = new Player("Mac",0,false);
    private Player dee = new Player("Dee",7,true);

    static AmazonS3 s3client = FileHandler.accessBucket();
    static String s3Bucket = System.getenv("S3_BUCKET_NAME");


    @AfterClass
    public static void tearDown() throws Exception {
        String testData = "Dennis,4,true" + "\n" + "Mac,0,false" + "\n" + "Dee,7,true" + "\n";
        File testFile = new File("testBoard-1.csv");
        FileWriter fw = new FileWriter(testFile,false);
        fw.write(testData);
        fw.flush();
        fw.close();

        s3client.putObject(s3Bucket,"testBoard-testRound.csv",testFile);
    }

    @Test
    public void getFilePath() {
        assertEquals("testBoard-testRound.csv",testBoard.getFilePath());
    }

    @Test
    public void getBoardPlayers() {
        ArrayList<Player> testPlayers = new ArrayList<>(testBoard.getBoardPlayers());
        assertNotNull(testPlayers);
    }

    @Test
    public void displayBoard() {
    }

    /* Test the addPlayer() method in the LeaderBoard class
     * A newly added Player is created with 0 wins and an active value of true */
    @Test
    public void addPlayer() {
        Player charlie = new Player("Charlie",0,true);
        testBoard.addPlayer("Charlie");

        Player addedPlayer = testBoard.findPlayer("Charlie");
        assertEquals(charlie,addedPlayer);
    }

    /* Test the updatePlayer() method in the LeaderBoard class
     * The test Player 'Mac' has an initial active value of false */
    @Test
    public void updatePlayerStatus() {
        Player mac = new Player("Mac",3,true);
        testBoard.updatePlayerStatus("Mac",true);

        Player updatedPlayer = testBoard.findPlayer("Mac");
        assertEquals(mac.getActive(),updatedPlayer.getActive());
    }

    /* Test the playerWin() method in the LeaderBoard class
     * The test Player 'Dennis' has an initial wins value of 4 */
    @Test
    public void playerWin() {
        Player dennis = new Player("Dennis",5,true);
        testBoard.playerWin("Dennis");

        Player winningPlayer = testBoard.findPlayer("Dennis");
        assertEquals(dennis.getWins(),winningPlayer.getWins());
    }

    /* Test the findPlayer() method in the LeaderBoard class
     * The test Player 'Dee' has an initial wins value of 7 and true active status */
    @Test
    public void findPlayer() {
        Player dee = new Player("Dee",7,true);
        Player foundPlayer = testBoard.findPlayer("Dee");

        assertEquals(dee.getName(),foundPlayer.getName());
    }

    @Test
    public void closeRound() {

    }
}