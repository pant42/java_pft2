package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
//Если нечего модифицировать - создай! как? вот тут и условие, надо ли создавать, как создавать, чем заполнить. Всё тут
  public void ensurePreconditions() {
    app.goTo().homePage();

    if (!app.contact().isThereAContact()) {
      app.contact().create( new ContactData().
              withFirstname("ИмяЕслиНетМодифицировать").
              withLastname("ФамилияЕслиНетМодифицировать")
      );
    }
  }

  @Test
  public void testContactModification() {


    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData contact =  new ContactData().
            withId(modifiedContact.getId()).
            withFirstname("UPD").
            withLastname("UPD").
            withAddress("UPD, область, город, улица, дом 5 кв 1").
            withHomeTel("003456789").
            withEmail("00@in.ru").
            withGroup("[none]");

    app.contact().modify(contact);


    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);

    Assert.assertEquals(before, after);
  }


}
