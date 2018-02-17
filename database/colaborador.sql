--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = colaborador, pg_catalog;

ALTER TABLE IF EXISTS ONLY colaborador.previlegio DROP CONSTRAINT IF EXISTS fk_previlegio_to_perfim;
ALTER TABLE IF EXISTS ONLY colaborador.previlegio DROP CONSTRAINT IF EXISTS fk_previlegio_to_menu;
ALTER TABLE IF EXISTS ONLY colaborador.previlegio DROP CONSTRAINT IF EXISTS fk_previlegio_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY colaborador.previlegio DROP CONSTRAINT IF EXISTS fk_previlegio_to_colaborador;
ALTER TABLE IF EXISTS ONLY colaborador.perfil DROP CONSTRAINT IF EXISTS fk_perfil_to_perfil;
ALTER TABLE IF EXISTS ONLY colaborador.perfil DROP CONSTRAINT IF EXISTS fk_perfil_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY colaborador.perfil DROP CONSTRAINT IF EXISTS fk_perfil_to_colaborador;
ALTER TABLE IF EXISTS ONLY colaborador.menu DROP CONSTRAINT IF EXISTS fk_menu_to_menu;
ALTER TABLE IF EXISTS ONLY colaborador.colaborador DROP CONSTRAINT IF EXISTS fk_colaborador_to_sexo;
ALTER TABLE IF EXISTS ONLY colaborador.colaborador DROP CONSTRAINT IF EXISTS fk_colaborador_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY colaborador.colaborador DROP CONSTRAINT IF EXISTS fk_colaborador_to_colaborador;
ALTER TABLE IF EXISTS ONLY colaborador.autenticacao DROP CONSTRAINT IF EXISTS fk_autenticacao_to_colaborador;
ALTER TABLE IF EXISTS ONLY colaborador.acesso DROP CONSTRAINT IF EXISTS fk_accesso_to_menu;
ALTER TABLE IF EXISTS ONLY colaborador.acesso DROP CONSTRAINT IF EXISTS fk_accesso_to_colaborador_propetario;
ALTER TABLE IF EXISTS ONLY colaborador.acesso DROP CONSTRAINT IF EXISTS fk_accesso_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY colaborador.acesso DROP CONSTRAINT IF EXISTS fk_accesso_to_colaborador;
DROP INDEX IF EXISTS colaborador.idx_sexo_id;
DROP INDEX IF EXISTS colaborador.idx_previlegio_perfil_id;
DROP INDEX IF EXISTS colaborador.idx_previlegio_menu_id;
DROP INDEX IF EXISTS colaborador.idx_previlegio_colaboirador_id;
DROP INDEX IF EXISTS colaborador.idx_previlegio_colaboirador_atualizacao;
DROP INDEX IF EXISTS colaborador.idx_perfil_perfil_perfil_id;
DROP INDEX IF EXISTS colaborador.idx_perfil_colaborador_id;
DROP INDEX IF EXISTS colaborador.idx_perfil_colaborador_atualizacao;
DROP INDEX IF EXISTS colaborador.idx_colaborador_token;
DROP INDEX IF EXISTS colaborador.idx_colaborador_sistema;
DROP INDEX IF EXISTS colaborador.idx_colaborador_sexo_id;
DROP INDEX IF EXISTS colaborador.idx_colaborador_nome_order;
DROP INDEX IF EXISTS colaborador.idx_colaborador_estado;
DROP INDEX IF EXISTS colaborador.idx_colaborador_colaborador_id;
DROP INDEX IF EXISTS colaborador.idx_colaborador_accesstoken;
DROP INDEX IF EXISTS colaborador.idx_colaborador_accesso;
DROP INDEX IF EXISTS colaborador.idx_autenticacao_colaborador_id;
DROP INDEX IF EXISTS colaborador.idx_agenda_colaborador_id;
DROP INDEX IF EXISTS colaborador.idx_acesso_menu_id;
DROP INDEX IF EXISTS colaborador.idx_acesso_estado;
DROP INDEX IF EXISTS colaborador.idx_acesso_colaborador_propetario;
ALTER TABLE IF EXISTS ONLY colaborador.sexo DROP CONSTRAINT IF EXISTS uq_sexo_desc;
ALTER TABLE IF EXISTS ONLY colaborador.menu DROP CONSTRAINT IF EXISTS uq_menu_codigo;
ALTER TABLE IF EXISTS ONLY colaborador.colaborador DROP CONSTRAINT IF EXISTS uq_colaborador_nif;
ALTER TABLE IF EXISTS ONLY colaborador.colaborador DROP CONSTRAINT IF EXISTS uq_colaborador_mail;
ALTER TABLE IF EXISTS ONLY colaborador.sexo DROP CONSTRAINT IF EXISTS pk_sexo_id;
ALTER TABLE IF EXISTS ONLY colaborador.previlegio DROP CONSTRAINT IF EXISTS pk_previlegio_id;
ALTER TABLE IF EXISTS ONLY colaborador.perfil DROP CONSTRAINT IF EXISTS pk_perfil_id;
ALTER TABLE IF EXISTS ONLY colaborador.menu DROP CONSTRAINT IF EXISTS pk_menu_id;
ALTER TABLE IF EXISTS ONLY colaborador.colaborador DROP CONSTRAINT IF EXISTS pk_colaborador_id;
ALTER TABLE IF EXISTS ONLY colaborador.autenticacao DROP CONSTRAINT IF EXISTS pk_autenticacao_id;
ALTER TABLE IF EXISTS ONLY colaborador.acesso DROP CONSTRAINT IF EXISTS pk_acesso_id;
DROP TABLE IF EXISTS colaborador.sexo;
DROP TABLE IF EXISTS colaborador.previlegio;
DROP TABLE IF EXISTS colaborador.perfil;
DROP TABLE IF EXISTS colaborador.menu;
DROP TABLE IF EXISTS colaborador.autenticacao;
DROP TABLE IF EXISTS colaborador.acesso;
DROP FUNCTION IF EXISTS colaborador.value_max_submenu();
DROP FUNCTION IF EXISTS colaborador.menu_load_structure(filter jsonb);
DROP FUNCTION IF EXISTS colaborador.menu_create_set_up();
DROP FUNCTION IF EXISTS colaborador.menu_create(arg_menu_codigo character varying, arg_menu_nome character varying, arg_menu_menu_codigo character varying, arg_menu_link character varying, arg_menu_icon character varying);
DROP FUNCTION IF EXISTS colaborador.menu_count_of_colaborador(arg_menu_id uuid, arg_colaborador_id uuid, arg_acesso_estado smallint);
DROP FUNCTION IF EXISTS colaborador.get_colaborador(arg_colaborador_id uuid);
DROP FUNCTION IF EXISTS colaborador.funct_reg_colaborador(arg_colaborador_colaborador_id uuid, arg_colaborador_nome character varying, arg_colaborador_apelido character varying, arg_colaborador_datanascimento date, arg_colaborador_nif character varying, arg_colaborador_mail character varying, arg_sexo_id smallint, arg_option jsonb);
DROP FUNCTION IF EXISTS colaborador.funct_reg_acesso(arg_colaborador_id uuid, arg_colaborador_propetario uuid, arg_menus jsonb);
DROP FUNCTION IF EXISTS colaborador.funct_load_menu_by_colaborador(filter jsonb);
DROP FUNCTION IF EXISTS colaborador.funct_load_colaborador_token(arg_colaborador_id uuid);
DROP FUNCTION IF EXISTS colaborador.funct_load_colaborador_by_nif(filter jsonb);
DROP FUNCTION IF EXISTS colaborador.funct_load_colaborador(filter jsonb);
DROP FUNCTION IF EXISTS colaborador.funct_find_colaborador_by_token(arg_colaborador_token_encrypted character varying);
DROP FUNCTION IF EXISTS colaborador.funct_colaborador_authenticate_logout(arg_autenticacao_id uuid);
DROP FUNCTION IF EXISTS colaborador.funct_colaborador_authenticate(arg_colaborador_key character varying, arg_colaborador_senha character varying);
DROP FUNCTION IF EXISTS colaborador.funct_change_colaborador_token_restore(arg_colaborador_mail character varying);
DROP FUNCTION IF EXISTS colaborador.funct_change_colaborador_token_ativate(arg_colaborador_id uuid, arg_colaborador_token character varying, colaborador_newsenha character varying);
DROP FUNCTION IF EXISTS colaborador.funct_change_colaborador_senha(arg_colaborador_id uuid, arg_colaborador_senhacurrent character varying, arg_colaborador_senhanew character varying);
DROP FUNCTION IF EXISTS colaborador.funct_change_colaborador_accesso_reativar(arg_colaborador_id uuid, arg_colaborador_reative uuid);
DROP FUNCTION IF EXISTS colaborador.funct_change_colaborador_accesso_disable(arg_colaborador_id uuid, arg_colaborador_disable uuid);
DROP FUNCTION IF EXISTS colaborador.funct_change_colaborador(arg_colaborador_id uuid, arg_colaborador_editar uuid, arg_colaborador_newdatas jsonb);
DROP FUNCTION IF EXISTS colaborador.encrypt(word text);
DROP FUNCTION IF EXISTS colaborador.colaborador_token_generate();
DROP FUNCTION IF EXISTS colaborador.colaborador_token_encrypt(_colaborador colaborador);
DROP FUNCTION IF EXISTS colaborador.colaborador_key(_colaborador colaborador);
DROP TABLE IF EXISTS colaborador.colaborador;
DROP FUNCTION IF EXISTS colaborador.colaborador_estado_desc(arg_colaborador_estado smallint);
DROP FUNCTION IF EXISTS colaborador.colaborador_accesso_desc(arg_colaborador_acesso smallint);
DROP SCHEMA IF EXISTS colaborador;
--
-- Name: colaborador; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA colaborador;


