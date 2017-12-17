package vn.locdt;

import vn.locdt.question.ChoiceQuestion;
import vn.locdt.question.InputQuestion;
import vn.locdt.util.ConsoleUtils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            JQuestion.createConsole();

            String input = new InputQuestion("How old are you?", "age").getValue();

            new ChoiceQuestion("Day la choice list", "eat")
                    .addSelector("an rau den khong?")
                    .addSelector("an ca khong?", true)
                    .addSelector("danh nhau khong?")
                    .addSelector("hoc bai khong?")
                    .prompt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
