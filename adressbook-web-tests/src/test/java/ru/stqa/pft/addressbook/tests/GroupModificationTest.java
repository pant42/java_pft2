package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
//Если нечего модифицировать - создай! как? вот тут и условие, надо ли создавать, как создавать, чем заполнить. Всё тут

  public void ensurePreconditions() {
    app.goTo().groupPage();

    if (!app.group().isThereAGroup()) {
      app.group().create(new GroupData().withName("Тест2"));
    }
  }

  @Test
  public void GroupModification() {

    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData().
            withId(modifiedGroup.getId()).
            withName("ТестUPD").
            withHeader("ТестUPD2").
            withFooter("ТестUPD3");

    app.group().modify(group);

    Groups after = app.group().all();

    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }


}
