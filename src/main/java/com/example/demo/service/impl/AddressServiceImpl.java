package com.example.demo.service.impl;

import com.example.demo.service.AddressService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import models.Address;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddressServiceImpl implements AddressService {
	private final ResourceLoader resourceLoader;

	public AddressServiceImpl(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public List<Address> getAddresses() {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser = null;
		try {
			Resource resource = resourceLoader.getResource("classpath:address.xml");
			InputStream inputStream = resource.getInputStream();
			parser = factory.createXMLStreamReader(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println("Check file path");
		} catch (XMLStreamException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		List<Address> clients = new ArrayList<>();
		try {
			while (true) {
				assert parser != null;
				if (!parser.hasNext())
					break;
				int event = parser.next();
				if (event == XMLStreamConstants.START_ELEMENT) {
					if (parser.getLocalName().equals("client")) {
						clients.add(new Address(
								Integer.parseInt(parser.getAttributeValue(0)),
								parser.getAttributeValue(1),
								parser.getAttributeValue(2),
								Integer.parseInt(parser.getAttributeValue(3)),
								Integer.parseInt(parser.getAttributeValue(4)),
								Integer.parseInt(parser.getAttributeValue(5))));
					}
				}
			}
		} catch (XMLStreamException e) {
			System.out.println(e.getMessage());
		}
		return clients;
	}
}
