package Zadanie1;

import java.util.Random;

public class Test {
    private static int ileWiadomosci = 1000;
    private static int ileProducentow = 20;
    private static int ileKonsumentow = 40;
    private static int rozmiarBuffora = 10;

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Buffor buff = new Buffor(rozmiarBuffora);
        Producent[] producenci = new Producent[ileProducentow];
        Konsument[] konsumenci= new Konsument[ileKonsumentow];
        for(int i=0;i<ileProducentow;i++){
            int ileWiadomosciNajendego = ileWiadomosci/ileProducentow;
            producenci[i] = new Producent(i,random,ileWiadomosciNajendego,buff);
            producenci[i].start();
        }
        for(int i=0;i<ileKonsumentow;i++){
            int ileWiadomosciNajendego = ileWiadomosci/ileKonsumentow;
            konsumenci[i] = new Konsument(i,random,ileWiadomosciNajendego,buff);
            konsumenci[i].start();
        }
        for (Producent p :producenci) {
            p.join();
        }
        for(Konsument k:konsumenci){
            k.join();
        }



    }

}
