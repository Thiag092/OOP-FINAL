import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.util.*;
/**
 * La clase BibliotecaGrafica es una interfaz gráfica que permite interactuar con las distintas opciones de Biblioteca.
 * 
 * @author Diaz, Isaac
 * @version 15/11/23
 */
public class BibliotecaGrafica
{
    public static void main(Biblioteca p_biblioteca)
    {
        JFrame frame = new JFrame("Menu de "+ p_biblioteca.getNombre());
        frame.setSize(300, 280);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        JButton agregarSocio = new JButton("Agregar socio");
        JButton mostrarSocios = new JButton("Mostrar socios");
        JButton agregarLibro = new JButton("Agregar Libro");
        JButton mostrarLibros = new JButton("Mostrar libros");
        JButton prestarLibro = new JButton("Prestar libro");
        JButton devolverLibro = new JButton("Devolver libro");
        JButton cantidadEstudiantes = new JButton("Cantidad de estudiantes");
        JButton docentesSinDeudas = new JButton("Docentes sin deudas");
        JButton consultarLibro = new JButton("Consultar libro");
        JButton quitarSocio = new JButton("Quitar socio");
        JButton quitarLibro = new JButton("Quitar libro");
        JButton plazosVencidos = new JButton("Plazos de libros vencidos");

        agregarSocio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] opciones = {"Estudiante", "Profesor"};
                int opcion = JOptionPane.showOptionDialog(null, "Seleccione el tipo de socio:", "Tipo de socio", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del socio:");
                int dni = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el DNI del socio:"));
                if (opcion == 0) {
                    String carrera = JOptionPane.showInputDialog(null, "Ingrese la carrera del estudiante:");
                    p_biblioteca.addSocio(new Estudiante(dni, nombre, carrera));
                } else {
                    String area = JOptionPane.showInputDialog(null, "Ingrese el area del profesor:");
                    p_biblioteca.addSocio(new Docente(dni, nombre, area));
                }
                JOptionPane.showMessageDialog(null, "Socio agregado exitosamente.");
            }
        });

        mostrarSocios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, p_biblioteca.listaDeSocios());
            }
        });

        agregarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog(null, "Ingrese el nombre del libro");
                int edicion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de edicion"));
                String editorial = JOptionPane.showInputDialog(null, "Ingrese la editorial");
                int anio = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el año"));
                p_biblioteca.addLibro(new Libro(titulo, edicion, editorial, anio));
                JOptionPane.showMessageDialog(null, "Libro agregado exitosamente.");
            }
        });

        mostrarLibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, p_biblioteca.listaDeLibros());
            }
        });

        prestarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dni = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el dni del socio"));
                Socio socio = p_biblioteca.buscarSocio(dni);
                if(socio == null){
                    JOptionPane.showMessageDialog(null, "Socio no encontrado");
                }else if(!socio.puedePedir()){
                    JOptionPane.showMessageDialog(null, "Socio no puede pedir mas libros");
                }
                else{
                    String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo del libro");
                    Libro libroAPrestar = null;
                    boolean encontrado = false;
                    for (Libro libro : p_biblioteca.getLibros()) {
                        if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                            libroAPrestar = libro;
                            encontrado = true;
                        }
                    }
                    
                    
                    if (encontrado && !libroAPrestar.prestado()) {
                        Calendar hoy = new GregorianCalendar();
                        p_biblioteca.prestarLibro(hoy, socio, libroAPrestar);
                        JOptionPane.showMessageDialog(null, "Préstamo realizado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Libro no encontrado!");
                    }
                }
            }
        });

        devolverLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo del libro");
                Libro aDevolver = null;
                boolean encontrado = false;

                for (Libro unLibro : p_biblioteca.getLibros()) {
                    if (unLibro.getTitulo().equalsIgnoreCase(titulo)) {
                        aDevolver = unLibro;
                        encontrado = true;
                    }
                }

                if (encontrado && aDevolver.prestado()) {
                    p_biblioteca.devolverLibro(aDevolver);
                    JOptionPane.showMessageDialog(null,"DEVOLUCION REGISTRADA!");
                } else {
                    JOptionPane.showMessageDialog(null,"Libro no encontrado!");
                }
            }
        });

        cantidadEstudiantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad = p_biblioteca.cantidadSociosPorTipo("Estudiante");
                JOptionPane.showMessageDialog(null, "Hay un total de " + cantidad + " socios estudiantes");
            }
        });

        docentesSinDeudas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, p_biblioteca.listaDeDocentesResponsables());
            }
        });

        consultarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo del libro");
                Libro aBuscar = null;
                for(Libro unLibro: p_biblioteca.getLibros()){
                    if(unLibro.getTitulo().equalsIgnoreCase(titulo)){
                        aBuscar = unLibro;
                    }
                }
                if(aBuscar != null) {
                    JOptionPane.showMessageDialog(null, "El libro consultado se encuentra en poder de: " + p_biblioteca.quienTieneElLibro(aBuscar));
                }else {
                    JOptionPane.showMessageDialog(null,"No se cuenta con ese titulo en la biblioteca");
                }
            }
        });

        quitarSocio.addActionListener(e -> {
            int dni = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el dni del socio a eliminar"));
            Socio socio = null;
            socio = p_biblioteca.buscarSocio(dni);
            if(socio != null){
                p_biblioteca.removeSocio(socio);
                JOptionPane.showMessageDialog(null, "Socio eliminado");
            }else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado al socio");
            }
        });

        quitarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog(null, "Ingrese el nombre del libro a eliminar");
                Libro libro = null;
                for(Libro unLibro: p_biblioteca.getLibros()){
                    if(unLibro.getTitulo().equalsIgnoreCase(titulo)){
                        libro = unLibro;
                    }
                }
                if(libro != null){
                    p_biblioteca.removeLibro(libro);
                    JOptionPane.showMessageDialog(null, "Libro eliminado");
                }else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el libro");
                }
            }
        });

        plazosVencidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String espacio="";
                if(p_biblioteca.prestamosVencidos().isEmpty()){
                    JOptionPane.showMessageDialog(null, "No hay libros con plazos vencidos");
                }else {
                    for (int i = 0; i < p_biblioteca.prestamosVencidos().size(); i++) {
                        Libro libro = p_biblioteca.prestamosVencidos().get(i).getLibro();
                        espacio += "Titulo: " + libro.getTitulo() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, espacio);
                }
            }
        });

        panel.add(agregarSocio);
        panel.add(mostrarSocios);
        panel.add(agregarLibro);
        panel.add(mostrarLibros);
        panel.add(prestarLibro);
        panel.add(devolverLibro);
        panel.add(cantidadEstudiantes);
        panel.add(docentesSinDeudas);
        panel.add(consultarLibro);
        panel.add(quitarSocio);
        panel.add(quitarLibro);
        panel.add(plazosVencidos);

        frame.setVisible(true);
    }
}
