# I) Generatorul pseudorandom bazat pe LFSR 
#- implementarea efectiva (aveti grija la initializarea corecta a generatorului (alegerea functiei de
feedback si a starii initiale astfel incat perioada sa fie maxima) – a se vedea
paginile 195-197 din http://cacr.uwaterloo.ca/hac/about/chap6.pdf
(aveti acolo si un exemplu (6.10)), iar pentru lista polinoamelor primitive, consultati Table 4.8 din http://cacr.uwaterloo.ca/hac/about/chap4.pdf)
#- efectuati comparatii (timp) cu generatoarele precedente (de la Tema 2)




# II) Criptosistemul RC4 
#- implementarea efectiva
#- efectuati teste/experimente privind existenta bias-urilor (confirmati experimental ca probabilitatea ca al doilea byte generat sa fie 0 este aproximativ 1/(128)) – a se vedea Sectiunea 3.1 din https://tls.mbed.org/public/RC4biases.pdf