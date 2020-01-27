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

    private static Scene loginScene;
    private static Scene registerScene;

    public SceneManager(Stage stage){
        SceneManager.stage = stage;
        stage.setResizable(false);

        setLoginScene();
        setRegisterScene();

        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public SceneManager(){

    }

    private void setLoginScene(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/fxml/loginWindow.fxml"));
            Pane pane = fxmlLoader.load();
            SceneManager.loginScene = new Scene(pane);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void setRegisterScene(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/fxml/registerWindow.fxml"));
            Pane pane = fxmlLoader.load();
            SceneManager.registerScene = new Scene(pane);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void LoginScene(){
        stage.setScene(loginScene);
        stage.setTitle("Login");
    }

    public void RegisterScene(){
        stage.setScene(registerScene);
        stage.setTitle("Register");
    }

    public void AccountScene(User user){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/fxml/accountWindow.fxml"));
            Pane pane = fxmlLoader.load();
            AccountController accountController = fxmlLoader.getController();
            accountController.setUser(user);
            Scene scene = new Scene(pane);

            stage.setScene(scene);
            stage.setTitle("Account");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void AccountScene(String username, String password, String email){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/fxml/accountWindow.fxml"));
            Pane pane = fxmlLoader.load();
            AccountController accountController = fxmlLoader.getController();
            accountController.setUser(username, password, email);
            Scene scene = new Scene(pane);

            stage.setScene(scene);
            stage.setTitle("Account");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
