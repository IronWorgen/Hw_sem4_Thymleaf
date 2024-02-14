package sem.task1.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sem.task1.model.Task;
import sem.task1.repositories.CalcRepository;
import sem.task1.service.CalculatorService;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
@Log
public class MainController {

    private final CalculatorService calculatorService;

    /**
     * получить главную страницу
     *
     * @param model
     * @return главная страница
     */
    @GetMapping
    public String getHome(Model model) {
        List<Task> tasks = calculatorService.findAll();
        model.addAttribute("tasks", tasks);
        log.info("страница отправлена пользователю");
        return "calculator.html";
    }

    /**
     * получает с формы переменные и вычисляет ответ + сохраняет пример в базе
     *
     * @param task  пример
     * @param model
     * @return главная страница
     */
    @PostMapping
    public String addTask(Task task, Model model) {
        try {
            calculatorService.calculateResult(task);
            model.addAttribute("lastNum", task.getLastNum());
            model.addAttribute("firstNum", task.getFirstNum());
            model.addAttribute("result", task.getResult());
            calculatorService.save(task);
            log.info("пример " + task + "сохранен");
        } catch (ArithmeticException e) {
            log.info(e.getMessage());
        }

        List<Task> tasks = calculatorService.findAll();
        Collections.reverse(tasks);
        model.addAttribute("tasks", tasks);
        return "calculator.html";
    }
}
