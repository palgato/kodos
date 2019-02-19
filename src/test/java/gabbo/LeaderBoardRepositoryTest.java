package gabbo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class LeaderBoardRepositoryTest {

    @Test
    public void getAllReturnsListOfBoards() {

        LeaderBoard boardOne = new LeaderBoard("boardOne");
        LeaderBoard boardTwo = new LeaderBoard("boardTwo");
        ArrayList<LeaderBoard> boards = new ArrayList<>();
        boards.add(boardOne);
        boards.add(boardTwo);

        LeaderBoardRepository leaderBoardRepository = new LeaderBoardRepository(Optional.of(boards));

        assertEquals(leaderBoardRepository.getAll(),boards);

    }

    @Test
    public void getAllReturnsNoBoards() {
        LeaderBoardRepository leaderBoardRepository = new LeaderBoardRepository();
        Optional<ArrayList<LeaderBoard>> boards = null;

        assertEquals(leaderBoardRepository.getAll(),boards);
    }

}