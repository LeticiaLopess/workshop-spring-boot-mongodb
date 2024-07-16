package com.synchrode.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.synchrode.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{ // primeiro param - tipo da classe de domínio que ele vai gerenciar / segundo param - tipo do id da classe que está sendo gerenciada 

}
