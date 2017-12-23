package vn.locdt.listener;

import vn.locdt.event.ChangeSelectorEvent;
import vn.locdt.event.ChooseSelectorEvent;

public interface ChoiceListener extends Listener{
    void onChanged(ChangeSelectorEvent e);
    void onChosen(ChooseSelectorEvent e);
}
