package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {
  //-f src/test/resources/groups.xml -c 50 -d xml
  //C:\Learning\java_pft2\adressbook-web-tests
  @Parameter(names = "-c", description = "GroupCount")
  public int count;
  @Parameter(names = "-f", description = "TargetFile")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {

    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander jCommander = new JCommander(generator);

    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  //----------------------------------------------------------------------------------------------------------------------
  private void run() throws IOException {
    List<GroupData> groups = generateGroups(count);

    if (format.equals("csv")) {
      saveAsCsv(groups, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(groups, new File(file));
    } else {
      System.out.println("Unrecognized format for group's TestData" + format);
    }
  }

  //-----------------------saveAsXml-------------------------
  private void saveAsXml(List<GroupData> groups, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(GroupData.class);
    String xml = xStream.toXML(groups);

    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  //-----------------------saveAsCsv--------------------------
  private void saveAsCsv(List<GroupData> groups, File file) throws IOException {

    try (Writer writer = new FileWriter(file)) {
      for (GroupData group : groups) {
        writer.write(String.format(
                "%s;%s;%s\n",

                group.getName(),
                group.getHeader(),
                group.getFooter()
        ));
      }
    }
  }
//--------------------------------------------------------------------------------------------------------------------

  private List<GroupData> generateGroups(int count) {

    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData().
              withName(String.format("xname%s", i)).
              withHeader(String.format("xheader%s", i)).
              withFooter(String.format("xfooter%s", i))

      );
    }
    return groups;
  }


}
