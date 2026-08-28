package hellofx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class WeatherApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather Chooser");

        // Weather dropdown
        ComboBox<String> weatherComboBox = new ComboBox<>();
        weatherComboBox.getItems().addAll(
                "Sunny",
                "Rainy",
                "Cloudy",
                "Snowy"
        );
        weatherComboBox.setPromptText("Select Weather");

        // Style dropdown
        weatherComboBox.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #4a90e2;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-font-size: 14px;"
        );

        // Go button
        Button goButton = new Button("Go");

        // Style Go button
        goButton.setStyle(
                "-fx-background-color: #4a90e2;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 8 20 8 20;" +
                        "-fx-cursor: hand;"
        );

        // Main layout
        HBox hBox = new HBox(10, weatherComboBox, goButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20));

        // Style main background
        hBox.setStyle(
                "-fx-background-color: #eaf4ff;"
        );

        Scene scene = new Scene(hBox, 300, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Go button action
        goButton.setOnAction(e -> {
            String selectedWeather = weatherComboBox.getValue();

            if (selectedWeather != null && !selectedWeather.isEmpty()) {
                String message = getMessageForWeather(selectedWeather);
                openSecondaryWindow(
                        primaryStage,
                        selectedWeather,
                        message
                );
            } else {
                openSecondaryWindow(
                        primaryStage,
                        "No Selection",
                        "Please select a weather option."
                );
            }
        });
    }

    private String getMessageForWeather(String weather) {

        switch (weather) {

            case "Sunny":
                return "It's a Sunny day! Don't forget to bring sunscreen and sunglasses.";

            case "Rainy":
                return "It's a Rainy day! Don't forget to bring an umbrella.";

            case "Cloudy":
                return "It's a Cloudy day! Make sure to wear a light jacket.";

            case "Snowy":
                return "It's a Snowy day! Make sure to stay warm.";

            default:
                return "No weather option selected.";
        }
    }

    private void openSecondaryWindow(
            Window owner,
            String title,
            String message) {

        Stage secondaryStage = new Stage();

        secondaryStage.setTitle(
                "Weather warning: " + title
        );

        secondaryStage.initModality(
                Modality.APPLICATION_MODAL
        );

        secondaryStage.initOwner(owner);

        // Message
        Label messageLabel = new Label(message);

        messageLabel.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-text-fill: #1e293b;" +
                        "-fx-font-weight: bold;" +
                        "-fx-wrap-text: true;"
        );

        // Close button
        Button closeButton = new Button("Close");

        closeButton.setStyle(
                "-fx-background-color: #64748b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 8 18 8 18;" +
                        "-fx-cursor: hand;"
        );

        closeButton.setOnAction(
                e -> secondaryStage.close()
        );

        // Secondary layout
        VBox layout = new VBox(
                10,
                messageLabel,
                closeButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        // Style secondary background
        layout.setStyle(
                "-fx-background-color: #f8fafc;"
        );

        Scene scene = new Scene(layout, 360, 120);

        secondaryStage.setScene(scene);
        secondaryStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}