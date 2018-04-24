package es.codeurjc.ais.tictactoe;


import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.net.MalformedURLException;

public class SeleniumSytemTest {

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

    private void goToHost(WebDriver driver) {
        //driver.get("http://localhost:8080");
        driver.get(URL_SUT);
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

        goToHost(driverPlayerOne);
        registerUser(namePlayerOne, driverPlayerOne);

        goToHost(driverPlayerTwo);
        registerUser(namePlayerTwo, driverPlayerTwo);


        move(driverPlayerOne,0);
        move(driverPlayerTwo,3);

        move(driverPlayerOne, 1);
        move(driverPlayerTwo, 4);

        move(driverPlayerOne, 2);

        String result = driverPlayerOne.switchTo().alert().getText();
//
//        System.out.println(result);
    }

}
