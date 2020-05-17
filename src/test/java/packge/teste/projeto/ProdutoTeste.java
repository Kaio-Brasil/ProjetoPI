package packge.teste.projeto;

import com.acai.model.entidade.Produto;
import com.acai.model.regradenegocio.ProdutoRN;

/**
 *
 * @author kaio
 */
public class ProdutoTeste {
    public void alterarProduto() {
        ProdutoRN rn = new ProdutoRN(ProdutoRN.HIBERNATE_PRODUTO_DAO);
        Produto produto = rn.buscarProduto(68);
        produto.setStatusProduto(true);
        rn.alterarProduto(produto);
    }
}
