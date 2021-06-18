package project.steps;


import io.qameta.allure.Step;
import project.pages.LoginPage;


public class LoginSteps {

    final LoginPage loginPage = new LoginPage();

    @Step("Страница авторизации: вводим логин: {0} и пароль: {1}")
    public LoginSteps loginWithParametres(String login, String password) {
        loginPage.enterLoginAndPassword(login, password);

        return new LoginSteps();
    }

    @Step("Страница авторизации: нажимаем кнопку подтвердить")
    public MainPageSteps submitBtnClick() {
        loginPage.submitClick();

        return new MainPageSteps();
    }
}
