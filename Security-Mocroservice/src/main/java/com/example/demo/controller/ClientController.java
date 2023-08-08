package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import com.example.demo.service.CustomClientDetails;

@RestController
@RequestMapping(value = "/home")
public class ClientController {
	
	private final ClientService clientService;
	
	@Autowired
	public ClientController(ClientService clientService) {
		 this.clientService= clientService;
	}
	
	@GetMapping(value = "/get")
	public List<Client> listAll(){
		return clientService.getLists();
	}
	
	@GetMapping(value = "/get/{id}")
	public Client getById(@PathVariable int id) {
		return clientService.getById(id);
	}
	
	@PostMapping(value = "/add")
	public Client addNewClient(@RequestBody Client client) {
		return clientService.addNew(client);
	}
	
	@PutMapping(value = "/update/{id}")
	public Client updateClient(@PathVariable int id,@RequestBody Client client) {
		return clientService.updateClient(id, client);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public String deleteClient(@PathVariable int id) {
		return clientService.deletedClient(id);
	}

    @GetMapping("/me")
    public String getUserRoles(Authentication authentication) {
    	CustomClientDetails userPrincipal = (CustomClientDetails) authentication.getPrincipal();
        // Access user roles: userPrincipal.getAuthorities()
        return "User roles: " + userPrincipal.getAuthorities();
    }
}