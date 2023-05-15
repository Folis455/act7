package test;

import domain.Docente;
import domain.Suplente;
import domain.Titular;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Act_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LocalDate fechaNacimiento;
        int numeroLegajo = 0, antiguedad = 0, edad = 0;
        boolean esValido, trabajaEnZonaRural = false; //con trabajaEnZonaRural,esValido asi intercambiados no compila tira error ¿por que? error: variable trabajaEnZonaRural might not have been initialized
        double salario = 0;
        String nombre, apellido;
        Docente docente = null;
        List<Integer> numerosLegajoPrevios = new ArrayList<>();



        int opcion = -1;
        while (opcion != 0) {
            System.out.println(" -- MENÚ -- ");
            System.out.println("[1] Ingresar Docentes");
            System.out.println("[2] Mostrar Docentes");
            System.out.println("[0] Salir");
            System.out.println("------------");
            System.out.print("Elija una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un numero entero válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    fechaNacimiento = null;
                    do {
                        System.out.print("Ingrese su nombre: ");
                        nombre = scanner.nextLine();
                        if (!nombre.matches("^[a-zA-Z]*$")) System.out.println("Debe ingresar un nombre válido");
                    } while (!nombre.matches("^[a-zA-Z]*$"));

                    do {
                        System.out.print("Ingrese su apellido: ");
                        apellido = scanner.nextLine();
                        if (!apellido.matches("^[a-zA-Z]*$"))
                            System.out.println("Debe ingresar un apellido válido");
                    } while (!apellido.matches("^[a-zA-Z]*$"));

                    esValido=false;
                    do {
                        try {
                            System.out.print("Ingrese su número de legajo: ");
                            numeroLegajo = scanner.nextInt();// se debe declarar al inicio para que siga global sino queda local al while
                            scanner.nextLine();
                            if (numerosLegajoPrevios.contains(numeroLegajo)) {
                                System.out.println("El número de legajo ya ha sido ingresado. Ingrese un número de legajo válido.");
                            } else {
                                numerosLegajoPrevios.add(numeroLegajo);
                                esValido = true;
                            }
                        } catch (InputMismatchException misMatch) {
                            System.out.println("Ha ocurrido un error. Ingrese un número de legajo válida");
                            scanner.nextLine(); // limpia la entrada no valida
                        }
                    } while (!esValido);

                    while (fechaNacimiento == null) {
                        try {
                            System.out.print("Ingrese su fecha de nacimiento en formato 'AAAA-MM-DD': ");
                            String fechaNacimientoString = scanner.nextLine();
                            fechaNacimiento = LocalDate.parse(fechaNacimientoString);

                            edad = docente.calcularEdad(fechaNacimiento);

                            if (edad < 18) {
                                System.out.println("Lo siento, debe ser mayor de edad para utilizar este servicio.");
                                fechaNacimiento = null;
                            } else {
                                System.out.println("Su edad es: " + edad + " años.");
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Ha ocurrido un error al procesar la fecha de nacimiento. Asegúrese de que esté en el formato correcto (AAAA-MM-DD) y vuelva a intentarlo.");
                        }
                    }

                    esValido = false;
                    while (!esValido) {
                        try {
                            System.out.print("Ingrese su salario: ");
                            salario = scanner.nextDouble();
                            scanner.nextLine(); // Consumir el salto de línea pendiente
                            esValido = true;
                        } catch (InputMismatchException misMatch) {
                            System.out.println("Ha ocurrido un error. Ingrese el salario con números");
                            scanner.nextLine(); // limpia la entrada no valida
                        }
                    }

                    String zonaRuralString;
                    do {
                        System.out.print("¿Trabaja en zona rural? (S/N): ");
                        zonaRuralString = scanner.nextLine();
                        zonaRuralString = zonaRuralString.toUpperCase();
                        if (!zonaRuralString.matches("[NS]")) System.out.println("Ingrese una opción válida");
                    } while (!zonaRuralString.matches("[NS]"));

                    switch (zonaRuralString) {
                        case "S":
                            trabajaEnZonaRural = true;
                            // docente.setTrabajaEnZonaRural(true);
                            break;
                        case "N":
                            trabajaEnZonaRural = false;
                            // docente.setTrabajaEnZonaRural(false);
                            break;
                        default:
                            System.out.println("Error");
                            break;
                    }

                    String tipoDocenteString;
                    do {
                        System.out.print("¿Es titular o suplente? (T/S): ");
                        tipoDocenteString = scanner.nextLine();
                        if (!tipoDocenteString.matches("[tTsS]")) System.out.println("Ingrese una opción válida");
                    } while (!tipoDocenteString.matches("[tTsS]"));
                    tipoDocenteString = tipoDocenteString.toUpperCase();
                    
                    switch (tipoDocenteString) {
                        case "T":
                            esValido = false;
                            do {
                                try {
                                    System.out.print("Ingrese su antigüedad: ");
                                    antiguedad = scanner.nextInt();
                                    scanner.nextLine();

                                    if (edad - antiguedad < 18) {
                                        System.out.println("Ingrese una antigüedad válida");
                                    } else {
                                        esValido = true;
                                    }
                                } catch (InputMismatchException misMatch) {
                                    System.out.println("Ha ocurrido un error. Ingrese correctamente la antigüedad en años con números");
                                    scanner.nextLine(); // limpia la entrada no válida
                                }
                            } while (!esValido);

                            docente = new Titular(numeroLegajo, nombre, apellido, fechaNacimiento.toString(), edad, salario, trabajaEnZonaRural, antiguedad);
                            break;
                        case "S":
                            String Zona;
                            do {
                                System.out.print("Ingrese la zona (A/B/C): ");
                                Zona = scanner.nextLine();
                                if (!Zona.matches("^[a-cA-C]*$"))
                                    System.out.println("Ingrese una de las zonas vigentes");
                            } while (!Zona.matches("^[a-cA-C]*$"));
                            docente = new Suplente(numeroLegajo, nombre, apellido, fechaNacimiento.toString(), edad, salario, trabajaEnZonaRural, Zona);
                            System.out.println("Suplente zona: " + Zona);
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }


                    docente.calcular_rural();
                    docente.calcular_plus();

                    System.out.println("\t------------------------------------" +
                            "\n\tSu salario actualizado es: " + docente.getSalario() + "\n"
                            + "\t------------------------------------");

                    docente.agregarDocente(docente);
                    break;
                case 2:
                    try {
                        docente.mostrarDocentes();
                    }catch (NullPointerException e) {
                        System.out.println("[!] No hay docentes cargados\n");
                        break;
                    }
                case 0:
                    System.out.println("¡Hasta Pronto!");
                    break;
                default:
                    System.out.println("Ingrese un número entre 0-3");
                    break;
            }
        }
    }
}