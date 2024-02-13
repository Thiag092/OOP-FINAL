import java.util.*;

/**
 * Clase abstracta Socio, define a un socio de la biblioteca con sus atributos caracteristicos.
 * 
 * @author (Ariel Antinori) 
 * @version (1/11/23)
 */
public abstract class Socio
{
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    ArrayList<Prestamo> prestamos;
    
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo){
        prestamos = new ArrayList<Prestamo>();
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(prestamos);
    }
    
    private void setDniSocio(int p_dniSocio){
        this.dniSocio = p_dniSocio;
    }
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }
    public void setDiasPrestamo(int p_dias){
        this.diasPrestamo = p_dias;
    }
    private void setPrestamos(ArrayList p_prestamos){
        this.prestamos = p_prestamos;
    }
    public int getDniSocio(){
        return this.dniSocio;
    }
    public String getNombre(){
        return this.nombre;
    }
    public int getDiasPrestamo(){
        return this.diasPrestamo;
    }    
    public ArrayList<Prestamo> getPrestamos(){
        return this.prestamos;
    }

    public int cantLibrosPrestados(){
        return this.getPrestamos().size();
    }

    public String toString(){
        return "D.N.I.: "+this.getDniSocio()+" || "+this.getNombre()+" ("+this.getClass().getName()+")";
    }

    public boolean puedePedir(){
       boolean puede_pedir = true;
        Calendar hoy = Calendar.getInstance();//instanciamos fecha actual
      
           for(Prestamo unPrestamo : this.getPrestamos()){
               
               if(unPrestamo.vencido(hoy)){
                    puede_pedir = false;
                }
            }
            
        return puede_pedir;
    }

    public boolean addPrestamo(Prestamo p_prestamo){
        return this.getPrestamos().add(p_prestamo);
    }

    public boolean removePrestamo(Prestamo p_prestamo){
        return this.getPrestamos().remove(p_prestamo);
    }
    
    public abstract String soyDeLaClase();
}
