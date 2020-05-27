package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ModelAndView getProducts(Products products)
	{
		ModelAndView mv = new ModelAndView("ViewProducts");
		List<Products> list = repo.findAll();
		Hashtable<UUID, String> table = new Hashtable<UUID, String>();
		//ArrayList<UUID> ids = new ArrayList<UUID>();
		//ArrayList<String> names = new ArrayList<String>();
		for (Products p : list) {
			table.put(p.getPid(), p.getName());
			//names.add(p.getName());
			//ids.add(p.getPid());
		}
		//mv.addObject("names", names);
		//mv.addObject("ids", ids);
		mv.addObject("table", table);
		return mv;
	}

@RequestMapping("/showProducts")
public ModelAndView getProducts1(@RequestParam UUID pid)
{
	ModelAndView mv = new ModelAndView("Retrieve");
	Products product = repo.findById(pid).orElse(null);
	//System.out.println(product.getName());
	mv.addObject("product", product);
	return mv;
}

@PostMapping(path="/products", consumes= {"application/json"})
public ResponseEntity<Void> addProducts1(@RequestBody Products product) {
	repo.save(product);
	return new ResponseEntity<>(HttpStatus.CREATED);
}

@GetMapping("/products")
@ResponseBody
public ResponseEntity<List<Products>> getAllProducts() {
	return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
}

@GetMapping("/products/{pid}")
@ResponseBody
public ResponseEntity<Optional<Products>> getOneProducts(@PathVariable("pid") UUID pid) {
	return ResponseEntity.status(HttpStatus.OK).body(repo.findById(pid));
} 

@DeleteMapping("/products/{pid}")
public ResponseEntity<Void> deleteProduct(@PathVariable("pid") UUID pid) {
	Products prod = repo.getOne(pid);
	repo.delete(prod);
	return new ResponseEntity<>(HttpStatus.OK);
}

@PutMapping(path="/products/{pid}", consumes= {"application/json"})
public Products updateProducts(@RequestBody Products newProduct, @PathVariable("pid") UUID pid) {
	return repo.findById(pid)
			.map(prod -> {
				prod.setName(newProduct.getName());
				prod.setCategory(newProduct.getCategory());
				prod.setText(newProduct.getText());
				return repo.save(prod);
			})
			.orElseGet(() -> {
				newProduct.setPid(pid);
				return repo.save(newProduct);
			});
}
}

