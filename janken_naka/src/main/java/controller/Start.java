package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/start")
public class Start extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/views/start.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ユーザーの選択を取得
        String playerCount = request.getParameter("playerCount");
        
        // 人数選択をセッションとして保存
        HttpSession session = request.getSession();
        session.setAttribute("playerCount", playerCount);
        
        // jankenページに転送
        String view = "/WEB-INF/views/janken.jsp";
		request.getRequestDispatcher(view).forward(request, response);
    }
}
