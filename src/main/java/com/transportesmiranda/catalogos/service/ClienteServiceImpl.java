package com.transportesmiranda.catalogos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.transportesmiranda.catalogos.model.Cliente;
import com.transportesmiranda.catalogos.repository.ClienteRepository;

/**
 * Clase de Logica de negocio para catalogo de Clientes.
 * @author mayolomirandamiranda
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	//Se inyecta repositorio para aceder a datos.
    @Autowired
    private ClienteRepository clienteRepository;
    
    /**
     * Metodo de logica para recuperar los datos del cliente a partir del id.
     * @param id -  clave del cliente.
     * @return clienteData - Lista de clientes recuperados.
     */
	public Optional<Cliente> readCliente(long id) {
        logger.info("El cliente a oconsultar es {}", id);
        Optional<Cliente> clienteData = clienteRepository.findById(id);
        return clienteData;	        		
    }
	
	/**
	 * Metodo para recuperar todos los clientes.
	 * @return Lista de clientes guardados en la bd.
	 * 
	 */
	public List<Cliente> getAllclientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		clienteRepository.findAll().forEach(clientes::add);
		return clientes;			
	}
	

	/**
	 * Metodo de logica de negocio para genear cliente.
	 * @param cliente - datos de entrada para generar cliente.
	 * @return Cliente guardado correctamente.
	 */
	public Cliente createCliente(Cliente cliente) {
		logger.info("Entrando a createcliente {} ", cliente.toString());
		Cliente clienteNuevo = new Cliente();
		try {
		    clienteNuevo.setNombre(cliente.getNombre());
		    clienteNuevo.setFechaRegistro(cliente.getFechaRegistro());
		    clienteNuevo.setFechaModificacion(cliente.getFechaModificacion());
			clienteNuevo = clienteRepository.save(clienteNuevo);
		} catch (Exception e) {
			e.getStackTrace();
		}
		logger.info("Cliente {} dado de alta satisfactoriamente!", cliente.getNombre());
		return clienteNuevo;
	}
	
	/**
	 * Metodo para actualizar datos del cliente.
	 * @param cliente - objeto que contiene los datos a modificar.
	 * @return Cliente - Regresa el objeto Cliente modificado.
	 */
	public Cliente updateCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
		logger.info("Entrada a updateCliente {} ", cliente.toString());
		Optional<Cliente> clienteData = clienteRepository.findById(id);
		if (clienteData.isPresent()) {
			Cliente clienteModificado = clienteData.get();
			clienteModificado.setNombre(cliente.getNombre());
			clienteModificado.setFechaModificacion(LocalDate.now().toString());
			clienteModificado = clienteRepository.save(clienteModificado);
			logger.info("Cliente {} actualizado correctamente!!!", id);
			return clienteModificado;
		} else {
			logger.info("Cliente {} no encontrado!!!", id);
			return null;
		}
	}
	
	/**
	 * Metodo que contiene la logica para eliinar un cliente.
	 * @param id - clave del cliente que se va eliminar.
	 * @return boleano que confirma si se elimino el cliente o no. 
	 */
	public Boolean deleteCliente(long id) {
		try {
			logger.info("Entrada deleteCliente: {}", id);
			Optional<Cliente> clienteData = this.readCliente(id);
			if (!clienteData.isPresent()) {
				logger.info("Cliente {} no encontrado!!!", id);
				return false;
			} 
			clienteRepository.deleteById(id);
			logger.info("Cliente {} eliminado satisfactoriamente!!!", id);
			
		} catch (Exception e) {
			logger.error("Error al eliminar el cliente {}", id);
            e.printStackTrace();
		}
		return true;
	}
}
