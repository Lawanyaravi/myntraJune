package StepDefinitions;


import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import Components.Homepage;
import Components.SearchResults;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyntraStepDefinition {
	WebDriver driver;
	Homepage homepage;
	SearchResults resultspage;
	String item;
	ArrayList<String> newTb;
	
	/**
	 * This method is to open Chrome browser
	 * @author - Lawanya
	 */
	@Before 
	public void start() {
		System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir")+"\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		newTb = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTb.get(1));
	}
	
	/**
	 * This method is to check if user is in Myntra homepage
	 * @author - Lawanya
	 */
	@Given("User is in homepage")
	public void user_is_in_homepage() {
		homepage = new Homepage(driver);
	    driver.get("https://www.myntra.com/");
	}

	/**
	 * This method is to check if Myntra logo is displayed in homepage
	 * @author - Lawanya
	 */
	@Given("Logo is visible")
	public void logo_is_visible() {
		
		boolean out= homepage.verifyLogoIsDisplayed();
		Assert.assertTrue(out);
	}

	/**
	 * This method is to check if the Cart has no items in it
	 * @author - Lawanya
	 * @input - requires default items count as 0
	 */
	@Then("Cart has no items")
	public void cart_has_no_items() {
	   boolean out = homepage.verifyItemsCount("0");
	   Assert.assertTrue(out);
	}

	/**
	 * This method is to search for items in Myntra
	 * @author - Lawanya
	 * @input - requires name of item to be searched
	 */
	@When("User searches {string}")
	public void user_searches(String string) {
	   homepage.searchItem(string);
	   item = string;
	}
	
	/**
	 * This method is to check if the Search results are shown
	 * @author - Lawanya
	 */
	@Then("Search results should be displayed")
	public void search_results_should_be_displayed() {
		resultspage = new SearchResults(driver);
	   boolean out= resultspage.verifyResultPage(item);
	   Assert.assertTrue(out);
	}

	/**
	 * This method is to check if Sort option is shown
	 * @author - Lawanya
	 */
	@Then("Sort option should be displayed")
	public void sort_option_should_be_displayed() {
	    boolean out= resultspage.verify_sort_field_is_Displayed();
	    Assert.assertTrue(out);	    
	}

	/**
	 * This method is to change Sort to Price: Low to High
	 * @author - Lawanya
	 */
	@When("User changes sort option")
	public void user_changes_sort_option(){
		resultspage.sortResults();
	}

	/**
	 * This method is to select the cheapest item
	 * @author - Lawanya
	 */
	@Then("Cheapest item should be selected")
	public void cheapest_item_should_be_selected(){
	  resultspage.selectCheapestItem();
	}

	/**
	 * This method is to check if the Item is available
	 * @author - Lawanya
	 */
	@When("Item is available")
	public void item_is_available() {
	   boolean out= resultspage.checkAvailability();
	   Assert.assertTrue(out);
	}

	/**
	 * This method is to select the available Size
	 * @author - Lawanya
	 */
	@Then("Select item size")
	public void select_item_size() {
	   resultspage.selectSize();
	}

	/**
	 * This method is to Add item to Bag
	 * @author - Lawanya
	 */
	@When("User clicks Add to Cart")
	public void user_clicks_Add_to_Cart() {
	   resultspage.addToCart();
	}
	
	/**
	 * This method is to check if the Cart items count is changed to 1
	 * @author - Lawanya
	 * @input - requires expected items count as 1
	 */
	@Then("Items count should be updated to {string}")
	public void items_count_should_be_updated_to(String string) {
		resultspage.verifyItemsCount(string);
	}
	
	
	/**
	 * This method is to check if the Go to Bag button is displayed
	 * @author - Lawanya
	 */
	@Then("Go to Cart is displayed")
	public void go_to_Cart_is_displayed() {
		boolean out = false;
		out = resultspage.verifyGoToCartButton();
		Assert.assertTrue(out);
	}

	/**
	 * This method is to click the Go to Bag button
	 * @author - Lawanya
	 */
	@When("User clicks Go to Cart")
	public void user_clicks_Go_to_Cart() {
	  resultspage.goToCart();
	}

	/**
	 * This method is to check if the Cart has the selected item, its quantity is equal to 1 and has the selected size
	 * @author - Lawanya
	 */
	@Then("Verify item in cart")
	public void verify_item_in_cart() {
		boolean out = resultspage.verifySelection();
		Assert.assertTrue(out);
	}


}
