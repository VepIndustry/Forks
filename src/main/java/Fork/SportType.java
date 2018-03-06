package Fork;

public enum SportType {
    FOOTBALL, TENNIS, BASKETBALL, HOCKEY;

    public static SportType getRussianType(String name) {
        switch (name.toLowerCase()) {
            case "футбол": return FOOTBALL;
            case "теннис": return TENNIS;
            case "баскетбол": return BASKETBALL;
            case "хоккей": return HOCKEY;
            default: return null;
        }
    }
}
