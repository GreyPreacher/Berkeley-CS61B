package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private final double compoundInterest;

    public AcceleratingSawToothGenerator(int period, double compoundInterest) {
        state = 0;
        this.period = period;
        this.compoundInterest = compoundInterest;
    }

    public double normalize(int state) {
        //Don't forget casting
        return (double)(state - period / 2) / (double)(period / 2);
    }

    public double next() {
        /*this is for condition that period == 1 or less
        if (period <= 5) {
            period = 200;
        }*/
        if (state == period - 1) {
            state = 0;
            //System.out.println("period: " + period);
            period = (int) (period * compoundInterest);
        } else {
            state += 1;
        }
        return normalize(state);
    }
}
