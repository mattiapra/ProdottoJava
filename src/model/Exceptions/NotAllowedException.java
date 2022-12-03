package model.Exceptions;

/**
 * Ho implementato un estensione di {@linkplain Exception} per poter "catturare" nel programma solo quest'eccezione e
 * conseguentemente gestirla, facendolo crashare nel caso di altre eccezioni non previste.
 */

public class NotAllowedException extends Exception {
    public NotAllowedException() {
        super("Operazione non permessa");
    }

    public NotAllowedException(String message) {
        super("Operazione non permessa: " + message);
    }
}
