package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


public class ContactDeleteTest extends TestBase {

  @Test
  public void testContactDelete() throws Exception {
    app.getContactHelper().gotoHomePage();

    int before = app.getContactHelper().getContactCount();

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(
              "Имя",
              "Фамилия",
              "Страна, область, город, улица, дом 5 кв 1",
              "00110011",
              "aa@ii.ru",
              "[none]"));
    }

    app.getContactHelper().selectionContact(before-1);
    app.getContactHelper().deletionContact();
    app.getContactHelper().alertAccept();
    app.getNavigationHelper().returnToHomePage();

    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

}
