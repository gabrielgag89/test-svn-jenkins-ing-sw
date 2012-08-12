package persistencia.proxy;

public class SectorImpl implements Sector{
   private int codigoSector;
   private String descripcionSector;
   private int nroPiso;

   @Override
   public int getCodigoSector() {
      return codigoSector;
   }

   @Override
   public void setCodigoSector(int codigoSector) {
      this.codigoSector = codigoSector;
   }

   @Override
   public String getDescripcionSector() {
      return descripcionSector;
   }

   @Override
   public void setDescripcionSector(String descripcionSector) {
      this.descripcionSector = descripcionSector;
   }

   @Override
   public int getNumeroPisoSector() {
      return nroPiso;
   }

   @Override
   public void setNumeroPisoSector(int nroPiso) {
      this.nroPiso = nroPiso;
   }
}