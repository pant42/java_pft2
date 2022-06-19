package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpService {
    @Test
    public void testMyIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("79.111.128.235");
        Assert.assertEquals(geoIp, "<GeoIP><Country>RU</Country><State>77</State></GeoIP>");
    }

}
