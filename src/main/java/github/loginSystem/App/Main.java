package github.loginSystem.App;

import github.loginSystem.JSON.JsonUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import github.loginSystem.Scenes.SceneManager;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        SceneManager sceneManager = new SceneManager(stage);
        JsonUtil jsonUtil = new JsonUtil("userFile.json");
        sceneManager.LoginScene();
        stage.show();
    }

}
