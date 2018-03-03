package Bookmaker.FakeBookmaker;

import Bookmaker.BookersPool;
import Bookmaker.Properties.Money;
import Bookmaker.Properties.Safety;
import Fork.GeneratorFork.SimpleForkGenerator;

public class FakeGenerator implements Bookmaker.BookPoolGenerator {
    @Override
    public BookersPool generate() {
        BookersPool pool = new BookersPool();
        pool.setForkGenerator(new SimpleForkGenerator());
        try {
            pool.addBookmaker(
                    new FakeBookmaker("fb1", "fb1.com", 0, Safety.A, new Money(100, 0))
            );
            pool.addBookmaker(
                    new FakeBookmaker("fb2", "fb2.com", 0, Safety.A, new Money(100, 0))
            );
            pool.addBookmaker(
                    new FakeBookmaker("fb3", "fb3.com", 0, Safety.A, new Money(100, 0))
            );
        } catch (Exception ignored) {

        }
        return pool;
    }
}
