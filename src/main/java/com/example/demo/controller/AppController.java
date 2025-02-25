package com.example.demo.controller;



import java.util.List;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.Entity.userdata;
import com.example.demo.Entity.vegetables;
import com.example.demo.Service.ImageUploadService;
import com.example.demo.Service.PaymentService;
import com.example.demo.Service.ProductService;
import com.example.demo.Service.UserService;
import com.example.demo.Entity.Grains;
import com.example.demo.Entity.cart;
import com.example.demo.Entity.contactus;
import com.example.demo.Entity.flower;
import com.example.demo.Entity.mimosa;
import com.example.demo.Entity.organic;
import com.example.demo.Entity.plants;
import com.example.demo.Entity.payment;
@Controller
public class AppController {
	
	@Autowired
	ProductService pserve;
	
	@Autowired
	ImageUploadService imgService;
	@GetMapping("/home")
	public String home(Model m) {
		long count=pserve.count();
		m.addAttribute("count",count);
		return "home";
	}
	
	
	@GetMapping ("/showcart")
	public String showcart(Model m)
	{
		m.addAttribute("info", pserve.show());
		return "newcart";
	}
	

//								ADD TO CART LOGIC
	@PostMapping("/add")
	public String add(@RequestParam String name,@RequestParam int qty,@RequestParam String price,@RequestParam String category,Model m)
	{
		Optional<cart> c=pserve.find(name);
		cart ca=new cart();
		if(c.isPresent()) 
		{
			ca=c.get();
			int q=ca.getQuantity()+qty;
			ca.setQuantity(q);
		}
		else 
	{
		ca.setName(name);
		int p=Integer.parseInt(price);
		ca.setPrice(p);
		ca.setQuantity(qty);
	}
		
		switch(category)
		{
			case "vegetables":
							vegetables v=pserve.searchVeg(name);
							int q=v.getQuantity();
							if(q-qty>=0)
							{
							v.setName(name);
							v.setPrice(Integer.parseInt(price));
							v.setQuantity(q-qty);
							pserve.insertVeg(v);
							pserve.insert(ca);
							}
							else
							{
								m.addAttribute("msg", "Can't ADD!");
							}
							return "redirect:/showVeg";
			
			case "grains":
					Grains g=pserve.searchGrains(name);
					q=g.getQuantity();
					if(q-qty>=0)
					{
						g.setName(name);
						g.setPrice(Integer.parseInt(price));
						g.setQuantity(q-qty);
						pserve.insertGrains(g);
						pserve.insert(ca);
					}
					return "redirect:/showGrains";
				
			case "plants":
				plants p=pserve.searchPlant(name);
				q=p.getQuantity();
				if(q-qty>=0)
				{
					p.setName(name);
					p.setPrice(Integer.parseInt(price));
					p.setQuantity(q-qty);
					pserve.insertPlants(p);
					pserve.insert(ca);
				}
				
				return "redirect:/showPlants";
				
			case "flowers":
				flower f=pserve.searchFlower(name);
				q=f.getQuantity();
				if(q-qty>=0)
				{
					f.setName(name);
					f.setPrice(Integer.parseInt(price));
					f.setQuantity(q-qty);
					pserve.insertFlower(f);
					pserve.insert(ca);
				}
				
				return "redirect:/showFlowers";
				
			case "organic":
				organic o=pserve.searchOrganic(name);
				q=o.getQuantity();
				if(q-qty>=0)
				{
					o.setName(name);
					o.setPrice(Integer.parseInt(price));
					o.setQuantity(q-qty);
					pserve.insertOrganic(o);
					pserve.insert(ca);
				}
				
				return "redirect:/showOrganic";
				
			case "mimosa":
				mimosa mi=pserve.searchMimosa(name);
				q=mi.getQuantity();
				if(q-qty>=0)
				{
					mi.setName(name);
					mi.setPrice(Integer.parseInt(price));
					mi.setQuantity(q-qty);
					pserve.insertMimosa(mi);
					pserve.insert(ca);
				}
					
					return "redirect:/showMimosa";
			case "product":
				pserve.insert(ca);
		}
			
		return "redirect:/home";
		}
		
	
	@GetMapping("/newcart")
	public String newcart() {
		return "newcart";
	}
	
										//Admin Product Insertion Logic
	
