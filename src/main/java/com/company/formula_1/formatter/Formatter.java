package com.company.formula_1.formatter;

import com.company.formula_1.models.Racer;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Formatter {

    private static final String LINE_BREAK = "\n";
    private static final String SPACE = " ";
    private static final String VERTICAL_LINE = "|";
    private static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("mm:ss.SSS");


    public String format(List<Racer> racerList) {
        StringBuilder result = new StringBuilder();
        AtomicInteger i = new AtomicInteger(1);
        try {
            racerList.stream().sorted()
                    .limit(15)
                    .forEach(e -> result.append(i.getAndIncrement())
                            .append(".")
                            .append(SPACE)
                            .append(e.name())
                            .append(String.format("%1$" + (22 - e.name().length()) + "s", VERTICAL_LINE))
                            .append(SPACE)
                            .append(e.command())
                            .append(String.format("%1$" + (30 - e.command().length()) + "s", VERTICAL_LINE))
                            .append(FORMAT_TIME.format(e.lapTime()))
                            .append(LINE_BREAK)
                    );
            result.append("------------------------------------------------------------------------" + LINE_BREAK);
            racerList.stream().sorted()
                    .skip(15)
                    .forEach(e -> result.append(i.getAndIncrement())
                            .append(".")
                            .append(SPACE)
                            .append(e.name())
                            .append(String.format("%1$" + (22 - e.name().length()) + "s", VERTICAL_LINE))
                            .append(SPACE)
                            .append(e.command())
                            .append(String.format("%1$" + (30 - e.command().length()) + "s", VERTICAL_LINE))
                            .append(FORMAT_TIME.format(e.lapTime()))
                            .append(LINE_BREAK));

            return String.valueOf(result);
        } catch (NullPointerException e){
            throw new NullPointerException("RacerList is null");
        }

    }
}
