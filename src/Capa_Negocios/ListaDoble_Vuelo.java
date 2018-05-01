package Capa_Negocios;

import Capa_Entidades.Avion;

public class ListaDoble_Vuelo {

    private Avion ini;
    private Avion fin;

    public ListaDoble_Vuelo() {

        ini = null;
        fin = null;
    }

    public void InsertarAvion(String modelo, int cantpa, int cantsob, String NomPiloto) { //Método para ingresar avion a la lista

        Avion nuevo = new Avion(modelo, cantpa, cantsob, NomPiloto);

        if (ini == null) {
            ini = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSiguiente(ini);
            ini.setAnterior(nuevo);
            ini = nuevo;
        }
    }

    public void InsertarAvion(Avion nuevo) { //Método para ingresar el avión a otra lista
        if (ini == null) {
            ini = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSiguiente(ini);
            ini.setAnterior(nuevo);
            ini = nuevo;
        }
    }

    public Avion ExtraerAvion(int avion) { //Método para extraer avión manualmente a lista aterrizaje
        Avion ext = get(avion);

        if (ext == null) {
            return null;
        }
        if (ext == ini) { //Condición para extraer el primer avión
            ini = ini.getSiguiente();
            if (ini != null) {
                ini.setAnterior(null);
            }

            ext.setSiguiente(null);
            return ext;
        }
        if (ext == fin) { //Condición para extraer el ultimo avión

            fin = fin.getAnterior();
            if (fin != null) {
                fin.setSiguiente(null);
            }

            ext.setAnterior(null);
            return ext;
        }
        ext.getSiguiente().setAnterior(ext.getAnterior());
        ext.getAnterior().setSiguiente(ext.getSiguiente());
        ext.setSiguiente(null);
        ext.setAnterior(null);

        return ext;
    }

    public int getLength() {
        Avion aux = ini;
        int cont = 0;

        while (aux != null) {
            cont++;
            aux = aux.getSiguiente();
        }
        return cont;
    }

    public Avion get(int index) { //Método para extraer avión en específico
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

    public void OrdenarNomPiloto() { //Método Ordenamiento Burbuja para los Nombre de los pilotos
        boolean cambio;

        do {
            Avion aux = ini;
            Avion anterior = null;
            Avion siguiente = ini.getSiguiente();
            cambio = false;
            while (siguiente != null) {
                if (aux.getNomPiloto().compareTo(siguiente.getNomPiloto()) > 0) {
                    cambio = true;
                    if (anterior != null) {
                        Avion sig = siguiente.getSiguiente();
                        anterior.setSiguiente(siguiente);
                        siguiente.setSiguiente(aux);
                        aux.setSiguiente(sig);
                    } else {
                        Avion sig = siguiente.getSiguiente();
                        ini = siguiente;
                        siguiente.setSiguiente(aux);
                        aux.setSiguiente(sig);
                    }
                    anterior = siguiente;
                    siguiente = aux.getSiguiente();

                } else {
                    anterior = aux;
                    aux = siguiente;
                    siguiente = siguiente.getSiguiente();
                }
            }
        } while (cambio);

    }

}
