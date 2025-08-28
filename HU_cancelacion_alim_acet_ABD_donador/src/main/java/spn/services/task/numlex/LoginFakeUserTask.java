package spn.services.task.numlex;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import spn.services.utils.ExcelUserData;

import java.util.List;
import java.util.Map;

import static spn.services.ui.numlex.LoginUi.*;

public class LoginFakeUserTask implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, String>> dataFakeUser;
        try {
            dataFakeUser = ExcelUserData.leerDatos("src/test/resources/data/fakeUser.xlsx", "fakeUser");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        actor.attemptsTo(
                Enter.theValue(dataFakeUser.get(0).get("fakeuser")).into(TXT_username),
                Enter.theValue(dataFakeUser.get(0).get("password")).into(TXT_password),
                Click.on(BTN_login)

        );
    }
    public static LoginFakeUserTask on(){
        return new LoginFakeUserTask();
    }
}
