package vn.locdt.jquestion.element.question;

import org.jline.reader.LineReader;
import vn.locdt.jquestion.util.CommonUtils;

public class InputQuestion extends BaseInputQuestion<String> {

    public InputQuestion(LineReader reader, String title) {
        super(reader, title);
    }

    @Override
    protected String convertInput(String inputValue) {
        return CommonUtils.isEmpty(inputValue) ? "" : inputValue;
    }
}
