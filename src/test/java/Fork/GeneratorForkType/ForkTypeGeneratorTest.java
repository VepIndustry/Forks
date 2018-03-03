package Fork.GeneratorForkType;

import org.junit.Test;

import static Fork.BetType.*;
import java.util.*;

import static org.junit.Assert.*;

public class ForkTypeGeneratorTest {

    @Test
    public void test1() {
        ForkTypeGenerator generator = new ForkTypeGenerator();
        Set<ForkPrototype> prototypes = generator.generate(FIRST, DRAW, SECOND, NOT_FIRST, NOT_SECOND);

        Set<ForkPrototype> correctAnswer = new HashSet<>();
        correctAnswer.add(new ForkPrototype(FIRST, NOT_FIRST));
        correctAnswer.add(new ForkPrototype(SECOND, NOT_SECOND));
        correctAnswer.add(new ForkPrototype(FIRST, DRAW, SECOND));
        assertTrue(prototypes.equals(correctAnswer));
    }

}