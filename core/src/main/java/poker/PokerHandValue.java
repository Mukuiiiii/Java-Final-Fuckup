package poker;

import java.util.ArrayList;
import java.util.List;

public class PokerHandValue implements Comparable<PokerHandValue> {
    private final PokerHandRank rank;
    private final List<Integer> tieBreakers;

    public PokerHandValue(PokerHandRank rank, List<Integer> tieBreakers) {
        this.rank = rank;
        this.tieBreakers = new ArrayList<>(tieBreakers);
    }

    public PokerHandRank getRank() {
        return rank;
    }

    public List<Integer> getTieBreakers() {
        return new ArrayList<>(tieBreakers);
    }

    @Override
    public int compareTo(PokerHandValue other) {
        int rankCompare = Integer.compare(rank.ordinal(), other.rank.ordinal());
        if (rankCompare != 0) {
            return rankCompare;
        }

        int size = Math.min(tieBreakers.size(), other.tieBreakers.size());
        for (int i = 0; i < size; i++) {
            int compare = Integer.compare(tieBreakers.get(i), other.tieBreakers.get(i));
            if (compare != 0) {
                return compare;
            }
        }

        return Integer.compare(tieBreakers.size(), other.tieBreakers.size());
    }
}
