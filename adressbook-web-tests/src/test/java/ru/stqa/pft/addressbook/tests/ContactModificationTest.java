package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
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
    ContactData contact = new ContactData(
            before.get(before.size() - 1).getId(),
            "ИмяUPD",
            "ФамилияUPD",
            "СтранаUPD, область, город, улица, дом 5 кв 1",
            "123456UPD",
            "UPD@ii.ru",
            "[none]");
    app.getContactHelper().initContactModification(before.size() - 1);

    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();


    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
