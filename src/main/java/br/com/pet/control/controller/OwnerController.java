package br.com.pet.control.controller;

import br.com.pet.control.Application;
import br.com.pet.control.dto.PetOwnerDTO;
import br.com.pet.control.logger.LogExecutionTime;
import br.com.pet.control.model.PetEntity;
import br.com.pet.control.model.PetOwnerEntity;
import br.com.pet.control.services.PetOwnerServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/owner")
@Tag(name="Owners", description="End points for managing clients")
public class OwnerController {

	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
    @Autowired	
	private PetOwnerServices service;
    @LogExecutionTime
	@GetMapping( produces= MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(value = "listaOwners", key ="#id", condition="#p0!=null")
	@Operation(summary = "Show all clients on this API", description= "Show all clients on this API",

	responses = {
			@ApiResponse(description ="Success", responseCode = "200", content = {@Content(
					mediaType = "application/json",
					array = @ArraySchema (schema = @Schema( implementation = PetOwnerEntity.class))
			)

					}),
			@ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
			@ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
	})
	public List<PetOwnerDTO> findAll()	 {
    	return service.findAll();
	}
	@GetMapping(value = "/{id}",
			produces= MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find one client by id", description= "Find one client by id",

			responses = {
					@ApiResponse(description ="Success", responseCode = "200", content = {@Content(
							mediaType = "application/json",
							array = @ArraySchema (schema = @Schema( implementation = PetOwnerEntity.class))
					)

					}),
					@ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
					@ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
			})
	public PetOwnerDTO findByid(@PathVariable(value = "id") Long id)
	{

		return service.findByid(id);
	}
	@PostMapping(produces= MediaType.APPLICATION_JSON_VALUE,
			     consumes =MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create new Owner", description= "Create new Owner",

			responses = {
					@ApiResponse(description ="Success", responseCode = "200", content = {@Content(
							mediaType = "application/json",
							array = @ArraySchema (schema = @Schema( implementation = PetEntity.class))
					)

					}),
					@ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
					@ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
			})
	public PetOwnerEntity create(@RequestBody PetOwnerEntity owner)
	{

		  return service.create(owner);
	}
	@PutMapping(
			   produces= MediaType.APPLICATION_JSON_VALUE,
			   consumes =MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update a pet information", description= "Update a owner information",

			responses = {
					@ApiResponse(description ="Success", responseCode = "200", content = {@Content(
							mediaType = "application/json",
							array = @ArraySchema (schema = @Schema( implementation = PetEntity.class))
					)

					}),
					@ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
					@ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
			})
	public PetOwnerEntity update(@RequestBody PetOwnerEntity owner)
	{

		return service.update(owner);

	}
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a owner by id", description= "Delete a owner by id",

			responses = {
					@ApiResponse(description ="Success", responseCode = "200", content = {@Content(
							mediaType = "application/json",
							array = @ArraySchema (schema = @Schema( implementation = PetEntity.class))
					)

					}),
					@ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
					@ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
			})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id)
	{
		 service.delete(id);
		 return ResponseEntity.noContent().build();

	}
}
