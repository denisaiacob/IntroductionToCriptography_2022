package tema3;

import java.util.ArrayList;
import java.util.List;

public class LFRS {

    List<Integer> registruInitial=new ArrayList<>();
    List<Integer> registru=new ArrayList<>();
    List<Integer> output=new ArrayList<>();
    int perioada=0;
    int ok=1;

    public void LFRS() {
    }

    public void prelucrare(){
      while(ok==1){
        //addOutput();
          System.out.println(registru.get(15));
        shift();
        ok=0;
        for(int i=0;i<16;i++){
            if(registruInitial.get(i)!=registru.get(i)){
                ok=1;
            }
        }
        perioada++;
      }
      //perioada=output.size();
      System.out.println(perioada);
        if(perioada<65535) //2^16-1
            System.out.println("Perioada nu este maxima");
        else
            System.out.println("Perioada este maxima");
    }

    public void init(){
        while(!registru.contains(1)) {
            for (int i = 0; i < 16; i++) {
                this.registru.add((int) (Math.random() * 2));
            }
        }
        registruInitial.addAll(registru);
        System.out.println(registru);
    }

    public void addOutput(){
        this.output.add(registru.get(15));
    }

    public int first(){
        int val=0;
        val=(registru.get(1)+registru.get(2)+registru.get(4))%2;
        val=(val+registru.get(15))%2;
        return val;
    }

    public  void shift(){
        int nou=first();
       for (int i=15;i>0;i--)
       {
           registru.set(i,registru.get(i-1));
       }
       registru.set(0,nou);
       // System.out.println(registru);
    }

}
