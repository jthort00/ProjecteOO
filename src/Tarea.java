public class Tarea {
    private String titulo;
    private String descripcion;
    private Programador programador;
    private boolean finalizada;  // Atributo para marcar si la tarea está finalizada

    public Tarea(String titulo, String descripcion, Programador programador) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.programador = programador;
        this.finalizada = false; // Inicialmente no finalizada
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Programador getProgramador() {
        return programador;
    }

    // Getter y Setter para el atributo finalizada
    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    // Método toString para facilitar la impresión de la tarea
    @Override
    public String toString() {
        return "Tarea: " + titulo + ", Descripción: " + descripcion +
                ", Asignada a: " + (programador != null ? programador.getNombre() : "Ninguno") +
                ", Finalizada: " + (finalizada ? "Sí" : "No");
    }
}


