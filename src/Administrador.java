import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Administrador extends Usuario {
    public Administrador() {
        super("admin", "Administrador");
    }

    public void crearUsuario(String nombre, String rol, ArrayList<Usuario> usuarios) {
        Usuario nuevoUsuario;
        if (rol.equalsIgnoreCase("Gestor")) {
            nuevoUsuario = new Gestor(nombre);
        } else if (rol.equalsIgnoreCase("Programador")) {
            nuevoUsuario = new Programador(nombre);
        } else {
            System.out.println("Rol no válido.");
            return;
        }
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario creado: " + nuevoUsuario);
    }
    private Usuario buscarUsuarioPorNombre(String nombre, ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre.trim())) {
                return usuario;
            }
        }
        return null;
    }
    public void eliminarUsuario(String nombre, ArrayList<Usuario> usuarios) {
        Usuario usuarioAEliminar = buscarUsuarioPorNombre(nombre, usuarios);

        if (usuarioAEliminar != null) {
            usuarios.remove(usuarioAEliminar);
            System.out.println("Usuario " + nombre + " eliminado correctamente.");
        } else {
            System.out.println("Error: El usuario '" + nombre + "' no existe.");
        }
    }

    public void listarUsuarios(ArrayList<Usuario> usuarios) {
        System.out.println("Lista de usuarios:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    // Nueva función para listar solo programadores
    public void listarProgramadores(ArrayList<Usuario> usuarios) {
        System.out.println("Lista de programadores:");
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Programador) {
                System.out.println(usuario.getNombre());
            }
        }
    }
}