	@PostMapping("/uploadres")
	public String uploadres(@RequestParam String name,@RequestParam int price,@RequestParam int quantity,@RequestParam String category,@RequestParam MultipartFile file,Model m)
	{
		switch(category) 
		{
		case "vegetables":
			vegetables v=new vegetables();
			v.setName(name);
			v.setPrice(price);
			v.setQuantity(quantity);
			String fv=file.getOriginalFilename();
			v.setPicurl(fv);
			vegetables veg=pserve.insertVeg(v);
			if(veg!=null) {
			String msg=imgService.imageUpload(file);
			m.addAttribute("msg", msg);
		}
			break;
		
		case "grains":
			Grains g=new Grains();
			g.setName(name);
			g.setPrice(price);
			g.setQuantity(quantity);
			String fg=file.getOriginalFilename();
			g.setPicurl(fg);
			Grains gr=pserve.insertGrains(g);
			if(gr!=null) {
			String msg=imgService.imageUpload(file);
			m.addAttribute("msg", msg);
		}
			break;
			
		case "plants":
			plants p=new plants();
			p.setName(name);
			p.setPrice(price);
			p.setQuantity(quantity);
			String fp=file.getOriginalFilename();
			p.setPicurl(fp);
			plants plants=pserve.insertPlants(p);
			if(plants!=null) {
			String msg=imgService.imageUpload(file);
			m.addAttribute("msg", msg);
		}
			break;	
			
		case "flowers":
			flower f=new flower();
			f.setName(name);
			f.setPrice(price);
			f.setQuantity(quantity);
			String ff=file.getOriginalFilename();
			f.setPicurl(ff);
			flower fr=pserve.insertFlower(f);
			if(fr!=null) {
			String msg=imgService.imageUpload(file);
			m.addAttribute("msg", msg);
		}
			break;
			
		case "organic":
			organic o=new organic();
			o.setName(name);
			o.setPrice(price);
			o.setQuantity(quantity);
			String fo=file.getOriginalFilename();
			o.setPicurl(fo);
			organic or=pserve.insertOrganic(o);
			if(or!=null) {
			String msg=imgService.imageUpload(file);
			m.addAttribute("msg", msg);
		}
			break;
			
		case "mimosa":
			mimosa mimosa=new mimosa();
			mimosa.setName(name);
			mimosa.setPrice(price);
			mimosa.setQuantity(quantity);
			String fm=file.getOriginalFilename();
			mimosa.setPicurl(fm);
			mimosa ma=pserve.insertMimosa(mimosa);
			if(ma!=null) {
			String msg=imgService.imageUpload(file);
			m.addAttribute("msg", msg);
		}
			break;
		}
		return "redirect:/index";
	}
	
	@GetMapping("/showVeg")
	public String show(Model m) {
		long count=pserve.count();
		m.addAttribute("count",count);
		m.addAttribute("records", pserve.showAllVeg());
		return "Vegetables";
	}
	
	@GetMapping("/showGrains")
	public String showGrains(Model m) {
		long count=pserve.count();
		m.addAttribute("count",count);
		m.addAttribute("records", pserve.showAllGrains());
		return "grains";
	}
	
	@GetMapping("/showPlants")
	public String showPlants(Model m) {
		long count=pserve.count();
	m.addAttribute("count",count);
		m.addAttribute("records", pserve.showAllPlants());
		return "plants";
	}
	
	@GetMapping("/showFlowers")
	public String showFlowers(Model m) {
		long count=pserve.count();
		m.addAttribute("count",count);
		m.addAttribute("records", pserve.showAllFlowers());
		return "flower";
	}
	
	@GetMapping("/showOrganic")
	public String showOrganic(Model m) {
		long count=pserve.count();
		m.addAttribute("count",count);
		m.addAttribute("records", pserve.showAllOrganics());
		return "organic";
	}
	
	@GetMapping("/showMimosa")
	public String showMimosa(Model m) {
		long count=pserve.count();
		m.addAttribute("count",count);
		m.addAttribute("records", pserve.showAll());
		return "mimosa";
	}
	
	@GetMapping("/aboutUs")
	public String aboutUs() {
		return "aboutus";
	}
	
	
	@GetMapping("/privacy")
	public String privacy() {
		return "privacy policy";
	}
	
	@GetMapping("/index")
	public String index(Model m) {
		m.addAttribute("count", pserve.countMsg());
		m.addAttribute("countPay",payserve.countPayment());
		return "index";
	}
	
	
	@GetMapping("/delete/{name}")
	public String deleteCart(@PathVariable String name,Model m )
	{
		pserve.delete(name);
		m.addAttribute("info",pserve.show());
		return "newcart";
	}
	
	

