import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {
    Burger burger = new Burger();
    private static final float PRICE_DELTA = 0.001f;
    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;
    @Mock
    Ingredient ingredient1;
    @Mock
    Ingredient ingredient2;
    @Mock
    Ingredient ingredient3;
    @Mock
    Ingredient sauce;
    @Mock
    Ingredient filling;

    @Test
    public void addIngredientBurgerTest() {
        int indexIngredientBurger = burger.ingredients.size();
        burger.addIngredient(ingredient);
        int newIndex = burger.ingredients.size();
        assertEquals(indexIngredientBurger, (newIndex - 1));
    }
    @Test
    public void removeIngredientBurgerTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        int initialSize = burger.ingredients.size();
        assertEquals(3, initialSize, "Изначально должно быть 3 ингредиента");

        burger.removeIngredient(1);

        assertEquals(2, burger.ingredients.size(), "Должно остаться 2 ингредиента");
        assertEquals(ingredient1, burger.ingredients.get(0), "Первый ингредиент должен остаться");
        assertEquals(ingredient3, burger.ingredients.get(1), "Третий ингредиент должен стать вторым");
        assertFalse(burger.ingredients.contains(ingredient2), "Удаленного ингредиента нет");
    }
    @ParameterizedTest
    @MethodSource("burgerPrices")
    void testGetPriceWithDifferentIngredients(float bunPrice, float saucePrice,
                                              float fillingPrice, float expectedPrice) {
        when(bun.getPrice()).thenReturn(bunPrice);
        when(sauce.getPrice()).thenReturn(saucePrice);
        when(filling.getPrice()).thenReturn(fillingPrice);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(expectedPrice, burger.getPrice(), PRICE_DELTA);
    }
    private static Stream<Arguments> burgerPrices() {
        return Stream.of(
                Arguments.of(100.0f, 200.0f, 200.0f, 600.0f),
                Arguments.of(200.0f, 100.0f, 100.0f, 600.0f),
                Arguments.of(300.0f, 300.0f, 200.0f, 1100.0f),
                Arguments.of(100.0f, 300.0f, 100.0f, 600.0f)
        );
    }
    @Test
    public void getReceiptBurgerTest() {
        burger.setBuns(bun);
        when(bun.getName()).thenReturn("Еда");
        boolean actual = burger.getReceipt().contains("Еда");
        boolean expected = true;
        assertEquals(expected, actual);
    }
    @ParameterizedTest
    @MethodSource("provideBurgerPositionsParams")
    void moveIngredientDifferentPositionsBurgerTest(int fromIndex, int toIndex, String description) {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        Ingredient movedIngredient = burger.ingredients.get(fromIndex);
        burger.moveIngredient(fromIndex, toIndex);

        assertEquals(movedIngredient, burger.ingredients.get(toIndex));
    }
    private static Stream<Arguments> provideBurgerPositionsParams() {
        return Stream.of(
                Arguments.of(0, 1, "Первый ингредиент с позиции 0 на позицию 1"),
                Arguments.of(1, 0, "Второй ингредиент с позиции 1 на позицию 0"),
                Arguments.of(0, 2, "Первый ингредиент с позиции 0 на позицию 2"),
                Arguments.of(2, 0, "Третий ингредиент с позиции 2 на позицию 0")
        );
    }
    @After
    public void clearList() {
        burger.ingredients.clear();
    }
}
