package es.codeurjc.ais.tictactoe;


import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SeleniumSytemTest {

    @Parameters
    public static Collection<Object[]> data () {
        Object[][] values = {
                { new int[] { 4,0,7,1,2,6,3,5,8} },
                { new int[] { 0,3,1,4,7,5} },
                { new int[] { 0,3,1,4,2} },
        };

        return Arrays.asList(values);
    }

    @Parameter(0) public int[] moves;


    WebDriver driverPlayerOne;
    WebDriver driverPlayerTwo;

    static String URL_SUT = "http://localhost:8080";
    static String cellIdString = "cell-";

    @BeforeClass
    public static void beforeAll() {
        //System.setProperty("webdriver.chrome.driver", "/home/feanaro/projects/tic-tac-toe-enunciado/chromedriver");
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

    private void releaseWebDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }


    private void registerUser(String playerNickName, WebDriver driver) {
        String play_button = "startBtn";
        String name_text = "nickname";

        driver.findElement(By.id(name_text)).sendKeys(playerNickName);
        driver.findElement(By.id(play_button)).click();
    }

    private void goToHost(WebDriver driver, String url) {
        driver.get(url);
    }

    private void move(WebDriver driver, int cell) {
        StringBuilder sb = new StringBuilder(cellIdString);
        sb.append(cell);

        driver.findElement(By.id(sb.toString())).click();
    }

    @Test
    public void test() {
        // Exercise and verify
        String namePlayerOne = "player One";
        String namePlayerTwo = "player Two";

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

        String result = drivers[index].switchTo().alert().getText();
        System.out.println(result);
    }

}
