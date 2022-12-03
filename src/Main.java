import model.*;
import model.Inventario.*;
import model.Prodotto.*;
import test.*;

/**
 * La suddivisione in piccoli moduli di test, per ciascuna classe, è giustificata dal fatto del non voler gestire un {@code Main}
 * eccessivamente lungo e di difficile lettura/gestione.
 *
 * <p>Ciascun <code>Test.run</code> riceve in input ciò che gli serve a testare correttamente l'implementazione della classe, così come da
 * specifiche dell'assignment
 *
 * <p>Ogni classe "non espressamente richiesta" contiene la ratio per la quale ho deciso di implementarla, in particolare:
 * <ul>
 *     <li>{@link model.Indirizzo} per gli indirizzi</li>
 *     <li>{@link utils.StringUtils Utils} per metodi di utilità generica</li>
 *     <li>{@link model.Identifiable.Identifiable Identifiable} per la gestione degli ID</li>
 *     <li>{@link model.Exceptions.NotAllowedException NotAllowedException} per la gestione di un'eccezione più specifica</li>
 *     <li>{@link model.Inventario.AbstractInventario AbstractInventario} per l'implementazione dell'inventario</li>
 * </ul>
 */

public class Main {
    public static void main(String[] args) {
        // === INDIRIZZI ===
        Indirizzo casa = new Indirizzo("Corso Brescia 30", "Torino", "Italia");
        Indirizzo scuola = new Indirizzo("Lungo Dora Siena 100", "Torino", "Italia");
        TestIndirizzo.run(casa, scuola); // Esegue solo il print degli oggetti

        // === PERSONE ===
        Persona amore = new Persona("Stasi", scuola);
        Persona mattia = new Persona("Prà", casa);
        TestPersona.run(amore, mattia, casa); // Testa un setter

        // === CASE PRODUTTRICI ===
        CasaProduttrice ikea = new CasaProduttrice("IKEA", scuola);
        CasaProduttrice samsung = new CasaProduttrice("Samsung", scuola);
        CasaProduttrice tiger = new CasaProduttrice("Tiger", casa);
        TestCasaProduttrice.run(tiger, scuola); // Testa due setter

        // === PRODOTTI ===
        Prodotto penna = new Prodotto("Penna", tiger, 0.75f);
        Prodotto matita = new Prodotto("Matita", amore, tiger, 1f);
        TestProdotto.run(penna, matita, amore, mattia); // Testa i metodi modificaPrezzo e setProprietario (con aumento degli utilizzatori)

        ProdottoElettronico galaxyBook = new ProdottoElettronico("Galaxy Book", mattia, samsung, 10, 12, 20);
        TestProdottoElettronico.run(galaxyBook, amore); // Testa l'exception nel cambioProprietario, che non deve essere permesso

        Mobile tavolino = new Mobile("Tavolino", amore, ikea, 11, "Legno", "26x12x06");
        TestMobile.run(tavolino); // Testa il valoreTotale con IVA al 10%

        // === INVENTARIO ===
        // Per testare Inventario e InventarioManuale basta cambiare solo il costruttore da InventarioManuale a Inventario e viceversa
        AbstractInventario inventario = new InventarioManuale("Inventario Ufficio", amore, scuola);
        TestInventario.run(inventario, new Prodotto[] { matita, tavolino, galaxyBook, penna }); // Testa tutti i metodi dell'inventario
    }
}