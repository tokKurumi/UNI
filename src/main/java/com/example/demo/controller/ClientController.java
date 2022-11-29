package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ClientService;

import models.Client;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ClientController {
	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping("internal/clients")
	public List<Client> getClient() {
		return clientService.getClients();
	}

}
