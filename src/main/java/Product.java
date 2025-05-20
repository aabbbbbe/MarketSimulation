import lombok.*;
import lombok.extern.java.*;

@Log
@Getter
@AllArgsConstructor
@ToString
public class Product {
    private String name;
    private double price;
    private int amount;
    private String description;


}
