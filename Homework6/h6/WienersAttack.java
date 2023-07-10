package tema6;

import java.math.BigInteger;
import java.util.Random;

import static java.math.BigInteger.probablePrime;

public class WienersAttack {

    private BigInteger P,Q,N;
    private BigInteger phi;

    public void attack(){
        Random random = new Random();
        int ok = 0;

        Q = probablePrime(1024, random);
        BigInteger doubleQ=Q.multiply(BigInteger.TWO);

        while (ok == 0) {
            P = probablePrime(1024, random);
            if (Q.compareTo(P)==-1 && P.compareTo(doubleQ)==-1) {
                ok = 1;
            }
        }
        System.out.println("P: "+P + "\n");
        System.out.println("Q: "+ Q + "\n");

        N = P.multiply(Q);
        System.out.println("N: "+N + "\n");
        phi=P.subtract(BigInteger.ONE).multiply(Q.subtract(BigInteger.ONE));
        System.out.println("PHI: "+phi);
    }

}
