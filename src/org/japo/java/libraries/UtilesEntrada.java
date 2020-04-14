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

import java.util.Locale;
import java.util.Scanner;

public class UtilesEntrada {

  // Scanner + Codificación Windows
  // Instanciar Scanner
  public static final Scanner SCN
          = new Scanner(System.in, "Windows-1252")
                  .useLocale(Locale.ENGLISH).useDelimiter("\\s+");

  // Consola >> Entero
  public static final int leerEntero(String msgUsr, String msgErr) {
    // Dato a introducir
    int dato = 0;

    // Proceso de lectura
    boolean lecturaOK = false;
    do {
      try {
        // Entrada dato
        System.out.print(msgUsr);
        dato = SCN.nextInt();

        // Marca el semáforo
        lecturaOK = true;
      } catch (Exception e) {
        System.out.println(msgErr);
      } finally {
        SCN.nextLine();
      }
    } while (!lecturaOK);

    // Devolver dato
    return dato;
  }

  // Consola >> Entero [min .. max]
  public static final int leerEntero(String msgUsr, String msgErr, int min, int max) {
    // Numero a devolver
    int dato;

    // Semaforo validacion
    boolean rangoOK;

    // Bucle Validacion
    do {
      // Introducir Entero
      dato = leerEntero(msgUsr, msgErr);

      // Validar Entero
      rangoOK = dato >= min && dato <= max;

      // Mensaje de error
      if (!rangoOK) {
        System.out.println(msgErr);
      }
    } while (!rangoOK);

    // Devolver número
    return dato;
  }
}
