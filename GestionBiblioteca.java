import java.util.Scanner;
import java.util.Calendar;
import java.util.*;
/**
 * La clase BibliotecaMenu es un menú que muestra por consola las distintas opciones de Biblioteca y permite interactuar con esta.
 * 
 * @version 14/11/23
 */
public class GestionBiblioteca
{
    public static void main(String[] args)
    {
        Biblioteca biblioteca = new Biblioteca("Biblioteca FACENA-UNNE");
        agregarInstancias(biblioteca);
        menu(biblioteca);
    }
    public static void agregarInstancias(Biblioteca p_biblio)
    {
        p_biblio.nuevoSocioDocente(12345678, "Carlos González", "Informática");
        p_biblio.nuevoSocioDocente(23456789, "Ana Rodríguez", "Matemáticas");
        p_biblio.nuevoSocioDocente(34567890, "Luisa Pérez", "Historia");
        p_biblio.nuevoSocioDocente(45678901, "Pedro Sánchez", "Literatura");
        p_biblio.nuevoSocioDocente(56789012, "María Gómez", "Química");
        p_biblio.nuevoSocioDocente(67890123, "Laura Martínez", "Física");
        p_biblio.nuevoSocioDocente(78901234, "Javier López", "Biología");
        p_biblio.nuevoSocioDocente(89012345, "Elena Torres", "Educación Física");
        p_biblio.nuevoSocioDocente(90123456, "Santiago Ruiz", "Arte");
        p_biblio.nuevoSocioDocente(10111213, "Carolina Herrera", "Idiomas");

        p_biblio.nuevoSocioEstudiante(11111111, "Ana García", "Ingeniería Informática");
        p_biblio.nuevoSocioEstudiante(22222222, "Carlos Rodríguez", "Medicina");
        p_biblio.nuevoSocioEstudiante(33333333, "María López", "Derecho");
        p_biblio.nuevoSocioEstudiante(44444444, "Juan Martínez", "Psicología");
        p_biblio.nuevoSocioEstudiante(55555555, "Laura González", "Economía");
        p_biblio.nuevoSocioEstudiante(66666666, "Roberto Pérez", "Arquitectura");
        p_biblio.nuevoSocioEstudiante(77777777, "Lucía Fernández", "Biología");
        p_biblio.nuevoSocioEstudiante(88888888, "Diego Sánchez", "Historia");
        p_biblio.nuevoSocioEstudiante(99999999, "Elena Ruiz", "Química");
        p_biblio.nuevoSocioEstudiante(10101010, "Pedro Gómez", "Matemáticas");

        Libro libro1 = new Libro("1984", 5, "Secker & Warburg", 1949);
        Libro libro2 = new Libro("Cien años de soledad", 1, "Sudamericana", 1967);
        Libro libro3 = new Libro("El señor de los anillos", 1, "George Allen & Unwin", 1954);
        Libro libro4 = new Libro("Don Quijote de la Mancha", 1, "Francisco de Robles", 1605);
        Libro libro5 = new Libro("Harry Potter y la piedra filosofal", 1, "Bloomsbury", 1997);
        Libro libro6 = new Libro("Matar a un ruiseñor", 1, "J.B. Lippincott & Co.", 1960);
        Libro libro7 = new Libro("Crimen y castigo", 1, "The Russian Messenger", 1866);
        Libro libro8 = new Libro("Orgullo y prejuicio", 1, "Thomas Egerton", 1813);
        Libro libro9 = new Libro("Ulises", 1, "Sylvia Beach", 1922);
        Libro libro10 = new Libro("Los miserables", 1, "A. Lacroix, Verboeckhoven & Cie.", 1862);

        p_biblio.addLibro(libro1);
        p_biblio.addLibro(libro2);
        p_biblio.addLibro(libro3);
        p_biblio.addLibro(libro4);
        p_biblio.addLibro(libro5);
        p_biblio.addLibro(libro6);
        p_biblio.addLibro(libro7);
        p_biblio.addLibro(libro8);
        p_biblio.addLibro(libro9);
        p_biblio.addLibro(libro10);
    }

