package com.synchrode.workshopmongo.repository;

import java.util.Date;
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
	
	@Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }") 
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	// reutilizei a expressão de cima no searchTitle para buscar no title

	/*
	  { field: { $gte: value } } -> essa expressão ($gte) 
	  verifica se o valor é maior ou igual que. 
	  
	  { field: { $lte: value } } -> essa expressão ($lte) 
	  verifica se o valor é menor ou igual que. 
	  
	  ?1 -> é o minDate
	  
	  { 'comments.text': { $regex: ?0, $options: 'i' } } -> como
	  comments é uma lista, eu não coloco somente comments, mas
	  coloco comments.text pois dessa forma eu estou fazendo
	  uma busca dentro do texto de cada comentário
	*/
}