	@Autowired
	UserService userservice;
	@PostMapping("/loginres")
	public String loginres(@RequestParam String username,@RequestParam String email,@RequestParam String pw,Model m)
	{
		
		List<userdata> s=userservice.search(username,pw);
		if(username.equals("admin") && pw.equals("1234"))
			return "index";
		else if(s.isEmpty())
		{
			m.addAttribute("msg", "Invalid Username or password!!");
			return "login";
		}
		else
		{
			m.addAttribute("count", 0);
			return "home";
		}
	}
	
	
	@PostMapping("/signupres")
	public String signupres(@RequestParam String username,@RequestParam String email,@RequestParam String pw,Model m)
	{
		userdata u=new userdata(username,email,pw);
		userdata newuser=userservice.create(u);
		if(newuser!=null)
			{
				m.addAttribute("msg", "Account Created!!..Now, Login with Credentials ") ;
				return "login";
			}
		else
			{
			m.addAttribute("msg", "Sorry Can't procees your request!") ;
			return "signup";
			}
	}
	
	@GetMapping ("/")
	public String login()
	{
		return "login";
	}
	
	@GetMapping ("/signup")
	public String signup()
	{
		return "signup";
	}
	
	@PostMapping("/payment")
	public String payment(@RequestParam String amt,Model m) 
	{
		m.addAttribute("amt", amt);
		return "payment";
	}
	
	@GetMapping("/showdelete")
	public String showdelete(Model m) 
	{
		m.addAttribute("record1", pserve.showAllVeg());	
		m.addAttribute("record2",pserve.showAllGrains());
		m.addAttribute("record3",pserve.showAllPlants());
		m.addAttribute("record4",pserve.showAllFlowers());
		m.addAttribute("record5",pserve.showAllOrganics());
		m.addAttribute("record6",pserve.showAll());
		return "viewProduct";
	}
	
	
	//Delete Product Mappings
	
	@GetMapping("/deleteVeg/{name}")
	public String deleteVeg(@PathVariable String name )
	{
		pserve.deleteVeg(name);
		return "redirect:/showdelete";
	}
	
	@GetMapping("/deleteGrains/{name}")
	public String deleteGrains(@PathVariable String name )
	{
		pserve.deleteGrains(name);
		return "redirect:/showdelete";
	}
	
	@GetMapping("/deletePlants/{name}")
	public String deleteplants(@PathVariable String name )
	{
		pserve.deletePlants(name);
		return "redirect:/showdelete";
	}
	
	@GetMapping("/deleteFlowers/{name}")
	public String deleteFlowers(@PathVariable String name )
	{
		pserve.deleteFlower(name);
		return "redirect:/showdelete";
	}
	
	@GetMapping("/deleteOrganic/{name}")
	public String deleteOrganic(@PathVariable String name )
	{
		pserve.deleteOrganic(name);
		return "redirect:/showdelete";
	}
	
	@GetMapping("/deleteMimosa/{name}")
	public String deleteMimosa(@PathVariable String name)
	{
		pserve.deleteMimosa(name);
		return "redirect:/showdelete";
	}
	
	@GetMapping("/updateVeg/{name}")
	public String updateveg(@PathVariable String name, Model m)
	{
			vegetables v=pserve.searchVeg(name);
			m.addAttribute("name", v.getName());
			m.addAttribute("price",v.getPrice());
			m.addAttribute("quantity", v.getQuantity());
			m.addAttribute("category","vegetables");
			m.addAttribute("picurl", v.getPicurl());
			return "updateProduct";	
	}
	
	@GetMapping("/updateGrains/{name}")
	public String updateGrains(@PathVariable String name, Model m)
	{
		Grains v=pserve.searchGrains(name);
		m.addAttribute("name", v.getName());
		m.addAttribute("price",v.getPrice());
		m.addAttribute("quantity", v.getQuantity());
		m.addAttribute("category","grains");
		m.addAttribute("picurl", v.getPicurl());
		return "updateProduct";	
	}
	
	@GetMapping("/updatePlants/{name}")
	public String updatePlants(@PathVariable String name, Model m)
	{
		plants v=pserve.searchPlant(name);
		m.addAttribute("name", v.getName());
		m.addAttribute("price",v.getPrice());
		m.addAttribute("quantity", v.getQuantity());
		m.addAttribute("category","plants");
		m.addAttribute("picurl", v.getPicurl());
		return "updateProduct";	
	}
	
	@GetMapping("/updateFlowers/{name}")
	public String updateFlowers(@PathVariable String name, Model m)
	{
		flower v=pserve.searchFlower(name);
		m.addAttribute("name", v.getName());
		m.addAttribute("price",v.getPrice());
		m.addAttribute("quantity", v.getQuantity());
		m.addAttribute("category","flowers");
		m.addAttribute("picurl", v.getPicurl());
		return "updateProduct";	
	}
	
