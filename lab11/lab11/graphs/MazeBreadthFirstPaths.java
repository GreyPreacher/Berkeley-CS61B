package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int source;
    private int target;
    private Maze maze;
    private boolean isFound = false;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        source = maze.xyTo1D(sourceX, sourceY);
        target = maze.xyTo1D(targetX, targetY);
        distTo[source] = 0;
        edgeTo[source] = source;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int s) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        marked[s] = true;
        while(!queue.isEmpty()) {
            int front = queue.remove();
            if (front == target) {
                isFound = true;
                break;
            }
            for (int w: maze.adj(front)) {
                if (!marked[w]) {
                    marked[w] = true;
                    distTo[w] = distTo[front] + 1;
                    edgeTo[w] = front;
                    announce();
                    queue.add(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(source);
    }
}

