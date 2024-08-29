package br.xksoberbado.multitenancymongo.web.controller;

import br.xksoberbado.multitenancymongo.model.Person;
import br.xksoberbado.multitenancymongo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@RestController
@RequestMapping("v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository repository;

    @GetMapping
    public List<? extends Object> get() {
        return repository.findAll();
    }

    @PostMapping
    public Object post(@RequestBody Person person) {
        person.setId(UUID.randomUUID());

        return repository.save(person);
    }

    @PutMapping("{id}")
    public Object put(@PathVariable UUID id,
                      @RequestBody Person person) {
        return repository.findById(id)
            .map(update(person))
            .orElseThrow();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }

    private Function<Person, Person> update(final Person person) {
        return p -> {
            p.setName(person.getName());
            p.setAge(person.getAge());

            return repository.save(p);
        };
    }
}
