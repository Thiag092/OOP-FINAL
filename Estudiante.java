/**
 * Clase Estudiante.
 *
 * @author (Del Valle Aguilar, Mauro L)
 */
public class Estudiante extends Socio
{
    private String carrera;
    
    public Estudiante(int p_dniSocio, String p_nombre, String p_carrera)
    {
        super(p_dniSocio, p_nombre, 20);
        this.setCarrera(p_carrera);
    }
    
    private void setCarrera(String p_carrera)
    {
        this.carrera = p_carrera;
    }
    public String getCarrera()
    {
        return this.carrera;
    }
    
    public boolean puedePedir()
    {
        int librosNoDevueltos = 0; 
        for(Prestamo prestamo : this.getPrestamos()){
            if(prestamo.getFechaDevolucion() == null){
                librosNoDevueltos++;
            }
        }
        
        return  ((librosNoDevueltos < 3) && super.puedePedir());
    }
    
    public String soyDeLaClase()
    {
        return "Estudiante";
    }
    
    
    

}
