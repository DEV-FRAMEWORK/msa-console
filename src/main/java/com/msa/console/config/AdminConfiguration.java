package com.msa.console.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.msa.console.dao.AdminDao;
import com.msa.console.dao.CodeDao;
import com.msa.console.dao.CompetitionDao;
import com.msa.console.dao.ContractDao;
import com.msa.console.dao.CustomerDao;
import com.msa.console.dao.MonitorDao;
import com.msa.console.dao.ServiceApplyDao;
import com.msa.console.dao.UtilDao;
import com.msa.console.dao.impl.AdminDaoImpl;
import com.msa.console.dao.impl.CodeDaoImpl;
import com.msa.console.dao.impl.CompetitionDaoImpl;
import com.msa.console.dao.impl.ContractDaoImpl;
import com.msa.console.dao.impl.CustomerDaoImpl;
import com.msa.console.dao.impl.MonitorDaoImpl;
import com.msa.console.dao.impl.ServiceApplyDaoImpl;
import com.msa.console.dao.impl.UtilDaoImpl; 

@Configuration
public class AdminConfiguration {
  
  @Bean
  public TilesConfigurer tilesConfigurer() {
	  final TilesConfigurer configurer = new TilesConfigurer();
	  configurer.setDefinitions(new String[] {"WEB-INF/tiles/tiles.xml"});
	  configurer.setCheckRefresh(true);
	  return configurer;
  }
  
  @Bean
  public TilesViewResolver tilesViewResolver() {
	  final TilesViewResolver resolver = new TilesViewResolver();
	  resolver.setViewClass(TilesView.class);
	  return resolver;
  }
  
  @Bean
  public UtilDao utilDao() {
	return new UtilDaoImpl(); 
  }
  
  @Bean
  public CodeDao codeDao() {
	return new CodeDaoImpl(); 
  }
  
  @Bean
  public AdminDao adminDao() {
	return new AdminDaoImpl();
  }
  
  @Bean
  public CustomerDao customerDao() {
	return new CustomerDaoImpl();
  }
  
  @Bean
  public ContractDao contractDao() {
	return new ContractDaoImpl();
  }
  
  @Bean
  public CompetitionDao competitionDao() {
	return new CompetitionDaoImpl();
  }
  
  @Bean
  public ServiceApplyDao serviceApplyDao() {
	return new ServiceApplyDaoImpl();
  }
  
  @Bean
  public MonitorDao monitorDao() {
	return new MonitorDaoImpl();
  }

}