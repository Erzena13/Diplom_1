import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class TestBun {
    private Bun bun;

    @Before
    public void setUp() {
        //Создаем булку перед каждым тестом
        bun = new Bun("Test Bun", 150.0f);
    }

    @Test
    public void testGeBunName() {
        assertEquals("Test Bun", bun.getName());
    }

    @Test
    public void testGetBunPrice() {
        assertEquals(150.0f, bun.getPrice(), 0.0);
    }
}
