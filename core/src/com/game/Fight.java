package com.game;

import com.game.chars.BaseHero;
import com.game.chars.Party;

import java.util.ArrayList;

public class Fight {

    Party members;
    BattleField field;

    public Party getMembers() {
        return members;
    }

    public Fight(int teamSize, String [] request, String [] request1, int fieldSize) {
        field = new BattleField(fieldSize);
        this.members = new Party(teamSize, request, request1, fieldSize, field);
    }

    public boolean round (int step) {
        if (step == 0) {
            members.sortBySpeed();
            return true;

        } else {
            ArrayList <BaseHero> active = members.getAliveAsList();
            boolean flag = isContinue(active);
            if (!flag) return false;
            else for (BaseHero h : active) h.step(members);
        }
        return true;
    }

    public boolean isContinue (ArrayList<BaseHero> active) {
        boolean res = false;
        for (int i = 0; i < active.size()-1; i++) {
            res = !active.get(i + 1).getFraction()
                    .equals(active.get(i).getFraction());
            if (res) break;
        }
        return res;
    }

}

