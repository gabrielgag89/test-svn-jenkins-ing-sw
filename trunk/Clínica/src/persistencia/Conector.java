package persistencia;

import java.sql.*;

public class Conector {
   private static String bd = "clinica";
   private static String login = "fulltime";
   private static String password = "disenio";
   private static String host = "localhost";
   private static String url_bd = "jdbc:mysql://";
   private static String driver = "com.mysql.jdbc.Driver";
   private static Conector instancia;
   private static Connection conexion;
   
   private Conector(){}
   
   public static Conector getInstancia(){
      if (instancia == null)
         instancia = new Conector();
      
      return instancia;
   } // fin del método getInstancia
   
   public void iniciarTransaccion(){
      if (conexion == null) {
         try {
            conexion = DriverManager.getConnection(url_bd+host+"/"+bd,login,password);
            conexion.setAutoCommit(false);
            System.out.println("Se logro la conexion con la base de datos "+url_bd+host+"/"+bd+".");
         }
         catch (SQLException e) {
            System.out.println (e.getMessage());
         }
      }
   } // fin del método iniciarTransaccion
   
   public Connection getConexion() {
      return conexion;
   } // fin del método getConexion
   
   public void confirmarTransaccion(){
      try {
         conexion.commit();
      }
      catch (SQLException ex) {
         System.err.println("Conector - confirmarTransaccion() - " + ex.getStackTrace());
      }
   } // fin del método confirmarTransaccion
   
   public void deshacerTransaccion(){
      try {
         conexion.rollback();
      }
      catch (SQLException ex) {
         System.err.println("Conector - deshacerTransaccion() - " + ex.getStackTrace());
      }
   } // fin del método deshacerTransaccion
} // fin de la clase Conector