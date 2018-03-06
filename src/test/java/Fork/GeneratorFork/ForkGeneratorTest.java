package Fork.GeneratorFork;

import Fork.*;
import Fork.GeneratorForkType.ForkPrototype;
import Fork.GeneratorForkType.ForkTypeGenerator;
import org.junit.Test;
import java.util.*;
import static Fork.BetType.*;

public class ForkGeneratorTest {
    @Test
    public void test1() {
        ForkTypeGenerator forkTypeGenerator = new ForkTypeGenerator();
        Set<ForkPrototype> prototypes = forkTypeGenerator.generate(BetType.ALL_TYPES);
        ForkGenerator generator = new ForkGenerator(prototypes);

        Line line1 = new Line(null, null);
        line1.setCoefficient(FIRST, 2);
        line1.setCoefficient(SECOND, 2);
        line1.setCoefficient(DRAW, 2);
        line1.setCoefficient(NOT_FIRST, 2);
        line1.setCoefficient(NOT_SECOND, 2);
        //line1.setBookmaker(new Bookmaker("first"));

        Line line2 = new Line(null, null);
        line2.setCoefficient(FIRST, 2);
        line2.setCoefficient(SECOND, 2);
        line2.setCoefficient(DRAW, 2);
        line2.setCoefficient(NOT_FIRST, 3);
        line2.setCoefficient(NOT_SECOND, 2);
        //line2.setBookmaker(new Bookmaker("second"));

        Line line3 = new Line(null, null);
        line3.setCoefficient(FIRST, 2);
        line3.setCoefficient(SECOND, 2);
        line3.setCoefficient(DRAW, 2);
        line3.setCoefficient(NOT_FIRST, 2);
        line3.setCoefficient(NOT_SECOND, 2);
        //line3.setBookmaker(new Bookmaker("third"));

        Set<Line> lines = new HashSet<>(Arrays.asList(line1, line2));

        for (Fork fork : generator.generate(lines, new Match())) {
            if (fork.calculateUsuallyPercent() > 1.0) {
                System.out.println(fork);
            }
        }
    }
}