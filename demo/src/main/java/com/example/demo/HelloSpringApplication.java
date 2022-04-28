package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import javax.xml.ws.Response;

//import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HelloSpringApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloSpringApplication.class);

	ArrayList<Coffee> orderLine = new ArrayList<Coffee>();

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

	@PostMapping("/createCoffee")
	public ResponseEntity<String> createCoffee(@RequestBody Coffee coffee){
		int coffeeCount = orderLine.size();
		coffee.setId(coffeeCount);
		orderLine.add(coffee);
		return ResponseEntity.ok("Success");
	}

	@GetMapping("/getAll")
	public ResponseEntity<ArrayList<Coffee>> getAll() {
		return ResponseEntity.ok(this.orderLine);
	}

	@GetMapping("/getOrder")
	public void getOrder(@RequestParam(name="id") int getID){
		Coffee current = orderLine.get(getID);

	}

	@DeleteMapping("/delete")
	public void deleteOrder(@RequestParam(name="id") int getID){
		if (orderLine.size() == 0){
			System.out.println("Order Line empty.");
			return;
		}
		Coffee current = orderLine.get(getID);
		orderLine.remove(getID);

	}

	//PUT Commands to edit the order given its orderID
	//curl -X PUT http://localhost:8080/change -H 'Content-Type: application/json' --data-raw '{"ID": "0", "name": "Latte", "milk": true }'
	@PutMapping("/change")
	public void changeOrder(@RequestBody Coffee coffee){
		LOGGER.info("Coffee Name: {} Coffee Milk: {}", coffee.getName(), coffee.getMilk());

		int orderInQ = coffee.getId();
		String newString = coffee.getName();

		LOGGER.info("{} {} {}", orderInQ, orderLine, orderLine.get(orderInQ));

		Coffee current = orderLine.get(orderInQ);
		boolean milkChanged = false;

		if (current.getName() != coffee.getName() || coffee.getName() != null){		
			current.name = newString;
			System.out.println("-----------------------");
			System.out.println("New name: " + current.name);
			System.out.println("-----------------------");
			milkChanged = true;
		}

		if(current.getMilk() != coffee.getMilk()){
			current.milk = coffee.getMilk();
			if (milkChanged == false){
				System.out.println("-----------------------");
			}
			System.out.println("New milk: " + current.milk);
			System.out.println("-----------------------");
		} 
	}

	public void iterateOrderList(){ 
		for (int i = 0; i < orderLine.size(); i++){
			Coffee current = orderLine.get(i);
			System.out.println("ID: " + current.getId() + "  "+ orderLine.get(i));
		}
	}


}
