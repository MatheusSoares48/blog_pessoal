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

import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired //Injeção de independencia, usando metodos dessa interface
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(temaRepository.findAll()); 
		
		// SELECT * FROM tb_postagens;
	}
	
	//Pega o id especifico da postagem especifica através do PathVariable
	@GetMapping("/{id}")
	public ResponseEntity<Tema>  getById(@PathVariable Long id){
		
		return temaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
		// SELECT * FROM tb_postagens
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
		
	@PostMapping
	public ResponseEntity<Tema> post(@Valid @RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(temaRepository.save(tema));
		
		//INSERT INTO tb_postagens (titulo, texto) VALUES (?, ?);
	}
	
	@PutMapping
	public ResponseEntity<Tema> put(@Valid @RequestBody Tema tema){
		if(temaRepository.existsById(tema.getId())) {
			return ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema));
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
				
		
		//INSERT INTO tb_postagens SET titulo = ?, texto = ?  WHERE id = ?;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		if(temaRepository.existsById(id)) {
			temaRepository.deleteById(id);
		}else {	
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		//DELETE FROM tb_postagens WHERE id = ?;
	}
}
