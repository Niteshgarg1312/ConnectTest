package in.co.TestingEngine.Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.co.TestingEngine.Modal.User_Details;

@Repository
public class TestingDaoImpl implements TestingDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int getUserSignUp(User_Details u) {
		int i=0;
		final String procedureCall = "{call serDetails(?,?,?,?,?,?)}";
		Connection connection = null;
		try {	
		connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "normalSignUp");
		callableSt.setString(2, u.getUserName());
		callableSt.setString(3, u.getUserEmailId());
		callableSt.setString(4, u.getUserMobileNumber());
		callableSt.setString(5, u.getUserPhotoLink());
		callableSt.setString(6, u.getUserPassword());
		
		callableSt.execute();
		i=1;
	    }
		catch(Exception e)
		{
			i=0;
			e.printStackTrace();
		}
		return i;

	}

	@Override
	public int getUserSignIn(User_Details u) {
		int i=0;
		final String procedureCall = "{call normalUserDetails(?,?,?,?,?,?)}";
		Connection connection = null;
		try {	
		connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "normalSignIn");
		callableSt.setString(2, null);
		callableSt.setString(3, u.getUserEmailId());
		callableSt.setString(4, null);
		callableSt.setString(5, null);
		callableSt.setString(6, null);
		
		ResultSet rs=callableSt.executeQuery();
		if(rs.next()) {
			if(rs.getString("userPassword").equals(u.getUserPassword()))
					{
				             i=1;
					}
			else
			{
				i=0;
			}
		}
		else
		{
			i=2;
		}
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;

	}

}
