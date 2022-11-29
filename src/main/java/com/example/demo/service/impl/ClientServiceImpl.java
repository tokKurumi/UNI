package com.example.demo.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.example.demo.service.ClientService;

import models.Client;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
	private final ResourceLoader resourceLoader;

	public ClientServiceImpl(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public List<Client> getClients() {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser = null;
		try {
			Resource resource = resourceLoader.getResource("classpath:client.xml");
			InputStream inputStream = resource.getInputStream();
			parser = factory.createXMLStreamReader(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println("Check file path");
		} catch (XMLStreamException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		List<Client> clients = new ArrayList<>();
		try {
			while (true) {
				assert parser != null;
				if (!parser.hasNext())
					break;
				int event = parser.next();
				if (event == XMLStreamConstants.START_ELEMENT) {
					if (parser.getLocalName().equals("client")) {
						clients.add(new Client(
								Integer.parseInt(parser.getAttributeValue(0)),
								parser.getAttributeValue(1),
								parser.getAttributeValue(2),
								Integer.parseInt(parser.getAttributeValue(3))));
					}
				}
			}
		} catch (XMLStreamException e) {
			System.out.println(e.getMessage());
		}
		return clients;
	}
}
