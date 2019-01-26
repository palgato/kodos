package palgato.kodos;

import java.util.Date;

public class Round {

    private String name;
    private Date startDate;
    private Date endDate;
    private LeaderBoard roundLeaderBoard;
    private CustomRule customRule1;
    private CustomRule customRule2;

    public Round(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
