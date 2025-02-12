package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
public class GuitarHero {
    static GuitarString [] stringAlpha = new GuitarString[37];
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        for (int i = 0; i < 37; i++) {
            stringAlpha[i] = new GuitarString(440 * Math.pow(2, (double) (i - 24) / 12));
        }
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) == -1) {
                    continue;
                }
                stringAlpha[keyboard.indexOf(key)].pluck();
            }
            double sample = 0;
            for (GuitarString i : stringAlpha) {
                sample += i.sample();
            }
            StdAudio.play(sample);
            for (GuitarString i : stringAlpha) {
                i.tic();
            }
        }
    }
}
