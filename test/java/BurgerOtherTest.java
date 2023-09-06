package practicum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerOtherTest {
    @Mock
    Ingredient ingredientFirst;
    Ingredient ingredientSecond;
    @Mock
    private Bun bun;

    private final Burger burger = new Burger();

    @Test
    public void addIngredientTest() {
        int before = burger.ingredients.size();
        burger.addIngredient(ingredientFirst);
        int after = burger.ingredients.size();

        Assert.assertEquals(before + 1, after);
    }

    @Test
    public void removeIngredientTest() {
        int before = burger.ingredients.size();
        burger.addIngredient(ingredientFirst);
        burger.removeIngredient(0);
        int after = burger.ingredients.size();

        Assert.assertEquals(before, after);
    }

    @Test
    public void moveIngredientTest() {
        ingredientFirst = new Ingredient(IngredientType.FILLING, "something", 100);
        ingredientSecond =  new Ingredient(IngredientType.SAUCE, "something", 200);
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);

        burger.moveIngredient(1,0);
        Assert.assertEquals(ingredientSecond, burger.ingredients.get(0));
    }

    @Test
    public void getReceiptTest() {
            String result = String.format("(==== some bun ====)%n"
            + "= filling something =%n"
            + "(==== some bun ====)%n"
            +"%n"
            +"Price: 300,000000%n");

        burger.addIngredient(ingredientFirst);
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(ingredientFirst.getPrice()).thenReturn(100F);
        Mockito.when(bun.getName()).thenReturn("some bun");
        Mockito.when(ingredientFirst.getName()).thenReturn("something");
        Mockito.when(ingredientFirst.getType()).thenReturn(IngredientType.FILLING);
        Assert.assertEquals(result, burger.getReceipt());

    }
}
