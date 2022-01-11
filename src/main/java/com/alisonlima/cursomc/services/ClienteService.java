package com.alisonlima.cursomc.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alisonlima.cursomc.domain.Cliente;
import com.alisonlima.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	// metodo de busca
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new com.alisonlima.cursomc.services.exceptions.ObjectNotFoundException(
				"Objeto não Encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
