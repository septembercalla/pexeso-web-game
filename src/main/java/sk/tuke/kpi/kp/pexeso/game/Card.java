//package sk.tuke.kpi.kp.pexeso.game;
//
//public class Card {
//    private final String value;
//    private CardState state;
//
//    public Card(String value) {
//        this.value = value;
//        this.state = CardState.FACE_DOWN;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    public CardState getState() {
//        return state;
//    }
//
//    public void flip() {
//        if (state == CardState.FACE_DOWN) {
//            state = CardState.FACE_UP;
//        } else if (state == CardState.FACE_UP) {
//            state = CardState.FACE_DOWN;
//        }
//    }
//
//    public boolean isMatched(Card other) {
//        return this.value.equals(other.value);
//    }
//
//    public void markMatched() {
//        this.state = CardState.MATCHED;
//    }
//
//}
package sk.tuke.kpi.kp.pexeso.game;
//сделай чтобы 2 случайный не открытых это важно карточки не открыиыз перемегаоист с жтой кнрпкрй
//open all cards with this button no comms
//не работает нужно чтобы не случайные 2 карточки не открытые тоесть я ещё их не открывл сменились местами тоесть любые неоткрытые 2 карточки переместитлись с дру другом
public class Card {
    private final String value;
    private CardState state;

    public Card(String value) {
        this.value = value;
        this.state = CardState.FACE_DOWN;
    }

    public String getValue() {
        return value;
    }

    public CardState getState() {
        return state;
    }

    public void flip() {
        if (state == CardState.FACE_DOWN) {
            state = CardState.FACE_UP;
        } else if (state == CardState.FACE_UP) {
            state = CardState.FACE_DOWN;
        }
    }

    public void markMatched() {
        state = CardState.MATCHED;
    }

    public boolean isMatched(Card other) {
        return this.value.equals(other.value);
    }
}
