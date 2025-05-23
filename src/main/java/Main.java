public class Main {
    public static void main(String[] args) {

    EndUser user = new EndUser();
    Market market = new Market();
    user.setMarket(market);
    Menu menu = new Menu(user);

    menu.startMenu();

    }

}
