package OlympicsMedals;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortTest {
	
	WebDriver driver;
	String url = "https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table";
	List<WebElement> rank;
	List<Integer> rankNum;
	List<Integer>sorted;
	List<WebElement>countries;
	List<String>SCountry;
	List<String>sortedCountries;
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		
	}
	
	//Test Case 1: SORT TEST
	//1.Go to website https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.
	//2.Verify that by default the Medal table is sorted by rank.To do that you need to capture all the cells in the Rank column 
	// and check if they are in ascending order(high lighted in the picture).Use TestNG assertions.
	//3.Click link NOC.
	//4.Now verify that the table is nows orted by the country names.To do that you need to capture all  the names in the NOC column 
	//and check if they are in ascending/alphabetical order(high lighted in the picture).Use TestNG assertions
	@Test(priority=1)
	public void Test1() {
		driver.get(url);
		rank = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		rank.remove(rank.size()-1);
		rankNum =new ArrayList<Integer>();
		sorted = new ArrayList<Integer>();
		System.out.println(rank.size());
		
		for(int i = 0; i< rank.size();i++) {
			rankNum.add(Integer.parseInt(rank.get(i).getText()));
			}
		System.out.println(rankNum);
		
		
		for(Integer sort : rankNum) {
			sorted.add(sort);
		}
		Collections.sort(sorted);
		System.out.println(sorted);
		Assert.assertTrue(sorted.equals(rankNum));

		}
	//step 4 and 5
	@Test(priority = 2)
	public void test2() {
		SCountry = new ArrayList<String>();
		 driver.findElement(
	                By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[2]"))
	                .click();

        countries = driver.findElements(
                By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th[2]"));
        for (int i = 0; i < countries.size(); i++) {
        	SCountry.add(countries.get(i).getText());
        }
        // System.out.println(obtainedList);
        ArrayList<String> sortedCountries = new ArrayList<String>();
        for (String country : SCountry) {
            sortedCountries.add(country);
        }
        Collections.sort(sortedCountries);
        // System.out.println(sortedList);
        assertTrue(sortedCountries.equals(SCountry));

	}
	
	@Test(priority = 3)
	public void test3() throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> ranksAgain = driver.findElements(
                By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
        List<Integer>Rank1=new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			Rank1.add(Integer.parseInt(ranksAgain.get(i).getText()));
        }
      
        assertFalse(sorted.equals(Rank1));
    
	}
	

	
	@AfterClass
	public void cleanUp() {
		driver.close();
	}
	
			
			}
	
		
	
	
		


