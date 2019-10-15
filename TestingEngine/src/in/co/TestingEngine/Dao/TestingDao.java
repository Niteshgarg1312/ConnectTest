package in.co.TestingEngine.Dao;

import in.co.TestingEngine.Modal.User_Details;

public interface TestingDao {

	int getUserSignUp(User_Details u);

	int getUserSignIn(User_Details u);

}
