drop FUNCTION funct_load_cliente(filter jsonb);

CREATE FUNCTION funct_load_cliente(filter jsonb)
  RETURNS TABLE(
    cliente_id uuid,
    cliente_nome character varying,
    cliente_apelido character varying,
    cliente_datanascimento date,
    cliente_documentonumero character varying,
    cliente_mail character varying,
    cliente_morada character varying,
    cliente_telefone character varying,
    cliente_telemovel character varying,
    cliente_localtrabalho character varying,
    cliente_estado smallint,
    cliente_estadodesc character varying,
    sexo_id smallint, sexo_desc character varying, distrito_id smallint, distrito_nome character varying, tdocumento_id smallint, tdocumento_desc character varying, cliente_montentecompras numeric, cliente_montentedividas numeric, cliente_montantetatal numeric, cliente_montanteamortizado numeric, cliente_montantependente numeric)
LANGUAGE plpgsql
AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query select
      cli.cliente_id,
      cli.cliente_nome,
      cli.cliente_apelido,
      cli.cliente_datanascimento,
      cli.cliente_documentonumero,
      cli.cliente_mail,
      cli.cliente_morada,
      cli.cliente_telefone,
      cli.cliente_telemovel,
      cli.cliente_localtrabalho,
      cli.cliente_estado,
      rule.cliente_estado_desc( cli, _const ),
      se.sexo_id,
      se.sexo_desc,
      dist.distrito_id,
      dist.distrito_nome,
      tdoc.tdocumento_id,
      tdoc.tdocumento_desc,
      sum( cp.compra_custopagar ) filter ( where cp.compra_tcompra_id = _const.tipocompra_compra ) as cliente_montentecompras,
      sum( cp.compra_custopagar ) filter ( where cp.compra_tcompra_id = _const.tipocompra_divida ) as cliente_montentedividas,
      sum( cp.compra_custopagar ) as cliente_montantetatal,
      sum( cp.compra_custoamortizado ) as cliente_montanteamortizado,
      sum( cp.compra_custopagar - cp.compra_custoamortizado ) as cliente_montantependente
    from cliente cli
      left join ggviario.sexo se on cli.cliente_sexo_id = se.sexo_id
      left join ggviario.distrito dist on cli.cliente_distrito_id = dist.distrito_id
      left join ggviario.tipodocumento tdoc on cli.cliente_tdocumento_id = tdoc.tdocumento_id
      left join ggviario.compra cp on cli.cliente_id = cp.compra_cliente_id
    group by cli.cliente_id,
      se.sexo_id,
      dist.distrito_id,
      tdoc.tdocumento_id
    order by
      case
        when cli.cliente_id = lib.to_uuid ( 1 ) then 1
        else 2
      end asc,
      cli.cliente_nome asc,
      cli.cliente_apelido asc
  ;
end;
$$;

