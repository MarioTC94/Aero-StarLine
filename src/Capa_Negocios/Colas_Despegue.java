package Capa_Negocios;

import Capa_Entidades.Avion;

public class Colas_Despegue {

    private Avion ini;

    public Colas_Despegue() {

        ini = null;

    }

    public void InsertarAvion(String modelo, int cantpa, int cantsob, String NomPiloto) { // Metodo para insertar avión

        Avion nuevo = new Avion(modelo, cantpa, cantsob, NomPiloto);

        if (ini == null) {
            ini = nuevo;
        } else {
            Avion aux = ini;
            while (aux != null) {

                if (aux.getSiguiente() == null) {
                    aux.setSiguiente(nuevo);
                    break;
                }
                aux = aux.getSiguiente();
            }
        }
    }

    public void InsertarAvion(Avion nuevo) { // Metodo para insertar avión en otra lista

        if (ini == null) {
            ini = nuevo;
        } else {
            Avion aux = ini;
            while (aux != null) {

                if (aux.getSiguiente() == null) {
                    aux.setSiguiente(nuevo);
                    break;
                }
                aux = aux.getSiguiente();
            }

        }
    }

    public Avion ExtraerAvion() { //Metodo para extraer el avion y colocarlos en las diferentes listas
        Avion aux = ini;
        if (aux != null) {
            ini = ini.getSiguiente();
            
            aux.setSiguiente(null);
        }
        return aux;
    }

    public int getLength() { //Metodo para devolver la cantidad de aviones en la lista
        Avion aux = ini;
        int cont = 0;

        while (aux != null) {
            cont++;
            aux = aux.getSiguiente();
        }
        return cont;
    }

    public Avion get(int index) { //Metodo para extraer un avion en especifico
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
