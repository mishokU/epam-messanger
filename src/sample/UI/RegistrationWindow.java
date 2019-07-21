package sample.UI;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegistrationWindow {

    private TextField userName;
    private TextField ipAddress;
    private TextField portField;
    private Label welcomeText;

    public void windowInit(){
        initUserNameField();
        initIpAddressField();
        initPort();
        initWelcomeText();
    }

    private void initPort() {
        portField = new TextField();
        portField.setTranslateY(250);
        portField.setTranslateX(180);
        portField.setPromptText("Port");
    }

    private void initWelcomeText() {
        welcomeText = new Label("Welcome to chat");
        welcomeText.setTranslateX(150);
        welcomeText.setTranslateY(20);
        welcomeText.setStyle("-fx-font: 32 Arial");
    }

    private void initIpAddressField() {
        ipAddress = new TextField();
        ipAddress.setTranslateX(180);
        ipAddress.setTranslateY(200);
        ipAddress.setPromptText("Ip address");
    }

    private void initUserNameField() {
        userName = new TextField();
        userName.setTranslateX(180);
        userName.setTranslateY(150);
        userName.setPromptText("Input name");
    }

    public int getPort(){
        return Integer.parseInt(portField.getCharacters().toString());
    }

    public String getIpAddress(){
        return ipAddress.getCharacters().toString();
    }

    public TextField getUserNameField(){
        return userName;
    }

    public TextField getPortField(){
        return portField;
    }

    public TextField getIpAddressField(){
        return ipAddress;
    }

    public Label getWelcomeTextField(){
        return welcomeText;
    }
}
