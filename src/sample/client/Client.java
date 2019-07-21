package sample.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.UI.ChatWindow;
import sample.UI.RegistrationWindow;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Application {

    private Stage mainStage;
    private RegistrationWindow registrationWindow;
    private ChatWindow chatWindow;
    private Group regGroup,chatGroup;
    private Scene regScene, chatScene;
    private Button registrationBtn;

    private Scanner in;
    private PrintWriter out;
    private Thread clientThread;

    private Socket client;
    private String userName;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        mainStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        initRegistrationScene();

        stage.setScene(regScene);
        stage.setResizable(false);
        stage.setTitle("Chat application");
        stage.show();
    }

    private void initChatScene() {
        chatWindow = new ChatWindow(out);
        chatWindow.initChatWindow();

        chatGroup = new Group();
        chatGroup.getChildren().addAll(
                chatWindow.getInputField(),
                chatWindow.getMessagesField(),
                chatWindow.getPushMessage()
                //chatWindow.getUsersField()
        );

        chatScene = new Scene(chatGroup,500,450,Color.BISQUE);
        mainStage.setScene(chatScene);
    }

    private void initRegistrationScene() {
        registrationWindow = new RegistrationWindow();
        registrationWindow.windowInit();

        initRegistrationButton();

        regGroup = new Group();
        regGroup.getChildren().addAll(
                registrationWindow.getWelcomeTextField(),
                registrationWindow.getUserNameField(),
                registrationWindow.getIpAddressField(),
                registrationWindow.getPortField(),
                registrationBtn
        );

        regScene = new Scene(regGroup,500,450,Color.BISQUE);
    }

    private void initRegistrationButton() {
        registrationBtn = new Button("Registration");
        registrationBtn.setTranslateY(300);
        registrationBtn.setTranslateX(220);
        registrationBtn.setOnAction(actionEvent -> {
            initSocket();
            initChatScene();
        });
    }

    private void initSocket() {
        try {
            client = new Socket(registrationWindow.getIpAddress(),
                    registrationWindow.getPort());

            in = new Scanner(client.getInputStream());
            out = new PrintWriter(client.getOutputStream(),true);

            userName = registrationWindow.getUserNameField().getCharacters().toString();
            out.println(userName);

            clientThread = new Thread(() -> {
                while(in.hasNextLine()){
                    chatWindow.getMessagesField().appendText(in.nextLine() + "\n");
                }
            });

            clientThread.start();

        } catch (IOException e) {
            clientThread.interrupt();
            throw new RuntimeException(e);
        }
    }
}
