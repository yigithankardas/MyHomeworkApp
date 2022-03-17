package odev;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "This program takes an array of integers and two integer discretely and check if the average of that array is in the range between two integers.");

        post("/compute", (req, res) -> {
          String input1 = req.queryParams("input1");
          Scanner scan = new Scanner(input1);
          scan.useDelimiter("[;\r\n]+");
          ArrayList<Integer> inputList = new ArrayList<>();
          while (scan.hasNext()) {
            int value = Integer.parseInt(scan.next().replaceAll("\\s",""));
            inputList.add(value);
          }
          scan.close();

          String input2 = req.queryParams("input2");
          scan = new Scanner(input2);
          scan.useDelimiter("[;\r\n]+");
          int a = Integer.parseInt(scan.next().replaceAll("\\s", ""));
          scan.close();

          String input3 = req.queryParams("input3");
          scan = new Scanner(input3);
          scan.useDelimiter("[;\r\n]+");
          int b = Integer.parseInt(scan.next().replaceAll("\\s", ""));
          scan.close();

          Integer[] arr = new Integer[inputList.size()];
          for (int i = 0; i < inputList.size(); i++)
              arr[i] = inputList.get(i);

          boolean result = App.isItsAverageInRangeBetween(arr, a, b);

          Map<String, Boolean> map = new HashMap<String, Boolean>();
          map.put("result", result);
          return new ModelAndView(map, "average.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
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


    public static boolean isItsAverageInRangeBetween(Integer[] array, int a, int b) {
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