CREATE or replace FUNCTION rule.functg_venda_intert_update_produto_stock()
  RETURNS trigger
LANGUAGE plpgsql
AS $$
declare
    _new ggviario.venda;
    _const rule.constant;
    _produto ggviario.produto;
  begin
    _const := rule.constant_init();
    _new := new;
    -- marcar os itens da venda como feito!
    update ggviario.produto
      set produto_stock = produto_stock - _new.venda_quantidadeproduto ,
          produto_colaborador_atualizacao = _new.venda_colaborador_id ,
          produto_dataatualizacao = current_timestamp
      where produto_id = _new.venda_produto_id
    ;
    return null;
  end;
$$;

