package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class InvalidateCount
 */
@WebServlet("/invalidate")
public class InvalidateCount extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    // 既にセッションが存在する場合は一度破棄する
	    if (session != null) {
	      session.invalidate();
	    }

	    // セッション破棄後にログイン画面に遷移する
	    request.getRequestDispatcher("/WEB-INF/views/start.jsp").forward(request, response);
	}

}
