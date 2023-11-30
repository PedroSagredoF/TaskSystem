package ps.task.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ps.task.model.Tasks;
import ps.task.repository.TaskRepo;

import java.util.List;

@Service
public class TaskService implements ITaskService{

    @Autowired
    private TaskRepo taskRepo;
    @Override
    public List<Tasks> taskList() {
        return taskRepo.findAll();
    }

    @Override
    public Tasks findTaskById(Integer idTask) {
        return taskRepo.findById(idTask).orElse(null);
    }

    @Override
    public void saveTask(Tasks tasks) {
        taskRepo.save(tasks);
    }

    @Override
    public void deleteTask(Tasks tasks) {
        taskRepo.delete(tasks);
    }
}
