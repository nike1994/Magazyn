package pl.edu.wszib.magazyn.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.magazyn.database.DataBase;
import pl.edu.wszib.magazyn.database.IDataBase;
import pl.edu.wszib.magazyn.model.ProductInstance;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Component
public class GUI implements Igui {

    Scanner scanner = new Scanner(System.in);
    
    @Autowired
    public IDataBase db = new DataBase();

    private void showAllProducts(){
        String tableFormat = "| %-15s | %-7d  | %-13s |%n";
        
        System.out.format("+-----------------+----------+---------------+%n");
        System.out.format("| Name            | Quantity | EAN           |%n");
        System.out.format("+-----------------+----------+---------------+%n");
        
        for(ProductInstance b: this.db.getAll()){
            System.out.format(tableFormat,b.getName(),b.getQuantity(),b.getEAN());
        }

        System.out.format("+-----------------+----------+---------------+%n");
    }

    private int getQuantity(){
        System.out.println("Quantity:");
        return scanner.nextInt();

    }

    private String getEAN(){
        System.out.println("EAN:");
        return scanner.nextLine();
    }
    
    private void increaseQuantity(){
        //dodać obsługe błędów
        //zły ean
        //zła ilość
        db.add(getEAN(),getQuantity());
    }
    
    private void reduceQuantity(){
        //poniżej ilości produktów
        db.remove(getEAN(),getQuantity());
    }

    @Override
    public void showMainMenu() {
        
        System.out.println("1.Show all products");
        System.out.println("2.increase quantity");
        System.out.println("3.reduce quantity");
        System.out.println("4.EXIT");


        String choose = scanner.nextLine();
        //zignorowanie entera
        // zapobiega podwójemu uchwyceniu entera po wpisaniu ilości
        // wyświetlał się wrong number i 2x menu
        while (choose==""){
            choose=scanner.nextLine();
        }

        switch (choose){
            case "1":
                this.showAllProducts();
                this.showMainMenu();
                break;
            case "2":
                this.increaseQuantity();
                this.showMainMenu();
                break;
            case "3":
                this.reduceQuantity();
                this.showMainMenu();
                break;
            case "4":
                System.exit(0);
            case "":
                this.showMainMenu();
            default:

                System.out.println("Wrong number");
                this.showMainMenu();
        }


    }
}
