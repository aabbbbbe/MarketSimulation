import lombok.*;
import lombok.extern.java.*;
import java.util.LinkedList;

@Log
@Setter
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

    // TODO: Plan and implement
    public void addProductHistory(double buyingPrice, int buyingAmount, double priceOfTransaction, String timeAndDate){
        if(history.size() < 4) {
            history.add(new ProductHistory(buyingPrice, buyingAmount, priceOfTransaction, timeAndDate));
            return;
        }
        history.pop();
        history.add(new ProductHistory(buyingPrice, buyingAmount, priceOfTransaction, timeAndDate));
    }

}
