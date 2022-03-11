package odev;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(App.class);

        int port = Integer.parseInt(System.getenv("PORT"));
        port(port);
        logger.error("Current port number:" + port);


        port(getHerokuAssignedPort());

        get("/", (req, res) -> "This program takes an array of integers and two integer discretely and check if the average of that array is in the range between two integers.");

        post("/average", (req, res) -> {
          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          sc1.useDelimiter("[;\r\n]+");
          ArrayList<Integer> inputList = new ArrayList<>();
          while (sc1.hasNext()) {
            int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
            inputList.add(value);
          }
          sc1.close();

          String input2 = req.queryParams("input2");
          sc1 = new java.util.Scanner(input2);
          sc1.useDelimiter("[;\r\n]+");
          int a = Integer.parseInt(sc1.next().replaceAll("\\s", ""));
          int b = Integer.parseInt(sc1.next().replaceAll("\\s", ""));
          sc1.close();

          int[] arr = new int[inputList.size()];
          for (int i = 0; i < inputList.size(); i++)
              arr[i] = inputList.get(i);

          boolean result = App.isItsAverageInRangeBetween(arr, a, b);

          Map<String, Boolean> map = new HashMap<String, Boolean>();
          map.put("result", result);
          return new ModelAndView(map, "average.mustache");
        }, new MustacheTemplateEngine());


        get("/average",
            (rq, rs) -> {
              Map<String, String> map = new HashMap<String, String>();
              map.put("result", "not computed yet!");
              return new ModelAndView(map, "average.mustache");
            },
            new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


    public static boolean isItsAverageInRangeBetween(int[] array, int a, int b) {
        if (array == null)
            return false;
        if (array.length == 0)
            return false;
        
        int total = 0;
        for (int i = 0; i < array.length; i++)
            total += array[i];
        double average = (double)total / array.length;
        return average >= a && average <= b;
    }
}