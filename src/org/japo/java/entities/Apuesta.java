/*
 * Copyright (C) 2020 Jonsui
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.japo.java.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.japo.java.libraries.UtilesGen;
import org.japo.java.libraries.UtilesValidacion;

/**
 *
 * @author Jonsui
 */
public class Apuesta {

  public static final DateTimeFormatter DTF
          = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public static final String DEF_ID = "00000000";
  public static final String DEF_NOM = "DEF_NOMBRE";
  public static final double DEF_CANT = 0.0;
  public static final int DEF_CABALLO = 0;

  public static final String REG_CANT = "^(\\d+(\\.\\d{0,2})?|\\.?\\d{1,2})$";
  public static final String REG_CABALLO = "[1-8]";

  private String id;
  private String nombre;
  private double cantidad;
  private int caballo;
  private Carrera evento;
  private final LocalDateTime fecha;

  public Apuesta() {
    id = UtilesGen.generarId();
    nombre = DEF_NOM;
    cantidad = DEF_CANT;
    caballo = DEF_CABALLO;
    evento = new Carrera();
    fecha = LocalDateTime.now();
  }

  public Apuesta(String id, String nombre,
          double cantidad, int caballo, Carrera evento) {
    this.id = id;   //es generada automáticamente en procesarApuesta.

    //Validar nombre
    this.nombre = nombre;

    //Valida Cantidad
    if (UtilesValidacion.validar(cantidad + "", REG_CANT)) {
      this.cantidad = cantidad;
    } else {
      this.cantidad = DEF_CANT;
    }
    if (UtilesValidacion.validar(caballo + "", REG_CABALLO)) {
      this.caballo = caballo;

    } else {
      this.caballo = DEF_CABALLO;
    }

    this.evento = evento;

    this.fecha = LocalDateTime.now();

  }

  public String getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public double getCantidad() {
    return cantidad;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public int getCaballo() {
    return caballo;
  }

  public Carrera getEvento() {
    return evento;
  }

  public void setCaballo(int caballo) {
    this.caballo = caballo;
  }

  public void setEvento(Carrera evento) {
    this.evento = evento;
  }

  public void setId(String id) {
    this.id = id;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setCantidad(double cantidad) {
    if (UtilesValidacion.validar(cantidad + "", REG_CANT)) {
      this.cantidad = cantidad;
    }
  }

  @Override
  public String toString() {
    return String.format("INFO DE APUESTA%n"
            + "=========%n"
            + "ID ......: %s%n"
            + "NOMBRE ..: %s%n"
            + "CANTIDAD : %.2f%n"
            + "FECHA ...: %s%n"
            + "CABALLO .: %d%n"
            + "EVENTO ..: %s%n",
            id, nombre, cantidad, fecha.format(DTF),
            caballo, getEvento().getId());
  }

  public final void muestraApuesta() {
    System.out.println(toString());
  }

}
