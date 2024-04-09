package com.example.demo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class LesothoTrivia extends Application {

    private int questionIndex = 0;
    private String[] questions = {
            "What is the capital city of Lesotho?",
            "What is the highest point in Lesotho?",
            "Which river forms part of the border between Lesotho and South Africa?",
            "What is the traditional Basotho blanket called?",
            "What is the name of the national park in Lesotho known for its dinosaur footprints?"
    };
    private String[][] options = {
            {"Maseru", "Leribe", "Mokhotlong", "Butha-Buthe"},
            {"Thabana Ntlenyana", "Mount Qiloane", "Njesuthi", "Tsoelike"},
            {"Orange River", "Vaal River", "Caledon River", "Lepelle River"},
            {"Sesotho", "Mokorotlo", "Bogolan", "Kente"},
            {"Sehlabathebe National Park", "Ts'ehlanyane National Park", "Bokong Nature Reserve", "Sequoia National Park"}
    };
    private String[] correctAnswers = {"Maseru", "Thabana Ntlenyana", "Caledon River", "Mokorotlo", "Ts'ehlanyane National Park"};

    private Label questionLabel;
    private Button[] optionButtons;

    @Override
    public void start(Stage primaryStage) {
        questionLabel = new Label();
        questionLabel.setWrapText(true);

        ImageView imageView = new ImageView(new Image("lesotho.jpg"));
        imageView.setFitWidth(400);
        imageView.setFitHeight(300);

        HBox imageBox = new HBox(imageView);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(10));

        optionButtons = new Button[4];
        for (int i = 0; i < 4; i++) {
            final int optionIndex = i;
            optionButtons[i] = new Button();
            optionButtons[i].setOnAction(event -> checkAnswer(optionIndex));
            optionButtons[i].setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-cursor: hand;");
        }

        VBox root = new VBox(10, imageBox, questionLabel, optionButtons[0], optionButtons[1], optionButtons[2], optionButtons[3]);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f2f2f2;");

        Scene scene = new Scene(root, 600, 500);

        // Load CSS file
        File cssFile = new File("style.css");
        scene.getStylesheets().add(cssFile.toURI().toString());

        primaryStage.setTitle("Lesotho Trivia Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        displayQuestion();
    }

    private void displayQuestion() {
        questionLabel.setText(questions[questionIndex]);
        // Load corresponding image for the current question
        ImageView imageView = new ImageView(new Image("question" + questionIndex + ".jpg"));
        imageView.setFitWidth(400);
        imageView.setFitHeight(300);
        ((HBox)((VBox) questionLabel.getParent()).getChildren().get(0)).getChildren().setAll(imageView);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[questionIndex][i]);
        }
    }

    private void checkAnswer(int optionIndex) {
        if (options[questionIndex][optionIndex].equals(correctAnswers[questionIndex])) {
            showFeedback("Correct!", "green");
            // Move to the next question if the answer is correct
            questionIndex++;
            // Check if all questions have been answered
            if (questionIndex < questions.length) {
                // Display the next question
                displayQuestion();
            } else {
                // End of the game
                showFeedback("Congratulations! You have completed all questions.", "blue");
            }
        } else {
            showFeedback("Incorrect. Try again.", "red");
        }
    }

    private void showFeedback(String message, String color) {
        Label feedbackLabel = new Label(message);
        feedbackLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: " + color + ";");
        ((VBox) questionLabel.getParent()).getChildren().add(feedbackLabel);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
