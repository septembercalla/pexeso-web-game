package sk.tuke.kpi.kp.pexeso.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private final List<Card> cards;

    private static final List<String> AVAILABLE_VALUES = List.of(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
            "29", "30", "31", "32", "33", "34", "35", "36"
    );


    public Board(int size) {
        this.cards = new ArrayList<>();
        initializeBoard(size);
    }

    private void initializeBoard(int size) {
        int numberOfPairs = (size * size) / 2;

        if (numberOfPairs > AVAILABLE_VALUES.size()) {
            throw new IllegalArgumentException("nooe");
        }
        List<String> shuffled = new ArrayList<>(AVAILABLE_VALUES);
        Collections.shuffle(shuffled);
        List<String> selectedValues = shuffled.subList(0, numberOfPairs);
//тут же где то логика сломана ты тут лучше делай измени и сделай чтобы оно хоть что то меняло местами тоесть оно типо меняло карточки местами  пусть
        List<String> allValues = new ArrayList<>();
        for (String value : selectedValues) {
            allValues.add(value);
            allValues.add(value);
        }
        Collections.shuffle(allValues);

        for (String value : allValues) {
            cards.add(new Card(value));
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
