package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Cart;

public class DbCart {
	
	public static Cart getCart(int cartID)
	{
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		Cart cart = em.find(Cart.class, cartID);
		return cart;		
	}
	
	public static void insert(Cart cart) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(cart);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Cart cart) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(cart);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static List<Cart> listOfCarts (){
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "select b from Cart b";
		
		List<Cart> posts = null;
		try{
			TypedQuery<Cart> query = em.createQuery(qString,Cart.class);
			posts = query.getResultList();

		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
				em.close();
			}
		return posts;
	}
	
	public static void delete(Cart cart) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.remove(em.merge(cart));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
//	public static List<Product> postsofUser(long userid)
//	{
//		EntityManager em = DbUtil.getEmFactory().createEntityManager();
//		List<Product> userposts = null;
//		String qString = "select b from products b where b.bhuser.bhuserid = :userid";
//		
//		try{
//			TypedQuery<Product> query = em.createQuery(qString,Product.class);
//			query.setParameter("userid", userid);
//			userposts = query.getResultList();
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		finally{
//				em.close();
//			}
//		return userposts;	
//	}
	
//	public static List<Product> listOfProducts(String useremail)
//	{
//		EntityManager em = DbUtil.getEmFactory().createEntityManager();
//		List<Product> userposts = null;
//		String qString = "select b from products b "
//				+ "where b.bhuser.useremail = :useremail";
//		
//		try{
//			TypedQuery<Product> query = em.createQuery(qString,Product.class);
//			query.setParameter("useremail", useremail);
//			userposts = query.getResultList();
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		finally{
//				em.close();
//			}
//		return userposts;	
//	}
	
//	public static List<Product> searchProducts (String search)
//	{
//		EntityManager em = DbUtil.getEmFactory().createEntityManager();
//		List<Product> searchposts = null;
//		String qString = "select b from products b "
//				+ "where b.posttext like :search";
//		
//		try{
//			TypedQuery<Product> query = em.createQuery(qString,Product.class);
//			query.setParameter("search", "%" + search + "%");
//			searchposts = query.getResultList();
//		}catch (Exception e){
//			e.printStackTrace();
//		}finally{
//			em.close();
//		}return searchposts;
//	}
	
}