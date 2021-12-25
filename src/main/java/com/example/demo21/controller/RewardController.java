package com.example.demo21.controller;
import com.example.demo21.domain.Reward;
import com.example.demo21.repository.RepositoryReward;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RewardController {
    private final RepositoryReward repositoryReward;

    public RewardController(RepositoryReward repositoryReward) {
        this.repositoryReward = repositoryReward;
    }


    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Reward saveEmployee(@RequestBody Reward reward) {
        return repositoryReward.save(reward);
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Reward> getAllUsers() {
        return repositoryReward.findAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reward getEmployeeById(@PathVariable Long id) {

        Reward reward = repositoryReward.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));

        /*if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }*/

        return reward;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reward refreshEmployee(@PathVariable("id") Long id, @RequestBody Reward reward) {

        return repositoryReward.findById(id)
                .map(entity -> {
                    entity.setTitle(reward.getTitle());
                    entity.setYear(reward.getYear());
                    return repositoryReward.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
    }

    //Удаление по id
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Long id) {
        //repository.deleteById(id);
        Reward reward = repositoryReward.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
        //employee.setIsDeleted(true);
        repositoryReward.delete(reward);//save(employee);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        repositoryReward.deleteAll();
    }

}
