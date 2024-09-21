import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class Proyecto {

        protected String nombre;
        protected Gestor gestor;
        protected ArrayList<Programador> programadores;
        protected ArrayList<Tarea> tareas;

        public Proyecto(String nombre, Gestor gestor) {
            this.nombre = nombre;
            this.gestor = gestor;
            this.programadores = new ArrayList<>();
            this.tareas = new ArrayList<>();
        }

        public void asignarProgramador(Programador programador) {
            programadores.add(programador);
        }

        public void agregarTarea(Tarea tarea) {

            tareas.add(tarea);
        }

        public ArrayList<Tarea> consultarTareasDeProgramador(Programador programador) {
            ArrayList<Tarea> tareasAsignadas = new ArrayList<>();

            for (Tarea tarea : tareas) {
                // Suponiendo que Tarea tiene un método getProgramador() que retorna el programador asignado
                if (tarea.getProgramador().equals(programador)) {
                    tareasAsignadas.add(tarea);
                }
            }

            return tareasAsignadas;
        }



        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        // Getters y Setters para 'gestor'
        public Gestor getGestor() {
            return gestor;
        }

        public void setGestor(Gestor gestor) {
            this.gestor = gestor;
        }

        // Getters y Setters para 'programadores'
        public ArrayList<Programador> getProgramadores() {
            return programadores;
        }

        public void setProgramadores(ArrayList<Programador> programadores) {
            this.programadores = programadores;
        }

        public ArrayList<Tarea> getTareas() {
            return tareas;
        }

        public void setTareas(ArrayList<Tarea> tareas) {
            this.tareas = tareas;
        }


        @Override
        public String toString() {
            return "Proyecto: " + nombre + ", Gestor: " + gestor.getNombre();
        }
    }

