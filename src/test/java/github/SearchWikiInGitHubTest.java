package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class SearchWikiInGitHubTest {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void checkSoftAssertionsHasJUnitCode() {

        open("/selenide/selenide");
        $(byText("Wiki")).click();
        $("#wiki-pages-box").$(withText("Show")).click();
        $(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave(text("Using JUnit5 extend test class:"));

    }
}
