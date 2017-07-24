

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DbCart;
import customTools.DbProduct;
import model.Cart;
import model.Product;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		

		HttpSession session = request.getSession();
		String nextURL;
		
	    if (session.getAttribute("user")==null){
	        //http://stackoverflow.com/questions/13638446/checking-servlet-session-attribute-value-in-jsp-file
	        nextURL = "/login.jsp";
	        response.sendRedirect(request.getContextPath() + nextURL);
	        return;//return prevents an error; Don't believe me? Take it out.
	    }
		
		int remove = Integer.parseInt(request.getParameter("remove"));
		
		if (remove != 0){
			DbCart.delete(DbCart.getCart(remove));
			List<Cart> shoppingCart = DbCart.listOfCarts();
			session.setAttribute("shoppingCart", shoppingCart);
			response.sendRedirect(request.getContextPath() + "/cart.jsp");
			//getServletContext().getRequestDispatcher("/cart.jsp").forward(request,response);
			return;
		}
		
		int productid = Integer.parseInt(request.getParameter("productid"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Cart c = new Cart();
		Product p = DbProduct.getProduct(productid);
		
		c.setProduct(p);
		c.setQuantity(quantity);
		c.setUserid(7);
		DbCart.insert(c);
		
		List<Cart> shoppingCart = DbCart.listOfCarts();
		
		session.setAttribute("shoppingCart", shoppingCart);
		getServletContext().getRequestDispatcher("/cart.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
