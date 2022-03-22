package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getGroupHelper().gotoGroupPage();
    app.initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("Тест1", "Тест2", "Тест3"));
    app.getGroupHelper().submitGroupCreation();
    app.returnToGroupPage();
  }

}
