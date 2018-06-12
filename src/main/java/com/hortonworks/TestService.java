package com.hortonworks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("DataSourceService")
public class TestService {

	Logger logger = LoggerFactory.getLogger(TestService.class);

	@Autowired
	public Connection connection;

	@Autowired
	private Environment env;
	/**
	 * 
	 * @param query
	 *            - search msg
	 * @return List of Twitter2
	 */
	public String check(String query) {


		try {
			if (connection == null) {
				logger.error("Null connection");
				return "FAIL";
			}
			String query1 = env.getProperty("userBucket.path");
			PreparedStatement ps = connection.prepareStatement(query1);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				String first = res.getString(1);
				System.out.println(first);
			}

			res.close();
			ps.close();
			connection.close();
			res = null;
			ps = null;
			connection = null;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in check", e);
		}

		return "SUCCESS";
	}
}