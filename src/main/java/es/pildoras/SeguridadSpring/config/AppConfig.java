package es.pildoras.SeguridadSpring.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;



@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "es.pildoras.SeguridadSpring")
@PropertySource("classpath:application.properties")
public class AppConfig {
	
	@Autowired
	private Environment env;
	private Logger logger= Logger.getLogger(getClass().getName());
	
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/views/");
		vr.setSuffix(".jsp");
		return vr;
	}
	
	@Bean
	public DataSource securitySource() {
		
		//Crear pool
		ComboPooledDataSource combo = new ComboPooledDataSource();
		
		//Setear el driver
		try {
			combo.setDriverClass(env.getProperty("jdbc.driver"));
		}
		catch(PropertyVetoException e) {
			e.printStackTrace();
		}
		
		//Log de las propiedades de conexion
		logger.info("URL DE LA BBD: " + env.getProperty("jdbc.url"));
		logger.info("USUARIO CONECTADO: " + env.getProperty("jdbc.user"));
		
		//Setear propiedades de conexion
		combo.setJdbcUrl(env.getProperty("jdbc.url"));
		combo.setUser(env.getProperty("jdbc.user"));
		combo.setPassword(env.getProperty("jdbc.password"));
		
		//Establecer propiedades del pool
		combo.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		combo.setMaxPoolSize(Integer.parseInt(env.getProperty("conection.pool.maxPoolSize")));
		combo.setMinPoolSize(Integer.parseInt(env.getProperty("conection.pool.minPoolSize")));
		combo.setMaxIdleTime(Integer.parseInt(env.getProperty("conection.pool.maxIdleTime")));
		
		
		return combo;
	}
}



