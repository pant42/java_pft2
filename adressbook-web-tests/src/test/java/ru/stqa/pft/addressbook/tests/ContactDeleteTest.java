package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeleteTest extends TestBase {

  @BeforeMethod
//Если нечего модифицировать - создай! как? вот тут и условие, надо ли создавать, как создавать, чем заполнить. Всё тут

  public void ensurePreconditions() {
    app.contact().gotoHomePage();

    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().
              withFirstname("СоздИмяУдалить").
              withLastname("СоздФамилияУдалить").
              withAddress("СтранаУдалить, область, город, улица, дом 5 кв 1").
              withHomeTel("463456789").
              withEmail("46@in.ru").
              withGroup("[none]")
      );
    }
  }

  @Test
  public void testContactDelete() throws Exception {

    List<ContactData> before = app.contact().contactList();
    int index = before.size() - 1;

    app.contact().delete(index);

    List<ContactData> after = app.contact().contactList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}
