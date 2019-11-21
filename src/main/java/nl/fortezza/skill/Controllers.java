package nl.fortezza.skill;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Controllers {
    public static ModelAndView serveHomePage (Request req, Response res) {
        return new ModelAndView(null, "index.html");
    }

}
