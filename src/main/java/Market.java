//Central logic unit for whole simulation. Handles Product management in all forms

import java.util.HashMap;
import java.util.LinkedList;

import lombok.*;
import lombok.extern.java.Log;

@Log
@Getter
public class Market {
    HashMap<Integer, Product> products = new HashMap<>();
    private Integer productID = 1201;
    LinkedList<Integer> foundProductIDs = new LinkedList<>();



    public Market(){
        existingProducts();
    }


    //Adds product
    public void addProduct(String name, double price, Integer amount, String description) {
        products.put(productID, new Product(name, price, amount, description));
        productID += 100;

        log.warning("Added product " + name + " successfully");
    }


    //TODO
    public void buyProduct(Integer key, int buyingAmount) {
        String purchasedProductName = products.get(key).getName();
        double buyingPrice = calculatePrice(products.get(key).getPrice(), buyingAmount);
        if(buyingAmount == products.get(key).getAmount()){
            products.remove(key);
            log.warning("Successfully bought all stock of " + purchasedProductName + "\nProduct " + purchasedProductName +" is not available anymore");
            return;
        }

        products.get(key).decrementAmount(buyingAmount);
        log.warning("Successfully bought " + buyingAmount + " of product " + purchasedProductName + "\nPrice of transaction: " + buyingPrice);


    }

    public void searchProducts(String searchTerm) {
        foundProductIDs.clear();


        for(Integer i = 0; i < productID; i++){
            if(products.get(i) == null) continue;

            if(products.get(i).getName().toLowerCase().contains(searchTerm.toLowerCase()) || products.get(i).getDescription().toLowerCase().contains(searchTerm.toLowerCase())){
                foundProductIDs.add(i);
                log.warning("Found product: " + i);
            }
        }

    }

    //TODO: plan and implement
    public void priceAdjust(){

    }


    public double calculatePrice(double buyingPrice, int amount){
        return  buyingPrice*amount;
    }

    // Can be ignored. It's just random products for testing
    public void existingProducts(){
        products.put(1, new Product("Sternburg Export", 0.59, 100, "Beer. For alcoholics and students alike."));
        products.put(101, new Product("Mario Kart World", 89, 20, "Video game. Crazy fun racing with all your favorite characters to a very reasonable price!"));
        products.put(201, new Product("Augustiner Helles", 1.5, 1000, "Beer. Delicious monk adjacent beer from Bavaria "));
        products.put(301, new Product("Hardhat", 20, 32, "Safety equipment. Helmet to keep your head safe at the worksite"));
        products.put(401, new Product("The C Programming Language, 2nd Edition", 64.99, 200, "Book. For the times when you want to learn to code the good old way"));
        products.put(501, new Product("Abacus", 19.99, 3123, "Calculator. A normal abacus, we ordered way too many of them"));
        products.put(601, new Product("Apple lightning charger", 15, 15, "Phone Charger. Thank god for the EU"));
        products.put(701, new Product("Windows 7 licence", 49.98, 300, "Software licence. The last good version of Windows, sadly no security updates since 14.01.2020"));
        products.put(801, new Product("Valve Orange Box", 20, 3, "Video game. A memento of a bygone era"));
        products.put(901, new Product("Cow", 499.99, 5, "Animal. It's an actual cow. Pick up only"));
        products.put(1001, new Product("SAAB 900", 1799.99, 1, "Car. Pinnacle of Swedish Engineering"));
        products.put(1101, new Product("Lenovo Thinkpad T480s", 200, 14, "Laptop. It may be 7 years old, but it's still the perfect laptop"));
    }

    public Product getProduct(Integer productID){
        return products.get(productID);
    }
}
