package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class ConnectionDbTest {
  @Test
  public void testConnectionDb() {

    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://Localhost:3306/addressbook?user=root&password=");

      Statement st = conn.createStatement();

      ResultSet rs = st.executeQuery(
              "select GROUP_ID, GROUP_NAME, GROUP_HEADER, GROUP_FOOTER from GROUP_LIST");

      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().
                withId(rs.getInt("GROUP_ID")).
                withName(rs.getString("GROUP_NAME")).
                withHeader(rs.getString("GROUP_HEADER")).
                withFooter(rs.getString("GROUP_FOOTER"))
        );
      }

      rs.close();
      st.close();
      conn.close();

      System.out.println(groups);

    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
