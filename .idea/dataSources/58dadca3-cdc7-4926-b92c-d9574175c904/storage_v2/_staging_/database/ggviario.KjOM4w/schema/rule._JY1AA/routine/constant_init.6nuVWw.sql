CREATE or replace FUNCTION constant_init()
  RETURNS rule.constant
IMMUTABLE
LANGUAGE plpgsql
AS $$
declare
  _const rule.constant;
begin

  --//Estado de equivalencia
  _const.equivalencia_estado_ativo := 1;
  _const.equivalencia_estado_fechado := 0;

  --// Estdao de preco
  _const.preco_estado_ativo := 1;
  _const.preco_estado_fechado := 0;

  --//Estado de setor
  _const.setor_estado_protegido := 2;
  _const.setor_estado_ativo := 1;
  _const.setor_estado_fechado := 0;

  --//Estado de produto
  _const.produto_estado_ativo := 1;
  _const.produto_estado_fechado := 0;

  --//Estados de compra
  _const.compra_estado_pendente := 3;
  _const.compra_estado_feito := 2;
  _const.compra_estado_empagamento := 1;
  _const.compra_estado_pago := 0;
  _const.compra_estado_anulado := -1;

  --//Esatado de despesa
  _const.despesa_estado_pendente := 2;
  _const.despesa_estado_empagamento := 1;
  _const.despesa_estado_pago := 0;

  --//Estado de tipo movimento
  _const.tipomovimento_credito := 1;
  _const.tipomovimento_debito := 2;

  --//Estado de movimentos
  _const.movimento_estado_devolver := 2;
  _const.movimento_estado_emdevolucao := 1;
  _const.movimento_estado_devolvido := 0;
  _const.movimento_estado_anulado := -1;

  --//Tipo de compra
  _const.tipocompra_compra := 1;
  _const.tipocompra_divida := 2;

  --//Esatdo dos cliente
  _const.cliente_estado_ativo := 1;
  _const.cliente_estado_fechado := 1;

  return _const;
end;
$$;

