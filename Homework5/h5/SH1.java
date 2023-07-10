package tema5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SH1 {
    List<String>binary=new ArrayList<>();
    String numWord=new String();
    String backPart=new String();
    String concatString=new String();
    List<String>chunk=new ArrayList<>();
    List <String> hList= new ArrayList<>();

    public void returnSH1(String word){
        createASCIIBinaryArray(word);
        createFrontPart();
        createBackPart();
        concatString=numWord+backPart;
       // System.out.println(concatString);
        createSublist();
        extendSublist();
        recalculateSublist();
        convertHexadecimal();
    }
 public void createASCIIBinaryArray(String word){
     for(int i=0;i<word.length();i++){
         int asciiCharacter= word.charAt(i);
         String ASCIIToBinary=Integer.toBinaryString(asciiCharacter);
         String newBinary=new String();
         int a=ASCIIToBinary.length();
         while(a<8) {
             newBinary = newBinary + "0";
             a++;
         }
         newBinary=newBinary+ASCIIToBinary;
         binary.add(newBinary);
     }
    // System.out.println(binary);
 }

 public void createFrontPart(){
     for(int i=0;i<binary.size();i++){
         numWord+=binary.get(i);
     }
     numWord=numWord+"1";
     while(numWord.length()%512!=448)
         numWord+="0";
 }
 public void createBackPart(){
     int binaryDimension=8*binary.size();
     String dimensionToBinary=Integer.toBinaryString(binaryDimension);
     int b=dimensionToBinary.length();
     while (b<64) {
         backPart += "0";
         b++;
     }
     backPart+=dimensionToBinary;
 }

 public void createSublist(){

        for (int i=0;i<concatString.length();i++){
            if((i+1)%32==0)
                chunk.add(concatString.substring(i-31,i+1));
        }
    // System.out.println(chunk);
 }

 public String xOR(String a,String b){
    String result=new String();
    for(int i=0;i<a.length();i++){
        if((a.charAt(i)=='0'&& b.charAt(i)=='0')||(a.charAt(i)=='1'&& b.charAt(i)=='1'))
            result+="0";
        else
        if((a.charAt(i)=='1'&& b.charAt(i)=='0')||(a.charAt(i)=='0'&& b.charAt(i)=='1'))
            result+="1";
    }
     return result;
 }

 public String shift(String word){
        String shiftWord=new String();
        for(int i=1;i<word.length();i++)
            shiftWord+=word.charAt(i);
        shiftWord+=word.charAt(0);

        return shiftWord;
 }

 public void extendSublist(){
        for(int i=16;i<80;i++){
            String wordA=chunk.get(i-3);
            String wordB=chunk.get(i-8);
            String wordC=chunk.get(i-14);
            String wordD=chunk.get(i-16);

            String xorA=xOR(wordA,wordB);
            String xorB=xOR(xorA,wordC);
            String xorC=xOR(xorB,wordD);

            chunk.add(shift(xorC));
        }
    // System.out.println(chunk);
 }

 public void recalculateSublist(){

        hList.add("1000111100000000000000001111101");
 }

 public void convertHexadecimal(){
        for(int i=0;i<hList.size();i++){
            int num;
            num = Integer.parseInt(hList.get(i), 2);

            String hexa = Integer.toHexString(num);
            System.out.println("HexaDecimal Value is : " + hexa);
        }
 }
}

