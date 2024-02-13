import java.util.*;
import java.util.ArrayList;

public class Biblioteca
{
    private String nombre;
    private ArrayList<Libro> libros;
    private ArrayList<Socio> socios;
    
    public Biblioteca(String nombre)
    {
        this.setNombre(nombre);
        this.setLibros(new ArrayList<Libro>());
        this.setSocios(new ArrayList<Socio>());
    }
    
    private void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    private void setLibros(ArrayList<Libro> libros)
    {
        this.libros = libros;
    }
    private void setSocios(ArrayList<Socio> socios)
    {
        this.socios = socios;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    public ArrayList<Libro> getLibros()
    {
        return this.libros;
    }
    public ArrayList<Socio> getSocios()
    {
        return this.socios;
    }
    
    public void addSocio(Socio p_socio)
    {
        this.getSocios().add(p_socio);
    }
    public void removeSocio(Socio p_socio)
    {
        this.getSocios().remove(p_socio);
    }
    
    public void addLibro(Libro p_libro)
    {
        this.getLibros().add(p_libro);
    }
    
    public void removeLibro(Libro p_libro)
    {
        this.getLibros().remove(p_libro);
    }
    
    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int p_anio)
    {
        Libro nuevoLibro = new Libro(p_titulo,p_edicion,p_editorial,p_anio);
        this.addLibro(nuevoLibro);
    }
    
    public void nuevoSocioEstudiante(int p_dniSocio,String p_nombre,String p_carrera)
    {
        Estudiante nuevoEstudiante = new Estudiante(p_dniSocio,p_nombre,p_carrera);
        this.getSocios().add(nuevoEstudiante);
    }
    
    public void nuevoSocioDocente(int p_dniSocio,String p_nombre,String p_area){
        Docente nuevoDocente = new Docente(p_dniSocio,p_nombre,p_area);
        this.addSocio(nuevoDocente);
    }
    
    public int cantidadSociosPorTipo(String p_objeto)
    {
        int cantidad=0;
        for (Socio unSocio :  this.getSocios()) {
            if(unSocio.soyDeLaClase() == p_objeto){
                cantidad+=1;
            }
        }
        return cantidad;
    }
    
    public boolean prestarLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro)
    {
        Prestamo prestamo = new Prestamo(p_fechaRetiro, p_socio, p_libro);
        if (p_socio.puedePedir()) {
            p_libro.addPrestamo(prestamo);
            return p_socio.addPrestamo(prestamo);
            } else {
            return false;
        }
    }
    
    public void devolverLibro(Libro p_libro)
    {
        Calendar fechaHoy = new GregorianCalendar();
        Prestamo prestamo = p_libro.getPrestamo();
        
        if (prestamo != null) {
            prestamo.registrarFechaDevolucion(fechaHoy);
            prestamo.getSocio().removePrestamo(prestamo);
        } else {
            System.out.println("El libro no tiene préstamo registrado.");
        }
    }
    
    public ArrayList<Prestamo> prestamosVencidos()
    {
        Calendar fechaHoy = new GregorianCalendar();
        ArrayList prestamosVencidos = new ArrayList();
        
        for(Socio unSocio: this.getSocios()){
            for(Prestamo unPrestamo: unSocio.getPrestamos()){
                if(unPrestamo.vencido(fechaHoy) == true){
                    prestamosVencidos.add(unPrestamo);
                }
            }
        }
        return prestamosVencidos;
    }
    
    public ArrayList<Docente> docentesResponsables()
    {
        ArrayList<Docente> docentesResponsables = new ArrayList();
        for(Socio unSocio: this.getSocios()){
            if(unSocio.getClass() == Docente.class && ((Docente)unSocio).esResponsable() == true){
                docentesResponsables.add((Docente)unSocio);
            }
        }
        return docentesResponsables;
    }
    
    public String quienTieneElLibro(Libro p_libro)
    {
        String socio="";
        
        for(Socio unSocio: this.getSocios()){
            for(Prestamo unPrestamo: unSocio.getPrestamos()){
                if(unPrestamo.getLibro().getTitulo().equalsIgnoreCase(p_libro.getTitulo())){
                    socio = unSocio.getNombre();
                    return socio;
                }
            }
        }
        return "El libro se encuentra en biblioteca";
    }
    
    public String listaDeSocios()
    {
        String espacio="";
        int aumentar = 1;
           
        for(Socio unSocio: this.getSocios()){
                espacio+=aumentar+")"+unSocio.toString()+"|| Libros Prestados: "+ unSocio.cantLibrosPrestados()+"\n";
                aumentar++;
            }
        espacio+="\n********************************************";
        espacio+="\nCant. Socio Tipo Docente: "+ this.cantidadSociosPorTipo("Docente");
        espacio+="\nCant. Socio Tipo Estudiante: "+ this.cantidadSociosPorTipo("Estudiante");
        espacio+="\n********************************************";
        return espacio;
    }
    
    public String listaDeLibros()
    {
        String espacio="";
        int aumentar = 1;

        for(Libro unLibro: this.getLibros()){
            String opcion= "";

            if(unLibro.prestado()){
                opcion="SI";
            } else{
                opcion="NO";
            }
                espacio += aumentar + ")"+"Titulo: "+unLibro.getTitulo()+"|| Prestado: "+"("+opcion+")"+"\n";
                aumentar++;
        }
        return espacio;
    }

    public String listaDeDocentesResponsables()
    {
       String listaResponsables = "";
       
       for(Socio unSocio: this.getSocios()){
           if(unSocio.soyDeLaClase() == "Docente"){
               listaResponsables += "DNI: "+unSocio.getDniSocio()+
               " || "+unSocio.getNombre()+"("+((Docente)unSocio).soyDeLaClase()+")"+
               " || Libros prestados: "+unSocio.cantLibrosPrestados() + "\n";
           }
       }
       return listaResponsables;
    }

    public Socio buscarSocio(int p_dni)
    {
        Socio socio=null;
    
        for(Socio unSocio: this.getSocios()){
            if(unSocio.getDniSocio()==p_dni){
                socio=unSocio;
                break;
            }
        }
        return socio;
    }
}