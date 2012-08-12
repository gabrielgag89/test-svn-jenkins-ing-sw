package persistencia.proxy;

public class SectorAgente extends ObjetoPersistente  implements Sector{
   private SectorImpl impl;

   public void setImplementacion(SectorImpl impl){
      this.impl = impl;
   }

   @Override
   public int getCodigoSector() {
      return this.impl.getCodigoSector();
   }
   
   @Override
   public void setCodigoSector(int codigoSector) {
      this.impl.setCodigoSector(codigoSector);
   }

   @Override
   public String getDescripcionSector() {
      return this.impl.getDescripcionSector();
   }
   
   @Override
   public void setDescripcionSector(String descripcionSector) {
      this.impl.setDescripcionSector(descripcionSector);
   }
   
   @Override
   public int getNumeroPisoSector() {
      return this.impl.getNumeroPisoSector();
   }
   
   @Override
   public void setNumeroPisoSector(int nroPisoSector) {
      this.impl.setNumeroPisoSector(nroPisoSector);
   }
} // fin de la clase SectorAgente