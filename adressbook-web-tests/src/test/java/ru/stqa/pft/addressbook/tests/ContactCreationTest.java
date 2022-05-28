package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.contact().gotoHomePage();

    Contacts before = app.contact().allCache();

    File photo = new File("src/test/resources/stru.png");

    ContactData contact = new ContactData().
            withFirstname("СоздИмяСоздание").
            withLastname("СоздФамилияСоздание").
            withAddress("СтранаСоздание, область, город, улица, дом 5 кв 1").
            withHomePhone("463456789").
            withEmail("46@in.ru").
            withPhoto(photo);

    app.contact().create(contact);

    assertEquals(app.contact().count(), before.size() + 1);

    Contacts after = app.contact().allCache();

    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());

    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
