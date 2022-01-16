package com.alisonlima.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alisonlima.cursomc.domain.Categoria;
import com.alisonlima.cursomc.repositories.CategoriaRepository;
import com.alisonlima.cursomc.services.exceptions.DataIntegrityException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	// metodo de busca
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		return obj.orElseThrow(() -> new com.alisonlima.cursomc.services.exceptions.ObjectNotFoundException(
				"Objeto não Encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	// metodo de inserção
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);

	}

	// metodo de atualização
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	// metodo de deleção
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é Possivel excluir uma categoria que possui produtos");

		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();

	}

}
