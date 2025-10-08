import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {
    @ParameterizedTest
    @MethodSource("ingredientParam")
    public void shouldIngredientType(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        IngredientType actual = ingredient.getType();
        assertEquals(type, actual);
    }
    @ParameterizedTest
    @MethodSource("ingredientParam")
    public void shouldIngredientName(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        String actual = ingredient.getName();
        assertEquals(name, actual);
    }
    @ParameterizedTest
    @MethodSource("ingredientParam")
    public void shouldIngredientPrice(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        float actual = ingredient.getPrice();
        assertEquals(price, actual);
    }
    static Stream<Arguments> ingredientParam() {
        return Stream.of(
                Arguments.of("SAUCE", "тест1", "1.23"),
                Arguments.of("SAUCE", "тест2", "1.23"),
                Arguments.of("FILLING", "тест3", "1.23"),
                Arguments.of("FILLING", "тест4", "1.23")
        );
    }
}
