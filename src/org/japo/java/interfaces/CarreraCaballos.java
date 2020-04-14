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
package org.japo.java.interfaces;

public interface CarreraCaballos {

  //Constantes aquí
  public static int DISTANCIA = 5000;
  //velocidad del galope del caballo
  public static int VEL_MIN = 3;
  public static int VEL_MAX = 6;
  //componentes oscilación de velocidad para el cálculo del Thread(n) / velocidad.
  // -parámetro que divida aprox entre 1 en un rango de +- 0.25
  public static double COMP_MIN = 0.75;
  public static double COMP_MAX = 1.25;
  //por si acaso, defininimos máx y min también aquí
  public static int DEF_MAX_CABALLOS = 8;
  public static int DEF_MIN_CABALLOS = 2;
  

  //corre la carrera
  public void run();

  //getId, del ganador, de toda la vida, implementado. 
  public String getId();
}
