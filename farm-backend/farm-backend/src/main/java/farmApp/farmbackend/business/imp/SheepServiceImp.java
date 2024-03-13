package farmApp.farmbackend.business.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import farmApp.farmbackend.dataAccess.SheepRepository;
import farmApp.farmbackend.dto.SheepDto;
import farmApp.farmbackend.entities.Sheep;
import farmApp.farmbackend.exception.ResourceNotFoundException;
import farmApp.farmbackend.mapper.SheepMapper;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class SheepServiceImp implements SheepService{
	
	private SheepRepository sheepRepository;
	
	//Get Sheep REST API
	@Override
	public long getTotalNumberOfSheep() {
		
		return sheepRepository.count();
	}

	//Get Sheep REST API {id}
	@Override
	public SheepDto getSheepById(Long sheepId) {
		
		Sheep sheep = sheepRepository.findById(sheepId)
				.orElseThrow(() -> new ResourceNotFoundException("Sheep is not exists with given id" + sheepId));
		
		return SheepMapper.mapToSheepDto(sheep);
	}

	//Get Sheep REST API
	@Override
	public List<SheepDto> getAllSheeps() {
		
		List<Sheep> sheeps = sheepRepository.findAll();
		
		return sheeps.stream().map((sheep) -> SheepMapper.mapToSheepDto(sheep))
				.collect(Collectors.toList());
	}
	
	//Update Sheep REST API {id}
	@Override
	public SheepDto updateSheep(Long sheepId, SheepDto updatedSheep) {
		
		Sheep sheep = sheepRepository.findById(sheepId)
				.orElseThrow(() -> new ResourceNotFoundException("Sheep is not exists with given id" + sheepId));
		
		sheep.setAge(updatedSheep.getAge());
		sheep.setGender(updatedSheep.getGender());
		sheep.setType(updatedSheep.getType());
		sheep.setWeight(updatedSheep.getWeight());
		
		Sheep newSheepUpdate = sheepRepository.save(sheep);
		
		return SheepMapper.mapToSheepDto(newSheepUpdate);
	}
	

	//Delete Sheep REST API {id}
	@Override
	public void deleteSheep(Long sheepId) {
		
		Sheep sheep = sheepRepository.findById(sheepId)
				.orElseThrow(() -> new ResourceNotFoundException("Sheep is not exists with given id" + sheepId));
		
		sheepRepository.deleteById(sheepId);
		
	}

	
	//Post Sheep REST API
	@Override
	public SheepDto createSheep(SheepDto sheepDto) {
		
		Sheep sheep = SheepMapper.mapToGoat(sheepDto);
		
		Sheep savedSheep = sheepRepository.save(sheep);
		
		return SheepMapper.mapToSheepDto(savedSheep);
	}

	

}
