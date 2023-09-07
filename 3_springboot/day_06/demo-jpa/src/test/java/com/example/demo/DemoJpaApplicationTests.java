package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Student;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class DemoJpaApplicationTests {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void save_student() {
		Student student = new Student(null, "Hiên", "hien@gmail.com", 27);
		studentRepository.save(student);
	}

	@Test
	void save_students() {
		Student student = new Student(null, "Bình", "binh@gmail.com", 31);
		Student student1 = new Student(null, "An", "an@gmail.com", 30);
		studentRepository.saveAll(List.of(student, student1));
	}

	@Test
	void save_all_student() {
		Faker faker = new Faker();
		for (int i = 0; i < 100; i++) {
			Student student = new Student(
					null,
					faker.name().fullName(),
					faker.internet().emailAddress(),
					faker.number().numberBetween(18, 50)
			);
			studentRepository.save(student);
		}
	}

	@Test
	void get_all_student() {
		List<Student> students = studentRepository.findAll();
		students.forEach(System.out::println);
	}

	@Test
	void get_by_id() {
		Student student = studentRepository.findById(1).orElse(null);
		System.out.println(student);
	}

	@Test
	void update_student() {
		Student student = studentRepository.findById(1).orElse(null); // name = Hiên
		student.setName("Bùi Hiên");
		studentRepository.save(student);
	}

	@Test
	void delete_by_id() {
		// Xóa theo đối tuượng
		Student student = studentRepository.findById(1).orElse(null); // name = Hiên
		studentRepository.delete(student);

		// Xoa theo id
		studentRepository.deleteById(2);
	}

	@Test
	void find_by_email() {
		Student student = studentRepository.findByEmail("hien@gmail.com").orElse(null);
		System.out.println(student);
	}

	@Test
	void save_all_employee() {
		Faker faker = new Faker();
		Random rd = new Random();
		List<String> departments = List.of("A", "B", "C", "D");
		Date from = new Date(1204095742779L);
		Date to = new Date(1604095742779L);
		for (int i = 0; i < 30; i++) {
			Employee employee = new Employee(
					faker.name().fullName(),
					departments.get(rd.nextInt(departments.size())),
					(long) faker.number().numberBetween(1000, 5000),
					covertToLocalDate(faker.date().between(from, to))
			);
			employeeRepository.save(employee);
		}
	}

	private LocalDate covertToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
