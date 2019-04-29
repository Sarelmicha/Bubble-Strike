
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Game : Bubble Strike © Written By: Sarel Micha
 * 
 **/

public class Main extends Application implements Buttons {

	public static final int SIZE = 1300;

	// Stage, Scene and Main pane
	private Pane mainLayout;
	private Stage stage;
	private Scene mainScene;
	private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

	// Players and there shape

	private Player player;
	private Player player2;
	private Rectangle playerOneRec;
	private Rectangle playerTwoRec;

	// Floor
	private Rectangle floorColor;

	// Bubbles Arrays

	private List<GameObject> enemies;
	private List<GameObject> points;
	private List<GameObject> bonuses;
	private List<GameObject> lifePoints;
	private List<GameObject> shieldPoints;

	// Players Colors
	private Color[] colors1 = { Color.BLUE, Color.PALEVIOLETRED, Color.PURPLE, Color.BLACK };
	private Color[] colors2 = { Color.GREEN, Color.CYAN, Color.ANTIQUEWHITE, Color.LIGHTSTEELBLUE };

	// Color counter

	private int counter1 = 0;
	private int counter2 = 0;

	// Score And Player Name Text

	private Text scoreTextPlayer1;
	private Text scoreTextPlayer2;
	private Text player1Name;
	private Text player2Name;
	private TextField player1NameField;
	private TextField player2NameField;

	// Score

	private int scorePlayer1;
	private int scorePlayer2;

	// Shields

	private Shield shield1 = new Shield(Color.TRANSPARENT, Color.LAWNGREEN, 3, 50, 35, 10);
	private Shield shield2 = new Shield(Color.TRANSPARENT, Color.GREENYELLOW, 3, 50, 35, 10);

	// Booleans

	private boolean twoPlayers = false;
	private boolean easyMode = false;
	private boolean mediumMode = false;
	private boolean hardMode = false;
	private boolean crazyMode = false;
	private boolean desertWasChoose = false;
	private boolean farmWasChoose = false;
	private boolean nightWatchWasChoose = false;
	private boolean inTheMiddleOfGame = false;
	private boolean isShieldOn;
	private boolean isDead = false;
	private boolean isScoreHigher = false;

	// Booleans Property

	private BooleanProperty leftPressed = new SimpleBooleanProperty();
	private BooleanProperty rightPressed = new SimpleBooleanProperty();
	private BooleanProperty aPressed = new SimpleBooleanProperty();
	private BooleanProperty dPressed = new SimpleBooleanProperty();

	// File and Image

	private File highScoreFile = new File("Resources/HighScore/HighScores.txt");
	private ImageView backgrowndView;

	// Connectors between classes
	private ArrayList<String> dailyTip = new ArrayList<>();
	private Movement movement = new Movement();
	private Soundtrack soundtrack = new Soundtrack();
	private TheDailyTip theDailyTip = new TheDailyTip();
	private Timelines myTimeline = new Timelines();
	private HighScore highscores = new HighScore();

	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// set stage
		stage = primaryStage;

		// activate soundtrack

		soundtrack.mainSoundTrackOn();

		// set main layout
		mainLayout = new Pane();
		mainLayout.setPrefSize(SIZE, SIZE);

		// set scene
		mainScene = new Scene(menu());

		// Set cross mouse

		mainScene.setCursor(Cursor.CROSSHAIR);

		stage.setX(primaryScreenBounds.getMinX());
		stage.setY(primaryScreenBounds.getMinY());
		stage.setWidth(primaryScreenBounds.getWidth());
		stage.setHeight(primaryScreenBounds.getHeight());

		stage.setScene(mainScene);

		// Full screen mode

		stage.setFullScreen(true);

		setMenuKeys();

