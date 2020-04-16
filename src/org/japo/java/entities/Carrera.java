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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.japo.java.libraries.UtilesGen;
import org.japo.java.libraries.enums.EstadoCarrera;

/**
 *
 * @author Jonsui
 */
public class Carrera {

  public static final DateTimeFormatter DTF
          = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  //Atr
  private String id;
  private LocalDate fecha;
  private List<Apuesta> listApu = new ArrayList<>();
  private List<String> rank = new ArrayList<>();
  private EstadoCarrera estado;

  public Carrera() {
    id = UtilesGen.generarCarrera();
    fecha = LocalDate.now();
    listApu = new ArrayList<>();
    rank = new ArrayList<>();
    estado = EstadoCarrera.ACTIVA;
  }

  //La fecha se añade a la lista como el tiempo que certifica la carrera.
  public Carrera(String id, List listApu, List rank, EstadoCarrera est) {
    this.id = id;
    this.fecha = LocalDate.now();
    this.listApu = listApu;
    this.rank = rank;
    this.estado = est;
  }

  public Carrera(List listApu, List rank, EstadoCarrera est) {
    this.id = UtilesGen.generarCarrera();
    this.fecha = LocalDate.now();
    this.listApu = listApu;
    this.rank = rank;
    this.estado = est;
  }

  //probar para búsqueda simple sin comparar nada más...
  public Carrera(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public List<Apuesta> getList() {
    return listApu;
  }

  public List<String> getRank() {
    return rank;
  }

  public EstadoCarrera getEstado() {
    return estado;
  }

  public void setEstado(EstadoCarrera estado) {
    this.estado = estado;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public void setList(List<Apuesta> listApu) {
    this.listApu = listApu;
  }

  public void setRank(List<String> rank) {
    this.rank = rank;
  }

  @Override
  public String toString() {
    return String.format(
              "S/N ......: %s%n"
            + "Fecha ....: %s%n"
            + "Estado ...: %s%n"
            + "Apuestas .: %d%n",
            getId(), fecha.format(DTF), getEstado(), getList().size());
  }

  public final void muestraInfo() {
    System.out.println(toString());
  }
}
