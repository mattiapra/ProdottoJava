package test;

import model.CasaProduttrice;
import model.Exceptions.NotAllowedException;
import model.Inventario.AbstractInventario;
import model.Persona;
import model.Prodotto.Prodotto;

import java.util.Arrays;

public class TestInventario {
    public static void run(AbstractInventario inventario, Prodotto[] lista) {
        System.out.println("\nTEST INVENTARIO:");
        // Solo per inizializzare l'inventario e preparare alcune variabili per i test
        for (Prodotto p : lista) {
            inventario.push(p);
        }
        Persona amore = inventario.getProdotto(0).getDipendente();
        CasaProduttrice tiger = inventario.getProdotto(0).getProduttore();

        // Test:
        System.out.println("Prima di ordinare: " + inventario.getListAsString());
        inventario.sort();
        System.out.println("Dopo l'ordinamento: " + inventario.getListAsString());

        try {
            inventario.setDipendenteAtIndex(3, amore);
            System.out.println("Modifica proprietario all'indice 3 riuscita");
        } catch (NotAllowedException e) {
            System.out.println("Errore nella modifica del proprietario all'indice 3 (" + inventario.getProdotto(3).getNome() + "): " + e.getMessage());
        }

        int indexToTest = 3;
        String prodottoNameAtIndex = inventario.getProdotto(indexToTest).getNome();

        System.out.println("Dipendente indice 3 (" + prodottoNameAtIndex + "): " + inventario.getDipendenteByIndex(indexToTest).getCognome());
        System.out.println("Prezzo indice 3 (" + prodottoNameAtIndex + "): " + Prodotto.getPriceString(inventario.getPrezzoByIndex(indexToTest)));
        System.out.println("Valore totale inventario: " + Prodotto.getPriceString(inventario.getPrezzoTotale()));
        System.out.println("Indici dipendente amore: " + Arrays.toString(inventario.getProdottoIndexesByDipendente(amore)));
        System.out.println("Meno costoso: " + inventario.getMenoCostoso().getNome());
        System.out.println("Piu costoso: " + inventario.getPiuCostoso().getNome());
        System.out.println("Prodotti Tiger: " + AbstractInventario.getListAsString(inventario.getProdottiByCasaProduttrice(tiger)));
        System.out.println("Prodotti fedeli: " + AbstractInventario.getListAsString(inventario.getProdottiFedeli()));

        inventario.printAllDeep();

        inventario.remove();
        System.out.println("Dopo rimozione ultimo elemento: " + inventario.getListAsString());
        inventario.remove(0);
        System.out.println("Dopo rimozione primo elemento: " + inventario.getListAsString());
    }
}
