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
        assertEquals(3,que.front);
        assertEquals(6,que.last);
        String first = que.removeFirst();
        assertEquals("one", first);

        String last = que.removeLast();
        assertEquals("three", last);

        assertEquals(1, que.size());
    }

    @Test(timeout = 1000)
    public static void test_extenArray_and_reduce(){
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
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        test_extenArray_and_reduce();
    }
}
