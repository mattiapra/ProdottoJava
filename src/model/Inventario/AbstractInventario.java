package model.Inventario;

import model.Exceptions.NotAllowedException;
import model.Indirizzo;
import model.Persona;
import model.Prodotto.Prodotto;
import utils.Filterable;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Ho voluto implementare diversi concetti per questo assignment:
 * <ul>
 *  <li>Un'interfaccia {@link InventarioAssignmentAPI} che "elenca" le API che l'inventario deve necessariamente avere <i>(secondo l'assignment3)</i></li>
 *  <li>
 *      Una gerarchia solo per raggruppare codice, in quanto ho voluto creare due versione dell'inventario
 *      (<i>Inventario</i> e <i>InventarioManuale</i>) solo per dimostrare come si potessero ottenere gli stessi risultasti
 *      in modo diametralmente diversi:
 *      <ul>
 *          <li>{@link Inventario} poggia su librerie predefinite (classe <i>Collections</i>) e l'uso di un metodo di
 *          Utils scritto da me ({@link utils.ArrayListUtils#filter(ArrayList, Filterable)}) per filtrare l'ArrayList</li>
 *          <li>{@link InventarioManuale} usa metodi creati da me scrivendo manualmente i vari algoritmi</li>
 *      </ul>
 *      <li><i>AbstractInventario</i> è una classe astratta estesa dalle due implementazioni di Inventario e contiene il codice comune ai due inventari</li>
 *  </li>
 * </ul>
 */

public abstract class AbstractInventario implements InventarioAssignmentAPI {
    protected String nome;
    protected Persona titolare;
    protected Indirizzo indirizzo;
    protected ArrayList<Prodotto> lista;

    protected AbstractInventario(String nome, Persona titolare, Indirizzo indirizzo) {
        this.nome = nome;
        this.titolare = titolare;
        this.indirizzo = indirizzo;
        this.lista = new ArrayList<>();
    }

    @Override
    public String getNome() { return this.nome; }
    @Override
    public Persona getTitolare() { return this.titolare; }
    @Override
    public Indirizzo getIndirizzo() { return this.indirizzo; }
    @Override
    public void setNome(String nome) { this.nome = nome; }
    @Override
    public void setTitolare(Persona titolare) { this.titolare = titolare; }
    @Override
    public void setIndirizzo(Indirizzo indirizzo) { this.indirizzo = indirizzo; }

    public void push(Prodotto p) {
        this.lista.add(p);
    }
    public Prodotto remove() {
        return this.lista.remove(this.lista.size() - 1);
    }
    public Prodotto remove(int index) {
        return this.lista.remove(index);
    }
    public Prodotto getProdotto(int index) {
        return this.lista.get(index);
    }

    @Override
    public Persona getDipendenteByIndex(int index) {
        return this.lista.get(index).getDipendente();
    }

    @Override
    public float getPrezzoByIndex(int index) {
        return this.lista.get(index).getPrezzo();
    }

    @Override
    public float getPrezzoTotale() {
        float somma = 0;
        for (Prodotto prodotto : this.lista) {
            somma += prodotto.getPrezzo();
        }
        return somma;
    }

    @Override
    public int[] getProdottoIndexesByDipendente(Persona p) {
        int[] indici = new int[this.lista.size()];
        int j = 0;
        for (int i = 0; i < indici.length; i++) {
            if (p != null && p.equals(this.lista.get(i).getDipendente())) {
                indici[j] = i;
                j++;
            }
        }

        return Arrays.copyOf(indici, j);
    }

    // Wrapper API che pubblicamente vieta l'ottenimento dell'array formattato usando toString
    public String getListAsString() {
        return this.getListAsString(false);
    }
    private String getListAsString(boolean useToString) {
        return AbstractInventario.getListAsString(this.lista.toArray(Prodotto[]::new), useToString);
    }

    // Stessa logica del Wrapper API visto sopra, ma applicata al metodo statico
    public static String getListAsString(Prodotto[] array) {
        return AbstractInventario.getListAsString(array, false);
    }
    private static String getListAsString(Prodotto[] array, boolean useToString) {
        StringBuilder output = new StringBuilder();
        int length = array.length;

        if (!useToString) output.append("[");

        for (int i = 0; i < length; i++) {
            Prodotto prodotto = array[i];
            output.append(useToString ? prodotto.toString() : prodotto.toFlatString());

            if (i < length - 1) {
                output.append(useToString ? "\n" : ", ");
            }
        }

        if (!useToString) output.append("]");

        return output.toString();
    }

    @Override
    public void printAllDeep() {
        System.out.println("=== INIZIO Inventario ===");
        System.out.println(this.getListAsString(true));
        System.out.println("=== FINE Inventario ===");
    }

    @Override
    public void setDipendenteAtIndex(int index, Persona p) throws NotAllowedException {
        this.lista.get(index).setDipendente(p);
    }

    @Override
    public String toString() {
        return "Inventario >" +
            "\n\tNome: " + StringUtils.wrapIntoQuotes(this.nome) +
            "\n\tTitolare: " + StringUtils.wrapIntoQuotes(this.titolare.getCognome()) +
            "\n\tIndirizzo: " + this.indirizzo +
            "\n\tProdotti: " + this.getListAsString() +
            "\n\tTotale prodotti: " + this.lista.size();
    }
}
