package main.game.helpers;

import main.game.enums.GameResult;
import main.game.models.HandResult;

public class GameResolverHelper {

	public static GameResult calculateGameResult(HandResult playerOneResult, HandResult playerTwoResult) {
		GameResult resultType = compareResultTypes(playerOneResult, playerTwoResult);
		if (resultType.equals(GameResult.DRAW))
			return resultType;
		// Hand Result Type Now Tied - Check Hand Winning Cards For Highest
		GameResult resultWinningHighest = compareHighestWinningCardsRank(playerOneResult, playerTwoResult);
		if (resultWinningHighest.equals(GameResult.DRAW))
			return resultWinningHighest;
		// Check remaining cards - check for draws and throw error
		return compareRemainingCardsHighest(playerOneResult, playerTwoResult);
	}

	private static GameResult compareRemainingCardsHighest(HandResult playerOneResult, HandResult playerTwoResult) {
		int resultTypeComparison = playerOneResult.getType().compareTo(playerTwoResult.getType());
		if (resultTypeComparison == 1)
			return GameResult.PLAYER_ONE_WIN;
		else if (resultTypeComparison == 1)
			return GameResult.PLAYER_TWO_WIN;
		else
			throw new IllegalStateException("A game has resulted in a draw, please debug me.");
	}

	private static GameResult compareResultTypes(HandResult playerOneResult, HandResult playerTwoResult) {
		int resultTypeComparison = playerOneResult.getType().compareTo(playerTwoResult.getType());
		if (resultTypeComparison == 1)
			return GameResult.PLAYER_ONE_WIN;
		else if (resultTypeComparison == 1)
			return GameResult.PLAYER_TWO_WIN;
		else
			return GameResult.DRAW;
	}

	private static GameResult compareHighestWinningCardsRank(HandResult playerOneResult, HandResult playerTwoResult) {
		int resultWinningCardComparison = playerOneResult.getHighestWinningRank()
				.compareTo(playerTwoResult.getHighestWinningRank());
		if (resultWinningCardComparison == 1)
			return GameResult.PLAYER_ONE_WIN;
		else if (resultWinningCardComparison == 1)
			return GameResult.PLAYER_TWO_WIN;
		else
			return GameResult.DRAW;
	}
}
