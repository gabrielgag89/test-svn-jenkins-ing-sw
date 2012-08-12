package cobrar_factura_paciente;

import java.util.List;
import fabricaExpertos.FabricaExpertos;
import dtos.DTOFacturaPaciente;
import dtos.DTORecibo;
import observador.*;

public class ControladorCobrarFacturaPaciente implements ObservadorGenerarFacturaPaciente{
   private ExpertoCobrarFacturaPaciente experto;
   private IUCobrarFacturaPaciente iuCobrar;
   
   public ControladorCobrarFacturaPaciente(){
      this.iuCobrar = new IUCobrarFacturaPaciente(this);
      this.iuCobrar.setVisible(true);
   } // fin del constructor
   
   public List<DTOFacturaPaciente> buscarFacturasPacientes(){
      // el controlador se suscribe como observador para ser notificado
      SuscriptorGenerarFacturaPaciente.getInstancia().suscribirse(this);
      
      // se obtiene un ExpertoCobrarFacturaPaciente y se guarda en una variable de instancia
      this.experto = (ExpertoCobrarFacturaPaciente) FabricaExpertos.getInstancia().getExperto("CobrarFacturaPaciente");
      
      // se buscan las facturas pendientes de cobro y se devuelven a la GUI
      return this.experto.buscarFacturasPendientes();
   } // fin del método cobrarFacturaPaciente
   
   public void cobrarFactura(int numFactura){
      // se genera el recibo para la factura
      DTORecibo dtoRecibo = this.experto.cobrarFactura(numFactura);
      
      // se comprueba que se haya emitido el recibo con éxito
      if(dtoRecibo != null){
         // se crea una GUI para mostrar el recibo emitido
         IUMostrarRecibo iuMostrar = new IUMostrarRecibo();
         // se envía el DTO con los datos del recibo a la GUI
         iuMostrar.cargarCampos(dtoRecibo);
         // se hace visible la GUI
         iuMostrar.setVisible(true);
      } // find e if de comprobación de emisión del recibo
   } // fin del método cobrarFactura
   
   @Override
   public void actualizar(DTOFacturaPaciente dtoFactura) {
      // se actualiza en la lista de facturas pendientes con el DTO de factura recibido
      this.iuCobrar.actualizar(dtoFactura);
   } // fin del método actualizar
} // fin de la clase ControladorCobrarFacturaPaciente