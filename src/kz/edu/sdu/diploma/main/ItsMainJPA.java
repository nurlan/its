package kz.edu.sdu.diploma.main;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import kz.edu.sdu.diploma.persistance.Answer;
import kz.edu.sdu.diploma.persistance.Question;
import kz.edu.sdu.diploma.persistance.Subject;
import kz.edu.sdu.diploma.persistance.User;

public class ItsMainJPA {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("its");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		User user = new User();
		user.setFirstname("Light");
		user.setLastname("Yagami");
		user.setPatronymic("Quintessential");
		user.setUsername("light");
		user.setPassword("yagami");
		
		kz.edu.sdu.diploma.persistance.Class clazz = new kz.edu.sdu.diploma.persistance.Class();
		clazz.setName("11A");

		Subject subject = new Subject();
		subject.setName("HISTORY");
		
		Question question = new Question();
		question.setQuestion("It is my 1st testing question, isn't it?");
		question.setLevel(1);
		question.setSubject(subject);
		question.setClazz(clazz);
		question.setAuthor(user);
		question.setCreatedDate(new Date());
		
		Answer answer1 = new Answer();
		answer1.setText("A");
		answer1.setAnswer(true);
		answer1.setQuestion(question);
		
		Answer answer2 = new Answer();
		answer2.setText("B");
		answer2.setAnswer(false);
		answer2.setQuestion(question);
		
		em.persist(user);
		em.persist(clazz);
		em.persist(subject);
		em.persist(question);
		em.persist(answer1);
		em.persist(answer2);
		
		em.getTransaction().commit();
	}
}
