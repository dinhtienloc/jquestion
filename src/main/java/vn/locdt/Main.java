package vn.locdt;

import vn.locdt.question.ChoiceQuestion;
import vn.locdt.question.InputQuestion;
import vn.locdt.result.ResultHandler;
import vn.locdt.util.ConsoleUtils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            JQuestion.createConsole();

            InputQuestion q1 = new InputQuestion("How old are you?", "age");
            q1.setPrintedResult(true);
            ResultHandler r1 = q1.prompt();
            System.out.println(r1);

            ChoiceQuestion q2 = new ChoiceQuestion("Day la choice list", "eat")
                    .addSelector("an rau den khong?")
                    .addSelector("an ca khong?", true)
                    .addSelector("danh nhau khong?")
                    .addSelector("hoc bai khong?");
            ResultHandler r2 = q2.prompt();
            System.out.println(r2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
