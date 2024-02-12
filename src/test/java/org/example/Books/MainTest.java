package org.example.Books;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$;

public class MainTest {

    @BeforeClass
    public void beforeClass() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    public void booksCanBeFiltered1() {
        Selenide.open("/books");
        $("input").setValue("Silver");
        $("input").pressEnter();
        ElementsCollection rows = $(".rt-table").$$(".rt-tr-group");
        rows.shouldHave(texts("Silver").because("unknown reason"));
    }

    @Test
    public void booksCanBeFiltered2() {
        Selenide.open("/books");
        $("input").setValue("Silver");
        $("input").pressEnter();
        ElementsCollection rows = $(".rt-table").$$(".rt-tr-group").filterBy(Condition.visible);
        rows.shouldHave(texts("Silver").because("unknown reason"));
    }

    @Test
    public void booksCanBeFiltered3() {
        Selenide.open("/books");
        $("input").setValue("Silver");
        $("input").pressEnter();
        ElementsCollection rows = $(".rt-table").$$(".rt-tr-group").filterBy(Condition.visible).filterBy(Condition.text("Silver"));
        rows.shouldHave(texts("Silver").because("unknown reason"));
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}
