package game.models;

import game.enums.HandResultType;
import game.models.data.HandResultCardInfo;

public class HandResult {

	private final HandResultType type;
	private final HandResultCardInfo cards;

	public HandResult(HandResultType type, HandResultCardInfo cards) {
		this.type = type;
		this.cards = cards;
	}

	public HandResultType getType() {
		return type;
	}

	public HandResultCardInfo getCards() {
		return cards;
	}

}
