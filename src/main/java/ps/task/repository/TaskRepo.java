package ps.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ps.task.model.Tasks;

public interface TaskRepo extends JpaRepository<Tasks, Integer> {

}
