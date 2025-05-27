// TODO: This class got out off hand. Consider refactor/new class
// Note to self: take this as a lesson for not putting everything into one class

import lombok.*;
import lombok.extern.java.Log;

import java.util.LinkedList;
import java.util.Scanner;


@Log
@Setter
@Getter
public class EndUser {
    private boolean isEmployee = false;
    Market market;
    LinkedList<Integer> foundProductIDs;
    Scanner userInput = new Scanner(System.in);


    public void presentAllAvailableProducts(){
        int j = 1;
        for (Integer i = 0; i < market.getProductID(); i++) {
            if(!(market.getProduct(i) == null)) {
                log.warning("\nProduct number " + j + ":\n" + formattedProduct(market.getProduct(i)));
                j++;
            }
        }
    }

    // Takes user input for which product to show product history for. Has the same logic as buy()
    public void presentPurchasingHistory(){
        int selectedProductIdx = userInputSelectedProductIDx("Which product's history do you want to see?");
        if(selectedProductIdx == -1) return;

        log.warning("Getting product history for " + getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getName()+"...");
        if(getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getHistory().isEmpty()){
            log.warning("Product history is empty!");
            return;
        }
        loopThroughFormattedProductHistory(getFoundProductIDs().get(selectedProductIdx-1));

    }

    //Buys product
    //Checks if inputs are allowed (i.e. input != 0, not making product stock go negative etc.) and then calls the associated market methods.
    //TODO: If time left over, figure out a nicer way to call market functions instead of those novels like getProduct(getFoundProductIDs().get(selectedProductIdx-1))
    public void buy() {
        int selectedProductIdx = userInputSelectedProductIDx("Which of the found products do you want to buy?");
        if(selectedProductIdx == -1) return;

        log.warning("Selecting product " + getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getName()+"...");
        int amount;
        while(true) {
            log.info("\nAvailable stock of " + getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getName() +
                    ": "+ market.getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getAmount()+"\nInput amount: ");
            try{
                amount = Integer.parseInt(userInput.nextLine());
                if(market.getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getAmount() < amount || amount <= 0){
                    log.warning("Invalid amount!\nAvailable stock of " + getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getName() +
                            ": "+ market.getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getAmount());
                    log.info("What do you want to do?\n1. Buy all available stock\n2. Buy another amount\n3. Quit transaction");
                    int buyingAmountTemp = Integer.parseInt(userInput.nextLine());
                    switch (buyingAmountTemp){
                        case 1:
                            amount = market.getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getAmount();
                            break;
                        case 2:
                            continue;
                        case 3:
                            return;
                    }
                }
            } catch (NumberFormatException e) {
                log.warning("Only numbers are allowed!");
                continue;
            }
            break;
        }
        log.warning("Buying "+ amount  + " of Product " + getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getName()+"...");
        log.info("Price of transaction is: " +
                market.calculatePrice(getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getPrice(), amount) +
                "\nDo you want to proceed with the transaction? [Y/N]");
        String tempString = userInput.nextLine().toLowerCase();
        switch (tempString.charAt(0)) {
            case 'y':
                market.buyProduct(getFoundProductIDs().get(selectedProductIdx-1), amount);
                break;
            case 'n':
                log.warning("Returning to the main menu...");
                return;
            default:
                log.warning("Not a valid option!");
                break;
        }
    }

    //Searches for product in Market
    public void search(){
        int tries = 0;


        String searchTerm;
        while(true) {
            try {
                log.info("Input search: ");
                searchTerm = userInputString();
                break;
            } catch (StringNullException e) {
                log.warning("Search " + e.getMessage());
                tries++;
            }
            if (tries == 5) {
                tooManyAttempts();
                return;
            }
        }
        market.searchProducts(searchTerm);

        if (!searchIsSuccessful()) {
            log.warning("Your query was not successful!\nDo you want to try again?\n[Y/N]");
            String temp = userInput.nextLine();
            switch (temp.charAt(0)) {
                case 'y':
                    search();
                    break;
                case 'n':
                    log.warning("Returning to main menu...");
                    return;
                default:
                    log.warning("Not a valid option!");
                    break;
            }

        }
        foundProductIDs = market.getFoundProductIDs();
        int i = 1;
        for (Integer foundProductID : foundProductIDs) {
            log.warning("\nProduct number " + i + ":\n" + formattedProduct(market.getProduct(foundProductID)));
            i++;
        }
    }

    //Glorified setIsEmployee
    //TODO: Nice to have actual password and login, but not the priority
    public void userLogIn() throws NotAuthorizedException {
        if(isEmployee) throw new NotAuthorizedException("You're already authorized!");
        isEmployee = true;
    }

    public void userLogOff() throws NotAuthorizedException{
        if(!isEmployee) throw new NotAuthorizedException("You need to be logged in to log off!");
        isEmployee = false;
    }

    // Checks if user inputs are valid before calling market class method.
    // The only actually well done piece of code in this mess, but it was the easiest one to implement
    //TODO: Nice to have: Option to cancel transaction during input
    public void addProduct() throws NotAuthorizedException {
        if (!isEmployee) throw new NotAuthorizedException("You are not authorized! You need to log in to add products");

        String name, description;
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
            if (tries == 5) {
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
            if (tries == 5) {
                tooManyAttempts();
                return;
            }
        }
        while (true) {
            try {
                amount = userInputAmount("\nWhat amount of product are you adding?");
                tries = 0;
                break;
            } catch (OutOfBoundsException e) {
                log.warning(e.getMessage());
                tries++;
            }
            if (tries == 5) {
                tooManyAttempts();
                return;
            }
        }
        while (true) {
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
            if (tries == 5) {
                tooManyAttempts();
                return;
            }
        }

        market.addProduct(name, price, amount, description);
    }

