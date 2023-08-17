package vn.techmaster.demo;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.techmaster.demo.database.PostDB;
import vn.techmaster.demo.utils.IFileReader;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	@Autowired
	private IFileReader fileReader;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// PostDB.postList = fileReader.readFile("static/post.json");
		String filePath = System.getProperty("user.dir").concat(File.separator).concat("post.json");
		PostDB.postList = fileReader.readFile(filePath);
	}

}
