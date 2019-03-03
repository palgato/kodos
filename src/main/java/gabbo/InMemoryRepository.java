package gabbo;

import java.util.List;
import java.util.Optional;

public class InMemoryRepository implements BoardRepository {
    private final List<LeaderBoard> boards;

    public InMemoryRepository(List<LeaderBoard> boards) {
        this.boards = boards;
    }

    @Override
    public List<LeaderBoard> getAll() {
        return boards;
    }

    @Override
    public List<LeaderBoard> save(LeaderBoard board) {

        this.boards.add(board);
        return boards;
    }

    @Override
    public Optional<LeaderBoard> find(String name) {
        return boards.stream()
                .filter(foundBoard -> name.equals(foundBoard.getName()))
                .findAny();
    }
}
