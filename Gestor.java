import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


class Gestor extends Usuario {
    ArrayList<Proyecto> proyectos;

    public Gestor(String nombre) {
        super(nombre, "Gestor");
        proyectos = new ArrayList<>();
    }

    public void crearProyecto(String nombreProyecto) {
        Proyecto proyecto = new Proyecto(nombreProyecto, this);
        proyectos.add(proyecto);
        System.out.println("Proyecto creado: " + proyecto);
    }

    public void listarProyectos() {
        System.out.println("Proyectos del gestor " + this.getNombre() + ":");
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto);
        }
    }

    public void asignarProgramadorAProyecto(Programador programador, Proyecto proyecto) {
        proyecto.asignarProgramador(programador);
        programador.proyectos.add(proyecto); // Asigna el proyecto al programador
        System.out.println("Programador " + programador.getNombre() + " asignado al proyecto " + proyecto.getNombre());
    }

    public void crearTareaEnProyecto(String descripcion, Proyecto proyecto, Programador programador) {
        // Verificamos si el programador está asignado al proyecto
        if (!proyecto.getProgramadores().contains(programador)) {
            System.out.println("El programador no está asignado a este proyecto.");
            return;
        }

        // Creamos la nueva tarea y la asignamos al programador
        Tarea tarea = new Tarea(descripcion, programador);
        proyecto.agregarTarea(tarea);
        System.out.println("Tarea creada y asignada a " + programador.getNombre() + " en el proyecto " + proyecto.getNombre());
    }

    public void listarProgramadoresDeProyecto(Proyecto proyecto) {
        if (proyecto.programadores.isEmpty()) {
            System.out.println("No hay programadores asignados al proyecto '" + proyecto.nombre + "'.");
        } else {
            System.out.println("Programadores asignados al proyecto '" + proyecto.nombre + "':");
            for (Programador programador : proyecto.programadores) {
                System.out.println("- " + programador.getNombre());
            }
        }
    }
}