package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView img;

    @FXML
    private AnchorPane loginPane, gamePane;

    @FXML
    private TextField userTF;

    @FXML
    private PasswordField passTF;

    @FXML
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, startButton;

    @FXML
    private Label l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16,
            errorLabel, nameLabel, scoreLabel;


    private final String[] animals = {"Cat", "Dog", "Dragon", "Rabbit", "Snake", "Parrot", "Horse", "Pony",
            "Cat", "Dog", "Dragon", "Rabbit", "Snake", "Parrot", "Horse", "Pony"};
    private ArrayList<Button> buttonsList = new ArrayList<>();
    private ArrayList<Label> labelsList = new ArrayList<>();
    private boolean isFirst = true;
    private String firstAnimal;
    private int score = 0, counter = 0, total = 0, counter1 = 0;
    private Button lastButton;
    private Label lastLabel;
    private ArrayList<Integer> spots = new ArrayList<>();
    private ArrayList<Integer> animalSpots = new ArrayList<>();
    private PostgreSQL psql = new PostgreSQL();

    @FXML
    void logIn() throws SQLException {
        psql.setCredentials(userTF.getText(), passTF.getText());
        if(psql.checkUser()){
            errorLabel.setVisible(false);
            loginPane.setVisible(false);
            loginPane.setDisable(true);
            gamePane.setVisible(true);
            gamePane.setDisable(false);
            total = psql.getScore();
            nameLabel.setText(userTF.getText());
            scoreLabel.setText("Score: " + total);
        }else{
            errorLabel.setVisible(true);
        }
    }

    void disableAll(){
        counter = 0;
        Timeline tml = new Timeline(new KeyFrame(Duration.millis(1), e->{
            buttonsList.get(counter).setDisable(true);
            counter++;
        }));
        tml.setCycleCount(16);
        tml.setAutoReverse(false);
        tml.play();
    }

    void restartButtons(){
        counter = 0;
        Timeline tml = new Timeline(new KeyFrame(Duration.millis(1), e->{
            buttonsList.get(counter).setDisable(false);
            buttonsList.get(counter).setVisible(true);
            counter++;
        }));
        tml.setCycleCount(16);
        tml.setAutoReverse(false);
        tml.play();
    }

    void ableAll(){
        counter = 0;
        Timeline tml = new Timeline(new KeyFrame(Duration.millis(1), e->{
            if(labelsList.get(counter).getTextFill() != Color.GREEN){
                buttonsList.get(counter).setDisable(false);
            }
            counter++;
        }));
        tml.setCycleCount(16);
        tml.setAutoReverse(false);
        tml.play();
    }

    @FXML
    void open(ActionEvent event) throws SQLException {
        Button thisButton = (Button) event.getSource();
        int index = buttonsList.indexOf(thisButton);
        labelsList.get(index).setVisible(true);
        thisButton.setVisible(false);
        thisButton.setDisable(true);
        if(isFirst){
            firstAnimal = labelsList.get(index).getText();
            lastLabel = labelsList.get(index);
            lastButton = thisButton;
            isFirst = false;
        }else{

            String secondAnimal = labelsList.get(index).getText();
            if(!firstAnimal.equals(secondAnimal)){
                disableAll();
                final int[] cycles = {0};
                Timeline tml = new Timeline(new KeyFrame(Duration.millis(240), e->{
                    if(cycles[0] > 0){
                        thisButton.setVisible(true);
                        lastButton.setVisible(true);
                        lastLabel.setVisible(false);
                        labelsList.get(index).setVisible(false);
                        ableAll();
                    }else{
                        cycles[0]++;
                    }
                }));
                tml.setCycleCount(2);
                tml.setAutoReverse(false);;
                tml.play();
            }else{
                lastLabel.setTextFill(Color.GREEN);
                labelsList.get(index).setTextFill(Color.GREEN);
                ableAll();
                score+=2;
                if(score == 16){
                    total += 16;
                    startButton.setText("Restart");
                    startButton.setDisable(false);
                    psql.setScore(total);
                    scoreLabel.setText("Score: " + total);
                }
            }
            isFirst = true;
        }
    }

    void resetLabels(){
        counter1 = 0;
        Timeline tml = new Timeline(new KeyFrame(Duration.millis(1), e->{
            labelsList.get(counter1).setTextFill(Color.BLACK);
            labelsList.get(counter1).setVisible(false);
            counter1++;
        }));
        tml.setCycleCount(16);
        tml.setAutoReverse(false);
        tml.play();
    }

    void setNums(){
        spots = new ArrayList<>();
        spots.add(0);
        spots.add(1);
        spots.add(2);
        spots.add(3);
        spots.add(4);
        spots.add(5);
        spots.add(6);
        spots.add(7);
        spots.add(8);
        spots.add(9);
        spots.add(10);
        spots.add(11);
        spots.add(12);
        spots.add(13);
        spots.add(14);
        spots.add(15);
        animalSpots = new ArrayList<>();
        animalSpots.add(0);
        animalSpots.add(1);
        animalSpots.add(2);
        animalSpots.add(3);
        animalSpots.add(4);
        animalSpots.add(5);
        animalSpots.add(6);
        animalSpots.add(7);
        animalSpots.add(8);
        animalSpots.add(9);
        animalSpots.add(10);
        animalSpots.add(11);
        animalSpots.add(12);
        animalSpots.add(13);
        animalSpots.add(14);
        animalSpots.add(15);
        labelsList = new ArrayList<>();
        labelsList.add(l1);
        labelsList.add(l2);
        labelsList.add(l3);
        labelsList.add(l4);
        labelsList.add(l5);
        labelsList.add(l6);
        labelsList.add(l7);
        labelsList.add(l8);
        labelsList.add(l9);
        labelsList.add(l10);
        labelsList.add(l11);
        labelsList.add(l12);
        labelsList.add(l13);
        labelsList.add(l14);
        labelsList.add(l15);
        labelsList.add(l16);
        buttonsList = new ArrayList<>();
        buttonsList.add(b1);
        buttonsList.add(b2);
        buttonsList.add(b3);
        buttonsList.add(b4);
        buttonsList.add(b5);
        buttonsList.add(b6);
        buttonsList.add(b7);
        buttonsList.add(b8);
        buttonsList.add(b9);
        buttonsList.add(b10);
        buttonsList.add(b11);
        buttonsList.add(b12);
        buttonsList.add(b13);
        buttonsList.add(b14);
        buttonsList.add(b15);
        buttonsList.add(b16);
    }

    public void setButtons(){
        score = 0;
        startButton.setDisable(true);
        setNums();
        restartButtons();
        resetLabels();
        Random rnd = new Random();
        Timeline tml = new Timeline(new KeyFrame(Duration.millis(5), e ->{
            int s = rnd.nextInt(spots.size());
            int a = rnd.nextInt(animalSpots.size());
            labelsList.get(spots.get(s)).setText(animals[animalSpots.get(a)]);
            spots.remove(spots.get(s));
            animalSpots.remove(animalSpots.get(a));
        }));
        tml.setCycleCount(16);
        tml.setAutoReverse(false);
        tml.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        img = new ImageView("logo.png");
    }
}
