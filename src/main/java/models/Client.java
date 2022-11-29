package models;

import java.lang.String;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	private int id;
	private String name;
	private String personnelNumber;
	private int addressId;
}