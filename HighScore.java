import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.scene.text.Text;

public class HighScore implements Comparable<HighScore> {

	private String nameOfPlayer;
	private int scoreOfPlayer;

	public HighScore(String nameOfPlayer, int scoreOfPlayer) {

		setNameOfPlayer(nameOfPlayer);
		setScoreOfPlayer(scoreOfPlayer);

	}

	public HighScore() {
	}

	public String getNameOfPlayer() {
		return nameOfPlayer;
	}

	public void setNameOfPlayer(String nameOfPlayer) {
		this.nameOfPlayer = nameOfPlayer;
	}

	public int getScoreOfPlayer() {
		return scoreOfPlayer;
	}

	public void setScoreOfPlayer(int scoreOfPlayer) {
		this.scoreOfPlayer = scoreOfPlayer;
	}

	public static Text[] readHighScoreFromFile(File highScoreFile) {

		Text[] textHighScorePrint = new Text[10];

		try {

			Scanner fileReader = new Scanner(highScoreFile);

			for (int i = 0; i < 9; i++) {

				String stringHighScorePrint = fileReader.nextLine();

				textHighScorePrint[i] = new Text(stringHighScorePrint);

				Main.setShadowText(textHighScorePrint[i], 30, 200, 50);

			}

			fileReader.close();

			return textHighScorePrint;

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return textHighScorePrint;

	}

	public boolean setHighScore(File highScoreFile, String highScorePlayer, Text highScoreName, boolean isScoreHigher) {

		HighScore highScoreResult;
		final int sizeOfHighScoreList = 9;
		ArrayList<HighScore> highScoreArray = new ArrayList<>();

		try {

			Scanner highScoreScanner = new Scanner(highScoreFile);

			while (highScoreScanner.hasNext()) {

				String nameOfPlayer = highScoreScanner.next();

				int scoreOfPlayer = highScoreScanner.nextInt();

				highScoreResult = new HighScore(nameOfPlayer, scoreOfPlayer);

				highScoreArray.add(highScoreResult);

			}

			highScoreScanner.close();

			Collections.sort(highScoreArray);

			HighScore currentScore = new HighScore(highScoreName.getText(), Integer.parseInt(highScorePlayer));

			//

			for (int i = 0; i < sizeOfHighScoreList; i++) {

				if (currentScore.compareTo(highScoreArray.get(i)) < 0) {

					highScoreArray.add(i, currentScore);

					isScoreHigher = true;

					break;

				}
			}

			highScoreScanner.close();
			PrintWriter pw = new PrintWriter(highScoreFile);

			for (int i = 0; i < sizeOfHighScoreList; i++) {
				pw.println(highScoreArray.get(i).toString());
			}

			pw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isScoreHigher;

	}

	@Override
	public int compareTo(HighScore other) {
		if (this.scoreOfPlayer < other.scoreOfPlayer)
			return 1;
		else if (this.scoreOfPlayer > other.scoreOfPlayer)
			return -1;
		else
			return 0;

	}

	public String toString() {

		return String.format("%-40s %-20s", nameOfPlayer, scoreOfPlayer);
	}

}