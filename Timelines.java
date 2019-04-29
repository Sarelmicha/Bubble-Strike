import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;

public class Timelines {

	public static Timeline makeBalls; // respond to make the balls
	public static Timeline time; // respond to check if there was a hit
	public static Timeline falling; // respond to the enemies falling movement
	public static Timeline scoresFalling;// respond to the score falling movement
	public static Timeline bonusFalling;// respond to the bonus falling movement
	public static Timeline shieldPointsFalling;// respond to the shieldPoint falling movement
	public static Timeline lifePointsFalling;// respond to the lifePoint falling movement
	public static Timeline fadeHighScore;
	public static Timeline shieldsCounter;
	public static AnimationTimer animationTimer;

	public void pauseAllTimeLines() {
		makeBalls.pause();
		time.pause();
		falling.pause();
		scoresFalling.pause();
		bonusFalling.pause();
		shieldPointsFalling.pause();
		lifePointsFalling.pause();
	}

	void playAllTimeLines() {

		makeBalls.play();

		time.play();

		falling.play();

		scoresFalling.play();

		bonusFalling.play();

		shieldPointsFalling.play();

		lifePointsFalling.play();

		shieldsCounter.play();

		fadeHighScore.play();

		animationTimer.start();

	}

	public void stopAllTimeLines() {

		makeBalls.stop();
		time.stop();
		falling.stop();
		scoresFalling.stop();
		bonusFalling.stop();
		lifePointsFalling.stop();
		shieldPointsFalling.stop();
		fadeHighScore.stop();
		animationTimer.stop();
		shieldsCounter.stop();

	}

	public void setAllTimelinesToNull() {

		makeBalls = null;
		time = null;
		falling = null;
		scoresFalling = null;
		bonusFalling = null;
		lifePointsFalling = null;
		shieldPointsFalling = null;
		fadeHighScore = null;
		shieldsCounter = null;
		animationTimer = null;

	}

}
