import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



// Clase Usuario
public abstract class Usuario {
    String nombre;
    String rol;

    public Usuario(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Rol: " + rol;
    }
}