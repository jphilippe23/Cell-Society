

import front_end.GUI;
import back_end.Simulation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{
	public static final String TITLE = "Simulator";
	public static final int SIZE = 600;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int KEY_INPUT_SPEED = 5;
	public static final double GROWTH_RATE = 1.1;

	// some things we need to remember during our game

	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	public void start (Stage s)
	{	
		GUI container = new GUI(SIZE,SIZE);
		Scene scene = container.setScene();
		Simulation simulation;
		s.setScene(scene);
		s.setTitle(TITLE);
		s.show();

		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY, container));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		container.initButtons(() -> { animation.play(); 
		System.out.println("test1");},
				() -> {animation.pause();
				System.out.println("test2");
				}, 
				() -> 
				{
					if (animation.getStatus() == Animation.Status.PAUSED) step(SECOND_DELAY,container);
					System.out.println("test3");
				}
		);
	}    

	private void step (double elapsedTime, GUI inContainer)
	{
		//simulation.updateState();
		//inContainer.renderGrid(simulation.getColors());
	}

	/**
	 * Start the program.
	 */
	public static void main (String[] args)
	{
		launch(args);
	}
}