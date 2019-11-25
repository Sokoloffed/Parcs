import java.io.*;
import parcs.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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
      p1.execute("RhoPollard");
      c1.write(n.toString());

      System.out.println("Waiting for result...");
      ArrayList<BigInteger> r = ArrayList<BigInteger>(c1.readLong());
      System.out.println("Result found.");

      System.out.println(Arrays.toString(r));
    }
}
