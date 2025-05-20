//Central logic unit for whole simulation. Handles Product management in all forms

import java.util.HashMap;
import java.util.Scanner;

import lombok.ToString;
import lombok.extern.java.Log;

@Log

public class Market {
    HashMap<Integer, Product> products = new HashMap<>();
    private Integer productID = 1201;
    private EndUser user;
    Scanner userInput = new Scanner(System.in);



    public Market(EndUser user){
        this.user = user;
        existingProducts();

    }


    //Adds product
    public void addProduct(String name, double price, Integer amount, String description) {
        products.put(productID, new Product(name, price, amount, description));

        productID += 100;
    }

    public void showProduct(Integer key){
        log.warning(products.get(key).toString());
    }

    public void existingProducts(){
        products.put(101, new Product("Mario Kart World", 89, 20, "Crazy fun racing with all your favorite characters to a very reasonable price!"));
        products.put(201, new Product("Augustiner Helles", 1.5, 1000, "Delicious monk adjacent beer from Bavaria "));
        products.put(301, new Product("Hardhat", 20, 32, "Helmet to keep your head safe at the worksite"));
        products.put(401, new Product("The C Programming Language, 2nd Edition", 64.99, 200, "For the times when you want to learn to code the good old way"));
        products.put(501, new Product("Abacus", 19.99, 3123, "A normal abacus, we ordered way too many of them"));
        products.put(601, new Product("Apple lightning charger", 15, 15, "Thank god for the EU"));
        products.put(701, new Product("Windows 7 licence", 49.98, 300, "The last good version of Windows, sadly no security updates since 14.01.2020"));
        products.put(801, new Product("Valve Orange Box", 20, 3, "A memento of a bygone era"));
        products.put(901, new Product("Cow", 499.99, 5, "It's an actual cow. Pick up only"));
        products.put(1001, new Product("SAAB 900", 1799.99, 1, "Pinnacle of Swedish Engineering"));
        products.put(1101, new Product("Lenovo Thinkpad 480s", 200, 14, "It may be 7 years old, but it's still the perfect laptop"));

    }


}
