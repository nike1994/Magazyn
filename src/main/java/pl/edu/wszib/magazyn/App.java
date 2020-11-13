package pl.edu.wszib.magazyn;

import pl.edu.wszib.magazyn.database.DataBase;

public class App {
    public static void main(String[] args) {

        DataBase db = new DataBase();
        System.out.println(db.selectAll().get(0).getQuantity());
        db.add("3039532524879", 20);
        System.out.println(db.selectAll().get(0).getQuantity());
        db.remove("3039532524879", 5);
        System.out.println(db.selectAll().get(0).getQuantity());
    }
}
