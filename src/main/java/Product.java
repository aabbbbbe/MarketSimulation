import lombok.*;
import lombok.extern.java.*;
import java.util.LinkedList;

@Log
@Getter
@ToString
public class Product {
    private String name;
    private double price;
    private int amount;
    private String description;
    LinkedList<ProductHistory> history = new LinkedList<>();

    public Product(String name, double price, int amount, String description){
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
    }

    public void decrementAmount(int buyingAmount){
        this.amount -= buyingAmount;
    }

}
