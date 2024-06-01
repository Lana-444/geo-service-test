package geo.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceTest {

    private final LocalizationService localizationService;
    private final String RUSSIAN_GREETING;
    private final String ENGLISH_GREETING;

    public LocalizationServiceTest() {
        this.localizationService = new LocalizationServiceImpl();
        this.RUSSIAN_GREETING = "Добро пожаловать";
        this.ENGLISH_GREETING = "Welcome";
    }

    @Test
    public void russian_greeting_if_country_is_russia() {
        String result = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals(result, RUSSIAN_GREETING);
    }

    @Test
    public void english_greeting_if_country_is_usa() {
        String result = localizationService.locale(Country.USA);
        Assertions.assertEquals(result, ENGLISH_GREETING);
    }

    @Test
    public void english_greeting_if_country_is_brazil() {
        String result = localizationService.locale(Country.BRAZIL);
        Assertions.assertEquals(result, ENGLISH_GREETING);
    }

    @Test
    public void english_greeting_if_country_is_germany() {
        String result = localizationService.locale(Country.GERMANY);
        Assertions.assertEquals(result, ENGLISH_GREETING);
    }
}