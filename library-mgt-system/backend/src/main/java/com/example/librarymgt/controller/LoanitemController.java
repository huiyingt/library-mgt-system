package com.example.librarymgt.controller;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.librarymgt.services.LoanitemServices;
import com.example.librarymgt.model.Loanitem;

@RestController
@RequestMapping("/api/loanitems")
@CrossOrigin(origins= "http://localhost:5173")

//CrossOrigin to connect to ReactJS frontend
public class LoanitemController {
	
	@Autowired //to inject the LoanServices bean
	private LoanitemServices loanitemServices; //service to handle business logic
	
	@PostMapping("/create") //endpoint to create a new loanitem
	public ResponseEntity<Loanitem> createLoanitem(@RequestBody Loanitem loanitem) {
		// This method will handle the creation of a new loanitem
		// It will receive a Loanitem object from the request body
		Loanitem createdLoanitem = loanitemServices.saveLoanitem(loanitem); //call the service to save the loanitem
		return new ResponseEntity<>(createdLoanitem, HttpStatus.CREATED); //return the saved loanitem with CREATED status
	}
	
	@GetMapping("/{id}") //endpoint to get a loanitem by ID
	public ResponseEntity<Optional<Loanitem>> getLoanitemById(@PathVariable Long id) {
		// This method will handle the retrieval of a loanitem by ID
		Optional<Loanitem> loanitem = loanitemServices.getLoanitemById(id); //call the service to get the loanitem by ID
		return new ResponseEntity<>(loanitem, HttpStatus.OK); //return the loanitem with OK status
	}
	
	@GetMapping("/{id}/fine") //endpoint to get the fine amount for a loanitem by ID
	public ResponseEntity<Double> getFineAmountByLoanitemId(@PathVariable Long id) {
		// This method will handle the retrieval of the fine amount for a loanitem by ID
		Optional<Loanitem> loanitem = loanitemServices.getLoanitemById(id); //call the service to get the loanitem by ID
		if (loanitem.isPresent()) {
			double fineAmount = loanitem.get().getFineAmount(); //get the fine amount from the loanitem
			return new ResponseEntity<>(fineAmount, HttpStatus.OK); //return the fine amount with OK status
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); //return NOT_FOUND status if loanitem does not exist
		}
	}
	
	@GetMapping("/due") //endpoint to get a loanitem by due date
	public ResponseEntity<Optional<Loanitem>> getLoanitemByDueDate(@PathVariable LocalDate dueDate) {
		// This method will handle the retrieval of a loanitem by due date
		Optional<Loanitem> loanitem = loanitemServices.getLoanitemByDueDate(dueDate); //call the service to get the loanitem by due date
		return new ResponseEntity<>(loanitem, HttpStatus.OK); //return the loanitem with OK status
	}
		
	@GetMapping("/{loanId}") //endpoint to get all loanitems by loan ID
	public ResponseEntity<List<Loanitem>> getLoanitemsByLoanId(@PathVariable Long loanId) {
		// This method will handle the retrieval of all loanitems by loan ID
		List<Loanitem> loanitems = loanitemServices.getLoanitemsByLoanId(loanId); //call the service to get all loanitems by loan ID
		return new ResponseEntity<>(loanitems, HttpStatus.OK); //return the list of loanitems with OK status
	}
	
	@GetMapping("/all") //endpoint to get all loanitems
	public ResponseEntity<List<Loanitem>> getAllLoanitems() {
		// This method will handle the retrieval of all loanitems
		List<Loanitem> loanitems = loanitemServices.getAllLoanitems(); //call the service to get all loanitems
		return new ResponseEntity<>(loanitems, HttpStatus.OK); //return the list of loanitems with OK status
	}
	
	@GetMapping("/overdue") //endpoint to get all overdue loanitems
	public ResponseEntity<List<Loanitem>> getOverdueLoanitems() {
		// This method will handle the retrieval of all overdue loanitems
		List<Loanitem> overdueLoanitems = loanitemServices.getOverdueLoanitems(); //call the service to get all overdue loanitems
		return new ResponseEntity<>(overdueLoanitems, HttpStatus.OK); //return the list of overdue loanitems with OK status
	}
	
	@PutMapping("/update/{id}") //endpoint to update a loanitem by ID (pathvariable)
	public ResponseEntity<Loanitem> updateLoanitem(@PathVariable Long id, @RequestBody Loanitem loanitem) {
		// This method will handle the update of an existing loanitem
		// It will receive a Loanitem object from the request body
		Loanitem updatedLoanitem = loanitemServices.updateLoanitem(id, loanitem); //call the service to update the loanitem
		return new ResponseEntity<>(updatedLoanitem, HttpStatus.OK); //return the updated loanitem with OK status
	}
	
	@DeleteMapping("/delete/{id}") //endpoint to delete a loanitem by ID
	public ResponseEntity<Void> deleteLoanitem(@PathVariable Long id) {
		// This method will handle the deletion of a loanitem by ID
		loanitemServices.deleteLoanitem(id); //call the service to delete the loanitem by ID
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //return NO_CONTENT status for successful deletion
	}
	
}