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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value="/index")
	public String index(Model model, 
			@RequestParam(name="page", defaultValue="0")int p, 
			@RequestParam(name="size", defaultValue="5")int s,
			@RequestParam(name="mc", defaultValue="")String mc) {
		Pageable pageable = PageRequest.of(p, s);
		Page<Product> pageProducts = productRepository.chercher("%"+mc+"%", pageable);
		model.addAttribute("listProducts", pageProducts.getContent());
		int[] pages = new int[pageProducts.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("mc", mc);
		return "product";
	}
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(Long id, String mc, int page, int size) {
		productRepository.deleteById(id);
		return "redirect:/index?page="+page+"&size="+size+"&mc="+mc;
	}
	@RequestMapping(value="/form", method = RequestMethod.GET)
	public String formProduct() {
		return "FormProduct";
	}
}
