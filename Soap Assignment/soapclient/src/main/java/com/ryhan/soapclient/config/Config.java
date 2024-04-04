package com.ryhan.soapclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;

@EnableWs
@Configuration
@ComponentScan("com.ryhan.soapclient")
public class Config {
	
	@Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.ryhan.soapclient.entity");
        return marshaller;

}
	
	 @Bean
	    public Client client(Jaxb2Marshaller marshaller) {
	        Client client = new Client();
	        
	        client.setDefaultUri("http://localhost:8089/updateStudentDetailsService");
	        client.setMarshaller(marshaller);
	        client.setUnmarshaller(marshaller);
	        return client;
	    }
	
	
}