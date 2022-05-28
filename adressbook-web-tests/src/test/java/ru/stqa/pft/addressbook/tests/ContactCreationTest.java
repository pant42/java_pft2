package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

  @DataProvider

  public Iterator<Object[]> validContacts() {
    File photo = new File("src/test/resources/stru.png");
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().
            withFirstname("СИмя1").
            withLastname("СФамилия1").
            withAddress("САдрес 1").
            withHomePhone("1634567891").
            withEmail("1sa@in.ru").
            withPhoto(photo)
    });
    list.add(new Object[]{new ContactData().
            withFirstname("СИмя2").
            withLastname("СФамилия2").
            withAddress("САдрес 2").
            withHomePhone("2634567892").
            withEmail("2sa@in.ru").
            withPhoto(photo)
    });
    list.add(new Object[]{new ContactData().
            withFirstname("СИмя3").
            withLastname("СФамилия3").
            withAddress("САдрес 3").
            withHomePhone("3634567893").
            withEmail("3sa@in.ru").
            withPhoto(photo)
    });
    return list.iterator();
  }
  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {

    app.contact().gotoHomePage();

    Contacts before = app.contact().allCache();

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
