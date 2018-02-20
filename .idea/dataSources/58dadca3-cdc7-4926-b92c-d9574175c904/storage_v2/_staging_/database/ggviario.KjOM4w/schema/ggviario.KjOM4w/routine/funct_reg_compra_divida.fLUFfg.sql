CREATE or replace FUNCTION funct_reg_compra_divida(arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_colaborador_id uuid, arg_compra_quantidade numeric, arg_compra_custounitario numeric, arg_compra_custobruto numeric, arg_compra_custodesconto numeric, arg_compra_custopagar numeric, arg_compra_data date, arg_compra_datafinalizar date)
  RETURNS lib.result
LANGUAGE plpgsql
AS $$
declare
  _res lib.result;
  _tipocompra ggviario.tipocompra;
  _const rule.constant;
begin
  _const := rule.constant_init();
  _tipocompra := ggviario.get_tipocompra( _const.tipocompra_divida );
  
  _res := rule.compra_insert(
    arg_produto_id,
    arg_unidade_id,
    arg_cliente_id,
    _tipocompra.tcompra_id,
    arg_colaborador_id,
    arg_compra_quantidade,
    arg_compra_custounitario,
    arg_compra_custobruto,
    arg_compra_custodesconto,
    arg_compra_custopagar,
    arg_compra_data,
    arg_compra_datafinalizar
  );
  
  return _res;
end
$$;

