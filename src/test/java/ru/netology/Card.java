package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class Card {
    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldCardApplication() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1600x900";
        String planningDate = generateDate(4);
        open("http://localhost:9999");
        $("[data-test-id='city'] input").val("Киров");
        $("[data-test-id='date'] input").doubleClick().sendKeys(planningDate);
        $("[data-test-id='name'] input").val("Иванова Анна");
        $("[data-test-id='phone'] input").val("+79998887766");
        $("[data-test-id='agreement']>span").click();
        $("[role='button'] span [class='button__text']").click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }
}
