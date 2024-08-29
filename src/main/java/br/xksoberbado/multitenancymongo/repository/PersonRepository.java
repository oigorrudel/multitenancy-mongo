package br.xksoberbado.multitenancymongo.repository;

import br.xksoberbado.multitenancymongo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PersonRepository extends MongoRepository<Person, UUID> {
}
