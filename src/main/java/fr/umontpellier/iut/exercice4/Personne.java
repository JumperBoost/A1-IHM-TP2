package fr.umontpellier.iut.exercice4;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne {

    private final String nom;
    private final IntegerProperty age;
    private final StringProperty villeDeNaissance;

    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = new SimpleIntegerProperty(age);
        this.villeDeNaissance = new SimpleStringProperty("Paris");
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getVilleDeNaissance() {
        return villeDeNaissance.get();
    }

    public StringProperty villeDeNaissanceProperty() {
        return villeDeNaissance;
    }

    public void setVilleDeNaissance(String villeDeNaissance) {
        this.villeDeNaissance.set(villeDeNaissance);
    }
}
