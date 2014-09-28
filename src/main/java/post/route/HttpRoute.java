package post.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import post.route.api.AddressRoute;

/**
 * HTTP受信 <br>
 * 返却値は自動的にjsonに変換される 
 * 
 */
@Component
public class HttpRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		rest("/address")
		.get("/zip").to(AddressRoute.IN);
		
	}

}
