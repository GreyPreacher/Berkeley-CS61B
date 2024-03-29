package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private boolean isFould = false;
    private int[] nodeTo;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        nodeTo = new int[maze.N() * maze.N()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(-1, 0);
    }

    // Helper methods go here
    private void dfs(int u, int v) {
        marked[v] = true;
        announce();
        for (int w: maze.adj(v)) {
            if (!marked[w]) {
                marked[w] = true;
                nodeTo[w] = v;
                dfs(w, v);
            } else if (w != v) {
                edgeTo[w] = v;
                announce();
                for (int x = v; x != w; x = nodeTo[x]) {
                    edgeTo[x] = nodeTo[x];
                    announce();
                }
                isFould = true;
            }
            if (isFould) return;
        }
    }
}

