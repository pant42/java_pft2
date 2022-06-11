package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

  @DataProvider

  public Iterator<Object[]> validContacts() throws IOException {
    // Для формата файла = CSVFile photo = new File("src/test/resources/stru.png");

    // Для формата файла = CSV List<Object[]> list = new ArrayList<Object[]>();

    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }

    /*    while (line != null) {
      String[] split = line.split(";");

      list.add(new Object[]{new ContactData().
              withFirstname(split[0]).
              withLastname(split[1]).
              withAddress(split[2]).
              withHomePhone(split[3]).
              withEmail(split[4]).
              withPhoto(photo)
      });
      line = reader.readLine();
    }
    return list.iterator();
  }
*/

      XStream xstream = new XStream();
      xstream.allowTypes(new Class[]{ContactData.class});
      xstream.processAnnotations(ContactData.class);

      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {

    Contacts before = app.db().contacts();

    app.contact().gotoHomePage();
    app.contact().create(contact);

    assertEquals(app.contact().count(), before.size() + 1);

    Contacts after = app.db().contacts();

    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    verifyContactListInUi();
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());

    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
