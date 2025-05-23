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
            log.info("What do you want to do?\n1. Search for Product\n2. Log into an authorized account\n3. Add Product\n4. Log off an authorized account\n5. Exit\n");
            int temp = Integer.parseInt(userInput.nextLine());



            //TODO: Figure out User vs. Employee experience
            switch (temp) {
                case 1:
                    log.warning("User chose 1\nSearching...");
                    searchMenu();
                    break;
                case 2:
                    log.warning("User chose 2\nLogging in...");
                    try {
                        user.userLogIn();
                    } catch (NotAuthorizedException e) {
                        log.warning(e.getMessage());
                    }
                    break;
                case 3:
                    log.warning("User chose 3\nAdding product...");
                    try{
                        user.addProduct();
                    } catch (NotAuthorizedException e) {
                        log.warning(e.getMessage());
                    }
                    break;
                case 4:
                    log.warning("User chose 4\nLogging off...");
                    try {
                        user.userLogOff();
                    } catch (NotAuthorizedException e) {
                        log.warning(e.getMessage());
                    }
                    break;
                case 5:
                    log.warning("User chose 5\nExiting...");
                    countdown();
                    log.warning("Thank you and goodbye!");
                    return;
                default:
                    log.warning("Not a valid option!");
                    break;
            }
        }
    }

    public void searchMenu(){
        user.search();


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
