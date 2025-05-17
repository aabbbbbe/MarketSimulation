import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class Menu {
    private Market market;
    private EndUser user;
    Scanner userInput = new Scanner(System.in);

    public Menu(Market market, EndUser user){
        this.market = market;
        this.user = user;
    }

    public void startMenu(){   while(true) {

        log.info("What do you want to do?\n1. Search for Product\n2. Log into an authorized account\n3. Add Product\n4. Exit\n");
        int temp = userInput.nextInt();
        log.error("User chose: " + temp);

        //TODO: Figure out User vs. Employee experience
        switch (temp) {
            case 1:
                user.search();
                break;
            case 2:
                user.userLogIn();
                break;
            case 3:
                try{
                    user.addProduct();
                } catch (NotAuthorizedException e) {
                    log.info(e.getMessage());
                }
                break;
            case 4:
                log.info("Exiting...");
                for (int i = 3; i > 0; i--) {
                    log.info(i + "...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        log.info("I got interrupted");
                    }
                }
                return;
        }
    }

    }
}
