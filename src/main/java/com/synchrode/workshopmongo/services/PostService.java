package com.synchrode.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synchrode.workshopmongo.domain.Post;
import com.synchrode.workshopmongo.repository.PostRepository;
import com.synchrode.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired 
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		//maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 *1000);
		// no meu caso não foi necessária essa linha
		return repo.fullSearch(text, minDate, maxDate);
	}
	
	/*
	  maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 *1000);
	  Esta linha adiciona 24 horas à maxDate. Isso é feito porque a 
	  data máxima fornecida pelo usuário é considerada até a meia-noite
	  daquele dia, mas queremos incluir todos os posts até o final do 
	  dia (23:59:59.999).
	  
	  Em termos técnicos, as datas em Java (e muitas outras 
	  linguagens) são armazenadas com precisão de milissegundos. 
	  Então, para garantir que todos os posts do dia fornecido 
	  sejam incluídos, precisamos ajustar a data máxima para o 
	  início do próximo dia.
	*/
}
