package sem.task1.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;

@Data
@Component
@NoArgsConstructor
public class Task {
    private int id;
    private int firstNum;
    private int lastNum;
    private String operation;
    private double result;
    private Time timeOfCreation;


    public Task(int firstNum, int lastNum, String operation, double result, Time timeOfCreation) {
        this.firstNum = firstNum;
        this.lastNum = lastNum;
        this.operation = operation;
        this.result = result;
        this.timeOfCreation = timeOfCreation;
    }
}
