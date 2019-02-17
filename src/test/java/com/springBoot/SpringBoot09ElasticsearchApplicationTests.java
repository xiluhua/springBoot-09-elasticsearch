package com.springBoot;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springBoot.entity.Article;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot09ElasticsearchApplicationTests {

	@Resource
	JestClient jestClient;
	
	@Test
	public void contextLoads() {
		Article article = new Article();
		article.setId(3);
		article.setAuthor("李四");
		article.setContent("水浒传");
		article.setTitle("大话水浒传");
		Index index = new Index.Builder(article).index("article").type("story").id("3").build();
		
		try {
			jestClient.execute(index);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void search() {
		String json = "";
		Search search = new Search.Builder(json).addIndex("article").addType("story").build();
		try {
			SearchResult searchResult = jestClient.execute(search);
			System.out.println(searchResult.getJsonString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
