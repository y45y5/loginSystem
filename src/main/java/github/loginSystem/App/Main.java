package github.loginSystem.App;

import javafx.application.Application;
import javafx.stage.Stage;
import github.loginSystem.Scenes.SceneManager;

import static github.loginSystem.Hibernate.HibernateUtil.addAllConfigs;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        SceneManager sceneManager = new SceneManager(stage);
        addAllConfigs();
        sceneManager.LoginScene();
        stage.show();
    }

}
