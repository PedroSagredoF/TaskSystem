package ps.task.sevice;

import ps.task.model.Tasks;

import java.util.List;

public interface ITaskService {
    public List<Tasks> taskList();

    public Tasks findTaskById(Integer idTask);

    public void saveTask(Tasks tasks);

    public void deleteTask(Tasks tasks);



}
