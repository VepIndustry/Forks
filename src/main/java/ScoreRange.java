package Fork;

import java.util.*;


public class ScoreRange {

    private Range firstCommand;
    private Range secondCommand;
    public static final int MAX_VALUE = 10;
    private static final Range FULL_RANGE = new Range(0, MAX_VALUE);

    public ScoreRange(int fromFirst, int toFirst, int fromSecond, int toSecond) {
        if (fromFirst < FULL_RANGE.getMinValue() || fromSecond < FULL_RANGE.getMinValue()) {
            throw new IllegalArgumentException("Start edge less of " + FULL_RANGE.getMinValue());
        }
        toFirst = toFirst > FULL_RANGE.getMaxValue() ? FULL_RANGE.getMaxValue() : toFirst;
        toSecond = toSecond > FULL_RANGE.getMaxValue() ? FULL_RANGE.getMaxValue() : toSecond;
        firstCommand = new Range(fromFirst, toFirst);
        secondCommand = new Range(fromSecond, toSecond);
    }

    private ScoreRange(Range command1, Range command2) {
        firstCommand = command1;
        secondCommand = command2;
    }

    public boolean isFullRange() {
        return firstCommand.equals(FULL_RANGE) && secondCommand.equals(FULL_RANGE);
    }

    public ScoreRange union(ScoreRange scoreRange) {
        return new ScoreRange(firstCommand.union(scoreRange.firstCommand), secondCommand.union(scoreRange.secondCommand));
    }

    @Override
    public String toString() {
        return "ScoreRange{" +
                "firstCommand=" + firstCommand +
                ", secondCommand=" + secondCommand +
                '}';
    }
}

class Range {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Range{");
        for (Point point : points) {
            if (point.isOpen) {
                builder.append(point.position);
            } else {
                builder.append(" - ").append(point.position).append(", ");
            }
        }
        String result = builder.substring(0, builder.length() - 2);
        return result + '}';
    }

    private List<Point> points = new ArrayList<>();

    int getMaxValue() {
        return points.get(points.size() - 1).position;
    }

    int getMinValue() {
        return points.get(0).position;
    }

    Range(int from, int to) {
        points.add(new Point(from, true));
        points.add(new Point(to, false));
    }

    private Range(List<Point> list) {
        points = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return points.equals(range.points);
    }

    @Override
    public int hashCode() {
        return points.hashCode();
    }

    /**
     * @param range другой промежуток который объединяется с текущим
     * @return true если промежутки накладываются друг на друга
     */
    Range union(Range range) {
        List<Point> resultPoints = new ArrayList<>();
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>(points);
        priorityQueue.addAll(range.points);

        while (!priorityQueue.isEmpty()) {
            Point newPoint = priorityQueue.poll();

            Point lastPoint;
            if (resultPoints.size() == 0) {
                resultPoints.add(newPoint);
                continue;
            } else {
                lastPoint = resultPoints.get(resultPoints.size() - 1);
            }
            if (lastPoint.isOpen && newPoint.isOpen) {
                continue;
            } else if (!lastPoint.isOpen && !newPoint.isOpen) {
                resultPoints.set(resultPoints.size() - 1, newPoint);
            } else if (!lastPoint.isOpen && lastPoint.position == newPoint.position) {
                resultPoints.remove(resultPoints.size() - 1);
            } else {
                resultPoints.add(newPoint);
            }
        }
        return new Range(resultPoints);
    }
}

class Point implements Comparable<Point> {
    int position;
    boolean isOpen;

    public Point(int position, boolean isOpen) {
        this.position = position;
        this.isOpen = isOpen;
    }

    @Override
    public String toString() {
        return "" + position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return position == point.position;
    }

    @Override
    public int hashCode() {

        return Objects.hash(position);
    }

    @Override
    public int compareTo(Point o) {
        return Integer.compare(position, o.position);
    }
}