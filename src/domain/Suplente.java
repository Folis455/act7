package domain;

public class Suplente extends Docente {

    private String zona;

    public Suplente(int numeroLegajo, String nombre, String apellido, String fechaNacimiento, int edad, double salario, boolean trabajaEnZonaRural, String zona) {
        super(numeroLegajo, nombre, apellido, fechaNacimiento, edad, salario, trabajaEnZonaRural);
        this.zona = zona;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public void calcular_plus() {

        if (getEdad() < 25 && getZona().equals("A")) {
            double nuevoSalario = getSalario() + 10000; // Si trabaja en la zona A le agregamos 10.000$ a su salario
            setSalario(nuevoSalario);
        }
    }
}
