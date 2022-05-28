package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new GroupData().
            withName("Тест1").
            withHeader("header1").
            withFooter("footer1")
    });
    list.add(new Object[]{new GroupData().
            withName("Тест2").
            withHeader("header2").
            withFooter("footer2")
    });
    list.add(new Object[]{new GroupData().
            withName("Тест3").
            withHeader("header3").
            withFooter("footer3")
    });

    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {

    app.group().gotoGroupPage();

    Groups before = app.group().all();

    app.group().create(group);

    assertEquals(app.group().count(), before.size() + 1);
    Groups after = app.group().all();

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
