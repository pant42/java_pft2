package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.contact().gotoHomePage();

    Contacts before = app.contact().all();

    ContactData contact = new ContactData().
            withFirstname("СоздИмяСоздание").
            withLastname("СоздФамилияСоздание").
            withAddress("СтранаСоздание, область, город, улица, дом 5 кв 1").
            withHomeTel("463456789").
            withEmail("46@in.ru").
            withGroup("[none]");

    app.contact().create(contact);

    assertEquals(app.contact().count(), before.size() + 1);

    Contacts after = app.contact().all();

    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }


}
