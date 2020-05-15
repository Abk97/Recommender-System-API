package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	@Autowired
	ProductRepo repo;	
	
	@RequestMapping("/")
	public String details()
	{
		return "prods";
	}
	
	@RequestMapping("/addProducts")
	public String addProducts(Products products)
	{
		repo.save(products);
		return "prods";
	}
	
	@RequestMapping("/getProducts")
	public String getProducts(Products products)
	{
	//List<Products> listProducts = repo.findAll();
		return "ViewProducts";
	}	

@PostMapping("/getProducts")
public ModelAndView getProducts(@RequestParam UUID pid)
{
	ModelAndView mv = new ModelAndView("Retrieve");
	Products products = repo.findById(pid).orElse(null);
	mv.addObject(products);
	return mv;
}

@GetMapping("/products")
@ResponseBody
public List<Products> getProducts() {
	return repo.findAll();
}

@GetMapping("/products/{pid}")
@ResponseBody
public Optional<Products> getProducts2(@PathVariable("pid") UUID pid) {
	return repo.findById(pid);
} 

@PostMapping(path="/products", consumes= {"application/json"})
public Products addProduct(@RequestBody Products products) {
	repo.save(products);
	return products;
}

@DeleteMapping("/products/{pid}")
public String deleteProduct(@PathVariable("pid") UUID pid) {
	Products prod = repo.getOne(pid);
	repo.delete(prod);
	return "deleted";
}

@PutMapping(path="/products", consumes= {"application/json"})
public Products updateProduct(@RequestBody Products products) {
	repo.save(products);
	return products;
}
}

