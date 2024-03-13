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

import farmApp.farmbackend.business.imp.ChickenService;
import farmApp.farmbackend.dto.ChickenDto;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/chicken")
public class ChickenController {
	
	private ChickenService chickenService;
	
	
	@GetMapping("/count") //getCount
	public long getTotalNumberOfChickens() {
		return chickenService.getTotalNumberOfChicken();
	}
	
	
	@GetMapping("{id}") //getId
	public ResponseEntity<ChickenDto> getChickenById(@PathVariable("id") Long chickenId){
		
		ChickenDto chickenDto = chickenService.getChickenById(chickenId);
		
		return ResponseEntity.ok(chickenDto);
		
	}
	
	
	@GetMapping() //getAll
	public ResponseEntity<List<ChickenDto>> getAllChicken(){
		
		List<ChickenDto> chicken = chickenService.getAllChickens();
		
		return ResponseEntity.ok(chicken);
	}
	
	@PutMapping("{id}") //updateId
	public ResponseEntity<ChickenDto> updateChicken (@PathVariable("id") Long chickenId, @RequestBody ChickenDto updatedChicken){
		
		ChickenDto chickenDto = chickenService.updateChicken(chickenId, updatedChicken);
		
		return ResponseEntity.ok(chickenDto);
	}
	
	@DeleteMapping("{id}") //deleteId
	public ResponseEntity<String> deleteChicken (@PathVariable("id") Long chickendId){
		
		chickenService.deleteChicken(chickendId);
		
		return ResponseEntity.ok("Chicken deleted successfully"); 
	}
	
	
	
	@PostMapping() //create
	public ResponseEntity<ChickenDto> createChicken(@RequestBody ChickenDto chickenDto){
		
		ChickenDto savedChicken = chickenService.createChicken(chickenDto);
		
		return new ResponseEntity<>(savedChicken,HttpStatus.CREATED);
		
	}
	
	

}
