package io.dog;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmFactory {

	private static EntityManagerFactory instance;

	private EmFactory() {
		// "fora" was the value of the name attribute of the
		// persistence.xml file.
		instance = Persistence.createEntityManagerFactory("fora");
	}

	public EntityManager getEntityManager() {
		return instance.createEntityManager();
	}

	public void close() {
		instance.close();
	}
	
	public static EntityManagerFactory getInstance(){
		if (instance == null){
			instance = Persistence.createEntityManagerFactory("dogroyal");
		}
		return instance;
	}
	
	public static EntityManager createEntityManager(){
		return getInstance().createEntityManager();
	}
	

	public static <T> T transaction(Function<EntityManager,T> worker){

		EntityManager em = createEntityManager();
		em.getTransaction().begin();


		T result = worker.apply(em);

		em.getTransaction().commit();
		em.close();

		return result;
	}
	
	public static void voidTransaction(Consumer<EntityManager> worker){

		EntityManager em = createEntityManager();
		em.getTransaction().begin();


		worker.accept(em);

		em.getTransaction().commit();
		em.close();
	}

	
}
