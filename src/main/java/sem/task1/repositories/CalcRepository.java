package sem.task1.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sem.task1.config.SqlConfig;
import sem.task1.model.Task;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CalcRepository {
    private final JdbcTemplate template;
    private final SqlConfig config;


    public Task save(Task task) {
        String sql = config.getSave();
        template.update(sql,
                task.getFirstNum(),
                task.getLastNum(),
                task.getOperation(),
                task.getResult(),
                task.getTimeOfCreation());
        return task;
    }


    public List<Task> findAll() {
        String sql = config.getFindAll();
        RowMapper<Task> taskRowMapper = (r, i) -> {
            Task task = new Task();
            task.setId(r.getInt("id"));
            task.setFirstNum(r.getInt("firstNum"));
            task.setLastNum(r.getInt("lastNum"));
            task.setOperation(r.getString("operation"));
            task.setResult(r.getDouble("result"));
            task.setTimeOfCreation(r.getTime("timeOfCreation"));
            return task;
        };
        return template.query(sql, taskRowMapper);
    }

}
