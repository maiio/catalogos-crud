package com.transportesmiranda.catalogos.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.transportesmiranda.catalogos.model.Cliente;
import com.transportesmiranda.catalogos.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class ClienteController {
	
	 private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	ClienteService clienteService;

	@Operation(summary = "Muestra versión de servicio.")
	@GetMapping("/version")
	public String version() {
		return "version: 1.0.0";
	}

	@Operation(summary = "Consulta todos los clientes del catalogo.")
	@GetMapping("/clientes/all")
	public ResponseEntity<List<Cliente>> getAllclientes() {

		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			clientes = clienteService.getAllclientes();
			if (clientes.isEmpty()) {
				logger.info("Sin clientes encontrados!");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			logger.info("Todos los clientes son: {}", clientes.size());
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Operation(summary = "Consulta cliente a partir del idCliente.")
	@GetMapping("/clientes/read/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") long id) {
		logger.info("Entrando a getclienteById: {}", id);
		Optional<Cliente> clienteData = clienteService.readCliente(id);
		if (clienteData.isPresent()) {
			logger.info("Cliente {} identificado satisfactoriamente!",id);
			return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
		} else {
			logger.info("Cliente {} no encontrado!", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Operation(summary = "Registra un nuevo cliente.")
	@PostMapping("/clientes/create")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		logger.info("Entrando a createcliente {} ", cliente.toString());
		try {			
			Cliente clienteNuevo = clienteService.createCliente(cliente);
			logger.info("Cliente {} dado de alta satisfactoriamente!", cliente.getNombre());
			return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Actualza Cliente a partir de un idCliente.")
	@PutMapping("/clientes/update/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
		logger.info("Entrada a updateCliente {} ", cliente.toString());
		Optional<Cliente> clienteData = clienteService.readCliente(id);
		if (clienteData.isPresent()) {
			Cliente clienteModificado = clienteData.get();
			clienteModificado.setNombre(cliente.getNombre());
			clienteModificado.setFechaModificacion(LocalDate.now().toString());
			clienteModificado = clienteService.updateCliente(id, clienteModificado);
			logger.info("Cliente {} actualizado correctamente!!!", id);
			return new ResponseEntity<>(clienteModificado, HttpStatus.OK);
		} else {
			logger.info("Cliente {} no encontrado!!!", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Operation(summary = "Elimina Cliente del catalogo.")
	@DeleteMapping("/clientes/delete/{id}")
	public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") long id) {
		try {
			logger.info("Entrada deleteCliente: {}", id);
			Optional<Cliente> clienteData = clienteService.readCliente(id);
			if (clienteData.isPresent()) {
				clienteService.deleteCliente(id);
				logger.info("Cliente {} eliminado satisfactoriamente!!!", id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				logger.info("Cliente {} no encontrado!!!", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			logger.error("Error al eliminar el cliente {}", id);
            e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
