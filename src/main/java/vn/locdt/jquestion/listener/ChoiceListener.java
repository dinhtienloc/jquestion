package vn.locdt.jquestion.listener;

import vn.locdt.jquestion.event.ChangeSelectorEvent;
import vn.locdt.jquestion.event.ChooseSelectorEvent;

public interface ChoiceListener extends Listener{
    void onChanged(ChangeSelectorEvent e);
    void onChosen(ChooseSelectorEvent e);
}
