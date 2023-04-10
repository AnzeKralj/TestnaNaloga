import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class NBA_3PM {

    public static final ArrayList<String> playerURL = new ArrayList<>();
    public static int playerURLIndex = 0;
    public String DATA_API = "https://api.sportsdata.io/v3/nba/scores/json/Players/DAL?key=58957574730c4ee1b809da2f53525997";

    public NBA_3PM() {
        try {
            playerURL.addAll(getPlayersURL());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public ArrayList<String> getPlayersURL() throws IOException {
        // Retrieving the URLs of all players in the team
        ArrayList<String> URLs = new ArrayList<>();
        URL url = new URL(DATA_API);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            JSONArray jsonArray = new JSONArray(reader.readLine());
            for (Object object : jsonArray) {
                JSONObject player = (JSONObject) object;
                URLs.add("https://www.nba.com/player/" + player.getInt("NbaDotComPlayerID"));
            }
        }
        return URLs;
    }

    @Test(invocationCount  = 17)
    public void testPlayer3PM() {
        // Set up the chrome driver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        driver.get(playerURL.get(playerURLIndex++));

        double sum = 0.0, average = 0.0;

        // Loop through the rows of the table and add up the 3-pointers made
        for (int i = 1; i <= 5; i++) {
            try {
                sum += Double.parseDouble(driver.findElement(By.xpath("//table[1]/tbody/tr" + "[" + i + "]/td[9]/a")).getText());
            } catch (NoSuchElementException exception) {
                sum += 0;
            }
        }

        // Calculating the average and print the result
        average = sum / 5;
        driver.close();
        Assert.assertTrue(average >= 1, "Average (" + average + ") is not greater than 1");
        System.out.print("Player has: " + average + " 3PM average in the last 5 games.");
    }


}