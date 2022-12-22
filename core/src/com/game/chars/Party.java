package com.game.chars;

import com.game.BattleField;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class Party {
    public ArrayList<BaseHero> members;
    private final ArrayList<String> fractions;

    public Party(int teamSize, String [] request, String [] request1, int fieldSize, BattleField field) {
        fractions = new ArrayList<>();
        fractions.add(request[0]);
        fractions.add(request1[0]);
        members = this.makeRandomly(teamSize, request, 0, 0, request[0], field);
        members.addAll(this.makeRandomly(teamSize, request1, 0, fieldSize-1, request1[0], field));
    }


    public ArrayList<String> getFractions() {
        return fractions;
    }

    private ArrayList<BaseHero> makeRandomly(int teamCount, String [] request, int x, int y, String fraction, BattleField field) {
        ArrayList<BaseHero> team = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < teamCount; i++) {
            switch (request[r.nextInt(1, request.length)]) {
                case "Monk" -> team.add(new Monk(x++, y, fraction, field));
                case "Peasant" -> team.add(new Peasant(x++, y, fraction, field));
                case "Robber" -> team.add(new Robber(x++, y, fraction, field));
                case "Sniper" -> team.add(new Sniper(x++, y, fraction, field));
                case "Spearman" -> team.add(new Spearman(x++, y, fraction, field));
                case "Warlock" -> team.add(new Warlock(x++, y, fraction, field));
                case "Xbowman" -> team.add(new Xbowman(x++, y, fraction, field));
            }
        }
        return team;
    }

    public BaseHero get(int index) {
        return members.get(index);
    }
    public int size() {
        return members.size();
    }

    public void sortBySpeed() {
        Comparator<BaseHero> comp = Comparator.comparingInt(h -> h.speed);
        members.sort(comp.reversed());
    }

    public ArrayList<BaseHero> getAliveAsList() {
        ArrayList<BaseHero> res = new ArrayList<>();
        for (BaseHero h: members) {
            if (!h.status.equals("dead"))  res.add(h);
        }
        return res;
    }

    private Party(Party whole) {
        this.fractions = whole.fractions;
        this.members = whole.getAliveAsList();
    }

    public Party getAliveAsParty() {
        return new Party(this);
    }

    public ArrayList<BaseHero> getByFraction(String fraction, boolean ally) {
        ArrayList<BaseHero> res = new ArrayList<>();
        for (BaseHero h: members) {
            if (!ally) {
                if (!h.fraction.equals(fraction)) res.add(h);
            }
            if (ally && h.fraction.equals(fraction)) res.add(h);
        }
        return res;
    }

}