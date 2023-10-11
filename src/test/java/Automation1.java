import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
public class Automation1 {
    public static void main(String[] args) throws InterruptedException {
        //    1. Navigate to http://duotify.us-east-2.elasticbeanstalk.com/register.php

        WebDriver webDriver = new ChromeDriver();

        webDriver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");

//    2. Verify the  title is "Welcome to Duotify!" (Use Assert class methods for all assertions)

        Assert.assertEquals(webDriver.getTitle(), "Welcome to Duotify!");

//    3. Click on Signup here
        webDriver.findElement(By.id("hideLogin")).click();
//    4. Fill out the form with the required info
//    5. (You might want to use Faker class for that purpose since the data has to be different every time because the application does not allow you to sign up with the same username or email more than once)

        Faker faker = new Faker();

        String userName = faker.name().username();
        webDriver.findElement(By.id("username")).sendKeys(userName);
        String firstName = faker.name().firstName();
        webDriver.findElement(By.id("firstName")).sendKeys(firstName);
        String lastName = faker.name().lastName();
        webDriver.findElement(By.id("lastName")).sendKeys(lastName);
        String email = faker.internet().emailAddress();
        webDriver.findElement(By.id("email")).sendKeys(email);
        webDriver.findElement(By.id("email2")).sendKeys(email);
        String password = faker.internet().password();
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("password2")).sendKeys(password);

//    6. Click on Sign up
        webDriver.findElement(By.name("registerButton")).click();
        Thread.sleep(1000);

//    7. Once logged in to the application, verify that the URL is:
//    http://duotify.us-east-2.elasticbeanstalk.com/browse.php?
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/browse.php?");

//    8. In the left navigation bar, verify that your first and last name matches the first and last name that you used when signing up.

        Assert.assertEquals(webDriver.findElement(By.id("nameFirstAndLast")).getText(), firstName + " " + lastName);
//    9. Click on the username on the left navigation bar and verify the username on the main window is correct and then click logout.

        webDriver.findElement(By.id("nameFirstAndLast")).click();

        Thread.sleep(1000);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[1]/div/h1")).getText(), firstName + " " + lastName);

        webDriver.findElement(By.id("rafael")).click();
        Thread.sleep(1000);

//    10. Verify that you are logged out by verifying the URL is:
//    http://duotify.us-east-2.elasticbeanstalk.com/register.php
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/register.php");

//    11. Login using the same username and password when you signed up.

        webDriver.findElement(By.id("loginUsername")).sendKeys(userName);
        webDriver.findElement(By.id("loginPassword")).sendKeys(password);
        webDriver.findElement(By.name("loginButton")).click();
        Thread.sleep(1000);

//    12. Verify successful login by verifying that the home page contains the text "You Might Also Like".

        Assert.assertTrue(webDriver.getPageSource().toString().contains("You Might Also Like"));
//    13. Log out once again and verify that you are logged out.
        webDriver.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.id("rafael")).click();
        Thread.sleep(1000);

        webDriver.quit();

    }
}
