package pl.edu.wszib.magazyn;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.edu.wszib.magazyn.configuration.AppConfiguration;
import pl.edu.wszib.magazyn.gui.GUI;

public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        new AnnotationConfigApplicationContext(AppConfiguration.class).getBean(GUI.class).showMainMenu();
    }
}
