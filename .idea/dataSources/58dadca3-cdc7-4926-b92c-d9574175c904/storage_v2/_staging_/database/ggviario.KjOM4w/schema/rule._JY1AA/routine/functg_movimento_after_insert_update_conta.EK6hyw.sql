CREATE or replace FUNCTION functg_movimento_after_insert_update_conta()
  RETURNS trigger
LANGUAGE plpgsql
AS $$
declare
  _new ggviario.movimento;
  _const rule.constant;
  _conta ggviario.conta;
  arg_movimento_credito  numeric default 0;
  arg_movimento_debito  numeric default 0;
begin

  _new := new;
  if _new.movimento_conta_id is null then return null; end if;

  _const := rule.constant_init();
  _conta := ggviario.get_conta( _new.movimento_conta_id );

  if _new.movimento_tmovimento_id = _const.tipomovimento_credito then
    arg_movimento_credito := _new.movimento_montante;
  elseif _new.movimento_tmovimento_id = _const.tipomovimento_debito then
    arg_movimento_debito := _new.movimento_montante;
  end if;

  if _conta.conta_dataultimamovimentacao < _new.movimento_data then
    _conta.conta_dataultimamovimentacao := _new.movimento_data;
  end if;

  update ggviario.conta
    set conta_credito = conta_credito + arg_movimento_credito,
        conta_debito = conta_debito + arg_movimento_debito,
        conta_dataultimamovimentacao = _conta.conta_dataultimamovimentacao,
        conta_saldo = conta_saldo + arg_movimento_credito - arg_movimento_debito,
        conta_colaborador_atualizacao = _new.movimento_colaborador_id,
        conta_dataatualizacao = current_timestamp
    where conta_id = _conta.conta_id;
  
  return null;

end;
$$;

