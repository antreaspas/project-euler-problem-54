package main.game.models.data;

import java.util.Collections;
import java.util.List;

import main.game.models.Card;

public class HandResultCardInfo {
	private final List<Card> handResultCards;
	private final List<Card> remainingCards;

	public HandResultCardInfo(List<Card> handResultCards, List<Card> remainingCards) {
		this.handResultCards = handResultCards;
		this.remainingCards = remainingCards;
		
		Collections.sort(this.handResultCards, Collections.reverseOrder());
		Collections.sort(this.remainingCards, Collections.reverseOrder());
	}

	public List<Card> getHandResultCards() {
		return handResultCards;
	}

	public List<Card> getRemainingCards() {
		return remainingCards;
	}
}
