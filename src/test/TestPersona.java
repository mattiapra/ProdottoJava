package test;

import model.Indirizzo;
import model.Persona;

public class TestPersona {
    public static void run(Persona amore, Persona mattia, Indirizzo casa) {
        System.out.println("\nTEST PERSONE:");

        amore.setIndirizzo(casa);

        System.out.println(amore);
        System.out.println(mattia);

        System.out.println("Ci sono " + Persona.getInstancesCount() + " persone");
    }
}
