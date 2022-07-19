package org.sid.web;

import org.sid.dao.ProductRepository;
import org.sid.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value="/index")
	public String index(Model model, 
			@RequestParam(name="page", defaultValue="0")int p, 
			@RequestParam(name="size", defaultValue="5")int s) {
		Pageable pageable = PageRequest.of(p, s);
		Page<Product> pageProducts = productRepository.findAll(pageable);
		model.addAttribute("listProducts", pageProducts.getContent());
		int[] pages = new int[pageProducts.getTotalPages()];
		model.addAttribute("pages", pages);
		return "product";
	}
}
