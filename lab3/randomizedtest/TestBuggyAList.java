package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        BuggyAList<Integer> l1=new BuggyAList<>();
        AListNoResizing<Integer> l2=new AListNoResizing<>();
        for(int i=3;i<6;i+=1){
            l1.addLast(i);
            l2.addLast(i);
        }
        for(int i=3;i<6;i+=1){
            assertEquals(l1.removeLast(),l2.removeLast());
        }
    }
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> l1=new BuggyAList<>();
        int N = 4000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                l1.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size1 = l1.size();
                assertEquals(size, size1);
                System.out.println("size: " + size+','+size1);
            }else if(operationNumber == 2) {
                if(L.size()>0){
                    assertEquals(l1.getLast(),L.getLast());
                    System.out.println("getlast: "+L.getLast()+','+l1.getLast());
                }
            }else if (operationNumber == 3) {
                if (L.size()>0){
                    int last = L.removeLast();
                    int last1 = l1.removeLast();
                    assertEquals(last,last1);
                    System.out.println("removelast: "+last+','+last1);
                }
            }
        }
    }
}
