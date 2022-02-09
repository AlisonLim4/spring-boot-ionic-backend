package com.alisonlima.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alisonlima.cursomc.domain.Categoria;
import com.alisonlima.cursomc.domain.Produto;
import com.alisonlima.cursomc.repositories.CategoriaRepository;
import com.alisonlima.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository categoriaRepository;

	// metodo de busca
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);

		return obj.orElseThrow(() -> new com.alisonlima.cursomc.services.exceptions.ObjectNotFoundException(
				"Objeto não Encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerpage,String orderBy,String direction){
			PageRequest pageRequest = PageRequest.of(page, linesPerpage, Direction.valueOf(direction),orderBy);
			List<Categoria> categorias = categoriaRepository.findAllById(ids);
			return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);
	}
}

