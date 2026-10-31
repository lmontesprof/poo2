package lambda;

public class Main {

// Esta expresión es una clase anónima que implementa la interfaz Runnable
    Runnable runnaable= new Runnable() {
        @Override
        public void run() {
            System.out.println("Ejecutando un hilo con Runnable");
        }
};
 Runnable runnableLambda = () -> System.out.println("Ejecutando un hilo con Runnable usando Lambda");

    public static void main(String[] args) {
        Main main = new Main();
        main.runnaable.run();
        main.runnableLambda.run();
    }

}
