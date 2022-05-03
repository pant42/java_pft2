package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
//Если нечего модифицировать - создай! как? вот тут и условие, надо ли создавать, как создавать, чем заполнить. Всё тут

  public void ensurePreconditions() {
    app.goTo().homePage();

    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData(
              "Имя",
              "Фамилия",
              "Страна, область, город, улица, дом 5 кв 1",
              "123456",
              "aa@ii.ru",
              "[none]"));
    }
  }

  @Test
  public void testContactModification() {


    List<ContactData> before = app.contact().contactList();
    int index = before.size() - 1;

    ContactData contact = new ContactData(
            before.get(index).getId(),
            "ИмяUPD",
            "ФамилияUPD",
            "СтранаUPD, область, город, улица, дом 5 кв 1",
            "123456UPD",
            "UPD@ii.ru",
            "[none]");

    app.contact().modify(index, contact);


    List<ContactData> after = app.contact().contactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
