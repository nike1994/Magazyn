package pl.edu.wszib.magazyn.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.magazyn.database.DataBase;
import pl.edu.wszib.magazyn.database.IDataBase;
import pl.edu.wszib.magazyn.model.ProductInstance;

import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedMap;

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

    private int getQuantity() throws InputMismatchException{
        System.out.println("Quantity:");
        // stworzono nowy obiekt gdyż użycie zmiennej powodowało podwójne użycie
        // wartości strumienia po obsłudze InputMismatchException
        return  new Scanner(System.in).nextInt();
    }

    private String getEAN(){
        System.out.println("EAN:");
        return scanner.nextLine();
    }

    private String getName(){
        System.out.println("Name:");
        return scanner.nextLine();
    }
    
    private void increaseQuantity(){
        try{
            if (db.addQuantity(getEAN(),getQuantity())){
                System.out.println("Successful completed");
            }else{
                System.out.println("Wrong data");
            }
        }catch(InputMismatchException e){
            System.out.println("Wrong data");
        }
        System.out.println();
    }
    
    private void reduceQuantity(){
        try {
            if (db.removeQuantity(getEAN(),getQuantity())){
                System.out.println("Successful completed");
            }else{
                System.out.println("Wrong data");
            }
        }catch (InputMismatchException e){
            System.out.println("Wrong data");

        }

        System.out.println();
    }

    private void addProduct(){
        try {
            if (db.createProduct(getEAN(),getName(),getQuantity())){
                System.out.println("Successful completed");
            }else{
                System.out.println("Wrong data");
            }
        }catch (InputMismatchException e){
            System.out.println("Wrong data");

        }

        System.out.println();
    }

    private void removeProduct(){
        if (db.removeProduct(getEAN())){
            System.out.println("Successful completed");
        }else {
            System.out.println("Wrong data");
        }
    }

    @Override
    public void showMainMenu() {
        
        System.out.println("1.Show all products");
        System.out.println("2.increase quantity");
        System.out.println("3.reduce quantity");
        System.out.println("4.add new product");
        System.out.println("5.remove product");
        System.out.println("6.EXIT");


        switch (scanner.nextLine()){
            case "1":
                this.showAllProducts();
                break;
            case "2":
                this.increaseQuantity();
                break;
            case "3":
                this.reduceQuantity();
                break;
            case "4":
                this.addProduct();
                break;
            case "5":
                this.removeProduct();
                break;
            case "6":
                System.exit(0);
            default:
                System.out.println("Wrong number");
        }


        this.showMainMenu();


    }
}
