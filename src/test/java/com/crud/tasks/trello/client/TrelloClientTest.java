package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class TrelloClientTest {

    private TrelloMapper trelloMapper = new TrelloMapper();
    private TaskMapper taskMapper = new TaskMapper();

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {

        //Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn(("http://test.com"));
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrelloUser()).thenReturn("test");

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "Kodilla", new ArrayList<>());

        URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("Kodilla", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() throws URISyntaxException {
        // Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");

        TrelloCardDto trelloCardDto = new TrelloCardDto("Test task", "Test Description", "top", "test_id");
        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto("1", "Test task", "http://test.com");

        when(restTemplate.postForObject(uri, null, CreatedTrelloCardDto.class)).thenReturn(createdTrelloCard);
        // When
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrelloUser()).thenReturn("test");

        URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);
        //When
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        //Then
        assertEquals(0, trelloBoards.size());
        assertNotNull(trelloBoards);
    }

    @Test
    public void mapToBoardsTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = Arrays.asList(
                new TrelloBoardDto("1", "BoardNameOne", Arrays.asList(new TrelloListDto("1", "ListOne", false))),
                new TrelloBoardDto("2", "BoardNameTwo", Arrays.asList(new TrelloListDto("1", "ListTwo", true)))
        );

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(2, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("BoardNameOne", trelloBoards.get(0).getName());
        assertEquals("ListOne", trelloBoards.get(0).getLists().get(0).getName());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        List<TrelloBoard> trelloBoards = Arrays.asList(
                new TrelloBoard("1", "BoardNameOne", Arrays.asList(new TrelloList("1", "ListOne", false))),
                new TrelloBoard("2", "BoardNameTwo", Arrays.asList(new TrelloList("1", "ListTwo", true)))
        );

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(2, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("BoardNameOne", trelloBoards.get(0).getName());
        assertEquals("ListOne", trelloBoards.get(0).getLists().get(0).getName());
    }

    @Test
    public void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListDtos = Arrays.asList(
                new TrelloListDto("1", "ListOne", false),
                new TrelloListDto("2", "ListTwo", true)
        );

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals(2, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("ListOne", trelloLists.get(0).getName());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloLists = Arrays.asList(
                new TrelloList("1", "ListOne", false),
                new TrelloList("2", "ListTwo", true)
        );

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(2, trelloListDtos.size());
        assertEquals("2", trelloListDtos.get(1).getId());
        assertEquals("ListTwo", trelloListDtos.get(1).getName());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Task", "Description", "top", "list_id");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("Task", trelloCard.getName());
        assertEquals("Description", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("list_id", trelloCard.getListId());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Task", "Description", "top", "list_id");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("Task", trelloCardDto.getName());
        assertEquals("Description", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("list_id", trelloCardDto.getListId());
    }

    @Test
    public void MapToTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Title one", "Content one");

        // When
        Task task = taskMapper.mapToTask(taskDto);

        // Then
        assertNotNull(task);
        assertEquals(1L, task.getId());
        assertEquals("Title one", task.getTitle());
        assertEquals("Content one", task.getContent());
    }

    @Test
    public void MapToTaskDto() {
        // Given
        Task task = new Task(1L, "Title two", "Content two");

        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        // Then
        assertNotNull(taskDto);
        assertEquals(1L, taskDto.getId());
        assertEquals("Title two", taskDto.getTitle());
        assertEquals("Content two", taskDto.getContent());
    }

    @Test
    public void MapToTaskDtoList() {
        // Given
        List<Task> taskList = List.of(
                new Task(1L, "Title 1", "Content 1"),
                new Task(2L, "Title 2", "Content 2")
        );

        // When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        // Then
        assertNotNull(taskDtoList);
        assertEquals(2, taskDtoList.size());
        assertEquals(1L, taskDtoList.get(0).getId());
        assertEquals("Title 1", taskDtoList.get(0).getTitle());
        assertEquals("Content 1", taskDtoList.get(0).getContent());
    }

    @Test
    public void shouldSetAndGetBoard() {
        // Given
        Trello trello = new Trello();

        // When
        trello.setBoard(5);

        // Then
        assertEquals(5, trello.getBoard());
    }

    @Test
    public void shouldSetAndGetCard() {
        // Given
        Trello trello = new Trello();

        // When
        trello.setCard(10);

        // Then
        assertEquals(10, trello.getCard());
    }

    @Test
    public void shouldCreateTrelloBoardWithAllArgsConstructor() {
        // Given
        List<TrelloList> trelloLists = Arrays.asList(
                new TrelloList("1", "List One", false),
                new TrelloList("2", "List Two", true)
        );

        // When
        TrelloBoard trelloBoard = new TrelloBoard("123", "Board Name", trelloLists);

        // Then
        assertEquals("123", trelloBoard.getId());
        assertEquals("Board Name", trelloBoard.getName());
        assertEquals(2, trelloBoard.getLists().size());
        assertEquals("List One", trelloBoard.getLists().get(0).getName());
    }

    @Test
    public void shouldCreateTrelloBoardWithNoArgsConstructor() {
        // When
        TrelloBoard trelloBoard = new TrelloBoard();

        // Then
        assertNull(trelloBoard.getId());
        assertNull(trelloBoard.getName());
        assertNull(trelloBoard.getLists());
    }

    @Test
    public void shouldCreateTrelloBoardDtoWithAllArgsConstructor() {
        // Given
        List<TrelloListDto> trelloLists = Arrays.asList(
                new TrelloListDto("1", "List One", false),
                new TrelloListDto("2", "List Two", true)
        );

        // When
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("123", "Board Name", trelloLists);

        // Then
        assertEquals("123", trelloBoardDto.getId());
        assertEquals("Board Name", trelloBoardDto.getName());
        assertEquals(2, trelloBoardDto.getLists().size());
        assertEquals("List One", trelloBoardDto.getLists().get(0).getName());
    }

    @Test
    public void shouldCreateTrelloBoardDtoWithNoArgsConstructor() {
        // When
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();

        // Then
        assertNull(trelloBoardDto.getId());
        assertNull(trelloBoardDto.getName());
        assertNull(trelloBoardDto.getLists());
    }

    @Test
    public void shouldSetFieldsCorrectly() {
        // Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();

        List<TrelloListDto> trelloLists = Arrays.asList(
                new TrelloListDto("1", "List One", false),
                new TrelloListDto("2", "List Two", true)
        );

        // When
        trelloBoardDto.setId("123");
        trelloBoardDto.setName("Board Name");
        trelloBoardDto.setLists(trelloLists);

        // Then
        assertEquals("123", trelloBoardDto.getId());
        assertEquals("Board Name", trelloBoardDto.getName());
        assertEquals(2, trelloBoardDto.getLists().size());
    }



}
