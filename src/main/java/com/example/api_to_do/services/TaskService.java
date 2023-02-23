package com.example.api_to_do.services;


import com.example.api_to_do.entities.Task;
import com.example.api_to_do.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;
    public Task findById(Long id) {
       /* Task entity = repository.findById(id).get();
        TaskDto dto = new TaskDto(entity); */
        return repository.findById(id).orElse(null);
    }

    public Task deleteTasks(Long id) {
        repository.deleteById(id);
        return null;
    }

    public List<Task> findAllTasks() {
      /*  List<Task> tasks = repository.findAll();
        List<TaskDto> dtos = new ArrayList<>();
        for(Task task : tasks) {
            dtos.add(new TaskDto(task));
        }*/
        return repository.findAll();
    }

    public Task create(Task task) {

        return repository.save(task);
    }

    public Task update (Long id, Task task) {

        Optional<Task> oldTask = repository.findById(id);
        if(oldTask.isPresent()){

           oldTask.get().setName(task.getName());
           oldTask.get().setPrioridade(task.getPrioridade());
           oldTask.get().setTarefaFinalizada(task.getTarefaFinalizada());
           return repository.save(oldTask.get());

        }
        return null;
    }
    public Task updateStatus (Long id, Task task){

        Optional<Task> oldTask = repository.findById(id);
        if(oldTask.isPresent()) {
            oldTask.get().setTarefaFinalizada(task.getTarefaFinalizada());
            return repository.save(oldTask.get());
        }
        return null;
    }

  public List<Task> findTaskDone (List <Task> task) {
        List<Task> concluidas = new ArrayList<>();
        for(Task indice : task){
            if(indice.getTarefaFinalizada() == true) {
                concluidas.add(indice);
            }
        }
        return concluidas;


  }


    public List<Task> findTaskNotComplete(List<Task> tasks) {
        List<Task> naoConcluidas = new ArrayList<>();
        for(Task indice : tasks) {
            if(indice.getTarefaFinalizada() == false) {
                naoConcluidas.add(indice);
            }

        }
        return naoConcluidas;
    }
}
