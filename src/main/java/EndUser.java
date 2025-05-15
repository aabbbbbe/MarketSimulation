import lombok.*;
@Getter
public class EndUser {

    private boolean isEmployee = false;
    
    
    //Buys product
    public void buy(){}


    //Searches for product in Market
    public void search(){}


    public void userLogIn(){
        isEmployee = true;
    }

    public void userLogOff(){
        isEmployee = false;
    }


}
