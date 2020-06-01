package com.acai.model.entidade;

public enum StatusPedido {
    PEDIDO_REALIZADO("Pedido_Realizado"), PEDIDO_RECEBIDO("Pedido_Recebido"), CANCELADO("Cancelado"), FINALIZADO("Finalizado");
    
    private final String status;
    
    private StatusPedido(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return this.status;
    }
    
}
