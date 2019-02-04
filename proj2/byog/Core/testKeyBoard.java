package byog.Core;

import edu.princeton.cs.introcs.StdDraw;

public class testKeyBoard {
    public static void main(String[] args) {
        while (true) {
            if(!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            System.out.print(StdDraw.nextKeyTyped());
        }
    }
}
