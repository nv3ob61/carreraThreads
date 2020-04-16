/* 
 * Copyright (C) 2020 mon_mode   0mon.mode@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.japo.java.libraries;

/**
 *
 * @author Jonsui
 */
public class UtilesGen {

  public static final int MATRICULA_LEN = 8;
  public static final int APUESTA_LEN = 10;
  public static final int DIGI_LEN = 4;
  public static final int CHAR_LEN = 3;
  public static final String LISTA_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static final String LISTA_DIGI = "0123456789";
  
  public static final String APUESTA_CHAR = "ABCDEFGH";
  public static final String APUESTA_DIGI = "01234569";

  //                  formato de la matrícula: 1111-XXX
  public static final String EXP_MATRICULA = "^[0-9]{4}-[A-Z]{3}$";
  //largo del NIA
  public static final int NIA_LONG = 8;
  
  //Se genera una matrícula llamando al constructor por defecto o cuando
  //al parametrizado se le pasa un formato fuera del correcto: 1111-XXX
  public static String generarCarrera() {
    StringBuilder sb = new StringBuilder(MATRICULA_LEN);
    
    sb.append("C");
    //Generamos 4 números  (resta -1 por el guión  (regulero.....)
    for (int i = 0; i < MATRICULA_LEN - CHAR_LEN - 1; i++) {
      int index = (int) (LISTA_DIGI.length() * Math.random());
      sb.append(LISTA_DIGI.charAt(index));
    }
    //Añadimos el guión de la matrícula
//    sb.append('-');

    //Falta generar 3 char y añadirlos al sb
    for (int i = 0; i < MATRICULA_LEN - DIGI_LEN - 1; i++) {
      int index = (int) (LISTA_CHAR.length() * Math.random());
      sb.append(LISTA_CHAR.charAt(index));
    }
    return sb.toString();
  }
  
  public static String generarApuesta() {
    StringBuilder sb = new StringBuilder(APUESTA_LEN);
    
    sb.append("C");
    //Generamos 4 números  (resta -1 por el guión  (regulero.....)
    for (int i = 0; i < APUESTA_LEN - 2; i++) {
      int index = (int) (APUESTA_DIGI.length() * Math.random());
      sb.append(APUESTA_DIGI.charAt(index));
    }
    //Añadimos el guión de la matrícula
//    sb.append('-');

    //Falta generar 3 char y añadirlos al sb
    for (int i = 0; i < APUESTA_LEN - 8; i++) {
      int index = (int) (APUESTA_CHAR.length() * Math.random());
      sb.append(APUESTA_CHAR.charAt(index));
    }
    return sb.toString();
  }

  public static String generarId() {   //generaba NIA aleatorio
    StringBuilder sb = new StringBuilder(NIA_LONG);
    //Genera nia del 00 hasta el 99 en teoría, añadiendo 8 ceros delante
    for (int i = 0; i < NIA_LONG - 6; i++) {
      int index = 0;
      sb.append(index);
    }
    for (int i = 0; i < NIA_LONG - 2; i++) {
      int index = (int) (Math.random() * 9);
      sb.append((index));
    }
    return sb.toString();
  }
}
