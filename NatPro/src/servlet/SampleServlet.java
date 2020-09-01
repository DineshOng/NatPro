package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SampleServlet
 */
@WebServlet(urlPatterns = "/SampleServlet", asyncSupported = true)
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SampleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

//		response.setContentType("text/html;charset=UTF-8");
//		try
//		{
//		    Thread.sleep(2000);
//		}
//		catch(InterruptedException ex)
//		{
//		    Thread.currentThread().interrupt();
//		}
//		
//		PrintWriter out = response.getWriter();
////		Date currentTime = new Date();
////		String message = String.format("Currently time is %tr on %tD.", currentTime, currentTime);
//		String message = "FINISHED";
//		out.println(message);

		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
	
		if (request.isAsyncSupported()) {
			AsyncContext aContext = request.startAsync();
			
//			out.println("hello");
			// Do whatever with context
			aContext.start(() -> {
				int i = 0;
				while (i < 5) {
//					out.println("<script>document.getElementById('bar2').style.width = '" + (i++) + "%'</script>");
//					out.println(i++);
					out.println("<script>document.getElementById('bar2').style.width = '" + (i++) + "%'</script>");
//					request.setAttribute("i", i);
//					i = i+1;
					System.out.println("hello");
					out.flush();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				}
				aContext.complete();
			});
			request.getRequestDispatcher("uploadprogress.jsp").forward(request, response);
		}
	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

	}

}
