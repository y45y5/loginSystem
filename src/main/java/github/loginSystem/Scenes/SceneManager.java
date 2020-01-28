package github.loginSystem.Scenes;

import github.loginSystem.Controllers.AccountController;
import github.loginSystem.User.User;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage stage;

    public SceneManager(Stage stage){
        SceneManager.stage = stage;
        stage.setResizable(false);

        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public SceneManager(){

    }

    public void LoginScene(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/fxml/loginWindow.fxml"));
            Pane pane = fxmlLoader.load();
            stage.setScene(new Scene(pane));
            stage.setTitle("Login");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void RegisterScene(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/fxml/registerWindow.fxml"));
            Pane pane = fxmlLoader.load();
            stage.setScene(new Scene(pane));
            stage.setTitle("Register");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void AccountScene(User user){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/fxml/accountWindow.fxml"));
            Pane pane = fxmlLoader.load();
            AccountController accountController = fxmlLoader.getController();
            accountController.setUser(user);

            stage.setScene(new Scene(pane));
            stage.setTitle("Account");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
