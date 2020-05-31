package packge.teste.projeto;

import com.acai.model.entidade.Produto;
import com.acai.model.regradenegocio.ProdutoRN;
import java.math.BigDecimal;

/**
 *
 * @author kaio
 */
public class ProdutoTeste {
    private static void alterarProduto() {
        ProdutoRN rn = new ProdutoRN(ProdutoRN.HIBERNATE_PRODUTO_DAO);
        Produto produto = rn.buscarProduto(70);
        produto.setDescricao("TestePassou");
        produto.setPreco(new BigDecimal("5.50"));
        produto.setTipo("ExtraA");
        produto.setStatusProduto(true);
        rn.alterarProduto(produto);
    }

}
