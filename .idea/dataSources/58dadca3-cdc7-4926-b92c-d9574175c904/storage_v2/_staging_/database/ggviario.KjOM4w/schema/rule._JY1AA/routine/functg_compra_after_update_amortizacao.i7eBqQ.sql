CREATE FUNCTION functg_compra_after_update_amortizacao()
  RETURNS trigger
LANGUAGE plpgsql
AS $$
declare
  _new ggviario.compra;
  _old ggviario.compra;
  _const rule.constant;
  arg_movimento_montante numeric;
begin
  _const := rule.constant_init();
  _new := new;
  _old := old;

  if _old.compra_estado in ( _const.compra_estado_feito, _const.compra_estado_empagamento )
    and _new.compra_estado in ( _const.compra_estado_empagamento, _const.compra_estado_pago )
    and _old.compra_custoamortizado != _new.compra_custoamortizado
  then
    
    _compra := get_compra( _old.itemcompra_compra_id );
    _compra.compra_estado := _const.compra_estado_empagamento;
    arg_movimento_montante := _new.itemcompra_custoamortizado - _old.itemcompra_custoamortizado;

    if _compra.compra_dataultimamovimento < _new.itemcompra_dataultimamovimento then 
      _compra.compra_dataultimamovimento := _new.itemcompra_dataultimamovimento;
    end if;
    
    update ggviario.compra
      set compra_estado = _compra.compra_estado,
          compra_montanteamortizado = compra_montanteamortizado + arg_movimento_montante,
          compra_dataultimamovimento = _compra.compra_dataultimamovimento,
          compra_colaborador_atualizacao = coalesce( _new.itemcompra_colaborador_atualizacao, compra_colaborador_atualizacao ),
          compra_dataatualizacao = coalesce( _new.itemcompra_dataatualizacao, current_timestamp )
      where compra_id = _compra.compra_id
    ;
          
    
  end if;
    
  return null;
end;
$$;

