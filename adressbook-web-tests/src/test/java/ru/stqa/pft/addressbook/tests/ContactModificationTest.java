package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
//Если нечего модифицировать - создай! как? вот тут и условие, надо ли создавать, как создавать, чем заполнить. Всё тут
  public void ensurePreconditions() {
    app.goTo().homePage();

    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().
              withFirstname("ИмяЕслиНетМодифицировать").
              withLastname("ФамилияЕслиНетМодифицировать")
      );
    }
  }

  @Test
  public void testContactModification() {


    Contacts before = app.contact().allCache();
    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).
            withFirstname("UPD").
            withLastname("UPD").
            withAddress("UPD, область, город, улица, дом 5 кв 1").
            withHomePhone("003456789").
            withEmail("00@in.ru").
            withGroup("[none]");

    app.contact().modify(contact);

    assertEquals(app.contact().count(), before.size());

    Contacts after = app.contact().allCache();

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
