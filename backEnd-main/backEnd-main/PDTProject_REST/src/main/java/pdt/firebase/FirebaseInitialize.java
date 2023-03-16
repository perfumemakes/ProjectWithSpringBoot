package pdt.firebase;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseInitialize {
	
	@PostConstruct
	public void initialize() {
		
		try {

		FileInputStream serviceAccount = new FileInputStream("C:\\Users\\perfu\\PlayData\\102.FinalProject\\PDTProject\\playdatatalk-firebase-adminsdk-znqdh-f609f4e8be.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://playdatatalk-default-rtdb.firebaseio.com").build();

		FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
