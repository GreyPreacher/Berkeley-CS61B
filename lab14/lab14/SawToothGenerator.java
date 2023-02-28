package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class SawToothGenerator implements Generator{
    private final int period;
    private int state;

    public SawToothGenerator(int period) {
        state = 0;
        this.period = period;
    }

    public double normalize(int state) {
        //Don't forget casting
        return (double)(state - period / 2) / (double)(period / 2);
    }

    public double next() {
        state = (state + 1) % period;
        return normalize(state);
    }
}
