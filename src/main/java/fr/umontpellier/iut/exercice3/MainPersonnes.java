package fr.umontpellier.iut.exercice3;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@SuppressWarnings("Duplicates")
public class MainPersonnes  {

    private static ObservableList<Personne> lesPersonnes;

    private static ListChangeListener<Personne> unChangementListener;

    public static void main(String[] args) {
        lesPersonnes = FXCollections.observableArrayList(personne -> new Observable[] {personne.ageProperty()});

        unChangementListener = new ListChangeListener<Personne>() {
            @Override
            public void onChanged(Change<? extends Personne> change) {
                while(change.next()) {
                    if(change.wasAdded()) {
                        for(Personne p : change.getAddedSubList())
                            System.out.println(p.getNom() + " a été ajouté");
                    } else if(change.wasRemoved()) {
                        for(Personne p : change.getRemoved())
                            System.out.println(p.getNom() + " a été enlevé");
                    } else if(change.wasUpdated()) {
                        for(int i = change.getFrom(); i < change.getTo(); i++) {
                            Personne p = change.getList().get(i);
                            System.out.println(p.getNom() + " a maintenant " + p.getAge() + " ans");
                        }
                    }
                }
            }
        };
        lesPersonnes.addListener(unChangementListener);
        question3();
    }

    public static void question1() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.remove(paul);
        lesPersonnes.remove(pierre);
    }

    public static void question3() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        paul.setAge(5);
        jacques.setAge(8);
    }

    public static void question5() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.addAll(pierre, paul, jacques);
        for (Personne p : lesPersonnes)
            p.setAge(p.getAge()+10);
        lesPersonnes.removeAll(paul, pierre);
    }
}

