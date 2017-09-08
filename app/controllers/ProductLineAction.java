
package controllers;

import java.util.Date;

import models.ProductLine;

import org.bson.Document;

import play.mvc.Controller;
import framework.yaomy.mongo.pool.DBCollection;
import framework.yaomy.mongo.pool.GGMongoOperator;
import framework.yaomy.mongo.pool.WriteResult;
import framework.yaomy.util.ResultUtil;

/**
 * @Description:TODO
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @company xxxxxxxxxxxxxx
 * @copyright (c) 2017 yaomy Co'Ltd Inc. All rights reserved.
 * @date 2017年9月7日 下午2:04:18
 */
public class ProductLineAction extends Controller{

	public static void list(){
		render();
	}
	/**
	 * 
	 * @Description:渲染新增产品线视图
	 * @author yaomy
	 * @date 2017年9月7日 下午7:03:23
	 */
	public static void addProductLineView(){
		render();
	}
	/**
	 * 
	 * @Description:新增产品线信息
	 * @author yaomy
	 * @date 2017年9月7日 下午7:03:55
	 */
	public static void insertProductLine(ProductLine product){
		DBCollection collection = GGMongoOperator.getGGBusinessDBCollection("gg_product_line");
		
		Document doc = GGMongoOperator.newId(collection);
		doc.append("product_line", product.getProduct_line());
		doc.append("product_name", product.getProduct_name());
		doc.append("product_desc", product.getProduct_desc());
		doc.append("creator", "admin");
		doc.append("operator", "admin");
		doc.append("create_time", new Date());
		doc.append("update_time", new Date());
		
		WriteResult result = collection.insertOne(doc);
		if(result.getModifiedCount() == 1) {
			 renderJSON(ResultUtil.getReturnResult(1001, "新增产品线成功！"));
		} else {
			renderJSON(ResultUtil.getReturnResult(101, "新增产品线失败！"));
		}
	}
}