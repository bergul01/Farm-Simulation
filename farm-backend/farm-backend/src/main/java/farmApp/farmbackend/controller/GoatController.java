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

import farmApp.farmbackend.business.imp.GoatService;
import farmApp.farmbackend.dto.GoatDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/goat")
public class GoatController {
	
	private GoatService goatService;
	
	@GetMapping("/count") //getCount
	public long getTotalNumberOfGoat() {
		
		return goatService.getTotalNumberOfGoat();
	}
	
	@GetMapping("{id}") //getId
	public ResponseEntity<GoatDto> getGoatById(@PathVariable("id") Long goatId){
		
		GoatDto goatDto = goatService.getGoatById(goatId);
		
		return ResponseEntity.ok(goatDto);
	}
	
	@GetMapping() //getAll
	public ResponseEntity<List<GoatDto>> getAllGoat(){
		
		List<GoatDto> goatDtos =  goatService.getAllGoat();
		
		return ResponseEntity.ok(goatDtos);
	}
	
	@PutMapping("{id}") //updateId
	public ResponseEntity<GoatDto> updateGoat(@PathVariable("id") Long goatId, @RequestBody GoatDto updatedGoat){
		
		GoatDto goatDto = goatService.updateGoat(goatId, updatedGoat); 
		
		return ResponseEntity.ok(goatDto);
		
	}
	@DeleteMapping("{id}") //deleteId
	public ResponseEntity<String> deleteGoat(@PathVariable("id") Long goatId){
		
		goatService.deleteGoat(goatId);
		
		return ResponseEntity.ok("Goat deleted successfully");
	}
	
	@PostMapping() //create
	public ResponseEntity<GoatDto> createGoat(@RequestBody GoatDto goatDto){
		
		GoatDto savedGoat = goatService.createGoat(goatDto);
		
		return new ResponseEntity<>(savedGoat,HttpStatus.CREATED);
		
	}
	

}
