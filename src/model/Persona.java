package model;

import model.Identifiable.*;
import utils.StringUtils;

public class Persona extends Identifiable {
    private static IdentifiableManager personaIDs = new IdentifiableManager();

    private String cognome;
    private Indirizzo indirizzo;

    public Persona(String cognome, Indirizzo indirizzo) {
        super(personaIDs);

        this.cognome = cognome;
        this.indirizzo = indirizzo;
    }

    public String getCognome() { return this.cognome; }
    public Indirizzo getIndirizzo() { return this.indirizzo; }
    public void setIndirizzo(Indirizzo indirizzo) { this.indirizzo = indirizzo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;

        return this.getID() == persona.getID();
    }

    @Override
    public String toString() {
        return "Persona " + super.toString() + " >" +
                "\n\tCognome: " + StringUtils.wrapIntoQuotes(this.cognome) +
                "\n\t" + this.indirizzo;
    }

    public static int getInstancesCount() { return Persona.personaIDs.getCounter(); }
}
