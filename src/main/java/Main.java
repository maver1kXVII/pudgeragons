import actors.Creature;
import actors.Dragon;
import actors.Player;
import scenarios.Battle;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] argv) {
        System.out.println("Pudgers & Dragons v0.0.1! \n");
        List<Creature> allies = new ArrayList<>();
        allies.add(new Player(200, 10));
        List<Creature> enemies = new ArrayList<>();
        enemies.add(new Dragon(50, 5));
        enemies.add(new Dragon(125, 10));

        Battle testBattle = new Battle(allies, enemies);
        testBattle.start();
    }
}
