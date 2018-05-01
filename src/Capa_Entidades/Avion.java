
package Capa_Entidades;

public class Avion {
    
   String ModeloAvion;
   int CantPasajeros;
   int CantSobrecargos;
   String NomPiloto;  
   Avion Anterior;
   Avion Siguiente;
   
   public Avion(String ModAvion, int CantPa, int CantSob, String Nombre_Piloto){
       this.ModeloAvion = ModAvion;
       this.CantPasajeros = CantPa;
       this.CantSobrecargos = CantSob;
       this.NomPiloto = Nombre_Piloto;
       Anterior = null;
       Siguiente = null;
   }
   

   public String getModeloAvion() {
        return ModeloAvion;
    }

    public void setModeloAvion(String ModeloAvion) {
        this.ModeloAvion = ModeloAvion;
    }

    public int getCantPasajeros() {
        return CantPasajeros;
    }

    public void setCantPasajeros(int CantPasajeros) {
        this.CantPasajeros = CantPasajeros;
    }

    public int getCantSobrecargos() {
        return CantSobrecargos;
    }

    public void setCantSobrecargos(int CantSobrecargos) {
        this.CantSobrecargos = CantSobrecargos;
    }

    public String getNomPiloto() {
        return NomPiloto;
    }

    public void setNomPiloto(String NomPiloto) {
        this.NomPiloto = NomPiloto;
    }

    public Avion getAnterior() {
        return Anterior;
    }

    public void setAnterior(Avion Anterior) {
        this.Anterior = Anterior;
    }
    
    public Avion getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(Avion siguiente) {
        this.Siguiente = siguiente;
    }

}


