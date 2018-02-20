CREATE or replace FUNCTION rule. movimento_insert(arg_colaborador_id uuid, arg_conta_id uuid, arg_tmovimento_id smallint, arg_movimento_documento character varying, arg_movimento_data date, arg_movimento_montante numeric, arg_movimento_libele character varying, arg_itemcompra_id uuid DEFAULT NULL::uuid, arg_despesa_id uuid DEFAULT NULL::uuid, arg_movimento_id uuid DEFAULT NULL::uuid, arg_movimento_devolucao boolean DEFAULT false, arg_movimento_transferencianumero integer DEFAULT NULL::integer)
  RETURNS ggviario.movimento
LANGUAGE SQL
AS $$
insert into ggviario.movimento(
      movimento_colaborador_id,
      movimento_conta_id,
      movimento_tmovimento_id,
      movimento_documento,
      movimento_data,
      movimento_montante,
      movimento_libele,
      movimento_compra_id,
      movimento_despeda_id,
      movimento_movimento_id,
      movimento_devolucao,
      movimento_transferencianumero
    ) values (
      arg_colaborador_id,
      arg_conta_id,
      arg_tmovimento_id,
      arg_movimento_documento,
      arg_movimento_data,
      arg_movimento_montante,
      arg_movimento_libele,
      arg_itemcompra_id,
      arg_despesa_id,
      arg_movimento_id,
      arg_movimento_devolucao,
      arg_movimento_transferencianumero
    ) returning *;
$$;

