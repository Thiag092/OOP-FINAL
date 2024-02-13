public class BibliotecaExecutable
{
    public static void main(String[] args) 
    {
        Biblioteca unaBiblioteca = new Biblioteca("Hemeroteca");
        GestionBiblioteca.agregarInstancias(unaBiblioteca);
        BibliotecaGrafica.main(unaBiblioteca);
    }
}