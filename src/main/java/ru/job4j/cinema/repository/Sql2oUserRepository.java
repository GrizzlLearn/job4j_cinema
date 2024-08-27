package ru.job4j.cinema.repository;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.User;
import java.util.Optional;

/**
 * @author dl
 * @date 27.08.2024 17:34
 */
@Repository
public class Sql2oUserRepository implements UserRepository {
	private static final Logger LOG = LoggerFactory.getLogger(Sql2oUserRepository.class.getName());
	private final Sql2o sql2o;

	public Sql2oUserRepository(Sql2o sql2o) {
		this.sql2o = sql2o;
	}

	@Override
	public Optional<User> save(User user) {
		Optional<User> result = Optional.empty();
		try (var connection = sql2o.open()) {
			String sql = """
					INSERT INTO users(email, full_name, password)
					VALUES (:email, :name, :password)
					""";
			var query = connection.createQuery(sql, true)
					.addParameter("email", user.getEmail())
					.addParameter("name", user.getName())
					.addParameter("password", user.getPassword());
			int generatedId = query.executeUpdate().getKey(Integer.class);
			user.setId(generatedId);
			result = Optional.of(user);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		try (var connection = sql2o.open()) {
			String sql = "SELECT * FROM users WHERE email = :email AND password = :password";
			var query = connection.createQuery(sql)
					.addParameter("email", email)
					.addParameter("password", password);
			return Optional.ofNullable(query.executeAndFetchFirst(User.class));
		}
	}
}
