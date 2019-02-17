package com.msa.console.config;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

//import com.sicc.console.common.CertificationInterceptor;


@Configuration
public class WebAppConfiguration extends WebMvcConfigurerAdapter{
	private static final String[] RESOURCE_LOCATIONS = {
	        "classpath:/static/"
	        };
/*	
	@Value("{tomcatAjp.protocol}")
	String ajpProtocol;
	
	@Value("{tomcatAjp.port}")
	int ajpPort;
	
	@Value("{tomcatAjp.enabled}")
	String ajpEnabled;
	
	@Value("{tomcatAjp.scheme}")
	String ajpScheme;*/
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
        .addResourceHandler("/**")
        .addResourceLocations(RESOURCE_LOCATIONS)
        .setCachePeriod(3600)
        .resourceChain(true)
        .addResolver(new PathResourceResolver());
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/*/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	@Bean
	public HttpMessageConverter<String> responseBodyConverter(){
		return new StringHttpMessageConverter(Charset.forName("UTF-8"));
	}
	
	@Bean
	public Filter characterEncodinfFilter(){
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
	
	/*
     * 로그인 인증 Interceptor 설정
     * */
//    @Autowired
//    CertificationInterceptor certificationInterceptor;
//    
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(certificationInterceptor)
//                .addPathPatterns("/*").excludePathPatterns("/login");
//    }
    
	/*@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		
		Boolean ajpEnabledBool = Boolean.valueOf(ajpEnabled);
		
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		
		//if(ajpEnabledBool) {
			Connector ajpConnector = new Connector("AJP/1.3");
			
			ajpConnector.setProtocol("AJP/1.3");
			ajpConnector.setPort(8009);
			ajpConnector.setSecure(false);
			ajpConnector.setAllowTrace(false);
			ajpConnector.setScheme("http");
			tomcat.addAdditionalTomcatConnectors(ajpConnector);
			
			
		//}
		
		return tomcat;
	}*/
	@Bean
	public MultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}

}