import parcs.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList.*;
import java.util.ArrayList;
import java.math.*;
import java.io.*;


public class PollardRho implements AM{
    private final static BigInteger ZERO = new BigInteger("0");
    private final static BigInteger ONE  = new BigInteger("1");
    private final static BigInteger TWO  = new BigInteger("2");
    private final static SecureRandom random = new SecureRandom();


    public static BigInteger rho(BigInteger N) {
        System.out.println("Hello from Rho" + N);
        BigInteger divisor;
        BigInteger c  = new BigInteger(N.bitLength(), random);
        BigInteger x  = new BigInteger(N.bitLength(), random);
        BigInteger xx = x;




        // check divisibility by 2
        if (N.mod(TWO).compareTo(ZERO) == 1) return TWO;

        do {
            x  =  x.multiply(x).mod(N).add(c).mod(N);
            xx = xx.multiply(xx).mod(N).add(c).mod(N);
            xx = xx.multiply(xx).mod(N).add(c).mod(N);
            divisor = x.subtract(xx).gcd(N);
        } while((divisor.compareTo(ONE)) == 0);
        System.out.println("Divisor: " + divisor);
        return divisor;
    }

    public void run(AMInfo info){
        System.out.println("Info is: " + info);
	    Result result = new Result();
        String obj = info.parent.readObject().toString();
        BigInteger n = new BigInteger(obj);  // (BigInteger) // (info.parent.readObject().toString());

        if (n.isProbablePrime(1) || n.compareTo(ONE) == 0) result.add(n);
	else
	{ 
	    BigInteger divisor = rho(n);

            point p1 = info.createPoint();
            channel c1 = p1.createChannel();
            p1.execute("PollardRho");
            c1.write(divisor.toString());

            point p2 = info.createPoint();
            channel c2 = p2.createChannel();
            p2.execute("PollardRho");
            c2.write(n.divide(divisor).toString());
            Result r1 = (Result) (c1.readObject());
            Result r2 = (Result) (c2.readObject());
            // ArrayList<BigInteger> r1 = ( ArrayList<BigInteger> ) (c1.readObject());//((int));
 	        // ArrayList<BigInteger> r2 = ( ArrayList<BigInteger> )(c2.readObject());//((int)c1.readObject());
            // String c1Str = c1.readObject().toString();
            // String c2Str = c2.readObject().toString();
            System.out.println("C1 object: "  );
            for (BigInteger bi: r1.getList()) {
                System.out.print(bi + " ");
            }
            System.out.println(" ");
            System.out.println("C2 object: "  );
            for (BigInteger bi: r2.getList()) {
                System.out.print(bi + " ");
            }
            System.out.println(" ");

            // BigInteger b1 = new BigInteger(c1Str);
            // BigInteger b2 = new BigInteger(c2Str);
            // System.out.println("B1 : " + b1);
            // System.out.println("B2 : " + b2);
            // r.add(b1);
            // r.add(b2);
            
            for (BigInteger bi : r1.getList())
                result.add(bi);
            for (BigInteger bi : r2.getList())
                result.add(bi);
        }
        info.parent.write(result);
    }
}
