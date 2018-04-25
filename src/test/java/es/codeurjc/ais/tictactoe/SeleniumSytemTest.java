package es.codeurjc.ais.tictactoe;


import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

import static es.codeurjc.ais.tictactoe.SystemAndAcceptanceTestUtilities.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(Parameterized.class)
public class SeleniumSytemTest {

    static String namePlayerOne = "player One";
    static String namePlayerTwo = "player Two";

    @Parameters
    public static Collection<Object[]> data () {
        Object[][] values = {
                { new int[] { 4,0,7,1,2,6,3,5,8}, "draw"},
                { new int[] { 0,3,1,4,7,5}, namePlayerTwo},
                { new int[] { 0,3,1,4,2} , namePlayerOne},
        };

        return Arrays.asList(values);
    }

    @Parameter(0) public int[] moves;
    @Parameter(1) public String result;


    WebDriver driverPlayerOne;
    WebDriver driverPlayerTwo;

    static String URL_SUT = "http://localhost:8080";

    @BeforeClass
    public static void beforeAll() {
        ChromeDriverManager.getInstance().setup();
        WebApp.start();
    }

    @AfterClass
    public static void afterAll() {
        WebApp.stop();
    }

    @Before
    public void beforeEach() throws MalformedURLException {
        driverPlayerOne = new ChromeDriver();
        driverPlayerTwo = new ChromeDriver();
    }

    @After
    public void afterEach() {
        releaseWebDriver(driverPlayerOne);
        releaseWebDriver(driverPlayerTwo);
    }

    @Test
    public void test() {
        // Exercise and verify


        goToHost(driverPlayerOne,URL_SUT);
        registerUser(namePlayerOne, driverPlayerOne);

        goToHost(driverPlayerTwo,URL_SUT);
        registerUser(namePlayerTwo, driverPlayerTwo);

        WebDriver[] drivers = {driverPlayerOne, driverPlayerTwo};
        int index = 0;

        for(int cell : moves) {
            move(drivers[index], cell);
            index = (index+1)%drivers.length;
        }

        index = (index+1)%drivers.length;
        WebDriverWait wait = new WebDriverWait(drivers[index], 30);

        wait.until(ExpectedConditions.alertIsPresent());

        String alert_result = drivers[index].switchTo().alert().getText();
        assertThat(alert_result.toLowerCase()).startsWith(result.toLowerCase());
    }

}
