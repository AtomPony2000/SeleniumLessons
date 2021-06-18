package project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//form[@id='login-form']")
    private WebElement loginFormWindow;

    @FindBy(xpath = "//input[@id='prependedInput']")
    private WebElement loginRow;

    @FindBy(xpath = "//input[@id='prependedInput2']")
    private WebElement passwordRow;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement subtitle;

    public void enterLoginAndPassword(String login, String password) {
        wait.until(visibilityOf(loginFormWindow));
        loginRow.sendKeys(login);
        passwordRow.sendKeys(password);
    }

    public void submitClick() {
        submitBtn.click();
        wait.until(visibilityOf(subtitle));
    }
}
