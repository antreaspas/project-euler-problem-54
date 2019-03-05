package main.game.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import main.game.enums.HandResultType;
import main.game.enums.Rank;
import main.game.enums.Suite;
import main.game.models.Card;
import main.game.models.Hand;
import main.game.models.HandResult;

public class HandResultHelper {

	private final Card[] cards;
	private Map<Rank, Integer> mapOfOccurences;
	private Boolean isFlush, isStraight;

	public HandResultHelper(Hand hand) {
		this.cards = hand.getCards();
	}

	public HandResult getHandResult() {
		// If statement hell expected :(
		HandResultType type;
		List<Card> cardsList = Arrays.asList(cards), winningCards, remainingCards;
		generateMapOfRankOccurences();
		if (isStraightFlush()) {
			type = HandResultType.STRAIGHT_FLUSH;
			winningCards = cardsList;
			remainingCards = new ArrayList<>();
		} else if (isFourOfAKind()) {
			type = HandResultType.FOUR_OF_A_KIND;
			Rank winningRank = null;
			for (Entry<Rank, Integer> entrySet : mapOfOccurences.entrySet()) {
				if (entrySet.getValue() == 4)
					winningRank = entrySet.getKey();
			}
			Map<Boolean, List<Card>> winningAndRemaining = cardsList.stream()
					.collect(Collectors.partitioningBy(card -> card.getRank().equals(winningRank)));
			winningCards = winningAndRemaining.get(true);
			remainingCards = winningAndRemaining.get(false);
		}
		else if (isFullHouse()) {
			type = HandResultType.FOUR_OF_A_KIND;
			Rank winningRank = null;
			for (Entry<Rank, Integer> entrySet : mapOfOccurences.entrySet()) {
				if (entrySet.getValue() == 4)
					winningRank = entrySet.getKey();
			}
			Map<Boolean, List<Card>> winningAndRemaining = cardsList.stream()
					.collect(Collectors.partitioningBy(card -> card.getRank().equals(winningRank)));
			winningCards = winningAndRemaining.get(true);
			remainingCards = winningAndRemaining.get(false);
		}
		return new HandResult(type, winningCards, remainingCards);
	}

	private boolean isStraightFlush() {
		return isStraight() && isFlush();
	}

	private boolean isStraight() {
		if (isStraight != null)
			return isStraight;
		isStraight = true;
		Rank previousRank = cards[0].getRank();
		for (int i = 1; i < 5; i++) {
			Rank currentRank = cards[i].getRank();
			if (currentRank.ordinal() - previousRank.ordinal() != 1)
				isStraight = false;
		}
		return isStraight;
	}

	private boolean isFlush() {
		if (isFlush != null)
			return isFlush;
		isFlush = true;
		Suite suite = cards[0].getSuite();
		for (int i = 1; i < 5; i++) {
			Suite currentSuite = cards[i].getSuite();
			if (!currentSuite.equals(suite))
				isFlush = false;
		}
		return isFlush;
	}

	private boolean isFourOfAKind() {
		return generateMapOfRankOccurences().containsValue(4);

	}

	private boolean isThreeOfAKind() {
		return generateMapOfRankOccurences().containsValue(3);
	}

	private boolean isFullHouse() {
		return isThreeOfAKind() && countPairs() == 1;
	}

	private boolean isTwoPairs() {
		return countPairs() == 2;
	}

	private boolean isPair() {
		return countPairs() == 1;
	}

	private int countPairs() {
		return Collections.frequency(generateMapOfRankOccurences().values(), 2);
	}

	private void generateMapOfRankOccurences() {
		if (mapOfOccurences != null)
			return;
		mapOfOccurences = new HashMap<>();
		for (Card card : cards) {
			Rank currentRank = card.getRank();
			if (mapOfOccurences.containsKey(currentRank))
				mapOfOccurences.put(currentRank, mapOfOccurences.get(currentRank) + 1);
			else
				mapOfOccurences.put(currentRank, 1);
		}
	}

}
