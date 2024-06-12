package one.programa.TabelaFipeExercicio;

import one.programa.TabelaFipeExercicio.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipeExercicioApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TabelaFipeExercicioApplication.class, args);
    }

    public void run(String... args) throws Exception {
        Main main = new Main();
        main.exibeMenu();
    }
}
