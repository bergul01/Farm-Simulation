package farmApp.farmbackend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import farmApp.farmbackend.business.imp.SheepService;
import farmApp.farmbackend.dto.SheepDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/sheep")
public class SheepController {
	
	private SheepService sheepService;
	
	@GetMapping("/count") //getCount
	public long getTotalNumberOfSheep() {
		
		return sheepService.getTotalNumberOfSheep();
	}
	
	@GetMapping("{id}") //getId
	public ResponseEntity<SheepDto> getSheepById(@PathVariable("id") Long sheepId){
		
		SheepDto sheepDto = sheepService.getSheepById(sheepId);
		
		return ResponseEntity.ok(sheepDto);
	}
	
	@GetMapping() //getAll
	public ResponseEntity<List<SheepDto>> getAllSheeps(){
		
		List<SheepDto> sheepDtos = sheepService.getAllSheeps();
		
		return ResponseEntity.ok(sheepDtos);
	}
	
	@PutMapping("{id}") //updateId
	public ResponseEntity<SheepDto> updateSheep (@PathVariable("id") Long sheepId, @RequestBody SheepDto updatedSheep){
		
		SheepDto sheepDto = sheepService.updateSheep(sheepId, updatedSheep);
		
		return ResponseEntity.ok(sheepDto);
		
	}
	
	
	@DeleteMapping("{id}") //deleteId
	public ResponseEntity<String> deleteSheep (@PathVariable("id") Long sheepId){
		
		sheepService.deleteSheep(sheepId);
		
		return ResponseEntity.ok("Sheep deleted successfully");
	}
	
	
	@PostMapping() //create
	public ResponseEntity<SheepDto> createSheep(@RequestBody SheepDto sheepDto){
		
		SheepDto savedSheep = sheepService.createSheep(sheepDto);
		
		return new ResponseEntity<>(savedSheep,HttpStatus.CREATED);
		
		
	}
}
