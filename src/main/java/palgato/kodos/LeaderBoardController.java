package palgato.kodos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LeaderBoardController {

    LeaderBoard unoBoard = new LeaderBoard("unoBoard.csv");

    @GetMapping(value = "/showBoard")
    @ResponseBody
    public HashMap<String, Player> showBoard() {
        return (HashMap<String, Player>) LeaderBoard.displayBoard(unoBoard);
    }

    @GetMapping("/findPlayer/{name}")
    public Map<String, Player> findPlayer(@PathVariable String name) {
        Map<String, Player> foundPlayer = new HashMap<>();
        foundPlayer.put(name,unoBoard.showPlayer(name));
        return foundPlayer;
    }

    @GetMapping("/playerWin/{name}")
    public Map<String, Player> playerWin(@PathVariable String name) {
        Map<String, Player> winner = new HashMap<>();
        winner.put(name,unoBoard.playerWin(name));
        return winner;
    }

    @GetMapping("/addPlayer/{name}")
    public Map<String, Player> addPlayer(@PathVariable String name) {
        Map<String, Player> addedPlayer = new HashMap<>();
        addedPlayer.put(name,unoBoard.addPlayer(name));
        return addedPlayer;
    }

    @GetMapping("/updatePlayer/{name}/{status}")
    public Map<String, Player> updatePlayer(@PathVariable String name, @PathVariable boolean status) {
        Map<String, Player> updatedPlayer = new HashMap<>();
        updatedPlayer.put(name,unoBoard.updatePlayerStatus(name,status));
        return updatedPlayer;
    }
}
