package dao;

import dto.Input;
import dto.Output;

import java.sql.SQLException;

/**
 * Created by toprak on 24-Mar-17.
 */
public interface Repository {
  int saveInput(Input input) throws SQLException;
  void saveOutput(Output output);
}
