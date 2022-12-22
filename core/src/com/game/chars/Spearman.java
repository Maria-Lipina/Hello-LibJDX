package com.game.chars;

import com.game.BattleField;

import java.util.ArrayList;

public class Spearman extends BaseHero {


    public Spearman(int x, int y, String fraction, BattleField field) {
        super(4, 5,  new int[]{1,3}, 10, 4,  "Spearman", x, y, fraction, field);
    }

    public Spearman(int attack, int protection, int[] damage, int health, int speed,
                    String name, int x, int y, String fraction, BattleField field) {
        super(attack, protection, damage, health, speed, name, x, y, fraction, field);
    }

    @Override
    public void step(Party party) {

        ArrayList<BaseHero> enemies = party.getAliveAsParty().getByFraction(fraction, false);
        if (enemies.size() == 0) {
            target = this;
            damageValue = 0;
            return;
        }

        target = position.findNearest(enemies);
        int x = position.x;
        int y = position.y;

        int distance = position.distance(target.position);

        boolean checkPos;
        if (distance == (int) Math.sqrt(2)) {
            damageValue = super.damageValue(target);
            target.damage(damageValue);
            return;
        }

        checkPos = field.isValidPos(--x, y);
        if (target.position.x < position.x && checkPos) {
            field.placeMe(position.x, position.y, --position.x, position.y);
            damageValue = 0;
            return;
        }
        ++x;
        checkPos = field.isValidPos(++x, y);
        if (target.position.x > position.x && checkPos) {
            field.placeMe(position.x, position.y, ++position.x, position.y);
            damageValue = 0;
            return;
        }
        --x;
        checkPos = field.isValidPos(x, --y);
        if (target.position.y < position.y && checkPos) {
            field.placeMe(position.x, position.y, position.x, --position.y);
            damageValue = 0;
            return;
        }
        ++y;
        checkPos = field.isValidPos(x, ++y);
        if (target.position.y > position.y && checkPos) {
            field.placeMe(position.x, position.y, position.x, ++position.y);
            damageValue = 0;
        }
    }

}
