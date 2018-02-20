CREATE or replace  FUNCTION functg_compra_intert_update_produto_stock()
  RETURNS trigger
LANGUAGE plpgsql
AS $$
declare
    _new ggviario.compra;
    _old ggviario.compra;
    _const rule.constant;
    _produto ggviario.produto;
  begin
    _const := rule.constant_init();
    _new := new;
    _old := old;

    -- marcar os itens da compra como feito!
    update ggviario.produto
      set produto_stock = produto_stock - _old.compra_quantidadeproduto ,
          produto_colaborador_atualizacao = _new.compra_colaborador_id ,
          produto_dataatualizacao = current_timestamp
      where produto_id = _old.compra_produto_id
    ;

    return null;
  end;
$$;

