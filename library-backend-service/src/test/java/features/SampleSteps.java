package features;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SampleSteps {

  @When("{string}にアクセスする")
  public void にアクセスする(String string) {
    //Selenide.open("/v1/sample/hoge");
  }

  @Then("本を{string}する")
  public void をする(String string) {
    // DBにアクセスしてassertする。
    assertThat("").isEqualTo("");
  }
}
