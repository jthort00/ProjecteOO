import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


// Clase Tarea
public class Tarea {
    String descripcion;
    boolean finalizada;
    Programador programador;
    public Tarea(String descripcion, Programador programador) {
        this.descripcion = descripcion;
        this.finalizada = false;
        this.programador = programador;
    }
    public void marcarFinalizada() {
        this.finalizada = true;
    }
    @Override
    public String toString() {
        return "Tarea: " + descripcion + ", Finalizada: " + finalizada;
    }
    public Object getProgramador()
    {
        return this.getProgramador();
    }
}