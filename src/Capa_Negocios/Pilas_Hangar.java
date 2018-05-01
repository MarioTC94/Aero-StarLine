package Capa_Negocios;

import Capa_Entidades.Avion;

public class Pilas_Hangar {

    private Avion ini;

    public Pilas_Hangar() {
        ini = null;
    }

    public void InsertarAvion(String modelo, int cantpa, int cantsob, String NomPiloto) { //Método para Insertar Avión

        Avion aux = new Avion(modelo, cantpa, cantsob, NomPiloto);
        aux.setSiguiente(ini);
        ini = aux;

    }

    public void InsertarAvion(Avion nuevo) { //Insertar nuevo Avión en otra lista

        nuevo.setSiguiente(ini);
        ini = nuevo;

    }

    public Avion ExtraerAvion() { //Método para extraer el avión y colocarlos en las diferentes listas
        Avion aux = ini;
        if (aux != null) {
            ini = ini.getSiguiente();           
            aux.setSiguiente(null);

        }
        return aux;
    }

    

    public int getLength() { //Método para devolver la cantidad de aviones en la lista
        Avion aux = ini;
        int cont = 0;

        while (aux != null) {
            cont++;
            aux = aux.getSiguiente();
        }
        return cont;
    }

    public Avion get(int index) { //Método para extraer un avión en especifico
        Avion aux = ini;
        int cont = 0;

        while (aux != null) {
            if (cont == index) {
                return aux;
            }
            cont++;
            aux = aux.getSiguiente();
        }
        return null;
    }
}
