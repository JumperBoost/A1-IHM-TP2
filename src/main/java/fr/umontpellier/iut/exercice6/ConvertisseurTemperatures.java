package fr.umontpellier.iut.exercice6;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
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
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    private DoubleProperty celsiusProperty;
    private DoubleProperty fahrenheitProperty;

    @Override
    public void start(Stage primaryStage) {
        celsiusProperty = new SimpleDoubleProperty(0);
        fahrenheitProperty = new SimpleDoubleProperty(32);

        Label labelCelsius = new Label("°C");
        labelCelsius.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Slider sliderCelsius = new Slider(0, 100, 0);
        setSliderAttributes(sliderCelsius);
        sliderCelsius.valueProperty().bindBidirectional(celsiusProperty);
        celsiusProperty.addListener((observableValue, oldValue, newValue) -> {
            if((double) newValue > 100)
                celsiusProperty.setValue(100);
            else if((double) newValue < 0)
                celsiusProperty.setValue(0);
            else fahrenheitProperty.setValue(((double) newValue * 9/5) + 32);
        });
        TextField fieldCelsius = new TextField();
        fieldCelsius.textProperty().bindBidirectional(celsiusProperty, new NumberStringConverter("###.##"));
        fieldCelsius.setPrefWidth(50);
        VBox panneauCelsius = new VBox(30, labelCelsius, sliderCelsius, fieldCelsius);
        panneauCelsius.setAlignment(Pos.CENTER);

        Label labelFahrenheit = new Label("°F");
        labelFahrenheit.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Slider sliderFahrenheit = new Slider(0, 212, 32);
        setSliderAttributes(sliderFahrenheit);
        sliderFahrenheit.valueProperty().bindBidirectional(fahrenheitProperty);
        ChangeListener<Number> fahrenheitListener = (observableValue, oldValue, newValue) -> {
            if((double) newValue < 32)
                fahrenheitProperty.setValue(32);
            else if((double) newValue > 212)
                fahrenheitProperty.setValue(212);
            else celsiusProperty.setValue(((double) newValue - 32) * 5/9);
        };
        sliderFahrenheit.valueProperty().addListener(fahrenheitListener);
        TextField fieldFahrenheit = new TextField();
        fieldFahrenheit.textProperty().bindBidirectional(fahrenheitProperty, new NumberStringConverter("###.##"));
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