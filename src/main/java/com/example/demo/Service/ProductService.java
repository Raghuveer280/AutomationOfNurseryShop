package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Grains;
import com.example.demo.Entity.cart;
import com.example.demo.Entity.contactus;
import com.example.demo.Entity.flower;
import com.example.demo.Entity.mimosa;
import com.example.demo.Entity.organic;
import com.example.demo.Entity.plants;
import com.example.demo.Entity.vegetables;
import com.example.demo.Repository.CartRepository;
import com.example.demo.Repository.FlowerRepository;
import com.example.demo.Repository.GrainsRepository;
import com.example.demo.Repository.MimosaRepository;
import com.example.demo.Repository.OrganicRepository;
import com.example.demo.Repository.PlantsRepository;
import com.example.demo.Repository.VegetableRepository;
import com.example.demo.Repository.contactUsRepository;

@Service
public class ProductService {

	@Autowired
	VegetableRepository vrepo;
	@Autowired
	GrainsRepository grepo;
	@Autowired
	PlantsRepository prepo;
	@Autowired
	FlowerRepository frepo;
	@Autowired
	OrganicRepository orepo;
	@Autowired
	MimosaRepository mrepo;
	@Autowired
	CartRepository cartrepo;
	@Autowired
	contactUsRepository contactrepo;
	//Vegetable Service
	public Iterable<vegetables> showAllVeg()
	{
		return vrepo.findAll();
	}
	
	public vegetables insertVeg(vegetables v) 
	{
		return vrepo.save(v);
	}
	
	public void deleteVeg(String name)
	{
		vrepo.deleteById(name);
	}
	
	public vegetables searchVeg(String name)
	{
		return vrepo.findById(name).get();
	}
	
	//Grains Service
	public Iterable<Grains> showAllGrains()
	{
		return grepo.findAll();
	}
	
	public Grains insertGrains(Grains g) {
		return grepo.save(g);
	}
	
	public Grains searchGrains(String name)
	{
		return grepo.findById(name).get();
	}
	
	public void deleteGrains(String name)
	{
		 grepo.deleteById(name);
	}
	
	//Plants Service
	public Iterable<plants> showAllPlants()
	{
		return prepo.findAll();
	}
	
	public plants insertPlants(plants p) 
	{
		return prepo.save(p);
	}
	
	public plants searchPlant(String name)
	{
		return prepo.findById(name).get();
	}
	
	public void deletePlants(String name)
	{
		prepo.deleteById(name);
	}
	
	//Flower Service
	public Iterable<flower> showAllFlowers()
	{
		return frepo.findAll();
	}
	
	public flower insertFlower(flower f) 
	{
		return frepo.save(f);
	}
	
	public flower searchFlower(String name)
	{
		return frepo.findById(name).get();
	}
	
	public void deleteFlower(String name)
	{
		frepo.deleteById(name);
	}
	
	//Organic Seeds Service
	public Iterable<organic> showAllOrganics()
	{
		return orepo.findAll();
	}
	
	public organic insertOrganic(organic o) 
	{
		return orepo.save(o);
	}
	
	public organic searchOrganic(String name)
	{
		return orepo.findById(name).get();
	}
	
	public void deleteOrganic(String name)
	{
		orepo.deleteById(name);
	}
	
	//Mimosa Seeds Service
	public Iterable<mimosa> showAll()
	{
		return mrepo.findAll();
	}
	
	public mimosa insertMimosa(mimosa m) 
	{
		return mrepo.save(m);
	}
	
	public mimosa searchMimosa(String name)
	{
		return mrepo.findById(name).get();
	}
	
	public void deleteMimosa(String name)
	{
		mrepo.deleteById(name);
	}
	
	//cart Management
	public cart insert(cart p)
	{
		return cartrepo.save(p);
	}
	
	public Optional<cart> find(String name)
	{
		return cartrepo.findById(name);
	}
	
	public Iterable<cart> show()
	{
		return cartrepo.findAll();
	}
	public void delete(String name)
	{
		cartrepo.deleteById(name);
	}
	
	public long count()
	{
		return cartrepo.count();
	}
	public void deleteCart()
	{
		cartrepo.deleteAll();
	}
	
	//Search Logic
	public  List<vegetables> isVeg(String name)
	{
		return vrepo.findBynameIgnoreCase(name);
	}
	public List<Grains> isGrains(String name)
	{
		return grepo.findBynameIgnoreCase(name);
	}
	public List<plants> isPlant(String name)
	{
		return prepo.findBynameIgnoreCase(name);
	}
	public List<flower> isFlower(String name)
	{
		return frepo.findBynameIgnoreCase(name);
	}
	public List<organic> isOrganic(String name)
	{
		return orepo.findBynameIgnoreCase(name);
	}
	public List<mimosa> isMimosa(String name)
	{
		return mrepo.findBynameIgnoreCase(name);
	}

	public contactus insertMsg(contactus c)
	{
		return contactrepo.save(c);
	}
	
	public Iterable<contactus> showMsg()
	{
		return contactrepo.findAll();
	}
	
	public long countMsg()
	{
		return contactrepo.count();
	}
	
	public void deleteMsg(String name)
	{
		contactrepo.deleteById(name);
	}
	
	public void clearMsg()
	{
		contactrepo.deleteAll();
	}
	}
