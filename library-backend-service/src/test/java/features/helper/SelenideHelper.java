package features.helper;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideConfig;

public class SelenideHelper {
    private static SelenideConfig config = new SelenideConfig();

    public static void setup() {
        Configuration.headless = true;
        Configuration.reportsFolder = config.reportsFolder();
        Configuration.screenshots = false;
    }
}
