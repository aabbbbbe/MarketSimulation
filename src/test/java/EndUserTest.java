// Hard to test this class because it's so reliant on user input and market class

import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

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
        boolean isExceptionTriggered = false;

        try {
            user.userLogOff();
        } catch (NotAuthorizedException e) {
            isExceptionTriggered = true;
        }

        assertTrue(isExceptionTriggered);
    }
    @Test
    public void logInLogInExceptionTest(){
        boolean isExceptionTriggered = false;
        try {
            user.userLogIn();
        } catch (NotAuthorizedException e) {

        }
        try {
            user.userLogIn();
        } catch (NotAuthorizedException e) {
            isExceptionTriggered = true;
        }

        assertTrue(isExceptionTriggered);
    }

    @Test
    public void formattedProductTest(){
        String testString = "Name: Test || Price: 1.00 || Stock: 1\nDescription: TestTestTest";
        Product testProduct = new Product("Test", 1, 1, "TestTestTest");

        assertEquals(testString, user.formattedProduct(testProduct));
    }

    @Test
    public void doubleIsOutOfBoundsTest(){
        assertTrue(user.isDoubleOutOfBounds(-1));
    }

    @Test
    public void doubleIsInBoundsTest(){
        assertFalse(user.isDoubleOutOfBounds(1));
    }

    @Test
    public void intIsOutOfBoundsTest(){
        assertTrue(user.isIntOutOfBounds(-1));
    }

    @Test
    public void intIsInBoundsTest(){
        assertFalse(user.isIntOutOfBounds(1));
    }

    @Test
    public void unauthorizedCannotAddProductTest(){
        boolean isExceptionTriggered = false;

        try {
            user.addProduct();
        } catch (NotAuthorizedException e) {
            isExceptionTriggered = true;
        }
        assertTrue(isExceptionTriggered);
    }
    @Test
    public void unauthorizedCannotIncreaseProductTest(){
        boolean isExceptionTriggered = false;

        try {
            user.increaseProductStock();
        } catch (NotAuthorizedException e) {
            isExceptionTriggered = true;
        }
        assertTrue(isExceptionTriggered);
    }
    @Test
    public void unauthorizedCannotDecreaseProductStoc(){
        boolean isExceptionTriggered = false;

        try {
            user.decreaseProductStock();
        } catch (NotAuthorizedException e) {
            isExceptionTriggered = true;
        }
        assertTrue(isExceptionTriggered);
    }
}