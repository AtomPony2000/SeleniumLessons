package LoginTests;

import extension.AllureExtension;
import extension.DriverExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import project.steps.LoginSteps;

import java.util.Properties;

import static project.properties.TestProperties.getInstance;

@DisplayName(value = "Сценарий для allure репорта")
@ExtendWith({DriverExtension.class, AllureExtension.class})
class SecondTest {

    private final LoginSteps loginSteps = new LoginSteps();
    private final Properties properties = getInstance().getProperties();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка авторизации c подстановкой данных")
    @TmsLink("Test #123")
    @Link("http://training.appline.ru/user/login")
    void LoginTest() {
        loginSteps
                .loginWithParametres(
                        properties.getProperty("LOGIN"),
                        properties.getProperty("PASSWORD"))
                .submitBtnClick()
                .clickCost()
                .clickAssigment()
                .checkNotNull();
    }
}
