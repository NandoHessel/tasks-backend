package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskControllerTest {

    //Inicializa a Task sem a necessidade do Spring
    @Mock
    private TaskRepo taskRepo;

    //Injeta os Mocks do contexto na classse abaixo
    @InjectMocks
    private TaskController controller;

    @Before
    public void Setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void NaoDeveSalvarTarefaSemDescricao() {
        Task todo = new Task();
//        todo.setTask("Descrição");
        todo.setDueDate(LocalDate.now());
        try {
            controller.save(todo);
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void NaoDeveSalvarTarefaSemData() {
        Task todo = new Task();
        todo.setTask("Descrição");
//        todo.setDueDate(LocalDate.now());
        try {
            controller.save(todo);
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void NaoDeveSalvarTarefaComDataPassada() {
        Task todo = new Task();
        todo.setTask("Descrição");
        todo.setDueDate(LocalDate.of(2010, 01, 01));
        try {
            controller.save(todo);
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void DeveSalvarTarefaComSucesso() throws ValidationException {
        Task todo = new Task();
        todo.setTask("Descrição");
        todo.setDueDate(LocalDate.now());
        controller.save(todo);

        Mockito.verify(taskRepo).save(todo);
    }
}
