package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by ukoochana on 27/11/2019.
 */
public class GetFixtureSteps {

    String URL = "http://localhost:3000";

    @Given("^I perform get operation for fixtures$")
    public void I_perform_get_operation_for() throws Throwable {
        given()
                .contentType(ContentType.JSON);
    }

    @Then("^Assert that there are 3 fixtures within the returned object$")
    public void I_should_see_all_the_three_fixturesx() throws Throwable {
        when()
                .get(URL+"/fixtures")
        .then()
                .assertThat()
                .statusCode(200)
                .body("fixtureId[0]", equalTo("1"))
                .body("fixtureId[1]", equalTo("2"))
                .body("fixtureId[2]", equalTo("3"));

    }

    @Then("^Assert that each of the three fixtures has a fixtureId value$")
    public void Assert_that_each_of_the_fixtures_has_a_fixtureId_value() throws Throwable {
        when()
                .get(URL+"/fixtures")
        .then()
                .assertThat()
                .statusCode(200)
                .body("size()", is(3));
    }
}
