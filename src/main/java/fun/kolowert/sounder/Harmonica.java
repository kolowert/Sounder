package fun.kolowert.sounder;

import java.util.Arrays;

public class Harmonica {

    private final String name;
    private final int[] bases;
    private final int[] tones;

    public Harmonica(String name, int[] bases, int[] tones) {
        this.name = name;
        this.bases = bases;
        this.tones = tones;
    }

    public String getName() {
        return name;
    }

    public int[] getBases() {
        return bases;
    }

    public int[] getTones() {
        return tones;
    }

    @Override
    public String toString() {
        return "Harmonica [name=" + name + ", bases=" + Arrays.toString(bases) + ", tones=" + Arrays.toString(tones) + "]";
    }
}
