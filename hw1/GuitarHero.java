import synthesizer.GuitarString;
import edu.princeton.cs.algs4.StdAudio;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int allKey = 37;

    public static double ithFrequency(int i){
        return CONCERT_A * Math.pow(2,(i - 24) / 2);
    }

    public static void main(String[] args){
        synthesizer.GuitarString[] gs = new GuitarString[allKey];
        for (int i = 0; i < allKey; i++){
            gs[i] = new synthesizer.GuitarString(ithFrequency(i));
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index < 0) {
                    System.out.println("Please input key among the keyboard: " + keyboard);
                    continue;
                }
                gs[index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;

            for (synthesizer.GuitarString string : gs){
                sample += string.sample();
            }
            StdAudio.play(sample);
            for (synthesizer.GuitarString string : gs) {
                string.tic();
            }
        }
    }
}
