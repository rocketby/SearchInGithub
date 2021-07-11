package github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
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
        Configuration.startMaximized = true;
    }

    @Test
    void shouldFindWikiInGitHub() {
        String exampleJUnit5 = "@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}";


        open("/selenide/selenide");

        $(byText("Wiki")).closest("a").click();

        $(byText("Welcome to the selenide wiki!")).shouldBe(visible);

        SelenideElement softAssertions = $("#wiki-pages-box").$(byText("SoftAssertions"));

        softAssertions.should(exist);

        if (!(softAssertions.isDisplayed())) {
            $("#wiki-pages-box").$(withText("Show"))
                    .scrollIntoView(true)
                    .click();
        }

        softAssertions.shouldBe(visible);
        softAssertions.click();

        $(byText("How to soft assert using Selenide")).shouldBe(visible);

        $(byText("Using JUnit5 extend test class:"))
                .parent()
                .sibling(0)
                .$("pre")
                .shouldHave(text(exampleJUnit5));


    }
}
