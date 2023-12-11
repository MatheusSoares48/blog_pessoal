package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired //Injeção de independencia, usando metodos dessa interface
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll()); 
		
		// SELECT * FROM tb_postagens;
	}
	
	//Pega o id especifico da postagem especifica através do PathVariable
	@GetMapping("/{id}")
	public ResponseEntity<Postagem>  getById(@PathVariable Long id){
		
		//Jeito de fazer sem usar expressõe Lambda
		/*Optional<Postagem> resposta	= postagemRepository.findById(id);
		if (resposta.isPresent())
			return ResponseEntity.ok(resposta);
		else
			return ResponseEntity.notFound().build();*/
		
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
		// SELECT * FROM tb_postagens
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
		
		//INSERT INTO tb_postagens (titulo, texto) VALUES (?, ?);
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		if(postagemRepository.existsById(postagem.getId())) {
			return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
				
		
		//INSERT INTO tb_postagens SET titulo = ?, texto = ?  WHERE id = ?;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		if(postagemRepository.existsById(id)) {
			postagemRepository.deleteById(id);
		}else {	
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		//DELETE FROM tb_postagens WHERE id = ?;
	}
}

