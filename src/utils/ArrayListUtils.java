package utils;

import java.util.ArrayList;

public class ArrayListUtils {
    /**
     * Filtra un {@code ArrayList}: per ogni elemento viene richiamato il {@code filter} e se ritorna True, l'elemento viene mantenuto,
     * altrimenti viene scartato.
     *
     * @param list L'{@code ArrayList} da filtrare, di qualunque tipo
     * @param filter {@code Filterable} lambda function che ritorna un booleano, usato come filtro
     * @return Una nuova istanza dell'{@code ArrayList} filtrato
     */
    public static <T> ArrayList<T> filter(ArrayList<T> list, Filterable<T> filter) {
        ArrayList<T> temp = new ArrayList<>();
        for (T p : list) {
            if (filter.keep(p)) {
                temp.add(p);
            }
        }
        return temp;
    }
}
