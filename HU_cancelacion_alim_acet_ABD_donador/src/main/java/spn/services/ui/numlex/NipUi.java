package spn.services.ui.numlex;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class NipUi {
    public static final Target TXT_NIP = Target.the("text NIP")
            .located(By.cssSelector("tbody > tr:first-child > td:nth-child(3)"));
    public static final Target TXT_CELLPHONE = Target.the("text cellphone")
            .located(By.cssSelector("tbody > tr:first-child > td:nth-child(1)"));
    public static final Target BTN_logout = Target.the("click in the button logout")
            .located(By.cssSelector("button[aria-label=\"Logout\"]"));
}
