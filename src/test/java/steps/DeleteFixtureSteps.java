package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by ukoochana on 28/11/2019.
 */
public class DeleteFixtureSteps {

    String URL = "http://localhost:3000";

    @Given("^I ensure to perform POST new fixture$")
    public void I_ensure_to_perform_POST_new_fixture() throws Throwable {
        given()
                .contentType(ContentType.JSON);
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

    @And("^I perform GET operation for new fixture$")
    public void I_perform_GET_operation_for_new_fixture() throws Throwable {
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

    @Then("^I perform DELETE operation for that new fixture$")
    public void I_perform_DELETE_operation_for_that_new_fixture() throws Throwable {

        when()
                .delete(URL+"/fixture/9")
                .then()
                .assertThat()
                .statusCode(200)
                .body(equalTo("Fixture has been deleted"));
    }

    @Then("^I Assert that the new fixture no longer exists$")
    public void I_Assert_that_the_new_fixture_no_longer_exists() throws Throwable {
        given()
                .contentType(ContentType.JSON);
        when()
                .get(URL+"/fixture/9")
                .then()
                .assertThat()
                .statusCode(404)
                .body(equalTo("Fixture not found"));
    }
}
