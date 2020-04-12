package packge.teste.projeto;

import com.acai.controller.FreteBean;
import com.acai.model.entidade.Frete;
import com.acai.model.entidadedao.ClienteDAO;
import com.acai.model.regradenegocio.ClienteRN;
import java.math.BigDecimal;
import com.acai.model.entidade.Cliente;
import com.acai.util.HibernateUtil;

public class Teste {
    public static void main(String[] args) {
        /*HibernateUsuarioDAO hud = new HibernateUsuarioDAO();
        Usuario usuario = hud.buscar(1);
	new UsuarioController().excluirUsuarioAction(usuario);*/
        
//        Usuario usuario = new Usuario();
//        usuario.setIdUsuario(1);
//        usuario.setNome("Paulo");
//        usuario.setSenha("123456");
//        new UsuarioController().excluirUsuarioAction(usuario);

//            Frete f = new Frete();
//            FreteBean fc = new FreteBean();
//            f = fc.buscarFreteAction(1);
//            System.out.print(f.getCodigoFrete()+" valor: "+f.getValor());

//          ClienteRN cli = new ClienteRN(ClienteRN.HIBERNATE_CLIENTE_DAO);
//          Cliente c = new Cliente();
//          c = cli.autenticar("luizinho@ifpe.com", "12345");
//          System.out.println("Email: "+c.getEmail()+" senha: "+c.getSenha());

           HibernateUtil.getSessionFactory();
           HibernateUtil.getSessionFactory().openSession();
    }
}
