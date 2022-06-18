/*This is the CS61B project 1c implemented by @GreyPreacher
*
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testStudentDequeGold(){
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> stdDeque = new ArrayDequeSolution<>();
        String log = "";
        for (int i = 0; i < 1000; i++){
            if (stdDeque.size()==0){
                int summand = StdRandom.uniform(1000);
                int head = StdRandom.uniform(2);//if it's 1 then add to head, else back
                if (head == 1){
                    log = log + "addFirst(" + summand + ")\n";
                    stdDeque.addFirst(summand);
                    studentDeque.addFirst(summand);
                }else{
                    log = log + "addLast(" + summand + ")\n";
                    stdDeque.addLast(summand);
                    studentDeque.addLast(summand);
                }
            }else{
                int summand = StdRandom.uniform(1000);
                int operator = StdRandom.uniform(4);
                Integer testRemoveNumber = 1;
                Integer stdRemoveNumber = 1;
                switch (operator){//indicate which action to execute
                    case 0:
                        log = log + "addFirst(" + summand + ")\n";
                        stdDeque.addFirst(summand);
                        studentDeque.addFirst(summand);
                        break;
                    case 1:
                        log = log + "addLast(" + summand + ")\n";
                        stdDeque.addLast(summand);
                        studentDeque.addLast(summand);
                    case 2:
                        log = log + "removeFirst()\n";
                        stdDeque.removeFirst();
                        studentDeque.removeFirst();
                    case 3:
                        log = log + "removeLast()\n";
                        stdDeque.removeLast();
                        studentDeque.removeLast();
                }
                assertEquals(log,testRemoveNumber,stdRemoveNumber);
            }
        }
    }

}
