package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }

    public double normalize(int state) {
        //Don't forget casting
        return (double)(state - period / 2) / (double)(period / 2);
    }

    public double next() {
        state = state + 1;
        //int weirdState = state & (state >>> 3) % period;
        int weirdState = state & (state >> 3) & (state >> 8) % period;
        return normalize(weirdState);
    }
}
