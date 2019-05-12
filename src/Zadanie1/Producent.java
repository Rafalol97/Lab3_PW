package Zadanie1;

import java.util.Random;

public class Producent extends  Thread {

    private int nr;
    private Random random;
    private int ileWiadomosci;
    private Buffor buff;

    Producent( int nr, Random random, int ileWiadomosci, Buffor buff){
    super(String.valueOf(nr));
        this.nr = nr;
        this.random = random;
        this.ileWiadomosci = ileWiadomosci;
        this.buff = buff;
    }
    public void run(){
        for(int i=0;i<ileWiadomosci;i++){
            sleepRandom();
            String string = String.format("[P-%d : %d] => %s", nr, i, random.nextInt(100));
            sleepRandom();
            try{
               buff.wstaw(string);
            }
            catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
    private void sleepRandom(){
        try {
            sleep(random.nextInt(10)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
