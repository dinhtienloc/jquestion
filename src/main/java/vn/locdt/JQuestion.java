package vn.locdt;

import jline.console.ConsoleReader;
import vn.locdt.question.InputQuestion;
import vn.locdt.question.Question;
import vn.locdt.question.SingleChoiceQuestion;
import vn.locdt.result.Answer;

import java.io.IOException;
import java.util.*;

public class JQuestion {
    private static ConsoleReader rootConsole;
    private List<Question> questions;

    public JQuestion() {
        this.questions = new ArrayList<>();
    }

    public static ConsoleReader createConsole() {
        try {
            if (JQuestion.rootConsole == null)
                JQuestion.rootConsole = new ConsoleReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JQuestion.rootConsole;
    }

    public static ConsoleReader getConsole() {
        return createConsole();
    }

    public JQuestion addInputQuestion(String title, String name) {
        this.questions.add(new InputQuestion(title, name));
        return this;
    }

    public JQuestion addInputQuestion(InputQuestion inputQuestion) {
        this.questions.add(inputQuestion);
        return this;
    }

    public JQuestion addSingleChoiceQuestion(SingleChoiceQuestion singleChoiceQuestion) {
        this.questions.add(singleChoiceQuestion);
        return this;
    }

    public Map<String, String> prompt() throws IOException {
        Map<String, String> resultMap = new LinkedHashMap<>();

        if (questions.size() == 0)
            return resultMap;

        for (Question q : questions) {
            Answer answer = q.prompt();
            resultMap.put(answer.getName(), answer.getValue());
//            System.out.println(answer);
        }

        return resultMap;
    }
}
