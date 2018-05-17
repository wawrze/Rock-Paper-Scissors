package rps;

public final class Figure {

    private final int number;
    private final String name;
    private final int[] beats;
    private final int[] isBeaten;

    public Figure(int number, String name, int[] beats, int[] isBeaten) {
        this.number = number;
        this.name = name;
        this.beats = beats;
        this.isBeaten = isBeaten;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int[] getBeats() {
        return beats;
    }

    public int[] getIsBeaten() {
        return isBeaten;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
