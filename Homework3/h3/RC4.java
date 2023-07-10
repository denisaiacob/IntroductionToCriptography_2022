package tema3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RC4 {

    int N=256;
    List<Integer>S=new ArrayList<>();
    int M;

    public RC4(){}

    public void KSA( String key) {

        for (int i = 0; i < N; i++) {
            S.add(i);
        }
        int j=0;
        for (int i = 0; i < N; i++) {
            j = (j + S.get(i) + key.charAt(i % (key.length()/8))) % N;
            Collections.swap(S,i,j);
        }
    }

    public void PRGA(){
        int ok=0;
        int i=0;
        int j=0;
        int  nr0=0;
        while(ok<1000){
            i=(i+1)%N;
            j=(j+S.get(i))%N;
            Collections.swap(S,i,j);
            M=S.get((S.get(i)+S.get(j))%N);
            System.out.println(M);
            if(M==0){
                nr0++;
            }
            ok++;
        }
        System.out.println(nr0);
        int probabilitatea=nr0/1000;
        System.out.println(probabilitatea);
    }
}
