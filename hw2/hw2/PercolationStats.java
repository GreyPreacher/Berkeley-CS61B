package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int T;
    private double[] x;

    private int[] generateUnrepeatableRandomNumber(int range){//generate a series of random numbers from 1-range
        if (range <= 0){
            throw new IllegalArgumentException("size should be > 0\n");
        }
        int[] source = new int[range];
        for (int i = 0; i < range; i++){
            source[i] = i;
        }

        int index, len = range;
        for (int i = 0; i < range; i++){
            index = StdRandom.uniform(len--);//uniform(N) generates integer in [0, N-1]
            int temp = source[index];
            source[index] = source[len];
            source[len] = temp;
        }
        return source;
    }
    public PercolationStats(int N, int T, PercolationFactory pf) {   // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0){
            throw new IllegalArgumentException("N and T should be > 0\n");
        }
        this.T = T;
        x = new double[T];
        for (int i = 0; i < T; i++){
            Percolation world = pf.make(N);
            int[] pos = generateUnrepeatableRandomNumber(N * N);
            int index = 0;//to pick a random number in the pos[N*N] from 0
            while(!world.percolates()){
                int position = pos[index];
                int row = position / N, col = position % N;
                world.open(row, col);
            }
            x[i] = (double) world.numberOfOpenSites() / N * N;
        }
    }
    public double mean() {             // sample mean of percolation threshold
        return StdStats.mean(x);
    }
    public double stddev() {           // sample standard deviation of percolation threshold
        return StdStats.stddev(x);
    }
    public double confidenceLow() {    // low endpoint of 95% confidence interval
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    public double confidenceHigh() {   // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
