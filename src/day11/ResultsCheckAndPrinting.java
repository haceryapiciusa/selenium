package day11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ResultsCheckAndPrinting {
    public static void main(String[] args) {
        System.setProperty( "webdriver.chrome.driver", "D:\\TechnoStudy\\Selenium\\ChromeDriver\\chromedriver.exe" );
        WebDriver driver = new ChromeDriver();
        driver.get("https://test-basqar.mersys.io");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//span[contains(text(),'Entrance Exams')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Entrance Exams')]/../..//span[contains(text(),'Setup')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Setup')]/../..//span[contains(text(),'Entrance Exams')]")).click();
        WebDriverWait wait = new WebDriverWait( driver, 15 );
        try {
            wait.until( ExpectedConditions.presenceOfElementLocated( By.cssSelector( "tbody > tr" ) ) ); //this is to wait for the first search
        } catch (Exception e){
            // there's no results for first search, continue
        }

        driver.findElement(By.cssSelector("input[placeholder=\"Name\"]")).sendKeys("2");
        driver.findElement(By.xpath("//span[contains(text(),'Search')]")).click();



        try {
            wait.until( ExpectedConditions.numberOfElementsToBe( By.cssSelector( "tbody > tr" ), 2)  );
            System.out.println("Success!");
        } catch (Exception e){
            System.out.println("Failure!");
        }
        List<WebElement> elements = driver.findElements( By.cssSelector( "tbody > tr > td:nth-child(2)" ) );
        for(WebElement e: elements) {
            System.out.println(e.getText());
        }
        driver.quit();
    }
}
