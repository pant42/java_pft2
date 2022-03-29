package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {
  @Test
  public void testContactModification() {

    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectionContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData(
                    "ИмяUPD",
                    "ФамилияUPD",
                    "СтранаUPD, областьUPD, городUPD, улицаUPD, дом 5 кв 1",
                    "00110011",
                    "upd@ii.ru",
                    null),
            false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
