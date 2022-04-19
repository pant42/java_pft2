package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactModificationTest extends TestBase {
  @Test
  public void testContactModification() {

    app.getContactHelper().gotoHomePage();

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(
              "Имя",
              "Фамилия",
              "Страна, область, город, улица, дом 5 кв 1",
              "123456",
              "aa@ii.ru",
              "[none]"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().selectionContact(before.size() - 1);
    app.getContactHelper().initContactModification(42);
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


    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
