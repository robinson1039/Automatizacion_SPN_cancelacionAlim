package spn.services.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import spn.services.task.numlex.LoginTask;

import static spn.services.ui.NoPrivatePageMessage.*;
public class NoPrivatePageMessageTask implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_avanzado),
                Click.on(BTN_proceedLink)
        );
    }
    public static NoPrivatePageMessageTask on(){
        return new NoPrivatePageMessageTask();
    }
}
