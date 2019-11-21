package nl.fortezza.skill;


import static spark.Spark.get;

public class SkillSearcherApp {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}
