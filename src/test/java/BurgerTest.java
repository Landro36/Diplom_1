import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class BurgerTest {
    Burger burger = new Burger();
    int indexFirst;
    int indexSecond;

    @Before
    public void addToListIngredients() {
        burger.ingredients.add(new Ingredient(IngredientType.SAUCE, "test1", 100));
        burger.ingredients.add(new Ingredient(IngredientType.SAUCE, "test2", 200));
        burger.ingredients.add(new Ingredient(IngredientType.FILLING, "test3", 300));
        burger.ingredients.add(new Ingredient(IngredientType.FILLING, "test4", 400));
    }

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @Test
    public void addIngredientBurgerTest() {
        int indexIngredientBurger = burger.ingredients.size();
        burger.addIngredient(ingredient);
        int newIndex = burger.ingredients.size();
        assertEquals(indexIngredientBurger, (newIndex - 1));
    }

    @Test
    public void removeIngredientBurgerTest() {
        int index = burger.ingredients.size() - 1;
        burger.removeIngredient(index);
        int newIndex = burger.ingredients.size();
        assertEquals(index, newIndex);
    }

    @Test
    public void moveIngredientBurgerTest() {
        indexFirst = 1;
        indexSecond = 2;
        Ingredient expected = burger.ingredients.get(indexFirst);
        burger.moveIngredient(indexFirst, indexSecond);
        Ingredient actualOne = burger.ingredients.get(indexSecond);
        Ingredient actualTwo = burger.ingredients.get(indexFirst);
        assertEquals(expected, actualOne);
        assertNotEquals(expected, actualTwo);
    }

    @Test
    public void getPriceBurgerTest() {
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(123F);
        float actual = burger.getPrice();
        float expected = 246F;
        assertEquals(expected, actual);
    }

    @Test
    public void getReceiptBurgerTest() {
        burger.setBuns(bun);
        Mockito.when(bun.getName()).thenReturn("Еда");
        boolean actual = burger.getReceipt().contains("Еда");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @After
    public void clearList() {
        burger.ingredients.clear();
    }

}
