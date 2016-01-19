/**
 * 
 */
package me.finiteloop.redhat.demo.fabric.fuse.twitter;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;

/**
 * @author klimaye
 *
 */
public class SearchTwitterRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		PropertiesComponent pc = new PropertiesComponent();
		pc.setLocation("classpath:twitter.properties");
		getContext().addComponent("properties", pc);

		from("direct:search-twitter")
		.to("twitter://search?type=polling&"
				+ "delay={{twitter.search.delay}}&"
				+ "keywords={{twitter.search.criteria}}&"
				+ "accessToken={{twitter.access.token}}&"
				+ "accessTokenSecret={{twitter.access.token.secret}}&"
				+ "consumerKey={{twitter.consumer.key}}&"
				+ "consumerSecret={{twitter.consumer.secret}}"
				+ "")
		.log("${body}")
		.to("direct:save-message");
		
	}

}
