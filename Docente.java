import java.util.*;

/**
 * Clase Docent.
 *
 * @author (Del Valle Aguilar, Mauro L)
 */

public class Docente extends Socio
{
    private String area;
    
    public Docente(int p_dniSocio, String p_nombre, String p_area)
    {
        super(p_dniSocio, p_nombre, 5);
        this.setArea(p_area);
    }
    
    
    private void setArea(String p_area){
        this.area = p_area;
    }
    public String getArea(){
        return this.area;
    }
    
    
    public boolean esResponsable(){
        Calendar fechaDevolucion = null;
        Calendar fechaHoy = new GregorianCalendar();
        
        
        for(Prestamo prestamo: this.getPrestamos()){
            if(prestamo.getFechaDevolucion() == null && prestamo.vencido(fechaHoy) ||
                prestamo.getFechaDevolucion() != null && prestamo.vencido(prestamo.getFechaDevolucion())){
                    return false;
            }
        }
        return true;
    }
    public void agregarDiasDePrestamo(int p_dias){
        if(this.esResponsable()){
            this.setDiasPrestamo(this.getDiasPrestamo() + p_dias);
        }
    }
    

    public String soyDeLaClase(){
        return "Docente";
    }
}
