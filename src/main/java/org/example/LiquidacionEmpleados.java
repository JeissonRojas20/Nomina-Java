package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class LiquidacionEmpleados {
    private String nombre;
    private String apellido;
    private String documento;
    private int diasTrabajados;
    private boolean auxTransporte;
    private static final int SALARIO_BASICO = 1000000; // Salario básico mensual
    private static final int AUX_TRANSPORTE_VALOR = 117172; // Auxilio de transporte

    public LiquidacionEmpleados(String nombre, String apellido, String documento, int diasTrabajados, boolean auxTransporte) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.diasTrabajados = diasTrabajados;
        this.auxTransporte = auxTransporte;
    }

    public double calcularSalarioBruto() {
        double salarioDiario = SALARIO_BASICO / 30.0;
        return salarioDiario * diasTrabajados;
    }

    public double calcularDevengos() {
        double salarioBruto = calcularSalarioBruto();
        double auxTransporteValor = (auxTransporte && salarioBruto <= 1817052) ? AUX_TRANSPORTE_VALOR : 0;
        return salarioBruto + auxTransporteValor;
    }

    public double calcularDescuentos() {
        double salarioBruto = calcularSalarioBruto();
        return salarioBruto * 0.08; // Descuento del 8% por seguridad social
    }

    public double calcularSalarioNeto() {
        return calcularDevengos() - calcularDescuentos();
    }

    public void mostrarLiquidacion() {
        System.out.println("\nNombre: " + nombre + " " + apellido);
        System.out.println("Documento: " + documento);
        System.out.printf("Total Bruto: $%.2f%n", calcularSalarioBruto());
        System.out.printf("Devengos: $%.2f%n", calcularDevengos());
        System.out.printf("Descuentos: $%.2f%n", calcularDescuentos());
        System.out.printf("Valor Neto a Pagar: $%.2f%n", calcularSalarioNeto());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<LiquidacionEmpleados> empleados = new ArrayList<>();

        System.out.print("Ingrese el número de empleados: ");
        int numEmpleados = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del número

        for (int i = 0; i < numEmpleados; i++) {
            System.out.printf("Ingrese el nombre del empleado %d: ", i + 1);
            String nombre = scanner.nextLine();

            System.out.printf("Ingrese el apellido del empleado %d: ", i + 1);
            String apellido = scanner.nextLine();

            System.out.printf("Ingrese el documento del empleado %d: ", i + 1);
            String documento = scanner.nextLine();

            System.out.printf("Ingrese los días trabajados por el empleado %d: ", i + 1);
            int diasTrabajados = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después del número

            System.out.print("¿El empleado tiene derecho al auxilio de transporte? (s/n): ");
            String auxTransporteInput = scanner.nextLine().toLowerCase();
            boolean auxTransporte = auxTransporteInput.equals("s");

            empleados.add(new LiquidacionEmpleados(nombre, apellido, documento, diasTrabajados, auxTransporte));
        }

        for (LiquidacionEmpleados empleado : empleados) {
            empleado.mostrarLiquidacion();
        }

        scanner.close();
    }
}
