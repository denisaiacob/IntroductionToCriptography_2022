package tema;

import java.math.*;
import java.sql.Timestamp;
import java.util.*;
import java.time.*;

import static java.math.BigInteger.*;

public class Main {

    public static void main(String[] args) {
        BBS();
        Jacobi();
    }

    public static void BBS() {

        Random random = new Random();
        int ok = 0;
        BigInteger m = new BigInteger("4");
        BigInteger result = new BigInteger("3");
        BigInteger P = null;
        while (ok == 0) {
            P = probablePrime(512, random);
            if (P.mod(m).compareTo(result) == 0) {
                ok = 1;
            }
        }
        ok = 0;
        BigInteger Q = null;
        while (ok == 0) {
            Q = probablePrime(512, random);
            if (Q.mod(m).compareTo(result) == 0) {
                ok = 1;
            }
        }
        System.out.println(P + "\n");
        System.out.println(Q + "\n");
        BigInteger N;
        N = P.multiply(Q);
        System.out.println(N + "\n");

        LocalDateTime time;
        time = LocalDateTime.now();
        BigInteger x0 = BigInteger.valueOf((Timestamp.valueOf(time).getTime()));
        x0 = x0.multiply(x0);
        x0 = x0.mod(N);
        System.out.println(x0);
        BigInteger xi = x0;

        int nr1 = 0, nr0 = 0;
        BigInteger b = new BigInteger("1");
        for (int i = 0; i < 512; i++) {
            b = b.multiply(BigInteger.valueOf(10)).add(xi.mod(BigInteger.valueOf(2)));
            if (xi.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(0)) == 0) {
                nr0++;
            } else
                nr1++;
            xi = xi.multiply(xi).mod(N);
        }
        System.out.println(b);

        //TEST 1:
        int procent0;
        procent0 = nr0 * 100 / 512;
        if (procent0 >= 45 && procent0 <= 55)
            System.out.println("DA");
        else
            System.out.println("NU");

    }

    public static void Jacobi() {
        Random random = new Random();
        int ok = 0,nr0=0,nr1=0;
        BigInteger m = new BigInteger("4");
        BigInteger result = new BigInteger("3");
        BigInteger P = null;
        while (ok == 0) {
            P = probablePrime(512, random);
            if (P.mod(m).compareTo(result) == 0) {
                ok = 1;
            }
        }
        ok = 0;
        BigInteger Q = null;
        while (ok == 0) {
            Q = probablePrime(512, random);
            if (Q.mod(m).compareTo(result) == 0) {
                ok = 1;
            }
        }
        System.out.println(P + "\n");
        System.out.println(Q + "\n");
        BigInteger N;
        N = P.multiply(Q);
        System.out.println(N + "\n");

        LocalDateTime time;
        time = LocalDateTime.now();
        BigInteger a = BigInteger.valueOf((Timestamp.valueOf(time).getTime()));
        a = a.multiply(a);
        a = a.mod(N);
        System.out.println(a);

        BigInteger b=new BigInteger("1");
        for (int i = 0; i < 512; i++) {
          /*  BigInteger k;
            k = a.mod(N);
            BigInteger c = N;
            BigInteger s = new BigInteger("1");
            while (k.compareTo(BigInteger.valueOf(2)) == 1) {
                while (k.mod(BigInteger.valueOf(4)).compareTo(BigInteger.valueOf(0)) == 0) {
                    k = k.divide(BigInteger.valueOf(4));
                }
                if (k.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(0)) == 0) {
                    if (c.mod(BigInteger.valueOf(8)).compareTo(BigInteger.valueOf(3)) == 0 ||
                            c.mod(BigInteger.valueOf(8)).compareTo(BigInteger.valueOf(5)) == 0) {
                        k = k.divide(BigInteger.valueOf(2));
                    }
                }
                if (k.compareTo(BigInteger.valueOf(1)) == 0)
                    break;
                if (k.mod(BigInteger.valueOf(4)).compareTo(c.mod(BigInteger.valueOf(4))) == 0
                        && k.mod(BigInteger.valueOf(4)).compareTo(BigInteger.valueOf(3)) == 0) {
                    s = s.negate();
                }
                k = c.mod(k);
                c = k;
            }
            k=k.multiply(s);

           */
            BigInteger k;
            BigInteger r;
            BigInteger n=N;
            k = a.mod(N);
            BigInteger jacobi=new BigInteger("1");
            while ( k.compareTo(BigInteger.valueOf(0))==1 ) {
                while ( k.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(0)) == 0 ) {
                    k=k.divide(BigInteger.valueOf(2));
                    r=N.mod(BigInteger.valueOf(8));
                    if ( r.compareTo(BigInteger.valueOf(3))==0 || r.compareTo(BigInteger.valueOf(5))==0) {
                        jacobi = jacobi.negate();
                    }
                }
                r=n;
                n = k;
                k = r;
                if ( k.mod(BigInteger.valueOf(4)).compareTo(BigInteger.valueOf(3))==0
                && n.mod(BigInteger.valueOf(4)).compareTo(BigInteger.valueOf(3))==0 ) {
                    jacobi = jacobi.negate();
                }
                k=k.mod(n);
            }
            k=jacobi;
            a=a.add(BigInteger.valueOf(1));

            if(k.compareTo(BigInteger.valueOf(1))==0)
            {
                b=b.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(1));
                nr1++;
            }
            else
            {
                b=b.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(0));
                nr0++;
            }

        }

        System.out.println(b);
        //TEST 1:
        int procent0;
        procent0 = nr0 * 100 / 512;
        if (procent0 >= 45 && procent0 <= 55)
            System.out.println("DA");
        else
            System.out.println("NU");
    }
}