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
package org.japo.java.entities;

import java.util.Random;
import org.japo.java.interfaces.CarreraCaballos;
import org.japo.java.interfaces.ILineaMeta;

/**
 *
 * @author Jonsui
 */
public class Caballo implements Runnable, CarreraCaballos {
  //Inicio Random
  public static final Random RND = new Random();
  //definidas en CarreraCaballos
  public static double calculaVel = COMP_MIN + (COMP_MAX - COMP_MIN) * RND.nextDouble();
  
  //Atributos
  private String nombre;
  private String id;
  private int distancia;
  private double velocidad;
  private ILineaMeta l;
  
  //constructores
  public Caballo() {
  }
  
  public Caballo(String id, ILineaMeta l) {
    this.id = id;
    this.distancia = 0;
    this.l = l;
    this.velocidad = calculaVel;
  }

  public String getNombre() {
    return nombre;
  }

  @Override
  public String getId() {
    return id;
  }

  public int getDistancia() {
    return distancia;
  }

  public double getVelocidad() {
    return velocidad;
  }

  public ILineaMeta getL() {
    return l;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setDistancia(int distancia) {
    this.distancia = distancia;
  }

  public void setVelocidad(int velocidad) {
    this.velocidad = velocidad;
  }

  public void setL(ILineaMeta l) {
    this.l = l;
  }

  @Override
  public void run() {   //corre, implementa el interfaz carrera, pa eso están...

    int n = galope();

    //mostramos indicadores: println cada 100 m y el de los **** cada mil.
    while (distancia < DISTANCIA) {
      try {
        if(distancia == 0){
          System.out.println("CABALLO: " + getId() + " cabalga hacia la meta."); 
        }
        if (distancia % 100 == 0 && distancia % 1000 != 0) {
          System.out.println("CABALLO: " + getId() + " ha recorrido "
                  + distancia + "m.");
        }
        if (distancia % 100 == 0 && distancia % 1000 == 0 && distancia != 0) {
          System.out.println("CABALLO: " + getId() + " ha recorrido "
                  + distancia + "m.     ************** ["+getId()+"]");
        }
        //el mal llamado cálculo para esperar al siguiente paso.
        Thread.sleep((long) (n / velocidad));
      } catch (InterruptedException e) {
        System.out.println("OOOOPS!");
      }
      distancia += 1;
    }
    l.arrive(this);   //mensaje de llegada final.
  }

  //velocidad de los caballos, se controla desde el interface CarreraCaballos
  private int galope() {
    return RND.nextInt(VEL_MAX - VEL_MIN) + VEL_MIN;
  }
}
