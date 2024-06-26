import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestBurger {
    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockIngredient1;
    @Mock
    private Ingredient mockIngredient2;
    @InjectMocks
    private Burger burger;

    @Before
    public void setUp() {
        //Создаем моки
        when(mockBun.getName()).thenReturn("Mock Bun");
        when(mockBun.getPrice()).thenReturn(150.0f);

        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getName()).thenReturn("Mock cutlet");
        when(mockIngredient1.getPrice()).thenReturn(100.0f);

        when(mockIngredient2.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient2.getName()).thenReturn("Mock hot sauce");
        when(mockIngredient2.getPrice()).thenReturn(50.0f);

        //Добавляем ингредиенты в бургер
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
    }

    @Test
    public void testSetBuns() {
        //Создаем новую булочку
        Bun newBun = new Bun("New Bun", 200.0f);
        burger.setBuns(newBun);

        //Проверяем что булочка была успешно создана и стоимость правильная
        float expectedPrice = 2 * newBun.getPrice() + mockIngredient1.getPrice() + mockIngredient2.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0.0f);
    }

    @Test
    public void testAddIngredient() {
        //Создаем новый ингредиент
        Ingredient newIngredient = new Ingredient(IngredientType.SAUCE, "Mock new sauce", 25.0f);

        //Начальной стоимость бургера
        float initialPrice = burger.getPrice();

        //Добавляем новый ингредиент в бургер
        burger.addIngredient(newIngredient);

        //Новая стоимость бургера
        float newPrice = burger.getPrice();

        //Проверем что новая стоимость равна начальная плюс стоимость нового ингредиента
        assertEquals(initialPrice + newIngredient.getPrice(), newPrice, 0.0f);
    }

    @Test
    public void testRemoveIngredient() {
        //Начальной стоимость бургера
        float initialPrice = burger.getPrice();

        //Удаление ингредиента по индексу
        int indexToRemove = 1; // Выбираем индекс для удаления
        burger.removeIngredient(indexToRemove);

        //Новая стоимость бургера
        float newPrice = burger.getPrice();

        //Проверяем что новая стоимость уменьшилась на стоимость удаленного ингредиента
        assertEquals(initialPrice - mockIngredient2.getPrice(), newPrice, 0.0f);

        //Проверяем что ингредиент точно был удален
        assertFalse(burger.getIngredients().contains(mockIngredient2));
    }

    @Test
    public void testMoveIngredient() {
        //Ожидаемая стоимость = цена булочки * 2 + сумма цен всех ингредиентов
        float expectedPrice = 2 * mockBun.getPrice() + mockIngredient1.getPrice() + mockIngredient2.getPrice();

        //Начальной стоимость бургера
        float initialPrice = burger.getPrice();

        //Перемещение ингредиента
        int indexToMove = 1; // Выбираем индекс для перемещения
        int newIndex = 0; // Новая позиция для ингредиента
        burger.moveIngredient(indexToMove, newIndex);

        //Новая стоимость бургера
        float newPrice = burger.getPrice();

        //Проверяем ожидаемую и новую стоимость
        assertEquals(expectedPrice, newPrice, 0.0f);

        //Проверяем что ингредиенты на новых местах
        List<Ingredient> ingredients = burger.getIngredients();
        assertEquals(mockIngredient2, ingredients.get(newIndex));
        assertEquals(mockIngredient1, ingredients.get(newIndex + 1)); //индекс второго ингредиента смещается вправо на одну позицию
    }

    @Test
    public void testGetPrice() {
        //Ожидаемая стоимость = цена булки * 2 + сумма цен всех ингредиентов
        float expectedPrice = 2 * mockBun.getPrice() + mockIngredient1.getPrice() + mockIngredient2.getPrice();

        //Полученная стоимость через метод getPrice()
        float actualPrice = burger.getPrice();

        //Проверяем ожидаемую и новую стоимость
        assertEquals(expectedPrice, actualPrice, 0.0f);
    }

    @Test
    public void testGetReceipt() {
        //Ожидаемый результат чека
        StringBuilder expectedReceipt = new StringBuilder();
        expectedReceipt.append(String.format("(==== %s ====)%n", mockBun.getName()));
        expectedReceipt.append(String.format("= %s %s =%n", mockIngredient1.getType().toString().toLowerCase(), mockIngredient1.getName()));
        expectedReceipt.append(String.format("= %s %s =%n", mockIngredient2.getType().toString().toLowerCase(), mockIngredient2.getName()));
        expectedReceipt.append(String.format("(==== %s ====)%n", mockBun.getName()));
        expectedReceipt.append(String.format("%nPrice: %f%n", burger.getPrice()));

        //Полученный результат через метод getReceipt()
        String actualReceipt = burger.getReceipt();

        //Проверка соответствия ожидаемого и полученного чека
        assertEquals(expectedReceipt.toString(), actualReceipt);
    }
}
