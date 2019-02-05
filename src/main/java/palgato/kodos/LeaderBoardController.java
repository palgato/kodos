package palgato.kodos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LeaderBoardController {

    LeaderBoard unoBoard = new LeaderBoard("unoBoard",new BoardRound("339"));

    @GetMapping(value = "/displayBoard")
    @ResponseBody
    public ArrayList<Player> displayBoard() {
        return LeaderBoard.displayBoard(unoBoard);
    }

    @GetMapping("/findPlayer/{name}")
    public Player findPlayer(@PathVariable String name) {
        return unoBoard.findPlayer(name);
    }

    @GetMapping("/playerWin/{name}")
    public Player playerWin(@PathVariable String name) {
        return unoBoard.playerWin(name);
    }

    @GetMapping("/addPlayer/{name}")
    public Player addPlayer(@PathVariable String name) {
        return unoBoard.addPlayer(name);
    }

    @GetMapping("/updatePlayer/{name}/{status}")
    public Player updatePlayer(@PathVariable String name, @PathVariable boolean status) {
        return unoBoard.updatePlayerStatus(name,status);
    }


}
