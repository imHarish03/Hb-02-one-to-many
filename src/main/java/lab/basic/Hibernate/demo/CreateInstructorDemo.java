package lab.basic.Hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lab.basic.Hibernate.demo.entity.Course;
import lab.basic.Hibernate.demo.entity.Instructor;
import lab.basic.Hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				// Hibernate will load default file
				.configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		try {
			Session session = factory.getCurrentSession();

			// Start transaction
			session.beginTransaction();

			Instructor tempInstructor = new Instructor("Susan", " Public", "susan.public@luv2code.com");
			InstructorDetail detail = new InstructorDetail("http://susan", "Video Games");

			tempInstructor.setInstructorDetail(detail);

			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			factory.close();
		}
	}
}
