package test;

import model.Prodotto.Mobile;

public class TestMobile {
    public static void run(Mobile tavolino) {
        System.out.println("\nTEST MOBILI:");
        /*
            Per testare l'override del metodo valoreTotale stampo direttamente l'intero oggetto in quanto il toString invoca anche
            il metodo valoreTotale
         */
        System.out.println(tavolino);
    }
}
