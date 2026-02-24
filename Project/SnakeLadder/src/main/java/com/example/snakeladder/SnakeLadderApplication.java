package com.example.snakeladder;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnakeLadderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnakeLadderApplication.class, args);
		Board board = new Board(100);

		// Setup Hazards
		board.addHazard(new Snake(62, 18));
		board.addHazard(new Snake(99, 3));
		board.addHazard(new Ladder(2, 38));
		board.addHazard(new Ladder(50, 91));

		List<Player> players = Arrays.asList(new Player("Amit"), new Player("Suman"));

		SnakeAndLadderGame game = new SnakeAndLadderGame(board, new StandardDice(), players);
		game.start();
	}

}