SET search_path = colaborador, pg_catalog;

--
-- Name: colaborador_accesso_desc(smallint); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION colaborador_accesso_desc(arg_colaborador_acesso smallint) RETURNS character varying
    LANGUAGE sql
    AS $$
    select
      case
        when arg_colaborador_acesso = 1 then 'Ativo'
        when arg_colaborador_acesso = 2 then 'Pre-ativo'
        when arg_colaborador_acesso = 0 then 'Desativo'
      end
  $$;


--
-- Name: colaborador_estado_desc(smallint); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION colaborador_estado_desc(arg_colaborador_estado smallint) RETURNS character varying
    LANGUAGE sql
    AS $$
    select
      case
        when arg_colaborador_estado = 1 then 'Ativo'
        when arg_colaborador_estado = 0 then 'Desativo'
      end
  $$;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: colaborador; Type: TABLE; Schema: colaborador; Owner: -
--

CREATE TABLE colaborador (
    colaborador_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    colaborador_colaborador_id uuid NOT NULL,
    colaborador_colaborador_atualizacao uuid,
    colaborador_sexo_id smallint,
    colaborador_mail character varying(32) NOT NULL,
    colaborador_senha character varying(32) NOT NULL,
    colaborador_nome character varying(45) NOT NULL,
    colaborador_apelido character varying(62),
    colaborador_nif character varying(9) NOT NULL,
    colaborador_datanascimento date,
    colaborador_estado smallint DEFAULT 1 NOT NULL,
    colaborador_accesso smallint DEFAULT 2 NOT NULL,
    colaborador_dataregisto timestamp without time zone DEFAULT now() NOT NULL,
    colaborador_token character varying(16) DEFAULT NULL::character varying,
    colaborador_sistema boolean DEFAULT false NOT NULL,
    colaborador_dataatualizacao timestamp without time zone,
    colaborador_dataultimaatualizacasenha timestamp without time zone DEFAULT now(),
    CONSTRAINT ck_colaborador_apelido_normalized CHECK (((colaborador_apelido)::text = lib.normalize((colaborador_apelido)::text))),
    CONSTRAINT ck_colaborador_estado_is_valid CHECK ((((colaborador_estado = 0) AND (colaborador_accesso = 0)) OR ((colaborador_estado = 1) AND (colaborador_accesso = ANY (ARRAY[0, 1, 2]))))),
    CONSTRAINT ck_colaborador_mail_normalized CHECK (((colaborador_mail)::text = lower(lib.normalize((colaborador_mail)::text)))),
    CONSTRAINT ck_colaborador_nome_normalized CHECK (((colaborador_nome)::text = lib.normalize((colaborador_nome)::text))),
    CONSTRAINT ck_colaborador_unique_super_master CHECK (((colaborador_id <> colaborador_colaborador_id) OR (colaborador_id = lib.to_uuid(1))))
);


--
-- Name: TABLE colaborador; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON TABLE colaborador IS 'Entidade para armazenar os colaboradores';


--
-- Name: COLUMN colaborador.colaborador_id; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_id IS 'Identificacao do colaborador';


--
-- Name: COLUMN colaborador.colaborador_colaborador_id; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_colaborador_id IS 'Identificacao do colaborador master';


--
-- Name: COLUMN colaborador.colaborador_colaborador_atualizacao; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_colaborador_atualizacao IS 'Identificação do ultimo colaborador que modificou o registo';


--
-- Name: COLUMN colaborador.colaborador_sexo_id; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_sexo_id IS 'Coresponde ao sexo do colaborador | sexo={ 1 - MASCULINO, 0 - FIMININO }';


--
-- Name: COLUMN colaborador.colaborador_mail; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_mail IS 'O email do colaborado, usado como chave para autenticação';


--
-- Name: COLUMN colaborador.colaborador_senha; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_senha IS 'A senha do colaborador usado na outenticacao';


--
-- Name: COLUMN colaborador.colaborador_nome; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_nome IS 'Corresponde ao nome do colaborador';


--
-- Name: COLUMN colaborador.colaborador_apelido; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_apelido IS 'Corresponde ao apelido do colaborador';


--
-- Name: COLUMN colaborador.colaborador_nif; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_nif IS 'Corresponde ao NIF do colaborador (NUMERO DE IDENTIFICAÇÃO FISCAL)';


--
-- Name: COLUMN colaborador.colaborador_datanascimento; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_datanascimento IS 'A data em que o colaborador nasceu';


--
-- Name: COLUMN colaborador.colaborador_estado; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_estado IS 'O estado do colaborador | estado={1-Ativo, 0-Demitido}';


--
-- Name: COLUMN colaborador.colaborador_accesso; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_accesso IS 'O accesso do colaborador | acesso={ 2 - Pre Ativo, 1 - Ativo, 0 - Desativo }';


--
-- Name: COLUMN colaborador.colaborador_dataregisto; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_dataregisto IS 'A data em que o colaborador foi cadastrado no sistema';


--
-- Name: COLUMN colaborador.colaborador_token; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_token IS 'O token de ativação';


--
-- Name: COLUMN colaborador.colaborador_sistema; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_sistema IS '1 - O colaborador é o superusuario | 0 - O colaborador nao e o super usuario';


--
-- Name: COLUMN colaborador.colaborador_dataatualizacao; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_dataatualizacao IS 'A data da ultima atualização do registro';


--
-- Name: COLUMN colaborador.colaborador_dataultimaatualizacasenha; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN colaborador.colaborador_dataultimaatualizacasenha IS 'A ultima data em que a senha foi modificada';


--
-- Name: colaborador_key(colaborador); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION colaborador_key(_colaborador colaborador) RETURNS character varying
    LANGUAGE sql
    AS $$
select _colaborador.colaborador_nif
$$;


--
-- Name: colaborador_token_encrypt(colaborador); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION colaborador_token_encrypt(_colaborador colaborador) RETURNS character varying
    LANGUAGE sql
    AS $$
select
  colaborador.encrypt( coalesce( _colaborador.colaborador_token, '' ) )
  || colaborador.encrypt( _colaborador.colaborador_senha )
  || colaborador.encrypt( _colaborador.colaborador_id::text )
  || colaborador.encrypt( _colaborador.colaborador_senha::text )
$$;


--
-- Name: colaborador_token_generate(); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION colaborador_token_generate() RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare 
      /**
        Essa função serve para criar um token novo
       */
      token character varying;
    begin 
      loop 
        token := lib.random_text( 16 );
        if (
          select count( * )
            from colaborador.colaborador co
            where co.colaborador_token = token
        ) = 0 then 
          return token;
        end if;
      end loop;
    end;