		stage.show();
	}

	// set menu keys
	private void setMenuKeys() {

		twoPlayers = false;
		movement.setMovementPlayer1Available(true);

		// onePlayer button action
		onePlayer.setOnAction(e -> {

			mainLayout.getChildren().clear();
			mainScene.setRoot(setDifficult());

			setDifficultAction();

			stage.getScene().setOnKeyPressed(e2 -> {

				onePlayerKeysPressed(e2);

			});

		});

		twoPlayer.setOnAction(e -> {

			movement.setMovementPlayer2Available(true);

			mainLayout.getChildren().clear();
			twoPlayers = true;

			mainScene.setRoot(setDifficult());

			setDifficultAction();

			stage.getScene().setOnKeyPressed(e2 -> {

				onePlayerKeysPressed(e2);

				twoPlayerKeysPressed(e2);

			});

		});

		stage.getScene().setOnKeyReleased(e2 -> {

			onePlayerKeysReleased(e2);

			if (twoPlayers == true) {

				onePlayerKeysReleased(e2);

				twoPlayerKeysReleased(e2);

			}
		});

		highScores.setOnAction(e -> {

			mainScene.setRoot(highScorePane());

			resume1.setOnAction(e3 -> {

				mainLayout.getChildren().clear();

				mainScene.setRoot(menu());

			});
		});

		exit.setOnAction(e4 -> {
			System.exit(0);

		});

		Timelines.animationTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {

				playerOneMovementHandle();

				playerTwoMovementHandle();

			}

		};

		Timelines.animationTimer.start();
	}

	private void playerTwoMovementHandle() {

		if (aPressed.get()) {

			if (movement.isMovementPlayer2Available() == true) {
				player2.moveLeft();
				if (player2.isOutOfLeftScreen(player2)) {

					player2.setX(0);
				}

			}

		}

		if (dPressed.get()) {

			if (movement.isMovementPlayer2Available() == true) {
				player2.moveRight();

				if (player2.isOutOfRightScreen(player2)) {

					player2.setX(1220);

				}

			}

		}

	}

	private void playerOneMovementHandle() {

		final int LEFT_BOUNDRY = 0;
		final int RIGHT_BOUNDRY = 1220;

		if (leftPressed.get()) {

			if (movement.isMovementPlayer1Available() == true) {
				player.moveLeft();
				if (player.isOutOfLeftScreen(player)) {
					player.setX(LEFT_BOUNDRY);
				}
			}

		}

		if (rightPressed.get()) {

			if (movement.isMovementPlayer1Available() == true) {
				player.moveRight();
				if (player.isOutOfRightScreen(player)) {

					player.setX(RIGHT_BOUNDRY);
				}
			}

		}

	}

	private void twoPlayerKeysReleased(KeyEvent e2) {
		switch (e2.getCode()) {

		case A:

			aPressed.set(false);

			break;

		case D:

			dPressed.set(false);

			break;
		default:

			break;

		}

	}

	private void onePlayerKeysReleased(KeyEvent e2) {

		switch (e2.getCode()) {
		case LEFT:

			leftPressed.set(false);

			break;

		case RIGHT:

			rightPressed.set(false);
			break;

		default:

			break;

		}

	}

	private void onePlayerKeysPressed(KeyEvent e2) {

		switch (e2.getCode()) {

		case LEFT:

			leftPressed.set(true);

			break;

		case RIGHT:

			rightPressed.set(true);

			break;

		case Q:
			movement.setMovementPlayer1Available(false);
			myTimeline.pauseAllTimeLines();
			setMenuChooseButtons();

			break;

		default:
			break;

		}

	}

	private void twoPlayerKeysPressed(KeyEvent e2) {

		switch (e2.getCode()) {

		case A:

			aPressed.set(true);
			break;
		case D:

			dPressed.set(true);

			break;

		case Q:

			movement.setMovementPlayer2Available(false);
			myTimeline.pauseAllTimeLines();
			setMenuChooseButtons();

			break;

		default:

			break;

		}
	}

	private Parent highScorePane() {

		mainLayout.getChildren().clear();

		// set resume picture
		Image resumeBack1 = new Image("file:Resources\\Buttons\\left.gif");
		ImageView resumeBackView1 = new ImageView(resumeBack1);

		// set resume and lets go buttons

		resume1.setLayoutX(20);
		resume1.setLayoutY(20);
		resume1.setGraphic(resumeBackView1);

		Text[] textHighScorePrint = HighScore.readHighScoreFromFile(highScoreFile);

		Text playersTitle = new Text("Player");
		Text scoreTitle = new Text("Score");

		setShadowText(playersTitle, 70, 350, 100);
		setShadowText(scoreTitle, 70, 740, 100);

		for (int i = 0; i < 9; i++) {

			textHighScorePrint[i]
					.setText(("                     " + (i + 1) + " : " + textHighScorePrint[i].getText()));
			textHighScorePrint[i].setX(10);
			textHighScorePrint[i].setY(120 + i * 50);
			mainLayout.getChildren().add(textHighScorePrint[i]);

		}

		mainLayout.getChildren().addAll(resume1, playersTitle, scoreTitle);

		return mainLayout;

	}

	static Text setShadowText(Text unShadowText, int sizeOfText, double layoutX, double layoutY) {

		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

		unShadowText.setEffect(ds); // apply shadow effect
		unShadowText.setCache(true); // for faster rendering
		unShadowText.setFill(Color.RED);
		unShadowText.setLayoutX(layoutX);
		unShadowText.setLayoutY(layoutY);

		unShadowText.setFont(Font.font(null, FontWeight.BOLD, sizeOfText));

		return unShadowText;
	}

	private void setDifficultAction() {
		easy.setOnAction(e -> {
			easyMode = true;
			mainLayout.getChildren().clear();
			mainScene.setRoot(setBackgrownd());

		});
		medium.setOnAction(e -> {
			mediumMode = true;
			mainLayout.getChildren().clear();
			mainScene.setRoot(setBackgrownd());

		});
		hard.setOnAction(e -> {
			hardMode = true;
			mainLayout.getChildren().clear();
			mainScene.setRoot(setBackgrownd());

		});
		crazy.setOnAction(e -> {
			crazyMode = true;
			mainLayout.getChildren().clear();
			mainScene.setRoot(setBackgrownd());

		});

		resume1.setOnAction(e -> {

			mainLayout.getChildren().clear();
			twoPlayers = false;
			mainScene.setRoot(menu());
			setMenuKeys();
		});
		setBackgrowndAction();
	}

	private Parent setBackgrownd() {

		mainLayout.getChildren().clear();

		Text whereToPlay = new Text("Choose your world...");

		Image resumeBack3 = new Image("file:Resources\\Buttons\\left.gif");
		ImageView resumeBackView3 = new ImageView(resumeBack3);

		resume3.setGraphic(resumeBackView3);

		resume3.setLayoutX(20);
		resume3.setLayoutY(20);

		setShadowText(whereToPlay, 50, 430, 300);

		desert.setLayoutX(300);
		desert.setLayoutY(400);
		farm.setLayoutX(530);
		farm.setLayoutY(400);
		nightWatch.setLayoutX(760);
		nightWatch.setLayoutY(400);

		desert.setPrefWidth(200);
		farm.setPrefWidth(200);
		desert.setPrefHeight(60);
		farm.setPrefHeight(60);
		nightWatch.setPrefWidth(200);
		nightWatch.setPrefHeight(60);

		desert.setStyle("-fx-font-weight: bold;");
		farm.setStyle("-fx-font-weight: bold;");
		nightWatch.setStyle("-fx-font-weight: bold;");

		mainLayout.getChildren().addAll(whereToPlay, desert, farm, nightWatch, resume3);

		return mainLayout;
	}

	private void setBackgrowndAction() {

		desert.setOnAction(e -> {
			desertWasChoose = true;
			mainScene.setRoot(chooseNameAndColor());
			setButtonsControl();

		});
		farm.setOnAction(e -> {
			farmWasChoose = true;
			mainScene.setRoot(chooseNameAndColor());
			setButtonsControl();
		});
		nightWatch.setOnAction(e -> {
			nightWatchWasChoose = true;
			mainScene.setRoot(chooseNameAndColor());
			setButtonsControl();
		});

		resume3.setOnAction(e -> {

			mainLayout.getChildren().clear();
			easyMode = false;
			mediumMode = false;
			hardMode = false;
			crazyMode = false;
			mainScene.setRoot(setDifficult());

		});

	}

	// choose name and color
	private Parent chooseNameAndColor() {

		mainLayout.getChildren().clear();
		// set separator
		Line line = new Line(650, 0, 650, 1000);
		// line.setStrokeWidth(10);

		// set left arrow picture
		Image leftArrow = new Image("file:Resources\\Buttons\\left.gif");
		ImageView leftArrowView = new ImageView(leftArrow);

		// set right arrow picture
		Image rightArrow = new Image("file:Resources\\Buttons\\right.gif");
		ImageView rightArrowView = new ImageView(rightArrow);

		// set right and left buttons

		right1.setGraphic(rightArrowView);

		left1.setGraphic(leftArrowView);

		// set resume picture
		Image resumeBack2 = new Image("file:Resources\\Buttons\\left.gif");
		ImageView resumeBackView2 = new ImageView(resumeBack2);

		// set resume and lets go buttons

		resume2.setGraphic(resumeBackView2);

		// set buttons style
		right1.setStyle("-fx-font-weight: bold;");
		left1.setStyle("-fx-font-weight: bold;");
		resume2.setStyle("-fx-font-weight: bold;");
		letsGo.setStyle("-fx-font-weight: bold;");

		// set player1 text
		player1Name = new Text();
		player1Name.setStroke(Color.BLACK);
		player1NameField = new TextField();
		player1NameField.setPromptText("Player1");
		player1Name.setFont(Font.font(50));

		// set player1 block
		playerOneRec = new Rectangle(70, 20, colors1[counter1]);

		// set player2
		if (twoPlayers == true) {

			// set player2 right and left buttons

			// set arrow images
			Image leftArrow2 = new Image("file:Resources\\Buttons\\left.gif");
			ImageView leftArrowView2 = new ImageView(leftArrow2);
			left2.setGraphic(leftArrowView2);
			Image rightArrow2 = new Image("file:Resources\\Buttons\\right.gif");
			ImageView rightArrowView2 = new ImageView(rightArrow2);
			right2.setGraphic(rightArrowView2);
			right2.setStyle("-fx-font-weight: bold;");
			left2.setStyle("-fx-font-weight: bold;");

			// set player2 text
			player2Name = new Text("Player2");
			player2NameField = new TextField();
			player2Name.setStroke(Color.BLACK);
			player2NameField.setPromptText("Player2");
			player2Name.setFont(Font.font(50));

			// set player2 block
			playerTwoRec = new Rectangle(70, 20, colors2[counter2]);
		}

		setPlayersName();
		setObjectsOnLayout();

		// set the main layout
		if (twoPlayers == true) {
			mainLayout.getChildren().addAll(right1, left1, right2, left2, player1Name, player2Name, player1NameField,
					player2NameField, playerOneRec, playerTwoRec, line, letsGo, resume2);
		} else {
			mainLayout.getChildren().addAll(right1, left1, player1Name, player1NameField, playerOneRec, letsGo,
					resume2);
		}

		return mainLayout;
	}

	// set players names
	private void setPlayersName() {

		final int MAX = 7;

		textBindAndHandle(player1Name, player1NameField, MAX);

		if (twoPlayers == true) {

			textBindAndHandle(player2Name, player2NameField, MAX);

		}

	}

	private void textBindAndHandle(Text playerName, TextField playerNameField, int MAX) {

		playerName.textProperty().bind(playerNameField.textProperty());
		playerNameField.textProperty().addListener(e -> {

			if (playerNameField.getText().length() > MAX) {
				String shortName = playerNameField.getText().substring(0, MAX);
				playerNameField.textProperty().set(shortName);

			}

		});

	}

	private void setButtonsControl() {

		right1.setOnAction(e -> {
			counter1++;
			if (counter1 > colors1.length - 1) {
				counter1 = 0;
			}
			playerOneRec.setFill(colors1[counter1]);

		});

		left1.setOnAction(e -> {
			counter1--;
			if (counter1 < 0) {
				counter1 = colors1.length - 1;
			}
			playerOneRec.setFill(colors1[counter1]);
		});

		if (twoPlayers == true) {
			right2.setOnAction(e -> {
				counter2++;
				if (counter2 > colors2.length - 1) {
					counter2 = 0;
				}
				playerTwoRec.setFill(colors2[counter2]);
			});

			left2.setOnAction(e -> {
				counter2--;
				if (counter2 < 0) {
					counter2 = colors2.length - 1;
				}
				playerTwoRec.setFill(colors2[counter2]);
			});
		}

		letsGo.setOnAction(e -> {

			Timelines.makeBalls = new Timeline(); // respond to make the balls
			Timelines.time = new Timeline(); // respond to check if there was a hit
			Timelines.falling = new Timeline(); // respond to the enemies falling movement
			Timelines.scoresFalling = new Timeline();// respond to the score falling movement
			Timelines.bonusFalling = new Timeline();// respond to the bonus falling movement
			Timelines.shieldPointsFalling = new Timeline();// respond to the shieldPoint falling movement
			Timelines.lifePointsFalling = new Timeline();// respond to the lifePoint falling movement
			Timelines.fadeHighScore = new Timeline(); // respond to make highScore flickering
			Timelines.shieldsCounter = new Timeline(); // respond to count shield time

			enemies = new ArrayList<>();
			points = new ArrayList<>();
			bonuses = new ArrayList<>();
			lifePoints = new ArrayList<>();
			shieldPoints = new ArrayList<>();

			inTheMiddleOfGame = true;
			mainLayout.getChildren().clear();
			mainScene.setRoot(createContent());

			Timelines.makeBalls.getKeyFrames().add(new KeyFrame(Duration.millis(1000), e2 -> {
				makeBall();

			}));

			Timelines.makeBalls.setCycleCount(Animation.INDEFINITE);

			Timelines.makeBalls.play();

		});

		resume2.setOnAction(e -> {

			mainLayout.getChildren().clear();
			desertWasChoose = false;
			farmWasChoose = false;
			nightWatchWasChoose = false;
			mainScene.setRoot(setBackgrownd());

		});

	}

	// set nodes in layout
	private void setObjectsOnLayout() {

		resume2.setLayoutX(20);
		resume2.setLayoutY(20);

		if (twoPlayers == true) {

			right1.setLayoutX(1030);
			right1.setLayoutY(300);

			left1.setLayoutX(820);
			left1.setLayoutY(300);

			letsGo.setLayoutX(520);
			letsGo.setLayoutY(500);

			letsGo.setPrefWidth(280);
			letsGo.setPrefHeight(50);

			playerOneRec.setLayoutX(900);
			playerOneRec.setLayoutY(303);

			player1Name.setLayoutX(880);
			player1Name.setLayoutY(200);

			player1NameField.setLayoutX(864);
			player1NameField.setLayoutY(392);

			right2.setLayoutX(410);
			right2.setLayoutY(300);

			left2.setLayoutX(200);
			left2.setLayoutY(300);

			playerTwoRec.setLayoutX(280);
			playerTwoRec.setTranslateY(303);

			player2Name.setLayoutX(260);
			player2Name.setLayoutY(200);

			player2NameField.setLayoutX(244);
			player2NameField.setLayoutY(392);

		} else {
			right1.setLayoutX(800);
			right1.setLayoutY(300);

			left1.setLayoutX(500);
			left1.setLayoutY(300);

			letsGo.setLayoutX(520);
			letsGo.setLayoutY(500);

			letsGo.setPrefWidth(280);
			letsGo.setPrefHeight(50);

			playerOneRec.setLayoutX(620);
			playerOneRec.setLayoutY(303);

			player1Name.setLayoutX(600);
			player1Name.setLayoutY(200);

			player1NameField.setLayoutX(580);
			player1NameField.setLayoutY(392);
		}
	}

	// makeBall
	private void makeBall() {

		if (Math.random() > 0.20) {
			Enemy enemy = new Enemy();
			addEnemy(enemy, Math.random() * 1300, -5);

		}
		if (Math.random() > 0.6) {
			Bubble point = new Bubble(new Circle(10, Color.DARKBLUE));
			addPoint(point, Math.random() * 1300, -5);

		}

		if (Math.random() > 0.85) {
			Bubble bonus = new Bubble(new Circle(5, Color.GOLD));
			addBonus(bonus, Math.random() * 1300, -5);

		}

		if (Math.random() > 0.95) {
			Bubble shieldPoint = new Bubble(new Circle(10, Color.GREENYELLOW));
			addShieldPoint(shieldPoint, Math.random() * 1300, 0);
		}

		if (Math.random() > 0.98) {
			Bubble lifePoint = new Bubble(new Circle(5, Color.DEEPPINK));
			addLifePoint(lifePoint, Math.random() * 1300, 0);
		}

	}

	// set menu
	private Parent menu() {
		// Initialize VBox
		VBox menuPane = new VBox();
		menuPane.setPrefHeight(SIZE);
		menuPane.setPrefWidth(SIZE);

		// set text
		Text mainText = new Text("Bubble Strike");

		// set shadow affect
		setShadowText(mainText, 100, 10.0f, 270.0f);

		// set VBox
		menuPane.setAlignment(Pos.CENTER);
		menuPane.setSpacing(10);
		menuPane.setPadding(new Insets(10));

		// set style of buttons
		onePlayer.setStyle("-fx-font-weight: bold;");
		twoPlayer.setStyle("-fx-font-weight: bold;");
		exit.setStyle("-fx-font-weight: bold;");
		highScores.setStyle("-fx-font-weight: bold;");

		// set size of buttons
		onePlayer.setPrefWidth(200);
		onePlayer.setPrefHeight(50);
		twoPlayer.setPrefWidth(200);
		twoPlayer.setPrefHeight(50);
		highScores.setPrefWidth(200);
		highScores.setPrefHeight(50);
		exit.setPrefWidth(200);
		exit.setPrefHeight(50);

		// set place of buttons
		onePlayer.setLayoutX(SIZE / 2);
		twoPlayer.setLayoutX(SIZE / 2);
		highScores.setLayoutX(SIZE / 2);
		exit.setLayoutX(SIZE / 2);

		// set all the dailyTips into the array
		theDailyTip.setTheDailyTipStrings(dailyTip);

		Text dailyTipText = theDailyTip.setTheDailyTipPathTarnsition(dailyTip);

		// add nodes to VBox
		menuPane.getChildren().addAll(mainText, onePlayer, twoPlayer, highScores, exit, dailyTipText);

		return menuPane;

	}

	private Parent setDifficult() {

		Text chooseDif = new Text("Choose Difficulty");

		setShadowText(chooseDif, 60, 430, 250);

		// set resume picture
		Image resumeBack1 = new Image("file:Resources\\Buttons\\left.gif");
		ImageView resumeBackView1 = new ImageView(resumeBack1);

		// set resume and lets go buttons

		resume1.setLayoutX(20);
		resume1.setLayoutY(20);
		resume1.setGraphic(resumeBackView1);

		VBox difficultPane = new VBox();
		difficultPane.setPrefHeight(SIZE);
		difficultPane.setPrefWidth(SIZE);

		// set style of buttons
		easy.setStyle("-fx-font-weight: bold;");
		medium.setStyle("-fx-font-weight: bold;");
		hard.setStyle("-fx-font-weight: bold;");
		crazy.setStyle("-fx-font-weight: bold;");

		// set size of buttons
		easy.setPrefWidth(200);
		easy.setPrefHeight(50);
		medium.setPrefWidth(200);
		medium.setPrefHeight(50);
		hard.setPrefWidth(200);
		hard.setPrefHeight(50);
		crazy.setPrefWidth(200);
		crazy.setPrefHeight(50);

		// set VBox
		difficultPane.setAlignment(Pos.CENTER);
		difficultPane.setSpacing(10);
		difficultPane.setPadding(new Insets(10));

		// set place of buttons
		easy.setLayoutX(560);
		easy.setLayoutY(300);
		medium.setLayoutX(560);
		medium.setLayoutY(360);
		hard.setLayoutX(560);
		hard.setLayoutY(420);
		crazy.setLayoutX(560);
		crazy.setLayoutY(480);

		mainLayout.getChildren().addAll(chooseDif, easy, medium, hard, crazy, resume1);

		return mainLayout;

	}

	private Parent createContent() {

		movement.setMovementPlayer1Available(true);

		Timeline timeline = new Timeline(

				new KeyFrame(Duration.seconds(2), new KeyValue(soundtrack.getMainMediaPlayer().volumeProperty(), 0)));

		timeline.play();

		if (farmWasChoose == true) {

			soundtrack.farmovileSoundTrackOn();

			Image backgrownd = new Image("file:Resources\\Backgrounds\\farm.png");

			backgrowndView = new ImageView(backgrownd);

		}

		else if (desertWasChoose == true) {

			soundtrack.crazyDesertSoundTrackOn();

			Image backgrownd = new Image("file:Resources\\Backgrounds\\desert.png");

			backgrowndView = new ImageView(backgrownd);

		} else if (nightWatchWasChoose == true) {

			soundtrack.nightWatchSoundTrackOn();
			Image backgrownd = new Image("file:Resources\\Backgrounds\\nightwatch.png");

			backgrowndView = new ImageView(backgrownd);

		}
		backgrowndView.setFitWidth(1300);
		backgrowndView.setFitHeight(657);
		Text lifeText1 = new Text("LIFE");

		Rectangle lifeStroke = new Rectangle(300, 20);
		lifeStroke.setFill(Color.WHITE);
		lifeStroke.setX(950);
		lifeStroke.setY(640);
		lifeStroke.setStroke(Color.BLACK);

		movement.EnemiesMovement(enemies, easyMode, mediumMode, hardMode, crazyMode);
		movement.pointMovement(points, easyMode, mediumMode, hardMode, crazyMode);
		movement.bonusMovement(bonuses, easyMode, mediumMode, hardMode, crazyMode);
		movement.shieldPointsMovment(shieldPoints, easyMode, mediumMode, hardMode, crazyMode);
		movement.lifePointsMovement(lifePoints, easyMode, mediumMode, hardMode, crazyMode);

		player = new Player(playerOneRec);

		floorColor = new Rectangle(0, 600, 1500, 300);
		floorColor.setStroke(Color.BLACK);
		floorColor.setStrokeWidth(0.5);

		if (nightWatchWasChoose == true) {
			floorColor.setFill(Color.DARKSLATEGRAY);
		} else if (desertWasChoose == true) {
			floorColor.setFill(Color.SADDLEBROWN);
		} else {
			floorColor.setFill(Color.BURLYWOOD);
		}
		lifeText1.setX(1080);
		lifeText1.setY(655);

		lifeText1.setFont(Font.font(null, FontWeight.BOLD, 16));
		setTextOnField();
		player.setLifeLocation(950, 640);

		mainLayout.getChildren().addAll(backgrowndView, floorColor, scoreTextPlayer1, lifeStroke, player.getLife(),
				lifeText1);

		if (twoPlayers == true) {

			Text lifeText2 = new Text("LIFE");

			Rectangle lifeStroke2 = new Rectangle(300, 20);
			lifeStroke2.setFill(Color.WHITE);
			lifeStroke2.setX(30);
			lifeStroke2.setY(640);
			lifeStroke2.setStroke(Color.BLACK);

			lifeText2.setFont(Font.font(null, FontWeight.BOLD, 16));
			lifeText2.setX(150);
			lifeText2.setY(655);

			player2 = new Player(playerTwoRec);
			player2.setLifeLocation(30, 640);

			addGameObject(player, 800, 580);

			mainLayout.getChildren().addAll(scoreTextPlayer2, lifeStroke2, player2.getLife(), lifeText2);

			addGameObject(player2, 400, 580);

		} else {

			addGameObject(player, 600, 580);

		}

		Timelines.makeBalls.play();

		Timelines.time.getKeyFrames().add(new KeyFrame(Duration.millis(30), e -> {

			try {

				onUpdate();

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block

			}

		}));

		Timelines.shieldsCounter.getKeyFrames().add(new KeyFrame(Duration.millis(1000), e -> {

			isShieldOn = shield1.updateShield();
			if (!isShieldOn)
				mainLayout.getChildren().remove(shield1);

			isShieldOn = shield2.updateShield();
			if (!isShieldOn)
				mainLayout.getChildren().remove(shield2);
		}));

		Timelines.time.setCycleCount(Animation.INDEFINITE);
		Timelines.time.play();
		Timelines.shieldsCounter.setCycleCount(Animation.INDEFINITE);
		Timelines.shieldsCounter.play();

		return mainLayout;

	}

	// Very important method, check if there is a HIT between the Bubbles and the
	// player and do something when needed.

	private void onUpdate() throws FileNotFoundException {

		ArrayList<Object> scoreAndShield = playerUpdate(player, shield1, scoreTextPlayer1, player1Name, scorePlayer1);

		scorePlayer1 = (int) scoreAndShield.get(0);
		shield1 = (Shield) scoreAndShield.get(1);

		if (twoPlayers) {

			scoreAndShield = playerUpdate(player2, shield2, scoreTextPlayer2, player2Name, scorePlayer2);

			scorePlayer2 = (int) scoreAndShield.get(0);
			shield2 = (Shield) scoreAndShield.get(1);

		}

		scoreAndShield = null; // Deleting the array from memory

	}

	public ArrayList<Object> playerUpdate(Player player, Shield shield, Text scoreTextPlayer, Text playerName,
			int scorePlayer) throws FileNotFoundException {

		ArrayList<Object> scoreAndShield = new ArrayList<>();

		for (GameObject enemy : enemies) {
			if (shield.isShieldOn() == false) {
				if (enemy.isColliding(player)) {

					if (!enemy.isSoundEfectPlayed())
						soundtrack.hitSound();

					enemy.setSoundEfectPlayed(true);

					mainLayout.getChildren().remove(enemy.getNode());
					sendCirclesToHell(enemy);
					player.setLifeMinus();

				}
			}

			if (shield.isShieldOn() == true) {
				if (enemy.isCollidingShield(shield)) {

					if (!enemy.isSoundEfectPlayed())
						soundtrack.pointSound();

					scoreTextPlayer.setText(playerName.getText().toString() + " : " + ++scorePlayer);
					scoreTextPlayer.setStrokeWidth(0.5);

					enemy.setSoundEfectPlayed(true);

					mainLayout.getChildren().remove(enemy.getNode());
					sendCirclesToHell(enemy);
				}
			}

			if (enemy.isOutOfBounds()) {

				mainLayout.getChildren().remove(enemy.getNode());
				sendCirclesToHell(enemy);
			}

			if (player.getLife().getWidth() <= 0 && isDead == false) {
				mainLayout.getChildren().remove(enemy.getNode());
				Timelines.time.stop();
				Timelines.makeBalls.stop();
				isDead = true;

				setGameOverTextOnField();
			}

		}
		for (GameObject point : points) {
			if (point.isColliding(player)) {

				if (!point.isSoundEfectPlayed())
					soundtrack.pointSound();

				point.setSoundEfectPlayed(true);

				point.setAlive(false);

				scoreTextPlayer.setText(playerName.getText().toString() + " : " + ++scorePlayer);
				scoreTextPlayer.setStrokeWidth(0.5);
				mainLayout.getChildren().remove(point.getNode());
				sendCirclesToHell(point);

			}

			if (point.isOutOfBounds()) {
				mainLayout.getChildren().remove(point.getNode());
				sendCirclesToHell(point);

			}

		}

		for (GameObject bonus : bonuses) {
			if (bonus.isColliding(player)) {

				if (!bonus.isSoundEfectPlayed())

					soundtrack.pointSound();

				bonus.setSoundEfectPlayed(true);

				bonus.setAlive(false);

				scorePlayer += 5;
				scoreTextPlayer.setText(playerName.getText().toString() + " : " + +scorePlayer);
				mainLayout.getChildren().remove(bonus.getNode());
				sendCirclesToHell(bonus);

			}

			if (bonus.isOutOfBounds()) {
				mainLayout.getChildren().remove(bonus.getNode());
				sendCirclesToHell(bonus);
			}

		}

		for (GameObject shieldPoint : shieldPoints) {
			if (shieldPoint.isColliding(player)) {

				if (!shieldPoint.isSoundEfectPlayed())

					soundtrack.shieldSound();

				shieldPoint.setSoundEfectPlayed(true);

				if (shield.isShieldOn() == true) {

					shield.setShieldCounter(0);

					mainLayout.getChildren().remove(shield);
					sendCirclesToHell(shieldPoint);

				}

				shield.setShield(player);

				mainLayout.getChildren().add(shield);
				mainLayout.getChildren().remove(shieldPoint.getNode());
				sendCirclesToHell(shieldPoint);

			}

			if (shieldPoint.isOutOfBounds()) {
				mainLayout.getChildren().remove(shieldPoint.getNode());
				sendCirclesToHell(shieldPoint);

			}
		}

		for (GameObject lifePoint : lifePoints) {
			if (lifePoint.isColliding(player)) {

				if (!lifePoint.isSoundEfectPlayed())
					soundtrack.healSound();
				lifePoint.setSoundEfectPlayed(true);
				sendCirclesToHell(lifePoint);
				mainLayout.getChildren().remove(lifePoint.getNode());
				player.setLifePlus();

			}

			if (lifePoint.isOutOfBounds()) {
				mainLayout.getChildren().remove(lifePoint.getNode());
				sendCirclesToHell(lifePoint);
			}
		}

		scoreAndShield.add(scorePlayer);
		scoreAndShield.add(shield);

		return scoreAndShield;

	}

	private void sendCirclesToHell(GameObject point) {

		point = null;

	}

	private void addGameObject(GameObject object, double x, double y) {

		object.getNode().setTranslateX(x);
		object.getNode().setTranslateY(y);
		mainLayout.getChildren().add(object.getNode());

	}

	private void addEnemy(GameObject enemy, double x, double y) {
		enemies.add(enemy);
		addGameObject(enemy, x, y);
	}

	private void addPoint(GameObject point, double x, double y) {
		points.add(point);
		addGameObject(point, x, y);
	}

	private void addBonus(GameObject bonus, double x, double y) {
		bonuses.add(bonus);
		addGameObject(bonus, x, y);

	}

	private void addShieldPoint(GameObject shieldPoint, double x, double y) {
		shieldPoints.add(shieldPoint);
		addGameObject(shieldPoint, x, y);
	}

	private void addLifePoint(GameObject shieldPoint, double x, double y) {
		lifePoints.add(shieldPoint);
		addGameObject(shieldPoint, x, y);
	}

	public void setGameOverTextOnField() throws FileNotFoundException {

		Timelines.time.stop();
		Timelines.time.getKeyFrames().clear();

		isScoreHigher = highscores.setHighScore(highScoreFile, scorePlayer1 + "", player1Name, isScoreHigher);

		if (twoPlayers == true) {
			isScoreHigher = highscores.setHighScore(highScoreFile, scorePlayer2 + "", player2Name, isScoreHigher);

		}

		if (isScoreHigher == true) {

			soundtrack.highScoreSound();

			Text scoreIsHigher = new Text("NEW HIGH SCORE");
			setShadowText(scoreIsHigher, 80, 300, 600);

			setFadeHighScore(scoreIsHigher);

			mainLayout.getChildren().add(scoreIsHigher);

		}

		movement.setMovementPlayer1Available(false);
		movement.setMovementPlayer2Available(false);
		inTheMiddleOfGame = false;
		myTimeline.pauseAllTimeLines();

		// set text
		Text gameOverText = new Text("GAME OVER");
		setShadowText(gameOverText, 80, 410, 270.0f);

		mainLayout.getChildren().addAll(gameOverText, restart, exit, newGame);

		setMenuChooseButtons();

	}

	private void setFadeHighScore(Text scoreIsHigher) {

		Timelines.fadeHighScore.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
			if (mainLayout.getChildren().contains(scoreIsHigher)) {
				mainLayout.getChildren().remove(scoreIsHigher);
			} else {
				mainLayout.getChildren().add(scoreIsHigher);

			}

			if (movement.isMovementPlayer1Available() == true) {
				Timelines.fadeHighScore.stop();
			}
		}));

		Timelines.fadeHighScore.setCycleCount(Animation.INDEFINITE);
		Timelines.fadeHighScore.play();

	}

	public void setMenuChooseButtons() {

		restart.setStyle("-fx-font-weight: bold;");
		exit.setStyle("-fx-font-weight: bold;");
		newGame.setStyle("-fx-font-weight: bold;");

		restart.setPrefWidth(100);
		restart.setLayoutX(600);
		restart.setLayoutY(380);
		exit.setPrefWidth(100);
		exit.setPrefHeight(10);
		exit.setLayoutX(450);
		exit.setLayoutY(380);
		newGame.setPrefWidth(100);
		newGame.setPrefHeight(10);
		newGame.setLayoutX(750);
		newGame.setLayoutY(380);

		restart.setOnAction(e -> {

			isDead = false;

			scorePlayer1 = 0;
			scorePlayer2 = 0;

			if (desertWasChoose == true) {
				soundtrack.getDesertMediaPlayer().stop();

			} else if (farmWasChoose == true) {
				soundtrack.getFarmMediaPlayer().stop();

			} else if (nightWatchWasChoose == true) {
				soundtrack.getNightMediaPlayer().stop();

			}

			// Clears the Arrays

			setAllArraysToNull();

			// Clear all timeLines

			myTimeline.stopAllTimeLines();

			// Switch every Boolean to false again

			setBooleansToFlase();

			mainLayout.getChildren().clear();

			scoreTextPlayer1.setText(player1Name.getText().toString() + " : 0");
			scorePlayer1 = 0;
			mainScene.setRoot(createContent());
		});

		exit.setOnAction(e -> {
			System.exit(0);
		});

		newGame.setOnAction(e -> {

			isDead = false;

			scorePlayer1 = 0;
			scorePlayer2 = 0;

			if (desertWasChoose == true) {
				soundtrack.getDesertMediaPlayer().stop();
			} else if (farmWasChoose == true) {
				soundtrack.getFarmMediaPlayer().stop();
			} else if (nightWatchWasChoose == true) {
				soundtrack.getNightMediaPlayer().stop();
			}

			myTimeline.stopAllTimeLines();

			setBooleansToFlase();

			setAllArraysToNull();

			myTimeline.setAllTimelinesToNull();

			mainLayout.getChildren().clear();
			mainScene.setRoot(menu());
			soundtrack.mainSoundTrackOn();

			setMenuKeys();

		});

		if (inTheMiddleOfGame == true) {

			resumeGame.setStyle("-fx-font-weight: bold;");
			resumeGame.setPrefWidth(200);
			resumeGame.setPrefHeight(40);
			resumeGame.setLayoutX(570);
			resumeGame.setLayoutY(250);

			exit.setPrefWidth(200);
			exit.setPrefHeight(40);
			exit.setLayoutX(570);
			exit.setLayoutY(410);
			newGame.setPrefWidth(200);
			newGame.setPrefHeight(40);
			newGame.setLayoutX(570);
			newGame.setLayoutY(330);

			resumeGame.setOnAction(e -> {

				movement.setMovementPlayer1Available(true);
				movement.setMovementPlayer2Available(true);

				myTimeline.playAllTimeLines();

				mainLayout.getChildren().removeAll(resumeGame, exit, newGame);

			});

			mainLayout.getChildren().addAll(resumeGame, newGame, exit);

		}
	}

	private void setBooleansToFlase(boolean... args) {

		twoPlayers = false;
		easyMode = false;
		mediumMode = false;
		hardMode = false;
		crazyMode = false;
		farmWasChoose = false;
		desertWasChoose = false;
		nightWatchWasChoose = false;
		isScoreHigher = false;

	}

	private void setAllArraysToNull() {

		enemies = null;
		points = null;
		bonuses = null;
		lifePoints = null;
		shieldPoints = null;

	}

	private void setTextOnField() {

		if (player1Name.getText().isEmpty()) {
			player1Name.textProperty().unbind();
			player1Name.textProperty().set("Player1");
		}

		if (twoPlayers == true) {
			if (player2Name.getText().isEmpty()) {
				player2Name.textProperty().unbind();
				player2Name.textProperty().set("Player2");
			}
		}

		scoreTextPlayer1 = new Text(player1Name.getText().toString() + " : 0");

		scoreTextPlayer1.setStroke(Color.BLACK);

		if (twoPlayers == true) {

			scoreTextPlayer1.setX(1110);
			scoreTextPlayer1.setY(40);
			scoreTextPlayer1.setFont(Font.font("Bold", 30));
			scoreTextPlayer1.setFill(playerOneRec.getFill());

			scoreTextPlayer2 = new Text(player2Name.getText().toString() + " : 0");

			scoreTextPlayer2.setStroke(Color.BLACK);

			scoreTextPlayer2.setX(10);
			scoreTextPlayer2.setY(40);
			scoreTextPlayer2.setFont(Font.font("Bold", 30));
			scoreTextPlayer2.setFill(playerTwoRec.getFill());

		} else {

			scoreTextPlayer1.setX(20);
			scoreTextPlayer1.setY(40);
			scoreTextPlayer1.setFont(Font.font("Bold", 30));
			scoreTextPlayer1.setFill(playerOneRec.getFill());

		}

	}

}
