package Capa_Datos;

import java.util.Random;

public class Array_DatosAvion {
    
    Random random = new Random();
    int cant, ran;

    private final String[] ModAvion = {
        "A45", "BG4", "J78", "D09", "Y42", "W321", "HB65", "P09", "UL98", "T956",
    };

    private final int[] CantPas = {
        545, 134, 643, 365, 278, 908, 807, 1450, 456, 342,
    };

    private final int[] CantSob = {
        450, 678, 894, 932, 453, 124, 875, 313, 135, 242, 807,
    };

    private final String[] NomPil = {
        "Josue", "Mario", "Daniela", "Jorge", "Manuel", "David", "Kevin", "Andrey", "Carlos", "Marco"
    };

    public String getModAvion() {
        cant = ModAvion.length;
        ran = random.nextInt(cant);       
        return ModAvion[ran];
    }

    public int getCantPas() {
        cant = ModAvion.length;
        ran = random.nextInt(cant);
       return CantPas[ran];
    }

    public int getCantSob() {
        cant = ModAvion.length;
        ran = random.nextInt(cant);
        return CantSob[ran];
    }

    public String getNomPil() {
        cant = ModAvion.length;
        ran = random.nextInt(cant);
        return NomPil[ran];
    }

}
