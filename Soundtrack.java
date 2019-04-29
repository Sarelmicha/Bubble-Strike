import java.io.File;


import javafx.animation.Animation;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Soundtrack {

	private Media mainMedia;
	private MediaPlayer mainMediaPlayer;
	private Media desertMedia;
	private MediaPlayer desertMediaPlayer;
	private Media farmMedia;
	private MediaPlayer farmMediaPlayer;
	private Media nightMedia;
	private MediaPlayer nightMediaPlayer;
	private Media pointMedia;
	private MediaPlayer pointMediaPlayer;
	private Media shieldMedia;
	private MediaPlayer shieldMediaPlayer;
	private Media highScoreMedia;
	private MediaPlayer highScoreMediaPlayer;
	private Media healMedia;
	private MediaPlayer healMediaPlayer;
	private Media hitMedia;
	private MediaPlayer hitMediaPlayer;

	MediaPlayer mainSoundTrackOn() {

		String path = "Resources//Music//111 - music 11.mp3";
		mainMedia = new Media(new File(path).toURI().toString());
		mainMediaPlayer = new MediaPlayer(mainMedia);
		mainMediaPlayer.setAutoPlay(true);
		mainMediaPlayer.setCycleCount(Animation.INDEFINITE);
		mainMediaPlayer.play();

		return mainMediaPlayer;

	}

	public void crazyDesertSoundTrackOn() {
		
		

		String path = "Resources//Music//110 - music 10.mp3";
		desertMedia = new Media(new File(path).toURI().toString());
		desertMediaPlayer = new MediaPlayer(desertMedia);
		desertMediaPlayer.setAutoPlay(true);
		desertMediaPlayer.setCycleCount(Animation.INDEFINITE);
		desertMediaPlayer.play();

	}

	public void farmovileSoundTrackOn() {

		String path = "Resources//Music//105 - music 05.mp3";
		farmMedia = new Media(new File(path).toURI().toString());
		farmMediaPlayer = new MediaPlayer(farmMedia);
		farmMediaPlayer.setAutoPlay(true);
		farmMediaPlayer.setCycleCount(Animation.INDEFINITE);
		farmMediaPlayer.play();

	}

	public void nightWatchSoundTrackOn() {

		String path = "Resources//Music//107 - music 07.mp3";
		nightMedia = new Media(new File(path).toURI().toString());
		nightMediaPlayer = new MediaPlayer(nightMedia);
		nightMediaPlayer.setAutoPlay(true);
		nightMediaPlayer.setCycleCount(Animation.INDEFINITE);
		nightMediaPlayer.play();

	}

	public void pointSound() {

		String path = "Resources//Sounds//Blop-Mark_DiAngelo-79054334.mp3";
		pointMedia = new Media(new File(path).toURI().toString());
		pointMediaPlayer = new MediaPlayer(pointMedia);

		pointMediaPlayer.setAutoPlay(true);
		pointMediaPlayer.play();

	}

	public void shieldSound() {

		String path = "Resources//Sounds//Decapitation-SoundBible.com-800292304.mp3";
		shieldMedia = new Media(new File(path).toURI().toString());
		shieldMediaPlayer = new MediaPlayer(shieldMedia);
		shieldMediaPlayer.setAutoPlay(true);
		shieldMediaPlayer.play();

	}

	public void highScoreSound() {

		String path = "Resources//Sounds//Ta Da-SoundBible.com-1884170640.mp3";
		highScoreMedia = new Media(new File(path).toURI().toString());
		highScoreMediaPlayer = new MediaPlayer(highScoreMedia);
		highScoreMediaPlayer.setAutoPlay(true);
		highScoreMediaPlayer.play();

	}

	public void healSound() {

		String path = "Resources\\Sounds\\long-synth-choir_D_major (online-audio-converter.com).mp3";
		healMedia = new Media(new File(path).toURI().toString());
		healMediaPlayer = new MediaPlayer(healMedia);
		healMediaPlayer.setAutoPlay(true);
		healMediaPlayer.play();

	}

	public void hitSound() {

		String path = "Resources\\Sounds\\punch_or_whack_-Vladimir-403040765.mp3";
		hitMedia = new Media(new File(path).toURI().toString());
		hitMediaPlayer = new MediaPlayer(hitMedia);
		hitMediaPlayer.setAutoPlay(true);
		hitMediaPlayer.play();

	}

	public Media getMainMedia() {
		return mainMedia;
	}

	public MediaPlayer getMainMediaPlayer() {
		return mainMediaPlayer;
	}

	public Media getDesertMedia() {
		return desertMedia;
	}

	public MediaPlayer getDesertMediaPlayer() {
		return desertMediaPlayer;
	}

	public Media getFarmMedia() {
		return farmMedia;
	}

	public MediaPlayer getFarmMediaPlayer() {
		return farmMediaPlayer;
	}

	public Media getNightMedia() {
		return nightMedia;
	}

	public MediaPlayer getNightMediaPlayer() {
		return nightMediaPlayer;
	}

	public void setMainMedia(Media mainMedia) {
		this.mainMedia = mainMedia;
	}

	public void setMainMediaPlayer(MediaPlayer mainMediaPlayer) {
		this.mainMediaPlayer = mainMediaPlayer;
	}

	public void setDesertMedia(Media desertMedia) {
		this.desertMedia = desertMedia;
	}

	public void setDesertMediaPlayer(MediaPlayer desertMediaPlayer) {
		this.desertMediaPlayer = desertMediaPlayer;
	}

	public void setFarmMedia(Media farmMedia) {
		this.farmMedia = farmMedia;
	}

	public void setFarmMediaPlayer(MediaPlayer farmMediaPlayer) {
		this.farmMediaPlayer = farmMediaPlayer;
	}

	public void setNightMedia(Media nightMedia) {
		this.nightMedia = nightMedia;
	}

	public void setNightMediaPlayer(MediaPlayer nightMediaPlayer) {
		this.nightMediaPlayer = nightMediaPlayer;
	}

}
