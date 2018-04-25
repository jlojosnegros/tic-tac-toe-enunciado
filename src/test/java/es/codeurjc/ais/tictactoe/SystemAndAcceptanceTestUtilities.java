package es.codeurjc.ais.tictactoe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SystemAndAcceptanceTestUtilities {

    static public void releaseWebDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    static public void registerUser(String playerNickName, WebDriver driver) {
        String play_button = "startBtn";
        String name_text = "nickname";

        driver.findElement(By.id(name_text)).sendKeys(playerNickName);
        driver.findElement(By.id(play_button)).click();
    }

    static public void goToHost(WebDriver driver, String url) {
        driver.get(url);
    }

    static public void move(WebDriver driver, int cell) {
        String cellIdString = "cell-";
        StringBuilder sb = new StringBuilder(cellIdString);
        sb.append(cell);

        driver.findElement(By.id(sb.toString())).click();
    }
}
