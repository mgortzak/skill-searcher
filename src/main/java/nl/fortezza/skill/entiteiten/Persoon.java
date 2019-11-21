package nl.fortezza.skill.entiteiten;

import lombok.Builder;

@Builder
public class Persoon {

    String voornaam;
    String naam;
    String[] vaardigheden;
}
