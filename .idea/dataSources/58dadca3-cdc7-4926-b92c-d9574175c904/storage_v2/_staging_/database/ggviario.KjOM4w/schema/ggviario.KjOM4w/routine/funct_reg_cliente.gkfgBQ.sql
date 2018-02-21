CREATE or replace FUNCTION funct_reg_cliente(arg_colaborador_id uuid, arg_sexo_id smallint, arg_distrito_id smallint, arg_tdocumenti_id smallint, arg_cliente_documentonumero character varying, arg_cliente_nome character varying, arg_cliente_apelido character varying, arg_cliente_datanascimento date, arg_cliente_telemovel character varying, arg_cliente_telefone character varying, arg_cliente_mail character varying, arg_cliente_morada character varying, arg_cliente_localtrabalho character varying)
  RETURNS lib.result
LANGUAGE plpgsql
AS $$
declare
  _cliente ggviario.cliente;
begin

  arg_cliente_mail = lib.normalize( arg_cliente_mail );
  arg_cliente_nome = lib.normalize( arg_cliente_nome );
  arg_cliente_morada = lib.normalize( arg_cliente_morada );
  arg_cliente_apelido = lib.normalize( arg_cliente_apelido );
  arg_cliente_telemovel = lib.normalize( arg_cliente_telefone );
  arg_cliente_telemovel = lib.normalize( arg_cliente_telemovel );
  arg_cliente_documentonumero = lib.normalize( arg_cliente_documentonumero );
  arg_cliente_localtrabalho = lib.normalize( arg_cliente_localtrabalho );

  if (
    select count( * )
      from ggviario.cliente cli
      where cli.cliente_mail = arg_cliente_mail
  ) > 0 then
    return lib.result_false( 'Já existe um cliente com esse email!' );
  end if;


  if arg_cliente_documentonumero is not null and arg_tdocumenti_id is null then
    return lib.result_false( 'Para informar o numero de documento é necessario informar o tipo de documento!' );
  end if;

  if arg_cliente_documentonumero is null and arg_tdocumenti_id is not null then
    return lib.result_false( 'Para informar o tipo de dcumento  é necessario informar o numero de documento!' );
  end if;


  if (
    select count( * )
      from ggviario.cliente cli
      where cli.cliente_tdocumento_id = arg_tdocumenti_id
        and lower( cli.cliente_documentonumero ) = lower( arg_cliente_documentonumero )
  ) > 0 then
    return lib.result_false( 'Já existe um cliente com esse documento!' );
  end if;


  insert into ggviario.cliente(
    cliente_colaborador_id,
    cliente_sexo_id,
    cliente_distrito_id,
    cliente_tdocumento_id,
    cliente_documentonumero,
    cliente_nome,
    cliente_apelido,
    cliente_telemovel,
    cliente_telefone,
    cliente_mail,
    cliente_morada,
    cliente_localtrabalho,
    cliente_datanascimento
  ) values (
    arg_colaborador_id,
    arg_sexo_id,
    arg_distrito_id,
    arg_tdocumenti_id,
    arg_cliente_documentonumero,
    arg_cliente_nome,
    arg_cliente_apelido,
    arg_cliente_telemovel,
    arg_cliente_telefone,
    arg_cliente_mail,
    arg_cliente_morada,
    arg_cliente_localtrabalho,
    arg_cliente_datanascimento
  ) returning * into _cliente ;

  return lib.result_true(
    jsonb_build_object(
      'cliente', _cliente
    )
  );
end;

$$;