	@GetMapping("/updateOranic/{name}")
	public String updateOrganic(@PathVariable String name, Model m)
	{
		organic v=pserve.searchOrganic(name);
		m.addAttribute("name", v.getName());
		m.addAttribute("price",v.getPrice());
		m.addAttribute("quantity", v.getQuantity());
		m.addAttribute("category","organic");
		m.addAttribute("picurl", v.getPicurl());
		return "updateProduct";	
	}
	
	@GetMapping("/updateMimosa/{name}")
	public String update(@PathVariable String name, Model m)
	{
		vegetables v=pserve.searchVeg(name);
		m.addAttribute("name", v.getName());
		m.addAttribute("price",v.getPrice());
		m.addAttribute("quantity", v.getQuantity());
		m.addAttribute("category","mimosa");
		m.addAttribute("picurl", v.getPicurl());
		return "updateProduct";	
	}
	//ClearCart Button
	
	@GetMapping("/clearCart")
	public String clearCart() {
		pserve.deleteCart();
		return "redirect:/showcart";
	}
	
	//search option
	@GetMapping("/search")
	public String search(@RequestParam String name,Model m) {
		
		if(!pserve.isVeg(name).isEmpty())
			{
				m.addAttribute("category", "vegetables");
				m.addAttribute("results",pserve.isVeg(name));
				return "searchRes";
			}
		if(!pserve.isFlower(name).isEmpty())
		{
			m.addAttribute("category", "flowers");
			m.addAttribute("results",pserve.isFlower(name));
			return "searchRes";
		}
		else if(!pserve.isGrains(name).isEmpty())
		{
			m.addAttribute("category", "grains");
			m.addAttribute("results",pserve.isGrains(name));
			return "searchRes";
			
		}
		else if(!pserve.isPlant(name).isEmpty())
		{
			m.addAttribute("category", "plants");
			m.addAttribute("results",pserve.isPlant(name));
			return "searchRes";
			
		}
		else if(!pserve.isOrganic(name).isEmpty())
		{
			m.addAttribute("category", "organic");
			m.addAttribute("results",pserve.isOrganic(name));
			return "searchRes";
			
		}
		else if(!pserve.isMimosa(name).isEmpty())
		{
			m.addAttribute("category", "mimosa");
			m.addAttribute("results",pserve.isMimosa(name));
			return "searchRes";
		}
		else {
			m.addAttribute("category", "no");
			m.addAttribute("NotFound", "Item Does Not Exist!!");
			return "searchRes";
		}
		
	}
	@PostMapping("/contact")
	public String contact(@RequestParam String name, @RequestParam String email, @RequestParam String msg,Model m)
	{
		contactus c=new contactus(name,email,msg);
		pserve.insertMsg(c);
		m.addAttribute("message", "Your Feedback Reached Successfully.....");
		return "contactus";
	}
	
	@GetMapping("/contactUs")
	public String contactUs() {
		return "contactus";
	}
	
	@GetMapping("/feedback")
	public String feedback(Model m) {
		m.addAttribute("record", pserve.showMsg());
		return "feedback";
	}
	
	@GetMapping("/deleteFeedback/{name}")
	public String deleteFeedback(@PathVariable String name)
	{
		pserve.deleteMsg(name);
		return "redirect:/feedback";
	}
	
	@GetMapping("/clearAllMsgs")
	public String clearAllMsgs() {
		pserve.clearMsg();
		return "redirect:/feedback";
	}
	//payment Response logic
	@Autowired
	PaymentService payserve;
	
	@PostMapping("/paymentRes")
	public String paymentRes(@RequestParam String Fname,@RequestParam String email,@RequestParam int amt,@RequestParam String modeofpayment,@RequestParam String address,@RequestParam String city,@RequestParam String state,@RequestParam int	pincode)
	{
		Random r=new Random();
		int Orderid =r.nextInt(100000000,1100000000);
		payment p=new payment(Orderid,Fname,email,amt,modeofpayment,address,city,state,pincode);
		payserve.create(p);
		pserve.deleteCart();
		return "PaymentSuccess";
	}
	
	@GetMapping("/showpayments")
	public String showPayment(Model m) {
		m.addAttribute("record", payserve.showPayment());
		return "showpayments";
	}
	@GetMapping("/clearAllPayments")
	public String clearAllPayments()
	{
		payserve.deleteAllpayment();
		return "redirect:/showpayments";
	}
	@GetMapping("/deletePayment/{oid}")
	public String deletePayment(@PathVariable int oid)
	{
		payserve.deletePayment(oid);
		return "redirect:/showpayments";
	}
}
