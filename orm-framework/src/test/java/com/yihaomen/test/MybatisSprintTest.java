package com.yihaomen.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yihaomen.inter.IUserOperation;
import com.yihaomen.model.Article;
import com.yihaomen.model.User;


public class MybatisSprintTest {
    
	private static ApplicationContext ctx;  
    
    static 
    {  
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");  
    }        
      
    public static void main(String[] args)  
    {  
    	IUserOperation mapper = (IUserOperation)ctx.getBean("userMapper"); 
    	//测试id=1的用户查询，根据数据库中的情况，可以改成你自己的.
    	System.out.println("得到用户id=2的用户信息");
        User user = mapper.selectUserByID(2);
        System.out.println(user.getUserAddress()); 
        
        //得到文章列表测试
        System.out.println("得到用户id为2的所有文章列表");
        List<Article> articles = mapper.getUserArticles(2);
        
        for(Article article:articles){
        	System.out.println(article.getContent()+"--"+article.getTitle());
        }
        
    }  
 
	
}
