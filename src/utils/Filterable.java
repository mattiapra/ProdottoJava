package utils;

import java.util.ArrayList;

/**
 * Interfaccia per la Lambda function usata nel filtro dell'ArrayList (in {@linkplain ArrayListUtils#filter(ArrayList, Filterable)})
 */

public interface Filterable<T> {
    boolean keep(T t);
}
