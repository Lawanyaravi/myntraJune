package Components;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	By homepage= By.xpath("//a[@class= 'myntraweb-sprite desktop-logo sprites-headerLogo']");
	By items_In_Cart= By.xpath("//span[@data-reactid='695']");
	By search_Bar= By.className("desktop-searchBar");
	WebDriver driver;
	
	/**
	 * This method is to initialize WebDriver object driver
	 * @author - Lawanya
	 */
	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method is to check if Myntra logo is displayed in homepage
	 * @author - Lawanya
	 */
	public boolean verifyLogoIsDisplayed() {
		boolean is_Displayed = driver.findElement(homepage).isDisplayed();
		return is_Displayed;
	}
	
	/**
	 * This method is to check if the Cart has expected items in it if it is displayed
	 * @author - Lawanya
	 * @input - requires expected items count as 0
	 */
	public boolean verifyItemsInCart(String expected_Count)  {
		
		String items_Count= driver.findElement(items_In_Cart).getText().replace('"', ' ').trim();
		return items_Count.equals(expected_Count);
		
	}
	
	/**
	 * This method is to check the items count 
	 * @author - Lawanya
	 * @input - requires default items count as 0
	 */
	public boolean verifyItemsCount(String expected_Count)
	{
		boolean out = true;
		if(driver.findElement(items_In_Cart).isDisplayed())
		{
			out= verifyItemsInCart(expected_Count);
		}
		return out;
	}
	
	/**
	 * This method is to search for items in Myntra
	 * @author - Lawanya
	 * @input - requires name of item to be searched
	 */
	public void searchItem(String item)
	{
		driver.findElement(search_Bar).sendKeys(item);
		driver.findElement(search_Bar).sendKeys(Keys.ENTER);
		
	}
}
