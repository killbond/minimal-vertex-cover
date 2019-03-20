package solution;

import javafx.application.Application;
import algorithm.Algorithm;
import javafx.stage.Stage;
import problem.Problem;

import java.util.List;

public class Solution extends Application  {

    private Problem problem;

    private Algorithm algorithm;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            problem = new Problem();
            algorithm = new Algorithm(problem);
            List<Integer> cover = algorithm.solve();

            StringBuilder output = new StringBuilder("\n vertex cover is {");
            for(int vertex : cover) output.append(" ").append(vertex + 1).append(",");
            output.deleteCharAt(output.length() - 1);
            output.append(" }");

            System.out.println(output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
