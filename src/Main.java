import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Versió definitiva
class GestionProyectos {
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static HashMap<String,Proyecto> proyectos = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Administrador admin = new Administrador();
        usuarios.add(admin);

        while (true) {
            System.out.println("Introduce tu nombre de usuario. Ten en cuenta que deberás " +
                    "loguearte cómo 'admin' para crear gestores y programadores:");
            String nombreUsuario = scanner.nextLine();
            Usuario usuario = buscarUsuarioPorNombre(nombreUsuario);

            if (usuario != null) {
                if (usuario instanceof Administrador) {
                    menuAdministrador((Administrador) usuario);
                } else if (usuario instanceof Gestor) {
                    menuGestor((Gestor) usuario);
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

    public static void menuAdministrador(Administrador admin) {
        while (true) {
            System.out.println("Menú Administrador:");
            System.out.println("1. Crear usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("0. Salir");

            // Uso de try-catch para capturar entradas no numéricas
            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                if (opcion == 0) break; // Salir del menú

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
                                    break; // Si no quiere continuar, regresa al menú
                                } else {
                                    continue; // Si quiere continuar, vuelve a pedir nombre
                                }
                            }

                            // Si el nombre no está repetido, se procede con la creación del usuario
                            System.out.println("Introduce el rol (Gestor/Programador):");
                            String rol = scanner.nextLine();
                            admin.crearUsuario(nombre, rol, usuarios);
                            System.out.println("Usuario '" + nombre + "' creado exitosamente.");
                            usuarioCreado = true; // Se marca que el usuario fue creado
                        }
                        break;
                    case 2:
                        // Eliminar usuario
                        System.out.println("Introduce el nombre del usuario a eliminar:");
                        String nombreEliminar = scanner.nextLine();
                        if (nombreEliminar.isEmpty()) {
                            System.out.println("Error: El nombre no puede estar vacío.");
                            return;
                        }
                        admin.eliminarUsuario(nombreEliminar, usuarios);
                        break;
                    case 3:
                        // Listar usuarios
                        admin.listarUsuarios(usuarios);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                // Captura la excepción si el usuario ingresa algo que no es un número
                System.out.println("Esa opción no existe. Por favor, elige una opción de la lista.");
            }
        }
    }



    public static void menuGestor(Gestor gestor) {
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
                    System.out.println("Introduce el nombre del proyecto:");
                    nombreProyecto = scanner.nextLine();
                    Proyecto proyecto = buscarProyectoPorNombre(nombreProyecto);
                    if (proyecto == null) {
                        System.out.println("Error: Proyecto no encontrado.");
                        System.out.println("¿Quieres crear un nuevo proyecto con este nombre? (sí/no):");
                        String respuesta = scanner.nextLine();
                        if (respuesta.equalsIgnoreCase("sí") || respuesta.equalsIgnoreCase("si")) {
                            gestor.crearProyecto(nombreProyecto);
                            System.out.println("Proyecto '" + nombreProyecto + "' creado exitosamente.");
                        } else {
                            System.out.println("Regresando al menú del gestor...");
                        }
                        break;
                    }
                    // Resto de código para asignar programador si el proyecto fue encontrado
                    System.out.println("Introduce el nombre del programador:");
                    String nombreProgramador = scanner.nextLine();
                    Programador programador = (Programador) buscarUsuarioPorNombre(nombreProgramador);
                    if (programador == null) {
                        System.out.println("Error: Programador no encontrado.");
                        break;
                    }
                    gestor.asignarProgramadorAProyecto(programador, proyecto);
                    break;
                case 4:
                    // Crear tarea en proyecto
                    System.out.println("Introduce el nombre del proyecto:");
                    nombreProyecto = scanner.nextLine();
                    proyecto = buscarProyectoPorNombre(nombreProyecto);
                    if (proyecto == null) {
                        System.out.println("Error: Proyecto no encontrado.");
                        System.out.println("¿Quieres crear un nuevo proyecto con este nombre? (sí/no):");
                        String respuesta = scanner.nextLine().trim().toLowerCase();
                        if (respuesta.equals("sí")) {
                            gestor.crearProyecto(nombreProyecto);
                            System.out.println("Proyecto '" + nombreProyecto + "' creado.");
                            proyecto = buscarProyectoPorNombre(nombreProyecto); // Actualizar referencia del proyecto
                        } else {
                            System.out.println("Regresando al menú del gestor...");
                            continue;
                        }
                    }
                    // Aquí puedes continuar con la lógica para crear la tarea si el proyecto ya existe
                    System.out.println("Introduce el nombre del programador:");
                    nombreProgramador = scanner.nextLine();
                    programador = (Programador) buscarUsuarioPorNombre(nombreProgramador);
                    if (programador == null) {
                        System.out.println("Error: Programador no encontrado.");
                        continue;
                    }
                    System.out.println("Introduce la descripción de la tarea:");
                    String descripcionTarea = scanner.nextLine();
                    gestor.crearTareaEnProyecto(descripcionTarea, proyecto, programador);
                    break;



                case 5:
                    System.out.println("Programadores: ");
                    for (Usuario usuario : usuarios) {
                        if (usuario.getRol().equals("Programador")) {
                            System.out.println(usuario.getNombre());
                        }
                    }
                    break;
                case 6:
                    System.out.println("Introduce el nombre del proyecto:");
                    nombreProyecto = scanner.nextLine();
                    proyecto = buscarProyectoPorNombre(nombreProyecto, gestor);
                    if (proyecto != null) {
                        gestor.listarProgramadoresDeProyecto(proyecto);
                    } else {
                        System.out.println("Proyecto no encontrado.");
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
            int opcion = Integer.parseInt(scanner.nextLine());

            if (opcion == 0) break;

            switch (opcion) {
                case 1:
                    programador.consultarProyectos();
                    break;
                case 2:
                    System.out.println("Introduce el nombre del proyecto:");
                    String nombreProyecto = scanner.nextLine();
                    Proyecto proyecto = buscarProyectoPorNombre(nombreProyecto);
                    if (proyecto != null) {
                        programador.consultarTareas(proyecto);
                    } else {
                        System.out.println("Error: Proyecto no encontrado.");
                    }
                    programador.consultarTareas(proyecto);
                    break;
                case 3:
                    System.out.println("Introduce el nombre del proyecto:");
                    nombreProyecto = scanner.nextLine();
                    proyecto = buscarProyectoPorNombre(nombreProyecto);
                    if (proyecto == null) {
                        System.out.println("Error: Proyecto no encontrado.");
                        return;
                    }
                    System.out.println("Introduce la descripción de la tarea:");
                    String descripcionTarea = scanner.nextLine();
                    Tarea tareaEncontrada = null;
                    for (Tarea tarea : proyecto.tareas) {
                        if (tarea.descripcion.equals(descripcionTarea) && tarea.programador.equals(programador)) {
                            programador.marcarTareaFinalizada(tarea);
                            tareaEncontrada = tarea;
                        }
                    }
                    if (tareaEncontrada != null) {
                        programador.marcarTareaFinalizada(tareaEncontrada);
                        System.out.println("Tarea marcada como finalizada.");
                    }
                    if(tareaEncontrada == null)
                    {
                        System.out.println("Error: Tarea no encontrada o no asignada a este programador.");
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
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

