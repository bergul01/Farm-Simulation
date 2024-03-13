package farmApp.farmbackend.business.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import farmApp.farmbackend.dataAccess.GoatRepository;
import farmApp.farmbackend.dto.GoatDto;
import farmApp.farmbackend.entities.Goat;
import farmApp.farmbackend.exception.ResourceNotFoundException;
import farmApp.farmbackend.mapper.GoatMapper;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class GoatServiceImp implements GoatService{
	
	private GoatRepository goatRepository;
	
	//Get Goat REST API
	@Override
	public long getTotalNumberOfGoat() {
		
		return goatRepository.count();
	}

	
	//Get Goat REST API {id}
	@Override
	public GoatDto getGoatById(Long goatId) {
		
		Goat goat = goatRepository.findById(goatId)
				.orElseThrow(() -> new ResourceNotFoundException("Goat is not exists with given id" + goatId));
		
		return GoatMapper.mapToGoatDto(goat);
	}
	
	//Get Goat REST API
	@Override
	public List<GoatDto> getAllGoat() {
		
		List<Goat> goats =  goatRepository.findAll();
		
		return goats.stream().map((goat) -> GoatMapper.mapToGoatDto(goat))
				.collect(Collectors.toList());
	}
	
	
	//Update Goat REST API
	@Override
	public GoatDto updateGoat(Long goatId, GoatDto updatedGoat) {
		
		Goat goat = goatRepository.findById(goatId)
				.orElseThrow(() -> new ResourceNotFoundException("Goat is not exists with given id" + goatId));

		goat.setAge(updatedGoat.getAge());
		goat.setGender(updatedGoat.getGender());
		goat.setType(updatedGoat.getType());
		goat.setWeight(updatedGoat.getWeight());
		
		Goat newGoatUpdate = goatRepository.save(goat);
		
		return GoatMapper.mapToGoatDto(newGoatUpdate);
	}

	//Delete Goat REST API 
	@Override
	public void deleteGoat(Long goatId) {
		
		Goat goat = goatRepository.findById(goatId)
				.orElseThrow(() -> new ResourceNotFoundException("Goat is not exists with given id" + goatId));
		
		goatRepository.deleteById(goatId);
	}

	
	//Post Goat REST API 
	@Override
	public GoatDto createGoat(GoatDto goatDto) {
		
		Goat goat = GoatMapper.mapToGoat(goatDto);
		
		Goat savedGoat = goatRepository.save(goat);
		
		return GoatMapper.mapToGoatDto(savedGoat);
	}


	


	

	

}
