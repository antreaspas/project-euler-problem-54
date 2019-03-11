package game.helpers;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import game.enums.HandResultType;
import game.enums.Rank;
import game.enums.Suite;
import game.models.Card;
import game.models.HandResult;
import game.models.data.HandResultCardInfo;

public class HandResultHelper {

	public static Optional<HandResult> getFlushOrStraightResult(Card[] cards) {
		boolean isStraight = true, isFlush = true;
		Card previousCard = cards[0];
		for (int i = 1; i < 5; i++) {
			Rank currentRank = cards[i].getRank();
			Suite currentSuite = cards[i].getSuite();
			if (isStraight && currentRank.ordinal() - previousCard.getRank().ordinal() != 1)
				isStraight = false;
			if (isFlush && !currentSuite.equals(previousCard.getSuite()))
				isFlush = false;
			previousCard = cards[i];
		}

		HandResult handResult = null;
		new HandResultCardInfo(Arrays.asList(cards), Collections.emptyList());
		if (isFlush && isStraight)
			handResult = new HandResult(HandResultType.STRAIGHT_FLUSH,
					new HandResultCardInfo(Arrays.asList(cards), Collections.emptyList()));
		else if (isStraight)
			handResult = new HandResult(HandResultType.STRAIGHT,
					new HandResultCardInfo(Arrays.asList(cards), Collections.emptyList()));
		else if (isFlush)
			handResult = new HandResult(HandResultType.FLUSH,
					new HandResultCardInfo(Arrays.asList(cards), Collections.emptyList()));
		return Optional.ofNullable(handResult);
	}

	public static HandResult getPairOrTwoPairsResult(Card[] cards, Map<Rank, Integer> map) {
		HandResultCardInfo cardResult = splitCardsByFrequency(cards, map, 2);
		if (cardResult.getHandResultCards().size() == 4)
			return new HandResult(HandResultType.TWO_PAIR, cardResult);
		else
			return new HandResult(HandResultType.PAIR, cardResult);
	}

	public static HandResultCardInfo splitCardsByFrequency(Card[] cards, Map<Rank, Integer> map, int frequency) {
		Set<Rank> winningRanks = new HashSet<>();
		for (Entry<Rank, Integer> entrySet : map.entrySet()) {
			if (entrySet.getValue() == frequency)
				winningRanks.add(entrySet.getKey());
		}
		Map<Boolean, List<Card>> winningAndRemaining = Arrays.stream(cards)
				.collect(Collectors.partitioningBy(card -> winningRanks.contains(card.getRank())));
		return new HandResultCardInfo(winningAndRemaining.get(true), winningAndRemaining.get(false));
	}

	public static Map<Rank, Integer> generateMapOfRankOccurences(Card[] cards) {
		Map<Rank, Integer> map = new HashMap<>();
		for (Card card : cards) {
			Rank currentRank = card.getRank();
			if (map.containsKey(currentRank))
				map.put(currentRank, map.get(currentRank) + 1);
			else
				map.put(currentRank, 1);
		}
		return map;
	}

}
