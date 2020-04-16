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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.japo.java.comparators.ComparadorApuesta;
import org.japo.java.entities.Apuesta;
import org.japo.java.entities.Caballo;
import org.japo.java.entities.Carrera;
import org.japo.java.entities.LineaMeta;
import org.japo.java.enumerations.Criterio;
import org.japo.java.interfaces.ICarrera;
import org.japo.java.interfaces.ILineaMeta;
import org.japo.java.libraries.UtilesEntrada;
import org.japo.java.libraries.UtilesGen;
import org.japo.java.libraries.UtilesMenu;
import org.japo.java.libraries.UtilesTiempo;
import org.japo.java.libraries.UtilesValidacion;
import org.japo.java.libraries.enums.EstadoCarrera;

/**
 *
 * @author mon_mo
 */
public final class App {

  public static final int MAX_JUGADORES = 8;
  public static final int MIN_JUGADORES = 0;   //ponemos el 0 para la salida.

  //Aumento de 1.23 a lo que se apueste
  public static final double CANT_GANADOR = 1.23;

  //Lista para almacenar todas las carreras.
  public static final List<Carrera> CARRLIST = new ArrayList<Carrera>();
  // Colección listas Apuestas, primera recoge todas las apuestas del evento
  public static final List<Apuesta> BETLIST = new ArrayList<Apuesta>();
  //filtro apuestas ganadoras
  public static final List<Apuesta> BETWINLIST = new ArrayList<Apuesta>();

  //App principal
  public final void launchApp() throws InterruptedException {
    //inicio del menú principal
    procesarMenuPpal();
  }

  private void procesarMenuPpal() throws InterruptedException {
    // Semáforo Bucle
    boolean salirOK = false;

    // Bucle Menú Principal
    do {
      // Consola + Opciones > Opcion
      int opcion = UtilesEntrada.obtenerOpcion(UtilesMenu.TXT_MENU_PPAL,
              UtilesMenu.TXT_MENU_ERROR,
              UtilesMenu.OPC_MENU_PPAL);

      // Separación cosmética
      System.out.println("---");

      // Gestión Opciones
      switch (opcion) {
        case 1:
          procesarMenuJuego();
          break;
        case 0:
          salirOK = true;
          break;
        default:
          System.out.println("---");
          System.out.println("ERROR: Opción NO disponible");
          System.out.println("---");
      }
    } while (!salirOK);
  }

  private void procesarMenuJuego() throws InterruptedException {
    // Semáforo Bucle
    boolean salirOK = false;
    // Semáforo para que solo muestre las reglas la primera vez.
    boolean muestraReglasOk = true;

    //generar el num de caballos de cada carrera como condición, entre 2 y 8
    Random r = new Random();
    int cab = r.nextInt(ICarrera.DEF_MAX_CABALLOS
            - ICarrera.DEF_MIN_CABALLOS) + ICarrera.DEF_MIN_CABALLOS;

    //Hacer la clase carrera que contenga lista de apuestas, caballos y meta...
    //comprobar antes del menu info si la carrera ya se ha celebrado con alguna ID
    Carrera c = new Carrera(BETLIST, null, EstadoCarrera.ACTIVA);

//
//
//                           Bucle Menú Principal
//
//
    do {
      //menú informativo previo 
      System.out.printf("%nMENÚ INFO: ESTA CARRERA ES DE %d CABALLOS%n%n", cab);
      //muestra info carrera
      c.muestraInfo();

      // Consola + Opciones > Opcion
      int opcion = UtilesEntrada.obtenerOpcion(UtilesMenu.TXT_MENU_JUEG,
              UtilesMenu.TXT_MENU_ERROR,
              UtilesMenu.OPC_MENU_JUEG);

      // Separación cosmética
      System.out.println("---");

      // Gestión Opciones
      switch (opcion) {
        case 1:
          //este if valida que no es la primera vez que se muestran las reglas..
          if (muestraReglasOk) {
            muestraReglasOk = UtilesMenu.pausadito(UtilesMenu.muestraReglas());
          }
          procesarApuesta(c, cab);   //enviamos la carrera y el num de caballos.
          break;
        case 2:
//          cancelarApuesta();
          break;
        case 3:
//          comprobarApuesta();
          break;
        case 4:
          if (UtilesEntrada.confirmarProceso(
                  "¿Desea realmente empezar la carrera? (S/n) ...: ", true)) {
            if (!c.getEstado().equals(EstadoCarrera.FINALIZADA)) {
              iniciarCarrera(cab, c);
            } else {   //no entra ahora este else...
              System.out.printf("%nERROR: NO se puede empezar "
                      + "una carrera finalizada.%n%n"
                      + "Por favor, empieza una nueva partida... Gracias.%n");
            }
          }
          salirOK = true;
          break;
        case 0:
          salirOK = true;
          break;
        default:
          System.out.println("---");
          System.out.println("ERROR: Opción NO disponible");
          System.out.println("---");
      }
    } while (!salirOK);
  }

