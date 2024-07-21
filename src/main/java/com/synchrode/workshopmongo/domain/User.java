package com.synchrode.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String mail;
	
	@DBRef(lazy=true) 
	private List<Post> posts = new ArrayList<>();
	/* 
	  @DBRef(lazy=true)
	  Usamos por que, como a gente está referenciando uma 
	  coleção, geralmente por padrão a gente não quer 
	  carregar automaticamente os Posts quando eu recuperar
	  um usuário do banco de dados.
	  
	  Vamos supor que você está recuperando os usuários para 
	  fazer um relatório de usuários e essa consulta te 
	  retorna mil usuários... imagina se para cada um desses 
	  mil usuários já viessem também a lista de Post deles...
	  Seriam muitos dados, muito tráfego na rede desnecessário.
	  
	  Essa anotação vai garantir pra gente que os posts só 
	  vão ser carregados se eu explicitamente acessá-los.
	  
	  Se eu não acessar os Posts quando eu carregar um 
	  usuário, virá somente os dados básicos do usuário.
	*/
	public User() {
	}

	public User(String id, String name, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
 }
