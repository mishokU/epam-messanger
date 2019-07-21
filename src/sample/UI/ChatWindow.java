package sample.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

import java.awt.event.KeyEvent;
import java.io.PrintWriter;

public class ChatWindow {

    private TextArea messages;
    private TextArea users;
    private TextField inputField;
    private Button pushMessage;

    private PrintWriter out;

    public ChatWindow(PrintWriter out){
        this.out = out;
    }

    public void initChatWindow(){
        initMessages();
        initInputField();
        //initUsersField();
        initPushBtn(out);
    }

    /*private void initUsersField() {
        users = new TextArea();
        users.setMaxWidth(90);
        users.setMinHeight(560);
        users.setTranslateX(500);
        users.setTranslateY(30);
    }*/

    private void initMessages(){
        messages = new TextArea();
        messages.setTranslateX(10);
        messages.setTranslateY(30);
        messages.setMinSize(400,370);
    }

    private void initInputField(){
        inputField = new TextField();
        inputField.setTranslateY(420);
        inputField.setTranslateX(10);
        inputField.setMinSize(430,25);
    }

    private void initPushBtn(PrintWriter out){
        pushMessage = new Button();
        pushMessage.setTranslateX(442);
        pushMessage.setTranslateY(420);
        pushMessage.setText("Push");
        pushMessage.setOnAction(actionEvent -> {
                out.println(inputField.getCharacters().toString());
                inputField.setText("");
        });

    }

    public Button getPushMessage(){
        return pushMessage;
    }

    public TextField getInputField(){
        return inputField;
    }

    public TextArea getMessagesField(){
        return messages;
    }

    public TextArea getUsersField(){
        return users;
    }
}
