import java.util.ArrayList;
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

    public Programador seleccionarProgramadorDeProyecto(Proyecto proyecto) {
        ArrayList<Programador> programadoresAsignados = proyecto.getProgramadores();

        System.out.println("Programadores asignados al proyecto '" + proyecto.getNombre() + "':");
        for (int i = 0; i < programadoresAsignados.size(); i++) {
            System.out.println((i + 1) + ". " + programadoresAsignados.get(i).getNombre());
        }

        System.out.println("Selecciona el número del programador:");
        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine();

        if (!GestionProyectos.esNumero(opcion)) {
            System.out.println("Opción no válida.");
            return null;
        }

        int indiceProgramador = Integer.parseInt(opcion);
        if (indiceProgramador < 1 || indiceProgramador > programadoresAsignados.size()) {
            System.out.println("Opción inválida.");
            return null;
        }

        return programadoresAsignados.get(indiceProgramador - 1); // Retorna el programador seleccionado
    }


    public void listarProyectos() {
        System.out.println("Proyectos del gestor " + this.getNombre() + ":");
        for (int i = 0; i < proyectos.size(); i++) {
            System.out.println((i + 1) + ". " + proyectos.get(i).getNombre());
        }
    }

    // Método para seleccionar un proyecto usando un número
    public Proyecto seleccionarProyectoPorNumero() {
        if (proyectos.isEmpty()) {
            System.out.println("No tienes proyectos disponibles.");
            return null;
        }

        listarProyectos(); // Listamos todos los proyectos disponibles

        System.out.println("Selecciona el número del proyecto:");
        Scanner scanner = new Scanner(System.in);
        int indiceProyecto = scanner.nextInt();

        if (indiceProyecto < 1 || indiceProyecto > proyectos.size()) {
            System.out.println("Opción inválida.");
            return null;
        }

        return proyectos.get(indiceProyecto - 1); // Retorna el proyecto seleccionado
    }

    // Método para listar y seleccionar un programador por número
    public Programador seleccionarProgramadorPorNumero(ArrayList<Programador> programadores) {
        if (programadores.isEmpty()) {
            System.out.println("No hay programadores disponibles.");
            return null;
        }

        System.out.println("Lista de Programadores:");
        for (int i = 0; i < programadores.size(); i++) {
            System.out.println((i + 1) + ". " + programadores.get(i).getNombre());
        }

        System.out.println("Selecciona el número del programador:");
        Scanner scanner = new Scanner(System.in);
        int indiceProgramador = scanner.nextInt();

        if (indiceProgramador < 1 || indiceProgramador > programadores.size()) {
            System.out.println("Opción inválida.");
            return null;
        }

        return programadores.get(indiceProgramador - 1); // Retorna el programador seleccionado
    }

    public void asignarProgramadorAProyecto(ArrayList<Programador> programadores) {
        Proyecto proyecto = seleccionarProyectoPorNumero(); // Seleccionamos el proyecto por número
        if (proyecto == null) {
            return;
        }

        Programador programador = seleccionarProgramadorPorNumero(programadores); // Selección de programador por número
        if (programador == null) {
            return;
        }

        proyecto.asignarProgramador(programador);
        programador.proyectos.add(proyecto); // Asigna el proyecto al programador
        System.out.println("Programador " + programador.getNombre() + " asignado al proyecto " + proyecto.getNombre());
    }

    public void crearTareaEnProyecto(ArrayList<Programador> programadores) {
        Proyecto proyecto = seleccionarProyectoPorNumero(); // Seleccionamos el proyecto por número
        if (proyecto == null) {
            return;
        }

        // Verificamos si el proyecto tiene programadores asignados
        if (proyecto.getProgramadores().isEmpty()) {
            System.out.println("No hay programadores asignados a este proyecto.");
            System.out.println("1. Seleccionar otro proyecto.");
            System.out.println("2. Volver al menú del gestor.");

            Scanner scanner = new Scanner(System.in);
            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                crearTareaEnProyecto(programadores); // Volvemos a iniciar el proceso de selección
            } else {
                return; // Volvemos al menú del gestor
            }
            return; // Salir si no hay programadores y no se selecciona otra opción
        }

        // Seleccionamos un programador de los ya asignados al proyecto
        Programador programador = seleccionarProgramadorDeProyecto(proyecto);
        if (programador == null) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el título de la tarea:");
        String titulo = scanner.nextLine();

        System.out.println("Introduce la descripción de la tarea:");
        String descripcion = scanner.nextLine();

        // Crear la nueva tarea
        Tarea tarea = new Tarea(titulo, descripcion, programador);
        proyecto.agregarTarea(tarea);
        System.out.println("Tarea '" + titulo + "' creada y asignada a " + programador.getNombre() + " en el proyecto " + proyecto.getNombre());
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
