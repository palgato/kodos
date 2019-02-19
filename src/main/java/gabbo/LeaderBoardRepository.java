package gabbo;

import java.util.ArrayList;
import java.util.Optional;

public class LeaderBoardRepository {

    public Optional<ArrayList<LeaderBoard>> leaderBoards;

    public LeaderBoardRepository(Optional<ArrayList<LeaderBoard>> boards) {
        this.leaderBoards = boards;

    }

    public LeaderBoardRepository() {

    }

    public Optional<ArrayList<LeaderBoard>> getAll() {
        return leaderBoards;
    }

}
