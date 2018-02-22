CREATE or replace FUNCTION funct_change_produto_setoptions(arg_colaborador_id uuid, arg_produto_id uuid, options jsonb)
  RETURNS lib.result
LANGUAGE plpgsql
AS $$
declare
  _produto ggviario.produto;
  arg_produto_servicocliente boolean default coalesce( options->?'produto_servicocliente', true );
  arg_produto_servicofornecedor boolean default coalesce( options->?'produto_servicofornecedor', true );
  arg_produto_servicoproducao boolean default coalesce( options->?'produto_servicoproducao', true );
  arg_produto_servicostockdinamico boolean default coalesce( options->?'produto_servicostockdinamico', true );
  arg_produto_servicostockdinamiconegativo boolean default coalesce( options->?'produto_servicostockdinamiconegativo', false );
begin

  _produto := ggviario.get_produto( arg_produto_id );
  update ggviario.produto
  set produto_colaborador_atualizacao = arg_colaborador_id,
    produto_dataatualizacao = current_timestamp,
    produto_servicocliente = arg_produto_servicocliente,
    produto_servicofornecedor = arg_produto_servicofornecedor,
    produto_servicoproducao = arg_produto_servicoproducao,
    produto_servicostockdinamico = arg_produto_servicostockdinamico,
    produto_servicostockdinamiconegativo = arg_produto_servicostockdinamiconegativo
  where produto_id = arg_produto_id
  returning * into _produto
  ;

  return lib.result_true(
      jsonb_build_object(
          'produto', _produto
      )
  );

end;
$$;