    public void increaseProductStock() throws NotAuthorizedException{
        if(!isEmployee) throw new NotAuthorizedException("You are not authorized!\nYou need to log in to increase the stock of a product!");

        Integer selectedProductIdx = userInputSelectedProductIDx("Which product's stock you want to increase?");
        if (selectedProductIdx == -1) return;
        int amount;
        int tries = 0;
        while (true) {
            try {
                amount = userInputAmount("What amount of Product are you adding?");
                tries = 0;
                break;
            } catch (OutOfBoundsException e) {
                log.warning(e.getMessage());
                tries++;
            }
            if (tries == 5) {
                tooManyAttempts();
                return;
            }
        }
        market.increaseProductStock(market.getFoundProductIDs().get(selectedProductIdx-1), amount);
    }

    public void decreaseProductStock() throws NotAuthorizedException {
        if(!isEmployee) throw new NotAuthorizedException("You are not authorized!\nYou need to log in to decrease the stock of a product!");

        Integer selectedProductIdx = userInputSelectedProductIDx("Which product's stock do you want to decrease?");
        if (selectedProductIdx == -1) return;
        int amount;
        int tries = 0;
        while (true) {
            try {
                amount = userInputAmount("By how much do you want to decrease the stock of the product??");
                tries = 0;
                break;
            } catch (OutOfBoundsException e) {
                log.warning(e.getMessage());
                tries++;
            }
            if (tries == 5) {
                tooManyAttempts();
                return;
            }
        }
        market.decreaseProductStock(market.getFoundProductIDs().get(selectedProductIdx-1), amount);
    }

    //userInput<x> are utility methods. Not the most useful ones or most used ones.
    public String userInputString() throws StringNullException {
        String userString = userInput.nextLine();

        if(userString.isEmpty()) throw new StringNullException(" cannot be empty!");

        return userString;
    }

    // Streamlined function for search menu. User can select between the found products
    public Integer userInputSelectedProductIDx(String searchTerm){
        int selectedProductIdx;
        while (true) {
            log.info(searchTerm);

            int i = 1;
            for (Integer foundProductID : getFoundProductIDs()) {
                log.info(i + ". " + getProduct(foundProductID).getName());
                i++;
            }
            try {
                selectedProductIdx = Integer.parseInt(userInput.nextLine());
                if (!(selectedProductIdx >= 1 && selectedProductIdx <= getFoundProductIDs().size())) {
                    log.warning("Input invalid!");
                    log.info("Do you want to try again? [Y/N]");
                    String tempString = userInput.nextLine().toLowerCase();
                    switch (tempString.charAt(0)) {
                        case 'y':
                            break;
                        case 'n':
                            log.warning("Returning to main menu...");
                            return -1;
                        default:
                            log.warning("Not a valid option!");
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                log.warning("Only numbers are allowed!");
                continue;
            }
            break;
        }
        return selectedProductIdx;
    }

    public double userInputPrice() throws OutOfBoundsException {
        log.info("What is the price of the product?\n");
        double tempDouble = Double.parseDouble(userInput.nextLine());

        if(isDoubleOutOfBounds(tempDouble)) throw new OutOfBoundsException("Price cannot be negative!");
        log.warning("Price of product: " + tempDouble);

        return tempDouble;
    }

    public int userInputAmount(String msg) throws OutOfBoundsException {
        log.info(msg);
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

    // User feedback
    public void userStatus(){
        if (isEmployee) log.warning("You are logged in as an Employee");
        else log.warning("You are logged in as an User");
    }

    // Vanity function for that coolness factor
    // Used before return call in methods with time out function
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

    public String formattedProduct(Product product){
        String formattedSting = "Name: %s || Price: %.2f || Stock: %d\nDescription: %s";
        return String.format(formattedSting, product.getName(), product.getPrice(), product.getAmount(), product.getDescription());
    }

    public boolean searchIsSuccessful(){
        return !market.foundProductIDs.isEmpty();
    }

    public Product getProduct(Integer productID){
        return market.getProduct(productID);
    }

    //This takes the cake for the most ugly method I've ever written for this program, but at this point I just want to get this program working
    // TODO: fix this mess
    public void loopThroughFormattedProductHistory(Integer productID){
        String formatterProductHistory = "Product Name: %s\nPrice at time transaction: %.2f\nNet Amount: %d\nPrice of Transaction: %.2f\nTime of Transaction: %s";
        for (int i = 0; i < market.products.get(productID).getHistory().size(); i++){
            log.warning("\n"+String.format(formatterProductHistory, market.products.get(productID).getName(), market.products.get(productID).getHistory().get(i).getPriceAtTimeOfPurchase(), market.products.get(productID).getHistory().get(i).getAmountBought(),market.products.get(productID).getHistory().get(i).getPriceOfTransaction(),
                    market.products.get(productID).getHistory().get(i).getTimeAndDateOfPurchase()));
        }
    }

}