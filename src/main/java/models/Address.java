package models;

import java.lang.String;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	private int id;
	private String city;
	private String street;
	private int house;
	private int floor;
	private int flatNumber;
}
