CREATE or replace FUNCTION funct_reg_movimento_amortizacao_itemvenda(arg_colaborador_id uuid, arg_itemcompra_id uuid, arg_conta_id uuid, arg_movimento_montante numeric, arg_movimento_data date, arg_movimento_documento character varying)
  RETURNS lib.result
LANGUAGE plpgsql
AS $$
declare
  /**
   Amortizao de uma divida em um movimento de credito para a empresa
   */
  _movimento ggviario.movimento;
  _itemcompra record;
  _const rule.constant;
begin

  _const := rule.constant_init();

  select * into _itemcompra
    from ggviario.venda ic
      inner join tipocompra tc on ic.itemcompra_tcompra_id = tc.tcompra_id
      inner join compra cp on ic.itemcompra_compra_id = cp.compra_id
    where ic.itemcompra_id = arg_itemcompra_id
  ;

  if _itemcompra.itemcompra_estado not in ( _const.compra_estado_feito, _const.compra_estado_empagamento ) then
    return lib.result_false( format( 'Não pode amortizar uma compra no estado %I!', rule.itemcompra_estado_desc( _itemcompra, _const ) ) );
  end if;

  if _itemcompra.itemcompra_custoamortizado + arg_movimento_montante > _itemcompra.itemcompra_custopagar then
    return lib.result_false( 'O valor da pagar ultrapassa o valor total da compra!' );
  end if;

  arg_movimento_documento := lib.normalize( arg_movimento_documento );

  if arg_movimento_documento is null then
    arg_movimento_documento := '1';
  end if;

  _movimento := rule.movimento_insert(
    arg_colaborador_id,
    arg_conta_id,
    _const.tipomovimento_credito,
    arg_movimento_documento,
    arg_movimento_data,
    arg_movimento_montante,
    format(
      'Amortizacao da %s numero %s',
      _itemcompra.tcompra_desc,
      _itemcompra.compra_faturanumero||'/'||_itemcompra.itemcompra_codigo
    ),
    _itemcompra.itemcompra_id
  );

  _itemcompra := get_itemcompra( arg_itemcompra_id );

  return lib.result_true(
    jsonb_build_object(
      'movimento', _movimento,
       'venda', _itemcompra
    )
  );

end;
$$;

