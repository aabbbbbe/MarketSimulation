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

    public void decrementAmount(int decrementingBy){
        this.amount -= decrementingBy;
    }

    public void incrementAmount(int increasingBy){
        this.amount += increasingBy;
    }

    public void addProductHistory(double buyingPrice, int buyingAmount, double priceOfTransaction, String timeAndDate){
        if(history.size() < 3) {
            history.add(new ProductHistory(buyingPrice, buyingAmount, priceOfTransaction, timeAndDate));
            return;
        }
        history.pop();
        history.add(new ProductHistory(buyingPrice, buyingAmount, priceOfTransaction, timeAndDate));
    }

}
