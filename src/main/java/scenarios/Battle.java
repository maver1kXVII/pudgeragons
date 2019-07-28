package scenarios;

import actors.Creature;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

public class Battle {
    private List<Creature> allies;
    private List<Creature> enemies;
    private Integer turn = 1;

    public Battle(List<Creature> allies, List<Creature> enemies) {
        this.allies = allies;
        this.enemies = enemies;
    }

    private Boolean isPartyAlive(List<Creature> party) {
        Boolean alive = false;
        for (Creature dude : party) {
            alive |= dude.isAlive();
        }
        return alive;
    }

    private String getPartyState(List<Creature> party) {
        String linePrefix = " • ";
        String stats = "";
        for (Creature dude : party) {
            stats += linePrefix + dude.toString();
        }
        return stats;
    }

    public void start() {
        while (isPartyAlive(allies) && isPartyAlive(enemies)) {
            System.out.println("Ход " + turn);
            System.out.println("Твой отряд: \n" + getPartyState(allies));
            System.out.println("Отряд противника: \n" + getPartyState(enemies));
            allyTurn();
            enemyTurn();
        }

        if (isPartyAlive(allies)) {
            System.out.println("CONGLATURATION!!!");
        } else {
            System.out.println("YOU DIED");
        }
    }

    public void allyTurn() {
        System.out.println("Выберите противника:");
        for (Creature dude : allies) {
            if (dude.isAlive()) {
                Creature target = inputSelector(enemies);
                System.out.println(dude.getNAME() + " атакует " + target.getNAME());
                dude.attack(target);
            }
        }
    }

    public void enemyTurn() {
        Random rand = new Random();
        Integer n = 0;
        System.out.println("Ходит противник...");
        for (Creature dude : enemies) {
            if (dude.isAlive()) {
                n = rand.nextInt(allies.size());
                if (n == 0) {
                    System.out.println(dude.getNAME() + " атакует " + allies.get(n).getNAME());
                    dude.attack(allies.get(n));
                } else {
                    System.out.println(dude.getNAME() + " решил ничего не делать");
                }
            }
        }
    }

    public <T> T inputSelector(List<T> list) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            int index = -1;
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i+1) + ") " + list.get(i).toString());
            }
            while (true) {
                index = Integer.parseInt(br.readLine().trim()) - 1;
                if (index >= list.size() || index < 0) {
                    System.out.println("Invalid index! Try again.");
                } else {
                    return list.get(index);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return list.get(0);
        }
    }
}
