package sp.ManipulacaoFIles;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sp.ManipulacaoFIles.model.Pessoa;

public class App {
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Pessoa.class).buildSessionFactory();

		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			insertDataFromFile(session, "Dados.txt");

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}

	}

	private static void insertDataFromFile(Session session, String filePath) {
	    try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream(filePath)) {
	        if (inputStream != null) {
	            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split("\\|");
	                if (data.length >= 4) {
	                    String nome = data[1];
	                    String cpf = data[2];
	                    String endereco = data[3];

	                    Pessoa pessoa = new Pessoa(nome, cpf, endereco);

	                    session.save(pessoa);
	                }
	            }
	            br.close();
	        } else {
	            System.out.println("File not found: " + filePath);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
