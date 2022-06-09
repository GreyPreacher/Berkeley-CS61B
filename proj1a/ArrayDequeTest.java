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

    @Test
    public void testAdd2(){
        ArrayDeque<Integer> dq=new ArrayDeque<>();
        dq.addLast(0);
        assertEquals(0, dq.removeLast());
        dq.addLast(2);
        dq.addFirst(3);
        dq.addFirst(4);
        assertEquals(4, dq.removeFirst());
        dq.addFirst(6);
        dq.addFirst(7);
        dq.addFirst(8);
        dq.addFirst(9);
        assertEquals(9,dq.get(0));
        dq.addFirst(10);
        dq.addFirst(11);
        dq.addFirst(12);
        dq.addFirst(13);
        assertEquals(2, dq.removeLast());
        dq.addLast(15);
        assertEquals(8, dq.get(5));
        dq.printDeque();
        assertEquals(10,dq.size());
        assertEquals(13, dq.removeFirst());
    }

    @Test
    public void testAdd3(){
        ArrayDeque<Integer> d=new ArrayDeque<>();
        d.addFirst(0);
        d.addLast(1);
        d.addLast(2);
        assertEquals(0, d.removeFirst());
        d.addLast(4);
        d.addLast(5);
        d.addFirst(6);
        assertEquals(2, d.get(2));
        d.addFirst(8);
        assertEquals(5, d.get(5));
        d.addFirst(10);
        d.addFirst(11);
        assertEquals(11, d.get(0));
        assertEquals(10, d.get(1));
        d.addLast(12);
        //assertEquals(32,d.length());
        assertEquals(12, d.removeLast());
        assertEquals(4, d.get(6));
        assertEquals(11, d.removeFirst());
    }

    @Test
    public void testAdd4(){
        ArrayDeque<Integer> dq=new ArrayDeque<>();
        dq.addFirst(0);
        dq.addLast(1);
        dq.removeFirst();
        dq.addLast(3);
        dq.addFirst(4);
        dq.get(2);
        dq.removeFirst();
        dq.addLast(7);
        dq.get(2);
        dq.get(0);
        dq.addLast(10);
        dq.get(2);
        dq.addLast(12);
        dq.printDeque();
        dq.addLast(13);
        dq.addLast(14);
        dq.addFirst(15);
        dq.addFirst(16);
        dq.get(5);
        dq.get(6);
        dq.get(0);
        dq.get(7);
        dq.printDeque();
        assertEquals(14,dq.get(8));
        assertEquals(14,dq.removeLast());
    }
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
    }
}
