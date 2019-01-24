package palgato.kodos;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class KodosApplication {

	public static void main(String[] args) {

		SpringApplication.run(KodosApplication.class, args);

	}

/*	private static String readFile(S3ObjectInputStream inputStream) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()))) {
			return br.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/

}