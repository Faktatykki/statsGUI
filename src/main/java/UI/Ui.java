package UI;

import Classes.Platform;
import Logic.Logic;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Ui extends Application {

    private Logic logic;
    private Scanner reader;
    private Stage stage;

    public Ui() throws IOException {
        this.reader = new Scanner(System.in);
        this.logic = new Logic("stats.csv");
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        Scene scene = startingScene();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Scene startingScene() {
        VBox layout = new VBox();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(500, 700);
        layout.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("INFO MENU");
        title.setStyle("-fx-font-weight: bold");

        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(200);
        exitButton.setAlignment(Pos.BOTTOM_CENTER);

        Parent buttonLayout = startingMenuButtonLO();

        layout.getChildren().addAll(title, buttonLayout, exitButton);

        exitButton.setOnAction(e -> {
            javafx.application.Platform.exit();
        });

        stage.setTitle("Main menu");

        return new Scene(layout);
    }

    public Parent startingMenuButtonLO() {
        VBox buttonLayout = new VBox(10);
        buttonLayout.setPadding(new Insets(30, 30, 30, 30));
        buttonLayout.setAlignment(Pos.CENTER);

        Button totalStatsButton = new Button("1. Total statistics");
        totalStatsButton.setPrefWidth(200);
        Button platformsButton = new Button("2. All platforms");
        platformsButton.setPrefWidth(200);
        Button platformMenuButton = new Button("3. Platform menu");
        platformMenuButton.setPrefWidth(200);
        Button countryMenuButton = new Button("4. Country menu");
        countryMenuButton.setPrefWidth(200);
        Button compareButton = new Button("5. Spotify vs. Youtube");
        compareButton.setPrefWidth(200);

        totalStatsButton.setOnMouseClicked((e) -> {
            stage.setScene(totalStatsScene());
        });

        platformsButton.setOnMouseClicked((e) -> {
            stage.setScene(allPlatformsScene());
        });

        platformMenuButton.setOnMouseClicked((e) -> {
            stage.setScene(platformMenuScene());
        });

        countryMenuButton.setOnMouseClicked((e) -> {
            stage.setScene(countryMenuScene());
        });

        compareButton.setOnMouseClicked((e) -> {
            stage.setScene(marcosScene());
        });

        buttonLayout.getChildren().addAll(totalStatsButton, platformsButton, platformMenuButton, countryMenuButton, compareButton);

        return buttonLayout;
    }

    public Scene totalStatsScene() {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(500, 700);

        Button returnButton = new Button("Return");
        returnButton.setAlignment(Pos.BOTTOM_CENTER);
        returnButton.setPrefWidth(300);

        Text totalText = new Text("Total streams: " + logic.getTotalStreams() + "\n\n" +
                                    "Total downloads: " + logic.getTotalDownloads() + "\n\n" +
                                    "Total revenue: " + logic.getTotalMoney() + "\n\n");

        totalText.setStyle("-fx-font-weight: bold");

        layout.getChildren().addAll(totalText, returnButton);

        returnButton.setOnMouseClicked((e) -> {
            stage.setScene(startingScene());
        });

        stage.setTitle("All statistics");

        return new Scene(layout);
    }

    public Scene allPlatformsScene() {
        VBox layout = new VBox(5);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(500, 700);

        for(Platform platf: logic.getPlatforms().values()) {
            Text platfText = new Text(platf.getName());
            platfText.setStyle("-fx-font-weight: bold");
            layout.getChildren().add(platfText);
        }

        Button returnButton = new Button("Return");
        returnButton.setAlignment(Pos.BOTTOM_CENTER);
        returnButton.setPrefWidth(300);

        layout.getChildren().addAll(new Text("\n"), returnButton);

        returnButton.setOnMouseClicked((e) -> {
            stage.setScene(startingScene());
        });

        stage.setTitle("All platforms");
        return new Scene(layout);
    }

    public Scene platformMenuScene() {
        ScrollPane sp = new ScrollPane();

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(500, 700);

        for(Integer platf: logic.getPlatforms().keySet()) {
            Button button = new Button(logic.getPlatform(platf).getName());
            button.setStyle("-fx-font-weight: bold");
            button.setPrefWidth(300);

            button.setOnMouseClicked((e) -> {
                stage.setScene(platformScene(platf));
            });

            layout.getChildren().add(button);
        }

        Button returnButton = new Button("Return");
        returnButton.setAlignment(Pos.BOTTOM_CENTER);
        returnButton.setPrefWidth(300);

        layout.getChildren().addAll(new Text("\n"), returnButton);

        returnButton.setOnMouseClicked((e) -> {
            stage.setScene(startingScene());
        });

        sp.setContent(layout);
        stage.setTitle("Platform menu");

        return new Scene(sp);
    }

    public Scene platformScene(int num) {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(500, 700);

        Text platfText = new Text(logic.getPlatform(num).toString());
        platfText.setStyle("-fx-font-weight: bold");

        Button returnButton = new Button("Return");
        returnButton.setAlignment(Pos.BOTTOM_CENTER);
        returnButton.setPrefWidth(300);

        returnButton.setOnMouseClicked((e) -> {
            stage.setScene(platformMenuScene());
        });

        layout.getChildren().addAll(platfText, returnButton);

        stage.setTitle(logic.getPlatform(num).getName());

        return new Scene(layout);
    }

    public Scene countryScene(int num) {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(500, 700);

        Text platfText = new Text(logic.getCountry(num).toString());
        platfText.setStyle("-fx-font-weight: bold");

        Button returnButton = new Button("Return");
        returnButton.setAlignment(Pos.BOTTOM_CENTER);
        returnButton.setPrefWidth(300);

        returnButton.setOnMouseClicked((e) -> {
            stage.setScene(countryMenuScene());
        });

        layout.getChildren().addAll(platfText, returnButton);

        stage.setTitle(logic.getCountry(num).getName());

        return new Scene(layout);
    }


    public Scene countryMenuScene() {
        ScrollPane sp = new ScrollPane();

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(500, 700);

        for(Integer platf: logic.getCountries().keySet()) {
            Button button = new Button(logic.getCountry(platf).getName());
            button.setStyle("-fx-font-weight: bold");
            button.setPrefWidth(300);

            button.setOnMouseClicked((e) -> {
                stage.setScene(countryScene(platf));
            });

            layout.getChildren().add(button);
        }

        Button returnButton = new Button("Return");
        returnButton.setAlignment(Pos.BOTTOM_CENTER);
        returnButton.setPrefWidth(300);

        layout.getChildren().addAll(new Text("\n"), returnButton);

        returnButton.setOnMouseClicked((e) -> {
            stage.setScene(startingScene());
        });

        sp.setContent(layout);
        stage.setTitle("Country menu");

        return new Scene(sp);
    }

    public Scene marcosScene() {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(500, 700);

        Button returnButton = new Button("Return");
        returnButton.setPrefWidth(300);

        returnButton.setOnMouseClicked((e) -> {
            stage.setScene(startingScene());
        });

        Text spotifyTitle = new Text("Spotify\n");
        spotifyTitle.setStyle("-fx-font-weight: bold");

        Text youtubeTitle = new Text("Youtube\n");
        youtubeTitle.setStyle("-fx-font-weight: bold");

        layout.getChildren().addAll(spotifyTitle, new Text(logic.spotifyStats() + "\n"), youtubeTitle, new Text(logic.youtubeStats() + "\n\n"), returnButton);

        return new Scene(layout);
    }
}