package model.Prodotto;

import utils.Filterable;

import java.util.ArrayList;

public class ListaProdotti extends ArrayList<Prodotto> {
    public ListaProdotti() {
        super();
    }

    /**
     * Filtra la lista: per ogni elemento viene richiamato il {@code filter} e se ritorna True, l'elemento viene mantenuto,
     * altrimenti viene scartato.
     *
     * @param filter {@code Filterable} lambda function che ritorna un booleano, usato come filtro
     * @return Una nuova istanza della {@code ListaProdotti} filtrata
     */
    public ListaProdotti filter(Filterable<Prodotto> filter) {
        ListaProdotti temp = new ListaProdotti();
        for (Prodotto p : this) {
            if (filter.keep(p)) {
                temp.add(p);
            }
        }
        return temp;
    }
}
