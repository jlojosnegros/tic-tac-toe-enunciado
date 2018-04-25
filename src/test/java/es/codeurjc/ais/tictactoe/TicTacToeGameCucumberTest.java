package es.codeurjc.ais.tictactoe;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty"},
        features = { "classpath:es/codeurjc/test"},
        glue = {"es.codeurjc.ais.tictactoe"}
)
public class TicTacToeGameCucumberTest {

}
