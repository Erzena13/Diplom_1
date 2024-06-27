import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static junit.framework.TestCase.assertEquals;

public class TestIngredient {
    private Ingredient ingredient;

    @Before
    public void setUp() {
        //Создаем новый ингредиент перед каждым тестом
        ingredient = new Ingredient(IngredientType.FILLING, "Test Ingredient", 50.0f);
    }

    @Test
    public void testGetPrice() {
        //Ожидаемая цена
        float expectedPrice = 50.0f;

        //Получаем цену и проверяем, что она соответствует ожидаемой
        float actualPrice = ingredient.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.0f);
    }

    @Test
    public void testGetName() {
        //Ожидаемое имя ингредиента
        String expectedName = "Test Ingredient";

        //Получаем имя и проверяем, что оно соответствует ожидаемому
        String actualName = ingredient.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetType() {
        //Ожидаемый тип ингредиента
        IngredientType expectedType = IngredientType.FILLING;

        //Получаем тип и проверяем, что он соответствует ожидаемому
        IngredientType actualType = ingredient.getType();
        assertEquals(expectedType, actualType);
    }
}
