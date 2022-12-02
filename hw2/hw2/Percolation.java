package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] world;
    private int worldSize;
    private int numberOfOpened;
    private WeightedQuickUnionUF disjointSet;
    private int top;
    private int bottom;

    public Percolation(int N) {                // create N-by-N grid, with all sites initially blocked
        if (N <= 0){
            throw new IllegalArgumentException("N cannot be less or equal than 0\n");
        }
        worldSize = N;
        numberOfOpened = 0;
        world = new boolean[worldSize * worldSize]; //new boolean is false default
        disjointSet = new WeightedQuickUnionUF(worldSize * worldSize + 2);
        top = worldSize * worldSize;
        bottom = worldSize * worldSize + 1;
    }

    public int locationCal(int row, int col){
        return row * worldSize + col;
    }
    public void open(int row, int col) {    // open the site (row, col) if it is not open already
        int loc = locationCal(row, col);
        if (!world[loc]){
            world[loc] = true;
            numberOfOpened++;
        }
        //up. if the up block of the loc is opened, union the loc block and the block above
        if (loc / worldSize - 1 >= 0 && world[loc - worldSize]){
            disjointSet.union(loc, loc - worldSize);
        }
        //down
        if (loc / worldSize + 1 < worldSize && world[loc + worldSize]){
            disjointSet.union(loc, loc + worldSize);
        }
        //left
        if (loc / worldSize - 1 >= 0 && world[loc - 1]){
            disjointSet.union(loc, loc - 1);
        }
        //right
        if (loc / worldSize - 1 < worldSize && world[loc + 1]){
            disjointSet.union(loc, loc + 1);
        }

        //connect with the top?
        if (row == 0){
            disjointSet.union(loc, top);
        }

        //connect with the bottom?
        if (row == worldSize - 1){
            disjointSet.union(loc, bottom);
        }
    }

    public boolean isOpen(int row, int col) {  // is the site (row, col) open?
        int loc =locationCal(row, col);
        return world[loc];
    }
    public boolean isFull(int row, int col) {  // is the site (row, col) full?
        return disjointSet.connected(locationCal(row, col), top);
    }
    public int numberOfOpenSites() {           // number of open sites
        return numberOfOpened;
    }

    public boolean percolates() {              // does the system percolate?
        return disjointSet.connected(top, bottom);
    }

    public static void main(String[] args) {   // use for unit testing (not required)
        System.out.println("Running main");
    }
}
