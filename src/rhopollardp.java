import java.io.*;
import parcs.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.math.*;

public class RhoPollardP implements AM {

    public static void main(String[] args) {
        task curtask = new task();
        curtask.addJarFile("PollardRho.jar");
        (new RhoPollardP()).run(new AMInfo(curtask, (channel)null));
        curtask.end();

    }

    public void run(AMInfo info) {
      System.out.print("Enter n: ");
     Scanner sc = new Scanner(System.in);
	  BigInteger n = sc.nextBigInteger();
      System.out.println("N is: " + n);
      try {
          point p1 = info.createPoint();
            System.out.println("Point p1 is: " + n);
      channel c1 = p1.createChannel();
            System.out.println("Channel c1 is: " + n);

      // Used to be RhoPollard

      p1.execute("PollardRho");
      c1.write(n.toString());

      System.out.println("Waiting for result...");
    //   System.out.println("C1: " + c1.readLong());
    //   ArrayList<BigInteger> r = new ArrayList<BigInteger>((int)c1.readLong());
    //   System.out.println("Result found.");
    // for (BigInteger b : r) {
    //     System.out.println(b.toString(10));
    // }
        Result result = (Result) (c1.readObject());
        for (BigInteger bi : result.getList()) {
            System.out.print(bi + " ");
        }
        System.out.println(" ");
      } catch (Exception e) {e.printStackTrace(); return;}

      
    }
}
