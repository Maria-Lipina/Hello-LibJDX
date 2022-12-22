package com.game.chars;

import com.game.BattleField;

import java.util.ArrayList;

public class Xbowman extends BaseHero {
    private int shoot;
    public Xbowman(int x, int y, String fraction, BattleField field) {
        super(6, 3, new int[]{2,3}, 10, 4,  "Xbowman", x, y, fraction, field);
        this.shoot = 16;
    }
    public Xbowman(int attack, int protection, int[] damage, int health, int speed,
                   String name, int shoot, int x, int y, String fraction, BattleField field) {
        super(attack, protection, damage, health, speed, name, x, y, fraction, field);
        this.shoot = shoot;
    }

    @Override
    public String getInfo() {
        return super.getInfo() +
                ";shoot=" + shoot;
    }

    @Override
    public void step(Party party) {

        for (BaseHero h: party.getAliveAsParty().getByFraction(fraction, true)) {
            if (h.name.equals("Peasant") && h.status.equals("stand")) {
                shoot++;
                h.status = "used";
                break;
            }
        }
        if (shoot<1) {
            status = "used";
            target = null;
            damageValue = 0;
            return;
        }
        ArrayList<BaseHero> enemies = party.getAliveAsParty().getByFraction(fraction, false);
        if (enemies.size() == 0) {
            target = this;
            damageValue = 0;
            return;
        }
        target = position.findNearest(enemies);
        double dist = position.distance(target.position);
        damageValue = (dist < speed ?
                super.damageValue(target) :
                (super.damageValue(target)/2));
        target.damage(damageValue);
        shoot--;
    }
}

