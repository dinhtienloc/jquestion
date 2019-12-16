package vn.locdt.jquestion.element.item.autocomplete;

import java.util.List;

public interface SourceHandler<T> {

    List<T> load(String input);

    String convert(T element);
}
