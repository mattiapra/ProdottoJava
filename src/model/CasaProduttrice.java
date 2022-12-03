package model;

import utils.StringUtils;

public class CasaProduttrice {
    private String nome;
    private Indirizzo sede;

    public CasaProduttrice(String nome, Indirizzo sede) {
        this.nome = nome;
        this.sede = sede;
    }

    public String getNome() { return this.nome; }
    public Indirizzo getSede() { return this.sede; }

    public void setNome(String nome) { this.nome = nome; }
    public void setSede(Indirizzo sede) { this.sede = sede; }

    @Override
    public String toString() {
        return "CasaProduttrice >" +
                "\n\tNome: " + StringUtils.wrapIntoQuotes(this.nome) +
                "\n\t" + this.sede;
    }
}
