package com.dreyke.hellodb.repository;

import com.dreyke.hellodb.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long>{

    // sorts with urgent tasks first in the all tasks list
    List<Task> findAllByOrderByUrgentDesc();
}
