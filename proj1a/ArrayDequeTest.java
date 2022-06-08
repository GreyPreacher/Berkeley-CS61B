import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
public class ArrayDequeTest {
    @Test
    public void testSizeEmpty(){
        ArrayDeque<String> que=new ArrayDeque<>();
        assertEquals(true,que.isEmpty());

        que.addFirst("one");
        que.addLast("two");
        que.addLast("three");
        assertEquals(3,que.size());

        que.printDeque();
        String first = que.removeFirst();
        assertEquals("one", first);

        String last = que.removeLast();
        assertEquals("three", last);

        assertEquals(1, que.size());
    }

    @Test(timeout = 1000)
    public void testExtendArrayAndReduce(){
        ArrayDeque<Integer> que=new ArrayDeque<>();
        for(int i=0;i<16;i++){
            que.addLast(i);
        }
        for(int i=-16;i<0;i++){
            que.addFirst(i);
        }
        que.printDeque();
        assertEquals(32,que.size());
        for (int i = -1; i >= 16; i--) {
            assertEquals(i, que.get(i));
        }
        for (int i = 0; i < 30; i++) {
            que.removeFirst();
        }
        assertEquals(2, que.size());
        que.printDeque();
    }

    @Test
    public void testAdd(){
        ArrayDeque<Integer> dq=new ArrayDeque<>();
        dq.addFirst(0);
        assertEquals(0, dq.get(0));
        dq.addFirst(2);
        dq.addFirst(3);
        dq.addFirst(4);
        dq.addLast(5);
        dq.addFirst(6);
        dq.addFirst(7);
        dq.get(1);
        assertEquals(6,dq.get(1));
        dq.addLast(9);
        dq.get(5);
        dq.addFirst(11);
        dq.addLast(12);
        dq.addLast(13);
        dq.addLast(14);
        dq.addFirst(15);
        dq.addLast(16);
        assertEquals(15,dq.removeFirst());
    }
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
    }
}
