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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com | mon_mo mod | Monteverde
 * as well...
 */
public final class UtilesFecha {

  public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

  public static final String DEF_FECHA_MIN = "01/01/1920";
  public static final String DEF_FECHA_MAX = "31/12/2100";

  // Formato de fecha
  public static final String FORMATO_FECHA = "dd/MM/yyyy";

  // Nombres de los dias de la semana
  public static final String[] NOMBRE_DIA = {
    "lunes", "martes", "miércoles", "jueves",
    "viernes", "sábado", "domingo"};

  // Nombres de los meses del año
  public static final String[] NOMBRE_MES = {
    "enero", "febrero", "marzo", "abril", "mayo",
    "junio", "julio", "agosto", "septiembre",
    "octubre", "noviembre", "diciembre"};

  // Nombres de las estaciones
  public static final String[] NOMBRE_ESTACION = {
    "primavera", "verano", "otoño", "invierno"};

  // ExpReg - Día del mes hasta 28 - [1..28] / [01..28]
  public static final String ER_DIA28 = "(0?[1-9]|1[0-9]|2[0-8])";

  // ExpReg - Mes del año - [1..12] / [01..12]
  public static final String ER_MES = "(0?[1-9]|1[0-2])";

  // ExpReg - Año - [0..9999]
  public static final String ER_ANY = "([0-9]|[1-9][0-9]|[1-9][0-9]{2}|[1-9][0-9]{3})";

  // ExpReg - Separador de campos de fecha: "/" o "-"
  public static final String ER_SEP_FECHA = "[/-]";

  // ExpReg - Fecha válida entre el 1 y el 28 de cualquier mes
  public static final String ER_FECHA_DIA28
          = "(" + ER_DIA28 + ER_SEP_FECHA + ER_MES + ER_SEP_FECHA + ER_ANY + ")";

  // ExpReg - Años SI divisibles por 400 (Hasta 4 digitos)
  public static final String ER_ANYS_MOD400
          = "(" + "0?[48]00|[13579][26]00|[2468][048]00" + ")";

  // ExpReg - Años NO divisibles por 100 pero SI divisibles por 4 (Hasta 4 dígitos)
  public static final String ER_ANYS_MOD4_NO100
          = "(" + "[0-9]{0,2}" + "((0?|[2468])[48]|[13579][26]|[2468]0)" + ")";    // Desde 4 hasta 96

  // ExpReg - Años Bisiestos (Hasta 4 digitos)
  public static final String ER_ANYS_BISIESTOS
          = "(" + ER_ANYS_MOD400 + "|" + ER_ANYS_MOD4_NO100 + ")";

  // ExpReg - Fecha válida para 29 de Febreros BISIESTOS
  public static final String ER_FECHA_DIA29_BISIESTO
          = "(" + "29" + ER_SEP_FECHA + "(2|02)" + ER_SEP_FECHA + ER_ANYS_BISIESTOS + ")";

  // ExpReg - Meses que tienen 30 dias (Todos menos Febrero)
  public static final String ER_MESES_30DIAS = "(0?[13456789]|1[012])";

  // ExpReg - Fecha válida para el 29 de cualquier Mes EXCEPTO Febrero
  public static final String ER_FECHA_DIA29_NORMAL
          = "(" + "29" + ER_SEP_FECHA + ER_MESES_30DIAS + ER_SEP_FECHA + ER_ANY + ")";

  // ExpReg - Fecha válida para el 29 de cualquier Mes INCLUIDO Febrero
  public static final String ER_FECHA_DIA29
          = "(" + ER_FECHA_DIA29_BISIESTO + "|" + ER_FECHA_DIA29_NORMAL + ")";

  // ExpReg - Fecha válida para el 30 de cualquier Mes
  public static final String ER_FECHA_DIA30
          = "(" + "30" + ER_SEP_FECHA + ER_MESES_30DIAS + ER_SEP_FECHA + ER_ANY + ")";

  // ExpReg - Meses que tienen 31 dias
  public static final String ER_MESES_31DIAS = "(0?[13578]|1[02])";

  // ExpReg - Fecha válida para el 31 de cualquier Mes
  public static final String ER_FECHA_DIA31
          = "(" + "31" + ER_SEP_FECHA + ER_MESES_31DIAS + ER_SEP_FECHA + ER_ANY + ")";

  // ExpReg - Fecha válida (Cualquiera)
  public static final String ER_FECHA
          = "(" + ER_FECHA_DIA28 + "|" + ER_FECHA_DIA29 + "|" + ER_FECHA_DIA30 + "|" + ER_FECHA_DIA31 + ")";

  public static final String REG_FECHA
          = "^([0]?[1-9]|[1|2][0-9]|[3][0|1])/([0]?[1-9]|"
          + "[1][0-2])/([0-9]{4}|[0-9]{2})$";

