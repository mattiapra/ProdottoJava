package model.Inventario;

import model.*;
import model.Prodotto.Prodotto;
import utils.ArrayListUtils;
import java.util.Collections;

/**
 * @see AbstractInventario
 */

public class Inventario extends AbstractInventario {

    public Inventario(String nome, Persona titolare, Indirizzo indirizzo) {
        super(nome, titolare, indirizzo);
    }

    @Override
    public Prodotto getPiuCostoso() {
        return Collections.max(this.lista);
    }

    @Override
    public Prodotto getMenoCostoso() {
        return Collections.min(this.lista);
    }

    @Override
    public Prodotto[] getProdottiByCasaProduttrice(CasaProduttrice c) {
        return ArrayListUtils.filter(this.lista, prodotto -> c.equals(prodotto.getProduttore())).toArray(new Prodotto[0]);
    }

    @Override
    public Prodotto[] getProdottiFedeli() {
        return ArrayListUtils.filter(this.lista, prodotto -> prodotto.getUtilizzatori() <= 1).toArray(new Prodotto[0]);
    }

    @Override
    public void sort() {
        Collections.sort(this.lista);
    }
}
