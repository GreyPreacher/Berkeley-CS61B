package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.List;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int moves;
        private SearchNode pre;
        public SearchNode(WorldState s, int m, SearchNode p) {
            state = s;
            moves = m;
            pre = p;
        }
        public WorldState getState() {
            return state;
        }
        public int getMoves() {
            return moves;
        }
        public SearchNode getPre() {
            return pre;
        }

        @Override
        public int compareTo(SearchNode s) {
            return moves + state.estimatedDistanceToGoal() - s.moves - s.state.estimatedDistanceToGoal();
        }
    }

    private MinPQ<SearchNode> pq = new MinPQ<>();
    private ArrayList<WorldState> bestSolution;
    private int totMoves;

    private void getAnswer(SearchNode goal) {
        totMoves = goal.moves;
        bestSolution = new ArrayList<>();
        SearchNode p = goal;
        while(p != null) {
            bestSolution.add(p.state);
            p = p.pre;
        }
    }

    public Solver(WorldState initial){
        pq.insert(new SearchNode(initial, 0, null));
        while (true) {
            SearchNode node = pq.delMin();
            if (node.state.isGoal()) {
                getAnswer(node);
                return;
            } else {
                for (WorldState neighbor: node.state.neighbors()) {
                    if (node.pre == null || !neighbor.equals(node.pre.state)) {
                        pq.insert(new SearchNode(neighbor, node.getMoves() + 1, node));
                    }
                }
            }
        }
    }
    public int moves(){
        return totMoves;
    }
    public Iterable<WorldState> solution(){
        List<WorldState> list = new ArrayList<>();
        for (int i = totMoves; i >= 0; --i) {
            list.add(bestSolution.get(i));
        }
        return list;
    }
}
