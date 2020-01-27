package github.loginSystem.Controllers;

import github.loginSystem.Hibernate.HibernateUtil;
import github.loginSystem.User.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import github.loginSystem.Scenes.SceneManager;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label loginError;
    private SceneManager sceneManager;

    public LoginController(){

    }

    @FXML void initialize(){
        Platform.runLater(() -> {
            sceneManager = new SceneManager();
            loginError.setText("");
        });
    }

    @FXML void toRegisterAction(){
        sceneManager.RegisterScene();
    }

    @FXML void loginAction(){
        HibernateUtil hibernateUtil = new HibernateUtil();
        User user = hibernateUtil.login(usernameField.getCharacters().toString(), passwordField.getCharacters().toString());
        if(user != null) sceneManager.AccountScene(user);
        else loginError.setText("Username or password is invalid!");
    }
}
