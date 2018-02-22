CREATE or replace FUNCTION funct_load_produto_venda(filter jsonb)
  RETURNS TABLE(produto_id uuid, produto_nome character varying, produto_codigo character varying, produto_stock numeric, produto_precos jsonb, categoria_id uuid, categoria_nome character varying)
LANGUAGE plpgsql
AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();

  return query
    select
        pro.produto_id,
        pro.produto_nome,
        pro.produto_codigo,
        pro.produto_stock,
        lib.agg_jsonb_collect(  to_jsonb( prec )|| to_jsonb( u ) ),
        cat.categoria_id,
        cat.categoria_nome
      from ggviario.produto pro
        inner join ggviario.categoria cat on pro.produto_categoria_id = cat.categoria_id
        inner join preco prec on pro.produto_id = prec.preco_produto_id
          and prec.preco_estado = _const.preco_estado_ativo
          and prec.preco_quantidadeproduto is not null
          and prec.preco_custounidade is not null
        inner join unidade u on prec.preco_unidade_id = u.unidade_id
      group by pro.produto_id,
        cat.categoria_id
    ;
end;
$$;

