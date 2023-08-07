package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepo;

@Service
public class ClientService implements UserDetailsService{
	
	@Autowired
	private ClientRepo re;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	
	public List<Client> getLists(){
		return re.findAll();
	}
	
	public Client getById(int id) {
		return re.findById( id).get();
	}
	
	public Client addNew(Client client) {
		String encodedPassword = passwordEncoder.encode(client.getPassword());
		Client c1 = new Client();
		c1.setId(client.getId());
		c1.setName(client.getName());
		c1.setPassword(encodedPassword);
		c1.setRole(client.getRole());
		return re.save(c1);
	}
	
	public Client updateClient(int id, Client client) {
		String encodedPassword = passwordEncoder.encode(client.getPassword());
		Client client1 = re.findById(id).get();
		client1.setId(client.getId());
		client1.setName(client.getName());
		client1.setPassword(encodedPassword);
		client1.setRole(client.getRole());
		re.save(client1);
		return client1;
	}
	
	public String deletedClient(int id) {
		Client c1 = re.findById(id).get();
		String s1= c1.getName();
		re.delete(c1);
		return "Client with name "+s1+" get Deleted";
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Client client = re.findByName(username);
		if(client == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomClientDetails(client);
	}

}
