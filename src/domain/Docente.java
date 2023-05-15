package domain;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;

public abstract class Docente {

    private int numeroLegajo;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private int edad;
    private double salario;
    public static final double PLUS_RURALIDAD = 30000;
    public boolean trabajaEnZonaRural;
    private static List<Docente> docentes = new ArrayList<>();


    public Docente(int numeroLegajo, String nombre, String apellido, String fechaNacimiento, int edad, double salario, boolean trabajaEnZonaRural) {
        this.numeroLegajo = numeroLegajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.salario = salario;
        this.trabajaEnZonaRural = trabajaEnZonaRural;

    }

    public int getNumeroLegajo() {
        return numeroLegajo;
    }

    public void setNumeroLegajo(int numeroLegajo) {
        this.numeroLegajo = numeroLegajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean getTrabajaEnZonaRural() {
        return trabajaEnZonaRural;
    }

    public void setTrabajaEnZonaRural(boolean trabajaEnZonaRural) {
        this.trabajaEnZonaRural = trabajaEnZonaRural;
    }

    public static List<Docente> getDocentes() {
        return docentes;
    }

    public static void agregarDocente(Docente docente) {
        docentes.add(docente);
    }

    public static int calcularEdad(LocalDate fechaNacimiento) {
        LocalDate ahora = LocalDate.now();
        return fechaNacimiento != null && ahora.isAfter(fechaNacimiento) ? Period.between(fechaNacimiento, ahora).getYears() : 0;
    }
    public void mostrarDocentes() {
        System.out.println("         DOCENTES   ");
        int contador = 1;
        for (Docente docente : docentes) {
            System.out.println("[" + contador + "] ---------------------");
            System.out.println("Nombre: " + docente.getNombre());
            System.out.println("Apellido: " + docente.getApellido());
            System.out.println("Número de legajo: " + docente.getNumeroLegajo());
            System.out.println("Fecha de nacimiento: " + docente.getFechaNacimiento());
            System.out.println("Edad: " + docente.getEdad());
            System.out.println("Salario: $" + docente.getSalario());
            System.out.println("Trabaja en zona rural: " + (docente.getTrabajaEnZonaRural() ? "Sí" : "No"));

            if (docente instanceof Titular) {
                Titular titular = (Titular) docente;
                System.out.println("Antigüedad: " + titular.getAntiguedad() + " año/s");
                System.out.println("Tipo de docente: Titular");
            } else if (docente instanceof Suplente) {
                Suplente suplente = (Suplente) docente;
                System.out.println("Zona: " + suplente.getZona());
                System.out.println("Tipo de docente: Suplente");
            }

            System.out.println("-------------------------");
            contador++;

        }
    }


    public void calcular_rural() {
        if (trabajaEnZonaRural == true) {
            double plusRural = getSalario() + PLUS_RURALIDAD;
            setSalario(plusRural);
        }
    }

    public abstract void calcular_plus();
}
