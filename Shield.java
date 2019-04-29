
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Shield extends Circle {

	private Circle shield;
	private int shieldCounter;
	private boolean isShieldOn = false;

	public Shield(Color fill, Color stroke, int width, int radius, int x, int y) {

		this.setFill(fill);
		this.setStroke(stroke);
		this.setStrokeWidth(width);
		this.setRadius(radius);
		this.setLayoutX(x);
		this.setLayoutY(y);
	}

	public boolean isShieldOn() {
		return isShieldOn;
	}

	public void setShieldOn(boolean isShieldOn) {
		this.isShieldOn = isShieldOn;
	}

	public Circle getShield() {
		return shield;
	}

	public void setShield(Circle shield) {
		this.shield = shield;
	}

	public int getShieldCounter() {
		return shieldCounter;
	}

	public void setShieldCounter(int shieldCounter) {
		this.shieldCounter = shieldCounter;
	}

	public void setShield(Player player) {

		this.setShieldOn(true);

		this.translateXProperty().bind(player.getNode().translateXProperty());
		this.translateYProperty().bind(player.getNode().translateYProperty());

	}

	public boolean updateShield() { // here true means we still need to put the shield on player, false mean take it
									// off

		if (this.isShieldOn == true) {

			this.setShieldCounter(this.getShieldCounter() + 1); // adding 1 second to time with shield


			if (this.getShieldCounter() >= 10) {

				this.setShieldOn(false);
				this.setShieldCounter(0);
				return false;

			}

			return true;
		}

		return false;

	}

}
