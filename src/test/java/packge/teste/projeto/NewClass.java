package packge.teste.projeto;

import com.acai.model.entidade.Cliente;
import com.acai.model.entidade.EnderecoEntrega;
import com.acai.model.entidade.Frete;
import com.acai.model.entidade.ItemPedido;
import com.acai.model.entidade.Pedido;
import com.acai.model.entidade.Produto;
import java.math.BigDecimal;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaio
 */
public class NewClass {
    public void salvarItemCompra() {
        Produto produto = new Produto();
        produto.setDescricao("Feijao");
        BigDecimal bigdCompra = new BigDecimal("2.30");
        produto.setPreco(bigdCompra);
        produto.setTipo("Grao");
        
        Cliente cliente =  new Cliente();
        cliente.setNome("Paulo");
        cliente.setTelefone("99999999999");
        cliente.setEmail("paulo@ifpe.com");
        cliente.setSenha("12345");
        
        EnderecoEntrega ee = new EnderecoEntrega();
        ee.setRua("Av. Julio Brasileiro");
        ee.setNumero("120");
        ee.setBairro("Heliopolis");
        ee.setReferencia("Proximo ao posto de saude heliopolis");
        
        Frete frete = new Frete();
        BigDecimal bigdFrete = new BigDecimal("5.00");
        frete.setValor(bigdFrete);
        
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFrete(frete);
        pedido.setEnderecoEntrega(ee);
        pedido.setDataHora(new Date());
        BigDecimal valorDaCompra = bigdCompra.add(bigdFrete);
        pedido.setTotal(valorDaCompra);
        
        ItemPedido ip = new ItemPedido();
        ip.setPedido(pedido);
        ip.setProduto(produto);
        ip.setQuantidade(2);
        ip.setValor(produto.getPreco().multiply(new BigDecimal(ip.getQuantidade())));
        
        //ItemPedidoController ipc = new ItemPedidoController();
        //ipc.salvar(ip);
    }
}
