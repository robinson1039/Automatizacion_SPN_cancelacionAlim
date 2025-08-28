package spn.services.ui.numlex;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginUi {
    public static final Target TXT_username = Target.the("text input username")
            .located(By.id("username"));
    public static final Target TXT_password = Target.the("text input password")
            .located(By.id("password"));
    public static final Target BTN_login = Target.the("click on button login")
            .located(By.xpath("/html/body/app-root/app-login/mat-card/mat-card-content/form/mat-card-actions/button"));

}
