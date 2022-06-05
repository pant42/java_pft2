package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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

public class GroupCreationTest extends TestBase {

  @DataProvider

  public Iterator<Object[]> validGroups() throws IOException {

    // Для формата файла = CSV   List<Object[]> list = new ArrayList<Object[]>();

    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();

      while (line != null) {
        xml += line;
        line = reader.readLine();
      }


    /* Для формата файла = CSV
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")))){

      while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new GroupData().
              withName(split[0]).
              withHeader(split[1]).
              withFooter(split[2])
      });
      line = reader.readLine();
    }
    return list.iterator();
  }
*/
      XStream xstream = new XStream();
      xstream.allowTypes(new Class[]{GroupData.class});
      xstream.processAnnotations(GroupData.class);

      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }


  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {

    app.group().gotoGroupPage();

    Groups before = app.db().groups();

    app.group().create(group);

    assertEquals(app.group().count(), before.size() + 1);
    Groups after = app.db().groups();

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test(enabled = false)
  public void testBadGroupCreation() throws Exception {

    app.group().gotoGroupPage();

    Groups before = app.group().all();

    GroupData group = new GroupData().
            withName("Тест2'");

    app.group().create(group);

    assertEquals(app.group().count(), before.size());

    Groups after = app.group().all();

    assertThat(after, equalTo(before));
  }


}
