#@featureTest
Feature: Harry Potter book, prepare to checkout

  #Amazon search for Harry Potter
  #  view book details
  #  add to basket
  #  edit basket
  #Automation
  #@Scenario1
  Scenario: Find the book
    Given I navigate to "https://www.amazon.co.uk/"
    #Navigate to
    #Verify that the page is correct and opened
    #When I search for ...
    And I search for "Harry Potter and the Cursed Child"
    #  in section books
    Then the first item has the title "Harry Potter and the Cursed Child - Parts One and Two"
    # Verify that “”
    And it has not a badge "Best Seller"
    And selected type is "Paperback"
    And the price is "£5.99"

  #
  #@Scenario2
  Scenario: View book details
    Given I navigate to book details page "https://www.amazon.co.uk/" "Harry Potter and the Cursed Child - Parts One and Two"
    #Then navigate to the book details
    #Verify title, badge, type, price
    #When xxx
    Then the title contains "Harry Potter and the Cursed Child - Parts One and Two"
    ## it looks like the Best Seller badge is not shown on BookDetails page
    ##   that's why the following is commented
    #And it has not a badge "Best Seller"
    And rdSelected type is "Paperback"
    And rdThe price is "£5.99"
#
  Scenario: Add the book to the basket
    Given I add the book to the basket "https://www.amazon.co.uk/" "Harry Potter and the Cursed Child - Parts One and Two"
    #Verify that the notification is shown
    #When xxx
    Then a notification "Added to Baskettttt" is shown
    And there is "1 item" in the basket
#
  Scenario: Edit the basket
    #Click on edit the basket
    Given I navigate to edit basket page "https://www.amazon.co.uk/" "Harry Potter and the Cursed Child - Parts One and Two"
    #When xxx
    #Verify that
    #Then the book is shown on the list
    Then the title in basket contains "Harry Potter and the Cursed Child - Parts One and Two"
    And selected print type is "Paperback",
    And the price in basket is "£5.99"
    And the quantity is "1"
    And the total price is "£5.99"
