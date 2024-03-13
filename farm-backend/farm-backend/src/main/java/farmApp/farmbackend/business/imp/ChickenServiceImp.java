package farmApp.farmbackend.business.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import farmApp.farmbackend.dataAccess.ChickenRepository;
import farmApp.farmbackend.dto.ChickenDto;
import farmApp.farmbackend.entities.Chicken;
import farmApp.farmbackend.exception.ResourceNotFoundException;
import farmApp.farmbackend.mapper.ChickenMapper;
import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class ChickenServiceImp implements ChickenService{
	
	private ChickenRepository chickenRepository;
	
	//Get Chicken REST API
	@Override
	public long getTotalNumberOfChicken() {
		return chickenRepository.count();
	}
	
	//Get Chicken REST API {id}
	@Override
	public ChickenDto getChickenById(Long chickenId) {
		Chicken chicken = chickenRepository.findById(chickenId)
				.orElseThrow(() -> new ResourceNotFoundException("Chicken is not exists with given id" + chickenId));
		
		return ChickenMapper.mapToChickenDto(chicken);
	}
	//Get Chicken REST API
	@Override
	public List<ChickenDto> getAllChickens() {
		List<Chicken> chickens = chickenRepository.findAll();
		
		return chickens.stream().map((chicken) -> ChickenMapper.mapToChickenDto(chicken))
				.collect(Collectors.toList());
	}
	//Update Chicken REST API {id}
	@Override
	public ChickenDto updateChicken(Long chickenId, ChickenDto updatedChicken) {
		
		Chicken chicken = chickenRepository.findById(chickenId)
				.orElseThrow(() -> new ResourceNotFoundException("Chicken is not exists with given id" + chickenId));
		
		chicken.setAge(updatedChicken.getAge());
		chicken.setGender(updatedChicken.getGender());
		chicken.setType(updatedChicken.getType());
		chicken.setWeight(updatedChicken.getWeight());
		
		Chicken newChickenUpdate = chickenRepository.save(chicken);
		return ChickenMapper.mapToChickenDto(newChickenUpdate);
	}
	
	//Delete Chicken REST API {id}
	@Override
	public void deleteChicken(Long chickenId) {
		Chicken chicken = chickenRepository.findById(chickenId)
				.orElseThrow(() -> new ResourceNotFoundException("Chicken is not exists with given id" + chickenId));
		
		chickenRepository.deleteById(chickenId);
		
	}

	//Post Chicken REST API
	@Override
	public ChickenDto createChicken(ChickenDto chickenDto) {
		
		Chicken chicken = ChickenMapper.mapToChicken(chickenDto);
		
		Chicken savedChicken = chickenRepository.save(chicken);
		
		return ChickenMapper.mapToChickenDto(savedChicken);
		
	}
	
	
	
	
	

}