    private static void menu(Biblioteca p_biblio)
    {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        System.out.println("\nBienvenido al sistemas de gestion de la Biblioteca.");
        System.out.println("Por favor seleccione una de las siguientes opciones: ");
        System.out.println("1. Agregar Docente");
        System.out.println("2. Agregar  Estudiante");
        System.out.println("3. Agregar un Libro");
        System.out.println("4. Prestar libro");
        System.out.println("5. Devolver libro");
        System.out.println("6. Mostrar Cantidad de Estudiantes");
        System.out.println("7. Mostrar Lista de Docentes sin deudas");
        System.out.println("8. Mostrar Listas de Socios");
        System.out.println("9. Mostrar Lista de Libros");
        System.out.println("10. Consultar sobre un libro en especifico");
        System.out.println("0. Salir");
        try {
            opcion = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            opcion = 11;
        }
        limpiar();

        switch (opcion) {
            case 1:
                p_biblio.addSocio(instanciarDocente());
                System.out.println("Docente agregado exitosamente.");
                continuar();
                menu(p_biblio);
                break;
            case 2:
                p_biblio.addSocio(instanciarEstudiante());
                System.out.println("Estudiante agregado exitosamente.");
                continuar();
                menu(p_biblio);
                break;
            case 3:
                p_biblio.addLibro(instanciarLibro());
                System.out.println("Libro agregado exitosamente.");
                continuar();
                menu(p_biblio);
                break;
            case 4:
                prestarLibro(p_biblio);
                continuar();
                menu(p_biblio);
                break;
            case 5:
                devolverLibro(p_biblio);
                continuar();
                menu(p_biblio);
                break;
            case 6:
                mostrarCantidadEstudiantes(p_biblio);
                continuar();
                menu(p_biblio);
                break;
            case 7:
                mostrarDocentesSinDeudas(p_biblio);
                continuar();
                menu(p_biblio);
                break;
            case 8:
                mostrarSocios(p_biblio);
                continuar();
                menu(p_biblio);
                break;
            case 9:
                mostrarLibros(p_biblio);
                continuar();
                menu(p_biblio);
                break;
            case 10:
                consultarLibro(p_biblio);
                continuar();
                menu(p_biblio);
                break;
            case 0:
                System.out.println("Saliendo del programa. ¡Hasta luego!");
                System.out.println("\n--------------------------------------------");
                break;
            default:
                System.out.println("Opción no válida. Inténtelo nuevamente.");
                System.out.println("\n--------------------------------------------");
                continuar();
                menu(p_biblio);
                break;
        }
    }

    private static Docente instanciarDocente()
    {
        Scanner scanner = new Scanner(System.in);
        int dni;
        try {
            System.out.print("Ingrese D.N.I.: ");
            dni = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            entradaInvalida();
            continuar();
            return instanciarDocente();
        }
        System.out.print("Ingrese nombre y apellido: ");
        String nombreApellido = scanner.nextLine();
        System.out.print("Ingrese area: ");
        String area = scanner.nextLine();
        return new Docente(dni, nombreApellido, area);
    }

    private static Estudiante instanciarEstudiante()
    {
        Scanner scanner = new Scanner(System.in);
        int dni;
        System.out.print("Ingrese D.N.I.: ");
        try {
            dni = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            entradaInvalida();
            continuar();
            return instanciarEstudiante();
        }
        System.out.print("Ingrese nombre y apellido: ");
        String nombreApellido = scanner.nextLine();
        System.out.print("Ingrese carrera: ");
        String carrera = scanner.nextLine();

        return new Estudiante(dni, nombreApellido, carrera);
    }

    private static Libro instanciarLibro()
    {
        Scanner scanner = new Scanner(System.in);
        int anioPublicacion;
        int edicion;
        System.out.print("Ingrese título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese editorial: ");
        String editorial = scanner.nextLine();
        System.out.print("Ingrese año de publicación: ");
        try {
            anioPublicacion = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese edicion: ");
            edicion = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            entradaInvalida();
            continuar();
            return instanciarLibro();
        }
        return new Libro(titulo,edicion,editorial, anioPublicacion);
    }

