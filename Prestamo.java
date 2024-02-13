import java.util.*;

/**
 * Write a description of class Prestamo here.
 * 
 * @author (Ariel Antinori) 
 * @version (01/11/23)
 */
public class Prestamo
{
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Socio socio;
    private Libro libro;
    
    public Prestamo(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro){
        this.setFechaRetiro(p_fechaRetiro);
        this.setSocio(p_socio);
        this.setLibro(p_libro);
        this.setFechaDevolucion(null);
    }
    public Prestamo(Socio p_socio, Libro p_libro){
       this.setSocio(p_socio);
       this.setLibro(p_libro);
       Calendar hoy = Calendar.getInstance();
       this.setFechaRetiro(hoy);
       this.setFechaDevolucion(null);
    }

    /**
     * A partir de aca los setters
     */
    private void setFechaRetiro(Calendar p_fechaRetiro){
        this.fechaRetiro = p_fechaRetiro;
    }
    private void setFechaDevolucion(Calendar p_fechaDevolucion){
        this.fechaDevolucion = p_fechaDevolucion;
    }
    private void setSocio(Socio p_socio){
        this.socio = p_socio;
    }
    private void setLibro(Libro p_libro){
        this.libro = p_libro;
    }
    /**
     * A partir de aca los getters
     */
    public Calendar getFechaRetiro(){
        return this.fechaRetiro;
    }
    public Calendar getFechaDevolucion(){
        return this.fechaDevolucion;
    }
    public Socio getSocio(){
        return this.socio;
    }
    public Libro getLibro(){
        return this.libro;
    }

    public void registrarFechaDevolucion(Calendar p_fechaDevolucion){
        this.setFechaDevolucion(p_fechaDevolucion);
    }
    public boolean vencido(Calendar p_fecha){
        Calendar comprobar = Calendar.getInstance();

        comprobar.set(Calendar.DATE,this.getFechaRetiro().get(Calendar.DATE));
        comprobar.set(Calendar.MONTH,this.getFechaRetiro().get(Calendar.MONTH));
        comprobar.set(Calendar.YEAR,this.getFechaRetiro().get(Calendar.YEAR));
        comprobar.add(Calendar.DATE,+this.getSocio().getDiasPrestamo());
       
        if(comprobar.compareTo(p_fecha)<=0){
            return true;
        }
        else{
            return false;
        }
    }
    public String toString(){
        String retiro;
            retiro = this.getFechaRetiro().get(Calendar.YEAR)+"/";//concatenamos año
            retiro += (this.getFechaRetiro().get(Calendar.MONTH)+1)+"/";//concatenamos mes- le sumamos 1 para que machee con el mes real
            retiro += this.getFechaRetiro().get(Calendar.DATE);//concatenamos dia               

            String devolucion = "";
               
            if(this.getFechaDevolucion() != null){//consultamos sino esta vacio
               devolucion = this.getFechaDevolucion().get(Calendar.YEAR)+"/";//concatenamos año
               devolucion += (this.getFechaDevolucion().get(Calendar.MONTH)+1)+"/";//concatenamos mes-  le sumamos 1 para que machee con el mes real             
               devolucion += this.getFechaDevolucion().get(Calendar.DATE);//concatenamos dia  
            }
        return "Retiro: "+retiro+" - Devolución: "+devolucion+"\n"+
                "Libro : "+this.getLibro().getTitulo()+"\n"+"Socio: "+this.getSocio().getNombre();
    }
}