$$;


--
-- Name: encrypt(text); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION encrypt(word text) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
declare
  wordMd5 character varying default md5( word );
begin
  return md5(
      md5(
          wordMd5
          ||substring( wordMd5, 1, 20 )
          ||md5(
              md5( $$%#*//-+@$£€{}[]()?!&|\\:;,.^~ºª«»<>çáèíÒú$$ )
              || word
              || substring( wordMd5, 15, 28 )
              || substring( wordMd5, 13, 11 )
              || substring( wordMd5, 17, 20 )
              || substring( wordMd5, 7,  21 )
          )
      )
  );
end;
$_$;


--
-- Name: funct_change_colaborador(uuid, uuid, jsonb); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_change_colaborador(arg_colaborador_id uuid, arg_colaborador_editar uuid, arg_colaborador_newdatas jsonb) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
      _colaborador colaborador.colaborador;
      arg_colaborador_mail character varying default arg_colaborador_newdatas->>'colaborador_mail';
      arg_colaborador_nome character varying default arg_colaborador_newdatas->>'colaborador_nome';
      arg_colaborador_apelido character varying default arg_colaborador_newdatas->>'colaborador_apelido';
      arg_colaborador_sexo int2 default (arg_colaborador_newdatas->>'colaborador_sexo');
      arg_colaborador_datanascimento date default arg_colaborador_newdatas->%'colaborador_datanascimento';
      arg_colaborador_nif character varying default arg_colaborador_newdatas->>>'colaborador_nif';
    begin
      _colaborador := colaborador.get_colaborador( arg_colaborador_editar );
      if arg_colaborador_mail  is not null then
        if (
          select count( * )
            from colaborador.colaborador co
            where co.colaborador_mail = arg_colaborador_mail
              and co.colaborador_id != _colaborador.colaborador_id
        ) > 0 then
          return lib.result_false( 'Esse email já esta em USO!' );
        end if;
        _colaborador.colaborador_mail := arg_colaborador_mail;
      end if;

      if arg_colaborador_nome is not null then
        _colaborador.colaborador_nome := arg_colaborador_nome;
      end if;

      if arg_colaborador_apelido is not null then
        _colaborador.colaborador_apelido := arg_colaborador_apelido;
      end if;

      if arg_colaborador_sexo is not null then
        _colaborador.colaborador_sexo_id := arg_colaborador_sexo;
      end if;

      if arg_colaborador_nif is not null then
        _colaborador.colaborador_nif := arg_colaborador_nif;
      end if;
  
      if arg_colaborador_datanascimento is not null then
        _colaborador.colaborador_datanascimento := arg_colaborador_datanascimento;
      end if;

      update colaborador.colaborador
        set colaborador_mail = _colaborador.colaborador_mail,
            colaborador_nome = _colaborador.colaborador_nome,
            colaborador_apelido = _colaborador.colaborador_apelido,
            colaborador_sexo_id = _colaborador.colaborador_sexo_id,
            colaborador_datanascimento = _colaborador.colaborador_datanascimento,
            colaborador_nif = _colaborador.colaborador_nif,
            colaborador_colaborador_atualizacao = arg_colaborador_id,
            colaborador_dataatualizacao = now()
        where colaborador_id = _colaborador.colaborador_id
      ;

      return lib.result_true();
    end;
$$;


--
-- Name: funct_change_colaborador_accesso_disable(uuid, uuid); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_change_colaborador_accesso_disable(arg_colaborador_id uuid, arg_colaborador_disable uuid) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
    /**
      Essa função serve para disativar o accesso de um dado colaborador no sistema
     */
  begin 
    update colaborador.colaborador
      set colaborador_accesso = 0,
          colaborador_colaborador_atualizacao = arg_colaborador_id,
          colaborador_dataatualizacao = current_timestamp
      where colaborador_id = arg_colaborador_disable;
    
    return lib.result_true();
  end;
$$;


--
-- Name: funct_change_colaborador_accesso_reativar(uuid, uuid); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_change_colaborador_accesso_reativar(arg_colaborador_id uuid, arg_colaborador_reative uuid) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
    /**
      Essa função serve para reativar um colaborador
        * Na reativação do colaborador sera gerado um token de ativação
     */
    _colaborador colaborador.colaborador;
    token character varying;
  begin
    _colaborador := colaborador.get_colaborador( arg_colaborador_reative );

    if _colaborador.colaborador_estado != 1
      or _colaborador.colaborador_accesso != 0
    then
      return lib.result_false( 'Não pode reativar esse colaborador!' );
    end if;
    
    token := colaborador.colaborador_token_generate();
    
    update colaborador.colaborador
      set colaborador_accesso = 2,
          colaborador_senha = colaborador.encrypt( token ),
          colaborador_dataultimaatualizacasenha = now(),
          colaborador_token = token,
          colaborador_colaborador_atualizacao = arg_colaborador_id,
          colaborador_dataatualizacao = current_timestamp
      where colaborador_id = arg_colaborador_reative
      returning * into _colaborador
    ;
    
    token := colaborador.colaborador_token_encrypt( _colaborador );
    
    return lib.result_true(
      jsonb_build_object(
        'colaborador_token', token
      )
    );
  end;
$$;


--
-- Name: funct_change_colaborador_senha(uuid, character varying, character varying); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_change_colaborador_senha(arg_colaborador_id uuid, arg_colaborador_senhacurrent character varying, arg_colaborador_senhanew character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
    /**
        Essa funcao serve para alterar a senha de um colaborador autenticado
     */
  begin
    if(
      select count( * )
        from colaborador.colaborador co
        where co.colaborador_id = arg_colaborador_id
          and co.colaborador_senha = colaborador.encrypt( arg_colaborador_senhacurrent )
    ) = 0 then
      return lib.result_false( 'A senha atual esta invalida!' );
    end if;
    
    update colaborador.colaborador
      set colaborador_senha = colaborador.encrypt( arg_colaborador_senhanew ),
          colaborador_dataultimaatualizacasenha = now(),
          colaborador_dataatualizacao = now(),
          colaborador_colaborador_atualizacao = arg_colaborador_id
      where colaborador_id = arg_colaborador_id
    ;
    
    return lib.result_true( 'success' );
  end;
$$;


--
-- Name: funct_change_colaborador_token_ativate(uuid, character varying, character varying); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_change_colaborador_token_ativate(arg_colaborador_id uuid, arg_colaborador_token character varying, colaborador_newsenha character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
    /**
      Essa funcao serve para ativar um colaborador quando tem token ativo
      Na ativação a senha ser definida, o acesso passara a ser ativo e o token deixara de existir
     */
    _colaborador colaborador.colaborador;
  begin
    _colaborador := colaborador.get_colaborador( arg_colaborador_id := arg_colaborador_id );

    if _colaborador.colaborador_token is null then
      return lib.result_false( 'Não existe token para esse colaborador!' );
    end if;

    if _colaborador.colaborador_estado != 1 or _colaborador.colaborador_accesso not in ( 1, 2 ) then
      return lib.result_false( 'Esse colaborador não pode ser ativadoo!' );
    end if;

    if colaborador.colaborador_token_encrypt( _colaborador ) != arg_colaborador_token then
      return lib.result_false( 'Token de ativação invalidoo!' );
    end if;

    -- Ativar o colaborador
    update colaborador.colaborador
      set colaborador_accesso = 1,
        colaborador_token = null,
        colaborador_senha = colaborador.encrypt( colaborador_newsenha ),
        colaborador_dataultimaatualizacasenha = now(),
        colaborador_dataatualizacao = now(),
        colaborador_colaborador_atualizacao = arg_colaborador_id
      where colaborador_id = arg_colaborador_id
    ;

    return lib.result_true( 'sucesso' );

  end;
$$;


--
-- Name: funct_change_colaborador_token_restore(character varying); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_change_colaborador_token_restore(arg_colaborador_mail character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
    /**
        Essa funcao serve para recria o token de ativacao de conta de um colaborador
     */
    _colaborador colaborador.colaborador;
    _sexo colaborador.sexo;
    token text;
  begin
    arg_colaborador_mail := lower( lib.normalize( arg_colaborador_mail  ) );
    select  * into _colaborador
      from colaborador.colaborador co
      where co.colaborador_mail = arg_colaborador_mail
    ;

    if _colaborador.colaborador_estado != 1 or  _colaborador.colaborador_accesso not in (1 ,2 ) then
      return lib.result_false( 'Não pode restaurar o token para esse colaborador' );
    end if;

    if _colaborador.colaborador_id is null then
      return lib.result_false( 'Esse email não existe na base de dados!' );
    end if;

    <<unique_token>>
    loop
      token := lib.random_text( 16 );
      if (
        select count( * )
          from colaborador.colaborador co
          where co.colaborador_token = token
      ) = 0 then
        exit unique_token;
      end if;
    end loop;

    update colaborador.colaborador
      set colaborador_token = token
      where colaborador_id = _colaborador.colaborador_id
      returning * into _colaborador;

    select * into _sexo
        from colaborador.sexo sx
        where sx.sexo_id = _colaborador.colaborador_sexo_id;

    return lib.result_true(
      jsonb_build_object(
        'colaborador', jsonb_build_object(
          'colaborador_nome', _colaborador.colaborador_nome,
          'colaborador_apelido', _colaborador.colaborador_apelido,
          'colaborador_sexo', _sexo.sexo_id,
          'colaborador_sexodesc', _sexo.sexo_desc,
          'colaborador_mail', _colaborador.colaborador_mail
        ),
        'colaborador_token', colaborador.colaborador_token_encrypt( _colaborador )
      )
    );
  end;
$$;


--
-- Name: funct_colaborador_authenticate(character varying, character varying); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_colaborador_authenticate(arg_colaborador_key character varying, arg_colaborador_senha character varying) RETURNS TABLE(autenticacao_id uuid, colaborador_id uuid, colaborador_mail character varying, colaborador_nome character varying, colaborador_apelido character varying, colaborador_nif character varying, colaborador_datanascimento date, sexo_id smallint, sexo_desc character varying, colaborador_estado smallint, colaborador_estadodesc character varying, colaborador_accesso smallint, colaborador_accessodesc character varying)
    LANGUAGE plpgsql
    AS $$
declare
    /**
      Essa funcao serve para autenticar um colaborador
        no final da autenticacao sera devolvido as informacoes basica do colaboraodr
     */
    _colaborador record;
    _autenticacao colaborador.autenticacao;
  begin
    arg_colaborador_key := lower( lib.normalize( arg_colaborador_key ) );
    select * into _colaborador
      from colaborador.colaborador co
        left join colaborador.sexo sex on co.colaborador_sexo_id = sex.sexo_id
      where colaborador.colaborador_key( co ) = arg_colaborador_key
        and co.colaborador_senha= colaborador.encrypt( arg_colaborador_senha )
        and co.colaborador_estado = 1
        and co.colaborador_accesso in ( 1, 2 )
        and co.colaborador_sistema = b'0'
      ;

    -- Quando as credenciais nao se conicedem
    if _colaborador.colaborador_id is null then
      return;
    end if;


    insert into colaborador.autenticacao(
      autenticacao_colaborador_id
    ) values (
      _colaborador.colaborador_id
    ) returning * into _autenticacao;

    return query
      select
        _autenticacao.autenticacao_id,
        _colaborador.colaborador_id,
        _colaborador.colaborador_mail,
        _colaborador.colaborador_nome,
        _colaborador.colaborador_apelido,
        _colaborador.colaborador_nif,
        _colaborador.colaborador_datanascimento,
        _colaborador.sexo_id,
        _colaborador.sexo_desc,
        _colaborador.colaborador_estado,
        colaborador.colaborador_estado_desc( _colaborador.colaborador_estado ),
        _colaborador.colaborador_accesso,
        colaborador.colaborador_accesso_desc( _colaborador.colaborador_accesso )
    ;
  end;
$$;


--
-- Name: funct_colaborador_authenticate_logout(uuid); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_colaborador_authenticate_logout(arg_autenticacao_id uuid) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
      _autenticacao colaborador.autenticacao;
    begin
      select * into _autenticacao
        from colaborador.autenticacao auth
        where  auth.autenticacao_id = arg_autenticacao_id
      ;
      
      if _autenticacao.autenticacao_estado != 1 then
        return lib.result_false( 'Essa seção não esta aberta!' );
      end if;
      
      update colaborador.autenticacao
        set autenticacao_estado = 0,
            autenticacao_dataatualizacao = now()
        where autenticacao_id = arg_autenticacao_id;
      
      return lib.result_true( 'sucesso' );
      
    end;
$$;


--
-- Name: funct_find_colaborador_by_token(character varying); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_find_colaborador_by_token(arg_colaborador_token_encrypted character varying) RETURNS TABLE(colaborador_id uuid, colaborador_nome character varying, colaborador_apelido character varying, colaborador_mail character varying, colaborador_nif character varying, sexo_id smallint, sexo_desc character varying)
    LANGUAGE sql
    AS $$
select
      co.colaborador_id,
      co.colaborador_nome,
      co.colaborador_apelido,
      co.colaborador_mail,
      co.colaborador_nif,
      sx.sexo_id,
      sx.sexo_desc
    from colaborador.colaborador co
      inner join colaborador.sexo sx on co.colaborador_sexo_id = sx.sexo_id
      where colaborador.colaborador_token_encrypt( co ) = arg_colaborador_token_encrypted
$$;


--
-- Name: funct_load_colaborador(jsonb); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_load_colaborador(filter jsonb) RETURNS TABLE(colaborador_id uuid, colaborador_nome character varying, colaborador_apelido character varying, colaborador_mail character varying, colaborador_estado smallint, colaborador_estadodesc character varying, colaborador_acesso smallint, colaborador_acessodesc character varying, sexo_id smallint, sexo_desc character varying, colaborador_datanascimento date, colaborador_nif character varying)
    LANGUAGE sql
    AS $$
select co.colaborador_id,
    co.colaborador_nome,
    co.colaborador_apelido,
    co.colaborador_mail,
    co.colaborador_estado,
    colaborador.colaborador_estado_desc( co.colaborador_estado ),
    co.colaborador_accesso,
    colaborador.colaborador_accesso_desc( co.colaborador_accesso ),
    s2.sexo_id,
    s2.sexo_desc,
    colaborador_datanascimento,
    colaborador_nif
  from colaborador.colaborador co
    inner join colaborador.sexo s2 ON co.colaborador_sexo_id = s2.sexo_id
  where co.colaborador_sistema = b'0'
  order by co.colaborador_accesso desc,
    co.colaborador_nome,
    co.colaborador_apelido
  ;
$$;


--
-- Name: funct_load_colaborador_by_nif(jsonb); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_load_colaborador_by_nif(filter jsonb) RETURNS TABLE(colaborador_id uuid, colaborador_mail character varying, colaborador_nome character varying, colaborador_apelido character varying, colaborador_estado smallint, colaborador_accesso smallint)
    LANGUAGE sql
    AS $$
select co.colaborador_id,
      co.colaborador_mail,
      co.colaborador_nome,
      co.colaborador_apelido,
      co.colaborador_estado,
      co.colaborador_accesso
    from colaborador.colaborador co
    where co.colaborador_nif = filter->>'colaborador_nif';
$$;


--
-- Name: funct_load_colaborador_token(uuid); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_load_colaborador_token(arg_colaborador_id uuid) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
    _colaborador colaborador.colaborador;
  begin
    _colaborador := colaborador.get_colaborador( arg_colaborador_id );
    
    if _colaborador.colaborador_accesso != 2 then
      return lib.result_false( 'Não pode gerar token para esse colaborador!' );
    end if;
    
    return lib.result_true(
        jsonb_build_object(
            'colaborador_token', colaborador.colaborador_token_encrypt( _colaborador )
        )
    );
  end;
$$;


--
-- Name: funct_load_menu_by_colaborador(jsonb); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_load_menu_by_colaborador(filter jsonb) RETURNS TABLE(menu_id uuid, menu_codigo character varying, menu_nome character varying, menu_raiz character varying, menu_nivel smallint, menu_link character varying, menu_icon character varying, menu_estado smallint, menu_children smallint, menu_directchildern smallint, menu_maxnode smallint, menu_position smallint, menu_menu_id uuid, menu_menu_codigo character varying, menu_menu_nome character varying, menu_menu_raiz character varying, menu_menu_nivel smallint, menu_menu_link character varying, menu_menu_icon character varying)
    LANGUAGE plpgsql
    AS $$
declare
    arg_colaborador_propetario int2 default filter->>'colaborador_id';
  begin
    return query
      select mn.*
        from colaborador.menu_load_structure(null) mn
          inner join colaborador.acesso ac on mn.menu_id = ac.acesso_menu_id
            and ac.acesso_colaborador_propetario = arg_colaborador_propetario
            and ac.acesso_estado = 1
        order by
          mn.menu_position asc
    ;
  end;
$$;


--
-- Name: funct_reg_acesso(uuid, uuid, jsonb); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_reg_acesso(arg_colaborador_id uuid, arg_colaborador_propetario uuid, arg_menus jsonb) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  /**
    Essa funcao serve para dar acesse de um menu ao um colaborador
    -- Para dar acess o colaborador não pode ter previamente o acesso ao menu
   */
  menuList jsonb;
  iMenu jsonb;
  arg_menu_id uuid;
  _acesso colaborador.acesso;
  acesso_list jsonb default jsonb '[]';
  menu_grants uuid [ ] default '{}'::uuid[] ;
begin

  menuList := arg_menus -> 'menu_list';
  for iterator in 0 .. jsonb_array_length( menuList ) - 1 loop
    iMenu := menuList->iterator;
    arg_menu_id := iMenu->>'menu_id';

    -- Veriifcar se o colaborador já possui acesso ao menu
    select * into _acesso
    from colaborador.acesso ac
    where ac.acesso_colaborador_propetario = arg_colaborador_propetario
          and ac.acesso_menu_id = arg_menu_id
          and ac.acesso_estado = 1
  ;

    -- Casoo do colaborador não ter acesso ao menu em causa
    if _acesso.acesso_id is null then
      insert into colaborador.acesso(
        acesso_menu_id,
        acesso_colaborador_propetario,
        acesso_colaborador_id
      ) values(
        arg_menu_id,
        arg_colaborador_propetario,
        arg_colaborador_id
      ) returning * into _acesso;
    end if;
    menu_grants := menu_grants || arg_menu_id;
    acesso_list := acesso_list || to_jsonb( _acesso );
  end loop;

  -- Revogar os menus que o colaborador tinha acesso anteriormente mas atualmente já nao os têm
  -- São menus que aperecem no acesso ativo mas nao aperencem na nova lista de menus a ser dado ao colaborador
  update colaborador.acesso
    set acesso_estado = 0,
      acesso_dataatualizacao = current_timestamp,
      acesso_colaborador_atualizacao = arg_colaborador_id
    where acesso_estado = 1
          and acesso_colaborador_propetario = arg_colaborador_propetario
          and ( not acesso_menu_id = any( menu_grants ));

  return lib.result_true(
      jsonb_build_object(
          'acesso_list', acesso_list
      )
  );

end;
$$;


--
-- Name: funct_reg_colaborador(uuid, character varying, character varying, date, character varying, character varying, smallint, jsonb); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION funct_reg_colaborador(arg_colaborador_colaborador_id uuid, arg_colaborador_nome character varying, arg_colaborador_apelido character varying, arg_colaborador_datanascimento date, arg_colaborador_nif character varying, arg_colaborador_mail character varying, arg_sexo_id smallint, arg_option jsonb DEFAULT NULL::jsonb) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
      /**
        Essa função serve para cadastra novos colaboradores
        Ao cadastar o colaborador a senha é atribuida automaticamente em um random
        E gerado tambem um token que devera ser enviado pela aplicao ao email do colaborador cadastrado
        O email e o NIF tem que ser unico
       */
      _colaborador colaborador.colaborador;
      token text;
    begin

      arg_colaborador_mail := lower( lib.normalize( arg_colaborador_mail ) );
      arg_colaborador_nif := lower( lib.normalize( arg_colaborador_nif ) );
      arg_colaborador_nome := lib.normalize( arg_colaborador_nome );
      arg_colaborador_apelido := lib.normalize( arg_colaborador_apelido );

      -- As informacoes do tipo texto tem que estar normalizados
      if arg_colaborador_mail is null then
        return lib.result_false( 'Email invalido' );
      end if;

      /*
      if arg_colaborador_nif is null then
        return lib.result_false( 'NIF invalido' );
      end if;
      */

      if arg_colaborador_nome is null then
        return lib.result_false( 'Nome invalido' );
      end if;

      /*
      if arg_colaborador_apelido is null then
        return lib.result_false( 'Apelido invalido' );
      end if;
      */

      -- Garantir que nao exista o  NIF
      if (
        select count( * )
          from colaborador.colaborador co
          where co.colaborador_mail =  arg_colaborador_mail
      ) > 0 then
        return lib.result_false( 'Email já existe' );
      end if;

      -- Garantir que o nif seja unico
      if (
        select count( * )
          from colaborador.colaborador co
          where co.colaborador_nif = arg_colaborador_nif
      ) > 0 then
        return lib.result_false( 'Já existe um colaborador com esse NIF' );
      end if;


      -- Criar um novo token unico
      <<unique_token>>
      loop
        token := lib.random_text( 16 );
        if (
          select count( * )
            from colaborador.colaborador co
            where co.colaborador_token = token
        ) = 0 then
          exit unique_token;
        end if;
      end loop;

      insert into colaborador.colaborador(
        colaborador_colaborador_id,
        colaborador_mail,
        colaborador_senha,
        colaborador_nome,
        colaborador_apelido,
        colaborador_nif,
        colaborador_datanascimento,
        colaborador_sexo_id,
        colaborador_token
      ) values (
        arg_colaborador_colaborador_id,
        arg_colaborador_mail,
        colaborador.encrypt( token ),
        arg_colaborador_nome,
        arg_colaborador_apelido,
        arg_colaborador_nif,
        arg_colaborador_datanascimento,
        arg_sexo_id,
        token
      ) returning * into _colaborador;

      return lib.result_true(
        jsonb_build_object(
          'colaborador',  to_jsonb( _colaborador ) - 'colaborador_senha'- 'colaborador_token',
          'colaborador_token', colaborador.colaborador_token_encrypt( _colaborador )
        )
      );
    end;
$$;


--
-- Name: get_colaborador(uuid); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION get_colaborador(arg_colaborador_id uuid) RETURNS colaborador
    LANGUAGE sql
    AS $$
select *
from colaborador.colaborador co
where co.colaborador_id = arg_colaborador_id
;
$$;


--
-- Name: menu_count_of_colaborador(uuid, uuid, smallint); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION menu_count_of_colaborador(arg_menu_id uuid, arg_colaborador_id uuid, arg_acesso_estado smallint) RETURNS bigint
    LANGUAGE plpgsql
    AS $$
begin 
      return (
          select 
            count( * )
              from colaborador.acesso a
              where a.acesso_estado = arg_acesso_estado
                and a.acesso_colaborador_propetario = arg_colaborador_id
                and a.acesso_menu_id = arg_menu_id
        );
    end;
$$;


--
-- Name: menu_create(character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION menu_create(arg_menu_codigo character varying, arg_menu_nome character varying, arg_menu_menu_codigo character varying DEFAULT NULL::character varying, arg_menu_link character varying DEFAULT NULL::character varying, arg_menu_icon character varying DEFAULT NULL::character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
DECLARE
  res lib.result;
  superMenu colaborador.menu;
  arg_menu_nivel int2 default 0;
  arg_menu_raiz character varying;
  totalRaiz int2;
  numCharLengthRaiz character varying(2) default '00';
  newMenu colaborador.menu;
BEGIN
  res.result := FALSE ;
  -- O codigo do menu deve ser unico
  if (
       select count(*)
       from  colaborador.menu m
       where lower(m.menu_codigo) = lower( arg_menu_codigo )
     ) > 0 then
    return lib.result_false( 'O codigo de menu já existe!' );
  end if;

  -- O codigo do menu super tem de existir
  if arg_menu_menu_codigo is not null and (
                                       select count(*)
                                       from  colaborador.menu m
                                       where lower(m.menu_codigo) =  lower( arg_menu_menu_codigo )
                                     ) = 0 then
    return lib.result_false( 'Não existe menu parente com o codigo' );
  end if;

  if arg_menu_menu_codigo is not null then
    select * into superMenu
    from  colaborador.menu m
    where upper( m.menu_codigo ) = upper( arg_menu_menu_codigo );

    select count (*) into totalRaiz
    from  colaborador.menu me
    where me.menu_menu_id = superMenu.menu_id;
    arg_menu_raiz := superMenu.menu_raiz||'.'||trim(to_char(totalRaiz, numCharLengthRaiz));
    arg_menu_nivel := superMenu.menu_nivel +1;
  else
    select count (*) into totalRaiz
    from colaborador.menu me
    where me.menu_menu_id is null;

    -- Quando exeder a quantidade de sub menu maximo em um menu entao abortar a operacao emitindo uma message de eroo
    if totalRaiz > colaborador.value_max_submenu() then
      return lib.result_false( 'O numero maximo de menu excedido!' );
    END IF;
    arg_menu_raiz := trim(to_char(totalRaiz, numCharLengthRaiz));
  end if;

  insert into colaborador.menu(
    menu_menu_id,
    menu_nome,
    menu_link,
    menu_codigo,
    menu_nivel,
    menu_raiz,
    menu_icon
  ) values (
    superMenu.menu_id,
    arg_menu_nome,
    arg_menu_link,
    arg_menu_codigo,
    arg_menu_nivel,
    arg_menu_raiz,
    arg_menu_icon
  ) returning * into newMenu;

  perform  colaborador.menu_create_set_up();

  return lib.result_false(
      jsonb_build_object(
          'data', to_jsonb( newMenu ),
          'text', 'success'
      )
  );

END;
$$;


--
-- Name: menu_create_set_up(); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION menu_create_set_up() RETURNS void
    LANGUAGE plpgsql
    AS $$
declare
  _menu record;
  icount int default 0;
begin
  for _menu in (
    select *
    from colaborador.menu_load_structure( )
  ) loop
    update colaborador.menu cur
    set menu_children = (
      select count( * )
      from colaborador.menu mm
      where mm.menu_raiz like cur.menu_raiz||'.%'
    ),
      menu_directchildern = (
        select count( * )
        from colaborador.menu mm
        where mm.menu_menu_id = cur.menu_id
      ),
      menu_maxnode = (
        select length( replace( substr( mm.menu_raiz, length( cur.menu_raiz )+1, length( mm.menu_raiz ) ), '.', '') ) /2
        from colaborador.menu mm
        where mm.menu_raiz like cur.menu_raiz||'%'
        order by mm.menu_nivel desc
        limit 1
      ),
      menu_position = icount
    where cur.menu_id = _menu.menu_id;

    icount := icount  + 1 ;
  end loop;
end;
$$;


--
-- Name: menu_load_structure(jsonb); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION menu_load_structure(filter jsonb DEFAULT NULL::jsonb) RETURNS TABLE(menu_id uuid, menu_codigo character varying, menu_nome character varying, menu_raiz character varying, menu_nivel smallint, menu_link character varying, menu_icon character varying, menu_estado smallint, menu_children smallint, menu_directchildern smallint, menu_maxnode smallint, menu_position smallint, menu_menu_id uuid, menu_menu_codigo character varying, menu_menu_nome character varying, menu_menu_raiz character varying, menu_menu_nivel smallint, menu_menu_link character varying, menu_menu_icon character varying)
    LANGUAGE plpgsql
    AS $$
declare
  _menu record;
  menu_super_id uuid default filter->>'menu_id';
begin
  for _menu in (
    select
      me.menu_id,
      me.menu_codigo,
      me.menu_nome,
      me.menu_raiz,
      me.menu_nivel,
      me.menu_link,
      me.menu_icon,
      me.menu_estado,
      me.menu_children,
      me.menu_directchildern,
      me.menu_maxnode,
      me.menu_position,
      super.menu_id as menu_menu_id,
      super.menu_codigo as menu_menu_codigo,
      super.menu_nome as menu_menu_nome,
      super.menu_raiz as menu_menu_raiz,
      super.menu_nivel as menu_menu_nivel,
      super.menu_link as menu_menu_link,
      super.menu_icon as menu_menu_icon
    from colaborador.menu me
      left join colaborador.menu super on  me.menu_menu_id = super.menu_id
    where me.menu_menu_id = menu_super_id or (
      menu_super_id is null and me.menu_menu_id is null
    )
    order by
      me.menu_position,
      me.menu_nome
  ) loop -- proximo concorente



    -- Quando um menu tem filhos emtao motra todos os filhos desse menu antes de passa para o proximo meno
    return query
      select
        _menu.menu_id,
        _menu.menu_codigo,
        _menu.menu_nome,
        _menu.menu_raiz,
        _menu.menu_nivel,
        _menu.menu_link,
        _menu.menu_icon,
        _menu.menu_estado,
        _menu.menu_children,
        _menu.menu_directchildern,
        _menu.menu_maxnode,
        _menu.menu_position,
        _menu.menu_menu_id,
        _menu.menu_menu_codigo,
        _menu.menu_menu_nome,
        _menu.menu_menu_raiz,
        _menu.menu_menu_nivel,
        _menu.menu_menu_link,
        _menu.menu_menu_icon
    ;

    if _menu.menu_children > 0 then
      return query select *
                   from colaborador.menu_load_structure(
                       jsonb_build_object(
                           'menu_id', _menu.menu_id
                       )
                   );
    end if;

  end loop;
end;
$$;


--
-- Name: value_max_submenu(); Type: FUNCTION; Schema: colaborador; Owner: -
--

CREATE FUNCTION value_max_submenu() RETURNS integer
    LANGUAGE sql
    AS $$
select 99;
$$;


--
-- Name: acesso; Type: TABLE; Schema: colaborador; Owner: -
--

CREATE TABLE acesso (
    acesso_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    acesso_menu_id uuid NOT NULL,
    acesso_colaborador_propetario uuid NOT NULL,
    acesso_colaborador_id uuid NOT NULL,
    acesso_colaborador_atualizacao uuid,
    accesso_type boolean DEFAULT true NOT NULL,
    acesso_estado smallint DEFAULT 1 NOT NULL,
    acesso_dataregisto timestamp without time zone DEFAULT now() NOT NULL,
    acesso_dataatualizacao timestamp without time zone
);


--
-- Name: TABLE acesso; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON TABLE acesso IS 'Essa entidade serve para armazenar os acessos dos colaboradores a um dado menu';


--
-- Name: COLUMN acesso.acesso_id; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN acesso.acesso_id IS 'Identificador do acesso do colaborador';


--
-- Name: COLUMN acesso.acesso_menu_id; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN acesso.acesso_menu_id IS 'Identificacão do menu a qual o acesso remete';


--
-- Name: COLUMN acesso.acesso_colaborador_propetario; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN acesso.acesso_colaborador_propetario IS 'Indenticação do colaborador a quel é o propetario do refererido acesso';


--
-- Name: COLUMN acesso.acesso_colaborador_id; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN acesso.acesso_colaborador_id IS 'Identificação do colaborador a qual cadastrou o acesso';


--
-- Name: COLUMN acesso.acesso_estado; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN acesso.acesso_estado IS 'O atual estado do acesso | estado = { 1 - Acesso pemitido, 0 - Acesso revigado }';


--
-- Name: COLUMN acesso.acesso_dataregisto; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN acesso.acesso_dataregisto IS 'A data em que o acesso ao menu foi ao colaborador propetario dado';


--
-- Name: COLUMN acesso.acesso_dataatualizacao; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN acesso.acesso_dataatualizacao IS 'A data em que o menu foi revogado do colaborador!';


--
-- Name: autenticacao; Type: TABLE; Schema: colaborador; Owner: -
--

CREATE TABLE autenticacao (
    autenticacao_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    autenticacao_colaborador_id uuid NOT NULL,
    autenticacao_selects integer DEFAULT 0 NOT NULL,
    autenticacao_inserts integer DEFAULT 0 NOT NULL,
    autenticacao_updates integer DEFAULT 0 NOT NULL,
    autenticacao_deletes integer DEFAULT 0 NOT NULL,
    autenticacao_estado smallint DEFAULT 1,
    autenticacao_dataregisto timestamp without time zone DEFAULT now(),
    autenticacao_dataatualizacao timestamp without time zone
);


--
-- Name: TABLE autenticacao; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON TABLE autenticacao IS 'Essa entidade serve para armazenar as autenticacoes que um colaborador vai passadno';


--
-- Name: COLUMN autenticacao.autenticacao_id; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN autenticacao.autenticacao_id IS 'Identificador da autenticacao';


--
-- Name: COLUMN autenticacao.autenticacao_colaborador_id; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN autenticacao.autenticacao_colaborador_id IS 'Identificacao do colaborador que autenticou';


--
-- Name: COLUMN autenticacao.autenticacao_estado; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN autenticacao.autenticacao_estado IS 'O estado de autenticação | estado { 1 - Ativo | 0 - Desativo | 3 - Tentativa de login col um utilizador existente}';


--
-- Name: COLUMN autenticacao.autenticacao_dataregisto; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN autenticacao.autenticacao_dataregisto IS 'A data em que o colaborador entrou no sistema';


--
-- Name: COLUMN autenticacao.autenticacao_dataatualizacao; Type: COMMENT; Schema: colaborador; Owner: -
--

COMMENT ON COLUMN autenticacao.autenticacao_dataatualizacao IS 'A data em que o colaborador saiu do sistema';


--
-- Name: menu; Type: TABLE; Schema: colaborador; Owner: -
--

CREATE TABLE menu (
    menu_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    menu_menu_id uuid,
    menu_codigo character varying(128) NOT NULL,
    menu_raiz character varying(64) NOT NULL,
    menu_nivel smallint NOT NULL,
    menu_icon character varying(56) DEFAULT NULL::character varying,
    menu_nome character varying(48) NOT NULL,
    menu_link character varying(68) DEFAULT NULL::character varying,
    menu_estado smallint DEFAULT 1 NOT NULL,
    menu_children smallint DEFAULT 0 NOT NULL,
    menu_maxnode smallint DEFAULT 0 NOT NULL,
    menu_directchildern smallint DEFAULT 0 NOT NULL,
    menu_position smallint DEFAULT 0 NOT NULL
);


--
-- Name: perfil; Type: TABLE; Schema: colaborador; Owner: -
--

CREATE TABLE perfil (
    perfil_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    perfil_perfil_id uuid,
    perfil_colaborador_id uuid NOT NULL,
    perfil_colaborador_atualizacao uuid,
    perfil_nome character varying(32) NOT NULL,
    perfil_codigo character varying(16) DEFAULT NULL::character varying,
    perfil_estado smallint DEFAULT 1 NOT NULL,
    perfil_dataregisto timestamp without time zone DEFAULT now() NOT NULL,
    perfil_dataatualizacao timestamp without time zone,
    CONSTRAINT ck_perfil_codigo CHECK ((lib.is_normalized((perfil_codigo)::text) AND ((perfil_codigo)::text = lib.normalize((perfil_codigo)::text)))),
    CONSTRAINT ck_perfil_nome_normalized CHECK (lib.is_normalized((perfil_nome)::text))
);


--
-- Name: previlegio; Type: TABLE; Schema: colaborador; Owner: -
--

CREATE TABLE previlegio (
    previlegio_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    previlegio_perfil_id uuid NOT NULL,
    previlegio_menu_id uuid NOT NULL,
    previlegio_colaborador_id uuid NOT NULL,
    previlegio_colaborador_atualizacao uuid,
    previlegio_tipo boolean DEFAULT true,
    previlegio_estado smallint DEFAULT 1 NOT NULL,
    previlegio_dataregisto timestamp without time zone DEFAULT now() NOT NULL,
    previlegio_dataatualuzacao timestamp without time zone
);


--
-- Name: sexo; Type: TABLE; Schema: colaborador; Owner: -
--

CREATE TABLE sexo (
    sexo_id smallint NOT NULL,
    sexo_desc character varying(10) NOT NULL,
    CONSTRAINT ck_sexo_desc_is_nomalized CHECK (lib.is_normalized((sexo_desc)::text))
);


--
-- Data for Name: acesso; Type: TABLE DATA; Schema: colaborador; Owner: -
--

COPY acesso (acesso_id, acesso_menu_id, acesso_colaborador_propetario, acesso_colaborador_id, acesso_colaborador_atualizacao, accesso_type, acesso_estado, acesso_dataregisto, acesso_dataatualizacao) FROM stdin;
\.


--
-- Data for Name: autenticacao; Type: TABLE DATA; Schema: colaborador; Owner: -
--

COPY autenticacao (autenticacao_id, autenticacao_colaborador_id, autenticacao_selects, autenticacao_inserts, autenticacao_updates, autenticacao_deletes, autenticacao_estado, autenticacao_dataregisto, autenticacao_dataatualizacao) FROM stdin;
\.


--
-- Data for Name: colaborador; Type: TABLE DATA; Schema: colaborador; Owner: -
--

COPY colaborador (colaborador_id, colaborador_colaborador_id, colaborador_colaborador_atualizacao, colaborador_sexo_id, colaborador_mail, colaborador_senha, colaborador_nome, colaborador_apelido, colaborador_nif, colaborador_datanascimento, colaborador_estado, colaborador_accesso, colaborador_dataregisto, colaborador_token, colaborador_sistema, colaborador_dataatualizacao, colaborador_dataultimaatualizacasenha) FROM stdin;
00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	\N	data.system@jigastp.com	fa1fa6c024302268077f8063f7146540	Data	System data	#########	\N	1	2	2018-01-30 13:35:10.43203	\N	t	\N	2018-01-30 13:35:10.43203
\.


--
-- Data for Name: menu; Type: TABLE DATA; Schema: colaborador; Owner: -
--

COPY menu (menu_id, menu_menu_id, menu_codigo, menu_raiz, menu_nivel, menu_icon, menu_nome, menu_link, menu_estado, menu_children, menu_maxnode, menu_directchildern, menu_position) FROM stdin;
\.


--
-- Data for Name: perfil; Type: TABLE DATA; Schema: colaborador; Owner: -
--

COPY perfil (perfil_id, perfil_perfil_id, perfil_colaborador_id, perfil_colaborador_atualizacao, perfil_nome, perfil_codigo, perfil_estado, perfil_dataregisto, perfil_dataatualizacao) FROM stdin;
\.


--
-- Data for Name: previlegio; Type: TABLE DATA; Schema: colaborador; Owner: -
--

COPY previlegio (previlegio_id, previlegio_perfil_id, previlegio_menu_id, previlegio_colaborador_id, previlegio_colaborador_atualizacao, previlegio_tipo, previlegio_estado, previlegio_dataregisto, previlegio_dataatualuzacao) FROM stdin;
\.


--
-- Data for Name: sexo; Type: TABLE DATA; Schema: colaborador; Owner: -
--

COPY sexo (sexo_id, sexo_desc) FROM stdin;
1	Masculino
2	Feminino
\.


--
-- Name: acesso pk_acesso_id; Type: CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY acesso
    ADD CONSTRAINT pk_acesso_id PRIMARY KEY (acesso_id);


--
-- Name: autenticacao pk_autenticacao_id; Type: CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY autenticacao
    ADD CONSTRAINT pk_autenticacao_id PRIMARY KEY (autenticacao_id);


--
-- Name: colaborador pk_colaborador_id; Type: CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY colaborador
    ADD CONSTRAINT pk_colaborador_id PRIMARY KEY (colaborador_id);


--
-- Name: menu pk_menu_id; Type: CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY menu
    ADD CONSTRAINT pk_menu_id PRIMARY KEY (menu_id);


--
-- Name: perfil pk_perfil_id; Type: CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY perfil
    ADD CONSTRAINT pk_perfil_id PRIMARY KEY (perfil_id);


--
-- Name: previlegio pk_previlegio_id; Type: CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY previlegio
    ADD CONSTRAINT pk_previlegio_id PRIMARY KEY (previlegio_id);


--
-- Name: sexo pk_sexo_id; Type: CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY sexo
    ADD CONSTRAINT pk_sexo_id PRIMARY KEY (sexo_id);


--
-- Name: colaborador uq_colaborador_mail; Type: CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY colaborador
    ADD CONSTRAINT uq_colaborador_mail UNIQUE (colaborador_mail);


--
-- Name: colaborador uq_colaborador_nif; Type: CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY colaborador
    ADD CONSTRAINT uq_colaborador_nif UNIQUE (colaborador_nif);


--
-- Name: menu uq_menu_codigo; Type: CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY menu
    ADD CONSTRAINT uq_menu_codigo UNIQUE (menu_codigo);


--
-- Name: sexo uq_sexo_desc; Type: CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY sexo
    ADD CONSTRAINT uq_sexo_desc UNIQUE (sexo_desc);


--
-- Name: idx_acesso_colaborador_propetario; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_acesso_colaborador_propetario ON acesso USING btree (acesso_colaborador_propetario);


--
-- Name: idx_acesso_estado; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_acesso_estado ON acesso USING btree (acesso_estado);


--
-- Name: idx_acesso_menu_id; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_acesso_menu_id ON acesso USING btree (acesso_menu_id);


--
-- Name: idx_agenda_colaborador_id; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_agenda_colaborador_id ON colaborador USING btree (colaborador_id);


--
-- Name: idx_autenticacao_colaborador_id; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_autenticacao_colaborador_id ON colaborador USING btree (colaborador_id);


--
-- Name: idx_colaborador_accesso; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_colaborador_accesso ON colaborador USING btree (colaborador_accesso);


--
-- Name: idx_colaborador_accesstoken; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_colaborador_accesstoken ON colaborador USING btree (colaborador_mail, colaborador_senha);


--
-- Name: idx_colaborador_colaborador_id; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_colaborador_colaborador_id ON colaborador USING btree (colaborador_colaborador_id);


--
-- Name: idx_colaborador_estado; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_colaborador_estado ON colaborador USING btree (colaborador_estado);


--
-- Name: idx_colaborador_nome_order; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_colaborador_nome_order ON colaborador USING btree (colaborador_nome, colaborador_apelido);


--
-- Name: idx_colaborador_sexo_id; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_colaborador_sexo_id ON colaborador USING btree (colaborador_sexo_id);


--
-- Name: idx_colaborador_sistema; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_colaborador_sistema ON colaborador USING btree (colaborador_sistema);


--
-- Name: idx_colaborador_token; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_colaborador_token ON colaborador USING btree (colaborador_token);


--
-- Name: idx_perfil_colaborador_atualizacao; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_perfil_colaborador_atualizacao ON perfil USING btree (perfil_colaborador_atualizacao);


--
-- Name: idx_perfil_colaborador_id; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_perfil_colaborador_id ON perfil USING btree (perfil_colaborador_id);


--
-- Name: idx_perfil_perfil_perfil_id; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_perfil_perfil_perfil_id ON perfil USING btree (perfil_perfil_id);


--
-- Name: idx_previlegio_colaboirador_atualizacao; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_previlegio_colaboirador_atualizacao ON previlegio USING btree (previlegio_colaborador_atualizacao);


--
-- Name: idx_previlegio_colaboirador_id; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_previlegio_colaboirador_id ON previlegio USING btree (previlegio_colaborador_id);


--
-- Name: idx_previlegio_menu_id; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_previlegio_menu_id ON previlegio USING btree (previlegio_menu_id);


--
-- Name: idx_previlegio_perfil_id; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE INDEX idx_previlegio_perfil_id ON previlegio USING btree (previlegio_perfil_id);


--
-- Name: idx_sexo_id; Type: INDEX; Schema: colaborador; Owner: -
--

CREATE UNIQUE INDEX idx_sexo_id ON sexo USING btree (sexo_id);


--
-- Name: acesso fk_accesso_to_colaborador; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY acesso
    ADD CONSTRAINT fk_accesso_to_colaborador FOREIGN KEY (acesso_colaborador_id) REFERENCES colaborador(colaborador_id);


--
-- Name: acesso fk_accesso_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY acesso
    ADD CONSTRAINT fk_accesso_to_colaborador_atualizacao FOREIGN KEY (acesso_colaborador_atualizacao) REFERENCES colaborador(colaborador_id);


--
-- Name: acesso fk_accesso_to_colaborador_propetario; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY acesso
    ADD CONSTRAINT fk_accesso_to_colaborador_propetario FOREIGN KEY (acesso_colaborador_propetario) REFERENCES colaborador(colaborador_id);


--
-- Name: acesso fk_accesso_to_menu; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY acesso
    ADD CONSTRAINT fk_accesso_to_menu FOREIGN KEY (acesso_menu_id) REFERENCES menu(menu_id);


--
-- Name: autenticacao fk_autenticacao_to_colaborador; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY autenticacao
    ADD CONSTRAINT fk_autenticacao_to_colaborador FOREIGN KEY (autenticacao_colaborador_id) REFERENCES colaborador(colaborador_id);


--
-- Name: colaborador fk_colaborador_to_colaborador; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY colaborador
    ADD CONSTRAINT fk_colaborador_to_colaborador FOREIGN KEY (colaborador_colaborador_id) REFERENCES colaborador(colaborador_id);


--
-- Name: colaborador fk_colaborador_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY colaborador
    ADD CONSTRAINT fk_colaborador_to_colaborador_atualizacao FOREIGN KEY (colaborador_colaborador_atualizacao) REFERENCES colaborador(colaborador_id);


--
-- Name: colaborador fk_colaborador_to_sexo; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY colaborador
    ADD CONSTRAINT fk_colaborador_to_sexo FOREIGN KEY (colaborador_sexo_id) REFERENCES sexo(sexo_id);


--
-- Name: menu fk_menu_to_menu; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY menu
    ADD CONSTRAINT fk_menu_to_menu FOREIGN KEY (menu_menu_id) REFERENCES menu(menu_id);


--
-- Name: perfil fk_perfil_to_colaborador; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY perfil
    ADD CONSTRAINT fk_perfil_to_colaborador FOREIGN KEY (perfil_colaborador_id) REFERENCES colaborador(colaborador_id);


--
-- Name: perfil fk_perfil_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY perfil
    ADD CONSTRAINT fk_perfil_to_colaborador_atualizacao FOREIGN KEY (perfil_colaborador_atualizacao) REFERENCES colaborador(colaborador_id);


--
-- Name: perfil fk_perfil_to_perfil; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY perfil
    ADD CONSTRAINT fk_perfil_to_perfil FOREIGN KEY (perfil_perfil_id) REFERENCES perfil(perfil_id);


--
-- Name: previlegio fk_previlegio_to_colaborador; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY previlegio
    ADD CONSTRAINT fk_previlegio_to_colaborador FOREIGN KEY (previlegio_colaborador_id) REFERENCES colaborador(colaborador_id);


--
-- Name: previlegio fk_previlegio_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY previlegio
    ADD CONSTRAINT fk_previlegio_to_colaborador_atualizacao FOREIGN KEY (previlegio_colaborador_atualizacao) REFERENCES colaborador(colaborador_id);


--
-- Name: previlegio fk_previlegio_to_menu; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY previlegio
    ADD CONSTRAINT fk_previlegio_to_menu FOREIGN KEY (previlegio_menu_id) REFERENCES menu(menu_id);


--
-- Name: previlegio fk_previlegio_to_perfim; Type: FK CONSTRAINT; Schema: colaborador; Owner: -
--

ALTER TABLE ONLY previlegio
    ADD CONSTRAINT fk_previlegio_to_perfim FOREIGN KEY (previlegio_perfil_id) REFERENCES perfil(perfil_id);


--
-- PostgreSQL database dump complete
--

