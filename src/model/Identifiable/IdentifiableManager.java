package model.Identifiable;

/**
 * Questa classe è sostanzialmente un'alternativa al semplice tipo int per la gestione dei contatori statici nelle sottoclassi.
 * Così facendo posso gestire in modo più semplice l'implementazione del sistema di generazione degli ID e del contatore di istanze attive
 */

public class IdentifiableManager {
    private int AUTO_INCREMENT;
    private int COUNTER;

    public IdentifiableManager() {
        this.AUTO_INCREMENT = 0;
        this.COUNTER = 0;
    }

    public int get() {
        this.AUTO_INCREMENT += 1;
        this.COUNTER += 1;

        return this.AUTO_INCREMENT - 1;
    }

    public int getCounter() { return this.COUNTER; }
    public void die() { this.COUNTER -= 1; }
}
