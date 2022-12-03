package model.Prodotto;

import model.CasaProduttrice;
import model.Exceptions.NotAllowedException;
import model.Identifiable.*;
import model.Persona;
import utils.StringUtils;

public class Prodotto extends Identifiable implements Comparable<Prodotto> {
    private static IdentifiableManager prodottoIDs = new IdentifiableManager();
    public static final float IVA = 22;

    private String nome;
    private Persona dipendente;
    private CasaProduttrice produttore;
    private float prezzo;
    private int utilizzatori;

    public Prodotto(String nome, CasaProduttrice produttore, float prezzo) {
        super(prodottoIDs);

        this.nome = nome;
        this.dipendente = null;
        this.produttore = produttore;
        this.prezzo = prezzo;
        this.utilizzatori = 0;
    }

    public Prodotto(String nome, Persona dipendente, CasaProduttrice produttore, float prezzo) {
        super(prodottoIDs);

        this.nome = nome;
        this.dipendente = dipendente;
        this.produttore = produttore;
        this.prezzo = prezzo;
        this.utilizzatori = 1;
    }

    private void incrementOwners() { this.utilizzatori += 1; }

    public String getNome() { return this.nome; }
    public Persona getDipendente() { return this.dipendente; }
    public CasaProduttrice getProduttore() { return this.produttore; }
    public float getPrezzo() { return this.prezzo; }
    public int getUtilizzatori() { return this.utilizzatori; }

    public void setProduttore(CasaProduttrice produttore) { this.produttore = produttore; }
    public void setPrezzo(float prezzo) { this.prezzo = prezzo; }

    public void setDipendente(Persona dipendente) throws NotAllowedException {
        // Controlliamo che non sia lo stesso dipendente, in quanto non ci sarebbe un reale cambio di proprietà in quel caso
        if (this.dipendente == null || !this.dipendente.equals(dipendente)) {
            this.dipendente = dipendente;
            this.incrementOwners();
        }
    }

    public void modificaPrezzo(float modificatore) throws NotAllowedException {
        if (this.prezzo + modificatore >= 0) {
            this.prezzo += modificatore;
        } else {
            throw new NotAllowedException("Il prezzo non può scendere sotto lo 0, " +
                "ma lo si voleva ridurre da: " + this.prezzo + " a: " + (this.prezzo + modificatore));
        }
    }

    /**
     * Metodo per modificare il prezzo base del Prodotto
     *
     * @param modificatore La stringa usata come modificatore, che può essere sia una percentuale (positiva o negativa) e sia un numero
     * @throws NotAllowedException Nel caso in cui si tenti di scendere sotto 0
     */

    public void modificaPrezzo(String modificatore) throws NotAllowedException {
        boolean isPercentage = modificatore.contains("%");
        float modifier = Float.parseFloat(modificatore.replace("%", ""));

        if (isPercentage) modifier = this.prezzo * modifier / 100;

        this.modificaPrezzo(modifier);
    }

    public float valoreTotale() {
        return this.prezzo * ((Prodotto.IVA / 100) + 1);
    }

    @Override
    public String toString() {
        return "Prodotto " + super.toString() + " >" +
                "\n\tNome: " + StringUtils.wrapIntoQuotes(this.nome) +
                "\n\tDipendente: " + StringUtils.wrapIntoQuotes(this.dipendente != null ? this.dipendente.getCognome() : "Nessuno") +
                "\n\tProduttore: " + StringUtils.wrapIntoQuotes(this.produttore.getNome()) +
                "\n\tPrezzo: " + Prodotto.getPriceString(this.prezzo) +
                "\n\tPrezzo con IVA: " + Prodotto.getPriceString(this.valoreTotale()) +
                "\n\tNumero utilizzatori: " + this.utilizzatori;
    }

    public String toFlatString() {
        return this.nome + " (" + Prodotto.getPriceString(this.valoreTotale()) + ")";
    }

    public static String getPriceString(float price) {
        return String.format("%.2f€", price);
    }

    public static int getInstancesCount() { return Prodotto.prodottoIDs.getCounter(); }

    @Override
    public int compareTo(Prodotto o) {
        float diff = this.valoreTotale() - o.valoreTotale();
        if (diff > 0) return 1;
        else if (diff < 0) return -1;
        else return 0;

        // Oppure: return Float.compare(this.valoreTotale(), o.valoreTotale());
    }
}
