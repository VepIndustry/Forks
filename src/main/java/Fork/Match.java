package Fork;

//TODO добавить характеристики такие как, кто играет, когда, где, вид спорта
//TODO добавить сложный equals для сравнения матчей

import java.util.Calendar;

/**
 * Содержит информацию о матче, такую как кто играет, когда, где, какой вид спорта и так далле
 */
public class Match {
    /**
     * Время когда начался матч
     */
    private Calendar dateOfBegin;

    /**
     * Название команды 1
     */
    private String team1;

    /**
     * Название команды 2
     */
    private String team2;

    /**
     * Тип матча (футбол баскетболл ...
     */
    private SportType type;

    /**
     * Название матча
     */
    private String name;

    /**
     * Место проведения
     */
    private String place;

    public Calendar getDateOfBegin() {
        return dateOfBegin;
    }

    public void setDateOfBegin(Calendar dateOfBegin) {
        this.dateOfBegin = dateOfBegin;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public SportType getType() {
        return type;
    }

    public void setType(SportType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return название матча
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Match{" +
                "dateOfBegin=" + dateOfBegin.getTime() +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                '}' + '\n';
    }
}

