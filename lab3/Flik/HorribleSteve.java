import org.junit.Test;
import static org.junit.Assert.*;

public class HorribleSteve {
    public static void main(String[] args) {
        int i = 0;
        int j = 0;
        while (i < 500) {
            if(!Flik.isSameNumber(i, j)) {
                break;
            }
            i++;
            j++;
        }
        System.out.println("i is" + i);
    }


    @Test
    public void isSameNumberTest() {
        assertTrue("128 is not equal to 128!",Flik.isSameNumber(128, 128));
    }

}