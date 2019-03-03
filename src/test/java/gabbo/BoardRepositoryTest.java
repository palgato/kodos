package gabbo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class BoardRepositoryTest {

    @Test
    public void getAllReturnsListOfBoardsInMemory() {
        List<LeaderBoard> boards = new ArrayList<>();
        BoardRepository repo = new InMemoryRepository(boards);

        assertEquals(repo.getAll(), boards);
    }

//    @Test
//    public void getAllReturnsListOfBoardsFromBucket() {
//        List<LeaderBoard> boards = new ArrayList<>();
//        BoardRepository repo = new S3BucketRepository(boards,"testBoards");
//
//        assertEquals(repo.getAll(),boards);
//    }

    @Test
    public void saveStoresBoardInMemory() {
        List<LeaderBoard> boards = new ArrayList<>();
        BoardRepository repo = new InMemoryRepository(boards);

        LeaderBoard newBoard = new LeaderBoard("newBoard");
        boards.add(newBoard);

        assertEquals(repo.save(newBoard),boards);
    }

    @Test
    public void saveStoresBoardInBucket() {
        List<LeaderBoard> boards = new ArrayList<>();
        BoardRepository repo = new S3BucketRepository(boards,"testBoards");

        LeaderBoard newBoard = new LeaderBoard("newBoard");
        boards.add(newBoard);

        assertEquals(repo.save(newBoard),boards);
    }

    @Test
    public void findReturnsFoundBoardInMemory() {
        LeaderBoard board = new LeaderBoard("testBoard");
        List<LeaderBoard> boards = new ArrayList<>();
        boards.add(board);

        BoardRepository repo = new InMemoryRepository(boards);

        assertEquals(repo.find(board.getName()),Optional.of(board));
    }

    @Test
    public void findReturnsNoBoardInMemory() {
        List<LeaderBoard> boards = new ArrayList<>();
        BoardRepository repo = new InMemoryRepository(boards);

        assertNotEquals(repo.find("Dave"),null);
    }

}