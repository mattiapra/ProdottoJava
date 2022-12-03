package model.Inventario;
import model.*;
import model.Exceptions.NotAllowedException;
import model.Prodotto.Prodotto;

/**
 * @see AbstractInventario
 */

public interface InventarioAssignmentAPI {
    // Getter e setter per i suoi attributi:
    String getNome();
    Persona getTitolare();
    Indirizzo getIndirizzo();
    void setNome(String nome);
    void setTitolare(Persona titolare);
    void setIndirizzo(Indirizzo indirizzo);

    // Visualizzare il dipendente cui è assegnato un oggetto dato il suo indice:
    Persona getDipendenteByIndex(int index);

    // Cercare tutte gli oggetti (i loro indici) assegnati da una certa persona:
    int[] getProdottoIndexesByDipendente(Persona p);

    // Stampare i dati di ognuno degli oggetti:
    void printAllDeep();

    // Modificare l’assegnatario di un oggetto dato il suo indice:
    void setDipendenteAtIndex(int index, Persona p) throws NotAllowedException;

    // Trovare il prezzo di un oggetto dato indice:
    float getPrezzoByIndex(int index);

    // Trovare il totale dei prezzi di tutti gli oggetti nell’inventario:
    float getPrezzoTotale();

    // Trovare l’oggetto con prezzo massimo e quello con prezzo minimo
    Prodotto getPiuCostoso();
    Prodotto getMenoCostoso();

    // Trovare tutti gli oggetti data la marca
    Prodotto[] getProdottiByCasaProduttrice(CasaProduttrice c);

    // Trovare gli oggetti che non hanno mai cambiato assegnatario (unico assegnatario), restituendo il vettore di tali oggetti
    Prodotto[] getProdottiFedeli();

    // Ordinare gli oggetti in base al loro prezzo
    void sort();
}
