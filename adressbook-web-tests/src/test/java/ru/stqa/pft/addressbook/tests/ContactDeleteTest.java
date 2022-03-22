package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;


public class ContactDeleteTest extends TestBase {

  @Test
  public void testContactDelete() throws Exception {
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectionContact();
    app.getContactHelper().deletionContact();
    app.getContactHelper().alertAccept();
    app.returnToHomePage();

  }

}
