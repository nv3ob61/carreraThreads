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
import org.japo.java.entities.Apuesta;

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
  public static final int leerEntero(
          String msgUsr, String msgErr, int min, int max) {
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

  // Consola >> Real
  public static final double leerReal(String msgUsr, String msgErr) {
    // Dato a introducir
    double dato = 0;

    // Proceso de lectura
    boolean lecturaOK = false;
    do {
      try {
        // Entrada dato
        System.out.print(msgUsr);
        dato = SCN.nextDouble();

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

  // Consola >> Real [min .. max]
  public static final double leerReal(
          String msgUsr, String msgErr, double min, double max) {
    // Numero a devolver
    double dato;

    // Semaforo validacion
    boolean rangoOK;

    // Bucle Validacion
    do {
      // Introducir Entero
      dato = leerReal(msgUsr, msgErr);

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

  // Consola >> Real [min .. max]
  public static final double entradaApuestaClimites(
          String msgUsr, String msgErr, double min, double max) {
    // Numero a devolver
    double dato;

    // Semaforo validacion
    boolean rangoOK;

    // Bucle Validacion
    do {
      // Introducir Entero
      dato = leerReal(msgUsr, msgErr);

      if (UtilesValidacion.validar(dato + "", Apuesta.REG_CANT)) {
        // Validar Entero
        rangoOK = dato >= min && dato <= max;

        // Mensaje de error
        if (!rangoOK) {
          System.out.println(msgErr);
        }
      } else {
        rangoOK = false;
      }
    } while (!rangoOK);

    // Devolver número
    return dato;
  }

  // Opciones + Consola > Opcion
  public static final int obtenerOpcion(
          String msgUsr, String msgErr, String opc) {
    
    int opcion;
    boolean opcionOK = false;
    do {
      opcion = leerEntero(msgUsr, msgErr);
      if (opc.contains(opcion + "")) {
        opcionOK = true;
      } else {
        System.out.println("---");
        System.out.println(msgErr);
        System.out.println("---");
      }
    } while (!opcionOK);
    return opcion;
  }

  // Consola >> Texto
  public static final String leerTexto(String msgUsr) {
    System.out.print(msgUsr);
    return SCN.nextLine();
  }

  // Confirmación S/N + Defecto > Boolean
  public static final boolean confirmarProceso(String msgUsr, boolean defectoOK) {
    String sn = "[sSnN]";  //validador Sí o no
    // Semáforo
    boolean isOk = false;
    boolean confirmacionOK = defectoOK;
    do {
      // Consola > Caracter
      String entrada = leerTexto(msgUsr);

      // Analisis Entrada
      if (entrada.length() > 0) {
        // Entrada > Caracter 1
        char c = entrada.charAt(0);
        if (UtilesValidacion.validar(Character.toString(c), sn)) {
          isOk = true;
          // Caracter [Ss | Nn] > Boolean
          confirmacionOK = "Ss".contains(c + "");
        } else {
          System.out.println("ERROR: Opción no válida.");
        }
      }
    } while (!isOk);
    // Devolución Confirmación
    return confirmacionOK;
  }

  // Pausa + MSG >> INTRO
  public static final void hacerPausa(String msgUsr) {
    System.out.println("---");
    System.out.println(msgUsr);
    hacerPausa();
  }

  // Pausa >> INTRO
  public static final void hacerPausa() {
    System.out.println("---");
    System.out.print("Pulse INTRO para continuar ...");
    SCN.nextLine();
    System.out.println("---");
  }
}
