import jcombinatorics.Generator;
import jcombinatorics.combinations.Combinations;
import jcombinatorics.permutations.Permutations;
import org.junit.Test;
import java.util.*;

public class CombTest {
    @Test
    public void test1() {
        for (int[] permutation : Permutations.of(2, 3, 7)) {
            System.out.println(Arrays.toString(permutation));
        }
    }

    @Test
    public void test2() {
        int index = 0;
        Generator<int[]> generator = Permutations.permute(6, 3);
        while (index < generator.count()) {
            System.out.println(Arrays.toString(generator.get(index)));
            index++;
        }
    }

    @Test
    public void test3() {

    }
}
