package br.com.pet.control.controller;

import br.com.pet.control.Application;
import br.com.pet.control.dto.PetCareCreateDTO;
import br.com.pet.control.dto.PetCareResultDTO;
import br.com.pet.control.model.PetCareEntity;
import br.com.pet.control.model.PetEntity;
import br.com.pet.control.services.PetCareServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/petcare")
@Tag(name="Care", description="End points for managing pet care")
public class PetCareController {

	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
    @Autowired	
	private PetCareServices service;

	@GetMapping(value = "/{id}",
			produces= MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find cares by pet id", description= "Find pet careby pet id",

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
	public PetCareResultDTO findByid(@PathVariable(value = "id") Long id)
	{

		return service.findByPetid(id);
	}


	@PostMapping(produces= MediaType.APPLICATION_JSON_VALUE,
			     consumes =MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create new pet Care", description= "Create new pet care",

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
	public PetCareEntity create(@RequestBody PetCareCreateDTO care)
	{
		 PetCareCreateDTO petcare = new PetCareCreateDTO(care.petId(),care.dateTime(), care.hasGrooming());
	     return service.create(petcare);
	}

	@PutMapping(
			   produces= MediaType.APPLICATION_JSON_VALUE,
			   consumes =MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update a pet care information", description= "Update a pet care information",

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
	public PetCareEntity update(@RequestBody PetCareEntity care)
	{

		return service.update(care);

	}
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a pet care by id", description= "Delete a pet care by id",

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
