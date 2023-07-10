package tema3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        long t1 = System.nanoTime(); //de aici incepem masurarea timpului de executie
//
//        LFRS registru=new LFRS();
//        registru.init();
//        registru.prelucrare();
//
//        long t2 = System.nanoTime();// aici se sfarseste masurarea timului de executie
//        System.out.println("\n" + "Timpul de executie in nanaosecunde LFRS:" + (t2 - t1)); // afisam timpul de executie in nanosecunde

        RC4 r=new RC4();
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int length = 128;
        Random random = new Random();

        String key = random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(key);
        r.KSA(key);
        r.PRGA();
    }


}
