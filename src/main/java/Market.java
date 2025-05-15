import java.util.HashMap;
import java.util.Locale;
import lombok.*;

public class Market {
    private HashMap<Integer, Product> products = new HashMap<>();
    private int productID = 1001;
    private EndUser user;


    public Market(EndUser user){
        this.user = user;
    }


    //Adds product
    public void addProduct(String name, int price, int amount) throws NotAuthorizedException{
        if(user.isEmployee()){
            products.put(productID, new Product(name, price));
            productID += 10;
        } else throw new NotAuthorizedException("Not an Authorized User!");
    }


}
