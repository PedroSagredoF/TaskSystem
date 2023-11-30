package ps.task.display;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import ps.task.TaskApplication;

public class TaskSystemFx extends Application {

   //public static void main(String[] args) {
    //    launch(args);
    //}

    //To import spring features
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init(){
        this.applicationContext
                = new SpringApplicationBuilder(TaskApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {

        //To load Spring into JavaFx
        FXMLLoader loader= new FXMLLoader(TaskApplication.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean);

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop(){
        applicationContext.close();
        Platform.exit();
    }
}
