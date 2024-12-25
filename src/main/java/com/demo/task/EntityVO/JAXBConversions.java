package com.demo.task.EntityVO;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.JAXBContextFactory;

public class JAXBConversions {
	public void JAXBUsertoJson(User user) throws Exception{
		user.setUserId(1);
		user.setPassword("lavanya13");
		user.setName("lavanya13");
		user.setEmail("lavanya13@gmail.com");
		Map<String, Object> properties = new HashMap<>();
        properties.put("eclipselink.media-type", "application/json");
        properties.put("eclipselink.json.include-root", false);
        JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[]{User.class}, properties);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(user, System.out);  
	}
	
	public User JAXBJsonToPojo(String json) throws JAXBException {
        // Define the properties to configure JAXB (for EclipseLink MOXy)
        Map<String, Object> properties = new HashMap<>();
        properties.put("eclipselink.media-type", "application/json");
        properties.put("eclipselink.json.include-root", true);

        // Create JAXBContext with the User class and properties
        JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[]{User.class},properties);

        // Create an Unmarshaller instance
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // Convert the JSON string to a User object using the Unmarshaller
        StringReader reader = new StringReader(json);
        User user = (User) unmarshaller.unmarshal(reader);  // Unmarshaling JSON into User

        return user;  // Return the User object
    }
	
	public static void main(String[] args) throws Exception {
		String json = "{\"userId\":1,\"name\":\"lavanya13\", \"email\":\"lavanya13@gmail.com\", \"password\":\"lavanya13\"}";
		JAXBConversions converter = new JAXBConversions();
		User user=new User(); 
       
        converter.JAXBUsertoJson(user);
        converter.JAXBJsonToPojo(json);
	}
}








//public User JAXBJsonToPojo(String json) throws Exception{
//try {
//ObjectMapper objectMapper=new ObjectMapper();
//User user=objectMapper.readValue(json,User.class);
//return user;
//}catch(Exception e) {
//	e.printStackTrace();
//	return null;
//}
//}
