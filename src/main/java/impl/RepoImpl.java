package impl;


import dao.Repository;
import dto.Input;
import dto.Output;

import java.sql.*;

public class RepoImpl implements Repository {
  public static final String JDBC_NAME = "jdbc:sqlite:test.db";

  public RepoImpl() throws ClassNotFoundException, SQLException {
    //createTables();
  }

  private void createTables() {
    try {
      Class.forName("org.sqlite.JDBC");
      Connection connection = DriverManager.getConnection(JDBC_NAME);
      Statement statement = connection.createStatement();
      StringBuilder inputSql = new StringBuilder();
      inputSql.append("CREATE TABLE INPUT \n" +
              "(ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
              "PROCESS_DATE DATE NOT NULL,\n" +
              "CARD_NUMBER BIGINT NOT NULL, \n" +
              "CODE CHAR(20))");
      statement.execute(inputSql.toString());
      statement.close();
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int saveInput(Input input) throws SQLException {
    int id = 0;
    try {
      Class.forName("org.sqlite.JDBC");
      Connection connection = DriverManager.getConnection(JDBC_NAME);
      ResultSet rs;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("INSERT INTO INPUT\n" +
              "(PROCESS_DATE,\n" +
              "CARD_NUMBER,\n" +
              "CODE )\n" +
              "VALUES ( ?, ?, ? )");
      PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
      preparedStatement.setDate(1, input.date);
      preparedStatement.setLong(2, input.cardNumber);
      preparedStatement.setString(3, input.discountCode);
      preparedStatement.execute();
      id = retrieveLastInsertedId(id, connection);
      connection.close();
      return id;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return -1;
  }

  private int retrieveLastInsertedId(int id, Connection connection) throws SQLException {
    PreparedStatement preparedStatement;
    ResultSet rs;
    preparedStatement = connection.prepareStatement("SELECT last_insert_rowid()");
    rs = preparedStatement.executeQuery();

    while (rs.next())
      id = rs.getInt(1);

    preparedStatement.close();
    return id;
  }

  @Override
  public void saveOutput(Output output) {

  }


}
