package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c", description = "ContactCount")
  public int count;
  @Parameter(names = "-f", description = "TargetFile")
  public String file;

  public static void main(String[] args) throws IOException {

    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);

    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();

//    int count = Integer.parseInt(args[0]);
//    File file = new File(args[1]);
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }


  private List<ContactData> generateContacts(int count) {

    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().
              withFirstname(String.format("КонтИмяТест%s", i)).
              withLastname(String.format("КонтФамТест%s", i)).
              withAddress(String.format("Адрес%s", i)).
              withHomePhone(String.format("00000%s", i)).
              withEmail(String.format("qwer%s@ff.com", i))

      );
    }
    return contacts;
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);

    for (ContactData contact : contacts) {
      writer.write(String.format(
              "%s;%s;%s;%s;%s\n",

              contact.getFirstname(),
              contact.getLastname(),
              contact.getAddress(),
              contact.getHomePhone(),
              contact.getEmail()

      ));
    }
    writer.close();
  }


}

