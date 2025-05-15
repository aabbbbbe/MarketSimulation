import lombok.*;
@Getter
public class EndUser {

    private boolean isEmployee = false;
    
    
    //Buys product
    public void buy(){}


    //Searches for product in Market
    public void search(){}

    //TODO
    public void userLogIn(){
        isEmployee = true;
    }
    //TODO
    public void userLogOff(){
        isEmployee = false;
    }


}
