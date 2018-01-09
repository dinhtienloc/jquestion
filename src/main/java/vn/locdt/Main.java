package vn.locdt;

import com.google.gson.Gson;
import vn.locdt.exception.ConsoleNotInitializeException;
import vn.locdt.question.SingleChoiceQuestion;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            JQuestion jQuestion = new JQuestion();

            jQuestion
                .addInputQuestion("Mày học lớp nào?", "group")
                .addSingleChoiceQuestion(new SingleChoiceQuestion("Mày muốn gì?", "want")
                    .addSelectors("Éo nói", "Không tạch môn", "Muốn ôn thi", "Nói nhiều quá"));

            Map<String, String> resultMap = jQuestion.prompt();
            Gson gson = new Gson();
            System.out.println(gson.toJson(resultMap));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
    }
}
