package github.loginSystem.Controllers;

import github.loginSystem.Hibernate.HibernateUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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

    public void setUser(String username, String password, String email){
        user = new User(username, password, email);
    }

    @FXML void logOutAction(){
        sceneManager.LoginScene();
        welcomeLabel.setText("");
    }

    @FXML void deleteAccountAction(){
        try{
            HibernateUtil hibernateUtil = new HibernateUtil();
            hibernateUtil.deleteUser(user);
            logOutAction();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
