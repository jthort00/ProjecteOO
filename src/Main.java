import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Versió definitiva
class GestionProyectos {
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static HashMap<String,Proyecto> proyectos = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Crear un administrador y agregarlo a la lista de usuarios
        Administrador admin = new Administrador();
        usuarios.add(admin);

        // Inicializar la lista de programadores
        ArrayList<Programador> programadores = new ArrayList<>(); // Lista de programadores

        while (true) {
            System.out.println("Introduce tu nombre de usuario. Ten en cuenta que deberás " +
                    "loguearte cómo 'admin' para crear gestores y programadores:");
            String nombreUsuario = scanner.nextLine();
            Usuario usuario = buscarUsuarioPorNombre(nombreUsuario);

            if (usuario != null) {
                if (usuario instanceof Administrador) {
                    menuAdministrador((Administrador) usuario, programadores); // Ahora pasas también la lista de programadores
                } else if (usuario instanceof Gestor) {
                    menuGestor((Gestor) usuario, programadores);
                } else if (usuario instanceof Programador) {
                    menuProgramador((Programador) usuario);
                }
            } else {
                System.out.println("Usuario no encontrado.");
            }
        }
    }


    public static Usuario buscarUsuarioPorNombre(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            }
        }
        return null;
    }

    public static void menuAdministrador(Administrador admin, ArrayList<Programador> programadores) {
        while (true) {
            System.out.println("Menú Administrador:");
            System.out.println("1. Crear usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("0. Salir");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                if (opcion == 0) break;

                switch (opcion) {
                    case 1:
                        // Crear usuario
                        boolean usuarioCreado = false;
                        while (!usuarioCreado) {
                            System.out.println("Introduce el nombre del nuevo usuario:");
                            String nombre = scanner.nextLine();

                            if (nombre.isEmpty()) {
                                System.out.println("Error: El nombre no puede estar vacío.");
                                continue;
                            }

                            if (buscarUsuarioPorNombre(nombre, usuarios) != null) {
                                System.out.println("Error: El nombre de usuario '" + nombre + "' ya está en uso.");
                                System.out.println("¿Quieres intentar con otro nombre? (sí/no):");
                                String respuesta = scanner.nextLine().trim().toLowerCase();
                                if (respuesta.equals("no")) {
                                    break;
                                } else {
                                    continue;
                                }
                            }

                            System.out.println("Selecciona el rol:");
                            System.out.println("1. Gestor");
                            System.out.println("2. Programador");
                            int rolSeleccionado = Integer.parseInt(scanner.nextLine());

                            switch (rolSeleccionado) {
                                case 1:
                                    Gestor gestor = new Gestor(nombre);
                                    usuarios.add(gestor);
                                    System.out.println("Gestor '" + nombre + "' creado exitosamente.");
                                    break;
                                case 2:
                                    Programador programador = new Programador(nombre);
                                    usuarios.add(programador);
                                    programadores.add(programador);  // Mantener la lista actualizada
                                    System.out.println("Programador '" + nombre + "' creado exitosamente.");
                                    break;
                                default:
                                    System.out.println("Rol no válido. Inténtalo de nuevo.");
                                    continue;
                            }
                            usuarioCreado = true;
                        }
                        break;
                    case 2:
                        System.out.println("Introduce el nombre del usuario a eliminar:");
                        String nombreEliminar = scanner.nextLine();
                        if (nombreEliminar.isEmpty()) {
                            System.out.println("Error: El nombre no puede estar vacío.");
                            return;
                        }
                        admin.eliminarUsuario(nombreEliminar, usuarios);
                        break;
                    case 3:
                        admin.listarUsuarios(usuarios);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Esa opción no existe. Por favor, elige una opción de la lista.");
            }
        }
    }


    public static void menuGestor(Gestor gestor, ArrayList<Programador> programadores) {
        while (true) {
            System.out.println("Menú Gestor:");
            System.out.println("1. Crear proyecto");
            System.out.println("2. Listar proyectos");
            System.out.println("3. Asignar programador a proyecto");
            System.out.println("4. Crear tarea en proyecto");
            System.out.println("5. Listar todos los programadores");
            System.out.println("6. Listar programadores de un proyecto");
            System.out.println("0. Salir");
            String opcion = scanner.nextLine();

            if (!esNumero(opcion)) {
                System.out.println("Esa opción no existe. Por favor, elige una opción de la lista.");
                continue;
            }

            int opcionNum = Integer.parseInt(opcion);
            if (opcionNum == 0) break;

            switch (opcionNum) {
                case 1:
                    System.out.println("Introduce el nombre del proyecto:");
                    String nombreProyecto = scanner.nextLine();
                    if (nombreProyecto.isEmpty()) {
                        System.out.println("Error: El nombre del proyecto no puede estar vacío.");
                        break;
                    }
                    gestor.crearProyecto(nombreProyecto);
                    break;
                case 2:
                    gestor.listarProyectos();
                    break;
                case 3:
                    gestor.asignarProgramadorAProyecto(programadores); // Asignación de programador a proyecto
                    break;
                case 4:
                    gestor.crearTareaEnProyecto(programadores); // Creación de tarea
                    break;
                case 5:
                    // Listar todos los programadores disponibles
                    if (programadores.isEmpty()) {
                        System.out.println("No hay programadores disponibles.");
                    } else {
                        System.out.println("Programadores disponibles: ");
                        for (int i = 0; i < programadores.size(); i++) {
                            System.out.println((i + 1) + ". " + programadores.get(i).getNombre());
                        }
                    }
                    break;
                case 6:
                    // Listar programadores de un proyecto
                    Proyecto proyectoSeleccionado = gestor.seleccionarProyectoPorNumero(); // Selección de proyecto por número
                    if (proyectoSeleccionado != null) {
                        gestor.listarProgramadoresDeProyecto(proyectoSeleccionado);
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }




    public static boolean esNumero(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    public static Proyecto buscarProyectoPorNombre(String nombre, Usuario usuario) {
        if (usuario instanceof Gestor) {
            Gestor gestor = (Gestor) usuario;
            for (Proyecto proyecto : gestor.proyectos) {
                if (proyecto.nombre.equalsIgnoreCase(nombre)) {
                    return proyecto;
                }
            }
        } else if (usuario instanceof Programador) {
            Programador programador = (Programador) usuario;
            for (Proyecto proyecto : programador.proyectos) {
                if (proyecto.nombre.equalsIgnoreCase(nombre)) {
                    return proyecto;
                }
            }
        }
        return null;
    }

    public static void menuProgramador(Programador programador) {
        while (true) {
            System.out.println("Menú Programador:");
            System.out.println("1. Consultar proyectos asignados");
            System.out.println("2. Consultar tareas en un proyecto");
            System.out.println("3. Marcar tarea como finalizada");
            System.out.println("0. Salir");

            String opcion = scanner.nextLine();

            if (!esNumero(opcion)) {
                System.out.println("Opción no válida.");
                continue;
            }

            int opcionNum = Integer.parseInt(opcion);
            if (opcionNum == 0) break;

            switch (opcionNum) {
                case 1:
                    programador.consultarProyectos();
                    break;
                case 2:
                    Proyecto proyectoSeleccionado = seleccionarProyectoDeProgramador(programador);
                    if (proyectoSeleccionado != null) {
                        programador.consultarTareas(proyectoSeleccionado);
                    }
                    break;
                case 3:
                    Proyecto proyectoParaTarea = seleccionarProyectoDeProgramador(programador);
                    if (proyectoParaTarea == null) {
                        System.out.println("Error: Proyecto no encontrado.");
                        break;
                    }
                    System.out.println("Introduce la descripción de la tarea:");
                    String descripcionTarea = scanner.nextLine();
                    Tarea tareaEncontrada = null;

                    // Buscar la tarea en el proyecto
                    for (Tarea tarea : proyectoParaTarea.tareas) {
                        if (tarea.getDescripcion().equals(descripcionTarea) && tarea.getProgramador().equals(programador)) {
                            tareaEncontrada = tarea;
                        }
                    }
                    if (tareaEncontrada != null) {
                        programador.marcarTareaFinalizada(tareaEncontrada);
                        System.out.println("Tarea marcada como finalizada.");
                    } else {
                        System.out.println("Error: Tarea no encontrada o no asignada a este programador.");
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    public static Proyecto seleccionarProyectoDeProgramador(Programador programador) {
        ArrayList<Proyecto> proyectosAsignados = programador.getProyectos();

        if (proyectosAsignados.isEmpty()) {
            System.out.println("No tienes proyectos asignados.");
            return null;
        }

        System.out.println("Proyectos asignados:");
        for (int i = 0; i < proyectosAsignados.size(); i++) {
            System.out.println((i + 1) + ". " + proyectosAsignados.get(i).getNombre());
        }

        System.out.println("Selecciona un proyecto por número:");
        String opcion = scanner.nextLine();

        if (!esNumero(opcion)) {
            System.out.println("Opción no válida.");
            return null;
        }

        int numeroProyecto = Integer.parseInt(opcion);
        if (numeroProyecto < 1 || numeroProyecto > proyectosAsignados.size()) {
            System.out.println("Número de proyecto no válido.");
            return null;
        }

        return proyectosAsignados.get(numeroProyecto - 1); // Devolver el proyecto seleccionado
    }





    public static Proyecto buscarProyectoPorNombre(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Gestor) {
                Gestor gestor = (Gestor) usuario;
                for (Proyecto proyecto : gestor.proyectos) {
                    if (proyecto.nombre.equals(nombre)) {
                        return proyecto;
                    }
                }
            }
        }
        return null;
    }
    public static Usuario buscarUsuarioPorNombre(String nombre, ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre.trim())) {
                return usuario;
            }
        }
        return null;
    }
}

