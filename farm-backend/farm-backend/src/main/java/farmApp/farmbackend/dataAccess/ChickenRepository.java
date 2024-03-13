package farmApp.farmbackend.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import farmApp.farmbackend.entities.Chicken;

public interface ChickenRepository extends JpaRepository<Chicken, Long>{
	
	

}
