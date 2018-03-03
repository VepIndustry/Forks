package Fork.GeneratorFork;

import Fork.Match;

import java.util.*;

/**
 * Содержит информацию о реальной вилке
 */
public class Fork {
    /**
     * Матч в котором возникла вилка
     */
    private Match match;
    /**
     * Список плеч которые образовывают вилку
     */
    private List<Shoulder> shoulders;
    /**
     * Время когда вилка была актуальна, нужно так как с течением времени вилка может устареть и умереть
     */
    private Calendar creationTime;

    /**
     * @param match матч где возникла вилка
     * @param shoulders плечи из которых состоит вилка
     * @param creationTime время создания вилки, определяется минимальным временем создания линий
     */
    public Fork(Match match, Shoulder[] shoulders, Calendar creationTime) {
        this.match = match;
        this.shoulders = new ArrayList<>(Arrays.asList(shoulders));
        this.creationTime = creationTime;
    }

    /**
     * Создание вилки при условии что затем будут добавлены плечи
     * @param match матч где возникла вилка
     */
    Fork(Match match) {
        this.match = match;
        this.shoulders = new ArrayList<>();
    }

    /**
     * @return время создания вилки
     */
    public Calendar getCreationTime() {
        return creationTime;
    }

    /**
     * Добавление плеча к вилке, указывается время создания плеча
     * @param shoulder плечо которое будет добавленно к вилке
     */
    public void addShoulder(Shoulder shoulder) {
        shoulders.add(shoulder);
        if (this.creationTime.compareTo(shoulder.getCreationTime()) > 0) {
            this.creationTime = shoulder.getCreationTime();
        }
    }

    /**
     * @return матч где появилась вилка
     */
    public Match getMatch() {
        return match;
    }

    /**
     * Возвращает множитель банка при равномерном распределении коэффициентов
     * @return множитель банка
     */
    public double calculateUsuallyPercent() {
        return 1.0 / shoulders.stream().mapToDouble(shoulder -> 1 / shoulder.getCoefficient()).sum();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Fork. Match = ")
                .append(match.getName())
                .append(". Profit = ")
                .append(calculateUsuallyPercent())
                .append('\n');

        shoulders.forEach(shoulder -> builder.append("    ").append(shoulder).append('\n'));
        return builder.toString();
    }

    /**
     * @return список плеч из которых состоит вилка
     */
    public List<Shoulder> getShoulders() {
        return Collections.unmodifiableList(shoulders);
    }
}

