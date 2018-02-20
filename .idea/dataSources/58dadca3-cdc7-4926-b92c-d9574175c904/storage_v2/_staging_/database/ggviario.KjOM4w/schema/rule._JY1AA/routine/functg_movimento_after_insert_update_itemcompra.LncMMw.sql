CREATE or replace FUNCTION rule.functg_movimento_after_insert_update_itemcompra()
  RETURNS trigger
LANGUAGE plpgsql
AS $$
declare
  _new ggviario.movimento;
  _const rule.constant;
  _itemcompra ggviario.compra;

begin
  _const := rule.constant_init();
  _new := new;

  if _new.movimento_compra_id is not null then
    
    _itemcompra := ggviario.get_compra( _new.movimento_compra_id );
    _itemcompra.compra_estado := _const.compra_estado_empagamento;
    
    if _itemcompra.compra_custoamortizado + _new.movimento_montante >= _itemcompra.compra_custopagar then
      _itemcompra.compra_estado := _const.compra_estado_pago;
      _itemcompra.compra_datafim = current_timestamp;
    end if;
    
    if coalesce( _itemcompra.compra_dataultimamovimentacao, _new.movimento_data - interval '1' day ) < _new.movimento_data then
      _itemcompra.compra_dataultimamovimentacao := _new.movimento_data;
    end if;

    
    update ggviario.compra
      set compra_estado = _itemcompra.compra_estado,
          compra_dataultimamovimentacao = _itemcompra.compra_dataultimamovimentacao,
          compra_custoamortizado = compra_custoamortizado + _new.movimento_montante,
          compra_colaborador_atualizacao = _new.movimento_colaborador_id,
          compra_dataatualizacao = current_timestamp,
          compra_datafim = _itemcompra.compra_datafim
      where compra_id = _itemcompra.compra_id;
    
  end if;

  return null;
end;
$$;

