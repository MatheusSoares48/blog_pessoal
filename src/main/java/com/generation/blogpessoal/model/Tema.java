package com.generation.blogpessoal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_temas") // CREATE TABLE tb_tema
public class Tema {

		@Id //Primary Key
		@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
		private Long id;
		
		@Column(length = 1000) // Define o maximo 
		@NotBlank(message = "O Atributo Descrição é Obrigatorio e Não pode ser vazio") // NOT NULL do SQL só que não aceita nem espaço em branco ou pode usar NOT NULL que aceita espaços em branco
		@Size(min = 5, max = 1000, message = "O Atributo Texto deve conter no mínimo 5 e no máximo 1000 carácteres.")
		private String descricao;

		@OneToMany(mappedBy = "tema", cascade = CascadeType.REMOVE)
		@JsonIgnoreProperties
		private List<Postagem> postagem;
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public List<Postagem> getPostagem() {
			return postagem;
		}

		public void setPostagem(List<Postagem> postagem) {
			this.postagem = postagem;
		}
		
	}
	

