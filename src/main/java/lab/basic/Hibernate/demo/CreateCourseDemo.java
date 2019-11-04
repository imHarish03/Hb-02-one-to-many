package lab.basic.Hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lab.basic.Hibernate.demo.entity.Course;
import lab.basic.Hibernate.demo.entity.Instructor;
import lab.basic.Hibernate.demo.entity.InstructorDetail;

public class CreateCourseDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				// Hibernate will load default file
				.configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		try {
			Session session = factory.getCurrentSession();

			int id = 3;

			// Start transaction
			session.beginTransaction();
			Instructor instructor = (Instructor) session.get(Instructor.class, id);

			Course tempCourse1 = new Course("Air guitar");
			Course tempCourse2 = new Course("Pinball");

			instructor.add(tempCourse1);
			instructor.add(tempCourse2);

			session.save(tempCourse1);
			session.save(tempCourse2);

			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			factory.close();
		}
	}
}
