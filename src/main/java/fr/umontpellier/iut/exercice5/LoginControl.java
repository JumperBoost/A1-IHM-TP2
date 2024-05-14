package fr.umontpellier.iut.exercice5;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    private StringProperty userIdProperty;
    private StringProperty pwdProperty;

    @FXML
    private void initialize() {
        userIdProperty = new SimpleStringProperty("");
        pwdProperty = new SimpleStringProperty("");
        createBindings();
    }

    private void createBindings() {
        userId.textProperty().bindBidirectional(userIdProperty);
        pwd.textProperty().bindBidirectional(pwdProperty);
        pwd.editableProperty().bind(Bindings.greaterThanOrEqual(userIdProperty.length(), 6));
    }

    private boolean checkIfPwdIsValid() {
        if(pwdProperty.get().length() < 8)
            return false;

        boolean hasUpperCase = false;
        boolean hasDigit = false;
        for(char c : pwdProperty.get().toCharArray()) {
            if(Character.isUpperCase(c))
                hasUpperCase = true;
            else if(Character.isDigit(c))
                hasDigit = true;
        }
        return hasUpperCase && hasDigit;
    }

    @FXML
    private void okClicked() {
        if(!checkIfPwdIsValid())
            return;

        System.out.print(userIdProperty.get() + " ");
        for (char c : pwdProperty.get().toCharArray())
            System.out.print("*");

        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        if(userIdProperty.get().isEmpty() && pwdProperty.get().isEmpty())
            return;

        userIdProperty.set("");
        pwdProperty.set("");
    }
}