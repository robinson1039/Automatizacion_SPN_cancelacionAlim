package spn.services.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class NoPrivatePageMessage {
    public static final Target BTN_avanzado = Target.the("click on button avanzado")
            .located(By.id("details-button"));
    public static final Target BTN_proceedLink = Target.the("click on button proceed link")
            .located(By.id("proceed-link"));
}
