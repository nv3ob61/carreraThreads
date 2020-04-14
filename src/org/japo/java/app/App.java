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
package org.japo.java.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.japo.java.entities.Caballo;
import org.japo.java.entities.LineaMeta;
import org.japo.java.libraries.UtilesEntrada;
import org.japo.java.libraries.UtilesTiempo;

/**
 *
 * @author mon_mo
 */
public final class App {

  public static final int MAX_JUGADORES = 8;
  public static final int MIN_JUGADORES = 0;   //ponemos el 0 para la salida.

  public final void launchApp() throws InterruptedException {
    int numero = UtilesEntrada.leerEntero(
            String.format("%nElige el número de caballos. (MÁX 8) : "),
            "ERROR: Dato incorrecto", MIN_JUGADORES, MAX_JUGADORES);

    if (numero == 0) {   //salida con el 0
      System.out.println("---");
      System.out.println("SALIDA DEL PROGRAMA.");
    } else if (numero == 1) {
      System.out.println();
      System.out.println("QUË TRISTE, NOO?");
      System.out.println("---");
      System.out.println("¿Contra quién vas a competir? Adiós!");
    } else {
      System.out.println("=======================================");
      //cuenta atrás chachi
      UtilesTiempo.cuentaAtras();

      //separador
      System.out.println("--------------");

      //empieza la partida
      ExecutorService e = Executors.newFixedThreadPool(8);
      LineaMeta lm = new LineaMeta();

      for (int i = 0; i < numero; i++) {
        System.out.println("CABALLO ID : " + (i + 1));
        String strI = Integer.toString(i + 1);
        Caballo c = new Caballo(strI, lm);
        e.execute(c);
      }

      //separador
      System.out.println("--------------");
      System.out.println();
      System.out.println("INICIO DE LA CARRERA: ");
      System.out.println();

      //no se aceptan más tareas, intentamos parar la ejecución
      //esto es de apañar......
      e.shutdown();
      e.awaitTermination(40, TimeUnit.SECONDS);

      //banner salida
      System.out.println();
      System.out.println("=====================");
      System.out.println("POSICIONES FINALES .:");
      System.out.println("=====================");
      //muestra datos carrera.
      lm.print();
    }
  }

}
