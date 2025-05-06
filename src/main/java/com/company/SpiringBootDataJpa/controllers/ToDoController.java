package com.company.SpiringBootDataJpa.controllers;

import com.company.SpiringBootDataJpa.models.ToDoCriteria;
import com.company.SpiringBootDataJpa.models.ToDos;
import com.company.SpiringBootDataJpa.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Tag(name = "ToDo Controller", description = "This controller responsible for ToDo entity")
public class ToDoController {

    private final ToDoService toDoService;


    @PostMapping
    @Operation(summary = "Create ToDo endpoint", description = "This is for creating ToDo entity (description)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "ToDo created Successfully",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ToDos.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "While creating ToDo error occurred",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    }
    )
    public ResponseEntity<ToDos> createToDo(@RequestBody ToDos toDo) {
        return toDoService.createToDo(toDo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get ToDo by ID", description = "Get ToDo by it's Id (desc)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "ToDo found successfully by Id",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ToDos.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "ToDo not found by Id",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "While creating ToDo error occurred",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    }
    )
    public ResponseEntity<ToDos> getToDoById(@PathVariable Integer id) {
        return toDoService.getToDoById(id);
    }

    @Operation(summary = "Get All ToDos", description = "Get All ToDos List (desc)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "ToDos List found successfully",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ToDos.class))
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "ToDo List not found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "While getting  ToDo list  error occurred",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    }
    )
    @PostMapping("/get-all")
    public ResponseEntity<List<ToDos>> getAllToDos(@RequestBody ToDoCriteria toDoCreteria) {
        System.out.println(toDoCreteria);
        return toDoService.getAllToDos();
    }

    @Operation(summary = "Update ToDo by ID", description = "Update ToDo by it's Id (desc)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "ToDo updated successfully by Id",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ToDos.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "ToDo not found by Id",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "While updating ToDo error occurred",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    }
    )
    @PutMapping
    public ResponseEntity<ToDos> updateToDo(@RequestBody ToDos toDo) {
        return toDoService.updateToDo(toDo);
    }


    @Operation(summary = "Delete ToDo by ID", description = "Delete ToDo by it's Id (desc)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "ToDo deleted successfully",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ToDos.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "ToDo not found by Id",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "While deleting ToDo error occurred",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable Integer id) {
        return toDoService.deleteToDoById(id);
    }
}
