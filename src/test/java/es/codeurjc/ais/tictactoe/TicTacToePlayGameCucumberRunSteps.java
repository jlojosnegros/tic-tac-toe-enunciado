package es.codeurjc.ais.tictactoe;


import cucumber.api.Transform;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static es.codeurjc.ais.tictactoe.SystemAndAcceptanceTestUtilities.*;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TicTacToePlayGameCucumberRunSteps {

    static String URL_SUT = "http://localhost:8080";
    static String cellIdString = "cell-";

    WebDriver driverPlayerOne;
    WebDriver driverPlayerTwo;
    WebDriver lastMove;
    String nicknamePlayerOne;
    String nicknamePlayerTwo;


    @Before
    public void beforeEach() {
        driverPlayerOne = new ChromeDriver();
        driverPlayerTwo = new ChromeDriver();
    }

    @After
    public void afterEach() {
        releaseWebDriver(driverPlayerOne);
        releaseWebDriver(driverPlayerTwo);
    }


    @Given("^I have a tictactoe game at (-?.*)$")
    public void i_have_a_tictactoe_game_at(String url) throws Throwable {
        goToHost(driverPlayerOne, url);
        goToHost(driverPlayerTwo, url);

    }

    @And("^player_one is (-?[^ ]+)$")
    public void player_one_is(String nicknamePlayerOne) throws Throwable {
        this.nicknamePlayerOne = nicknamePlayerOne;
        registerUser(nicknamePlayerOne, driverPlayerOne);
    }

    @And("^player_two is (-?[^ ]+)$")
    public void player_two_is_player_two(String nicknamePlayerTwo) throws Throwable {
        this.nicknamePlayerTwo = nicknamePlayerTwo;
        registerUser(nicknamePlayerTwo, driverPlayerTwo);
    }

    @When("^this moves are played \\[(-?\\d+(?:[ \\t]*,[ \\t]*\\d+)+)\\]$")
    public void this_moves_are_played(
            @Transform(IntegerListTransformer.class) int[] moves) throws Throwable {
        WebDriverWait waitToMove = new WebDriverWait(driverPlayerOne, 30);
        WebElement cell_0 = driverPlayerOne.findElement(By.id("cell-0"));
        waitToMove.until(ExpectedConditions.elementToBeClickable(cell_0));

        WebDriver[] drivers = {driverPlayerOne, driverPlayerTwo};
        int index = 0;
        for (int cell : moves) {
            move(drivers[index], cell);
            index = (index+1)%drivers.length;
        }

        index = (index+1)%drivers.length;
        lastMove = drivers[index];
    }


    @Then("^the result is (-?[^ ]+ wins|draw)!$")
    public void the_result_is(String result) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebDriverWait wait = new WebDriverWait(lastMove, 30);
        wait.until(ExpectedConditions.alertIsPresent());

        String alert_text = lastMove.switchTo().alert().getText();
        assertThat(alert_text.toLowerCase()).startsWith(result.toLowerCase());
    }

}
