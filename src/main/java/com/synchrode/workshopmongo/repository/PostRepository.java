package com.synchrode.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.synchrode.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }") // { <field>: { $regex: /pattern/, $options: '<options>' } } 
	List<Post> searchTitle(String text);
	
	/*
	  Colocamos ?0 pois entra ali o primeiro parâmetro (text),
	  que é o que o usuário vai digitar.Se fosse o segundo a 
	  entrar ali, colocariamos ?1. 
	*/
}
