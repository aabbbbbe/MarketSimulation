import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {

    EndUser user = new EndUser();
    Market market = new Market(user);
    user.setMarket(market);
    Menu menu = new Menu(market, user);

    menu.startMenu();

    }

}
