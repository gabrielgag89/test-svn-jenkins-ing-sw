package util;

import java.util.Date;

public class ServiciosTiempo {
   private static ServiciosTiempo instancia;
   
   private ServiciosTiempo(){}
   
   public static ServiciosTiempo getInstancia(){
      if(instancia == null)
         instancia = new ServiciosTiempo();
      
      return instancia;
   } // fin del método ServiciosTiempo

   public boolean validarFecha(int dia, int mes, int anio){
      if(anio < 1900 || anio > 2050 || dia < 1 || mes < 1)
         return false;

      if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
         if(dia > 30)
            return false;
         
      }
      else if(mes == 1 || mes == 3 || mes == 5 || mes == 7 ||
               mes == 8 || mes == 10 || mes == 12){
         if(dia > 31)
            return false;
         
      }
      else if((anio % 4) == 0){
         if(dia > 29)
            return false;
         
      }
      else{
         if(dia > 28)
            return false;
         
      } // fin de if

      return true;
   } // fin del método validarFecha
   
   public String dateToString(Date fecha){
      int dia, mes, anio;
      dia = fecha.getDate();
      mes = fecha.getMonth() + 1;
      anio = fecha.getYear() + 1900;
      
      return ((anio < 10 ? "000" : (anio < 100 ? "00" : (anio < 1000 ? "0" : ""))) + anio +
                  "-" + (mes < 10 ? "0" : "") + mes + "-" + (dia < 10 ? "0" : "") + dia);
   } // fin del método dateToString
   
   public String dateToStringDDMMAAAA(Date fecha){
      int dia, mes, anio;
      dia = fecha.getDate();
      mes = fecha.getMonth() + 1;
      anio = fecha.getYear() + 1900;
      
      return ((dia < 10 ? "0" : "") + dia) + "-" + (mes < 10 ? "0" : "") + mes + "-" +
              (anio < 10 ? "000" : (anio < 100 ? "00" : (anio < 1000 ? "0" : ""))) + anio;
   } // fin del método dateToStringDDMMAAAA
   
   public Date stringToDate(String fechaS){
      Date fechaD = new Date();
      String diaS, mesS, anioS;
      diaS = mesS = anioS = "";
      int j = 0;
      char fecha[] = fechaS.toCharArray();
      
      for(int i = 0; i < fecha.length; i++){
         if(fecha[i] != '-' && j == 0)
            anioS += fecha[i];
         else if(fecha[i] != '-' && j == 1)
            mesS += fecha[i];
         else if(fecha[i] != '-' && j == 2)
            diaS += fecha[i];
         else
            j++;
      } // fin de for
      
      fechaD.setDate(Integer.parseInt(diaS));
      fechaD.setMonth(Integer.parseInt(mesS) - 1);
      fechaD.setYear(Integer.parseInt(anioS) - 1900);
      
      return fechaD;
   } // fin del método stringToDate
} // fin de la clase ServiciosTiempo