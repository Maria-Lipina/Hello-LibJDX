package com.game.chars;

import com.game.BattleField;

public class Warlock extends Monk {

    public Warlock(int x, int y, String fraction, BattleField field) {
        super(17, 12, new int[]{-5, -5}, 30, 9, "Warlock", x, y, fraction, field);
    }

}
