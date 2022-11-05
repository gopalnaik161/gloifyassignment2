package com.employee.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;

import java.util.Properties;

import com.mchange.v2.codegen.bean.PropsToStringGeneratorExtension;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value= {
		@ComponentScan("com.employee.spring.dao"),
		@ComponentScan("com.employee.spring.service"),
		
})
public class Appconfig {
	
	
	private Environment env;
	
	

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		
		Properties props = new Properties();
		props.put(DRIVER, env.getProperty("mysql.driver"));
		props.put(DRIVER, env.getProperty("mysql.url"));
		props.put(DRIVER, env.getProperty("mysql.user"));
		props.put(DRIVER, env.getProperty("mysql.password"));
		
		
		
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		
		
		props.put(C3P0_MIN_SIZE, env.getProperty("hiberante.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE, env.getProperty("hiberante.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hiberante.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT, env.getProperty("hiberante.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS, env.getProperty("hiberante.c3p0.max_statements"));
		
		
		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan("com.employee.spring.model");
		
		return factoryBean;
			
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		
		transactionManager .setSessionFactory(getSessionFactory().getObject());
		
		return transactionManager;
		
	}
	
	
	
	
}
