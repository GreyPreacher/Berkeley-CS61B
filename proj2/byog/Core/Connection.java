package byog.Core;


import javax.swing.text.Position;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Connection {
    Queue<Position> connections;

    void Conncection(){
        connections = new LinkedList();
    }

    /*
    * add the first connection which is limited by s
    */
    void addFirstConnection(Size s, Random random){
        int x = RandomUtils.uniform(random, 10, s.x - 10);
        int y = RandomUtils.uniform(random, 10, s.y - 10);
        byog.Core.Position p = new byog.Core.Position(x, y);
        connections.offer((Position) p);
    }
}
