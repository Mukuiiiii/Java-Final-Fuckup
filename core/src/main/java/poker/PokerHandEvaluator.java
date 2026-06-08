package poker;

import component.PlayingCard;
import component.PlayingCard.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import unhappyEC.Entity;

public class PokerHandEvaluator {
    public PokerHandValue evaluate(List<Entity> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Poker hand evaluation needs exactly 5 cards.");
        }

        ArrayList<PlayingCard> playingCards = new ArrayList<>();
        for (Entity card : cards) {
            PlayingCard playingCard = card.getComponent(PlayingCard.class);
            if (playingCard == null) {
                throw new IllegalArgumentException("Entity does not have PlayingCard component.");
            }
            playingCards.add(playingCard);
        }

        Map<Integer, Integer> rankCounts = countRanks(playingCards);
        boolean flush = isFlush(playingCards);
        int straightHigh = straightHighCard(rankCounts.keySet());

        if (flush && straightHigh > 0) {
            return new PokerHandValue(PokerHandRank.STRAIGHT_FLUSH, List.of(straightHigh));
        }

        Integer four = rankWithCount(rankCounts, 4);
        if (four != null) {
            return new PokerHandValue(PokerHandRank.FOUR_OF_A_KIND, withKickers(List.of(four), rankCounts, four));
        }

        Integer three = rankWithCount(rankCounts, 3);
        Integer pair = rankWithCount(rankCounts, 2);
        if (three != null && pair != null) {
            return new PokerHandValue(PokerHandRank.FULL_HOUSE, List.of(three, pair));
        }

        if (flush) {
            return new PokerHandValue(PokerHandRank.FLUSH, ranksDescending(rankCounts.keySet()));
        }

        if (straightHigh > 0) {
            return new PokerHandValue(PokerHandRank.STRAIGHT, List.of(straightHigh));
        }

        if (three != null) {
            return new PokerHandValue(PokerHandRank.THREE_OF_A_KIND, withKickers(List.of(three), rankCounts, three));
        }

        List<Integer> pairs = ranksWithCount(rankCounts, 2);
        if (pairs.size() == 2) {
            Collections.sort(pairs, Collections.reverseOrder());
            return new PokerHandValue(PokerHandRank.TWO_PAIR, withKickers(pairs, rankCounts, pairs.get(0), pairs.get(1)));
        }

        if (pairs.size() == 1) {
            return new PokerHandValue(PokerHandRank.ONE_PAIR, withKickers(List.of(pairs.get(0)), rankCounts, pairs.get(0)));
        }

        return new PokerHandValue(PokerHandRank.HIGH_CARD, ranksDescending(rankCounts.keySet()));
    }

    private Map<Integer, Integer> countRanks(List<PlayingCard> cards) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (PlayingCard card : cards) {
            int value = card.getRank().getValue();
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }
        return counts;
    }

    private boolean isFlush(List<PlayingCard> cards) {
        Suit suit = cards.get(0).getSuit();
        for (PlayingCard card : cards) {
            if (card.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    private int straightHighCard(Set<Integer> ranks) {
        if (ranks.size() != 5) {
            return 0;
        }

        HashSet<Integer> normalized = new HashSet<>(ranks);
        if (normalized.containsAll(List.of(14, 5, 4, 3, 2))) {
            return 5;
        }

        List<Integer> sorted = ranksDescending(normalized);
        int high = sorted.get(0);
        for (int i = 1; i < sorted.size(); i++) {
            if (sorted.get(i) != high - i) {
                return 0;
            }
        }
        return high;
    }

    private Integer rankWithCount(Map<Integer, Integer> rankCounts, int count) {
        List<Integer> matches = ranksWithCount(rankCounts, count);
        if (matches.isEmpty()) {
            return null;
        }
        return matches.get(0);
    }

    private List<Integer> ranksWithCount(Map<Integer, Integer> rankCounts, int count) {
        ArrayList<Integer> matches = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : rankCounts.entrySet()) {
            if (entry.getValue() == count) {
                matches.add(entry.getKey());
            }
        }
        matches.sort(Collections.reverseOrder());
        return matches;
    }

    private List<Integer> ranksDescending(Set<Integer> ranks) {
        ArrayList<Integer> sorted = new ArrayList<>(ranks);
        sorted.sort(Collections.reverseOrder());
        return sorted;
    }

    private List<Integer> withKickers(List<Integer> primary, Map<Integer, Integer> rankCounts, Integer... excluded) {
        ArrayList<Integer> result = new ArrayList<>(primary);
        HashSet<Integer> excludedRanks = new HashSet<>(List.of(excluded));

        ArrayList<Integer> kickers = new ArrayList<>();
        for (Integer rank : rankCounts.keySet()) {
            if (!excludedRanks.contains(rank)) {
                kickers.add(rank);
            }
        }
        kickers.sort(Collections.reverseOrder());
        result.addAll(kickers);
        return result;
    }
}
