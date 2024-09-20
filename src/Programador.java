import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Programador extends Usuario {
    ArrayList<Proyecto> proyectos;

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
            if (tarea.programador.equals(this)) {
                System.out.println(tarea);
            }
        }
    }

    public void marcarTareaFinalizada(Tarea tarea) {
        tarea.marcarFinalizada();
        System.out.println("Tarea " + tarea.descripcion + " marcada como finalizada.");
    }
}