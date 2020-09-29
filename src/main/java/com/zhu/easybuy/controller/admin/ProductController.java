package com.zhu.easybuy.controller.admin;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zhu.easybuy.pojo.Product;
import com.zhu.easybuy.pojo.Result;
import com.zhu.easybuy.service.ProductService;
import com.zhu.easybuy.util.CommonUtils;
import com.zhu.easybuy.util.FileUtil;


@RestController("AdminProductController")
@RequestMapping("/api/admin/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/allProduct")
	public Result allProduct(int pageNum,int pageSize) {
		return new Result(true,"查询成功",productService.allProduct(pageNum,pageSize));
	}
	

	@RequestMapping("/findProductById")
	public Result findProductById(int id) {
		return new Result(true,"查询成功",productService.findProductById(id));
	}
	
//	@RequestMapping("/addProduct")
//	public Result addProduct(@RequestBody Product product,HttpServletRequest request) {
//		product.setIsDelete(0);//设置状态
//		productService.addProduct(product);
//		return new Result(true,"添加成功");
//	}
	
	@RequestMapping("/addProduct")
	public Result addProduct(Product product,MultipartFile file,HttpServletRequest request) {
		String fileName;
		try {
			fileName = doUpload(request, file);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"上传失败");
		}
		product.setFileName(fileName);
		product.setIsDelete(0);//设置状态
		productService.addProduct(product);   
		return new Result(true,"添加成功"); 
	}  
	
	private String doUpload(HttpServletRequest request,MultipartFile file) throws IOException, Exception {
	    String filename = file.getOriginalFilename();
        String suffixName=filename.substring(filename.lastIndexOf("."));
        String filePath =ClassLoader.getSystemResource("static/files/").getPath();//上传到了target的static/files下
        String newName=CommonUtils.uuid()+suffixName;
      
        FileUtil.uploadFile(file.getBytes(), filePath, newName);
        return newName;
	}
	
	@RequestMapping("/updateProduct")
	public Result updateProduct(Product product,MultipartFile file,HttpServletRequest request) {
		if(file!=null) {
				String fileName;
				try {
					fileName = doUpload(request, file);
				} catch (Exception e) {
					e.printStackTrace();
					return new Result(false,"上传失败");
				}
				product.setFileName(fileName);
		}
		productService.updateProduct(product);   
		return new Result(true,"修改成功"); 
	}  
	
	@RequestMapping("/deleteProduct")
	public Result deleteProduct(int id) {
		productService.deleteProduct(id);//逻辑删除
		return new Result(true,"删除成功");
	}
	
	
	
}
