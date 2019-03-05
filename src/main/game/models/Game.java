package main.game.models;

import main.game.enums.GameResult;
import main.game.helpers.GameResolverHelper;
import main.game.helpers.HandResultHelper;

public class Game {

	private final Hand playerOneHand;
	private final Hand playerTwoHand;

	public Game(String[] strCards) {
		this.playerOneHand = new Hand();
		for (int i = 0; i < 5; i++) {
			this.playerOneHand.addCardFromTextAtIndex(strCards[i], i);
		}

		this.playerTwoHand = new Hand();
		for (int i = 5; i < 10; i++) {
			this.playerTwoHand.addCardFromTextAtIndex(strCards[i], i - 5);
		}
	}

	public boolean isPlayerOneWinner() {
		HandResultHelper helper1 = new HandResultHelper(playerOneHand);
		HandResultHelper helper2 = new HandResultHelper(playerTwoHand);
		return GameResolverHelper.calculateGameResult(helper1.getHandResult(), helper2.getHandResult())
				.equals(GameResult.PLAYER_ONE_WIN);
	}

}
