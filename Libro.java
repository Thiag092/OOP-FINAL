/**
 * La clase Libro es una representacion de este en una biblioteca que registra sus 
 * caracteristicas y los prestamos que se efectuan sobre el mismo.
 * 
 * @author Diaz, Isaac 
 * @version 05/11/23
 */

import java.util.ArrayList;

public class Libro
{
    private String titulo;
    private int edicion;
    private String editorial;
    private int anio;
    private ArrayList<Prestamo> prestamos;

    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio)
    {
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(new ArrayList<Prestamo>());
    }

    private void setAnio(int p_anio)
    {
        this.anio = p_anio;
    }
    private void setEditorial(String p_editorial)
    {
        this.editorial = p_editorial;
    }
    private void setEdicion(int p_edicion)
    {
        this.edicion = p_edicion;
    }
    private void setTitulo(String p_titulo) {
        this.titulo = p_titulo;
    }
    private void setPrestamos(ArrayList<Prestamo> p_prestamos)
    {
        this.prestamos = p_prestamos;
    }
    
    public String getTitulo()
    {
        return this.titulo;
    }
    public int getEdicion()
    {
        return this.edicion;
    }
    public String getEditorial()
    {
        return this.editorial;
    }
    public Prestamo getPrestamo()
    {
        if (prestamos.isEmpty()) {
            return null;
        }else{
            return this.getPrestamos().get(this.getPrestamos().size()-1);
        }
    }
    public int getAnio()
    {
        return this.anio;
    }
    public ArrayList<Prestamo> getPrestamos()
    {
        return this.prestamos;
    }

    public boolean addPrestamo(Prestamo p_prestamo)
    {
        return this.getPrestamos().add(p_prestamo);
    }
    
    /**
     * Verifica si el último prestamo ya se devolvió.
     * @return booleano
     */
    public boolean prestado()
    {
        return this.getPrestamo() != null && this.getPrestamo().getFechaDevolucion() == null;
    }
    
    /**
     * Devuelve la palabra titulo más el titulo del libro.
     * @return String
     */
    public String toString()
    {
        return "Titulo: "+ this.getTitulo();
    }
    
    public boolean removePrestamo(Prestamo p_prestamo)
    {
        return this.getPrestamos().remove(p_prestamo);
    }
}
