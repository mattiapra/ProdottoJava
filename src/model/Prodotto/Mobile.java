package model.Prodotto;

import model.CasaProduttrice;
import model.Persona;
import utils.StringUtils;

public class Mobile extends Prodotto {
    private String materiale;
    private String dimensioni;
    private static final float IVA = 10;

    public Mobile(String nome, CasaProduttrice produttore, float prezzo, String materiale, String dimensioni) {
        super(nome, produttore, prezzo);
        this.materiale = materiale;
        this.dimensioni = dimensioni;
    }

    public Mobile(String nome, Persona dipendente, CasaProduttrice produttore, float prezzo, String materiale, String dimensioni) {
        super(nome, dipendente, produttore, prezzo);
        this.materiale = materiale;
        this.dimensioni = dimensioni;
    }

    public String getMateriale() { return this.materiale; }
    public String getDimensioni() { return this.dimensioni; }

    @Override
    public float valoreTotale() {
        return this.getPrezzo() * ((Mobile.IVA / 100) + 1);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tTipo prodotto: Mobile" +
                "\n\tMateriale: " + StringUtils.wrapIntoQuotes(this.materiale) +
                "\n\tDimensioni: " + StringUtils.wrapIntoQuotes(this.dimensioni);
    }
}
