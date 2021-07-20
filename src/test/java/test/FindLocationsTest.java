package test;

import model.pages.FindLocationsPage;
import model.pages.HomePage;
import org.testng.annotations.Test;
import runner.BaseTest;

public class FindLocationsTest extends BaseTest {

    private static final String FIND_LOCATIONS = "Find USPS Locations";
    private static final String ZIP = "33578";
    private static final String PO_NAME = "Self-Service Kiosks";

    @Test
    public void findLocation() {

        new HomePage(getDriver())
                .getMenuOption(FIND_LOCATIONS).click();
        new FindLocationsPage(getDriver())
                .fillZip(ZIP)
                .selectPO(PO_NAME);

    }
}
