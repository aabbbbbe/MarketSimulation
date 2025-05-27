import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MarketTest {
    Market market;


    @BeforeEach
    public void init(){
        market = new Market();
    }

    @Test
    public void calculatePriceStandardTest(){
        assertEquals(((double)2*6), market.calculatePrice(2, 6));
    }


    @Test
    public void searchProductsSuccessfulTest(){
        market.searchProducts("beer");
        assertFalse(market.foundProductIDs.isEmpty());
    }
    @Test
    public void searchProductsFailureTest(){
        market.products.clear();
        market.searchProducts("N/A");
        assertTrue(market.foundProductIDs.isEmpty());
    }

    @Test
    public void addProductTest(){
        market.addProduct("Test", 1, 1, "TestTestTest");
        assertFalse(market.products.get(1201) == null);
    }

    @Test
    public void buyAllStockOfProductTest(){
        Product product = new Product("Test", 1, 1, "TestTestTest");
        market.products.put(1201, product);
        market.buyProduct(1201, 1);
        assertNull(market.products.get(1201));
    }
    @Test
    public void buyPartialStock(){
        Product product = new Product("Test", 1, 10, "TestTestTest");
        market.products.put(1111, product);
        market.buyProduct(1111, 5);
        assertEquals(market.products.get(1111).getAmount(), 5);
    }

    @Test
    public void buyProductHistoryTest(){
        Product product = new Product("Test", 1, 10, "TestTestTest");
        market.products.put(1111, product);
        market.buyProduct(1111, 5);

        assertFalse(market.products.get(1111).getHistory().isEmpty());
    }

    @Test
    public void buyThreeTimesProductTest(){
        Product product = new Product("Test", 1, 10, "TestTestTest");
        market.products.put(1111, product);
        for (int i = 0; i < 3; i++){
            market.buyProduct(1111, 1);
        }

        assertEquals(3, market.products.get(1111).getHistory().size());
    }

}