package fun.kolowert.sounder;

import javax.sound.sampled.LineUnavailableException;
import java.util.ArrayList;
import java.util.List;

public class Musician implements Runnable {
    private final MusicType musicType;
    private final int harmonicOrder;
    private final int rhythmOrder;
    private final int temp;
    private final double volume;
    private List<Harmonica> harmonics = new ArrayList<>();
    private List<Rhythm> rhythms = new ArrayList<>();

    public Musician(MusicType musicType, int harmonicOrder, int rhythmOrder, int temp, double volume) {
        initHarmonics();
        initRhythms();
        this.musicType = musicType;
        this.harmonicOrder = harmonicOrder;
        this.rhythmOrder = rhythmOrder;
        this.temp = temp;
        this.volume = volume;
    }

    private void initHarmonics() {
        harmonics.add(new Harmonica("China", new int[]{0, 207, 233, 277, 311, 370},
                new int[]{0, 415, 466, 554, 622, 740, 830, 932}));

        harmonics.add(new Harmonica("CMajor", new int[]{0, 165, 175, 196, 220, 247},
                new int[]{0, 262, 294, 330, 349, 392, 440, 494, 523, 587, 659, 698, 784, 880}));
    }

    private void initRhythms() {
        rhythms.add(new Rhythm("4/4 a",
                new double[]{0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.5, 0.5, 1.0, 0.25, 0.25, 0.5, 0.5, 0.25, 0.25,
                        1.0, 0.5, 0.5, 1.0, 1.0, 0.5, 0.5, 1.0},
                new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.5, 0.5, 2.0, 2.0}));
        rhythms.add(new Rhythm("4/4 b",
                new double[]{0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.5, 0.5, 1.0, 0.5, 0.5, 0.5, 0.5, 1.0, 0.5, 0.5,
                        1.0, 1.0, 0.5, 0.5, 1.0},
                new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.5, 0.5, 2.0, 2.0}));
        rhythms.add(new Rhythm("4/4 c",
                new double[]{1.0, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.5, 0.5, 1.0, 0.5, 0.5, 0.5, 0.5, 1.0, 0.5, 0.5, 1.0,
                        1.0, 0.5, 0.5, 1.0},
                new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 3.0, 1.0, 1.0, 2.0, 4.0}));
    }

    @Override
    public void run() {
        try {
            play();
        } catch (LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void play() throws LineUnavailableException, InterruptedException {

        int[] notes;
        double[] rhythmo;

        if (musicType == MusicType.SOLO) {
            notes = harmonics.get(harmonicOrder).getTones();
            rhythmo = rhythms.get(rhythmOrder).getPatternSolo();
        } else {
            notes = harmonics.get(harmonicOrder).getBases();
            rhythmo = rhythms.get(rhythmOrder).getPatternBass();
        }

        int[] durations = new int[rhythmo.length];
        for (int i = 0; i < rhythmo.length; i++) {
            durations[i] = (int) (0.5 + temp * rhythmo[i]);
        }

        int counter = 0;
        int repitation = 4;
        while (counter < repitation) {
            ++counter;
            for (int i = 0; i < durations.length; i++) {
                int noteOrder = (int) (notes.length * Math.random());
                if (notes[noteOrder] > 0) {
                    SoundUtils.tone(notes[noteOrder], durations[i], volume);
                } else {
                    Thread.sleep(durations[i]);
                }
            }

        }
    }
}
