package com.example.api_to_do.entities;

import com.example.api_to_do.entities.enums.Prioridade;
import org.hibernate.jpa.internal.util.PessimisticNumberParser;

import javax.persistence.*;

@Entity
@Table(name= "tb_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "prioridade")
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(name = "tarefa_finalizada")
    private Boolean tarefaFinalizada;

    public Boolean getTarefaFinalizada() {
        return tarefaFinalizada;
    }

    public void setTarefaFinalizada(Boolean tarefaFinalizada) {
        this.tarefaFinalizada = tarefaFinalizada;
    }

    public Task() {

    }
    public Task(String name, Prioridade prioridade, Boolean tarefaFinalizada){
        this.name = name;
        this.prioridade = prioridade;
        this.tarefaFinalizada = tarefaFinalizada;
    }
    public Task(Long id, String name, Prioridade prioridade) {
        this.id = id;
        this.name = name;
        this.prioridade = prioridade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }
}
