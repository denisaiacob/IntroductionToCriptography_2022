package tema4;

import java.util.ArrayList;
import java.util.List;

public class DES {
    List<Integer> plaintext = new ArrayList<>();
    List<Integer> key = new ArrayList<>();
    List<Integer> RPT = new ArrayList<>();
    List<Integer> LPT = new ArrayList<>();
    List<Integer> K1 = new ArrayList<>();
    List<Integer> K2 = new ArrayList<>();
    List<Integer> sBOX = new ArrayList<>();
    List<Integer> aux = new ArrayList<>();

    public void des() {
        generareCheie();
        permutareInitiala();
        impartire();
        criptare();
    }
    String hextoBin(String input)
    {
        int n = input.length() * 4;
        input = Long.toBinaryString(Long.parseUnsignedLong(input, 16));
        while (input.length() < n)
            input = "0" + input;
        return input;
    }

    String binToHex(String input)
    {
        int n = (int)input.length() / 4;
        input = Long.toHexString(Long.parseUnsignedLong(input, 2));
        while (input.length() < n)
            input = "0" + input;
        return input;
    }

    void generareCheie() {

        int random;
        for (int i = 0; i < 64; i++) {
            random = (int) (Math.random() * 2);
            plaintext.add(random);
        }
        key.addAll(plaintext);
        System.out.println(key);
        int j = 0;
        for (int i = 7; i < 58; i += 8) {
            key.remove(i);
        }
        System.out.println(key);
    }

    void permutareInitiala() {
        int aux;
        int n = 57;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                aux = plaintext.get(i * 8 + j);
                plaintext.set(i * 8 + j, plaintext.get(n - j * 8));
                plaintext.set(n - j * 8, aux);
            }
            n += 2;
        }
        System.out.println(plaintext);
    }

    void impartire() {
        for (int i = 0; i < 32; i++) {
            RPT.add(plaintext.get(i));
        }
        System.out.println(RPT);
        for (int i = 32; i < 64; i++) {
            LPT.add(plaintext.get(i));
        }
        System.out.println(LPT);
        for (int i = 0; i < 28; i++) {
            K1.add(key.get(i));
        }
        for (int i = 28; i < 56; i++) {
            K2.add(key.get(i));
        }
    }

    void criptare() {
        ////////////////////Key Transformation////////////////
        for (int i = 1; i <= 16; i++) {
            if (i == 1 || i == 2 || i == 9 || i == 16) {
                int aux1 = K1.get(0);
                int aux2 = K2.get(0);
                for (int j = 0; j < 27; j++) {
                    K1.set(j, K1.get(j + 1));
                    K2.set(j, K2.get(j + 1));
                }
                K1.set(27, aux1);
                K2.set(27, aux2);
            } else {
                int aux11 = K1.get(0);
                int aux21 = K2.get(0);
                int aux12 = K1.get(1);
                int aux22 = K2.get(1);
                for (int j = 0; j < 26; j++) {
                    K1.set(j, K1.get(j + 2));
                    K2.set(j, K2.get(j + 2));
                }
                K1.set(27, aux12);
                K1.set(26, aux11);
                K2.set(27, aux22);
                K2.set(26, aux21);
            }
        }
            //////////////Expansiunea///////////////
            aux.addAll(RPT);
            RPT.add(0, RPT.get(31));
            for (int e = 0; e < 47; e++) {
                if ((e + 1) % 4 == 0) {
                    RPT.add(e + 1, RPT.get(e + 2));
                    RPT.add(e + 2, RPT.get(e + 1));
                    e += 2;
                }
            }
            RPT.add(47, RPT.get(0));
            System.out.println(RPT);

            /////////////////////XOR//////////////////////

            for (int t = 0; t < 48; t++) {
                sBOX.add((key.get(t) + RPT.get(t)) % 2);
            }

            ///////////S-box////////////////

           // for (int t = 0; t < 48; t++) {
             //   if ((t + 1) % 6 == 0) {

               // }
            //}

            /////////////XOR si swap////////////
            aux.addAll(RPT);
            for (int t = 0; t < 32; t++) {
                RPT.set(t, (sBOX.get(t) + LPT.get(t)) % 2);
            }
            int t=31;
            while(t>0) {
                LPT.remove(t);
                t--;
            }
            LPT.addAll(aux);

            //////////////Final Permutation///////////////

            plaintext.removeAll(plaintext);
            plaintext.addAll(aux);


    }
}
