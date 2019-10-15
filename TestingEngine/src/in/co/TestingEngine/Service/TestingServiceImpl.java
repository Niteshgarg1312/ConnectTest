package in.co.TestingEngine.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.TestingEngine.Dao.TestingDao;
import in.co.TestingEngine.Modal.User_Details;

@Service
public class TestingServiceImpl implements TestingService{
	
	@Autowired
	TestingDao testingDao;

	@Override
	public int getUserSignUp(User_Details u) {
		return testingDao.getUserSignUp(u);
	}

	@Override
	public int getUserSignIn(User_Details u) {
		return testingDao.getUserSignIn(u);
	}
	
}
