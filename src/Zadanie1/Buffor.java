package Zadanie1;


import java.io.BufferedReader;
import java.util.concurrent.Semaphore;

public class Buffor {
    private int rozmiarBuffora;
    private String[] bufor;
    private Semaphore wolne;
    private Semaphore zajete;
    private int iteratorProducentow;
    private int iteratorKonsumentow;
    private Semaphore chronWpisywanie;
    private Semaphore chronWczytywanie;

    Buffor(int rozmiarBuffora) {
        this.rozmiarBuffora = rozmiarBuffora;
        this.bufor = new String[rozmiarBuffora];
        this.wolne = new Semaphore(rozmiarBuffora);
        this.zajete = new Semaphore(0);
        this.iteratorKonsumentow = 0;
        this.iteratorProducentow = 0;
        this.chronWczytywanie = new Semaphore(1);
        this.chronWpisywanie = new Semaphore(1);
    }

    void wstaw(String napis) throws InterruptedException {
        wolne.acquire();
        chronWpisywanie.acquire();
        bufor[iteratorProducentow] =napis;
        iteratorProducentow = (iteratorProducentow+1)%rozmiarBuffora;
        chronWpisywanie.release();
        zajete.release();
    }
    String pobierz() throws InterruptedException {
        zajete.acquire();
        chronWczytywanie.acquire();
        String element = bufor[iteratorKonsumentow];
        iteratorKonsumentow = (iteratorKonsumentow+1)%rozmiarBuffora;
        chronWczytywanie.release();
        wolne.release();
        return element;
    }
}

