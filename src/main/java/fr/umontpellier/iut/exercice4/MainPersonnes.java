package fr.umontpellier.iut.exercice4;

import javafx.beans.Observable;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

@SuppressWarnings("Duplicates")
public class MainPersonnes {

    private static SimpleListProperty<Personne> lesPersonnes;
    private static IntegerProperty ageMoyen;
    private static IntegerProperty nbParisiens;

    private static IntegerBinding calculAgeMoyen;
    private static IntegerBinding calculnbParisiens;

    public static void main(String[] args) {

        lesPersonnes = new SimpleListProperty<>(FXCollections.observableArrayList(personne -> new Observable[] {personne.ageProperty(), personne.villeDeNaissanceProperty()}));
        calculAgeMoyen = new IntegerBinding() {
            {
                super.bind(lesPersonnes);
            }

            @Override
            protected int computeValue() {
                int ageCumul = 0;
                for(Personne p : lesPersonnes.getValue())
                    ageCumul += p.getAge();
                return !lesPersonnes.isEmpty() ? ageCumul / lesPersonnes.size() : 0;
            }
        };
        calculnbParisiens = new IntegerBinding() {
            {
                super.bind(lesPersonnes);
            }

            @Override
            protected int computeValue() {
                return (int) lesPersonnes.stream().filter(p -> p.getVilleDeNaissance().equals("Paris")).count();
            }
        };

        ageMoyen = new SimpleIntegerProperty();
        ageMoyen.bind(calculAgeMoyen);
        nbParisiens = new SimpleIntegerProperty();
        nbParisiens.bind(calculnbParisiens);

        question1();
        question2();
    }

    public static void question1() {
        System.out.println("1 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne pierre = new Personne("Pierre", 20);
        lesPersonnes.add(pierre);
        System.out.println("2 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne paul = new Personne("Paul", 40);
        lesPersonnes.add(paul);
        System.out.println("3 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(jacques);
        System.out.println("4 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        paul.setAge(100);
        System.out.println("5 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        lesPersonnes.remove(paul);
        System.out.println("6 - L'age moyen est de " + ageMoyen.getValue() + " ans");
    }

    public static void question2() {
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
        lesPersonnes.get(0).setVilleDeNaissance("Marseille");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
        lesPersonnes.get(1).setVilleDeNaissance("Montpellier");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisien");
        for (Personne p : lesPersonnes)
            p.setVilleDeNaissance("Paris");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
    }

}

