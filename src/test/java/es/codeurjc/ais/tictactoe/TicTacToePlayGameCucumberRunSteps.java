package es.codeurjc.ais.tictactoe;


import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

public class TicTacToePlayGameCucumberRunSteps {

    @Given("^I have a tictactoe game$")
    public void i_have_a_tictactoe_game() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @And("^player_one is '(-?.*)'$")
    public void player_one_is(String nicknamePlayerOne) throws Throwable {
        System.out.println(nicknamePlayerOne);
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @And("^player_two is '(-?.*)'$")
    public void player_two_is_player_two(String nicknamePlayerTwo) throws Throwable {
        System.out.println(nicknamePlayerTwo);
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
    }

    @When("^this moves are played \\[(-?\\d+(?:[ \\t]*,[ \\t]*\\d+)+)\\]$")
    public void this_moves_are_played(
            @Transform(IntegerListTransformer.class) int[] moves) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }


    @Then("^the result is (-?player_one wins|player_two wins|draw)$")
    public void the_result_is(
            @Transform(ResultTransformer.class) Results result) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(result);
        throw new PendingException();
    }

}
