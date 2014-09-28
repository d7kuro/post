package post.route.api;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sql.SqlConstants;
import org.springframework.stereotype.Component;

/**
 * 000-0000形式の郵便番号からjsonデータを返す
 */
@Component
public class AddressRoute extends RouteBuilder {

	public static final String CLASSNAME = AddressRoute.class.getSimpleName();
	public static final String IN = "direct:in" + CLASSNAME;

	@Override
	public void configure() throws Exception {
		from(IN).routeId(CLASSNAME)
		
		// SQL実行
		.to("sql:SELECT * FROM ad_address WHERE zip = :#zip ")
		
		// 見つからなかった時の処理
		.choice()
		.when(header(SqlConstants.SQL_ROW_COUNT).isEqualTo(0))
			.setBody(constant(null))
		.otherwise()
			// 結果データ作成（List<Map>の0番目のデータを返却）
			.setBody().simple("${body[0]}")
		.end()
		;
	}

}
