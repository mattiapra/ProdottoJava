package model.Identifiable;

/**
 * Questa classe (unitamente alla classe {@linkplain IdentifiableManager}) contiene l'astrazione del sistema di assegnamento degli ID, implementata per avere un punto centrale
 * nel quale gestire l'assegnamento degli ID e, in ottica di sviluppo progressivo, avere terreno fertile per poter per esempio
 * passare a una gestionde gli ID basata su sistemi più evoluti (hash, auto_increment da un database, ...)
*/

public abstract class Identifiable /* implements AutoCloseable */ {
    private int ID;
    private IdentifiableManager i;

    /**
     * Il parametro deve essere di tipo {@link IdentifiableManager} e deve essere salvato in un attributo d'istanza per
     * poter poi essere richiamato successivamente (per esempio nel {@code finalize/close}).
     * <p>Nel caso l'implementazione del metodo {@code finalize/close} non sia necessaria, si poteva anche evitare di
     * passare un <code>IdentifiableManager</code> e passare direttamente il valore dell'ID (facendo per esempio {@code super(i.get())} )
     */
    protected Identifiable(IdentifiableManager i) {
        this.i = i;
        this.ID = i.get();
    }

    public int getID() { return this.ID; }

    /**
     * Sono a conoscenza del fatto che il metodo sia deprecato ed in qualche modo "inutile" da inserire in un progetto
     * di tale portata in quanto impossibile controllare il garbage collector ma al fine di mostrare una possibile implementazione
     * del metodo {@code die()} ho deciso di inserirlo lo stesso.
     * <p>Un modo più gestibile per testarne l'implementazione è quello di implementare l'interfaccia {@link AutoCloseable} e
     * fare l'override del metodo {@code close()} (con lo stesso corpo del metodo {@code finalize()}), il quale, se l'oggetto
     * viene istanziato all'interno di un blocco del tipo "try-with-resources", al termine del blocco di try, invocherà il
     * metodo {@code close()} in modo automatico.
     * <pre> @Override
     *public void close() { this.i.die(); }</pre>
     */
    @Override
    public void finalize() { this.i.die(); }

    @Override
    public String toString() {
        return "ID: " + this.ID;
    }
}
