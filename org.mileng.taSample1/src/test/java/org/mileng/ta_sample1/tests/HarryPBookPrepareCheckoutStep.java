package org.mileng.ta_sample1.tests;

import org.mileng.ta_sample1.pages.AmazonBasket;
import org.mileng.ta_sample1.pages.AmazonHome;
import org.mileng.ta_sample1.pages.EditBasket;
import org.mileng.ta_sample1.pages.ResultDetails;
import org.mileng.ta_sample1.pages.SearchResults;
import org.testng.Assert;

//import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class HarryPBookPrepareCheckoutStep extends TestBase {

	AmazonHome homePage;
	SearchResults searchResultsPage;
	ResultDetails resultDetailsPage;
	AmazonBasket basketPage;
	EditBasket editBasketPage;

	// @Given("^I navigate to \"([^\"]*)\"$")
	// public void i_navigate_to_amazon_co_uk() throws Throwable {
	// // amazon\\.co\\.uk$
	// // Write code here that turns the phrase above into concrete actions
	// throw new PendingException();
	// }

	@Given("^I navigate to \"(.*?)\"$")
	public void iNavigateTo(String arg1) throws Throwable {
		startTest("AmazonTest>scenario 1 find HarryPotterBook");

		startStep(String.format("Go to Amazon home page '%s'", arg1));
		homePage = goToAmazonHome(arg1);
		endStep();
	}

	// @Then("^the first item\\(s\\) has the title \"(.*?)\"$")
	// public void theFirstItemSHasTheTitle(String arg1) throws Throwable {
	//// throw new PendingException();
	// }

	@And("^I search for \"(.*?)\"$")
	public void iSearchFor(String arg1) throws Throwable {
		startStep(String.format("Search for '%s'", arg1));
		searchResultsPage = homePage.searchFor(arg1);
		endStep();
	}

	@Then("^the first item has the title \"(.*?)\"$")
	public void theFirstItemHasTheTitle(String arg1) throws Throwable {
		// throw new PendingException();
		startStep(String.format("Title contains '%s'", arg1));
		Assert.assertTrue(searchResultsPage.verifyFirstItemTitle(arg1));
		endStep();
	}

	@And("^it has not a badge \\\"(.*?)\\\"$")
	public void itHasNotABadge(String arg1) throws Throwable {
		startStep(String.format("It has not a badge '%s'", arg1));
		boolean hasToBeTrue = !searchResultsPage.verifyHasBadge(searchResultsPage.getParentXpath(), arg1);
		// check one Assert
		Assert.assertTrue(hasToBeTrue);
		// check two ...
		endStep(hasToBeTrue);
	}

	@And("^selected type is \"(.*?)\"$")
	public void selectedTypeIs(String arg1) throws Throwable {
		// throw new PendingException();
		startStep(String.format("Selected type is '%s'", arg1));
		endStep(searchResultsPage.verifySelectedType(searchResultsPage.getParentXpath(), arg1));
	}
	//
	// @Then("^the first item\\(s\\) has the title \"(.*?)\"$")
	// public void theFirstItemSHasTheTitle(String arg1) throws Throwable {
	// //throw new PendingException();
	// }

	@And("^the price is \"(.*?)\"$")
	public void thePriceIs(String arg1) throws Throwable {
		startStep(String.format("The price is '%s'", arg1));
		endStep(searchResultsPage.verifyPrice(searchResultsPage.getParentXpath(), arg1));

		endTest();
	}

	// @Given("^I navigate to book details page$")
	// public void iNavigateToBookDetailsPage(String arg1, String arg2) throws
	// Throwable {
	// startStep(String.format("Find the same book and Click on result's title and
	// go to book details page"));
	// homePage = goToAmazonHome(arg1);
	// searchResultsPage = homePage.searchFor(arg2);
	// // the following is to set searchResultsPage.parentXpath and .title
	// searchResultsPage.verifyFirstItemTitle(arg2);
	// // TODo 1 the following could be optimized, because Title is used on two
	// places
	// resultDetailsPage =
	// searchResultsPage.goToResultDetails(searchResultsPage.getParentXpath(),
	// searchResultsPage.getTitle());
	// endStep();
	//
	// endTest();
	// }

	@Given("^I navigate to book details page \"(.*?)\" \"(.*?)\"$")
	public void iNavigateToBookDetailsPage(String arg1, String arg2) throws Throwable {
		startTest("AmazonTest>scenario 2 view HarryPotterBook details");

		startStep(String.format(
				"Find the same ('%s') book on '%s'." + " Click on result's title and go to book details page", arg2,
				arg1));
		homePage = goToAmazonHome(arg1);
		searchResultsPage = homePage.searchFor(arg2);
		// the following is to set searchResultsPage.parentXpath and .title
		searchResultsPage.verifyFirstItemTitle(arg2);
		// TODo 1 the following could be optimized, because Title is used on two places
		resultDetailsPage = searchResultsPage.goToResultDetails(searchResultsPage.getParentXpath(),
				searchResultsPage.getTitle());
		endStep();
	}

	@Then("^the title contains \"(.*?)\"$")
	public void theTitleContains(String arg1) throws Throwable {
		startStep(String.format("Title contains '%s'", arg1));
		Assert.assertTrue(resultDetailsPage.verifyTitle(arg1));
		endStep();
	}

	@And("^rdSelected type is \"(.*?)\"$")
	public void rdSelectedTypeIs(String arg1) throws Throwable {
		// throw new PendingException();
		startStep(String.format("Selected (print) type is '%s'", arg1));
		endStep(resultDetailsPage.verifyPrintType(arg1));
	}
	//
	// @Then("^the first item\\(s\\) has the title \"(.*?)\"$")
	// public void theFirstItemSHasTheTitle(String arg1) throws Throwable {
	// //throw new PendingException();
	// }

	@And("^rdThe price is \"(.*?)\"$")
	public void rdThePriceIs(String arg1) throws Throwable {
		startStep(String.format("The price is '%s'", arg1));
		endStep(resultDetailsPage.verifyPrice(arg1));

		endTest();
	}

	@Given("^I add the book to the basket \"(.*?)\" \"(.*?)\"$")
	public void iAddTheBookToTheBasket(String arg1, String arg2) throws Throwable {
		startTest("AmazonTest>scenario 3 find and view HarryPotterBook details, add to basket");

		startStep(
				String.format("Find the same ('%s') book on '%s'. Go to book details page. Add to basket", arg2, arg1));
		homePage = goToAmazonHome(arg1);
		searchResultsPage = homePage.searchFor(arg2);
		// the following is to set searchResultsPage.parentXpath and .title
		searchResultsPage.verifyFirstItemTitle(arg2);
		// TODo 1 the following could be optimized, because Title is used on two places
		resultDetailsPage = searchResultsPage.goToResultDetails(searchResultsPage.getParentXpath(),
				searchResultsPage.getTitle());
		basketPage = resultDetailsPage.addResultToBasket();
		endStep();
	}

	@Then("^a notification \"(.*?)\" is shown$")
	public void aNotificationIsShown(String arg1) throws Throwable {
		startStep(String.format("Verify notification '%s'", arg1));
		endStep(basketPage.verifyNotification(arg1));
	}

	@And("^there is \"(.*?)\" in the basket$")
	public void thereIsInTheBasket(String arg1) throws Throwable {
		startStep(String.format("Verify items number by text '%s'", arg1));
		endStep(basketPage.verifyItemsNumberByText(arg1));

		endTest();
	}

	@Given("^I navigate to edit basket page \"(.*?)\" \"(.*?)\"$")
	public void iNavigateToEditBasketPage(String arg1, String arg2) throws Throwable {
		startTest("AmazonTest>scenario 4 find and view HarryPotterBook details, add to basket, edit basket");

		startStep(String.format(
				"Find the same ('%s') book on '%s'. Go to book details page. Add to basket. Edit basket", arg2, arg1));
		homePage = goToAmazonHome(arg1);
		searchResultsPage = homePage.searchFor(arg2);
		// the following is to set searchResultsPage.parentXpath and .title
		searchResultsPage.verifyFirstItemTitle(arg2);
		// TODo 1 the following could be optimized, because Title is used on two places
		resultDetailsPage = searchResultsPage.goToResultDetails(searchResultsPage.getParentXpath(),
				searchResultsPage.getTitle());
		basketPage = resultDetailsPage.addResultToBasket();
		editBasketPage = basketPage.editBasket();
		endStep();
	}

	@Then("^the title in basket contains \"(.*?)\"$")
	public void theTitleInBasketContains(String arg1) throws Throwable {
		startStep(String.format("The title in basket contains '%s'", arg1));
		endStep(editBasketPage.verifyTitle(arg1));
	}

	@And("^selected print type is \"(.*?)\",$")
	public void selectedPrintTypeIs(String arg1) throws Throwable {
		startStep(String.format("Verify selected print type '%s'", arg1));
		endStep(editBasketPage.verifySelectedPrintType(arg1));
	}

	@And("^the price in basket is \"(.*?)\"$")
	public void thePriceInBasketIs(String arg1) throws Throwable {
		startStep(String.format("Verify price '%s'", arg1));
		endStep(editBasketPage.verifyPrice(arg1));
	}

	@And("^the quantity is \"(.*?)\"$")
	public void theQuantityIs(String arg1) throws Throwable {
		startStep(String.format("Verify quantity '%s'", arg1));
		endStep(editBasketPage.verifyQuantity(arg1));
	}

	@And("^the total price is \"(.*?)\"$")
	public void theTotalPriceIs(String arg1) throws Throwable {
		startStep(String.format("Verify total price '%s'", arg1));
		endStep(editBasketPage.verifyTotalPrice(arg1));
		
		endTest();
	}
}
