package palgato.kodos;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player charlie = new Player("Charlie",5,true);

    @Test
    public void getName() {
        assertEquals("Charlie",charlie.getName());
    }

    @Test
    public void getWins() {
        assertEquals(5,charlie.getWins());
    }

    @Test
    public void getActive() {
        assertTrue(charlie.getActive());
    }

    @Test
    public void addWin() {
        charlie.addWin();
        assertEquals(6,charlie.getWins());
    }

    @Test
    public void updateStatus() {
        charlie.updateStatus(false);
        assertFalse(charlie.getActive());
    }
}