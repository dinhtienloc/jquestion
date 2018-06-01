package vn.locdt.jquestion;

import org.fusesource.jansi.AnsiConsole;
import vn.locdt.jquestion.element.question.MultipleChoiceQuestion;
import vn.locdt.jquestion.event.ChangeSelectorEvent;
import vn.locdt.jquestion.event.ChooseSelectorEvent;
import vn.locdt.jquestion.exception.ConsoleNotInitializeException;
import vn.locdt.jquestion.listener.ChoiceListener;

import java.io.IOException;
import java.util.Arrays;


public class Main {
    public enum DBType {
        MYSQL("MySQL"),
        ORACLE("Oracle");
        private String type;
        DBType(String type) {
            this.type = type;
        }
        public String getType() {return this.type;}
    }

    public static String[] getDatabaseTypes() {
        return Arrays.stream(DBType.values()).map(DBType::getType).toArray(String[]::new);
    }

    public static void main(String[] args) {
        try {
//            JQuestion.input("What do you want")
//                    .name("want")
//                    .prompt();
//
//			JQuestion.confirm("What do you want")
//					.name("want").prompt();
//            JQuestion.singleChoice("What do you want")
//                    .name("want")
//                    .addSelector("Apple").addSelector("Banana", true)
//                    .addSelectors("Kiwi", "Orange", "Pineapple")
//                    .prompt();
            JQuestion.multipleChoice("What do you want")
                    .name("want")
                    .addSelector("Apple").addActiveSelector("Banana", true)
                    .addSelectors("Kiwi", "Orange", "Pineapple")
					.prompt();
        } catch (IOException | ConsoleNotInitializeException e) {
            e.printStackTrace();
        }
    }

}

