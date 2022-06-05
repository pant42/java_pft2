package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);


  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


  @BeforeSuite(alwaysRun = true)
  protected void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.logout();
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test" + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test" + m.getName());
  }

  public void verifyGroupListInUi() {

//Пример строки в настройках проекта:  -ea -DverifyUI=true
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();

      MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(
//Сравниваем uiGroups с dbGroups которые потоком преобразованы из групп с полным набором атрибутов в группы без хедера и футера (равным по-умолчанию null)
              dbGroups.stream().map((g) -> new GroupData().
                      withId(g.getId()).
                      withName(g.getName())

              ).collect(Collectors.toSet())
      ));
    }
  }

  public void verifyContactListInUi() {

//Пример строки в настройках проекта:  -ea -DverifyUI=true
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().allCache();

      MatcherAssert.assertThat(uiContacts, CoreMatchers.equalTo(
//Сравниваем uiGroups с dbGroups которые потоком преобразованы из групп с полным набором атрибутов в группы без хедера и футера (равным по-умолчанию null)
              dbContacts.stream().map((g) -> new ContactData().
              /*У меня сравнение в TestBase идет по этим полям. Если вдруг нужно расширить кол-во полей для сравнения:
              -Перегенерируем Equals and HashSet-ы на поля, которые потребуются;
              -Так же, ToString Пересоздадим, там же
              -Генератор тестданных дополняем требуемыми полями, а так же, изменим метод для заполнения формы (fillContactForm) */
                      withId(g.getId()).
                      withFirstname(g.getFirstname()).
                      withLastname(g.getLastname()).
                      withAddress(g.getAddress()).
                      withHomePhone(g.getHomePhone()).
                      withEmail(g.getEmail())

              ).collect(Collectors.toSet())
      ));
    }
  }

}