  //método que inicia la carrera
  private void iniciarCarrera(int numero, Carrera carr)
          throws InterruptedException {
    //cabecera
    System.out.println("=======================================");

    //cuenta atrás chachi
    UtilesTiempo.cuentaAtras();

    //separador
    System.out.println("--------------");

    //empieza la partida
    ExecutorService e = Executors.newFixedThreadPool(8);
    LineaMeta lm = new LineaMeta();

    //llama a los caballos y a correr
    for (int i = 0; i < numero; i++) {
      System.out.println("CABALLO ID: " + (i + 1));
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
    e.awaitTermination(40, TimeUnit.SECONDS);   //40s para que haga el shutdown..

    //muestra datos carrera.
    lm.printRankingMeta();

    //añadimos lista ranking a la carrera
    carr.setRank(ILineaMeta.RANK);
    //TEST si ha copiado el mismo num que rank.... borrar!
//    System.out.println(carr.getRank().size());

    //pagar las apuestass..
    for (Apuesta ap : BETLIST) {
      if (Integer.toString(ap.getCaballo()).equals(carr.getRank().get(0))) {
        ap.setCantidad(ap.getCantidad() * CANT_GANADOR);
        BETWINLIST.add(ap);
      }
    }

    if (BETWINLIST != null) {
      System.out.printf(
              "Hay un total de %d apuestas acertadas...%n", BETWINLIST.size());

      //separador
      System.out.println("------------------------");
      System.out.println("ESTOS SON LOS GANADORES: ");
      System.out.println("------------------------");
      System.out.println();

      for (Apuesta ap : BETWINLIST) {
        ap.muestraApuesta();
      }
    }

    //añadimos lista apuestas a la carrera
    carr.setList(BETLIST);
    //pasamos estado de la carrera a finalizada
    carr.setEstado(EstadoCarrera.FINALIZADA);

    //una vez finalizada se añade a la lista de carreras
    CARRLIST.add(carr);
    //borrar BETLIST para que empiece una nueva carrera.
    BETLIST.clear();
  }

  //método para introducir las apuestas a la carrera.
  private void procesarApuesta(Carrera c, int cab) {
    boolean repeatOk = true;
//comparador por si el random id que genera se repite... //CAMBIAR PROCESO
    Comparator<Apuesta> cmp = new ComparadorApuesta(Criterio.ID);
    // Ordenacion
    Collections.sort(BETLIST, cmp);

    // Cabecera
    System.out.println("Procesar Apuesta");
    System.out.printf("=================%n");

    //generamos ID de la apuesta
    String id = UtilesGen.generarApuesta();

    //Clave de búsqueda
    Apuesta clave = new Apuesta(id, null, 0, 0, null);

    //proceso de búsqueda
    int pos = Collections.binarySearch(BETLIST, clave, cmp);

    // Analisis Resultado Busqueda
    if (pos < 0) {
      Apuesta a = new Apuesta(id, null, 0, 0, null);
      //Set nombre

      //añadimos los datos automáticos de la ID y del Evento
      a.setId(id);
      a.setEvento(c);

        // Consola > Nombre
        do {
          a.setNombre(UtilesEntrada.leerTexto("   Nombre ......: "));
          if (a.getNombre().isEmpty()) {
            repeatOk = false;
          }
        } while (a.getNombre().equals(Apuesta.DEF_NOM));

        // Consola > Cantidad
        do {
          a.setCantidad(UtilesEntrada.entradaApuestaClimites(
                  "   Cantidad......: ",
                  "ERROR: Valor introducido incorrecto", 0, 500));
          if (a.getCantidad() == 0) {
            repeatOk = false;
          }
        } while (a.getCantidad() == Apuesta.DEF_CANT && a.getCantidad() != 0);

        // Consola > Caballo
        do {
          a.setCaballo(UtilesEntrada.leerEntero("   Núm Caballo ..: ",
                  "ERROR: Valor introducido incorrecto", 0, cab));
        } while (a.getCaballo() == Apuesta.DEF_CABALLO
                && a.getCaballo() != 0);
     
      System.out.println("------------------------------");
      
      //Si la cantidad introducida es mayor que 0 y el núm caballo es correcto.
      if (a.getCantidad() > 0 && UtilesValidacion.validar(a.getCaballo() 
              + "", Apuesta.REG_CABALLO)) {
        
        // Consola > Datos Nueva Apuesta
        System.out.println("Alta de Apuesta");
        System.out.println("--------------");
        a.muestraApuesta();

        // Confirmar Proceso
        if (UtilesEntrada.confirmarProceso(
                "Confirmar Inserción (S/n) ...: ", true)) {
          
          // Insercion Ítem
          BETLIST.add(a);

          // Mensaje Informativo
          UtilesEntrada.hacerPausa("Apuesta AÑADIDA correctamente");
        } else {
          // Mensaje Informativo
          UtilesEntrada.hacerPausa("Inserción de apuesta CANCELADA");
        }
      } else {
        System.out.println("ERROR: Fallo al procesar "
                + "la cantidad o el caballo apostado");
      }
    }
  }
}
