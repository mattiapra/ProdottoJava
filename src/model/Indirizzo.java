package model;

import utils.StringUtils;

/**
 * Ho deciso di implementare una classe <i>Indirizzo</i> in quanto può essere importante vederlo come un'entità separata
 * e più complessa di una semplice Stringa
 */

public class Indirizzo {
    private String via;
    private String citta;
    private String nazione;

    public Indirizzo(String via, String citta, String nazione) {
        this.via = via;
        this.citta = citta;
        this.nazione = nazione;
    }

    public String getVia() { return this.via; }
    public String getCitta() { return this.citta; }
    public String getNazione() { return this.nazione; }

    @Override
    public String toString() { return "Indirizzo: " + StringUtils.wrapIntoQuotes(this.via + ", " + this.citta + " (" + this.nazione + ")"); }
}
