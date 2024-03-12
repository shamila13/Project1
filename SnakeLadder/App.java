import coordinates.Coordinates;
import dice.Dice;
import java.util.*;
import jumper.Jumper;
import player.Player;


public class App {
    public static void main(String[] args) throws Exception {
        Player p1 = new Player();
        p1.setPlayerDetailsFromUserInput(p1);
        Player p2 = new Player();
        p2.setPlayerDetailsFromUserInput(p2);

        System.out.println("Player name: " + p1.getPlayerName());
        System.out.println("Player name: " + p2.getPlayerName());
    }
}
