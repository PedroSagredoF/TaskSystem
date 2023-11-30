package ps.task;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ps.task.display.TaskSystemFx;


@SpringBootApplication
public class TaskApplication {

	public static void main(String[] args) {
		//SpringApplication.run(TaskApplication.class, args);

		//JavaFx starter
		Application.launch(TaskSystemFx.class, args);
	}

}
