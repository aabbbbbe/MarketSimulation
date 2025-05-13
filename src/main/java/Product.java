import lombok.*;
import lombok.extern.java.*;

@Log
@Getter
public class Product {
    private String name;
    private double price;
    @Setter
    private boolean isSold = false;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }


}
