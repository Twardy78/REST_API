package com.crud.tasks.trello.client;

import com.crud.tasks.controller.TrelloController;
import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloMapper trelloMapper;


    @Test
    void shouldFetchEmptyList() {
    // Given
    List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "test_list", false));
    List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "test", trelloLists));
    List<TrelloList> mappedTrelloLists = List.of(new TrelloList("1", "test_list", false));
    List<TrelloBoard> mappedTrelloBoards = List.of(new TrelloBoard("1", "test", mappedTrelloLists));
    when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
    when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
    when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(List.of());
    when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());

    // When
    List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

    // Then
    assertNotNull(trelloBoardDtos);
    assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    void shouldFetchTrelloBoards() {
    // Given
    List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "test_list", false));
    List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "test", trelloLists));
    List<TrelloList> mappedTrelloLists = List.of(new TrelloList("1", "test_list", false));
    List<TrelloBoard> mappedTrelloBoards = List.of(new TrelloBoard("1", "test", mappedTrelloLists));
    when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
    when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
    when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
    when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

    // When
    List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

    // Then
    assertNotNull(trelloBoardDtos);
    assertEquals(1, trelloBoardDtos.size());
    trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("test", trelloBoardDto.getName());
            trelloBoardDto.getLists().forEach(trelloListDto ->
            {
                assertEquals("1", trelloListDto.getId());
                assertEquals("test_list",
                        trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
            });
        });
    }

    @Test
    public void shouldHandleNullTrelloBoards() {

    //Given
    when(trelloService.fetchTrelloBoards()).thenReturn(null);

    //When
    List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

    //Then
    assertNotNull(trelloBoardDtos);
    assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void shouldHandleEmptyNamesInTrelloBoards() {
        // Given
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "", false));
        List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "", trelloLists));
        List<TrelloList> mappedTrelloLists = List.of(new TrelloList("1", "", false));
        List<TrelloBoard> mappedTrelloBoards = List.of(new TrelloBoard("1", "", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        // Debug
        System.out.println("Before validation: " + mappedTrelloBoards);

        // When
        List<TrelloBoardDto> result = trelloFacade.fetchTrelloBoards();

        // Debug
        System.out.println("After validation: " + result);

        // Then
        assertEquals(1, result.size());
    }

    @Test
    void shouldCallValidatorWhenFetchingBoards() {
        // Given
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "list", false));
        List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "board", trelloLists));
        List<TrelloList> mappedTrelloLists = List.of(new TrelloList("1", "list", false));
        List<TrelloBoard> mappedTrelloBoards = List.of(new TrelloBoard("1", "board", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        verify(trelloValidator, times(1)).validateTrelloBoards(mappedTrelloBoards);  // Sprawdź, czy metoda walidacji została wywołana
    }

    @Test
    void shouldNotCreateCardWithEmptyValues() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("", "", "", "");

        // When
        CreatedTrelloCardDto createdCard = trelloFacade.createCard(trelloCardDto);

        // Then
        assertNull(createdCard);  // Spodziewamy się, że karta nie zostanie utworzona, gdy wartości są puste
    }
}
