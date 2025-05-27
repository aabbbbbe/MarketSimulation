import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Log
class EndUserTest {
    EndUser user;
    Market market;

    @BeforeEach
    public void init(){
        user = new EndUser();
        market = new Market();
    }


    @Test
    public void logInLogOffTest(){

        assertFalse(user.isEmployee());
        try {
            user.userLogIn();
        } catch (NotAuthorizedException e) {
            log.warning("You are not authorized");
        }
        assertTrue(user.isEmployee());
        try {
            user.userLogOff();
        } catch (NotAuthorizedException e) {
            log.warning("You are not authorized");
        }
        assertFalse(user.isEmployee());
    }

    @Test
    public void logOffExceptionTest(){
        boolean passed = false;
        try {
            user.userLogOff();
        } catch (NotAuthorizedException e) {
            passed = true;
        }

        assertTrue(passed);
    }
    @Test
    public void logInLogInExceptionTest(){
        boolean passed = false;
        try {
            user.userLogIn();
        } catch (NotAuthorizedException e) {

        }
        try {
            user.userLogIn();
        } catch (NotAuthorizedException e) {
            passed = true;
        }

        assertTrue(passed);
    }

    @Test
    public void formattedProductTest(){
        String testString = "Name: Test || Price: 1.00 || Stock: 1\nDescription: TestTestTest";
        Product testProduct = new Product("Test", 1, 1, "TestTestTest");

        assertEquals(testString, user.formattedProduct(testProduct));
    }

//    @Test
//    public void authorizedProductIncrease(){
//        Product product = new Product("Test", 1, 10, "TestTestTest");
//        market.products.put(1111, product);
//        user.setEmployee(true);
//        try {
//            user.increaseProductStock();
//        } catch (NotAuthorizedException e) {
//            log.warning(e.getMessage());
//        }
//
//
//    }
}