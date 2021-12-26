package com.company.formula_1.models;

import java.util.Calendar;

public record Racer(String name, String command, Calendar lapTime) implements Comparable<Racer> {

    @Override
    public int compareTo(Racer o) {
        return (int) (this.lapTime.getTimeInMillis() - o.lapTime.getTimeInMillis());
    }
}
