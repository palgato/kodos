package palgato.kodos;

import java.util.Objects;

public class Player implements Comparable<Player> {

    private String name;
    private int wins;
    private boolean active;

    public Player(String playerName, int numberOfWins, boolean isActive) {
        name = playerName;
        wins = numberOfWins;
        active = isActive;
    }

    public String getName() {
        return name;
    }
    public int getWins() {
        return wins;
    }

    public boolean getActive() {
        return active;
    }

    public void addWin() {
        wins++;
    }

    public void updateStatus(boolean newStatus) {
        active = newStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return wins == player.wins &&
                active == player.active && name == player.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wins, active);
    }

    @Override
    public int compareTo(Player player) {
        return player.getWins() - this.wins;
    }
}