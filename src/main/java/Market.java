//Central logic unit for whole simulation. Handles Product management in all forms

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Market {
    private HashMap<Integer, Product> products = new HashMap<>();
    private int productID = 1001;
    private EndUser user;
    Scanner userInput = new Scanner(System.in);
    private MarketHelper marketHelper = new MarketHelper();

    public Market(EndUser user){
        this.user = user;
    }


    //Adds product
    public void addProduct() throws NotAuthorizedException {

        if(!user.isEmployee()) throw new NotAuthorizedException("You are not authorized! You need to log in to add products");


        log.info("Adding product...");
        int tries = 0;
        while(tries < 10) {
            try {
                String tempString = marketHelper.userInputName();
                break;
            } catch (NameNullException e) {
                log.info("Product name cannot be empty!");
                tries++;
            }
            log.info("Too many attempts!\nQuitting transaction...");
            for (int i = 3; i > 0; i--){
                log.info(i+"...");
            }
        }

    }


}
