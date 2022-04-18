package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class ContactDeleteTest extends TestBase {

  @Test
  public void testContactDelete() throws Exception {
    app.getContactHelper().gotoHomePage();

    List<ContactData> before = app.getContactHelper().getContactList();

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(
              "Имя",
              "Фамилия",
              "Страна, область, город, улица, дом 5 кв 1",
              "00110011",
              "aa@ii.ru",
              "[none]"));
    }

    app.getContactHelper().selectionContact(before.size() - 1);
    app.getContactHelper().deletionContact();
    app.getContactHelper().alertAccept();
    app.getNavigationHelper().returnToHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }


}
