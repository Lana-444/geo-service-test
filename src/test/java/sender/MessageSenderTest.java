package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderTest {

    private final LocalizationService localizationServiceMock;
    private final GeoService geoServiceMock;
    private final String RUSSIA_IP;
    private final String USA_IP;
    private final String IP_HEADER;
    private final String RUSSIA_GREETING;
    private final String ENGLISH_GREETING;

    public MessageSenderTest() {
        this.localizationServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        this.geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        this.RUSSIA_IP = "172.0.32.11";
        this.USA_IP = "96.44.183.149";
        this.IP_HEADER = "x-real-ip";
        this.RUSSIA_GREETING = "Добро пожаловать";
        this.ENGLISH_GREETING = "Welcome";
    }

    @Test
    public void send_russian_string_if_ip_is_russian() {
        Map<String, String> mapa = new HashMap<>();
        mapa.put(IP_HEADER, RUSSIA_IP);

        Mockito.when(geoServiceMock.byIp(RUSSIA_IP)).thenReturn(new Location(null, Country.RUSSIA, null, 0));
        Mockito.when(geoServiceMock.byIp(USA_IP)).thenReturn(new Location(null, Country.USA, null, 0));
        Mockito.when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn(RUSSIA_GREETING);
        Mockito.when(localizationServiceMock.locale(Country.USA)).thenReturn(ENGLISH_GREETING);

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
        String strLanguage = messageSender.send(mapa);

        // Посимвольно разбираем строку и смотим из символов кириллицы она состоит или нет
        int counter = 0;
        for (int i = 0; i < strLanguage.length(); i++) {
            if ((Character.UnicodeBlock.of(strLanguage.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) || (strLanguage.charAt(i) == ' ') || (strLanguage.charAt(i) == '-')) {
                counter++;
            }
        }

        boolean result;
        if (counter == strLanguage.length()){
            result = true;
        }
        else {
            result = false;
        }

        Assertions.assertTrue(result);
    }

    @Test
    public void send_english_string_if_ip_is_not_russian() {
        Map<String, String> mapa = new HashMap<>();
        mapa.put(IP_HEADER, USA_IP);

        Mockito.when(geoServiceMock.byIp(RUSSIA_IP)).thenReturn(new Location(null, Country.RUSSIA, null, 0));
        Mockito.when(geoServiceMock.byIp(USA_IP)).thenReturn(new Location(null, Country.USA, null, 0));
        Mockito.when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn(RUSSIA_GREETING);
        Mockito.when(localizationServiceMock.locale(Country.USA)).thenReturn(ENGLISH_GREETING);

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
        String strLanguage = messageSender.send(mapa);

        // Посимвольно разбираем строку и смотим из символов кириллицы она состоит или нет
        int counter = 0;
        for (int i = 0; i < strLanguage.length(); i++) {
            if ((Character.UnicodeBlock.of(strLanguage.charAt(i)).equals(Character.UnicodeBlock.BASIC_LATIN)) || (strLanguage.charAt(i) == ' ') || (strLanguage.charAt(i) == '-')) {
                counter++;
            }
        }

        boolean result;
        if (counter == strLanguage.length()){
            result = true;
        }
        else {
            result = false;
        }

        Assertions.assertTrue(result);
    }
}