package Fork;

//TODO добавить характеристики такие как, кто играет, когда, где, вид спорта
//TODO добавить сложный equals для сравнения матчей
/**
 * Содержит информацию о матче, такую как кто играет, когда, где, какой вид спорта и так далле
 */
public class Match {
    /**
     * Название матча
     */
    private String name;

    /**
     * Создаётся матч на основе имени
     * @param name название матча
     */
    public Match(String name) {
        this.name = name;
    }

    /**
     * @return название матча
     */
    public String getName() {
        return name;
    }
}
