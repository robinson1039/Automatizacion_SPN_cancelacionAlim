package spn.services.task.numlex;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static spn.services.ui.numlex.SolicitudesUi.BTN_NIP;

public class SolicitudesTask implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_NIP)
        );
    }
    public static SolicitudesTask on(){
        return new SolicitudesTask();
    }
}
