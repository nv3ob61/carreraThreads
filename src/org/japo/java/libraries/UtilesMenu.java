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
 * @author mon_mo
 */
public class UtilesMenu {

  // separador de Línea
  public static final String LS = System.getProperty("line.separator");

  // Opciones Menús
  public static final String OPC_MENU_PPAL = "10";
  public static final String OPC_MENU_JUEG = "12340";
  public static final String OPC_MENU_CONT = "1230";
  public static final String OPC_MENU_FILT = "1234567890";
  public static final String OPC_MENU_ORDE = "123450";
  public static final String OPC_MENU_PERS = "120";
  public static final String OPC_MENU_SN = "[s|S|n|N]";

  // Texto Menú Principal
  public static final String TXT_MENU_PPAL
          = "==================================" + LS
          + "MENÚ PRINCIPAL DE LA APP DE HÍPICA" + LS
          + "==================================" + LS
          + LS
          + "[ 1 ] Empezar el juego" + LS
          + "---" + LS
          + "[ 0 ] Salir" + LS
          + "---" + LS
          + "Introducir opción: ";

  // Texto Menú Items
  public static final String TXT_MENU_JUEG
          = "=================================" + LS
          + "MENÚ DE JUEGO - ¿QUÉ DESEA HACER?" + LS
          + "=================================" + LS
          + "[ 1 ] Apostar en la siguiente carrera" + LS
          + "[ 2 ] Cancelar su apuesta" + LS
          + "[ 3 ] Comprobar su apuesta" + LS
          + "---" + LS
          + "[ 4 ] Empezar siguiente carrera" + LS
          //            + "---" + LS
          //            + "[ 5 ] Habilitar Alumno" + LS
          //            + "[ 6 ] Deshabilitar Alumno" + LS
          + "---" + LS
          + "[ 0 ] Salir del juego" + LS
          + "---" + LS
          + "Introducir opción: ";

  // Texto Menú Persistencia
  public static final String TXT_MENU_PERS
          = "MATRÍCULA DE ALUMNOS - GESTIÓN DE PERSISTENCIA" + LS
          + "==============================================" + LS
          + "[ 1 ] Importar Datos" + LS
          + "[ 2 ] Exportar Datos" + LS
          + "---" + LS
          + "[ 0 ] Menú Anterior" + LS
          + "---" + LS
          + "Introducir opción: ";

  public static final String TXT_MENU_ERROR = "ERROR: Opción no válida";

  public static final boolean pausadito(String str) throws InterruptedException {
    //por defecto es falso, no hace nada. Solo asegurarnos que solo se repite
    //una vez el mensaje de info.
    boolean isOk = false;
    for (int i = 0; i < str.length(); i++) {
      System.out.printf("%c", str.charAt(i));
      Thread.sleep(30);
    }
    Thread.sleep(2000);
    System.out.println();
    return isOk;
  }

  public static final String muestraReglas() throws InterruptedException {
    return String.format("%nREGLAS DEL JUEGO: %n-----------------%n"
            + "-La cantidad máxima para apostar está "
            + "limitada a 1000 unidades%s%s%s%n%n"
            + "-Cada caballo tiene el mismo peso en la carrera, no hay más"
            + " lógica que el buen pseudorandom de java. Salud!%n",
            duerme(1000), duerme(1000), duerme(1000));
  }

  public static final String duerme(int cont) throws InterruptedException {
    System.out.printf(".");
    Thread.sleep(cont);
    return "";
  }
}
