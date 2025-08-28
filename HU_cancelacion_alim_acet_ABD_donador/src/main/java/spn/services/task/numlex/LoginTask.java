package spn.services.task.numlex;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;
import spn.services.utils.ExcelUserData;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static spn.services.ui.numlex.LoginUi.*;

public class LoginTask implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, String>> dataTestUser;
        try {
            dataTestUser = ExcelUserData.leerDatos("src/test/resources/data/testUser.xlsx", "testUser");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        actor.attemptsTo(
                Enter.theValue(dataTestUser.get(0).get("user")).into(TXT_username),
                Enter.theValue(dataTestUser.get(0).get("password")).into(TXT_password),
                Click.on(BTN_login)

        );
    }

    public static LoginTask on(){
        return new LoginTask();
    }
}
