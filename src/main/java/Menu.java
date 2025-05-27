import lombok.extern.java.Log;

import java.util.Scanner;

@Log
public class Menu {
    private EndUser user;
    Scanner userInput = new Scanner(System.in);

    public Menu(EndUser user){
        this.user = user;
    }

    public void startMenu(){
        while(true) {
            user.userStatus();
            log.info("What do you want to do?\n1. Search for Product\n2. Show all available Products\n3. Add Product\n4. Log into an authorized account\n5. Log off an authorized account\n6. Exit ");

            try {
                int temp = Integer.parseInt(userInput.nextLine());
                //TODO: Figure out User vs. Employee experience
                switch (temp) {
                    case 1:
                        log.warning("User chose 1\nSearching...");
                        user.search();
                        if (user.searchIsSuccessful()) buyMenu();
                        break;
                    case 2:
                        log.warning("User chose 2\nShowing all available products...");
                        user.presentAllAvailableProducts();
                        break;
                    case 3:
                        log.warning("User chose 3\nAdding product...");
                        try {
                            user.addProduct();
                        } catch (NotAuthorizedException e) {
                            log.warning(e.getMessage());
                        }
                        break;
                    case 4:
                        log.warning("User chose 4\nLogging in...");
                        try {
                            user.userLogIn();
                        } catch (NotAuthorizedException e) {
                            log.warning(e.getMessage());
                        }
                        break;
                    case 5:
                        log.warning("User chose 5\nLogging off...");
                        try {
                            user.userLogOff();
                        } catch (NotAuthorizedException e) {
                            log.warning(e.getMessage());
                        }
                        break;
                    case 6:
                        log.warning("User chose 6\nExiting...");
                        countdown();
                        log.warning("Thank you and goodbye!");
                        return;
                    default:
                        log.warning("Not a valid option!");
                        break;
                }
            } catch (NumberFormatException e){
                log.warning("Only numbers are allowed!");
            }
        }
    }

    public void buyMenu(){
        while(true){
            log.info("What do you want to do?\n1. Buy one of the found products\n2. Search again\n3. See product history of last three intervals for one of the found products" +
                    "\n4. Increase stock of a product\n5. Decrease stock of a product\n6. Return to main menu");
            try {
                int temp = Integer.parseInt(userInput.nextLine());
                switch (temp) {
                    case 1:
                        log.warning("User chose 1\nGoing to buy menu...");
                        user.buy();
                        return;
                    case 2:
                        log.warning("User chose 2\nSearching again...");
                        user.search();
                        break;
                    case 3:
                        log.warning("User chose 3\nGoing to history menu...");
                        user.presentPurchasingHistory();
                        break;
                    case 4:
                        log.warning("User chose 4\nGoing to employee menu...");
                        try {
                            user.increaseProductStock();
                        } catch (NotAuthorizedException e) {
                            log.warning(e.getMessage());
                        }
                        break;
                    case 5:
                        log.warning("User chose 5\nGoing to employee menu...");
                        try {
                            user.decreaseProductStock();
                        } catch (NotAuthorizedException e) {
                            log.warning(e.getMessage());
                        }
                        break;
                    case 6:
                        log.warning("User chose 6\nReturning to main menu");
                        return;
                    default:
                        log.warning("Not a valid option!");
                        break;
                }
            } catch (NumberFormatException e){
                log.warning("Only numbers are allowed!");
            }
        }
    }

    private static void countdown() {
        for (int i = 3; i > 0; i--) {
            log.warning(i + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.warning("I got interrupted");
            }
        }
    }
}