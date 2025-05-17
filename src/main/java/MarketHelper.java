//Help methods for the Market class


import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class MarketHelper {
    Scanner userInput = new Scanner(System.in);


    public String userInputName() throws NameNullException {

        log.info("What is the name of the Product?\n");
        String tempName = userInput.next();

        if(tempName.isEmpty()) throw new NameNullException("Name cannot be empty!");

        log.error("Name of Product:" + tempName);
        return tempName;
    }


    public double userInputDouble() throws PriceOutOfBoundsException{
        log.info("What is the price of the product?\n");
        double tempDouble = userInput.nextDouble();

        if(isDoubleInBounds(tempDouble)) throw new PriceOutOfBoundsException("Price cannot be negative!");
        log.error("Price of product: " + tempDouble);

        return tempDouble;
    }
    public boolean isDoubleInBounds(double inputDouble){
        return !(inputDouble < 0);
    }

}
