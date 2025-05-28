import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {



    @Test
    public void decrementByFiveTest(){
        Product product = new Product("Test", 1, 10, "TestTestTest");

        product.decrementAmount(5);
        assertEquals(5, product.getAmount());
    }

    @Test
    public void incrementByFiveTest(){
        Product product = new Product("Test", 1, 10, "TestTestTest");

        product.incrementAmount(5);
        assertEquals(15, product.getAmount());
    }

    @Test
    public void addFourProductHistoryTest(){
        Product product = new Product("Test", 1, 10, "TestTestTest");
        for(int i = 0; i < 4; i++){
            product.addProductHistory(i, i, i*i, "Test");
        }
        assertEquals(3, product.getHistory().size());
    }

    @Test
    public void addThreeProductHistoryTest(){
        Product product = new Product("Test", 1, 10, "TestTestTest");
        for(int i = 0; i < 3; i++){
            product.addProductHistory(i, i, i*i, "Test");
        }
        assertEquals(3, product.getHistory().size());
    }
}
