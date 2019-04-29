import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TheDailyTip {

	public void setTheDailyTipStrings(ArrayList<String> dailyTip) {

		dailyTip.add("When you feel snoozy...Press 'Q' for a short break");
		dailyTip.add("Try to catch as many yellow circles as you can, Trust me... they worth more..");
		dailyTip.add("Avoid the RED one and try to catch BLUE as many as you can!");
		dailyTip.add("Green balls will PROTECT you from bad... but not for long...");
		dailyTip.add("Life is improtant, and important is PINK!");

	}

	public Text setTheDailyTipPathTarnsition(ArrayList<String> dailyTip) {

		Text dailyTipText = new Text("The Daily TIP : " + dailyTip.get((int) (Math.random() * ((5 - 1) + 1))));
		dailyTipText.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		dailyTipText.setFill(Color.RED);
		PathTransition pt = new PathTransition();
		pt.setPath(new Line(-300, 100, 700, 100));
		pt.setNode(dailyTipText);
		pt.setAutoReverse(true);
		pt.setDuration(Duration.seconds(10));
		pt.setCycleCount(Animation.INDEFINITE);
		pt.play();

		return dailyTipText;

	}

}
