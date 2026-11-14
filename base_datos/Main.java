package base_datos;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        EstudianteController controller=new EstudianteController();
        Scanner scanner=new Scanner(System.in);
        int opcion=0;

        do{
            System.out.println("Sistema académico");
            System.out.println("1.Listar Estudiantes");
            System.out.println("2.Registrar Estudiantes");
            System.out.println("3.Salir");
            System.out.println("Seleccione la opcion ");
            opcion=Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    List<Estudiante> estudiantes=controller.obtenerEstudiantes();
                    estudiantes.forEach(e-> System.out.printf(
                                "ID : %d | Doc: %s | Nombre: %-20s | Programa : %-22s | Nota: %.2f%n",
                                        e.id(),e.documento(),e.nombre(),e.programa(),e.nota()));
                    
            
                case 2:
                    System.out.println("Documento");
                    scanner.nextLine();
                    System.out.println("Nombre ");
                    scanner.nextLine();
                    System.out.println("Programa ");
                    scanner.nextLine();
                    System.out.println("Nota ");
                    scanner.nextDouble();
            }

        }while(opcion!=2);

    }

}
