package tema6;

import java.math.BigInteger;
import java.util.Random;

import static java.math.BigInteger.probablePrime;

public class RSA {
    private BigInteger P;
    private BigInteger Q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private BigInteger C;

    public void create(){
        Random random = new Random();
        int ok = 0;

        P = probablePrime(1024, random);

        while (ok == 0) {
            Q = probablePrime(1024, random);
            if (P!=Q) {
                ok = 1;
            }
        }
        System.out.println("P: "+P + "\n");
        System.out.println("Q: "+ Q + "\n");

        N = P.multiply(Q);
        System.out.println("N: "+N + "\n");
        phi=P.subtract(BigInteger.ONE).multiply(Q.subtract(BigInteger.ONE));
        System.out.println("PHI: "+phi);

        e=BigInteger.valueOf((int) Math.pow(2,16) +1);
        for(int i=2;i<phi.intValue();i++){
            e=BigInteger.valueOf((int) Math.pow(2,16) +1);
            if(e.gcd(phi).compareTo(BigInteger.ONE)==0) {
                i=phi.intValue();
            }
        }
        System.out.println("e: "+ e);
        int i=1;
        d = phi.multiply(BigInteger.valueOf(i));
        d = d.add(BigInteger.valueOf(1));
        d = d.divide(e);
        while (e.multiply(d).mod(phi).compareTo(BigInteger.ONE)==-1||
                e.multiply(d).mod(phi).compareTo(BigInteger.ONE)==1) {
            i++;
            d = phi.multiply(BigInteger.valueOf(i));
            d = d.add(BigInteger.ONE);
            d = d.divide(e);
        }
        System.out.println(d);

        System.out.println("Public key: {" + e +","+N+"}");
        System.out.println("Private key: {" + d +","+N+"}");
    }

    public void encryption(BigInteger plaintext){
       // C=plaintext.pow(e.intValue()).mod(N);
        C=plaintext.modPow(e,N);
        System.out.println("Criptarea: " + C);

    }

    public void decryption(){
        BigInteger D;
       // D=C.pow(d.intValue()).mod(N);
        D=C.modPow(d,N);
        System.out.println("Decriptarea RSA: "+ D);
    }

    public void TCR(){
        BigInteger dp,dq;
        BigInteger ec1,ec2;
        BigInteger i,h;
        BigInteger decryptedTCR;

        dp=d.mod(P.subtract(BigInteger.ONE));
        dq=d.mod(Q.subtract(BigInteger.ONE));

        ec1=C.modPow(dp,P);
        ec2=C.modPow(dq,Q);
        i=Q.modInverse(P);
        h=i.multiply(ec1.subtract(ec2)).mod(P);
        decryptedTCR=ec2.add(h.multiply(Q)).mod(P.multiply(Q));
        System.out.println("Decriptarea TCR: "+decryptedTCR);
    }
}
