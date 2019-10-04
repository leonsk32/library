package features;


import com.codeborne.selenide.Selenide;
import features.helper.SelenideHelper;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks {
    @Before
    public void before(Scenario scenario) {
        SelenideHelper.setup();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            Selenide.screenshot(scenario.getName());
        }
    }
}
