package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_postagens") // CREATE TABLE tb_postagens
public class Postagem {

	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Long id;
	
	@Column(length = 100) // Define o maximo
	@NotBlank(message = "O Atributo Titulo é Obrigatorio ;D") // NOT NULL do SQL só que não aceita nem espaço em branco ou pode usar NOT NULL que aceita espaços em branco
	@Size(min = 5, max = 100, message = "O Atributo Titulo deve conter no mínimo 5 e no máximo 100 carácteres.")
	private String titulo ;
	
	@Column(length = 1000) // Define o maximo 
	@NotBlank(message = "O Atributo Texto é Obrigatorio ;D") // NOT NULL do SQL só que não aceita nem espaço em branco ou pode usar NOT NULL que aceita espaços em branco
	@Size(min = 10, max = 1000, message = "O Atributo Texto deve conter no mínimo 10 e no máximo 1000 carácteres.")
	private String texto ;
	
	@UpdateTimestamp
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("postagens")
	private Tema tema;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
}