    private static void prestarLibro(Biblioteca p_biblio)
    {
        Scanner scanner = new Scanner(System.in);
        Socio socioRetiro = null;
        System.out.print("Ingrese D.N.I. del Socio: ");
        try {
            int dni = scanner.nextInt();
            scanner.nextLine();
            socioRetiro = p_biblio.buscarSocio(dni);
        }catch (InputMismatchException e){
            entradaInvalida();
        }

        if (socioRetiro == null) {
            System.out.println("Socio no encontrado.");
            System.out.println("Elija nuevamente una opcion.");
        } else {
            System.out.print("Ingrese el título del libro: ");
            String titulo = scanner.nextLine();
            Libro libroAPrestar = null;
            boolean libroEncontrado = false;
            
            for (Libro libro : p_biblio.getLibros()) {
                if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                    libroAPrestar = libro;
                    libroEncontrado = true;
                }
            }

            if (libroEncontrado && !libroAPrestar.prestado()) {
               if (socioRetiro instanceof Estudiante && !socioRetiro.puedePedir()) {
                System.out.println("Este estudiante ya tiene el maximo de libros prestados alcanzado (3)");
                } else {
                Calendar hoy = new GregorianCalendar();
                p_biblio.prestarLibro(hoy, socioRetiro, libroAPrestar);
                System.out.println("Préstamo realizado con éxito.");
            }
            } else {
                System.out.println("Libro no encontrado!");
            }
        }
    }

    private static void devolverLibro(Biblioteca p_biblio)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el título del libro a devolver: ");
        String tituloLibroDevolucion = scanner.nextLine();
        Libro libroDevolver = null;
        boolean libroEncontrado = false;

        for (Libro unLibro : p_biblio.getLibros()) {
            if (unLibro.getTitulo().equalsIgnoreCase(tituloLibroDevolucion)) {
                libroDevolver = unLibro;
                libroEncontrado = true;
            }
        }

        if (libroEncontrado) {
            p_biblio.devolverLibro(libroDevolver);
            System.out.print("DEVOLUCION REGISTRADA!");
        } else {
            System.out.println("Libro no encontrado!");
        }
    }

    private static void mostrarCantidadEstudiantes(Biblioteca p_biblio)
    {
        int cantidadEstudiante = p_biblio.cantidadSociosPorTipo("Estudiante");
        System.out.println("Hay un total de "+cantidadEstudiante+" socios estudiantes");
    }

    private static void mostrarDocentesSinDeudas(Biblioteca p_biblio)
    {
        System.out.println("Los socios Docentes sin adeudar son los siguientes:");
        System.out.println(p_biblio.listaDeDocentesResponsables());
    }

    private static void mostrarSocios(Biblioteca p_biblio)
    {
        System.out.println("La lista de socios de la biblioteca es la siguiente: ");
        System.out.println(p_biblio.listaDeSocios());
    }

    private static void mostrarLibros(Biblioteca p_biblio)
    {
        System.out.println("La lista de libros de la biblioteca es la siguiente: ");
        System.out.println(p_biblio.listaDeLibros());
    }

    private static void consultarLibro(Biblioteca p_biblio)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el titulo del libro a buscar");
        String tituloBuscado = scanner.nextLine();
        Libro libroBuscado = null;
        
        for(Libro unLibro: p_biblio.getLibros()){
            if(unLibro.getTitulo().equalsIgnoreCase(tituloBuscado)){
                libroBuscado = unLibro;
            }
        }
        
        if(libroBuscado != null) {
            System.out.println("El libro consultado se encuentra en poder de: " + p_biblio.quienTieneElLibro(libroBuscado));
        }else {
            System.out.println("No se cuenta con ese título en la biblioteca");
        }
    }

    private static void continuar()
    {
        System.out.println("\n--------------------------------------------");
        Scanner read = new Scanner(System.in);
        System.out.println("\nPresione cualquier tecla para continuar...");
        read.next();
        limpiar();
    }

    private static void limpiar()
    {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
        }
    }

    private static void entradaInvalida()
    {
        System.out.println("Entrada inválida. Por favor, ingrese un número.");
    }
}