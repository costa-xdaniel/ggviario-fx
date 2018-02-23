CREATE or replace  FUNCTION rule.venda_insert(arg_colaborador_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_tvenda_id smallint, arg_venda_quantidade numeric, arg_venda_montanteunitario numeric, arg_venda_montantebruto numeric, arg_venda_montantedesconto numeric, arg_venda_montantepagar numeric, arg_venda_data date, arg_venda_datafinalizar date)
  RETURNS lib.result
LANGUAGE plpgsql
AS $$
declare
  arg_faturanumero character varying;
  arg_venda_quantidadeproduto numeric;
  _venda ggviario.venda;
  _const rule.constant;
  _preco ggviario.preco;
  _tipovenda ggviario.tipovenda;
begin
  _const := rule.constant_init();
  _tipovenda := ggviario.get_tipovenda( arg_tvenda_id );

  if arg_venda_montanteunitario is null then raise notice 'Para Aqui arg_venda_montanteunitario esta null';  end if;



  select * into _preco
  from ggviario.preco pre
  where pre.preco_produto_id = arg_produto_id
        and pre.preco_unidade_id = arg_unidade_id
        and pre.preco_estado = _const.preco_estado_ativo
  order by coalesce( pre.preco_dataatualizacao, pre.preco_dataregisto ) desc
  ;

  if _preco.preco_quantidadeproduto is null then
    return lib.result_false( 'Não foi encontrado nenhuma equivalencia do produto para a unidade!'  );
  end if;

  if _preco is null then
    return lib.result_false( 'Não foi encontrado nenhum preço ativo para a unidade do produto!');
  end if;

  arg_faturanumero := rule.venda_fatura_generatenext( _tipovenda );
  arg_venda_quantidadeproduto := arg_venda_quantidade * _preco.preco_quantidadeproduto;

  insert into ggviario.venda(
    venda_produto_id,
    venda_unidade_id,
    venda_cliente_id,
    venda_tvenda_id,
    venda_colaborador_id,
    venda_faturanumero,
    venda_quantidade,
    venda_quantidadeproduto,
    venda_montanteunitario,
    venda_montantebruto,
    venda_montantedesconto,
    venda_montantepagar,
    venda_data,
    venda_datafinalizar,
    venda_estado
  ) values (
    arg_produto_id,
    arg_unidade_id,
    arg_cliente_id,
    arg_tvenda_id,
    arg_colaborador_id,
    arg_faturanumero,
    arg_venda_quantidade,
    arg_venda_quantidadeproduto,
    arg_venda_montanteunitario,
    arg_venda_montantebruto,
    arg_venda_montantedesconto,
    arg_venda_montantepagar,
    arg_venda_data,
    arg_venda_datafinalizar,
    _const.venda_estado_pendente
  ) returning * into _venda;

  return lib.result_true(
      jsonb_build_object(
          'venda', _venda
      )
  );
end;
$$;

