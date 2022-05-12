package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

  @BeforeMethod
//Если нечего модифицировать - создай! как? вот тут и условие, надо ли создавать, как создавать, чем заполнить. Всё тут

  public void ensurePreconditions() {
    app.contact().gotoHomePage();

    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().
              withFirstname("СоздИмяУдалить").
              withLastname("СоздФамилияУдалить"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();

    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

//Сами проверки----------------------------------------------------------------------------------------
    MatcherAssert.assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllEmails(), equalTo(margeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAllPhones(), equalTo(margePhones(contactInfoFromEditForm)));
//-----------------------------------------------------------------------------------------------------
  }

  private String margeEmails(ContactData contact) {
    return Arrays.asList(

                    contact.getEmail(),
                    contact.getEmail2(),
                    contact.getEmail3())

            .stream().filter((s) -> !s.equals("")).
            collect(Collectors.joining("\n"));
  }

  private String margePhones(ContactData contact) {
    return Arrays.asList(

                    contact.getHomePhone(),
                    contact.getMobilePhone(),
                    contact.getWorkPhone())

            .stream().filter((s) -> !s.equals("")).
            map(ContactPhoneTest::cleaned).
            collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.
            replaceAll("\\s", "").
            replaceAll("[-()]", "");
  }


}
