package persistencia.criterios;

public class Criterio {
   private String atributo;
   private String operador;
   private Object valor;
   private String opLogico;

   Criterio(String atributo, String operador, Object valor, String opLogico) {
      this.atributo = atributo;
      this.operador = operador;
      this.valor = valor;
      this.opLogico = opLogico;
   } // fin del constructor

   public String getAtributo() {
      return atributo;
   } // fin del método getAtributo

   public void setAtributo(String atributo) {
      this.atributo = atributo;
   } // fin del método setAtributo

   public String getOperador() {
      return operador;
   } // fin del método getOperador

   public void setOperador(String operador) {
      this.operador = operador;
   } // fin del método setOperador

   public Object getValor() {
      return valor;
   } // fin del método getValor

   public void setValor(Object valor) {
      this.valor = valor;
   } // fin del método setValor

   public String getOpLogico() {
      return opLogico;
   } // fin del método getOpLogico

   public void setOpLogico(String opLogico) {
      this.opLogico = opLogico;
   } // fin del método setOpLogico
} // fin de la clase Criterio