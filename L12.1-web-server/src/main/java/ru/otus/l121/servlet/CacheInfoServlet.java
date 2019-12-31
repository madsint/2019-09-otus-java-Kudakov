package ru.otus.l121.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.otus.l121.cache.Cache;
import ru.otus.l121.dataset.DataSet;
import ru.otus.l121.dataset.DataSetKey;

@SuppressWarnings("serial")
public class CacheInfoServlet extends HttpServlet {

	private final Cache<DataSetKey, DataSet> cache;

	public CacheInfoServlet(Cache<DataSetKey, DataSet> cache) {
		this.cache = cache;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// If user has not logged in - redirect to login page
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			request.setAttribute("maxCapacity", cache.getProperties().get("maxCapacity"));
			request.setAttribute("lifeTime", cache.getProperties().get("lifeTime"));
			request.setAttribute("idleTime", cache.getProperties().get("idleTime"));
			request.setAttribute("isEternal", cache.getProperties().get("isEternal"));
			request.setAttribute("cacheSize", cache.getSize());
			request.setAttribute("hitCount", cache.getHitCount());
			request.setAttribute("missCount", cache.getMissCount());

			RequestDispatcher dispatcher = request.getRequestDispatcher("/cache-info.jsp");
			dispatcher.forward(request, response);
		}
	}
}
