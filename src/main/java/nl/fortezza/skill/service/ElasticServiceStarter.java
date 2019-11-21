package nl.fortezza.skill.service;

import static spark.Spark.get;

public class ElasticServiceStarter {

    private ElasticServiceStarter() {
        // Geen instanties aanmaken.
    }

    public static void main(String[] args) {
        get("/personen", (req, res) -> {
            return ElasticService.getAllData();
        });
    }
}