package packge.teste.projeto;

import com.acai.util.HibernateUtil;

/**
 * 
 * @author kaio
 */

public class HibernateUtilTeste {
    public static void main(String[] args) {
           HibernateUtil.getSessionFactory();
           HibernateUtil.getSessionFactory().openSession();
           HibernateUtil.getSessionFactory().close();
    }
}