  public static final int EDAD_MIN = 18;

  // Obtener el número de dias del mes del año
  public static final int obtenerDiasMes(int mes, int any) {
    // Número de dias del mes
    int numDias;

    // Análisis
    switch (mes) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        numDias = 31;
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        numDias = 30;
        break;
      case 2:
        numDias = validarBisiesto(any) ? 29 : 28;
        break;
      default:
        numDias = 0;
    }

    // Devolución resultado
    return numDias;
  }

  // Día (Número) > Día (Nombre)
  public static final String obtenerNombreDia(int dia) {
    return dia >= 1 && dia <= NOMBRE_DIA.length ? NOMBRE_DIA[dia - 1] : "indefinido";
  }

  // Mes (Número) > Mes (Nombre)
  public static final String obtenerNombreMes(int mes) {
    return mes >= 1 && mes <= NOMBRE_MES.length ? NOMBRE_MES[mes - 1] : "indefinido";
  }

  // Fecha (String) > dia (int)
  public static final int obtenerDiaFecha(String fecha) {
    // Dia de la fecha
    int dia;

    try {
      // Desglosa los campos de la fecha
      String[] campo = fecha.split(ER_SEP_FECHA);

      // Convierte el dia a número
      dia = Integer.parseInt(campo[0]);
    } catch (NumberFormatException e) {
      dia = -1;
    }

    // Devuelve el dia
    return dia;
  }

  // Fecha (String) > mes (int)
  public static final int obtenerMesFecha(String fecha) {
    // Mes de la fecha
    int mes;

    try {
      // Desglosa los campos de la fecha
      String[] campo = fecha.split(ER_SEP_FECHA);

      // Convierte el mes a número
      mes = Integer.parseInt(campo[1]);
    } catch (NumberFormatException e) {
      mes = -1;
    }

    // Devuelve el mes
    return mes;
  }

  // Fecha (String) > año (int)
  public static final int obtenerAnyFecha(String fecha) {
    // Año de la fecha
    int any;

    try {
      // Desglosa los campos de la fecha
      String[] campo = fecha.split(ER_SEP_FECHA);

      // Convierte el dia a número
      any = Integer.parseInt(campo[2]);
    } catch (NumberFormatException e) {
      any = -1;
    }

    // Devuelve el año
    return any;
  }

  // Número del Dia del Mes de Hoy
  public static final int obtenerDiaHoy() {
    return new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
  }

  // Número del Mes de Hoy
  public static final int obtenerMesHoy() {
    return new GregorianCalendar().get(Calendar.MONTH) + 1;
  }

  // Número del Año de Hoy
  public static final int obtenerAnyHoy() {
    return new GregorianCalendar().get(Calendar.YEAR);
  }

  // Fecha de Hoy - dd/mm/aaaa
  public static final String obtenerFechaHoy() {
    // Obtiene Fecha Hoy
    Date fechaHoy = new Date();

    // Formateador de Fecha
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // Formatea y devuelve la Fecha
    return sdf.format(fechaHoy);
  }

  // Validación Fecha - Texto sin gesglosar - Expresión Regular
  public static final boolean validarFecha(String fecha) {
    return UtilesValidacion.validar(fecha, UtilesFecha.ER_FECHA);
  }

  // Validación Fecha - Campos Separados - Expresión Regular
  public static final boolean validarFecha(int dia, int mes, int any) {
    // Construye la fecha a partir de sus componentes
    String fecha = String.format("%02d"
            + UtilesFecha.ER_SEP_FECHA.charAt(1) + "%02d"
            + UtilesFecha.ER_SEP_FECHA.charAt(1) + "%d", dia, mes, any);

    // Devuelve la validación de la fecha
    return UtilesValidacion.validar(fecha, UtilesFecha.ER_FECHA);
  }

  //creamos un método similar al anterior con la entrada de un array.
  public static boolean validarFecha(int[] fecha) {
    boolean fechaOK = false;
    if (fecha[1] == 2) {
      if (validarBisiesto(fecha[2]) && fecha[0] == 29) {
        fechaOK = true;
      } else if (fecha[0] >= 1 && fecha[0] <= 28) {
        fechaOK = true;
      }
    } else if (fecha[1] == 4 || fecha[1] == 6 || fecha[1] == 9
            || fecha[1] == 11) {
      if (fecha[0] >= 1 && fecha[0] <= 30) {
        fechaOK = true;
      }
    } else if (fecha[1] == 1 || fecha[1] == 3 || fecha[1] == 5
            || fecha[1] == 7 || fecha[1] == 8 || fecha[1] == 10
            || fecha[1] == 12) {
      if (fecha[0] >= 1 && fecha[0] <= 31) {
        fechaOK = true;
      }
    }
    return fechaOK;
  }

  public static final int[] splitFecha(String fecha) {
    int[] decom = new int[3];

    String delim = "/";
    String[] descom = fecha.split(delim);

    for (int i = 0; i < descom.length; i++) {
      decom[i] = Integer.valueOf(descom[i]);
    }

    return decom;
  }

  // Comprobar si el año es bisiesto
  public static final boolean validarBisiesto(int any) {
    return any % 400 == 0 || any % 100 != 0 && any % 4 == 0;
  }

  public static final boolean validarRegistroEdad(String pideFecha)
          throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date instant = Calendar.getInstance().getTime();  //Coge referencia del momnt
    //semáforo de la fecha
    boolean fechaOk;
    fechaOk = false;

    if (UtilesValidacion.validaFecha(pideFecha)) {
      if (validarFecha(splitFecha(pideFecha))) {
        if (instant.after(sdf.parse(pideFecha))) {  //si la fecha es anterior 
          fechaOk = true;
        } else if (instant.before(sdf.parse(pideFecha))) { //si es fecha futura
          System.out.println("ERROR: FECHA INTRODUCIDA FUTURA");
        }
      }
    }
    return fechaOk;
  }

  public static final boolean esMayorde(Date d) {
    boolean isOk = false;

    Calendar c = Calendar.getInstance();
    c.setTime(d);

    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH) + 1;
    int date = c.get(Calendar.DATE);

    LocalDate da = LocalDate.of(year, month, date);
    LocalDate now = LocalDate.now();

    Period diff = Period.between(da, now);

    if (diff.getYears() >= EDAD_MIN) {
      isOk = true;
    }
    return isOk;
  }

  public static final Date fechaMin() throws ParseException {
    Date d = SDF.parse(DEF_FECHA_MIN);
    return d;
  }

  public static final Date fechaMax() throws ParseException {
    Date d = SDF.parse(DEF_FECHA_MAX);
    return d;
  }

  // Fecha ( Calendar ) > Fecha ( String )
  public static final String convertir(Calendar fecha) {
    return String.format("%02d/%02d/%d",
            fecha.get(Calendar.DATE),
            fecha.get(Calendar.MONTH),
            fecha.get(Calendar.YEAR));
  }

  // Fecha ( Date ) > Fecha ( String )
  public static final String convertir(Date fecha) {
    // Objeto Calendar
    Calendar c = Calendar.getInstance();

    // Establece la fecha
    c.setTime(fecha);

    // Representación Fecha
    return String.format("%02d/%02d/%d",
            c.get(Calendar.DATE),
            c.get(Calendar.MONTH),
            c.get(Calendar.YEAR));
  }

  // Fecha ( String ) > Fecha ( Date )
  public static final Date convertir(String fecha) {
    return convertir(fecha, FORMATO_FECHA);
  }

  // Fecha ( String ) > Fecha ( Date ) - Formato Personalizado
  public static final Date convertir(String fecha, String formato) {
    // Referencia Fecha
    Date d = null;

    // Locale ESPAÑA
    Locale lcl = new Locale("ES", "es");

    try {
      // Formateador de Fecha
      SimpleDateFormat sdf = new SimpleDateFormat(formato, lcl);

      // Convierte Fecha
      d = sdf.parse(fecha);
    } catch (ParseException e) {
      System.out.println("ERROR: Conversión cancelada - " + e.getMessage());
    }

    // Devuelve Fecha Date
    return d;
  }

  // Calcular dias entre fechas
  public static final int obtenerDistancia(String fechaIni, String fechaFin) throws ParseException {
    // Objetos Date
    Date dateIni = convertir(fechaIni);
    Date dateFin = convertir(fechaFin);

    // Distancia Fechas >> ms
    long ms = dateFin.getTime() - dateIni.getTime();

    // ms >> dias
    return (int) (ms / 1000 / 3600 / 24);

//        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
  }

  // Calcular dias entre fechas
  public static final String obtenerFechaHoy2() {
    // Fecha del sistema
    Calendar c = Calendar.getInstance();

    // Representación texto
    return String.format("%02d/%02d/%d",
            c.get(Calendar.DATE),
            c.get(Calendar.MONTH),
            c.get(Calendar.YEAR));
  }

  public static final boolean validarRangoAdmision(Date d) throws ParseException {
    boolean isOk = false;
    if (d != null) {
      if (d.after(fechaMin())
              && d.before(fechaMax())) {
        isOk = true;
      }
    }
    return isOk;
  }

  //método que comprueba el formato y muestra null o la fecha.
  public static String formatDateTime(Date dateOrNull) {
    return (dateOrNull == null ? null : SDF.format(dateOrNull));
  }

  /*
  
                        MONTERDE EXPANSION
  
   */
  public final static int SP_MAYORIA_EDAD = 18;

  //Obtener fecha
  public final static LocalDate leerFecha(String msgUsr, String msgErr, DateTimeFormatter dtf) {

    //Variables
    String fecha;
    LocalDate retorno = LocalDate.MIN;
    boolean fechaOK = false;

    //Bucle
    do {
      //Zona de cuarentena
      try {
        //Obtenemos un String con la fecha
        fecha = UtilesEntrada.leerTexto(msgUsr);

        //Le hacemos un parse (si algo es incorrectos, mostraremos al error)
        retorno = LocalDate.parse(fecha, dtf);

        //Si la zona de cuarentena pasa, nos salimos del bucle
        fechaOK = true;

        //Si no, mostramos el mensaje de error
      } catch (Exception e) {
        System.out.println(msgErr);
      }
    } while (!fechaOK);

    //Devolvemos el valor
    return retorno;
  }

  //Obtener Periodo
  public final static Period obtenerPeriodo(LocalDate fechaMenor, LocalDate fechaMayor) {
    return Period.between(fechaMenor, fechaMayor);
  }

  //Diferencia del campo de años de dos fechas
  public final static int difYears(LocalDate fechaMenor, LocalDate fechaMayor) {
    return obtenerPeriodo(fechaMenor, fechaMayor).getYears();
  }

  //Diferencia del campo de meses de dos fechas
  public final static int difMeses(LocalDate fechaMenor, LocalDate fechaMayor) {
    return obtenerPeriodo(fechaMenor, fechaMayor).getMonths();
  }

  //Diferencia del campo de días de dos fechas
  public final static int difDias(LocalDate fechaMenor, LocalDate fechaMayor) {
    return obtenerPeriodo(fechaMenor, fechaMayor).getDays();
  }

  //Diferencia (contada en años) de dos fechas
  public final static long numYears(LocalDate fechaMenor, LocalDate fechaMayor) {
    return fechaMenor.until(fechaMayor, ChronoUnit.YEARS);
  }

  //Diferencia (contada en meses) de dos fechas
  public final static long numMeses(LocalDate fechaMenor, LocalDate fechaMayor) {
    return fechaMenor.until(fechaMayor, ChronoUnit.MONTHS);
  }

  //Diferencia (contada en días) de dos fechas
  public final static long numDias(LocalDate fechaMenor, LocalDate fechaMayor) {
    return fechaMenor.until(fechaMayor, ChronoUnit.DAYS);
  }

  //Comprueba si hay un mínimo del número de años deseado entre dos fechas (solo ese campo)
  public final static boolean compareDifYear(LocalDate fechaMenor, LocalDate fechaMayor, int numYears) {
    return difYears(fechaMenor, fechaMayor) >= numYears;
  }

  //Comprueba si hay un mínimo del número de meses deseado entre dos fechas (solo ese campo)
  public final static boolean compareDifMes(LocalDate fechaMenor, LocalDate fechaMayor, int numMeses) {
    return difMeses(fechaMenor, fechaMayor) >= numMeses;
  }

  //Comprueba si hay un mínimo del número de días deseado entre dos fechas (solo ese campo)
  public final static boolean compareDifDia(LocalDate fechaMenor, LocalDate fechaMayor, int numDias) {
    return difDias(fechaMenor, fechaMayor) >= numDias;
  }

  //Comprueba si hay un mínimo del número de años deseado entre dos fechas (en total)
  public final static boolean compareNumYears(LocalDate fechaMenor, LocalDate fechaMayor, int numYears) {
    return numYears(fechaMenor, fechaMayor) >= numYears;
  }

  //Comprueba si hay un mínimo del número de meses deseado entre dos fechas (en total)
  public final static boolean compareNumMeses(LocalDate fechaMenor, LocalDate fechaMayor, int numMeses) {
    return numMeses(fechaMenor, fechaMayor) >= numMeses;
  }

  //Comprueba si hay un mínimo del número de días deseado entre dos fechas (en total)
  public final static boolean compareNumDias(LocalDate fechaMenor, LocalDate fechaMayor, int numDias) {
    return numDias(fechaMenor, fechaMayor) >= numDias;
  }

  //Comprobar si el usuario tiene la mayoría de edad -DEF_España-
  public final static boolean hasMayoriaEdad(LocalDate fecha) {
    return hasMayoriaEdad(fecha, SP_MAYORIA_EDAD);
  }

  //Comprobar si el usuario tiene la mayoría de edad -MultiPais-
  public final static boolean hasMayoriaEdad(LocalDate fecha, int edadMin) {
    return compareNumYears(fecha, LocalDate.now(), edadMin);
  }
}
