package test;

import model.Exceptions.NotAllowedException;
import model.Persona;
import model.Prodotto.Prodotto;

public class TestProdotto {
    public static void run(Prodotto penna, Prodotto matita, Persona amore, Persona mattia) {
        System.out.println("\nTEST PRODOTTI:");

        try {
            /*
                Ho implementato un Exception anche per la modifica del prezzo in quanto lo trovo semanticamente coerente con il voler "avvisare"
                del fatto che una modificaPrezzo non sia andata a buon fine (nel caso si scenda sotto lo zero)
             */
            penna.modificaPrezzo(-0.25f);
            penna.modificaPrezzo("-150%");
        } catch (NotAllowedException e) {
            System.out.println("Errore nella modifica del prezzo di penna: " + e.getMessage());
        }

        try {
            /*
                In questo caso era necessario un blocco di try (sebbene in questo caso non vada mai in errore) in quanto
                ProdottoElettronico, che è figlio di Prodotto, ha necessità di lanciare un Exception nel metodo e quindi,
                per permettere l'override del metodo, anche il padre secondo la firma lancia un Exception
             */
            matita.setDipendente(mattia);
            matita.setDipendente(mattia);
            matita.setDipendente(mattia);
            matita.setDipendente(amore);
            matita.setDipendente(amore);
            matita.setDipendente(mattia);
            matita.setDipendente(amore);
            matita.modificaPrezzo("-50%");
            matita.modificaPrezzo(0.75f);
        } catch (NotAllowedException ignored) {}

        System.out.println(penna);
        System.out.println(matita);

        System.out.println("Ci sono " + Prodotto.getInstancesCount() + " prodotti");
    }
}
