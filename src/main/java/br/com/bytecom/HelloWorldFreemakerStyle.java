package br.com.bytecom;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clairton
 */
public class HelloWorldFreemakerStyle {

    public static void main(String args[]) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemakerStyle.class, "/");
        
        try {
            Template helloTemplate = configuration.getTemplate("hello.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name", "Bytecom");
            
            helloTemplate.process(helloMap, writer);
            
            System.out.println(writer);
        } catch (IOException ex) {
            Logger.getLogger(HelloWorldFreemakerStyle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateException ex) {
            Logger.getLogger(HelloWorldFreemakerStyle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
