package com.crud.tasks.domain;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TaskController.class)
@ExtendWith(SpringExtension.class)
public class TaskControlleTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldGetTask() throws Exception{
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        List<Task> tasks = new ArrayList<>() ;

        //When
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldAddTask() throws Exception{
        //Given
        Task task = new Task(1L, "Test title", "Test description");
        TaskDto taskDto = new TaskDto(1L, "Test title DTO", "Test description DTO");

        //When
        when(dbService.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //Then
        mockMvc
                .perform(MockMvcRequestBuilders
                    .get("/v1/tasks/1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Updated title", "Updated description");
        TaskDto taskDto = new TaskDto(1L, "Updated title DTO", "Updated description DTO");

        //When
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(taskDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated title DTO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Updated description DTO"));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        //Given
        Long taskId = 1L;

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
