package main.game.models;

import java.util.Collection;
import java.util.Collections;

import main.game.enums.HandResultType;
import main.game.enums.Rank;

public class HandResult {

	private final HandResultType type;
	private final Collection<Card> handResultCards;
	private final Collection<Card> remainingCards;

	public HandResult(HandResultType type, Collection<Card> handResultCards, Collection<Card> remainingCards) {
		this.type = type;
		this.handResultCards = handResultCards;
		this.remainingCards = remainingCards;
	}

	public HandResultType getType() {
		return type;
	}

	public Rank getHighestWinningRank() {
		return Collections.max(handResultCards).getRank();
	}

	public Collection<Card> getHandResultCards() {
		return handResultCards;
	}

	public Collection<Card> getRemainingCards() {
		return remainingCards;
	}

}
