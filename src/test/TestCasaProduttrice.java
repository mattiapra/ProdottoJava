package test;

import model.CasaProduttrice;
import model.Indirizzo;

public class TestCasaProduttrice {
    public static void run(CasaProduttrice tiger, Indirizzo scuola) {
        System.out.println("\nTEST CASE PRODUTTRICI:");

        tiger.setSede(scuola);
        tiger.setNome("Tiger S.p.A.");

        System.out.println(tiger);
    }
}
