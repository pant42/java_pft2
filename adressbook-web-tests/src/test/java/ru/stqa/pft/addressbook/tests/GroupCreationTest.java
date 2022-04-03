package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getGroupHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("Тест1", null, null));

  }

}
