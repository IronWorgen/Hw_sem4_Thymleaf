package sem.task1.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import sem.task1.model.Task;
import sem.task1.repositories.CalcRepository;

import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
@Data
public class CalculatorService {
    private final CalcRepository calcRepository;
    public List<Task> findAll() {
        return calcRepository.findAll();
    }


    public Task save(Task task) {
        task.setTimeOfCreation(Time.valueOf(LocalTime.now()));
        return calcRepository.save(task);
    }


    public Task calculateResult(Task task) {
        double result = 0;
        switch (task.getOperation().charAt(0)) {
            case '+':
                result = task.getFirstNum() + task.getLastNum();
                break;

            case '-':
                result = task.getFirstNum() - task.getLastNum();
                break;

            case '*':
                result = task.getFirstNum() * task.getLastNum();
                break;

            case '/':
                if (task.getLastNum() == 0) {
                    throw new ArithmeticException("Деление на 0");
                }
                result = (double) task.getFirstNum() / task.getLastNum();
                break;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String s = decimalFormat.format(result);
        task.setResult(Double.valueOf(s.replace(',', '.')));
        return task;
    }

}
