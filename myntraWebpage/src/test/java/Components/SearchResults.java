package Components;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class SearchResults {
    WebDriver driver;
    Actions a;
    Robot robot;
    ArrayList<String> newTb;
    ArrayList<WebElement> size_List = new ArrayList<WebElement>() ;
    String price;
    String size;
    String item_Name;
    String go_to_bag = "GO TO BAG";
    int quantity;
    
	By search_Result_Title = By.className("title-title");
	By breadcrumb_Item = By.xpath("//span[@class= 'breadcrumbs-crumb']");
	By sort_Field = By.xpath("//div[@class='sort-sortBy']");
	By sort_Option = By.xpath("//*[@id=\"desktopSearchResults\"]/div[1]/section/div[1]/div[1]/div/div/div/ul/li[5]/label");
//	By item = By.xpath("//*[@id=\"desktopSearchResults\"]/div[2]/section/ul/li[1]/a/div[1]/div/div/div/picture/img");
	By items_xpath = By.xpath("//img[@class = 'img-responsive']");
	WebElement item;
	By item_Price = By.xpath("//p[@class = 'pdp-discount-container']");
	By size_Buttons = By.xpath("//button[@class = 'size-buttons-size-button size-buttons-size-button-default size-buttons-big-size']");
/*	List<WebElement> listOfElements = driver.findElements(By.xpath("//button/span[@class='size-buttons-size-strike-hide']")); */
	WebElement size_Button;  
	By price_Object = By.xpath("//span[@class = 'pdp-price']");
//	By size_Button = By.xpath("//*[@id=\"sizeButtonsContainer\"]/div[2]/div[4]/div[1]/button/p");
	By add_To_Cart_Button = By.xpath("//*[@id=\"mountRoot\"]/div/div/div/main/div[2]/div[2]/div[3]/div/div[1]/span");
	By items_In_Cart= By.xpath("//*[@id=\"cartItemsList\"]/div/div/div/div/div[2]/div/div[3]/div[2]/span");
	By items_Count = By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[2]/a[2]/span[2]");
	By go_To_Cart_Button = By.xpath("//a[@class='pdp-goToCart pdp-add-to-bag pdp-button pdp-flex pdp-center ']");
	By cart_Price = By.xpath("//*[@id=\"cartItemsList\"]/div/div/div/div/div[2]/div/div[5]/div[1]/div");
	By cart_Size = By.xpath("//*[@id=\"cartItemsList\"]/div/div/div/div/div[2]/div/div[3]/div[1]/span");
	By cart_Quantity = By.xpath("//*[@id=\"cartItemsList\"]/div/div/div/div/div[2]/div/div[3]/div[2]/span");
	
	/**
	 * This method is to initialize WebDriver object driver
	 * @author - Lawanya
	 */
	public SearchResults(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method is to check if the searched item name is displayed in Results title
	 * @author - Lawanya
	 */
	public boolean verifyResultsTitle(String item) {
		boolean out= false;
		out= driver.findElement(search_Result_Title).getText().equalsIgnoreCase(item);
		return out;
	}
	
	/**
	 * This method is to check if the searched item name is displayed in breadcrumbs text
	 * @author - Lawanya
	 */
	public boolean verifyBreadcrumbsText(String item) {
		boolean out = false;
		out= driver.findElement(breadcrumb_Item).getText().equalsIgnoreCase(item);
		return out;
	}
	
	/**
	 * This method is to check if the Search results are shown
	 * @author - Lawanya
	 */
	public boolean verifyResultPage(String item) {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.delay(5000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
		boolean out= false;
		out=(verifyResultsTitle(item) && verifyBreadcrumbsText(item));
		return out;
	}
	
	/**
	 * This method is to check if Sort option is shown
	 * @author - Lawanya
	 */
	public boolean verify_sort_field_is_Displayed() {
		boolean isDisplayed = false;
		
		isDisplayed = driver.findElement(sort_Field).isDisplayed();
		return isDisplayed;
	}
	
	/**
	 * This method is to change Sort to Price: Low to High
	 * @author - Lawanya
	 */
	public void sortResults() {
		
		a = new Actions(driver);
		a.moveToElement(driver.findElement(sort_Field)).perform();
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.delay(5000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
		driver.findElement(sort_Option).click();
	}
	
	/**
	 * This method is to select the cheapest item
	 * @author - Lawanya
	 */
	public void selectCheapestItem(){
		robot.delay(2000);
		item = driver.findElements(items_xpath).get(0);
	//	driver.findElement(item).click();
		item.click();
	}
	
	/**
	 * This method is to return price of item
	 * @author - Lawanya
	 */
	public String returnPrice() {
		String price= driver.findElement(item_Price).findElement(price_Object).getText();
		return price;
	}
	
	/**
	 * This method is to return size of item
	 * @author - Lawanya
	 */
	public String returnSize() {
//		String size= driver.findElement(size_Button).getText();
		String size= size_Button.getText();
		return size;
	}
	
	/**
	 * This method is to check if the Item is available
	 * @author - Lawanya
	 */
	public boolean checkAvailability() {
		boolean is_Available= false;
		
		if(!(driver.getPageSource().contains("unavailable")))
		{is_Available = true;
		}
		return is_Available;
	} 
	
	/**
	 * This method is to select the available Size
	 * @author - Lawanya
	 */
	public void selectSize() {
		newTb = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTb.get(2));

		size_List = (ArrayList<WebElement>) driver.findElements(size_Buttons);
	    for(WebElement w: size_List) {
	    	if(!w.isEnabled()) {
	    		continue;
	    	}
	    	size_Button = w;
	    	break;
	    }
	//	size_Button = size_List.get(0);
		
		size_Button.click();
		robot.delay(5000);
		a.moveToElement(driver.findElement(add_To_Cart_Button)).perform();
		
	}
	
	/**
	 * This method is to click on Add to Bag button
	 * @author - Lawanya
	 */
	public void addToCart() {
		price = returnPrice();
		size = returnSize();
		quantity = 1;
		driver.findElement(add_To_Cart_Button).click();
	}
	
	/**
	 * This method is to return the items count 
	 * @author - Lawanya
	 * @input - requires expected items count as 1
	 */
	public boolean verifyItemsInCart(String expected_Count)  {
		
		String items_Count= driver.findElement(items_In_Cart).getText().replace('"', ' ').trim();
		String[] quantity_detail = items_Count.split(" ");
		System.out.println(quantity_detail[1]);
		return quantity_detail[1].equals(expected_Count);
		
	}
	
	/**
	 * This method is to check if the Cart items count is changed to 1
	 * @author - Lawanya
	 * @input - requires expected items count as 1
	 */
	public boolean verifyItemsCount(String expected_Count)
	{
		boolean out = false;
		if(driver.findElement(items_Count).isDisplayed())
		{
			out= true;
		}
		return out;
	}
	
	/**
	 * This method is to check if the Go to Bag button is displayed
	 * @author - Lawanya
	 */
	public boolean verifyGoToCartButton() {
		boolean is_Displayed = false;
		robot.delay(5000);
		is_Displayed = driver.findElement(go_To_Cart_Button).isDisplayed();
		return is_Displayed;
	}
	
	/**
	 * This method is to click the Go to Bag button
	 * @author - Lawanya
	 */
	public void goToCart() {
		driver.findElement(go_To_Cart_Button).click();
	}
	
	/**
	 * This method is to check if price of item selected is same as shown in cart
	 * @author - Lawanya
	 * @input - requires price of selected item
	 */
	public boolean verifyPrice(String expected_Price) {
		boolean out= false;
		out= price.equalsIgnoreCase(expected_Price);
		return out;
	}
	
	/**
	 * This method is to check if size of item selected is same as shown in cart
	 * @author - Lawanya
	 * @input - requires size of selected item
	 */
	public boolean verifySize(String expected_Size) {
		boolean out= false;
		out= size.equalsIgnoreCase(expected_Size);
		return out;
	}
	
	/**
	 * This method is to check if quantity of item selected is same as shown in cart
	 * @author - Lawanya
	 * @input - requires quantity of selected item
	 */
	public boolean verifyQuantity(int expected_Quantity) {
		boolean out= false;
		out= (quantity== expected_Quantity);
		return out;
	}
	
	/**
	 * This method is to check if the Cart has the selected item, its quantity is equal to 1 and has the selected size
	 * @author - Lawanya
	 */
	public boolean verifySelection() {
		boolean out = false;
		String expected_Price= driver.findElement(cart_Price).getText().substring(1).trim();
		String expected_Size= driver.findElement(cart_Size).getText();
		String items_Count= driver.findElement(items_In_Cart).getText().replace('"', ' ').trim();
		String[] quantity_detail = items_Count.split(" ");
		System.out.println(expected_Price+" "+ expected_Size);
		int expected_Quantity = Integer.parseInt(quantity_detail[1]);
		out= (verifyPrice(expected_Price) && verifySize(expected_Size)) && verifyQuantity(expected_Quantity); 
		return true;
	}
}
