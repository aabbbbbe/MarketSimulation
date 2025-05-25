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

}