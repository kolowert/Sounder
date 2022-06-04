package fun.kolowert.sounder;

public class MusicPlayer {

    public static void main(String[] args) {
        play();
    }

    public static void play() {
        System.out.println("MusicPlayer started");

        final int baseNoteDuration = 600;
        final double soloVolume = 0.85;
        final double bassVolume = 0.95;

        final int harmonicaOrder = 0;
        final int rhythmOrder = 2;

        Thread solo1 = new Thread(
                new Musician(MusicType.SOLO, harmonicaOrder, rhythmOrder, baseNoteDuration, soloVolume), "solo1");
        Thread solo2 = new Thread(
                new Musician(MusicType.SOLO, harmonicaOrder, rhythmOrder, baseNoteDuration, .6 * soloVolume), "solo2");
        Thread bass9 = new Thread(
                new Musician(MusicType.BASS, harmonicaOrder, rhythmOrder, baseNoteDuration, bassVolume), "bass9");
        Thread bass8 = new Thread(
                new Musician(MusicType.BASS, harmonicaOrder, rhythmOrder, baseNoteDuration, bassVolume), "bass8");

        solo1.start();
        //solo2.start();
        bass9.start();
        bass8.start();

        try {
            solo1.join();
            solo2.join();
            bass9.join();
            //bass8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nMusicPlayer finished");
    }
}
