package OlympicsMedals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class countries {
	WebDriver driver;
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		
	}
	
	@BeforeMethod
	public void bMethod() {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
	}
	
	@Test(priority = 1)
	public void theMost() {
		Assert.assertEquals(mostGold(), " United States (USA)");	
		Assert.assertEquals(mostSilver(), " United States (USA)");
		Assert.assertEquals(mostBronze(), " United States (USA)");
		Assert.assertEquals(mostMedals(), " United States (USA)");
	}
	@Test(priority = 2)
	public void countryByMedal() {
	List<String> expected=Arrays.asList( " France (FRA)"," China (CHN)");
	Assert.assertEquals(CountyByMedal(), expected);

	
	}
	
	@Test(priority = 3)
public void getIndex() {
		Assert.assertEquals(getIndex("Japan"), "4 - 2");
	
	 }
	
	@Test(priority = 4)
	public void getSum() {
		Assert.assertEquals(Sum(), Arrays.asList(" Australia (AUS)"," Italy (ITA)"));
	}
	
	
	
	
	// Method returns the country name with the most gold medals
 String mostGold() {
	 driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[3]")).click();
	 List<WebElement> gold = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[2]"));
	 
	List <String> goldNum= new ArrayList<String>();
	
	for(int i=0;i<gold.size();i++) {
		goldNum.add(gold.get(i).getText());
	}
	System.out.println(goldNum);
	
	 String cName =driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[10]/th")).getText();
	
	return cName;
	
 } 
//Method returns the country name with the most silver medals
 String mostSilver() {
	 driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[4]")).click();
	 List<WebElement> silver = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
	List <String> silverNum= new ArrayList<String>();
	
	for(int i=0;i<silver.size();i++) {
		silverNum.add(silver.get(i).getText());
	}
	System.out.println(silverNum);
	
	 String cName =driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[10]/th")).getText();
	
	return cName; 
 }
//Method returns the country name with the most bronze medals
 String mostBronze() {
	 driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[5]")).click();
	 List<WebElement> bronze = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
	List <String> bronzeNum= new ArrayList<String>();
	
	for(int i=0;i<bronze.size();i++) {
		bronzeNum.add(bronze.get(i).getText());
	}
	System.out.println(bronzeNum);
	
	 String cName =driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[10]/th")).getText();
	
	return cName; 
 }
//Method returns the country name with the most medals
 String mostMedals() {
	 driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[6]")).click();
	 List<WebElement> medal = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[5]"));
	List <String> medalNum= new ArrayList<String>();
	
	for(int i=0;i<medal.size();i++) {
		medalNum.add(medal.get(i).getText());
	}
	System.out.println(medalNum);
	
	 String cName =driver.findElement(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[10]/th")).getText();
	
	return cName; 
 }
 
 public List<String> CountyByMedal(){
	 List<WebElement> silver = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
		silver.remove(silver.size()-1);
		System.out.println(silver.size());
		List<WebElement>countryName =driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th")) ;
		countryName.remove(countryName.size()-1);
		System.out.println(countryName.size());
		List<String>countryName1=new ArrayList<String>();
		
		
		for (WebElement el : silver) {
			if (el.getText().equals("18")) {
				int index = silver.indexOf(el);
				countryName1.add(countryName.get(index).getText());
			}
		
			
		}
		return countryName1;}
 
 
 public String getIndex(String country){
	 int rowNumbers=driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//../tbody/tr")).size();
	 int columnNumber=driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//../thead/tr/th")).size();
	 String [][] colRow = new String[rowNumbers][columnNumber];
	 String colRowNumbers="";
	 
	 for (int i = 0; i < colRow.length; i++) {
		 			for (int j = 0; j < colRow[i].length; j++) {
		 				colRow[i][j] = driver.findElement(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//../tbody/tr["
		 						+ (i + 1) + "]/*)[" + (j + 1) + "]")).getText();
		 				if (colRow[i][j].contains(country)) {
		 					return (i + 1) + " - " + (j + 1);
		 				}
		 		}
		 		}
		 		return "not found";
		 	}
public Map<String,Integer> map(List<WebElement> coList,List<WebElement>brList) {
	Map<String,Integer>map = new HashMap();
	for(int i=0; i <  coList.size();i++) {
		map.put(coList.get(i).getText(), Integer.parseInt(brList.get(i).getText()));
	}
	
	
	
	return map;
}
public List<String> Sum() {
	List<WebElement> countryList =driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th")) ;
	List<WebElement>bronzeList=driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
	
	Map<String,Integer> newMap= map(countryList,bronzeList);
	Set<Entry<String,Integer>> setMap= newMap.entrySet();
	List<String> coSum=new ArrayList<String>();
	
	for (Entry<String, Integer> entry1 : setMap) {
		for (Entry<String, Integer> entry2 : setMap) {
			if(!entry1.getKey().equals(entry2.getKey()) && !coSum.contains(entry1.getKey()) && entry1.getValue() + entry2.getValue() == 18) {
				coSum.add(entry1.getKey());
				coSum.add(entry2.getKey());
			}
		}}
	return coSum;
	
	
}
 
}




