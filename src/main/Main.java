package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import main.game.models.Game;

public class Main {

	public static final String POKER_GAMES_TXT_FILE_PATH = "poker.txt";

	private final Path pokerGamesFilePath;
	private int totalGamesPlayed = 0, gamesPlayerOneWon = 0;

	public Main(String pokerGamesTxtFilePath) {
		this.pokerGamesFilePath = Paths.get(pokerGamesTxtFilePath);
	}

	private void runGames() throws IOException {
		Files.lines(pokerGamesFilePath).forEach(line -> {
			if (totalGamesPlayed == 751)
				System.out.println("here");
			Game game = new Game(line.split(" "));
			if (game.isPlayerOneWinner())
				gamesPlayerOneWon++;
			totalGamesPlayed++;
		});
	}

	public static void main(String[] args) throws IOException {
		Main main = new Main(POKER_GAMES_TXT_FILE_PATH);
		long start = System.nanoTime();
		main.runGames();
		long finish = System.nanoTime();
		long timeElapsed = finish - start;
		final double seconds = ((double) timeElapsed / 1000000000);

		System.out.println(String.format("Player one won %d times \nTotal games played: %d", main.gamesPlayerOneWon,
				main.totalGamesPlayed));
		System.out.println("Runtime: " + new DecimalFormat("#.##########").format(seconds) + " seconds");

	}

}
