package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import sun.jvm.hotspot.utilities.Assert;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by ukoochana on 27/11/2019.
 */
public class PostFixtureSteps {

    String URL = "http://localhost:3000";

    @Given("^I have postRequestBody$")
    public void I_have_postRequestBody() throws Throwable {
        given()
                .contentType(ContentType.JSON);
    }

    @When("^I post the post_end_point$")
    public void I_post_the_post_end_point() throws Throwable {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("testData_9.json").toURI());

        Stream<String> lines = Files.lines(path);
        String payload = lines.collect(Collectors.joining("\n"));
        lines.close();

        Response response = given().
                contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(URL + "/fixture");
    }

    @Then("^I should see new fixture created$")
    public void I_should_see_new_fixture_created() throws Throwable {
        Boolean continueSearch = true;
        do {
            Response response = when()
                    .get(URL+"/fixture/9");
            System.out.println(response.getStatusCode());

            if(response.getStatusCode()==200 && response.getBody().asString().contains("9")){
                continueSearch = false;
            }

        }
        while (continueSearch);
    }

    @Given("^I perform get operation for the new fixture$")
    public void I_perform_get_operation_for_the_new_fixture() throws Throwable {
        given()
                .contentType(ContentType.JSON);
    }

    @And("^Assert the first object has a teamId of HOME$")
    public void Assert_the_first_object_has_a_teamId_of_HOME() throws Throwable {
        when()
                .get(URL+"/fixture/9")
                .then()
                .assertThat()
                .statusCode(200)
                .body("footballFullState.teams[0].teamId", equalTo("HOME"));
    }
}
