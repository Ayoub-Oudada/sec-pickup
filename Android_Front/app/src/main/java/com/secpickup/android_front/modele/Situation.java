package com.secpickup.android_front.modele;




import java.util.List;

 enum Situations {
    DEPOSER,
    RECUPPERER,
    ABSENT
}


public class Situation extends BaseEntity {

    private Situations situation;


    private List<Eleve> eleves;

    public List<Eleve> getEleves() {
        return eleves;
    }

    public Situations getSituation() {
        return situation;
    }

    @Override
    public String toString() {
        return "Situation{" +
                "situation=" + situation +
                '}';
    }

    public void setSituation(Situations situation) {
        this.situation = situation;
    }
}
