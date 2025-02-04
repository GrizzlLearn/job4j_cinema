package ru.job4j.cinema.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;
import ru.job4j.cinema.model.User;

import java.io.IOException;

/**
 * @author dl
 * @date 27.08.2024 17:51
 */

@Component
@Order(2)
public class SessionFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		var session = request.getSession();
		addUserToSession(session, request);
		chain.doFilter(request, response);
	}

	private void addUserToSession(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			user = new User();
			user.setName("Guest");
		}
		request.setAttribute("user", user);
	}
}
