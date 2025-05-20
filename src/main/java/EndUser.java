import lombok.*;
import lombok.extern.java.Log;

import java.util.Scanner;


@Log
@Getter
public class EndUser {
    private boolean isEmployee = false;
    @Setter Market market;
    Scanner userInput = new Scanner(System.in);


    //Buys product
    public void buy(){}

    //Searches for product in Market
    public void search(){
        market.showProduct(userInput.nextInt());
    }

    //TODO
    public void userLogIn() throws NotAuthorizedException {
        if(isEmployee) throw new NotAuthorizedException("You're already authorized!");
        isEmployee = true;
    }
    //TODO
    public void userLogOff() throws NotAuthorizedException{
        if(!isEmployee) throw new NotAuthorizedException("You need to be authorized to log in!");
        isEmployee = false;
    }

    @SuppressWarnings("i know what im doing")
    public void addProduct() throws NotAuthorizedException {
        if (!isEmployee) throw new NotAuthorizedException("You are not authorized! You need to log in to add products");


        String name, description = "";
        double price;
        int amount;


        int tries = 0;
        while (true) {
            try {
                log.info("What is the name of the product?\n");
                name = userInputString();
                log.warning("Name of product: " + name);
                tries = 0;
                break;
            } catch (StringNullException e) {
                log.warning("Name" + e.getMessage());
                tries++;
            }
            if (tries == 10) {
                tooManyAttempts();
                return;
            }
        }
        while (true) {
            try {
                price = userInputPrice();
                tries = 0;
                break;
            } catch (OutOfBoundsException e) {
                log.warning(e.getMessage());
                tries++;
            }
            if (tries == 10) {
                tooManyAttempts();
                return;
            }
        }
        while (true) {
            try {
                amount = userInputAmount();
                tries = 0;
                break;
            } catch (OutOfBoundsException e) {
                log.warning(e.getMessage());
                tries++;
            }
            if (tries == 10) {
                tooManyAttempts();
                return;
            }
        }
        while (tries < 10) {
            try {
                log.info("What is the description of the product?\n");
                description = userInputString();
                log.warning("Description of Product: " + description);
                tries = 0;
                break;
            } catch (StringNullException e) {
                log.warning("Description" + e.getMessage());
                tries++;
            }
            if (tries == 10) {
                tooManyAttempts();
                return;
            }
        }

        market.addProduct(name, price, amount, description);
    }

    public String userInputString() throws StringNullException {
        String userString = userInput.nextLine();

        if(userString.isEmpty()) throw new StringNullException(" cannot be empty!");

        return userString;
    }

    public double userInputPrice() throws OutOfBoundsException {
        log.info("What is the price of the product?\n");
        double tempDouble = Double.parseDouble(userInput.nextLine());

        if(isDoubleOutOfBounds(tempDouble)) throw new OutOfBoundsException("Price cannot be negative!");
        log.warning("Price of product: " + tempDouble);

        return tempDouble;
    }

    public int userInputAmount() throws OutOfBoundsException {
        log.info("What amount of product are you adding?\n");
        int amount = Integer.parseInt(userInput.nextLine());

        if(isIntOutOfBounds(amount)) throw new OutOfBoundsException("Amount cannot be negative!");
        log.warning("Adding amount: " + amount);

        return amount;
    }

    public boolean isDoubleOutOfBounds(double inputDouble){
        return (inputDouble < 0);
    }

    public boolean isIntOutOfBounds(int inputInt){
        return (inputInt < 0);
    }

    public void userStatus(){
        if (isEmployee) log.warning("You are logged in as an Employee");
        else log.warning("You are logged in as an User");
    }

    public void tooManyAttempts(){
        log.warning("Too many attempts!\nQuitting transaction...");
        for (int i = 3; i > 0; i--) {
            try{
                log.warning(i + "...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.warning("I got interrupted!");
            }
        }
    }
}