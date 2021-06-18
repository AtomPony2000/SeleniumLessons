package project.steps;

import io.qameta.allure.Step;
import project.pages.MainPage;

public class MainPageSteps {

    final MainPage mainPage = new MainPage();

    @Step("Грид: выбираем 'Расходы'")
    public MainPageSteps clickCost() {
        mainPage.costsClick();

        return this;
    }


    @Step("Грид: выбираем 'Командировки' в выпадающем списке")
    public MainPageSteps clickAssigment() {
        mainPage.assignmentClick();

        return this;
    }

    @Step("Грид: ловим ошибку")
    public MainPageSteps checkNotNull() {
        mainPage.checkSmth();

        return this;
    }
}
