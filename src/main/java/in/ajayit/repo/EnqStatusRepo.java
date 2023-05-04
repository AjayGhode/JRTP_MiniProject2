package in.ajayit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ajayit.entity.EnqStatusEntity;

public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity, Integer> {

}
