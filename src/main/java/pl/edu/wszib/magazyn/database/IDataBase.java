package pl.edu.wszib.magazyn.database;

public interface IDataBase {
    boolean add(String EAN, int quantity);
    boolean remove(String EAN, int quantity);
    boolean selectAll();
}
