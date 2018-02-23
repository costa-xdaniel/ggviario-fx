CREATE or replace FUNCTION funct_reg_venda_divida(arg_colaborador_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_venda_quantidade numeric, arg_venda_montanteunitario numeric, arg_venda_montantebruto numeric, arg_venda_montantedesconto numeric, arg_venda_montantepagar numeric, arg_venda_data date, arg_venda_datafinalizar date)
  RETURNS lib.result
LANGUAGE plpgsql
AS $$
declare
  _res lib.result;
  _tipovenda ggviario.tipovenda;
  _const rule.constant;
begin
  _const := rule.constant_init();
  _tipovenda := ggviario.get_tipovenda( _const.tipovenda_divida );

  if arg_venda_montanteunitario is null then raise exception 'Para aqui estado nullo'; end if;
  _res := rule.venda_insert(
      arg_colaborador_id,
      arg_produto_id,
      arg_unidade_id,
      arg_cliente_id,
      _tipovenda.tvenda_id,
      arg_venda_quantidade,
      arg_venda_montanteunitario,
      arg_venda_montantebruto,
      arg_venda_montantedesconto,
      arg_venda_montantepagar,
      arg_venda_data,
      arg_venda_datafinalizar
  );

  return _res;
end
$$;

