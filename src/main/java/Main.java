import Bookmaker.BookersPool;
import Bookmaker.FakeBookmaker.FakeGenerator;
import Core.Core;
import Core.SimpleLogicModule;
import Solver.ForkSolver;
import Solver.PrintSolver;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        FakeGenerator bookersGenerator = new FakeGenerator();
        Core core = new Core("testCore" , bookersGenerator.generate(), new SimpleLogicModule(), new PrintSolver());
        core.start();
        core.join();
    }

}
