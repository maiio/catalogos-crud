
package com.transportesmiranda.catalogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transportesmiranda.catalogos.model.Cliente;

/**
 * Clase de persistencia para acceder al catalogo de clientes.
 * @author mayolomirandamiranda
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}