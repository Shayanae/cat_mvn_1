package org.sid.web;

import java.util.List;

import org.hibernate.criterion.Order;
import org.sid.dao.ProductRepository;
import org.sid.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	@RequestMapping(value="/index")
	public String index(Model model) {
		Pageable pageable = PageRequest.of(0, 10,  Sort.by((List<org.springframework.data.domain.Sort.Order>) Order.desc("id")));
		Page<Product> pageProducts = productRepository.findAll(pageable);
		model.addAttribute("listProducts", pageProducts.getContent());
		return "product";
	}
}
