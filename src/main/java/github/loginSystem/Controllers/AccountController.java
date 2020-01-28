package github.loginSystem.Controllers;

import github.loginSystem.JSON.JsonUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import github.loginSystem.Scenes.SceneManager;
import github.loginSystem.User.User;

public class AccountController {

    @FXML private Label welcomeLabel;

    private User user;
    private SceneManager sceneManager;

    public AccountController(){

    }

    @FXML void initialize(){
        Platform.runLater(() -> {
            sceneManager = new SceneManager();
            welcomeLabel.setText("Hi, " + user.getUsername() + "!\n(" + user.getEmail() + ")");
        });
    }

    public void setUser(User user){
        this.user = user;
    }

    @FXML void logOutAction(){
        sceneManager.LoginScene();
        welcomeLabel.setText("");
    }

    @FXML void deleteAccountAction(){
        try{
            JsonUtil jsonUtil = new JsonUtil();
            jsonUtil.deleteUser(user);
            logOutAction();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
