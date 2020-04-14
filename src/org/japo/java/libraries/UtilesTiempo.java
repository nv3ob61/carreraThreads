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
public class UtilesTiempo {
      
    
  public static final void cuentaAtras() {
    System.out.println("Inicio de la carrera en: ");
    System.out.println("---");
    for (int i = 10; i > 0; i--) {
      try {
        System.out.println(i);
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        System.out.println("OPSS! Algo ha fallado en la cuenta atrás.");
      }
    }
  }
}
