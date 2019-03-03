package gabbo;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    List<LeaderBoard> getAll();

    List<LeaderBoard> save(LeaderBoard newBoard);

    Optional<LeaderBoard> find(String name);
}
