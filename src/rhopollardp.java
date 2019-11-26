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
        curtask.addJarFile("RhoPollardP.jar");
        (new RhoPollardP()).run(new AMInfo(curtask, (channel)null));
        curtask.end();
        // TODO: Modified name
        // TODO: More attempts

    }

    public void run(AMInfo info) {
      BigInteger n;
      try {
          System.out.print("Enter n: ");
          Scanner sc = new Scanner(System.in);
	  n = sc.nextBigInteger();

      } catch (IOException e) {e.printStackTrace(); return;}

      point p1 = info.createPoint();
      channel c1 = p1.createChannel();
      // Used to be RhoPollard
      p1.execute("PollardRho");
      c1.write(n.toString());

      System.out.println("Waiting for result...");
      ArrayList<BigInteger> r = new ArrayList<BigInteger>((int)c1.readLong());
      System.out.println("Result found.");
    for (BigInteger b : r) {
        System.out.println(b.toString(10));
    }
      
    }
}
