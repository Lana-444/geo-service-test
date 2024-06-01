package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceTest {

    private final GeoService geoService;
    private final String USA_IP;
    private final String RUSSIA_IP;


    public GeoServiceTest () {
        this.geoService = new GeoServiceImpl();
        this.USA_IP = "96.44.183.149";
        this.RUSSIA_IP = "172.0.32.0";
    }

    @Test
    public void get_russia_by_ip() {
        Location expected = new Location(null, Country.RUSSIA, null, 0);
        Location result = geoService.byIp(RUSSIA_IP);
        Assertions.assertEquals(expected.getCountry(), result.getCountry());
    }

    @Test
    public void get_USA_by_ip() {
        Location expected = new Location(null, Country.USA, null, 0);
        Location result = geoService.byIp(USA_IP);
        Assertions.assertEquals(expected.getCountry(), result.getCountry());
    }
}