import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.*;

public class BuntTest {

    @ParameterizedTest
    @CsvSource({
            "булка, 1.23",
            "bulka, 1.23",
            "БУЛКА, 1.23",
            "бул ка, 1.23",
            "бул1ка, 1.23",
            "булк@, 1.23"
    })
    public void bunNameTest(String name, float price) {
        Bun bun = new Bun(name, price);
        String actual = bun.getName();
        assertEquals(name, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "булка, 1.23",
            "булка, -1.23",
            "булка, 1.2",
            "булка, 1",
            "булка, 0"
    })
    public void bunPriceTest(String name, float price) {
        Bun bun = new Bun(name, price);
        float actual = bun.getPrice();
        assertEquals(price, actual);
    }

}
