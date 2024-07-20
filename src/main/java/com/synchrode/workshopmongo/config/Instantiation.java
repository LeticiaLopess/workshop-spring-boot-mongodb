package com.synchrode.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.synchrode.workshopmongo.domain.Post;
import com.synchrode.workshopmongo.domain.User;
import com.synchrode.workshopmongo.repository.PostRepository;
import com.synchrode.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	/*
	  O CommandLineRunner é uma interface do Spring Boot usada 
	  para executar código após a inicialização do contexto da 
	  aplicação. É útil para tarefas de inicialização que 
	  precisam ser executadas assim que a aplicação começa, como 
	  populações de banco de dados, configuração inicial ou 
	  qualquer outra lógica que precise ser executada no início 
	  da aplicação.
	*/
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", maria);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", maria);
		// no author teremos uma cópia do objeto do User, é uma cópia e não uma referência.
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
