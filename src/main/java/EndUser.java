import lombok.*;
import lombok.extern.java.Log;

import java.util.LinkedList;
import java.util.Scanner;


@Log
@Getter
public class EndUser {
    private boolean isEmployee = false;
    @Setter Market market;
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

    //Buys product
    public void buy() {
        Integer selectedProductIdx=-1;
        while (true) {
            log.info("Which product do you want to buy?");

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
                            return;
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
        log.warning("Selecting product " + getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getName()+"...");
        Integer amount = -1;
        while(true) {
            log.info("How much do you want to buy?");
            try{
                amount = Integer.parseInt(userInput.nextLine());

                if(market.getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getAmount() < amount){
                    log.warning("Not enough stock!");
                    log.info("1. Buy all available stock\n2. Buy another amount\n3. Quit transaction");
                    int ifTemp = Integer.parseInt(userInput.nextLine());
                    switch (ifTemp){
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
            }
            break;
        }
        log.warning("Buying "+ amount  + " of Product " + getProduct(getFoundProductIDs().get(selectedProductIdx-1)).getName());
        market.buyProduct(getFoundProductIDs().get(selectedProductIdx-1), amount);
    }

    //Searches for product in Market
    public void search(){
        int tries = 0;


        String searchTerm = "";
        while(true) {
            try {
                log.info("Input search: ");
                searchTerm = userInputString();
                break;
            } catch (StringNullException e) {
                log.warning("Search " + e.getMessage());
                tries++;
            }
            if (tries == 10) {
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

    //TODO: Nice to have actual password and login, but not the priority
    public void userLogIn() throws NotAuthorizedException {
        if(isEmployee) throw new NotAuthorizedException("You're already authorized!");
        isEmployee = true;
    }

    public void userLogOff() throws NotAuthorizedException{
        if(!isEmployee) throw new NotAuthorizedException("You need to be logged in to log off!");
        isEmployee = false;
    }


    //Nice to have: Option to cancel transaction during input
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
}