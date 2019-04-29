

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameObject {

	private Node node;
	private boolean alive = true;
	private boolean soundEffectPlayed;
	private Rectangle life = new Rectangle();
	

	public GameObject(Node node) {
		setNode(node);

	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {

		this.node = node;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void moveRight() {
		node.setTranslateX(node.getTranslateX() + 10);
	}

	public void moveLeft() {
		node.setTranslateX(node.getTranslateX() -10);
	}

	public void setX(double x) {
		node.setTranslateX(x);
	}

	public void setY(double d) {
		node.setTranslateY(d);
	}

	public double getX() {
		return node.getTranslateX();
	}

	public double getY() {
		return node.getTranslateY();
	}

	public boolean isColliding(GameObject other) {

		return this.getNode().getBoundsInParent().intersects(other.getNode().getBoundsInParent());

	}

	public boolean isCollidingShield(Circle other) {
		return this.getNode().getBoundsInParent().intersects(other.getBoundsInParent());

	}

	public boolean isOutOfBounds() {
		if (this.getNode().getTranslateY() > 1000) {
			return true;

		}
		return false;

	}

	public boolean isOutOfRightScreen(GameObject other) {
		if (other.getNode().getTranslateX() > 1220) {
			return true;
		}

		return false;
	}

	public boolean isOutOfLeftScreen(GameObject other) {

		if (other.getNode().getTranslateX() < 0) {
			return true;
		}
		return false;

	}

	public boolean isDead() {
		return !alive;
	}

	public void setLifePlus() {

		if (life.getWidth() < 300) {
			life.setWidth(life.getWidth() + 8);
		}
		if (life.getWidth() > 300) {
			life.setWidth(300);
		}
		;

	}

	public void setLifeMinus() {

		life.setWidth(life.getWidth() - 5);
	}

	public Rectangle getLife() {

		return life;

	}
	
	public boolean isSoundEfectPlayed() {
		return soundEffectPlayed;
	}

	public void setSoundEfectPlayed(boolean soundEfectPlayed) {
		this.soundEffectPlayed = soundEfectPlayed;
	}

	public void setLifeLocation(double x, double y) {

		life.setFill(Color.RED);
		life.setWidth(300);
		life.setHeight(20);

		life.setLayoutX(x);
		life.setLayoutY(y);

	}

}