package fr.umontpellier.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label labelCelsius = new Label("°C");
        labelCelsius.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Slider sliderCelsius = new Slider(0, 100, 0);
        setSliderAttributes(sliderCelsius);
        TextField fieldCelsius = new TextField("0,00");
        fieldCelsius.setPrefWidth(50);
        VBox panneauCelsius = new VBox(30, labelCelsius, sliderCelsius, fieldCelsius);
        panneauCelsius.setAlignment(Pos.CENTER);

        Label labelFahrenheit = new Label("°F");
        labelFahrenheit.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Slider sliderFahrenheit = new Slider(0, 212, 32);
        setSliderAttributes(sliderFahrenheit);
        TextField fieldFahrenheit = new TextField("32,00");
        fieldFahrenheit.setPrefWidth(50);
        VBox panneauFahrenheit = new VBox(30, labelFahrenheit, sliderFahrenheit, fieldFahrenheit);
        panneauFahrenheit.setAlignment(Pos.CENTER);

        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void setSliderAttributes(Slider slider) {
        slider.setOrientation(Orientation.VERTICAL);
        slider.setPrefHeight(452);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(10);
    }

}