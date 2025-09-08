import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    @ParameterizedTest
    @CsvSource({
            "SAUCE, тест1, 1.23",
            "SAUCE, тест2, 1.23",
            "FILLING, тест3, 1.23",
            "FILLING, тест4, 1.23"
    })
    public void shouldIngredientType(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        IngredientType actual = ingredient.getType();
        assertEquals(type, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "SAUCE, тест1, 1.23",
            "SAUCE, тест2, 1.23",
            "FILLING, тест3, 1.23",
            "FILLING, тест4, 1.23"
    })
    public void shouldIngredientName(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        String actual = ingredient.getName();
        assertEquals(name, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "SAUCE, тест1, 1.23",
            "SAUCE, тест2, 1.23",
            "FILLING, тест3, 1.23",
            "FILLING, тест4, 1.23"
    })
    public void shouldIngredientPrice(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        float actual = ingredient.getPrice();
        assertEquals(price, actual);
    }

}
