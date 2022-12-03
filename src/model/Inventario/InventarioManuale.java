package model.Inventario;
import model.*;
import model.Prodotto.Prodotto;

import java.util.Arrays;

/**
 * Ho usato un approccio iterativo per quasi tutti i metodi, tranne che per il metodo che trova il massimo, per il quale ho voluto
 * dimostrare un approccio ricorsivo ({@linkplain #getPiuCostoso()})
 * <p>
 * @see AbstractInventario
 */

public class InventarioManuale extends AbstractInventario {

    public InventarioManuale(String nome, Persona titolare, Indirizzo indirizzo) {
        super(nome, titolare, indirizzo);
    }

    private Prodotto getPiuCostosoRicorsivo(int i) {
        if (i == this.lista.size() - 1) return this.lista.get(this.lista.size() - 1);
        else {
            Prodotto piuCostoso = this.getPiuCostosoRicorsivo(i + 1);
            Prodotto curr = this.lista.get(i);
            return piuCostoso.compareTo(curr) > 0 ? piuCostoso : curr;
        }
    }

    // Questo metodo diventa un Wrapper per la ricorsione, senza esporre vulnerabilità dovendo passare 0 come primo parametro di i
    @Override
    public Prodotto getPiuCostoso() {
        return this.getPiuCostosoRicorsivo(0);
    }

    @Override
    public Prodotto getMenoCostoso() {
        Prodotto min = this.lista.get(0);
        for (int i = 1; i < this.lista.size(); i++) {
            Prodotto prodotto = this.lista.get(i);
            if (prodotto.compareTo(min) < 0) min = prodotto;
        }
        return min;
    }

    @Override
    public Prodotto[] getProdottiByCasaProduttrice(CasaProduttrice c) {
        Prodotto[] prodotti = new Prodotto[this.lista.size()];
        int j = 0;
        for (int i = 0; i < prodotti.length; i++) {
            Prodotto prodotto = this.lista.get(i);
            if (c.equals(prodotto.getProduttore())) {
                prodotti[j] = prodotto;
                j++;
            }
        }

        // Arrays.copyOf ritorna una copia dell'array, lungo J. L'IDE stesso suggerisce di usarlo al posto della copia manuale
        return Arrays.copyOf(prodotti, j);
    }

    @Override
    public Prodotto[] getProdottiFedeli() {
        Prodotto[] prodotti = new Prodotto[this.lista.size()];
        int j = 0;
        for (int i = 0; i < prodotti.length; i++) {
            Prodotto prodotto = this.lista.get(i);
            if (prodotto.getUtilizzatori() <= 1) {
                prodotti[j] = prodotto;
                j++;
            }
        }

        // Alternativa ad Arrays.copyOf();
        Prodotto[] temp = new Prodotto[j];
        for (int i = 0; i < j; i++) {
            temp[i] = prodotti[i];
        }

        return temp;
    }

    @Override
    public void sort() {
        // BubbleSort
        int n = this.lista.size() - 1;
        int lastSwap = n;
        Prodotto temp;

        while (lastSwap > 0) {
            lastSwap = 0;
            for (int i = 0; i < n; i++) {
                Prodotto curr = this.lista.get(i);
                Prodotto next = this.lista.get(i + 1);
                if (curr.compareTo(next) > 0) {
                    temp = curr;
                    this.lista.set(i, next);
                    this.lista.set(i + 1, temp);
                    lastSwap = i;
                }
            }
            n = lastSwap;
        }
    }
}
