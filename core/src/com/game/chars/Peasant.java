package com.game.chars;
import com.game.BattleField;

public class Peasant extends BaseHero {

    public Peasant(int x, int y, String fraction, BattleField field) {
        super(1, 1, new int[]{1, 1}, 1, 3, "Peasant", x, y, fraction, field);
    }

    @Override
    public void step(Party party) {
        if (status.equals("used")) status = "stand";
    }
}
