package com.marcusjac.GalacticFighter;

public enum Heading {
    RIGHT,LEFT;

    public Heading next() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}
