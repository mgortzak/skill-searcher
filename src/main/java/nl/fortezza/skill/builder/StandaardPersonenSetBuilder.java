package nl.fortezza.skill.builder;

import nl.fortezza.skill.entiteiten.Persoon;

import java.util.ArrayList;
import java.util.Collection;

public final class StandaardPersonenSetBuilder {

    private StandaardPersonenSetBuilder() {
        // Geen default constructor gebruiken.
    }

    public static Collection<Persoon> getTestPersonen() {
        Collection<Persoon> personen = new ArrayList<>();
        personen.add(Persoon.builder()
                .naam("Jansen")
                .voornaam("Jan")
                .vaardigheden(new String[]{"Java", "SQL"})
                .build()
        );
        personen.add(Persoon.builder()
                .naam("Pietersen")
                .voornaam("Piet")
                .vaardigheden(new String[]{"Kotlin", "Javascript"})
                .build()
        );

        return personen;
    }
}
