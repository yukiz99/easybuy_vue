package com.zhu.easybuy.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig  implements WebMvcConfigurer  {
	
	@Autowired
    private LoginInterceptor loginInterceptor;

	    /**
	           * 配置拦截器
	     */
	    public void addInterceptors(InterceptorRegistry registry) {
  	      registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
	             .excludePathPatterns(
	                     "/**/*.html",
                         "/**/*.js",
                         "/**/*.jpg",
                         "/**/*.png",
                         "/**/*.gif",
                         "/**/*.css",
                         "/**/*.ts",
	                     "/api/member/user/login",
	                     "/api/member/user/register",
	                     "/api/member/news/allNews",
	                     "/api/member/category/findAllCategory",
	                     "/api/member/category/findAllProductAndCategory",
	                     "/api/member/product/search",
	                     "/api/member/product/findAllProductByCategoryId", 
	                     "/api/member/product/findProductById"
	                     );
     
      
	      /**
	        Index:
	        /api/member/news/allNews
	        /api/member/category/findAllCategory
	        /api/member/category/findAllProductAndCategory


	        ProductList:
	        /api/member/product/search
	        /api/member/product/findAllProductByCategoryId
	         
	         Product:
	         /api/member/product/findProductById
	        
	      **/

	   }
	    

}
