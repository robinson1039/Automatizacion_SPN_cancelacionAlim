package spn.services.ui.numlex;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SolicitudesUi {
    public static final Target BTN_NIP = Target.the("click on button NIP")
            .located(By.xpath("//*[@id=\"accordion-body-0\"]/div[6]/a"));
}
