package test;

import model.Exceptions.NotAllowedException;
import model.Persona;
import model.Prodotto.ProdottoElettronico;

public class TestProdottoElettronico {
    public static void run(ProdottoElettronico galaxyBook, Persona amore) {
        System.out.println("\nTEST PRODOTTI ELETTRONICI:");
        try {
            galaxyBook.setDipendente(amore);
        } catch (NotAllowedException e) {
            System.out.println("Errore nella modifica del proprietario di galaxyBook: " + e.getMessage());
        }

        System.out.println(galaxyBook);
    }
}
