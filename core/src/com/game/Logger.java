package com.game;

import com.game.chars.Party;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private FileWriter fw;

    private Party members;

    String[] header;

    public Logger(Party members) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM.dd_HH.mm.ss");
        StringBuilder sb = new StringBuilder("D:\\IT\\Java\\Fight in console\\log\\");
        sb.append(dtf.format(LocalDateTime.now()));
        sb.append(".csv");

        try {
            fw = new FileWriter(sb.toString(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sb.delete(0, sb.length());

        this.header = new String[]{"StepNo", "Fraction", "Me", "MyPos", "Target", "TargetPos", "Damage val", "TgStatus"};
        this.members = members;

    }
        public void printDefault(int step) {
            try {
                if (step == 0) {
                    for (int i = 0; i < members.size(); i++) {
                        fw.append(members.get(i).getInfo());
                        fw.append("\r\n");
                    }
                    fw.append(String.join(";", header));
                }
                else {
                    for (int i = 0; i < members.getAliveAsList().size(); i++) {
                        fw.append("\r\n");
                        fw.append(Integer.toString(step));
                        fw.append(";");
                        fw.append(members.getAliveAsList().get(i).defaultLog());
                    }
                }
                fw.flush();
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }

}

