import lombok.*;
import lombok.extern.java.*;

@Log
@Getter
public class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

}
