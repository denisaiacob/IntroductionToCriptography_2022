package tema6;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
	RSA rsa=new RSA();
    rsa.create();
    rsa.encryption(BigInteger.valueOf(15));
        long t1 = System.nanoTime();
        rsa.decryption();
        long t2 = System.nanoTime();
         System.out.println( "Timpul de executie in nanaosecunde pentru decriptarea RSA:" + (t2 - t1));
        long t3 = System.nanoTime();
        rsa.TCR();
        long t4 = System.nanoTime();
        System.out.println("Timpul de executie in nanaosecunde pentru decriptarea TCR:" + (t4 - t3));

    WienersAttack wienersAttack=new WienersAttack();
    wienersAttack.attack();
    }

}
