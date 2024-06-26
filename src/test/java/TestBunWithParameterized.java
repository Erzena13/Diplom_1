import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class TestBunWithParameterized {
    private final String name;
    private final float price;
    private Bun bun;
    public TestBunWithParameterized(String name, float price) {
        this.name = name;
        this.price = price;
        this.bun = new Bun(name, price);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black Bun", 100.0f},
                {"white Bun", 200.0f},
                {"red Bun", 300.0f}
        });
    }

    @Test
    public void testBunConstructor() {
        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice(), 0.0);
    }
}
