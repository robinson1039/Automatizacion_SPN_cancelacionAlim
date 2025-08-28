package spn.services.task.numlex;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static spn.services.ui.numlex.NipUi.BTN_logout;

public class LogoutTask implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_logout)
        );
    }

    public static LogoutTask on (){
        return new LogoutTask();
    }
}
