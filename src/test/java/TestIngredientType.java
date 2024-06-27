import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestIngredientType {
    private IngredientType type;
    private String expectedTypeName;

    public TestIngredientType(IngredientType type, String expectedTypeName) {
        this.type = type;
        this.expectedTypeName = expectedTypeName;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.FILLING, "FILLING"},
                {IngredientType.SAUCE, "SAUCE"}
        });
    }

    @Test
    public void testGetType() {
        //Создаем новый ингредиент с заданным типом и проверяем getType()
        Ingredient ingredient = new Ingredient(type, "Test Ingredient", 50.0f);

        //Получаем тип и проверяем, что соответствует ожидаемому
        assertEquals(expectedTypeName, ingredient.getType().toString());
    }
}

