package com.core.banking.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

/**
 * Spring Web Services configuration.
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext context) {

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "customers")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema customerSchema) {

        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("CustomerPort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace("http://core.banking/customer");
        wsdl.setSchema(customerSchema);

        return wsdl;
    }

    @Bean
    public XsdSchema customerSchema() {
        return new SimpleXsdSchema(
                new org.springframework.core.io.ClassPathResource("xsd/customer.xsd")
        );
    }
}
