package com.transportesmiranda.catalogos.service;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.transportesmiranda.catalogos.model.Cliente;

public interface ClienteService {
	Optional<Cliente> readCliente(long id);
	List<Cliente> getAllclientes();
	Cliente createCliente(Cliente cliente);
	Cliente updateCliente(long id, Cliente cliente);
	Boolean deleteCliente(long id);
}
