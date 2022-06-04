package fun.kolowert.sounder;

import java.util.Arrays;

public class Rhythm {

    private final String name;
    private final double[] patternSolo;
    private final double[] patternBass;

    public Rhythm(String name, double[] patternSolo, double[] patternBass) {
        super();
        this.name = name;
        this.patternSolo = patternSolo;
        this.patternBass = patternBass;
    }

    public String getName() {
        return name;
    }

    public double[] getPatternSolo() {
        return patternSolo;
    }

    public double[] getPatternBass() {
        return patternBass;
    }

    @Override
    public String toString() {
        return "Rhythm [name=" + name + ", patternSolo=" + Arrays.toString(patternSolo) + ", patternBass="
                + Arrays.toString(patternBass) + "]";
    }
}
