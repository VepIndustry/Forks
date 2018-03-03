package Fork;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreRangeTest {

    @Test
    public void test1() {
        Fork.Range range1 = new Fork.Range(0, 4);
        Fork.Range range2 = new Fork.Range(6, 8);
        assertEquals(range1.toString(), "Range{0 - 4}");
        assertEquals(range2.toString(), "Range{6 - 8}");

        Fork.Range unionRange = range1.union(range2);
        assertEquals(unionRange.toString(), "Range{0 - 4, 6 - 8}");
    }

    @Test
    public void test2() {
        Fork.Range range1 = new Fork.Range(0, 3);
        Fork.Range range2 = new Fork.Range(2, 9);
        assertEquals(range1.toString(), "Range{0 - 3}");
        assertEquals(range2.toString(), "Range{2 - 9}");

        Fork.Range unionRange = range1.union(range2);
        assertEquals(unionRange.toString(), "Range{0 - 9}");
    }

    @Test
    public void test3() {
        Fork.Range range1 = new Fork.Range(0, 3);
        Fork.Range range2 = new Fork.Range(3, 9);
        assertEquals(range1.toString(), "Range{0 - 3}");
        assertEquals(range2.toString(), "Range{3 - 9}");

        Fork.Range unionRange = range1.union(range2);
        assertEquals(unionRange.toString(), "Range{0 - 9}");
    }

    @Test
    public void test4() {
        Fork.Range range1 = new Fork.Range(0, 0);
        Fork.Range range2 = new Fork.Range(9, 9);
        assertEquals(range1.toString(), "Range{0 - 0}");
        assertEquals(range2.toString(), "Range{9 - 9}");

        Fork.Range unionRange = range1.union(range2);
        assertEquals(unionRange.toString(), "Range{0 - 0, 9 - 9}");
    }

    @Test
    public void test5() {
        Fork.Range range1 = new Fork.Range(0, 0);
        Fork.Range range2 = new Fork.Range(9, 9);
        Fork.Range range3 = new Fork.Range(0, 9);
        assertEquals(range1.toString(), "Range{0 - 0}");
        assertEquals(range2.toString(), "Range{9 - 9}");
        assertEquals(range3.toString(), "Range{0 - 9}");

        Fork.Range unionRange = range1.union(range2).union(range3);
        assertEquals(unionRange.toString(), "Range{0 - 9}");
    }

}