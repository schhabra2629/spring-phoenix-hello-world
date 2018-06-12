package com.hortonworks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Shubham.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Configuration
	@Profile("default")
	static class LocalConfiguration {
		Logger logger = LoggerFactory.getLogger(LocalConfiguration.class);

	    @Value("${purl}")
	    private String databaseUri;
	    	    
		@Bean
		public Connection connection() throws ClassNotFoundException {
		        Connection con = null;
				try {
					System.setProperty("java.security.krb5.conf","/tmp/krb5.conf");
					//System.setProperty("java.security.krb5.conf","/etc/krb5.conf");
					System.setProperty("javax.security.auth.useSubjectCredsOnly","false");
					//System.setProperty("java.security.krb5.realm","EXAMPLE.COM");
					//System.setProperty("java.security.krb5.kdc","shubh-1.openstacklocal");
					//System.setProperty("sun.security.krb5.debug","true");

					logger.info("getting connection: "  + databaseUri);
					Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
					con = DriverManager.getConnection(databaseUri);
					logger.info("with connection");
				} catch (SQLException e) {
					e.printStackTrace();
					logger.error("Connection fail: ", e);
				}
			logger.error("Initialized hbase");
			
			return con;
		}
	}
}
