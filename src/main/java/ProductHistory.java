import lombok.*;

@AllArgsConstructor
@Data
public class ProductHistory {
    private double priceAtTimeOfPurchase;
    private int amountBought;
    private double priceOfTransaction;
    private String timeAndDateOfPurchase;
}
