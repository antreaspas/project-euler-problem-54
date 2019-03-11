package game.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import game.enums.HandResultType;
import game.enums.Rank;
import game.helpers.HandResultHelper;
import game.models.data.HandResultCardInfo;

public class Hand {

	private final Card[] cards;

	public Hand(String[] strCards) {
		cards = new Card[5];
		for (int i = 0; i < 5; i++)
			cards[i] = new Card(strCards[i]);
		Arrays.sort(cards);
	}

	public HandResult getHandResult() {
		Optional<HandResult> optionalResult = HandResultHelper.getFlushOrStraightResult(cards);
		if (optionalResult.isPresent())
			return optionalResult.get();

		Map<Rank, Integer> map = HandResultHelper.generateMapOfRankOccurences(cards);

		if (map.containsValue(4))
			return new HandResult(HandResultType.FOUR_OF_A_KIND, HandResultHelper.splitCardsByFrequency(cards, map, 4));
		else if (map.containsValue(3) && map.containsValue(2))
			return new HandResult(HandResultType.FULL_HOUSE,
					new HandResultCardInfo(Arrays.asList(cards), Collections.emptyList()));
		else if (map.containsValue(3))
			return new HandResult(HandResultType.THREE_OF_A_KIND,
					HandResultHelper.splitCardsByFrequency(cards, map, 3));
		else if (map.containsValue(2))
			return HandResultHelper.getPairOrTwoPairsResult(cards, map);
		else
			return new HandResult(HandResultType.HIGH_CARD,
					new HandResultCardInfo(Arrays.asList(cards), Collections.emptyList()));
	}

}
