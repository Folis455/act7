package domain;

import java.util.HashMap;
import java.util.Map;

public class Titular extends Docente {

    private int antiguedad;

    public Titular(int numeroLegajo, String nombre, String apellido, String fechaNacimiento, int edad, double salario, boolean trabajaEnZonaRural, int antiguedad) {
        super(numeroLegajo, nombre, apellido, fechaNacimiento, edad, salario, trabajaEnZonaRural);
        this.antiguedad = antiguedad;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Override
    public void calcular_plus() {
        if (getEdad() > 30) {
            double nuevoSalario = getSalario();
            int antiguedad = getAntiguedad();

            // definimos un porcentaje plus en base a la antiguedad, podriamos usar hashmap o if else-if
            Map<Integer, Double> porcentajes = new HashMap<>();
            porcentajes.put(6, 0.11);
            porcentajes.put(8, 0.18);
            porcentajes.put(11, 0.26);
            porcentajes.put(15, 0.34);
            porcentajes.put(20, 0.42);
            porcentajes.put(25, 0.5);

            for (Map.Entry<Integer, Double> entry : porcentajes.entrySet()) {
                if (antiguedad >= entry.getKey()) {
                    nuevoSalario += getSalario() * entry.getValue();
                } else {
                    break;
                }
            }

            setSalario(nuevoSalario);
        }
    }

}
