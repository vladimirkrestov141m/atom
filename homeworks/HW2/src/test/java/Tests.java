import org.junit.Test;
import org.junit.Ignore;
import ru.example.BullsAndCows.Game;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class Tests {
    @Test
    public void testBulls() {
        Game.wordChars = new StringBuilder("tiods");
        Game.typedWordChars = new StringBuilder("teodf");
        assertThat(Game.calculateBulls(), is(equalTo(3)));
    }

    @Test
    public void testCows() {
        Game.wordChars = new StringBuilder("tiods");
        Game.typedWordChars = new StringBuilder("nzpdj");
        assertThat(Game.calculateCows(), is(equalTo(1)));
    }

    @Test
    public void testBulls2() {
        Game.wordChars = new StringBuilder("odfl");
        Game.typedWordChars = new StringBuilder("rohgf");
        assertThat(Game.calculateBulls(), is(equalTo(0)));
    }

    @Test
    public void testCows2() {
        Game.wordChars = new StringBuilder("odfl");
        Game.typedWordChars = new StringBuilder("rohgf");
        assertThat(Game.calculateCows(), is(equalTo(2)));
    }

}
