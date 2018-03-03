package Fork.GeneratorFork;

import Fork.BetType;
import Fork.GeneratorForkType.ForkTypeGenerator;

/**
 * Класс который служит для упрощения работы с генератором вилок. Использует полный набор типов вилок.
 */
public class SimpleForkGenerator extends ForkGenerator {
    public SimpleForkGenerator() {
        super(new ForkTypeGenerator().generate(BetType.ALL_TYPES));
    }
}
