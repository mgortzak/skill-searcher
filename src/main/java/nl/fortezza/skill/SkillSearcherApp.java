package nl.fortezza.skill;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class SkillSearcherApp {

    public static final String ROOT = "/";

    public static void main(String[] args) {
        staticFileLocation("/public");

        get(ROOT, Controllers::serveHomePage);
    }
}
