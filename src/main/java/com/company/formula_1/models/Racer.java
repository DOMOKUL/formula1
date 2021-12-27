package com.company.formula_1.models;

public record Racer(String name, String command, Long lapTime) implements Comparable<Racer> {

    @Override
    public int compareTo(Racer o) {
        return (int) (this.lapTime - o.lapTime);
    }
}

