import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class Movement {

	private boolean isMovementPlayer1Available;
	private boolean isMovementPlayer2Available;

	public void EnemiesMovement(List<GameObject> enemies, boolean easyMode, boolean mediumMode, boolean hardMode,
			boolean crazyMode) {

		Timelines.falling.getKeyFrames().add(new KeyFrame(Duration.millis(10), e -> {

			for (int i = 0; i < enemies.size(); i++) {

				if (easyMode == true) {
					enemies.get(i).setY(enemies.get(i).getY() + 0.7);
				}
				if (mediumMode == true) {
					enemies.get(i).setY(enemies.get(i).getY() + 1.2);
				}
				if (hardMode == true) {
					enemies.get(i).setY(enemies.get(i).getY() + 1.8);
				}
				if (crazyMode == true) {
					enemies.get(i).setY(enemies.get(i).getY() + 5);
				}

			}
		}));

		Timelines.falling.setCycleCount(Animation.INDEFINITE);

		Timelines.falling.play();

	}

	public void pointMovement(List<GameObject> points, boolean easyMode, boolean mediumMode, boolean hardMode,
			boolean crazyMode) {

		Timelines.scoresFalling.getKeyFrames().add(new KeyFrame(Duration.millis(20), e -> {

			for (int i = 0; i < points.size(); i++) {
				if (easyMode == true) {
					points.get(i).setY(points.get(i).getY() + 1);
				}
				if (mediumMode == true) {
					points.get(i).setY(points.get(i).getY() + 2.4);
				}
				if (hardMode == true) {
					points.get(i).setY(points.get(i).getY() + 3.5);
				}
				if (crazyMode == true) {
					points.get(i).setY(points.get(i).getY() + 7);
				}

			}
		}));
		Timelines.scoresFalling.setCycleCount(Animation.INDEFINITE);

		Timelines.scoresFalling.play();

	}

	public void bonusMovement(List<GameObject> bonuses, boolean easyMode, boolean mediumMode, boolean hardMode,
			boolean crazyMode) {

		Timelines.bonusFalling.getKeyFrames().add(new KeyFrame(Duration.millis(20), e -> {

			for (int i = 0; i < bonuses.size(); i++) {
				if (easyMode == true) {
					bonuses.get(i).setY(bonuses.get(i).getY() + 2.2);
				}
				if (mediumMode == true) {
					bonuses.get(i).setY(bonuses.get(i).getY() + 3);
				}
				if (hardMode == true) {
					bonuses.get(i).setY(bonuses.get(i).getY() + 4.5);
				}
				if (crazyMode == true) {
					bonuses.get(i).setY(bonuses.get(i).getY() + 9);
				}
			}

		}));
		Timelines.bonusFalling.setCycleCount(Animation.INDEFINITE);

		Timelines.bonusFalling.play();
	}

	public void shieldPointsMovment(List<GameObject> shieldPoints, boolean easyMode, boolean mediumMode,
			boolean hardMode, boolean crazyMode) {

		Timelines.shieldPointsFalling.getKeyFrames().add(new KeyFrame(Duration.millis(20), e -> {

			for (int i = 0; i < shieldPoints.size(); i++) {
				if (easyMode == true) {
					shieldPoints.get(i).setY(shieldPoints.get(i).getY() + 1);
				}
				if (mediumMode == true) {
					shieldPoints.get(i).setY(shieldPoints.get(i).getY() + 1.6);
				}
				if (hardMode == true) {
					shieldPoints.get(i).setY(shieldPoints.get(i).getY() + 2.5);
				}
				if (crazyMode == true) {
					shieldPoints.get(i).setY(shieldPoints.get(i).getY() + 9);
				}
			}

		}));
		Timelines.shieldPointsFalling.setCycleCount(Animation.INDEFINITE);

		Timelines.shieldPointsFalling.play();
	}

	public void lifePointsMovement(List<GameObject> lifePoints, boolean easyMode, boolean mediumMode, boolean hardMode,
			boolean crazyMode) {

		Timelines.lifePointsFalling.getKeyFrames().add(new KeyFrame(Duration.millis(20), e -> {

			for (int i = 0; i < lifePoints.size(); i++) {
				if (easyMode == true) {
					lifePoints.get(i).setY(lifePoints.get(i).getY() + 3);
				}
				if (mediumMode == true) {
					lifePoints.get(i).setY(lifePoints.get(i).getY() + 5);
				}
				if (hardMode == true) {
					lifePoints.get(i).setY(lifePoints.get(i).getY() + 8);
				}
				if (crazyMode == true) {
					lifePoints.get(i).setY(lifePoints.get(i).getY() + 10);
				}

			}
		}));
		Timelines.lifePointsFalling.setCycleCount(Animation.INDEFINITE);

		Timelines.lifePointsFalling.play();

	}

	
	
	

	public boolean isMovementPlayer1Available() {
		return isMovementPlayer1Available;
	}

	public boolean isMovementPlayer2Available() {
		return isMovementPlayer2Available;
	}

	public void setMovementPlayer1Available(boolean isMovementPlayer1Available) {
		this.isMovementPlayer1Available = isMovementPlayer1Available;
	}

	public void setMovementPlayer2Available(boolean isMovementPlayer2Available) {
		this.isMovementPlayer2Available = isMovementPlayer2Available;
	}

}
