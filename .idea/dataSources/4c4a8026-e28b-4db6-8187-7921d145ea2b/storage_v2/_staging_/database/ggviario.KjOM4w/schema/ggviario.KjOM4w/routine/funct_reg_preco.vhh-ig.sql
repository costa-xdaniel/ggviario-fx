CREATE or replace FUNCTION funct_reg_preco(arg_colaborador_id uuid, arg_produto_id uuid, arg_precos jsonb)
  RETURNS lib.result
LANGUAGE plpgsql
AS $fun$
declare
  KEY_UNIDADE_ID character varying default 'unidade_id';
  KEY_PRECO_VALORUNIDADE character varying default 'preco_custounidade';
  KEY_PRECO_QUANTIDADEPRODUTO character varying default 'preco_quantidadeproduto';
  KEY_PRECO_BASE character varying default 'preco_base';

  /**
    [
      {
        "unidade_id"::UUID,
        "preco_custounidade":PRECO
        "preco_quantidadeproduto":QUANTIDADE
      }
    ]
  */
  arg_next jsonb;
  arg_next_unidade_id uuid;
  arg_next_preco_valorunidade numeric;
  arg_next_preco_quantidadeproduto numeric;
  arg_next_preco_base boolean;

  list_precos uuid [] default '{}'::uuid[];
  _preco ggviario.preco;
  _const rule.constant;
  jPrecos jsonb default '[]';
begin
  _const := rule.constant_init();

  for iterator in 0 .. jsonb_array_length( arg_precos ) -1 loop
    arg_next := arg_precos -> iterator;
    arg_next_unidade_id := arg_next ->> KEY_UNIDADE_ID;
    arg_next_preco_valorunidade := arg_next -># KEY_PRECO_VALORUNIDADE;
    arg_next_preco_quantidadeproduto := arg_next -># KEY_PRECO_QUANTIDADEPRODUTO;
    arg_next_preco_base := arg_next ->? KEY_PRECO_BASE;

    select * into _preco
      from ggviario.preco
      where preco_unidade_id = arg_next_unidade_id
            and preco_produto_id = arg_produto_id
            and preco_estado = _const.preco_estado_ativo
    ;

    if _preco.preco_id is null
       or _preco.preco_custounidade !=== arg_next_preco_valorunidade
       or _preco.preco_quantidadeproduto !=== arg_next_preco_quantidadeproduto
       or _preco.preco_base !=== arg_next_preco_base
    then
      insert into ggviario.preco(
        preco_colaborador_id,
        preco_produto_id,
        preco_unidade_id,
        preco_custounidade,
        preco_quantidadeproduto,
        preco_base
      ) values (
        arg_colaborador_id,
        arg_produto_id,
        arg_next_unidade_id,
        arg_next_preco_valorunidade,
        arg_next_preco_quantidadeproduto,
        arg_next_preco_base
      ) returning * into _preco;
      jPrecos := jPrecos || ( to_jsonb( _preco ) || $${"preco_registro":true}$$::jsonb );
    else
      jPrecos := jPrecos || ( to_jsonb( _preco ) || $${"preco_registro":false}$$::jsonb );
    end if;

    list_precos := list_precos || _preco.preco_id;
  end loop;

  update ggviario.preco
    set preco_estado = _const.preco_estado_fechado,
      preco_colaborador_atualizacao = arg_colaborador_id,
      preco_dataatualizacao = current_timestamp
    where preco_produto_id = arg_produto_id
          and preco_estado = _const.preco_estado_ativo
          and not preco_id = any( list_precos )
    ;

  return lib.result_true(
      jsonb_build_object(
          'precos', jPrecos
      )
  );
end;
$fun$;

