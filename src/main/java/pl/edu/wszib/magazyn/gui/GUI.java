package pl.edu.wszib.magazyn.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.magazyn.database.DataBase;
import pl.edu.wszib.magazyn.database.IDataBase;
import pl.edu.wszib.magazyn.model.ProductInstance;

import java.util.Scanner;

@Component
public class GUI implements Igui {

    Scanner scanner = new Scanner(System.in);
    
    @Autowired
    public IDataBase db = new DataBase();

    private void showAllProducts(){
        String tableFormat = "| %-15s | %-7d  | %-13s |%n ";
        
        System.out.format("+-----------------+----------+--------------+%n");
        System.out.format("| Name            | Quantity | EAN          |%n");
        System.out.format("+-----------------+----------+--------------+%n");
        
        for(ProductInstance b: this.db.getAll()){
            System.out.format(tableFormat,b.getName(),b.getQuantity(),b.getEAN());
        }

        System.out.format("+-----------------+----------+--------------+%n");
    }
    
    private void increaseQuantity(){
        
    }
    
    private void reduceQuantity(){
        
    }

    @Override
    public void showMainMenu() {
        
        System.out.println("1.Show all products");
        System.out.println("2.increase quantity");
        System.out.println("3.reduce quantity");
        System.out.println("4.EXIT");
        

        String choose = scanner.nextLine();
        
        switch (choose){
            case "1":
                this.showAllProducts();
                this.showMainMenu();
                break;
            case "2":
                this.increaseQuantity();
                break;
            case "3":
                this.reduceQuantity();
                break;
            case "4":
                System.exit(0);
            default:
                System.out.println("Wrong number");
                this.showMainMenu();
                break;
        }
    }
}
