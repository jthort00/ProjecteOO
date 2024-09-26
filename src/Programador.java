import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Programador extends Usuario {
    ArrayList<Proyecto> proyectos = new ArrayList<>();

    public ArrayList<Proyecto> getProyectos() {
        return proyectos;
    }

    public Programador(String nombre) {
        super(nombre, "Programador");
        proyectos = new ArrayList<>();
    }

    public void consultarProyectos() {
        System.out.println("Proyectos asignados a " + this.getNombre() + ":");
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto.nombre);
        }
    }

    public void consultarTareas(Proyecto proyecto) {
        System.out.println("Tareas asignadas a " + this.getNombre() + " en el proyecto " + proyecto.nombre + ":");
        for (Tarea tarea : proyecto.tareas) {
            if (tarea.getProgramador().equals(this)) {
                System.out.println(tarea);
            }
        }
    }

    public static void marcarTareaFinalizada(Tarea tarea) {
        if (tarea != null) {
            tarea.setFinalizada(true);
            System.out.println("La tarea '" + tarea.getTitulo() + "' ha sido marcada como finalizada.");
        } else {
            System.out.println("La tarea no existe.");
        }
    }
}
