package model.Prodotto;

import model.CasaProduttrice;
import model.Exceptions.NotAllowedException;
import model.Persona;

public class ProdottoElettronico extends Prodotto {
    private float autonomia;
    private float tempoRicarica;

    public ProdottoElettronico(String nome, Persona dipendente, CasaProduttrice produttore, float prezzo, float autonomia, float tempoRicarica) {
        super(nome, dipendente, produttore, prezzo);

        this.autonomia = autonomia;
        this.tempoRicarica = tempoRicarica;
    }

    public float getAutonomia() { return this.autonomia; }
    public float getTempoRicarica() { return this.tempoRicarica; }

    @Override
    public void setDipendente(Persona dipendente) throws NotAllowedException {
        throw new NotAllowedException("ProdottoElettronico non accetta il cambio del proprietario");
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tTipo prodotto: ProdottoElettronico" +
                "\n\tAutonomia: " + this.autonomia +
                "\n\tTempo di ricarica: " + this.tempoRicarica;
    }
}
