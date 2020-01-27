package github.loginSystem.Controllers;

import github.loginSystem.Hibernate.HibernateUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import github.loginSystem.Scenes.SceneManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button toLoginButton;

    @FXML private Label usernameError;
    @FXML private Label emailError;
    @FXML private Label passwordError;
    @FXML private Label confirmPasswordError;

    private SceneManager sceneManager;

    public RegisterController(){

    }

    @FXML void initialize(){
        Platform.runLater(() -> {
            sceneManager = new SceneManager();
            resetErrorInfo();
        });
    }

    private void resetErrorInfo(){
        usernameError.setText("");
        emailError.setText("");
        passwordError.setText("");
        confirmPasswordError.setText("");
    }

    @FXML void toLoginAction(){
        sceneManager.LoginScene();
    }

    @FXML void onRegisterAction(){
        resetErrorInfo();
        Pattern userAndPassPattern = Pattern.compile("[a-zA-Z0-9]{7,}");
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

        Matcher usernameMatcher = userAndPassPattern.matcher(usernameField.getCharacters());
        Matcher passwordMatcher = userAndPassPattern.matcher(passwordField.getCharacters());
        Matcher emailMatcher = emailPattern.matcher(emailField.getCharacters());

        HibernateUtil hibernateUtil = new HibernateUtil();

        try{
            if(usernameMatcher.matches()) {
                if (hibernateUtil.checkUsername(usernameField.getCharacters().toString())){
                    if (emailMatcher.matches()) {
                        if (passwordMatcher.matches()) {
                            if (passwordField.getCharacters().toString().contentEquals(confirmPasswordField.getCharacters())) {

                                hibernateUtil.createUser(
                                        usernameField.getCharacters().toString(),
                                        passwordField.getCharacters().toString(),
                                        emailField.getCharacters().toString());

                                sceneManager.AccountScene(
                                        usernameField.getCharacters().toString(),
                                        passwordField.getCharacters().toString(),
                                        emailField.getCharacters().toString());

                            } else confirmPasswordError.setText("Passwords don't match!");
                        } else passwordError.setText("Password is invalid! (min. 7 characters)");
                    } else emailError.setText("Email is invalid!");
                } else usernameError.setText("Username already taken!");
            } else usernameError.setText("Username is invalid! (min. 7 characters)");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
