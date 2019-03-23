/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.util;

/**
 *
 * @author Usuario
 */
public class ConcatenarArrays {
    public static <T> T[] concat (Class<T> klass, T[]... arrays) {
        int length = 0;
        for (T[] array : arrays) { length += array.length; }
        @SuppressWarnings ("unchecked") 
            T[] ret = (T[]) java.lang.reflect.Array.newInstance (klass, length);
        int destPos = 0;
        for (T[] array : arrays) {
            System.arraycopy (array, 0, ret, destPos, array.length);
            destPos += array.length;
        }
        return ret;
    }
}
