package com.game;

import com.game.chars.Coordinates;
import com.game.chars.Party;

import java.util.Collections;


public class ConsoleView {
    private StringBuilder field;
    private StringBuilder charTable = new StringBuilder();
    private final int fieldSize;

    private Party members;

        public ConsoleView(int fieldSize, Party members) {
        this.field = new StringBuilder();
        this.fieldSize = fieldSize;
        this.members = members;
    }

    public StringBuilder show(int step) {

        field.delete(0, field.length());

        if (step == -1) {
            field.append(Colors.ANSI_RED);
            field.append("Game over!");
            field.append(Colors.ANSI_RESET);
            field.append("\n");
            return field;
        }

        if (step == 0) {
            field.append(Colors.ANSI_RED);
            field.append("First step!");
            field.append(Colors.ANSI_RESET);
            field.append("\n");
        }
        else {
            field.append(Colors.ANSI_RED);
            field.append("Step: ");
            field.append(step);
            field.append(Colors.ANSI_RESET);
            field.append("\n");
        }

        // Верх игровое поле
        field.append("\u250c");
        field.append(String.join("", Collections.nCopies(fieldSize-1, "\u2500\u2500\u252c")));
        field.append("\u2500\u2500\u2510\n");

        // Середина игровое поле
        for (int i = 0; i < fieldSize-1; i++) {
            this.getCharFull(i);
            field.append("\u251c");
            field.append(String.join("", Collections.nCopies(fieldSize-1, "\u2500\u2500\u253c")));
            field.append("\u2500\u2500\u2524\n");
        }

        // Низ игровое поле
        this.getCharFull(fieldSize-1);
        field.append("\u2514");
        field.append(String.join("", Collections.nCopies(fieldSize-1, "\u2500\u2500\u2534")));
        field.append("\u2500\u2500\u2518\n");
        field.append("Press ENTER to continue. Press Q to exit\n");

        return field;
    }

    private void getChar(int x, int y) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getPosition().isSame(new Coordinates(x, y))) {
                if (members.get(i).getStatus().equals("dead")) {
                    field.append(Colors.ANSI_RED);
                    field.append(members.get(i).getName(), 0, 2);
                    field.append(Colors.ANSI_RESET);

                    charTable.append("    ");
                    charTable.append(Colors.ANSI_RED);
                    charTable.append(members.get(i).getName());
                    charTable.append(" HP: ");
                    charTable.append(members.get(i).getHealth());
                    charTable.append(", Status: ");
                    charTable.append(members.get(i).getStatus());
                    charTable.append(Colors.ANSI_RESET);
                    return;

                }
                if(members.get(i).getFraction().equals(
                        members.getFractions().get(0))) {
                    field.append(Colors.ANSI_BLUE);
                    field.append(members.get(i).getName(), 0, 2);
                    field.append(Colors.ANSI_RESET);

                    charTable.append("    ");
                    charTable.append(Colors.ANSI_BLUE);
                    charTable.append(members.get(i).getName());
                    charTable.append(" HP: ");
                    charTable.append(members.get(i).getHealth());
                    charTable.append(", Status: ");
                    charTable.append(members.get(i).getStatus());
                    charTable.append(Colors.ANSI_RESET);
                    return;
                }
                if(members.get(i).getFraction().equals(
                        members.getFractions().get(1))) {
                    field.append(Colors.ANSI_GREEN);
                    field.append(members.get(i).getName(), 0, 2);
                    field.append(Colors.ANSI_RESET);

                    charTable.append("    ");
                    charTable.append(Colors.ANSI_GREEN);
                    charTable.append(members.get(i).getName());
                    charTable.append(" HP: ");
                    charTable.append(members.get(i).getHealth());
                    charTable.append(", Status: ");
                    charTable.append(members.get(i).getStatus());
                    charTable.append(Colors.ANSI_RESET);
                    return;
                }
            }
        }
        field.append("  ");
    }

    private void getCharFull (int x) {
        field.append("\u2502");
        this.getChar(x, 0);
        for (int y = 1; y < fieldSize; y++) {
            field.append("\u2502");
            this.getChar(x, y);
        }
        field.append(String.format("\u2502%s\n", charTable));
        charTable.delete(0, charTable.length());
    }
}

/*Шпаргалка по значению кодовых точек
 * '\u250c' - верхний левый угол
 * '\u252c' - пересечение верхней горизонтальной границы и внутренней вертикальной
 * '\u2510' - верхний правый угол
 * '\u251c' - пересечение внешней границы слева и горизонтальной внутренней
 * '\u253c' - пересечение внутренней вертикальной и горизонтальной границ ячейки
 * '\u2524' - пересечение внешней границы справа и горизонтальной внутренней
 * '\u2514' - левый нижний угол
 * '\u2534' - пересечение нижней горизонтальной границы и внутренней вертикальной
 * '\u2518' - правый нижний угол
 * '\u2500' - нижняя граница
 * '\u2574' - верхняя граница
 * "___"
 * */

class Colors {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
}