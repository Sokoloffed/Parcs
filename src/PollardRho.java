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

        return divisor;
    }

    public void run(AMInfo info){
        BigInteger n = new BigInteger();
	    ArrayList<BigInteger> r = new ArrayList<BigInteger>();
        String obj = info.parent.readObject().toString(());
        n = new BigInteger(obj);  // (BigInteger) // (info.parent.readObject().toString());

        if (n.isProbablePrime(1) || n.compareTo(ONE) == 0) r.add(n);
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
            ArrayList<BigInteger> r1 = new ArrayList<BigInteger>();//((int)c1.readObject());
 	        ArrayList<BigInteger> r2 = new ArrayList<BigInteger>();//((int)c1.readObject());

            r.addAll(r1);
	        r.addAll(r2);
        }
        info.parent.write(r);
    }
}
