package com.alisonlima.cursomc.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alisonlima.cursomc.domain.Pedido;
import com.alisonlima.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	// metodo de busca
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);

		return obj.orElseThrow(() -> new com.alisonlima.cursomc.services.exceptions.ObjectNotFoundException(
				"Objeto não Encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
