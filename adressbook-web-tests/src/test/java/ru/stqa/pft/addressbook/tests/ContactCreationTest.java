package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().gotoHomePage();

    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().createContact(new ContactData(
            "СоздИмя",
            "СоздФамилия",
            "Страна, область, город, улица, дом 5 кв 1",
            "123456789",
            "aa@in.ru",
            "[none]"));

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);
  }

}
