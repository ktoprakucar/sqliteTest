import dao.Repository;
import dto.Input;
import impl.RepoImpl;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by toprak on 24-Mar-17.
 */
public class sqliteTest {
  Input input;

  @Test
  public void test_insert_input() throws SQLException, ClassNotFoundException {
    input = new Input(new Date(2016, 03,13), 1111222233334444L, "KENT");
    Repository repo = new RepoImpl();
    int id = repo.saveInput(input);
    System.out.println(id);
  }



}

