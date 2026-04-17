package flik;
import org.junit.Test;
import static org.junit.Assert.*;
public class FlickTest {
    @Test
    public void test() {
        int i=128;
        int j=128;
        assertTrue(Flik.isSameNumber(i, j));
    }
}
