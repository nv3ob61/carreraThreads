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
package org.japo.java.comparators;

import java.util.Comparator;
import org.japo.java.entities.Apuesta;
import org.japo.java.enumerations.Criterio;

/**
 *
 * @author Jonsui
 */
public final class ComparadorApuesta implements Comparator<Apuesta> {

  private Criterio orden;

  public ComparadorApuesta(Criterio orden) {
    if (orden != null) {
      this.orden = orden;
    } else {
      this.orden = Criterio.ID;
    }
  }

  @Override
  public int compare(Apuesta o1, Apuesta o2) {
    // Valor de Comparación
    int comparacion;

    // Criterio Ordenacion > Comparacion
    switch (orden) {
      case ID:
        comparacion = o1.getId().compareTo(o2.getId());
        break;
      case NOMBRE:
        comparacion = o1.getNombre().compareTo(o2.getNombre());
        break;
      case CANTIDAD:
        comparacion = (int) (o1.getCantidad() - o2.getCantidad());
        break;
      case FECHAS:
        comparacion = comparaFechasInt(o1, o2);
        break;
      default:
        comparacion = o1.getId().compareTo(o2.getId());
        break;
    }

    // Devualve Comparacion
    return comparacion;

  }

  public Criterio getOrden() {
    return orden;
  }

  public void setOrden(Criterio orden) {
    if (orden != null) {
      this.orden = orden;
    }
  }

  public static int comparaFechasInt(Apuesta o1, Apuesta o2) {
    int comparacion = 0;
    
    if (o2.getFecha().compareTo(o1.getFecha()) == 0) {
      comparacion = 0;
    }

    if (o2.getFecha().compareTo(o1.getFecha()) > 0 ) {
      comparacion = 1;
    }

    if (o2.getFecha().compareTo(o1.getFecha()) < 0) {
      comparacion = -1;
    }
    return comparacion;
  }
}
