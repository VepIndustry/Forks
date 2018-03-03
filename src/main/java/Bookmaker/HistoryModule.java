package Bookmaker;

/**
 * Класс который содержит информацию о балансах букмекеров, их включениях и выключениях, а также о ставках.
 * Является синглетоном, который сохраняет информацию в файле history.vp
 * Его используют букмекеры и ForkSolver
 */
public class HistoryModule extends Thread {
    private static HistoryModule instance = new HistoryModule();

    private HistoryModule() {
    }

    public static HistoryModule getInstance() {
        return instance;
    }

    void addBookmaker(Bookmaker bookmaker) {
        //TODO добавить сохранение куда нибудь
        System.out.println("Включён букмекер " + bookmaker);
    }
}
