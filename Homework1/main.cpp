#include<iostream>
#include<cstring>

using namespace std;

char alfabet[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

double FrEn[] = { 8.12,1.49,2.71,4.32,12.02,2.3,2.03,5.92,7.31,0.10,0.69,3.98,2.61,6.95,7.69,1.82,0.11,6.02,6.28,9.10,2.88,1.11,2.09,0.17,2.11,0.07 };
double FrTxt[];

void Filtrare(char s[])
{
    char a[] = ".,!?*/;'-_(){}[]|:<>&";
    for (int i = 0; i < strlen(s); i++)
    {
        if (s[i] >= 'a' && s[i] <= 'z')
            s[i] = s[i] - 32;
        else
            if ((s[i] >= '0' && s[i] <= '9') || s[i] == ' ' || strchr(a, s[i]) != 0)
            {
                strcpy(s + i, s + i + 1);
                i--;
            }
    }
}

void Criptare(char s[],char key[])
{
    int p=0, q=0,ok;
    int m = strlen(key);
    for ( int i=0; i < strlen(s); i++)
    {
        ok = 0;
        for ( int j = 0; j < strlen(alfabet)&&ok==0; j++)
            if (alfabet[j] == s[i])
            {
                p = j;
                ok=1;
               // break;
            }
        ok = 0;
        for (int k = 0; k < strlen(alfabet)&&ok==0; k++)
            if (alfabet[k] == key[i % m])
            {
                q = k;
                ok=1;
               // break;
            }
        ok = p + q;
       // ok=p+q+1; 
        s[i] = alfabet[ok % 26];
    }

}
void Majuscule(char key[])
{
    for (int i = 0; i < strlen(key); i++)
    {
        if(key[i]>='a'&& key[i]<='z')
           key[i] = key[i] - 32;
    }
}

int LungimeCheie(char s[])
{
    int nr, ok=1, f1,f2,index=0;
    double IC;
    int alfa = strlen(s);
    char c;
    for (int m = 1; m < 20; m++)
    {
        for (int k = 1; k <= m && ok==1; k++)
        {
            IC = 0;
            index = 0;
            for ( c = 'A'; c <= 'Z'; c++)
            {
                nr = 0;
                for (int i = k-1; i < strlen(s); i + m)
                {
                    if (s[i] == c)
                        nr++;
                }
                FrTxt[index] = nr / alfa;
                index++;
                f1 = nr * (nr - 1);
                f2 = alfa * (alfa - 1);
                IC = IC +(f1 / f2);
            }
            if (IC > 0.073 || IC < 0.058)
            {
                ok = 0;
            }
        }
        if (ok == 1)
        {
            return m;
           // break;
        }
      
    }
    return 0;
}

void DeterminaCheia(char s[], int m,char newKey[])
{
    double MIC;
    int indexA = 0, ok=0;
    int shift;
    for (int k = 1; k <= m; k++)
    {
            shift = -1;
            do {
                shift++;
                for (indexA = 0; indexA < 26; indexA++)
                {
                    MIC = MIC + (FrEn[shift] * FrTxt[indexA]);
                }
                if (MIC > 0.058 && MIC < 0.073)
                    ok = 1;
            } while (ok == 1);
            newKey[k - 1] = alfabet[25-shift];
    }
}

void Decriptare(char s[],char newKey[])
{
    int p = 0, q = 0, ok;
    int m = strlen(newKey);
    for (int i = 0; i < strlen(s); i++)
    {
        ok = 0;
        for (int j = 0; j < strlen(alfabet) && ok == 0; j++)
            if (alfabet[j] == s[i])
            {
                p = j;
                ok = 1;
                // break;
            }
        ok = 0;
        for (int k = 0; k < strlen(alfabet) && ok == 0; k++)
            if (alfabet[k] == newKey[i % m])
            {
                q = k;
                ok = 1;
                // break;
            }
        ok = p - q;
        // ok=p-q-1; 
        s[i] = alfabet[ok % 26];
    }
}

int main()
{
    char s[1000];
    char key[20];
    char newKey[20];
    int m;
    cin.getline(s,1000);
    Filtrare(s);
    cout << s << endl;
    cout << "Introduceti cheia" << endl;
    cin >> key;
    Majuscule(key);
    Criptare(s,key);
    cout<< s;
    m = LungimeCheie(s);
    cout << "Lungimea cheii este " << m;
    DeterminaCheia(s,m,newKey);
    cout << "Cheia este: "<<newKey << endl;
    Decriptare(s,newKey);
    cout << "Textul decriptat:" << endl;
    cout << s;
	return 0;
}