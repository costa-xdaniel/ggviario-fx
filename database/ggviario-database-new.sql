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

SET search_path = ggviario, pg_catalog;

ALTER TABLE IF EXISTS ONLY ggviario.unidade DROP CONSTRAINT IF EXISTS fk_unidade_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.unidade DROP CONSTRAINT IF EXISTS fk_unidade_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.setor DROP CONSTRAINT IF EXISTS fk_setor_to_setor;
ALTER TABLE IF EXISTS ONLY ggviario.setor DROP CONSTRAINT IF EXISTS fk_setor_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.setor DROP CONSTRAINT IF EXISTS fk_setor_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.produto DROP CONSTRAINT IF EXISTS fk_produto_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.produto DROP CONSTRAINT IF EXISTS fk_produto_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.produto DROP CONSTRAINT IF EXISTS fk_produto_to_categoria;
ALTER TABLE IF EXISTS ONLY ggviario.producao DROP CONSTRAINT IF EXISTS fk_producao_to_setor;
ALTER TABLE IF EXISTS ONLY ggviario.producao DROP CONSTRAINT IF EXISTS fk_producao_to_produto;
ALTER TABLE IF EXISTS ONLY ggviario.producao DROP CONSTRAINT IF EXISTS fk_producao_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.producao DROP CONSTRAINT IF EXISTS fk_producao_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.preco DROP CONSTRAINT IF EXISTS fk_preco_to_unidade;
ALTER TABLE IF EXISTS ONLY ggviario.preco DROP CONSTRAINT IF EXISTS fk_preco_to_produto;
ALTER TABLE IF EXISTS ONLY ggviario.preco DROP CONSTRAINT IF EXISTS fk_preco_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.preco DROP CONSTRAINT IF EXISTS fk_preco_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.movimento DROP CONSTRAINT IF EXISTS fk_movimento_to_tipomovimento;
ALTER TABLE IF EXISTS ONLY ggviario.movimento DROP CONSTRAINT IF EXISTS fk_movimento_to_movimento_itemdevolucao;
ALTER TABLE IF EXISTS ONLY ggviario.movimento DROP CONSTRAINT IF EXISTS fk_movimento_to_despesa;
ALTER TABLE IF EXISTS ONLY ggviario.movimento DROP CONSTRAINT IF EXISTS fk_movimento_to_conta;
ALTER TABLE IF EXISTS ONLY ggviario.movimento DROP CONSTRAINT IF EXISTS fk_movimento_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.movimento DROP CONSTRAINT IF EXISTS fk_movimento_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS fk_fornecedor_to_distrito;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS fk_fornecedor_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS fk_fornecedor_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.equivalencia DROP CONSTRAINT IF EXISTS fk_equivalencia_to_unidade;
ALTER TABLE IF EXISTS ONLY ggviario.equivalencia DROP CONSTRAINT IF EXISTS fk_equivalencia_to_produto;
ALTER TABLE IF EXISTS ONLY ggviario.equivalencia DROP CONSTRAINT IF EXISTS fk_equivalencia_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.equivalencia DROP CONSTRAINT IF EXISTS fk_equivalencia_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS fk_despesa_to_unidade;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS fk_despesa_to_produto;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS fk_despesa_to_fornecedor;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS fk_despesa_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS fk_despesa_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.conta DROP CONSTRAINT IF EXISTS fk_conta_to_tipoconta;
ALTER TABLE IF EXISTS ONLY ggviario.conta DROP CONSTRAINT IF EXISTS fk_conta_to_contao;
ALTER TABLE IF EXISTS ONLY ggviario.conta DROP CONSTRAINT IF EXISTS fk_conta_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.conta DROP CONSTRAINT IF EXISTS fk_conta_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.compra DROP CONSTRAINT IF EXISTS fk_compra_to_unidade;
ALTER TABLE IF EXISTS ONLY ggviario.compra DROP CONSTRAINT IF EXISTS fk_compra_to_tipocompra;
ALTER TABLE IF EXISTS ONLY ggviario.compra DROP CONSTRAINT IF EXISTS fk_compra_to_produto;
ALTER TABLE IF EXISTS ONLY ggviario.compra DROP CONSTRAINT IF EXISTS fk_compra_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.compra DROP CONSTRAINT IF EXISTS fk_compra_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.compra DROP CONSTRAINT IF EXISTS fk_compra_to_cliente;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS fk_cliente_to_tipodocumento;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS fk_cliente_to_sexo;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS fk_cliente_to_distrito;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS fk_cliente_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS fk_cliente_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.categoria DROP CONSTRAINT IF EXISTS fk_categoria_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.categoria DROP CONSTRAINT IF EXISTS fk_categoria_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.categoria DROP CONSTRAINT IF EXISTS fk_categoria_to_categoria;
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
SET search_path = ggviario, pg_catalog;

DROP TRIGGER IF EXISTS tg_setor_after_update_update_cascade ON ggviario.setor;
DROP TRIGGER IF EXISTS tg_setor_after_insert_protect_parent ON ggviario.setor;
DROP TRIGGER IF EXISTS tg_producao_after_insert_movement_sector_and_product ON ggviario.producao;
DROP TRIGGER IF EXISTS tg_movimento_after_insert_update_movimento_devolucao ON ggviario.movimento;
DROP TRIGGER IF EXISTS tg_movimento_after_insert_update_itemcompra ON ggviario.movimento;
DROP TRIGGER IF EXISTS tg_movimento_after_insert_update_conta ON ggviario.movimento;
DROP TRIGGER IF EXISTS tg_despesa_after_insert_update_stock ON ggviario.compra;
DROP TRIGGER IF EXISTS tg_despesa_after_insert_update_stock ON ggviario.despesa;
SET search_path = colaborador, pg_catalog;

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
SET search_path = ggviario, pg_catalog;

ALTER TABLE IF EXISTS ONLY ggviario.unidade DROP CONSTRAINT IF EXISTS uq_unidade_nome;
ALTER TABLE IF EXISTS ONLY ggviario.tipomovimento DROP CONSTRAINT IF EXISTS uq_tmovimento_desc;
ALTER TABLE IF EXISTS ONLY ggviario.tipoconta DROP CONSTRAINT IF EXISTS uq_tconta_desc;
ALTER TABLE IF EXISTS ONLY ggviario.tipocompra DROP CONSTRAINT IF EXISTS uq_tcompra_desc;
ALTER TABLE IF EXISTS ONLY ggviario.sexo DROP CONSTRAINT IF EXISTS uq_sexo_id;
ALTER TABLE IF EXISTS ONLY ggviario.setor DROP CONSTRAINT IF EXISTS uq_setor_nome;
ALTER TABLE IF EXISTS ONLY ggviario.produto DROP CONSTRAINT IF EXISTS uq_produto_nome;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS uq_fornecedor_nome;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS uq_fornecedor_nif;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS uq_fornecedor_mail;
ALTER TABLE IF EXISTS ONLY ggviario.distrito DROP CONSTRAINT IF EXISTS uq_distrito_nome;
ALTER TABLE IF EXISTS ONLY ggviario.compra DROP CONSTRAINT IF EXISTS uq_compra_fatura;
ALTER TABLE IF EXISTS ONLY ggviario.unidade DROP CONSTRAINT IF EXISTS uq_codigo_nome;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS uq_cliente_mails;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS uq_cliente_documento;
ALTER TABLE IF EXISTS ONLY ggviario.categoria DROP CONSTRAINT IF EXISTS uq_categoria_nome;
ALTER TABLE IF EXISTS ONLY ggviario.tipodocumento DROP CONSTRAINT IF EXISTS qu_tdocumento_desc;
ALTER TABLE IF EXISTS ONLY ggviario.unidade DROP CONSTRAINT IF EXISTS pk_unidade_id;
ALTER TABLE IF EXISTS ONLY ggviario.tipomovimento DROP CONSTRAINT IF EXISTS pk_tmovimento_id;
ALTER TABLE IF EXISTS ONLY ggviario.tipodocumento DROP CONSTRAINT IF EXISTS pk_tdocumento_id;
ALTER TABLE IF EXISTS ONLY ggviario.tipoconta DROP CONSTRAINT IF EXISTS pk_tconta_id;
ALTER TABLE IF EXISTS ONLY ggviario.tipocompra DROP CONSTRAINT IF EXISTS pk_tcompra_id;
ALTER TABLE IF EXISTS ONLY ggviario.sexo DROP CONSTRAINT IF EXISTS pk_sexo_id;
ALTER TABLE IF EXISTS ONLY ggviario.setor DROP CONSTRAINT IF EXISTS pk_setor_id;
ALTER TABLE IF EXISTS ONLY ggviario.produto DROP CONSTRAINT IF EXISTS pk_produto_id;
ALTER TABLE IF EXISTS ONLY ggviario.producao DROP CONSTRAINT IF EXISTS pk_produca_id;
ALTER TABLE IF EXISTS ONLY ggviario.preco DROP CONSTRAINT IF EXISTS pk_preco_id;
ALTER TABLE IF EXISTS ONLY ggviario.movimento DROP CONSTRAINT IF EXISTS pk_movimento_id;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS pk_fornecedor_id;
ALTER TABLE IF EXISTS ONLY ggviario.equivalencia DROP CONSTRAINT IF EXISTS pk_equivalencia_id;
ALTER TABLE IF EXISTS ONLY ggviario.distrito DROP CONSTRAINT IF EXISTS pk_distrito_id;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS pk_despesa_id;
ALTER TABLE IF EXISTS ONLY ggviario.conta DROP CONSTRAINT IF EXISTS pk_conta_id;
ALTER TABLE IF EXISTS ONLY ggviario.compra DROP CONSTRAINT IF EXISTS pk_compra_id;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS pk_cliente_id;
ALTER TABLE IF EXISTS ONLY ggviario.categoria DROP CONSTRAINT IF EXISTS pk_categoria_id;
ALTER TABLE IF EXISTS ggviario.movimento DROP CONSTRAINT IF EXISTS ck_movimato_source_is_valid;
SET search_path = colaborador, pg_catalog;

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
SET search_path = ggviario, pg_catalog;

SET search_path = colaborador, pg_catalog;

SET search_path = ggviario, pg_catalog;

DROP TABLE IF EXISTS ggviario.tipomovimento;
DROP TABLE IF EXISTS ggviario.tipodocumento;
DROP TABLE IF EXISTS ggviario.tipoconta;
DROP TABLE IF EXISTS ggviario.sexo;
DROP SEQUENCE IF EXISTS ggviario.seq_faturanumero;
DROP TABLE IF EXISTS ggviario.producao;
DROP TABLE IF EXISTS ggviario.fornecedor;
DROP TABLE IF EXISTS ggviario.distrito;
SET search_path = colaborador, pg_catalog;

DROP TABLE IF EXISTS colaborador.sexo;
DROP TABLE IF EXISTS colaborador.previlegio;
DROP TABLE IF EXISTS colaborador.perfil;
DROP TABLE IF EXISTS colaborador.menu;
DROP TABLE IF EXISTS colaborador.autenticacao;
DROP TABLE IF EXISTS colaborador.acesso;
SET search_path = pg_catalog;

DROP CAST IF EXISTS (numeric AS uuid);
DROP CAST IF EXISTS (integer AS uuid);
SET search_path = lib, pg_catalog;

DROP OPERATOR IF EXISTS lib.||| (anyelement, anyelement);
DROP OPERATOR IF EXISTS lib.=== (anyelement, anyelement);
DROP OPERATOR IF EXISTS lib.->| (jsonb, text);
DROP OPERATOR IF EXISTS lib.->| (jsonb, integer);
DROP OPERATOR IF EXISTS lib.->? (jsonb, integer);
DROP OPERATOR IF EXISTS lib.->? (jsonb, text);
DROP OPERATOR IF EXISTS lib.->>> (jsonb, text);
DROP OPERATOR IF EXISTS lib.->>> (jsonb, integer);
DROP OPERATOR IF EXISTS lib.->%% (jsonb, integer);
DROP OPERATOR IF EXISTS lib.->%% (jsonb, text);
DROP OPERATOR IF EXISTS lib.->% (jsonb, integer);
DROP OPERATOR IF EXISTS lib.->% (jsonb, text);
DROP OPERATOR IF EXISTS lib.-># (jsonb, integer);
DROP OPERATOR IF EXISTS lib.-># (jsonb, text);
DROP OPERATOR IF EXISTS lib.!=== (anyelement, anyelement);
DROP OPERATOR IF EXISTS lib.! (boolean, jsonb);
DROP AGGREGATE IF EXISTS lib.agg_sum_nonnull(anyelement);
DROP AGGREGATE IF EXISTS lib.agg_jsonb_collect(jsonb);
DROP AGGREGATE IF EXISTS lib.agg_extreme(anyelement, boolean);
DROP AGGREGATE IF EXISTS lib.agg_collect(collect_next anyelement, collect_accepetnull boolean);
SET search_path = rule, pg_catalog;

DROP FUNCTION IF EXISTS rule.setor_estado_desc(_setor ggviario.setor, _const constant);
DROP FUNCTION IF EXISTS rule.produto_estado_desc(_produto ggviario.produto, _const constant);
DROP FUNCTION IF EXISTS rule.movimento_insert(arg_colaborador_id uuid, arg_conta_id uuid, arg_tmovimento_id smallint, arg_movimento_documento character varying, arg_movimento_data date, arg_movimento_montante numeric, arg_movimento_libele character varying, arg_itemcompra_id uuid, arg_despesa_id uuid, arg_movimento_id uuid, arg_movimento_devolucao boolean, arg_movimento_transferencianumero integer);
DROP FUNCTION IF EXISTS rule.movimento_check_source(_movimento ggviario.movimento);
DROP FUNCTION IF EXISTS rule.functg_setor_after_update_update_cascade();
DROP FUNCTION IF EXISTS rule.functg_setor_after_insert_protect_parent();
DROP FUNCTION IF EXISTS rule.functg_producao_after_insert_movement_sector_and_product();
DROP FUNCTION IF EXISTS rule.functg_movimento_after_insert_update_movimento_devolucao();
DROP FUNCTION IF EXISTS rule.functg_movimento_after_insert_update_itemcompra();
DROP FUNCTION IF EXISTS rule.functg_movimento_after_insert_update_conta();
DROP FUNCTION IF EXISTS rule.functg_despesa_after_insert_update_stock();
DROP FUNCTION IF EXISTS rule.functg_compra_intert_update_produto_stock();
DROP FUNCTION IF EXISTS rule.despesa_estado_desc(_despesa ggviario.despesa, _const constant);
DROP FUNCTION IF EXISTS rule.compra_insert(arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_tcompra_id uuid, arg_colaborador_id uuid, arg_compra_quantidade numeric, arg_compra_custounitario numeric, arg_compra_custobruto numeric, arg_compra_custodesconto numeric, arg_compra_custopagar numeric, arg_compra_data date, arg_compra_datafinalizar date);
DROP FUNCTION IF EXISTS rule.compra_fatura_generatenext(_tipocompra ggviario.tipocompra);
DROP FUNCTION IF EXISTS rule.compra_estado_desc(_compra ggviario.compra, _const constant);
DROP FUNCTION IF EXISTS rule.cliente_estado_desc(_cliente ggviario.cliente, _const constant);
SET search_path = ggviario, pg_catalog;

DROP TABLE IF EXISTS ggviario.cliente;
SET search_path = lib, pg_catalog;

DROP FUNCTION IF EXISTS lib.split_name(name character varying);
DROP FUNCTION IF EXISTS lib.result_true(message text);
DROP FUNCTION IF EXISTS lib.result_true(content jsonb);
DROP FUNCTION IF EXISTS lib.result_true();
DROP FUNCTION IF EXISTS lib.result_false(message text);
DROP FUNCTION IF EXISTS lib.result_false(content jsonb);
DROP FUNCTION IF EXISTS lib.result(result boolean, content jsonb);
DROP FUNCTION IF EXISTS lib.random_text(text_lenght numeric);
DROP FUNCTION IF EXISTS lib.normalize_match(argument text, option jsonb);
DROP FUNCTION IF EXISTS lib.money(moneyvalue anyelement);
DROP FUNCTION IF EXISTS lib.jsonb_to_array(argument jsonb, INOUT anyarray);
DROP FUNCTION IF EXISTS lib.jsonb_object_length(object jsonb);
DROP FUNCTION IF EXISTS lib.is_valid_nif(arg_nif text);
DROP FUNCTION IF EXISTS lib.is_numeric_sequence(argment text);
DROP FUNCTION IF EXISTS lib.is_numeric(argment text);
DROP FUNCTION IF EXISTS lib.is_integer(argment text);
DROP FUNCTION IF EXISTS lib.is_bigint(argment text);
DROP FUNCTION IF EXISTS lib.initials(name character varying);
DROP FUNCTION IF EXISTS lib.increment(numeric);
DROP FUNCTION IF EXISTS lib.increment(INOUT argment double precision);
DROP FUNCTION IF EXISTS lib.html_table_value(row_source jsonb, columns_map jsonb, column_name text);
DROP FUNCTION IF EXISTS lib.html_table_column(columns_map jsonb, column_name text);
DROP FUNCTION IF EXISTS lib.html_table(table_body_datasource jsonb, extra_columns text[], columns_map jsonb, styles jsonb);
DROP FUNCTION IF EXISTS lib.get_session_value(variablegroup character varying, variablename character varying);
DROP FUNCTION IF EXISTS lib.field_varchar(data jsonb, filed text);
DROP FUNCTION IF EXISTS lib.field_varchar(data jsonb, index integer);
DROP FUNCTION IF EXISTS lib.field_timestamp(data jsonb, filed character varying);
DROP FUNCTION IF EXISTS lib.field_timestamp(data jsonb, field text);
DROP FUNCTION IF EXISTS lib.field_timestamp(data jsonb, index integer);
DROP FUNCTION IF EXISTS lib.field_numeric(data jsonb, filed text);
DROP FUNCTION IF EXISTS lib.field_numeric(data jsonb, index integer);
DROP FUNCTION IF EXISTS lib.field_date(data jsonb, field text);
DROP FUNCTION IF EXISTS lib.field_date(data jsonb, index integer);
DROP FUNCTION IF EXISTS lib.field_boolean(data jsonb, filed text);
DROP FUNCTION IF EXISTS lib.field_boolean(data jsonb, index integer);
DROP FUNCTION IF EXISTS lib.field_bit(data jsonb, field text);
DROP FUNCTION IF EXISTS lib.field_bit(data jsonb, index integer);
DROP FUNCTION IF EXISTS lib.element_equals(anyelement, anyelement);
DROP FUNCTION IF EXISTS lib.element_different(anyelement, anyelement);
DROP FUNCTION IF EXISTS lib.dset_random_nexttext(text_lenght numeric);
DROP FUNCTION IF EXISTS lib.dset_random_nextelement(anyarray);
DROP FUNCTION IF EXISTS lib.dset_random_next(arg_min anyelement, arg_max anyelement);
DROP FUNCTION IF EXISTS lib.dset_random_next(anyelement);
DROP FUNCTION IF EXISTS lib.define_session_values(variablegroup character varying, variablename character varying, variablevalue jsonb);
DROP FUNCTION IF EXISTS lib.dblink_disconnect(connectionname character varying, option jsonb);
DROP FUNCTION IF EXISTS lib.dblink_connect(connectionname character varying, username character varying, passwordtext character varying, databasename character varying, hostname character varying, port numeric, options jsonb);
DROP FUNCTION IF EXISTS lib.concat(anyelement, anyelement);
DROP FUNCTION IF EXISTS lib.coincidences_likes(arguments_left text[], arguments_right text[], option jsonb);
DROP FUNCTION IF EXISTS lib.coincidences(arguments_left anyarray, arguments_right anyarray, option jsonb);
DROP FUNCTION IF EXISTS lib.array_remove_range(arr anyarray, arr_start_index integer, arr_count_elements integer);
DROP FUNCTION IF EXISTS lib.array_remove_at(arr anyarray, arr_start_index integer);
DROP FUNCTION IF EXISTS lib.array_min_element(arr anyarray, element anyelement);
DROP FUNCTION IF EXISTS lib.array_max_element(arr anyarray, element anyelement);
DROP FUNCTION IF EXISTS lib.array_length(anyarray);
DROP FUNCTION IF EXISTS lib.array_equals_ignore_position(arg_left anyarray, arg_right anyarray);
DROP FUNCTION IF EXISTS lib.array_build_nomnull(VARIADIC anyarray);
DROP FUNCTION IF EXISTS lib.array_build(VARIADIC argumests anyarray);
DROP FUNCTION IF EXISTS lib.agg_sum_nonnull_finalizer(acumulate anyelement);
DROP FUNCTION IF EXISTS lib.agg_sum_nonnull_collector(acumulate anyelement, nextval anyelement);
DROP FUNCTION IF EXISTS lib.agg_jsonb_collect_finalize(acumulate jsonb);
DROP FUNCTION IF EXISTS lib.agg_jsonb_collect_collector(acumulatecollect jsonb, nextcollect jsonb);
DROP FUNCTION IF EXISTS lib.agg_extreme_collector(acumulatecollect anyelement, nextcollect anyelement, first boolean);
DROP FUNCTION IF EXISTS lib.agg_collect_collector(collect_list anyarray, collect_next anyelement, collect_accepetnull boolean);
DROP FUNCTION IF EXISTS lib.age(timestamp without time zone);
SET search_path = ggviario, pg_catalog;

DROP FUNCTION IF EXISTS ggviario.get_unidade(arg_unidade_id uuid);
DROP TABLE IF EXISTS ggviario.unidade;
DROP FUNCTION IF EXISTS ggviario.get_tipocompra(arg_tcompra_id smallint);
DROP TABLE IF EXISTS ggviario.tipocompra;
DROP FUNCTION IF EXISTS ggviario.get_setor(arg_setor_id uuid);
DROP TABLE IF EXISTS ggviario.setor;
DROP FUNCTION IF EXISTS ggviario.get_produto(arg_produto_id uuid);
DROP TABLE IF EXISTS ggviario.produto;
DROP FUNCTION IF EXISTS ggviario.get_preco(arg_preco_id uuid);
DROP TABLE IF EXISTS ggviario.preco;
DROP FUNCTION IF EXISTS ggviario.get_movimento(arg_movimento_id uuid);
DROP TABLE IF EXISTS ggviario.movimento;
DROP FUNCTION IF EXISTS ggviario.get_equivalencia(arg_equivalecia_id uuid);
DROP TABLE IF EXISTS ggviario.equivalencia;
DROP FUNCTION IF EXISTS ggviario.get_despesa(arg_despesa_id uuid);
DROP TABLE IF EXISTS ggviario.despesa;
DROP FUNCTION IF EXISTS ggviario.get_conta(arg_conta_id uuid);
DROP TABLE IF EXISTS ggviario.conta;
DROP FUNCTION IF EXISTS ggviario.get_compra(arg_compra_id uuid);
DROP FUNCTION IF EXISTS ggviario.get_categoria(arg_categoria_id uuid);
DROP TABLE IF EXISTS ggviario.categoria;
SET search_path = lib, pg_catalog;

DROP FUNCTION IF EXISTS lib.is_normalized(text);
SET search_path = ggviario, pg_catalog;

DROP FUNCTION IF EXISTS ggviario.funct_reg_unidade(arg_colaborador_id uuid, arg_unidade_nome character varying, arg_unidade_codigo character varying);
DROP FUNCTION IF EXISTS ggviario.funct_reg_setor(arg_colaborador_id uuid, arg_setor_setor_id uuid, arg_setor_nome character varying);
DROP FUNCTION IF EXISTS ggviario.funct_reg_produto(arg_colaborador_id uuid, arg_categoria_id uuid, arg_produto_nome character varying, options jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_reg_producao(arg_colaborador_id uuid, arg_producao_data date, arg_producao_quantidade numeric, arg_produto_id uuid, arg_setor_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_reg_preco(arg_colaborador_id uuid, arg_produto_id uuid, arg_precos jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_reg_movimento_amortizacao_itemcompra(arg_colaborador_id uuid, arg_itemcompra_id uuid, arg_conta_id uuid, arg_movimento_montante numeric, arg_movimento_data date, arg_movimento_documento character varying);
DROP FUNCTION IF EXISTS ggviario.funct_reg_movimento_amortizacao_compra(arg_colaborador_id uuid, arg_compra_id uuid, arg_conta_id uuid, arg_movimento_montante numeric, arg_movimento_data date, arg_movimento_documento character varying);
DROP FUNCTION IF EXISTS ggviario.funct_reg_fornecedor(arg_colaborador_id uuid, arg_fornecedor_nome character varying, arg_fornecedor_nif character varying, arg_fornecedor_telefone character varying, arg_fornecedor_telemovel character varying, arg_fornecedor_mail character varying, arg_fornecedor_local character varying, arg_distrito_id smallint);
DROP FUNCTION IF EXISTS ggviario.funct_reg_equivalencia(arg_colaborador_id uuid, arg_produto_id uuid, arg_equivalencias jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_reg_despesa(arg_colaborador_id uuid, arg_fornecedor_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_despesa_quantidade numeric, arg_despesa_custounitario numeric, arg_despesa_custototal numeric, arg_despesa_data date, arg_despesa_numerofatura date);
DROP FUNCTION IF EXISTS ggviario.funct_reg_compra_divida(arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_colaborador_id uuid, arg_compra_quantidade numeric, arg_compra_custounitario numeric, arg_compra_custobruto numeric, arg_compra_custodesconto numeric, arg_compra_custopagar numeric, arg_compra_data date, arg_compra_datafinalizar date);
DROP FUNCTION IF EXISTS ggviario.funct_reg_compra_compra(arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_colaborador_id uuid, arg_compra_quantidade numeric, arg_compra_custounitario numeric, arg_compra_custobruto numeric, arg_compra_custodesconto numeric, arg_compra_custopagar numeric, arg_compra_data date);
DROP FUNCTION IF EXISTS ggviario.funct_reg_cliente(arg_colaborador_id uuid, arg_sexo_id smallint, arg_distrito_id smallint, arg_tdocumenti_id smallint, arg_cliente_documentonumero character varying, arg_cliente_nome character varying, arg_cliente_apelido character varying, arg_cliente_datanascimento date, arg_cliente_telemovel character varying, arg_cliente_telefone character varying, arg_cliente_mail character varying, arg_cliente_morada character varying, arg_cliente_localtrabalho character varying);
DROP FUNCTION IF EXISTS ggviario.funct_reg_categoria(arg_colaborador_id uuid, arg_categoria_super uuid, arg_categoria_nome character varying);
DROP FUNCTION IF EXISTS ggviario.funct_load_tipodocumento(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_produto(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_distrito(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_cliente(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_categoria(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_change_setor_estado_mode(arg_colaborador_id uuid, arg_setor_id uuid, arg_estado_modo boolean);
DROP FUNCTION IF EXISTS ggviario.funct_change_setor(arg_colaborador_id uuid, arg_setor_id uuid, arg_setor_data jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_change_produto_setoptions(arg_colaborador_id uuid, arg_produto_id uuid, options jsonb);
DROP FUNCTION IF EXISTS ggviario.compra_estado_desc(_compra compra, _const rule.constant);
DROP TABLE IF EXISTS ggviario.compra;
SET search_path = rule, pg_catalog;

DROP FUNCTION IF EXISTS rule.constant_init();
SET search_path = colaborador, pg_catalog;

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
SET search_path = lib, pg_catalog;

DROP FUNCTION IF EXISTS lib.to_uuid(anyelement);
DROP FUNCTION IF EXISTS lib.normalize(text text);
SET search_path = colaborador, pg_catalog;

DROP FUNCTION IF EXISTS colaborador.colaborador_estado_desc(arg_colaborador_estado smallint);
DROP FUNCTION IF EXISTS colaborador.colaborador_accesso_desc(arg_colaborador_acesso smallint);
SET search_path = rule, pg_catalog;

DROP TYPE IF EXISTS rule.constant;
SET search_path = lib, pg_catalog;

DROP TYPE IF EXISTS lib.result;
DROP TYPE IF EXISTS lib.person;
DROP EXTENSION IF EXISTS "uuid-ossp";
DROP EXTENSION IF EXISTS plpgsql;
DROP SCHEMA IF EXISTS rule;
DROP SCHEMA IF EXISTS public;
DROP SCHEMA IF EXISTS lib;
DROP SCHEMA IF EXISTS ggviario;
DROP SCHEMA IF EXISTS colaborador;
--
-- Name: colaborador; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA colaborador;


--
-- Name: ggviario; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA ggviario;


--
-- Name: lib; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA lib;


--
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- Name: rule; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA rule;


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET search_path = lib, pg_catalog;

--
-- Name: person; Type: TYPE; Schema: lib; Owner: -
--

CREATE TYPE person AS (
	firstname character varying,
	mediumname character varying,
	lastname character varying
);


--
-- Name: result; Type: TYPE; Schema: lib; Owner: -
--

CREATE TYPE result AS (
	result boolean,
	message jsonb
);


SET search_path = rule, pg_catalog;

--
-- Name: constant; Type: TYPE; Schema: rule; Owner: -
--

CREATE TYPE constant AS (
	equivalencia_estado_ativo smallint,
	equivalencia_estado_fechado smallint,
	preco_estado_ativo smallint,
	preco_estado_fechado smallint,
	setor_estado_ativo smallint,
	setor_estado_fechado smallint,
	setor_estado_protegido smallint,
	produto_estado_ativo smallint,
	produto_estado_fechado smallint,
	compra_estado_pendente smallint,
	compra_estado_feito smallint,
	compra_estado_empagamento smallint,
	compra_estado_pago smallint,
	compra_estado_anulado smallint,
	despesa_estado_pendente smallint,
	despesa_estado_empagamento smallint,
	despesa_estado_pago smallint,
	despesa_estado_anulado smallint,
	tipomovimento_credito smallint,
	tipomovimento_debito smallint,
	movimento_estado_devolver smallint,
	movimento_estado_emdevolucao smallint,
	movimento_estado_devolvido smallint,
	movimento_estado_anulado smallint,
	tipocompra_compra smallint,
	tipocompra_divida smallint,
	cliente_estado_ativo smallint,
	cliente_estado_fechado smallint
);


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


SET search_path = lib, pg_catalog;

--
-- Name: normalize(text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION normalize(text text) RETURNS text
    LANGUAGE plpgsql
    AS $$
declare
  new_text text;
begin
  if text is null or length( text ) = 0 then return null; end if;
  new_text := trim( replace( text, '  ', ' ' ) );

  if length( new_text ) != length( text ) then
    return lib.normalize( new_text );
  end if;

  if text is not null and length( new_text )  = 0 then
    text := null;
  end if;
  return new_text;
end;
$$;


--
-- Name: to_uuid(anyelement); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION to_uuid(anyelement) RETURNS uuid
    LANGUAGE sql IMMUTABLE
    AS $_$select cast(lpad(to_hex( $1::int8 ), 32, '0') as uuid )$_$;


SET search_path = colaborador, pg_catalog;

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
            autenticacao_datasaida = now()
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

CREATE FUNCTION funct_load_colaborador(filter jsonb) RETURNS TABLE(colaborador_id smallint, colaborador_nome character varying, colaborador_apelido character varying, colaborador_mail character varying, colaborador_estado smallint, colaborador_estadodesc character varying, colaborador_acesso smallint, colaborador_acessodesc character varying, sexo_id smallint, sexo_desc character varying, colaborador_datanascimento date, colaborador_nif character varying)
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
              where a.acesso_estao = arg_acesso_estado
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


SET search_path = rule, pg_catalog;

--
-- Name: constant_init(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION constant_init() RETURNS constant
    LANGUAGE plpgsql IMMUTABLE
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


SET search_path = ggviario, pg_catalog;

--
-- Name: compra; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE compra (
    compra_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    compra_produto_id uuid NOT NULL,
    compra_unidade_id uuid NOT NULL,
    compra_cliente_id uuid NOT NULL,
    compra_tcompra_id smallint NOT NULL,
    compra_colaborador_id uuid NOT NULL,
    compra_colaborador_atualizacao uuid NOT NULL,
    compra_faturanumero character varying(8) NOT NULL,
    compra_quantidade numeric NOT NULL,
    compra_quantidadeproduto numeric NOT NULL,
    compra_custounitario numeric NOT NULL,
    compra_custobruto numeric NOT NULL,
    compra_custodesconto numeric NOT NULL,
    compra_custopagar numeric NOT NULL,
    compra_custoamortizado numeric DEFAULT 0 NOT NULL,
    compra_data date,
    compra_datafinalizar date NOT NULL,
    compra_datafim timestamp without time zone,
    compra_dataultimamovimentacao date,
    compra_estado smallint DEFAULT 3 NOT NULL,
    compra_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    compra_dataatualizacao timestamp without time zone
);


--
-- Name: compra_estado_desc(compra, rule.constant); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION compra_estado_desc(_compra compra, _const rule.constant DEFAULT rule.constant_init()) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare
begin
  if _const is null then _const := rule.constant_init(); end if;
  return case
         when _compra.compra_estado = _const.compra_estado_pendente then 'Pendente'
         when _compra.compra_estado = _const.compra_estado_feito then 'Feito'
         when _compra.compra_estado = _const.compra_estado_empagamento then 'Em pagamento'
         when _compra.compra_estado = _const.compra_estado_pago then 'Pago'
         when _compra.compra_estado = _const.compra_estado_anulado then 'Anulado'
  end;
end;
$$;


--
-- Name: funct_change_produto_setoptions(uuid, uuid, jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_produto_setoptions(arg_colaborador_id uuid, arg_produto_id uuid, options jsonb) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _produto ggviario.produto;
  arg_produto_servicocliente boolean default coalesce( options->?'produto_servicocliente', true );
  arg_produto_servicofornecedor boolean default coalesce( options->?'produto_servicofornecedor', true );
  arg_produto_servicoproducao boolean default coalesce( options->?'produto_servicoproducao', true );
  arg_produto_stockdinamico boolean default coalesce( options->?'produto_stockdinamico', true );
begin

  _produto := ggviario.get_produto( arg_produto_id );
  update ggviario.produto
  set produto_colaborador_atualizacao = arg_colaborador_id,
    produto_dataatualizacao = current_timestamp,
    produto_servicocliente = arg_produto_servicocliente,
    produto_servicofornecedor = arg_produto_servicofornecedor,
    produto_servicoproducao = arg_produto_servicoproducao,
    produto_stockdinamico = arg_produto_stockdinamico
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


--
-- Name: funct_change_setor(uuid, uuid, jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_setor(arg_colaborador_id uuid, arg_setor_id uuid, arg_setor_data jsonb) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _setor ggviario.setor;
  arg_setor_nome character varying;
begin
  _setor := ggviario.get_setor( arg_setor_id );
  arg_setor_nome := coalesce( arg_setor_nome->>'setor_nome', _setor.setor_nome );
  
  
  if arg_setor_nome != _setor.setor_nome 
    and (
      select count( * )
        from ggviario.setor st 
        where lower( st.setor_nome ) = lower( arg_setor_nome )
     ) > 0 then
    return lib.result_false( 'Já existe um setor com esse nome!' );
  end if;
  
  update ggviario.setor
    set setor_nome = arg_setor_nome 
    where setor_id = arg_setor_id
    returning * into _setor
  ;
  
  return lib.result_true(
    jsonb_build_object(
      'setor', _setor
    )
  );
  
end;
$$;


--
-- Name: funct_change_setor_estado_mode(uuid, uuid, boolean); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_setor_estado_mode(arg_colaborador_id uuid, arg_setor_id uuid, arg_estado_modo boolean) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _setor ggviario.setor;
  arg_setor_estado int2;
  _const rule.constant;
begin
  _const := rule.constant_init();
  _setor := ggviario.get_setor( arg_setor_id );

  if arg_estado_modo and _setor.setor_estado not in( _const.setor_estado_fechado ) then
    return lib.result_false( 'Não pode abrir um setor no estado '||rule.setor_estado_desc( _setor, _const ) );
  elseif not arg_estado_modo and _setor.setor_estado not in ( _const.setor_estado_ativo, _const.setor_estado_protegido ) then
    return lib.result_false( 'Não pode fechar um setor no estado '||rule.setor_estado_desc( _setor, _const ) );
  end if;

  if arg_estado_modo and _setor.setor_totalsubsetores > 0 then
    arg_setor_estado := _const.setor_estado_protegido;
  elseif arg_estado_modo and _setor.setor_totalsubsetores = 0 then
    arg_setor_estado := _const.setor_estado_ativo;
  elsif not arg_estado_modo then
    arg_setor_estado := _const.setor_estado_fechado;
  end if;

  update ggviario.setor
    set setor_estado = arg_setor_estado,
        setor_colaborador_atualizacao = arg_colaborador_id,
        setor_dataatualizacao = current_timestamp
    where setor_id = arg_setor_id
    returning * into _setor
  ;

  return lib.result_true(
    jsonb_build_object(
      'setor', _setor
    )
  );
end;
$$;


--
-- Name: funct_load_categoria(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_categoria(filter jsonb) RETURNS TABLE(categoria_id uuid, categoria_nome character varying, categoria_super_id uuid, categoria_super_nome character varying)
    LANGUAGE sql
    AS $$
select
  cat.categoria_id,
  cat.categoria_nome,
  super.categoria_id,
  super.categoria_nome
from ggviario.categoria cat
  left join ggviario.categoria super on cat.categoria_categoria_id = super.categoria_id
$$;


--
-- Name: funct_load_cliente(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_cliente(filter jsonb) RETURNS TABLE(cliente_id uuid, cliente_nome character varying, cliente_apelido character varying, cliente_datanascimento date, cliente_documentonumero character varying, cliente_mail character varying, cliente_morada character varying, cliente_telefone character varying, cliente_telemovel character varying, cliente_localtrabalho character varying, cliente_estado smallint, cliente_estadodesc character varying, sexo_id smallint, sexo_desc character varying, distrito_id smallint, distrito_nome character varying, tdocumento_id smallint, tdocumento_desc character varying, cliente_montentecompras numeric, cliente_montentedividas numeric, cliente_montantetatal numeric, cliente_montanteamortizado numeric, cliente_montantependente numeric)
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


--
-- Name: funct_load_distrito(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_distrito(filter jsonb) RETURNS TABLE(distrito_id smallint, distrito_nome character varying)
    LANGUAGE sql
    AS $$ select distrito_id, distrito_nome from ggviario.distrito;$$;


--
-- Name: funct_load_produto(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_produto(filter jsonb) RETURNS TABLE(produto_id uuid, produto_nome character varying, produto_stock numeric, produto_servicocliente boolean, produto_servicofornecedor boolean, produto_servicoproducao boolean, produto_stockdinamico boolean, categoria_id uuid, categoria_nome character varying, equivalencias jsonb, precos jsonb)
    LANGUAGE sql
    AS $$
select pro.produto_id,
  pro.produto_nome,
  pro.produto_stock,
  pro.produto_servicocliente,
  pro.produto_servicofornecedor,
  pro.produto_servicoproducao,
  pro.produto_stockdinamico,
  cat.categoria_id,
  cat.categoria_nome,

  lib.agg_jsonb_collect(
  distinct to_jsonb( equi )|| to_jsonb( equiunit )
  ) filter ( where pre.preco_id is not null ),

  lib.agg_jsonb_collect(
  distinct to_jsonb( pre )|| to_jsonb( preunit )
  ) filter ( where pre.preco_id is not null )
from produto pro
  inner join ggviario.categoria cat on pro.produto_categoria_id = cat.categoria_id
  left join ggviario.equivalencia equi on pro.produto_id = equi.equivalencia_produto_id
  left join ggviario.unidade equiunit on equi.equivalencia_unidade_id = equiunit.unidade_id
  left join ggviario.preco pre on pro.produto_id = pre.preco_produto_id
  left join ggviario.unidade preunit on pre.preco_unidade_id = preunit.unidade_id
group by pro.produto_id,
  cat.categoria_id
order by pro.produto_nome,
  cat.categoria_nome

$$;


--
-- Name: funct_load_tipodocumento(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_tipodocumento(filter jsonb) RETURNS TABLE(tdocumento_id smallint, tdocumento_desc character varying)
    LANGUAGE sql
    AS $$
  select tdoc.tdocumento_id, tdoc.tdocumento_desc from ggviario.tipodocumento tdoc;
$$;


--
-- Name: funct_reg_categoria(uuid, uuid, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_categoria(arg_colaborador_id uuid, arg_categoria_super uuid, arg_categoria_nome character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _categoria ggviario.categoria;
begin

  if (
       select count( * )
       from ggviario.categoria cat
       where lower( cat.categoria_nome ) = lower( arg_categoria_nome )
     ) > 0 then
    return lib.result_false( 'O nome de categoria já existe!' );
  end if;


  insert into ggviario.categoria(
    categoria_colaborador_id,
    categoria_categoria_id,
    categoria_nome
  ) values (
    arg_colaborador_id,
    arg_categoria_super,
    arg_categoria_nome
  ) returning * into _categoria;

  return lib.result_true(
      jsonb_build_object(
          'categoria', _categoria
      )
  );
end;
$$;


--
-- Name: funct_reg_cliente(uuid, smallint, smallint, smallint, character varying, character varying, character varying, date, character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_cliente(arg_colaborador_id uuid, arg_sexo_id smallint, arg_distrito_id smallint, arg_tdocumenti_id smallint, arg_cliente_documentonumero character varying, arg_cliente_nome character varying, arg_cliente_apelido character varying, arg_cliente_datanascimento date, arg_cliente_telemovel character varying, arg_cliente_telefone character varying, arg_cliente_mail character varying, arg_cliente_morada character varying, arg_cliente_localtrabalho character varying) RETURNS lib.result
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


--
-- Name: funct_reg_compra_compra(uuid, uuid, uuid, uuid, numeric, numeric, numeric, numeric, numeric, date); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_compra_compra(arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_colaborador_id uuid, arg_compra_quantidade numeric, arg_compra_custounitario numeric, arg_compra_custobruto numeric, arg_compra_custodesconto numeric, arg_compra_custopagar numeric, arg_compra_data date) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _res lib.result;
  _tipocompra ggviario.tipocompra;
  _compra ggviario.compra;
  _const rule.constant;
begin
  _const := rule.constant_init();
  _tipocompra := ggviario.get_tipocompra( _const.tipocompra_compra );
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
      arg_compra_data
  );

  if not _res.result then
    return _res;
  end if;

  _compra := ggviario.get_compra(
      ( _res.message->'compra'->>'compra_id' )::uuid
  );


  _res := funct_reg_movimento_amortizacao_itemcompra(
    arg_colaborador_id,
    _compra.compra_id,
    null,
    arg_compra_custopagar,
    arg_compra_data,
    _compra.compra_faturanumero
  );
  
  return _res;

end
$$;


--
-- Name: funct_reg_compra_divida(uuid, uuid, uuid, uuid, numeric, numeric, numeric, numeric, numeric, date, date); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_compra_divida(arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_colaborador_id uuid, arg_compra_quantidade numeric, arg_compra_custounitario numeric, arg_compra_custobruto numeric, arg_compra_custodesconto numeric, arg_compra_custopagar numeric, arg_compra_data date, arg_compra_datafinalizar date) RETURNS lib.result
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


--
-- Name: funct_reg_despesa(uuid, uuid, uuid, uuid, numeric, numeric, numeric, date, date); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_despesa(arg_colaborador_id uuid, arg_fornecedor_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_despesa_quantidade numeric, arg_despesa_custounitario numeric, arg_despesa_custototal numeric, arg_despesa_data date, arg_despesa_numerofatura date) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _despesa ggviario.despesa;
  _produto ggviario.produto;
  _equivalencia ggviario.equivalencia;
  arg_totalprodutoincrementar numeric;
  _const rule.constant;
begin
  _const := rule.constant_init();
  arg_totalprodutoincrementar := arg_despesa_quantidade;
  _produto := ggviario.get_produto( arg_produto_id );

  if _produto.produto_servicostockdinamico then

    select * into _equivalencia
      from ggviario.equivalencia equ
      where equ.equivalencia_estado = _const.equivalencia_estado_ativo
        and equ.equivalencia_produto_id = arg_produto_id
        and equ.equivalencia_unidade_id = arg_unidade_id
    ;

    if _equivalencia.equivalencia_id is null then
      return lib.result_false( 'Não foi encontrado equivalencia da unidade para o produto!' );
    end if;

    arg_totalprodutoincrementar := arg_despesa_quantidade * _equivalencia.equivalencia_quantidade;

  end if;

  insert into ggviario.despesa(
    despesa_colaborador_id,
    despesa_fornecedor_id,
    despesa_produto_id,
    despesa_unidade_id,
    despesa_quatidade,
    despesa_custounitario,
    despesa_custototal,
    despesa_data,
    despesa_numerofatura,
    despesa_quantidadeproduto
  ) values (
    arg_colaborador_id,
    arg_fornecedor_id,
    arg_produto_id,
    arg_unidade_id,
    arg_despesa_quantidade,
    arg_despesa_custounitario,
    arg_despesa_custototal,
    arg_despesa_data,
    arg_despesa_numerofatura,
    arg_totalprodutoincrementar
  ) returning * into _despesa;
  
  return lib.result_true(
    jsonb_build_object(
      'despesa', _despesa
    )
  );
end;
$$;


--
-- Name: funct_reg_equivalencia(uuid, uuid, jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_equivalencia(arg_colaborador_id uuid, arg_produto_id uuid, arg_equivalencias jsonb) RETURNS lib.result
    LANGUAGE plpgsql
    AS $_$
declare
  KEY_UNIDADE_ID character varying default 'unidade_id';
  KEY_EQUIVALENCIA_QUANTIDADE character varying default 'equivalencia_quantidade';

  /**
    [
      {
        "unidade_id"::UUID,
        "equivalencia_quantidade":QUANTIDADE
      }
    ]
  */
  arg_next jsonb;
  arg_next_unidade_id uuid;
  arg_next_equivalencia_quantidade numeric;
  list_equivalencias uuid [] default '{}'::uuid[];
  _equivalencia ggviario.equivalencia;
  _const rule.constant;
  jEquivalencias jsonb default '[]';
begin
  _const := rule.constant_init();

  for iterator in 0 .. jsonb_array_length( arg_equivalencias ) -1 loop
    arg_next := arg_equivalencias -> iterator;
    arg_next_unidade_id := arg_next ->> KEY_UNIDADE_ID;
    arg_next_equivalencia_quantidade := arg_next -># KEY_EQUIVALENCIA_QUANTIDADE;
    select * into _equivalencia
    from ggviario.equivalencia
    where equivalencia_unidade_id = arg_next_unidade_id
          and equivalencia_produto_id = arg_produto_id
          and equivalencia_estado = _const.equivalencia_estado_ativo
  ;

    if _equivalencia.equivalencia_id is null
       or _equivalencia.equivalencia_quantidade !=== arg_next_equivalencia_quantidade
    then
      insert into ggviario.equivalencia(
        equivalencia_colaborador_id,
        equivalencia_produto_id,
        equivalencia_unidade_id,
        equivalencia_quantidade
      ) values (
        arg_colaborador_id,
        arg_produto_id,
        arg_next_unidade_id,
        arg_next_equivalencia_quantidade
      ) returning * into _equivalencia;
      jEquivalencias := jEquivalencias || ( to_jsonb( _equivalencia ) || $${"equivalencia_registro":true}$$::jsonb );
    else
      jEquivalencias := jEquivalencias || ( to_jsonb( _equivalencia ) || $${"equivalencia_registro":false}$$::jsonb );
    end if;

    list_equivalencias := list_equivalencias || _equivalencia.equivalencia_id;
  end loop;

  update ggviario.equivalencia
  set equivalencia_estado = _const.equivalencia_estado_fechado,
    equivalencia_colaborador_atualizacao = arg_colaborador_id,
    equivalencia_dataatualizacao = current_timestamp
  where equivalencia_produto_id = arg_produto_id
        and equivalencia_estado = _const.equivalencia_estado_ativo
        and not equivalencia_id = any( list_equivalencias )
  ;

  return lib.result_true(
      jsonb_build_object(
          'equivalencias', jEquivalencias
      )
  );
end;
$_$;


--
-- Name: funct_reg_fornecedor(uuid, character varying, character varying, character varying, character varying, character varying, character varying, smallint); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_fornecedor(arg_colaborador_id uuid, arg_fornecedor_nome character varying, arg_fornecedor_nif character varying, arg_fornecedor_telefone character varying, arg_fornecedor_telemovel character varying, arg_fornecedor_mail character varying, arg_fornecedor_local character varying, arg_distrito_id smallint) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$

declare
  _fornecedor ggviario.fornecedor;
begin

  if arg_fornecedor_nif is not null 
    and not lib.is_valid_nif( arg_fornecedor_nif )
  then
    return lib.result_false( 'NIF invalido!' );
  end if;
  
  if arg_fornecedor_mail is not null
    and (
      select count( * )
        from ggviario.fornecedor fo
        where fo.fornecedor_mail = arg_fornecedor_mail
     ) > 0 then
    return lib.result_false( 'Já existe um fornecedor com esse email!' );
  end if;

  if arg_fornecedor_nif is not null
    and (
      select count( * )
        from ggviario.fornecedor fo
        where fornecedor_nif = arg_fornecedor_nif
  ) > 0 then
    return lib.result_false( 'Já existe um fornecedor com esse NIF!' );
  end if;

  insert into ggviario.fornecedor(
    fornecedor_colaborador_id,
    fornecedor_distrito_id,
    fornecedor_nome,
    fornecedor_nif,
    fornecedor_telefone,
    fornecedor_telemovel,
    fornecedor_mail,
    fornecedor_local
  ) values(
    arg_colaborador_id,
    arg_distrito_id,
    arg_fornecedor_nome,
    arg_fornecedor_nif,
    arg_fornecedor_telefone,
    arg_fornecedor_telemovel,
    arg_fornecedor_mail,
    arg_fornecedor_local
  ) returning * into _fornecedor;

  return lib.result_true(
    jsonb_build_object(
      'fornecedor', _fornecedor
    )
  );
end;
$$;


--
-- Name: funct_reg_movimento_amortizacao_compra(uuid, uuid, uuid, numeric, date, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_movimento_amortizacao_compra(arg_colaborador_id uuid, arg_compra_id uuid, arg_conta_id uuid, arg_movimento_montante numeric, arg_movimento_data date, arg_movimento_documento character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  /**
   Amortizao de uma divida em um movimento de credito para a empresa
   */
  _movimento ggviario.movimento;
  _compra record;
  _const rule.constant;
begin

  _const := rule.constant_init();

  select * into _compra
    from  compra cp
    where cp.compra_id = arg_compra_id
  ;

  if _compra.compra_estado not in ( _const.compra_estado_feito, _const.compra_estado_empagamento ) then
    return lib.result_false( format( 'Não pode amortizar uma compra no estado %I!', rule.compra_estado_desc( _compra, _const ) ) );
  end if;

  if _compra.compra_custoamortizado + arg_movimento_montante > _compra.compra_custopagar then
    return lib.result_false( 'O valor da pagar ultrapassa o valor total da compra!' );
  end if;

  arg_movimento_documento := lib.normalize( arg_movimento_documento );

  if arg_movimento_documento is null then
    arg_movimento_documento := '1';
  end if;

  _movimento := rule.movimento_insert(
    arg_colaborador_id,
    arg_conta_id,
    _const.tipomovimento_credito,
    arg_movimento_documento,
    arg_movimento_data,
    arg_movimento_montante,
    format(
      'Amortizacao da %s numero %s',
      _compra.tcompra_desc,
      _compra.compra_faturanumero||'/'||_compra.compra_codigo
    ),
    _compra.compra_id
  );

  _compra := get_compra( arg_compra_id );

  return lib.result_true(
    jsonb_build_object(
      'movimento', _movimento,
       'compra', _compra
    )
  );

end;
$$;


--
-- Name: funct_reg_movimento_amortizacao_itemcompra(uuid, uuid, uuid, numeric, date, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_movimento_amortizacao_itemcompra(arg_colaborador_id uuid, arg_itemcompra_id uuid, arg_conta_id uuid, arg_movimento_montante numeric, arg_movimento_data date, arg_movimento_documento character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  /**
   Amortizao de uma divida em um movimento de credito para a empresa
   */
  _movimento ggviario.movimento;
  _itemcompra record;
  _const rule.constant;
begin

  _const := rule.constant_init();

  select * into _itemcompra
    from ggviario.itemcompra ic
      inner join tipocompra tc on ic.itemcompra_tcompra_id = tc.tcompra_id
      inner join compra cp on ic.itemcompra_compra_id = cp.compra_id
    where ic.itemcompra_id = arg_itemcompra_id
  ;

  if _itemcompra.itemcompra_estado not in ( _const.compra_estado_feito, _const.compra_estado_empagamento ) then
    return lib.result_false( format( 'Não pode amortizar uma compra no estado %I!', rule.itemcompra_estado_desc( _itemcompra, _const ) ) );
  end if;

  if _itemcompra.itemcompra_custoamortizado + arg_movimento_montante > _itemcompra.itemcompra_custopagar then
    return lib.result_false( 'O valor da pagar ultrapassa o valor total da compra!' );
  end if;

  arg_movimento_documento := lib.normalize( arg_movimento_documento );

  if arg_movimento_documento is null then
    arg_movimento_documento := '1';
  end if;

  _movimento := rule.movimento_insert(
    arg_colaborador_id,
    arg_conta_id,
    _const.tipomovimento_credito,
    arg_movimento_documento,
    arg_movimento_data,
    arg_movimento_montante,
    format(
      'Amortizacao da %s numero %s',
      _itemcompra.tcompra_desc,
      _itemcompra.compra_faturanumero||'/'||_itemcompra.itemcompra_codigo
    ),
    _itemcompra.itemcompra_id
  );

  _itemcompra := get_itemcompra( arg_itemcompra_id );

  return lib.result_true(
    jsonb_build_object(
      'movimento', _movimento,
       'itemcompra', _itemcompra
    )
  );

end;
$$;


--
-- Name: funct_reg_preco(uuid, uuid, jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_preco(arg_colaborador_id uuid, arg_produto_id uuid, arg_precos jsonb) RETURNS lib.result
    LANGUAGE plpgsql
    AS $_$
declare
  KEY_UNIDADE_ID character varying default 'unidade_id';
  KEY_PRECO_VALOR character varying default 'preco_valor';

  /**
    [
      {
        "unidade_id"::UUID,
        "equivalencia_quantidade":QUANTIDADE
      }
    ]
  */
  arg_next jsonb;
  arg_next_unidade_id uuid;
  arg_next_preco_valor numeric;
  list_precos uuid [] default '{}'::uuid[];
  _preco ggviario.preco;
  _const rule.constant;
  jPrecos jsonb default '[]';
begin
  _const := rule.constant_init();

  for iterator in 0 .. jsonb_array_length( arg_precos ) -1 loop
    arg_next := arg_precos -> iterator;
    arg_next_unidade_id := arg_next ->> KEY_UNIDADE_ID;
    arg_next_preco_valor := arg_next -># KEY_PRECO_VALOR;
    select * into _preco
    from ggviario.preco
    where preco_unidade_id = arg_next_unidade_id
          and preco_produto_id = arg_produto_id
          and preco_estado = _const.preco_estado_ativo
  ;

    if _preco.preco_id is null
       or _preco.preco_valor !=== arg_next_preco_valor
    then
      insert into ggviario.preco(
        preco_colaborador_id,
        preco_produto_id,
        preco_unidade_id,
        preco_valor
      ) values (
        arg_colaborador_id,
        arg_produto_id,
        arg_next_unidade_id,
        arg_next_preco_valor
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
$_$;


--
-- Name: funct_reg_producao(uuid, date, numeric, uuid, uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_producao(arg_colaborador_id uuid, arg_producao_data date, arg_producao_quantidade numeric, arg_produto_id uuid, arg_setor_id uuid) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _producao ggviario.producao;
  _setor ggviario.setor;
  _produto ggviario.produto;
  _const rule.constant;
begin
  _const := rule.constant_init();
  _setor := ggviario.get_setor( arg_setor_id );
  _produto := ggviario.get_produto( arg_produto_id );

  if _setor.setor_estado != _const.setor_estado_ativo then
    return lib.result_false( 'Não pode cadastra nenhuma produção directa para o setor no estado ' || rule.setor_estado_desc( _setor, _const ) );
  end if;

  if _produto.produto_estado != _const.produto_estado_ativo then
    return lib.result_false( 'Não pode cadastrar nenhuma produção para produdo em estado '||rule.produto_estado_desc( _produto, _const ) );
  end if;

  if not _produto.produto_servicoproducao then
    return lib.result_false( 'Não pode cadastrar nenhuma produção para produtos que não são para o servico de produção!' );
  end if;

  insert into ggviario.producao (
    producao_colaborador_id,
    producao_produto_id,
    producao_setor_id,
    producao_quantidade,
    producao_data
  ) values(
    arg_colaborador_id,
    arg_produto_id,
    arg_setor_id,
    arg_producao_quantidade,
    arg_producao_data
  ) returning * into _producao;
  
  return lib.result_true(
    jsonb_build_object(
      'producao', _producao
    )
  );
end;
$$;


--
-- Name: funct_reg_produto(uuid, uuid, character varying, jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_produto(arg_colaborador_id uuid, arg_categoria_id uuid, arg_produto_nome character varying, options jsonb) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _produto ggviario.produto;
begin

  if (
       select count( * )
       from ggviario.produto pro
       where lower( pro.produto_nome ) = lower( arg_produto_nome )
     ) > 0  then
    return lib.result_false(
        'Já existe um produto com esse nome!'
    );
  end if;

  insert into ggviario.produto(
    produto_categoria_id,
    produto_colaborador_id,
    produto_nome
  ) values (
    arg_categoria_id,
    arg_colaborador_id,
    arg_produto_nome
  ) returning * into _produto;

  return ggviario.funct_change_produto_setoptions(
      arg_colaborador_id,
      _produto.produto_id,
      options
  );
end;
$$;


--
-- Name: funct_reg_setor(uuid, uuid, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_setor(arg_colaborador_id uuid, arg_setor_setor_id uuid, arg_setor_nome character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _setor_parent ggviario.setor;
  _setor ggviario.setor;
  _const rule.constant;
begin

  _const := rule.constant_init();
  _setor_parent := ggviario.get_setor( arg_setor_setor_id );

  if _setor_parent.setor_id is not null
    and _setor_parent.setor_estado not in ( _const.setor_estado_protegido, _const.setor_estado_ativo )
  then
    return lib.result_false( 'Não pode criar um sub setor num setor no estado '||rule.setor_estado_desc( _setor_parent, _const ) );
  end if;

  if (
    select count( * )
      from ggviario.setor
      where lower( setor_nome ) = lower( arg_setor_nome )
  ) > 0 then
    return lib.result_false( 'Já existe um setor com esse nome!' );
  end if;

  insert into ggviario.setor(
    setor_colaborador_id,
    setor_setor_id,
    setor_nome
  ) values (
    arg_colaborador_id,
    arg_setor_setor_id,
    arg_setor_nome
  ) returning * into _setor;
  
  return lib.result_true(
    jsonb_build_object(
      'setor', _setor
    )
  );
end;
$$;


--
-- Name: funct_reg_unidade(uuid, character varying, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_unidade(arg_colaborador_id uuid, arg_unidade_nome character varying, arg_unidade_codigo character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _unidade ggviario.unidade;
begin
  if(
      select count( * )
      from ggviario.unidade
      where lower( arg_unidade_nome ) = lower( unidade_nome )
    ) > 0 then
    return lib.result_false( 'Já existe uma unidade com esse nome!' );
  end if;

  insert into ggviario.unidade(
    unidade_colaborador_id,
    unidade_nome,
    unidade_codigo
  ) values (
    arg_colaborador_id,
    arg_unidade_nome,
    arg_unidade_codigo
  ) returning * into _unidade;

  return lib.result_true(
      jsonb_build_object(
          'unidade', _unidade
      )
  );
end;
$$;


SET search_path = lib, pg_catalog;

--
-- Name: is_normalized(text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION is_normalized(text) RETURNS boolean
    LANGUAGE sql
    AS $_$
select
  lib.normalize( $1 ) = $1
    or lib.normalize( $1 ) is null and $1 is null
$_$;


SET search_path = ggviario, pg_catalog;

--
-- Name: categoria; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE categoria (
    categoria_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    categoria_colaborador_id uuid NOT NULL,
    categoria_colaborador_atualizacao uuid,
    categoria_categoria_id uuid,
    categoria_nome character varying(32) NOT NULL,
    categoria_posisao smallint,
    categoria_estado smallint DEFAULT 1 NOT NULL,
    categoria_dataregito timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    categoria_dataatualizacao timestamp without time zone,
    CONSTRAINT ck_categoria_nome_is_normalized CHECK (lib.is_normalized((categoria_nome)::text))
);


--
-- Name: get_categoria(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_categoria(arg_categoria_id uuid) RETURNS categoria
    LANGUAGE sql
    AS $$ select * from ggviario.categoria where categoria_id = arg_categoria_id; $$;


--
-- Name: get_compra(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_compra(arg_compra_id uuid) RETURNS compra
    LANGUAGE sql
    AS $$ select * from ggviario.compra where compra_id = arg_compra_id; $$;


--
-- Name: conta; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE conta (
    conta_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    conta_conta_id uuid,
    conta_tconta_id smallint NOT NULL,
    conta_colaborador_id uuid NOT NULL,
    conta_colaborador_atualizacao uuid,
    conta_numero character varying(15) NOT NULL,
    conta_numerobancario character varying(32) NOT NULL,
    conta_nome character varying(48) NOT NULL,
    conta_credito numeric DEFAULT 0 NOT NULL,
    conta_debito numeric DEFAULT 0 NOT NULL,
    conta_saldo numeric DEFAULT 0 NOT NULL,
    conta_dataultimamovimentacao date,
    conta_estado smallint DEFAULT 1 NOT NULL,
    conta_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    conta_dataatualizacao timestamp without time zone
);


--
-- Name: COLUMN conta.conta_saldo; Type: COMMENT; Schema: ggviario; Owner: -
--

COMMENT ON COLUMN conta.conta_saldo IS 'O saldo disponivel na conta';


--
-- Name: COLUMN conta.conta_dataultimamovimentacao; Type: COMMENT; Schema: ggviario; Owner: -
--

COMMENT ON COLUMN conta.conta_dataultimamovimentacao IS 'A data em que a conta teve a ultima movimentacao';


--
-- Name: get_conta(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_conta(arg_conta_id uuid) RETURNS conta
    LANGUAGE sql
    AS $$
  select *
    from ggviario.conta cont 
    where cont.conta_id = arg_conta_id;
$$;


--
-- Name: despesa; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE despesa (
    despesa_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    despesa_fornecedor_id uuid NOT NULL,
    despesa_produto_id uuid NOT NULL,
    despesa_unidade_id uuid NOT NULL,
    despesa_colaborador_id uuid NOT NULL,
    despesa_colaborador_atualizacao uuid,
    despesa_data date NOT NULL,
    despesa_numerofatura character varying(32),
    despesa_quatidade numeric NOT NULL,
    despesa_quantidadeproduto numeric NOT NULL,
    despesa_custounitario numeric NOT NULL,
    despesa_custototal numeric NOT NULL,
    despesa_custoamortizado numeric DEFAULT 0 NOT NULL,
    despesa_dataultimamovimento date,
    despesa_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    despesa_dataatualizacao timestamp without time zone,
    despesa_estado smallint DEFAULT 1 NOT NULL
);


--
-- Name: COLUMN despesa.despesa_numerofatura; Type: COMMENT; Schema: ggviario; Owner: -
--

COMMENT ON COLUMN despesa.despesa_numerofatura IS 'O numero de fatura recebido do fornecedor';


--
-- Name: get_despesa(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_despesa(arg_despesa_id uuid) RETURNS despesa
    LANGUAGE sql
    AS $$
    select *
      from ggviario.despesa fo
      where fo.despesa_id = arg_despesa_id;
  $$;


--
-- Name: equivalencia; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE equivalencia (
    equivalencia_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    equivalencia_produto_id uuid NOT NULL,
    equivalencia_unidade_id uuid NOT NULL,
    equivalencia_colaborador_id uuid NOT NULL,
    equivalencia_colaborador_atualizacao uuid,
    equivalencia_quantidade numeric,
    equivalencia_estado smallint DEFAULT 1 NOT NULL,
    equivalencia_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    equivalencia_dataatualizacao timestamp without time zone
);


--
-- Name: get_equivalencia(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_equivalencia(arg_equivalecia_id uuid) RETURNS equivalencia
    LANGUAGE sql
    AS $$
select *
from ggviario.equivalencia equi
where equi.equivalencia_id = arg_equivalecia_id
$$;


--
-- Name: movimento; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE movimento (
    movimento_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    movimento_conta_id uuid,
    movimento_tmovimento_id smallint NOT NULL,
    movimento_colaborador_id uuid NOT NULL,
    movimento_colaborador_atualizacao uuid,
    movimento_movimento_id uuid,
    movimento_compra_id uuid,
    movimento_despeda_id uuid,
    movimento_data date NOT NULL,
    movimento_documento character varying(32),
    movimento_montante numeric,
    movimento_libele character varying(128),
    movimento_transferencianumero integer,
    movimento_devolucao boolean DEFAULT false,
    movimento_devolucaoultimadada date,
    movimento_devolucamontantedevolvido numeric DEFAULT 0 NOT NULL,
    movimento_estado smallint DEFAULT 0 NOT NULL,
    movimento_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    movimento_dataatualizacao timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


--
-- Name: get_movimento(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_movimento(arg_movimento_id uuid) RETURNS movimento
    LANGUAGE sql
    AS $$
  select *
    from movimento mv
    where mv.movimento_id = arg_movimento_id;
  $$;


--
-- Name: preco; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE preco (
    preco_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    preco_produto_id uuid NOT NULL,
    preco_unidade_id uuid NOT NULL,
    preco_colaborador_id uuid NOT NULL,
    preco_colaborador_atualizacao uuid,
    preco_valor numeric,
    preco_estado smallint DEFAULT 1 NOT NULL,
    preco_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    preco_dataatualizacao timestamp without time zone
);


--
-- Name: get_preco(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_preco(arg_preco_id uuid) RETURNS preco
    LANGUAGE sql
    AS $$
select *
from ggviario.preco preco
where preco.preco_id = arg_preco_id
$$;


--
-- Name: produto; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE produto (
    produto_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    produto_categoria_id uuid NOT NULL,
    produto_colaborador_id uuid NOT NULL,
    produto_colaborador_atualizacao uuid,
    produto_nome character varying(32) NOT NULL,
    produto_stock numeric DEFAULT 0 NOT NULL,
    produto_stockminimo numeric DEFAULT 0 NOT NULL,
    produto_servicocliente boolean DEFAULT true NOT NULL,
    produto_servicofornecedor boolean DEFAULT true NOT NULL,
    produto_servicoproducao boolean DEFAULT true NOT NULL,
    produto_servicostockdinamico boolean DEFAULT true NOT NULL,
    produto_servicostockdinamiconegativo boolean DEFAULT false NOT NULL,
    produto_estado smallint DEFAULT 1,
    produto_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    produto_dataatualizacao timestamp without time zone,
    CONSTRAINT ck_produto_nome_is_normalizede CHECK (lib.is_normalized((produto_nome)::text))
);


--
-- Name: get_produto(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_produto(arg_produto_id uuid) RETURNS produto
    LANGUAGE sql
    AS $$
select *
      from produto where produto_id = arg_produto_id;
$$;


--
-- Name: setor; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE setor (
    setor_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    setor_setor_id uuid,
    setor_colaborador_id uuid NOT NULL,
    setor_colaborador_atualizacao uuid,
    setor_nome character varying(32) NOT NULL,
    setor_posicao smallint,
    setor_totalsubsetores smallint DEFAULT 0 NOT NULL,
    setor_quantidadetotalproduzida numeric DEFAULT 0 NOT NULL,
    setor_estado smallint DEFAULT 1,
    setor_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    setor_dataatualizacao timestamp without time zone,
    CONSTRAINT ck_setor_nome_is_normalized CHECK (lib.is_normalized((setor_nome)::text))
);


--
-- Name: COLUMN setor.setor_setor_id; Type: COMMENT; Schema: ggviario; Owner: -
--

COMMENT ON COLUMN setor.setor_setor_id IS 'Identificacao do setor parente';


--
-- Name: COLUMN setor.setor_estado; Type: COMMENT; Schema: ggviario; Owner: -
--

COMMENT ON COLUMN setor.setor_estado IS '<h2>Estado dos setoree<\h2>
<ul>
  <li> 2 - Protegido<\li>
  <li> 1 - Atvivo<\li>
  <li> 0 - Fechado<\li>
<\ul>';


--
-- Name: get_setor(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_setor(arg_setor_id uuid) RETURNS setor
    LANGUAGE sql
    AS $$
    select *
      from ggviario.setor 
      where setor_id = arg_setor_id;
  $$;


--
-- Name: tipocompra; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE tipocompra (
    tcompra_id smallint NOT NULL,
    tcompra_desc character varying(32) NOT NULL,
    CONSTRAINT fk_tcompra_desc_is_normalized CHECK (lib.is_normalized((tcompra_desc)::text))
);


--
-- Name: get_tipocompra(smallint); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_tipocompra(arg_tcompra_id smallint) RETURNS tipocompra
    LANGUAGE sql
    AS $$
  select * from ggviario.tipocompra tco where tco.tcompra_id = arg_tcompra_id;
$$;


--
-- Name: unidade; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE unidade (
    unidade_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    unidade_colaborador_id uuid NOT NULL,
    unidade_colaborador_atualizacao uuid,
    unidade_nome character varying(32) NOT NULL,
    unidade_codigo character varying(8) NOT NULL,
    unidade_estado smallint DEFAULT 1 NOT NULL,
    unidade_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    unidade_dataatualizacao timestamp without time zone,
    CONSTRAINT ck_unidade_codigo_is_normalized CHECK (lib.is_normalized((unidade_codigo)::text)),
    CONSTRAINT ck_unidade_nome_is_normalized CHECK (lib.is_normalized((unidade_nome)::text))
);


--
-- Name: get_unidade(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_unidade(arg_unidade_id uuid) RETURNS unidade
    LANGUAGE sql
    AS $$
select *
from ggviario.unidade
where unidade_id = arg_unidade_id;
$$;


SET search_path = lib, pg_catalog;

--
-- Name: age(timestamp without time zone); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION age(timestamp without time zone) RETURNS integer
    LANGUAGE sql
    AS $_$ select cast(to_char(age( $1 ), 'yyyy') as integer); $_$;


--
-- Name: agg_collect_collector(anyarray, anyelement, boolean); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION agg_collect_collector(collect_list anyarray, collect_next anyelement, collect_accepetnull boolean DEFAULT false) RETURNS anyarray
    LANGUAGE plpgsql
    AS $$
declare
begin
  if collect_list is null then collect_list := '{}'; end if;
  if collect_accepetnull is null then collect_accepetnull := false; end if;

  if collect_accepetnull or collect_next is not null then
    collect_list := collect_list || collect_next;
  end if;
  return collect_list;
end;
$$;


--
-- Name: agg_extreme_collector(anyelement, anyelement, boolean); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION agg_extreme_collector(acumulatecollect anyelement, nextcollect anyelement, first boolean) RETURNS anyelement
    LANGUAGE plpgsql
    AS $$
declare
begin
  if first then
    return case
           when acumulatecollect is null then nextcollect
           else acumulatecollect
           end;
  elseif not first then
    return case
           when nextcollect is null then acumulatecollect
           else nextcollect
           end;
  end if;
  return acumulatecollect;
end;
$$;


--
-- Name: agg_jsonb_collect_collector(jsonb, jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION agg_jsonb_collect_collector(acumulatecollect jsonb, nextcollect jsonb) RETURNS jsonb
    LANGUAGE plpgsql
    AS $$
declare

begin
  if acumulatecollect is null then
    acumulatecollect := '[]'::jsonb;
  end if;
  if nextcollect is not null then
    acumulatecollect := acumulatecollect || nextcollect;
  END IF;

  return acumulatecollect;
end;
$$;


--
-- Name: agg_jsonb_collect_finalize(jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION agg_jsonb_collect_finalize(acumulate jsonb) RETURNS jsonb
    LANGUAGE plpgsql
    AS $$
declare
begin
  if acumulate is null then
    acumulate := '[]'::jsonb;
  end if;

  return acumulate;
end;
$$;


--
-- Name: agg_sum_nonnull_collector(anyelement, anyelement); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION agg_sum_nonnull_collector(acumulate anyelement, nextval anyelement) RETURNS anyelement
    LANGUAGE plpgsql
    AS $$
declare
begin
  if acumulate is null and nextval is null then
    acumulate := 0;
  elseif acumulate is null then
    acumulate := nextval;
  elseif nextval is null then
    acumulate := acumulate;
  else
    acumulate := acumulate + nextval;
  end if;

  return acumulate;
end;
$$;


--
-- Name: agg_sum_nonnull_finalizer(anyelement); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION agg_sum_nonnull_finalizer(acumulate anyelement) RETURNS anyelement
    LANGUAGE sql
    AS $$
select
  case when acumulate is null then 0 else acumulate end;
$$;


--
-- Name: array_build(anyarray); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION array_build(VARIADIC argumests anyarray) RETURNS anyarray
    LANGUAGE sql
    AS $$
select argumests;
$$;


--
-- Name: array_build_nomnull(anyarray); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION array_build_nomnull(VARIADIC anyarray) RETURNS anyarray
    LANGUAGE sql
    AS $_$
select array_remove( $1, null );
$_$;


--
-- Name: array_equals_ignore_position(anyarray, anyarray); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION array_equals_ignore_position(arg_left anyarray, arg_right anyarray) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
declare
begin
  if arg_left is null or arg_right is null then return false; end if;
  if array_length( arg_left, 1 ) != array_length( arg_right, 1 ) then return false; end if;
  for iterator in 1..array_length( arg_left, 1 ) loop
    if not arg_left[ iterator ] = any( arg_right ) then return false; end if;
  end loop;
  return true;
end;
$$;


--
-- Name: array_length(anyarray); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION array_length(anyarray) RETURNS integer
    LANGUAGE plpgsql
    AS $_$
declare
  length integer := array_length( $1, 1 );
begin
  if length is null then
    length := 0;
  end if;
  return length;
end;
$_$;


--
-- Name: array_max_element(anyarray, anyelement); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION array_max_element(arr anyarray, element anyelement DEFAULT NULL::unknown) RETURNS anyelement
    LANGUAGE plpgsql
    AS $$
declare

begin
  element := null;
  for iterator in 1.. lib.array_length( arr ) loop
    if arr[ iterator ]  is not null and element is null or element < arr[ iterator ] then
      element := arr [ iterator ];
    end if;
  end loop;

  return element;
end;
$$;


--
-- Name: array_min_element(anyarray, anyelement); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION array_min_element(arr anyarray, element anyelement DEFAULT NULL::unknown) RETURNS anyelement
    LANGUAGE plpgsql
    AS $$
declare

begin
  element := null;
  for iterator in 1 .. lib.array_length( arr ) loop
    if arr[ iterator ]  is not null and element is null or element > arr[ iterator ] then
      element := arr [ iterator ];
    end if;
  end loop;

  return element;
end;
$$;


--
-- Name: array_remove_at(anyarray, integer); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION array_remove_at(arr anyarray, arr_start_index integer) RETURNS anyarray
    LANGUAGE sql
    AS $$
select lib.array_remove_range( arr, arr_start_index, 1 );
$$;


--
-- Name: array_remove_range(anyarray, integer, integer); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION array_remove_range(arr anyarray, arr_start_index integer, arr_count_elements integer) RETURNS anyarray
    LANGUAGE sql
    AS $$
select arr[:arr_start_index -1 ] || arr[ (arr_start_index + arr_count_elements):];
$$;


--
-- Name: coincidences(anyarray, anyarray, jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION coincidences(arguments_left anyarray, arguments_right anyarray, option jsonb DEFAULT NULL::jsonb) RETURNS numeric
    LANGUAGE plpgsql
    AS $$
declare

  --     iterator_left numeric default 1;

  -- text_left text;
  -- text_iterator text;
  coincidences_match_found numeric default 0;
  coincidences_total numeric;
begin
  coincidences_total := array_length( arguments_right, 1 );

  if arguments_left is null
     or coincidences_total = 0
     or arguments_right is null
     or array_length( arguments_right, 1 ) = 0
  then
    return 0;
  end if;


  for iterator_left in 1 .. array_length( arguments_left, 1 ) loop
    if  arguments_left[ iterator_left ] = any ( arguments_right ) then
      coincidences_match_found := coincidences_match_found + 1;
    end if;
  end loop;
  return ( 100 * coincidences_match_found ) / coincidences_total;
end;
$$;


--
-- Name: coincidences_likes(text[], text[], jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION coincidences_likes(arguments_left text[], arguments_right text[], option jsonb DEFAULT NULL::jsonb) RETURNS numeric
    LANGUAGE plpgsql
    AS $$
declare

  --     iterator_left numeric default 1;

  -- text_left text;
  -- text_iterator text;
  coincidences_match_found numeric default 0;
  coincidences_total numeric;
begin
  coincidences_total := array_length( arguments_right, 1 );

  if arguments_left is null
     or coincidences_total = 0
     or arguments_right is null
     or array_length( arguments_right, 1 ) = 0
  then
    return 0;
  end if;


  for iterator_left in 1 .. array_length( arguments_left, 1 ) loop
    if  arguments_left[ iterator_left ] like any ( arguments_right ) then
      coincidences_match_found := coincidences_match_found + 1;
    end if;
  end loop;

  return ( 100 * coincidences_match_found ) / coincidences_total;
end;
$$;


--
-- Name: concat(anyelement, anyelement); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION concat(anyelement, anyelement) RETURNS text
    LANGUAGE sql
    AS $_$ select coalesce( $1, '' )||coalesce( $2, '' )$_$;


--
-- Name: dblink_connect(character varying, character varying, character varying, character varying, character varying, numeric, jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION dblink_connect(connectionname character varying, username character varying, passwordtext character varying, databasename character varying, hostname character varying DEFAULT '::0'::character varying, port numeric DEFAULT 5432, options jsonb DEFAULT NULL::jsonb) RETURNS boolean
    LANGUAGE plpgsql
    AS $_$
declare
  connection_url text default  'hostaddr=$host port=$port dbname=$database user=$user password=$pwd';
begin
  if connectionname = any ( public.dblink_get_connections() ) then
    return true;
  end if;

  connection_url := lib.replace( connection_url,

                                 '$host', hostname,
                                 '$port', port::text,
                                 '$database', databasename,
                                 '$user', username,
                                 '$pwd', passwordtext
  );

  perform public.dblink_connect( connectionname, connection_url );
  return true;

  exception when others then return false;
end;
$_$;


--
-- Name: dblink_disconnect(character varying, jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION dblink_disconnect(connectionname character varying, option jsonb DEFAULT NULL::jsonb) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
declare
begin
  if not connectionname = any ( public.dblink_get_connections() ) then
    return true;
  end if;

  perform public.dblink_disconnect( connectionname );
  return true;

  exception when others then return false;
end;

$$;


--
-- Name: define_session_values(character varying, character varying, jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION define_session_values(variablegroup character varying, variablename character varying, variablevalue jsonb) RETURNS void
    LANGUAGE plpgsql
    AS $$
declare
begin
  PERFORM set_config( variablegroup||'.'||variablename, variablevalue::text, false  );
end;
$$;


--
-- Name: dset_random_next(anyelement); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION dset_random_next(anyelement) RETURNS anyelement
    LANGUAGE plpgsql
    AS $_$
declare
  arg_next numeric default ( random() * $1);
begin
  if arg_next = 0  then return lib.dset_random_next( $1 ); end if;
  return arg_next;
end;
$_$;


--
-- Name: dset_random_next(anyelement, anyelement); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION dset_random_next(arg_min anyelement, arg_max anyelement) RETURNS anyelement
    LANGUAGE plpgsql
    AS $$
declare
  max numeric default case when abs( arg_max ) > abs( arg_min ) then abs( arg_max ) else abs( arg_min ) end;
  arg_sigal int default case when lib.dset_random_next( 100 ) <= 50 then -1 else 1 end;
  generated numeric default ( random() * max) * arg_sigal;
begin
  if arg_min > arg_max then return null; end if;

  if generated < arg_min  or generated > arg_max then return lib.dset_random_next( arg_min, arg_max ); end if;
  return generated;
end;
$$;


--
-- Name: dset_random_nextelement(anyarray); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION dset_random_nextelement(anyarray) RETURNS anyelement
    LANGUAGE sql
    AS $_$
select $1[ lib.dset_random_next( array_length( $1, 1) )]
$_$;


--
-- Name: dset_random_nexttext(numeric); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION dset_random_nexttext(text_lenght numeric) RETURNS text
    LANGUAGE sql
    AS $$
select
  array_to_string(
      array(
          select
            chr((65 + round(random() * 25)) :: integer)
          from generate_series(1, text_lenght)
      ), ''
  );
$$;


--
-- Name: element_different(anyelement, anyelement); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION element_different(anyelement, anyelement) RETURNS boolean
    LANGUAGE sql
    AS $_$select not lib.element_equals( $1, $2 )$_$;


--
-- Name: element_equals(anyelement, anyelement); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION element_equals(anyelement, anyelement) RETURNS boolean
    LANGUAGE sql
    AS $_$
select case
       when $1 is null and $2 is null then true
       when $1 is not null and $2 is not null then $1 = $2
       else false
       end
$_$;


--
-- Name: field_bit(jsonb, integer); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_bit(data jsonb, index integer) RETURNS bit
    LANGUAGE sql IMMUTABLE
    AS $_$
select ( data->>$2 )::bit
$_$;


--
-- Name: field_bit(jsonb, text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_bit(data jsonb, field text) RETURNS bit
    LANGUAGE sql IMMUTABLE
    AS $_$
select ( data->>$2 )::bit
$_$;


--
-- Name: field_boolean(jsonb, integer); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_boolean(data jsonb, index integer) RETURNS boolean
    LANGUAGE plpgsql IMMUTABLE
    AS $_$
begin
  if ( data->>$2 ) :: boolean then return true; end if;
  return false;
  exception
  when others then return false;
end;
$_$;


--
-- Name: field_boolean(jsonb, text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_boolean(data jsonb, filed text) RETURNS boolean
    LANGUAGE plpgsql IMMUTABLE
    AS $_$
begin
  if ( data->>$2 ) :: boolean then return true; end if;
  return false;
  exception
  when others then return false;
end;
$_$;


--
-- Name: field_date(jsonb, integer); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_date(data jsonb, index integer) RETURNS date
    LANGUAGE sql IMMUTABLE
    AS $_$
select ( data->>$2 )::date
$_$;


--
-- Name: field_date(jsonb, text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_date(data jsonb, field text) RETURNS date
    LANGUAGE sql IMMUTABLE
    AS $_$
select ( data->>$2 )::date
$_$;


--
-- Name: field_numeric(jsonb, integer); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_numeric(data jsonb, index integer) RETURNS numeric
    LANGUAGE sql IMMUTABLE
    AS $_$
select ( data->>$2 )::numeric
$_$;


--
-- Name: field_numeric(jsonb, text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_numeric(data jsonb, filed text) RETURNS numeric
    LANGUAGE sql IMMUTABLE
    AS $_$
select ( data->>$2 )::numeric
$_$;


--
-- Name: field_timestamp(jsonb, integer); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_timestamp(data jsonb, index integer) RETURNS timestamp without time zone
    LANGUAGE sql IMMUTABLE
    AS $_$
select ( data->>$2 )::timestamp
$_$;


--
-- Name: field_timestamp(jsonb, text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_timestamp(data jsonb, field text) RETURNS timestamp without time zone
    LANGUAGE sql IMMUTABLE
    AS $_$
select ( data->>$2 )::timestamp
$_$;


--
-- Name: field_timestamp(jsonb, character varying); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_timestamp(data jsonb, filed character varying) RETURNS character varying
    LANGUAGE sql IMMUTABLE
    AS $_$
select ( data->>$2 )::character varying
$_$;


--
-- Name: field_varchar(jsonb, integer); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_varchar(data jsonb, index integer) RETURNS character varying
    LANGUAGE sql IMMUTABLE
    AS $_$
select ( data->>$2 )::character varying
$_$;


--
-- Name: field_varchar(jsonb, text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION field_varchar(data jsonb, filed text) RETURNS character varying
    LANGUAGE sql IMMUTABLE
    AS $_$
select ( data->>$2 )::character varying
$_$;


--
-- Name: get_session_value(character varying, character varying); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION get_session_value(variablegroup character varying, variablename character varying) RETURNS text
    LANGUAGE sql
    AS $$
select current_setting( variablegroup||'.'||variablename );
$$;


--
-- Name: html_table(jsonb, text[], jsonb, jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION html_table(table_body_datasource jsonb, extra_columns text[], columns_map jsonb, styles jsonb DEFAULT NULL::jsonb) RETURNS xml
    LANGUAGE plpgsql
    AS $_$
declare
  /*
    --STYLES--
    {
      table:{ ... },
      table_header_tr:{ ... },
      table_header_tr_th:{ ... },
      table_body_th:{ ... },
      table_body_td: { ... },
      use_td_as_title:true|false,
      use_th_as_title:true|false,
    }
   */
  xmlColumns xml;
  xmlUTF8 xml default $$<head><meta charset="UTF-8"/></head>$$::xml;
begin
  xmlColumns :=(
    select
      xmlelement(
          name tr,
          xmlattributes(
          styles->>'table_header_tr' as style
          ),
          xmlagg(
              xmlelement(
                  name th,
                  xmlattributes(
                  styles->>'table_header_tr_th'  as style,
                  case
                  when ( styles->>'use_th_as_title')::boolean then lib.html_table_column( columns_map, columns.column_name )
                  else ''
                  end as title
                  ),
                  lib.html_table_column( columns_map, columns.column_name )
              )
          )
      )
    from unnest( extra_columns ) with ordinality as columns( column_name, column_position )
  );

  return (
    with xml_servico_extra as (
        select
          xmlelement(
              name tr,
              xmlattributes(
              styles->>'table_body_tr'  as style
              ),
              xmlagg(
                  xmlelement(
                      name td,
                      xmlattributes(
                      styles->>'table_body_tr_td' as style,
                      case
                      when ( styles->>'use_td_as_title' )::boolean then lib.html_table_value( js.json_serico, columns_map, columns.column_name )
                      else ''
                      end as title
                      ),
                      lib.html_table_value( js.json_serico, columns_map, columns.column_name )
                  )
              )
          ) as tbody
        from jsonb_array_elements(  table_body_datasource ) js ( json_serico )
          inner join unnest( extra_columns ) with ordinality as columns( column_name, column_position ) on true
        group by js.json_serico
    )
    select
      xmlelement(
          name html,
          xmlUTF8,
          xmlelement(
              name body,
              xmlelement(
                  name table,
                  xmlattributes(
                  styles->>'table'  as style
                  ),
                  xmlColumns,
                  xmlagg( xml.tbody )
              )
          )
      )
    from xml_servico_extra xml
  );
end;
$_$;


--
-- Name: html_table_column(jsonb, text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION html_table_column(columns_map jsonb, column_name text) RETURNS text
    LANGUAGE plpgsql IMMUTABLE
    AS $$
begin
  return coalesce(
      columns_map ->( column_name )->>'column_name',
      ''
  );
end
$$;


--
-- Name: html_table_value(jsonb, jsonb, text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION html_table_value(row_source jsonb, columns_map jsonb, column_name text) RETURNS text
    LANGUAGE plpgsql IMMUTABLE
    AS $$
begin
  return coalesce(
      row_source ->( columns_map ->( column_name ) ->>'table' )->>( ( columns_map->( column_name ) ->>'column' ) ),
      ''
  );
end
$$;


--
-- Name: increment(double precision); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION increment(INOUT argment double precision) RETURNS double precision
    LANGUAGE plpgsql
    AS $$
BEGIN
  argment := argment + 1;
END;
$$;


--
-- Name: increment(numeric); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION increment(numeric) RETURNS numeric
    LANGUAGE sql
    AS $_$
SELECT  $1 + 1;
$_$;


--
-- Name: initials(character varying); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION initials(name character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare
  parts character varying [] default regexp_split_to_array(name, ' ');
  part character varying;
  initials character varying default '';
begin
  FOREACH part in ARRAY parts LOOP
    part := upper(trim(part));
    if part is not null and length(part)> 0 then
      initials := initials || substr(part, 1, 1);
    END IF;
  END LOOP;
  return initials;
end;
$$;


--
-- Name: is_bigint(text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION is_bigint(argment text) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
declare
  num numeric;
begin
  num := argment::BIGINT;
  return true;
  exception when OTHERS  then return false;
end;

$$;


--
-- Name: is_integer(text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION is_integer(argment text) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
declare
  num numeric;
begin
  num := argment::integer;
  return true;
  exception when OTHERS  then return false;
end;

$$;


--
-- Name: is_numeric(text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION is_numeric(argment text) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
declare
  num numeric;
begin
  num := argment::numeric;
  return true;
  exception when OTHERS  then return false;
end;

$$;


--
-- Name: is_numeric_sequence(text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION is_numeric_sequence(argment text) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
declare
  list character [] default regexp_split_to_array( argment, '');
  nums character [] default regexp_split_to_array( '0123456789', '');
  c character;
begin
  foreach c in array list loop
    if not ( c = any ( nums ) ) then
      return false;
    end if;
  end loop;

  return true;
end;

$$;


--
-- Name: is_valid_nif(text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION is_valid_nif(arg_nif text) RETURNS boolean
    LANGUAGE sql
    AS $$
select arg_nif is not null
      and substr( arg_nif, 1, 3 ) in ( '109' )
      and length( arg_nif ) = 9
      and lib.is_numeric_sequence( arg_nif )
$$;


--
-- Name: jsonb_object_length(jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION jsonb_object_length(object jsonb) RETURNS integer
    LANGUAGE plpgsql
    AS $$

declare
  iCount int;
  objType text default jsonb_typeof( object );
begin
  if objType is null or objType != 'object' then return null; end if;
  select count(*)  into iCount
  from jsonb_object_keys(object);
  return iCount;
end;
$$;


--
-- Name: jsonb_to_array(jsonb, anyarray); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION jsonb_to_array(argument jsonb, INOUT anyarray DEFAULT '{}'::text[]) RETURNS anyarray
    LANGUAGE plpgsql
    AS $_$
declare
  -- result anyelement;
begin
  if pg_typeof ( $2 ) in ( 'jsonb[]'::regtype, 'json[]'::regtype ) then
    $2 := (
      select  array_agg (
          element::anyelement
      )
      from  jsonb_array_elements( argument ) element
    );

  else
    $2 := (
      select  array_agg (
          element::anyelement
      )
      from  jsonb_array_elements_text( argument ) element
    );
  end if;

end;
$_$;


--
-- Name: money(anyelement); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION money(moneyvalue anyelement) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare
  vMoney character varying;
  positive boolean default moneyvalue >= 0;
begin
  if moneyvalue is null then return null; end if;
  moneyvalue := abs( moneyvalue );
  vMoney := to_char(moneyValue, 'FM999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999G999D90');
  vMoney := replace( vMoney, ',', ' ' );
  vMoney := replace( vMoney, '.', ',' );

  if not positive then
    vMoney := '-'||vMoney;
  end if;

  return lib.normalize( vMoney );
END;

$$;


--
-- Name: normalize_match(text, jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION normalize_match(argument text, option jsonb DEFAULT NULL::jsonb) RETURNS text
    LANGUAGE plpgsql
    AS $$
declare
  text_match text default '';
  text_point text;
  text_argument text[ ];
  text_add text [ ] default '{}'::text[];
  iterator numeric default 0;
  option_append text default option->>'append';
  option_prepend text default option->>'prepend';
  option_minlenght numeric default option->>'min-length';
begin
  if argument is null then return null; end if;
  argument := lib.normalize( argument );
  text_argument := regexp_split_to_array( argument, ' ' );

  if text_argument is null
     or array_length( text_argument, 1) = 0
  then
    return null;
  end if;

  for iterator in 1 .. array_length( text_argument, 1) loop

    text_point := text_argument[ iterator ];
    if option_minlenght is not null and length( text_point ) < option_minlenght then continue; end if;

    text_point := lib.normalize(  option_prepend ||| text_point||| option_append );

    if text_point is null then continue; end if;

    if not text_point = any ( text_add ) then
      text_match := text_match || ' '|| text_point;
      text_add := text_add || text_point;
    end if;

  end loop;

  return text_match;
end;
$$;


--
-- Name: random_text(numeric); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION random_text(text_lenght numeric) RETURNS text
    LANGUAGE sql
    AS $$
select
  array_to_string(
      array(
          select
            chr((65 + round(random() * 25)) :: integer)
          from generate_series(1, text_lenght)
      ), ''
  );
$$;


--
-- Name: result(boolean, jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION result(result boolean, content jsonb) RETURNS result
    LANGUAGE plpgsql
    AS $$
DECLARE
  res lib.result;
BEGIN
  res.result = result ;
  res.message = content;
  RETURN res;
END;
$$;


--
-- Name: result_false(jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION result_false(content jsonb) RETURNS result
    LANGUAGE plpgsql
    AS $$
DECLARE
  res lib.result;
BEGIN
  res.result = FALSE ;
  res.message = content;
  RETURN res;
END;
$$;


--
-- Name: result_false(text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION result_false(message text) RETURNS result
    LANGUAGE plpgsql
    AS $$
DECLARE
BEGIN
  RETURN lib.result_false(
      jsonb_build_object( 'text', message::text)
  );
END;
$$;


--
-- Name: result_true(); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION result_true() RETURNS result
    LANGUAGE sql
    AS $$
select lib.result_true( 'true'  );
$$;


--
-- Name: result_true(jsonb); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION result_true(content jsonb) RETURNS result
    LANGUAGE plpgsql
    AS $$
BEGIN
  return lib.result(
      true,
      content
  );
END;
$$;


--
-- Name: result_true(text); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION result_true(message text) RETURNS result
    LANGUAGE plpgsql
    AS $$
BEGIN
  return lib.result_true(
      jsonb_build_object( 'text', message::text)
  );
END;
$$;


--
-- Name: split_name(character varying); Type: FUNCTION; Schema: lib; Owner: -
--

CREATE FUNCTION split_name(name character varying) RETURNS person
    LANGUAGE plpgsql
    AS $$
DECLARE
  nameParts text [];
  vPerson lib.person;
  i integer;
BEGIN
  nameParts := regexp_split_to_array( name, ' ' );
  vPerson.firstname = nameParts[1];
  vPerson.lastname = nameParts[ array_length( nameParts, 1)];
  vPerson.mediumname = null;
  if array_length( nameParts, 1 ) > 2 then
    vPerson.mediumname = '';
    for i in 2 .. array_length( nameParts, 1 )-1 loop
      vPerson.mediumname = vPerson.mediumname || nameParts[i];
      if i < array_length( nameParts, 1 ) - 1 then vPerson.mediumname = vPerson.mediumname || ' '; end if;
    end loop;
  END IF;
  RETURN vPerson;

END;
$$;


SET search_path = ggviario, pg_catalog;

--
-- Name: cliente; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE cliente (
    cliente_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    cliente_sexo_id smallint,
    cliente_distrito_id smallint,
    cliente_tdocumento_id smallint,
    cliente_colaborador_id uuid,
    cliente_colaborador_atualizacao uuid,
    cliente_documentonumero character varying(32),
    cliente_nome character varying(48) NOT NULL,
    cliente_apelido character varying(48),
    cliente_datanascimento date,
    cliente_telefone character varying(15),
    cliente_telemovel character varying(15),
    cliente_mail character varying(32) DEFAULT NULL::character varying,
    cliente_morada character varying(48),
    cliente_localtrabalho character varying(64),
    cliente_estado smallint DEFAULT 1 NOT NULL,
    cliente_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    cliente_dataatualizacao timestamp without time zone,
    CONSTRAINT ck_cliente_apelido_is_normalized CHECK (lib.is_normalized((cliente_apelido)::text)),
    CONSTRAINT ck_cliente_documentonumero_is_normalized CHECK (lib.is_normalized((cliente_documentonumero)::text)),
    CONSTRAINT ck_cliente_morada_is_normalized CHECK (lib.is_normalized((cliente_morada)::text)),
    CONSTRAINT ck_cliente_nome_is_normalized CHECK (lib.is_normalized((cliente_nome)::text)),
    CONSTRAINT ck_cliente_telefone_is_normalized CHECK (lib.is_normalized((cliente_telefone)::text)),
    CONSTRAINT ck_cliente_telemovel_is_mail CHECK ((lib.is_normalized((cliente_mail)::text) AND (lower((cliente_mail)::text) = (cliente_mail)::text))),
    CONSTRAINT ck_cliente_telemovel_is_normalized CHECK (lib.is_normalized((cliente_telemovel)::text))
);


--
-- Name: COLUMN cliente.cliente_localtrabalho; Type: COMMENT; Schema: ggviario; Owner: -
--

COMMENT ON COLUMN cliente.cliente_localtrabalho IS 'O local em que o cliente trabalha';


--
-- Name: COLUMN cliente.cliente_datanascimento; Type: COMMENT; Schema: ggviario; Owner: -
--

COMMENT ON COLUMN cliente.cliente_datanascimento IS 'A data em que o cliente nasceu';


SET search_path = rule, pg_catalog;

--
-- Name: cliente_estado_desc(ggviario.cliente, constant); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION cliente_estado_desc(_cliente ggviario.cliente, _const constant DEFAULT constant_init()) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
begin
  if _const is null then _const := rule.constant_init(); end if;
  return case 
      when _cliente.cliente_estado = _const.cliente_estado_ativo then 'Ativo'
      when _cliente.cliente_estado = _const.cliente_estado_fechado then 'Fechado'
  end;
end;
$$;


--
-- Name: compra_estado_desc(ggviario.compra, constant); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION compra_estado_desc(_compra ggviario.compra, _const constant DEFAULT constant_init()) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare
begin
  if _const is null then _const := rule.constant_init(); end if;
  return case
         when _compra.compra_estado = _const.compra_estado_pendente then 'Pendente'
         when _compra.compra_estado = _const.compra_estado_feito then 'Feito'
         when _compra.compra_estado = _const.compra_estado_empagamento then 'Em pagamento'
         when _compra.compra_estado = _const.compra_estado_pago then 'Pago'
         when _compra.compra_estado = _const.compra_estado_anulado then 'Anulado'
  end;
end;
$$;


--
-- Name: compra_fatura_generatenext(ggviario.tipocompra); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION compra_fatura_generatenext(_tipocompra ggviario.tipocompra) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare
begin
  return nextval( 'ggviario.seq_faturanumero' )::character varying;
end;
$$;


--
-- Name: compra_insert(uuid, uuid, uuid, uuid, uuid, numeric, numeric, numeric, numeric, numeric, date, date); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION compra_insert(arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_tcompra_id uuid, arg_colaborador_id uuid, arg_compra_quantidade numeric, arg_compra_custounitario numeric, arg_compra_custobruto numeric, arg_compra_custodesconto numeric, arg_compra_custopagar numeric, arg_compra_data date, arg_compra_datafinalizar date) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  arg_faturanumero character varying;
  arg_compra_quantidadeproduto numeric;
  _compra ggviario.compra;
  _equivalencia ggviario.equivalencia;
  _const rule.constant;
  _preco ggviario.preco;
  _tipocompra ggviario.tipocompra;
begin
  _const := rule.constant_init();
  _tipocompra := ggviario.get_tipocompra( arg_tcompra_id );
  select * into _equivalencia
    from ggviario.equivalencia eq
    where eq.equivalencia_produto_id = arg_produto_id
      and eq.equivalencia_unidade_id = arg_unidade_id
      and eq.equivalencia_estado = _const.equivalencia_estado_ativo
    order by coalesce( eq.equivalencia_dataatualizacao, eq.equivalencia_dataregisto ) desc
  ;

  select * into _preco
    from ggviario.preco pre
    where pre.preco_produto_id = arg_produto_id
      and pre.preco_unidade_id = arg_unidade_id
      and pre.preco_estado = _const.preco_estado_ativo
    order by coalesce( pre.preco_dataatualizacao, pre.preco_dataregisto ) desc
  ;

  if _equivalencia is null then
    return lib.result_false( 'Não foi encontrado nenhuma equivalencia do produto para a unidade!'  );
  end if;

  if _preco is null then
    return lib.result_false( 'Não foi encontrado nenhum preço ativo para a unidade do produto!');
  end if;

  arg_faturanumero := rule.compra_fatura_generatenext( _tipocompra );
  arg_compra_quantidadeproduto := arg_compra_quantidade * _equivalencia.equivalencia_quantidade;

  insert into ggviario.compra(
    compra_produto_id,
    compra_unidade_id,
    compra_cliente_id,
    compra_tcompra_id,
    compra_colaborador_id,
    compra_faturanumero,
    compra_quantidade,
    compra_quantidadeproduto,
    compra_custounitario,
    compra_custobruto,
    compra_custodesconto,
    compra_custopagar,
    compra_data,
    compra_datafinalizar,
    compra_estado
  ) values (
    arg_produto_id,
    arg_unidade_id,
    arg_cliente_id,
    arg_tcompra_id,
    arg_colaborador_id,
    arg_faturanumero,
    arg_compra_quantidade,
    arg_compra_quantidadeproduto,
    arg_compra_custounitario,
    arg_compra_custobruto,
    arg_compra_custodesconto,
    arg_compra_custopagar,
    arg_compra_data,
    arg_compra_datafinalizar,
    _const.compra_estado_feito
  ) returning * into _compra;

  return lib.result_true(
    jsonb_build_object(
      'compra', _compra
    )
  );
end;
$$;


--
-- Name: despesa_estado_desc(ggviario.despesa, constant); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION despesa_estado_desc(_despesa ggviario.despesa, _const constant) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
  begin
    return case 
        when _despesa.despesa_estado = _const.despesa_estado_pendente then 'Pendente'
        when _despesa.despesa_estado = _const.despesa_estado_empagamento then 'Em pagamento'
        when _despesa.despesa_estado = _const.despesa_estado_pago then 'Pago'
        when _despesa.despesa_estado = _const.despesa_estado_anulado then 'Anulado'
      end;
  end;  
$$;


--
-- Name: functg_compra_intert_update_produto_stock(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_compra_intert_update_produto_stock() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
    _new ggviario.compra;
    _old ggviario.compra;
    _const rule.constant;
    _produto ggviario.produto;
  begin
    _const := rule.constant_init();
    _new := new;
    _old := old;

    -- marcar os itens da compra como feito!
    update ggviario.produto
      set produto_stock = produto_stock - _old.compra_quantidadeproduto ,
          produto_colaborador_atualizacao = _new.compra_colaborador_id ,
          produto_dataatualizacao = current_timestamp
      where produto_id = _old.compra_produto_id
    ;

    return null;
  end;
$$;


--
-- Name: functg_despesa_after_insert_update_stock(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_despesa_after_insert_update_stock() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
  _new ggviario.despesa;
  _produto ggviario.produto;
  _equivalencia ggviario.equivalencia;
begin
  _new := new;

  _produto := ggviario.get_produto( _new.despesa_produto_id );
  if _produto.produto_servicostockdinamico then

    update ggviario.produto
      set produto_stock = produto_stock + _new.despesa_quantidadeproduto,
          produto_colaborador_atualizacao = _new.despesa_colaborador_id,
          produto_dataatualizacao = _new.despesa_dataregisto
      where produto_id = _new.despesa_produto_id
    ;
    
  end if;
  
  return null;
end;
$$;


--
-- Name: functg_movimento_after_insert_update_conta(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_movimento_after_insert_update_conta() RETURNS trigger
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


--
-- Name: functg_movimento_after_insert_update_itemcompra(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_movimento_after_insert_update_itemcompra() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
  _new ggviario.movimento;
  _const rule.constant;
  _itemcompra ggviario.compra;

begin
  _const := rule.constant_init();
  _new := new;

  if _new.movimento_compra_id is not null then
    
    _itemcompra := ggviario.get_compra( _new.movimento_compra_id );
    _itemcompra.compra_estado := _const.compra_estado_empagamento;
    
    if _itemcompra.compra_custoamortizado + _new.movimento_montante >= _itemcompra.compra_custopagar then
      _itemcompra.compra_estado := _const.compra_estado_pago;
      _itemcompra.compra_datafim = current_timestamp;
    end if;
    
    if coalesce( _itemcompra.compra_dataultimamovimentacao, _new.movimento_data - interval '1' day ) < _new.movimento_data then
      _itemcompra.compra_dataultimamovimentacao := _new.movimento_data;
    end if;

    
    update ggviario.compra
      set compra_estado = _itemcompra.compra_estado,
          compra_dataultimamovimentacao = _itemcompra.compra_dataultimamovimentacao,
          compra_custoamortizado = compra_custoamortizado + _new.movimento_montante,
          compra_colaborador_atualizacao = _new.movimento_colaborador_id,
          compra_dataatualizacao = current_timestamp,
          compra_datafim = _itemcompra.compra_datafim
      where compra_id = _itemcompra.compra_id;
    
  end if;

  return null;
end;
$$;


--
-- Name: functg_movimento_after_insert_update_movimento_devolucao(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_movimento_after_insert_update_movimento_devolucao() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
  _new ggviario.movimento;
  _const rule.constant;
  _movimento ggviario.movimento;

begin
  _const := rule.constant_init();
  _new := new;



  if _new.movimento_movimento_id is not null then
    _movimento := ggviario.get_movimento( _new.movimento_movimento_id );
    _movimento.movimento_estado := _const.movimento_estado_emdevolucao;
    
    if _movimento.movimento_devolucamontantedevolvido + _new.movimento_montante >= _movimento.movimento_montante then
      _movimento.movimento_estado := _const.movimento_estado_devolvido;
    end if;
    
    if coalesce( _movimento.movimento_devolucaoultimadada, _new.movimento_data - interval '1' day ) < _new.movimento_data then
      _movimento.movimento_devolucaoultimadada := _new.movimento_data;
    end if;
    
    update ggviario.movimento
      set movimento_estado = _movimento.movimento_estado,
          movimento_devolucaoultimadada = _movimento.movimento_devolucaoultimadada,
          movimento_devolucamontantedevolvido = movimento_devolucamontantedevolvido + _new.movimento_montante,
          movimento_colaborador_atualizacao = _new.movimento_colaborador_id,
          movimento_dataatualizacao = current_timestamp
      where movimento_id = _movimento.movimento_id;

  end if;
  
  return null;

end;
$$;


--
-- Name: functg_producao_after_insert_movement_sector_and_product(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_producao_after_insert_movement_sector_and_product() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
    _new ggviario.producao;
    _produto ggviario.produto;
  begin
    _new := new;
    _produto := ggviario.get_produto( _new.producao_produto_id );

    -- Para os produtos com stock dinimico aumentar o stock do produto
    if _produto.produto_servicostockdinamico then
      update ggviario.produto
        set produto_stock = produto_stock + _new.producao_quantidade,
            produto_colaborador_atualizacao = _new.producao_colaborador_id,
            produto_dataatualizacao = _new.producao_dataregisto
        where produto_id = _new.producao_produto_id
      ;
    end if;

    -- Incrementar a quantidade total produzida do setor
    update ggviario.setor
      set setor_quantidadetotalproduzida =  setor_quantidadetotalproduzida + _new.producao_quantidade,
          setor_colaborador_atualizacao = _new.producao_colaborador_id,
          setor_dataatualizacao = _new.producao_dataregisto
      where setor_id = _new.producao_setor_id
    ;

    return null;
  end;
$$;


--
-- Name: functg_setor_after_insert_protect_parent(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_setor_after_insert_protect_parent() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare 
  _new ggviario.setor;
  _setor_parent ggviario.setor;
  _const rule.constant;
begin 
  _const := rule.constant_init();
  _new := new;
  _setor_parent := ggviario.get_setor( _new.setor_setor_id );
  
  if _new.setor_setor_id is not null
  then 
    update ggviario.setor
      set setor_estado = _const.setor_estado_protegido,
          setor_totalsubsetores = setor_totalsubsetores + 1,
          setor_colaborador_atualizacao = _new.setor_colaborador_atualizacao,
          setor_dataatualizacao = _new.setor_dataregisto
      where setor_id = _new.setor_setor_id
    ;
  end if;
  
  return null;
end;
$$;


--
-- Name: functg_setor_after_update_update_cascade(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_setor_after_update_update_cascade() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
    _new ggviario.setor;
    _old ggviario.setor;

  begin
    _new := new;
    _old := old;

    -- Se ouver mudanca na quantidade produzida de um setor filho entao transferir tambem essa quantidade produzida para o setor pae
    if _new.setor_quantidadetotalproduzida != _old.setor_quantidadetotalproduzida
      and _old.setor_setor_id is not null
      and _old.setor_id != coalesce( _old.setor_setor_id, _old.setor_id )
    then
      update ggviario.setor
        set setor_quantidadetotalproduzida = setor_quantidadetotalproduzida + _new.setor_quantidadetotalproduzida - _old.setor_quantidadetotalproduzida,
            setor_colaborador_atualizacao = _new.setor_colaborador_atualizacao,
            setor_dataatualizacao = _new.setor_dataatualizacao
        where setor_id = _old.setor_setor_id
      ;
    end if;

    return null;
  end;
$$;


--
-- Name: movimento_check_source(ggviario.movimento); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION movimento_check_source(_movimento ggviario.movimento) RETURNS boolean
    LANGUAGE plpgsql IMMUTABLE
    AS $$
begin
  return true;
end;
$$;


--
-- Name: movimento_insert(uuid, uuid, smallint, character varying, date, numeric, character varying, uuid, uuid, uuid, boolean, integer); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION movimento_insert(arg_colaborador_id uuid, arg_conta_id uuid, arg_tmovimento_id smallint, arg_movimento_documento character varying, arg_movimento_data date, arg_movimento_montante numeric, arg_movimento_libele character varying, arg_itemcompra_id uuid DEFAULT NULL::uuid, arg_despesa_id uuid DEFAULT NULL::uuid, arg_movimento_id uuid DEFAULT NULL::uuid, arg_movimento_devolucao boolean DEFAULT false, arg_movimento_transferencianumero integer DEFAULT NULL::integer) RETURNS ggviario.movimento
    LANGUAGE sql
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


--
-- Name: produto_estado_desc(ggviario.produto, constant); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION produto_estado_desc(_produto ggviario.produto, _const constant) RETURNS character varying
    LANGUAGE sql
    AS $$
  select case 
      when _produto.produto_estado = _const.produto_estado_ativo then 'Ativo' 
      when _produto.produto_estado = _const.produto_estado_fechado then 'Fechado' 
    end
$$;


--
-- Name: setor_estado_desc(ggviario.setor, constant); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION setor_estado_desc(_setor ggviario.setor, _const constant) RETURNS character varying
    LANGUAGE sql
    AS $$
    select case
        when _setor.setor_estado = _const.setor_estado_ativo then 'Ativo'
        when _setor.setor_estado = _const.setor_estado_fechado then 'Fechado'
        when _setor.setor_estado = _const.setor_estado_protegido then 'Protegido'
      end;
  $$;


SET search_path = lib, pg_catalog;

--
-- Name: agg_collect(anyelement, boolean); Type: AGGREGATE; Schema: lib; Owner: -
--

CREATE AGGREGATE agg_collect(collect_next anyelement, collect_accepetnull boolean) (
    SFUNC = agg_collect_collector,
    STYPE = anyarray
);


--
-- Name: agg_extreme(anyelement, boolean); Type: AGGREGATE; Schema: lib; Owner: -
--

CREATE AGGREGATE agg_extreme(anyelement, boolean) (
    SFUNC = agg_extreme_collector,
    STYPE = anyelement
);


--
-- Name: agg_jsonb_collect(jsonb); Type: AGGREGATE; Schema: lib; Owner: -
--

CREATE AGGREGATE agg_jsonb_collect(jsonb) (
    SFUNC = agg_jsonb_collect_collector,
    STYPE = jsonb,
    FINALFUNC = agg_jsonb_collect_finalize
);


--
-- Name: agg_sum_nonnull(anyelement); Type: AGGREGATE; Schema: lib; Owner: -
--

CREATE AGGREGATE agg_sum_nonnull(anyelement) (
    SFUNC = agg_sum_nonnull_collector,
    STYPE = anyelement,
    FINALFUNC = agg_sum_nonnull_finalizer
);


--
-- Name: !; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ! (
    PROCEDURE = result,
    LEFTARG = boolean,
    RIGHTARG = jsonb
);


--
-- Name: !===; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR !=== (
    PROCEDURE = element_different,
    LEFTARG = anyelement,
    RIGHTARG = anyelement
);


--
-- Name: ->#; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR -># (
    PROCEDURE = field_numeric,
    LEFTARG = jsonb,
    RIGHTARG = text
);


--
-- Name: ->#; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR -># (
    PROCEDURE = field_numeric,
    LEFTARG = jsonb,
    RIGHTARG = integer
);


--
-- Name: ->%; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ->% (
    PROCEDURE = field_date,
    LEFTARG = jsonb,
    RIGHTARG = text
);


--
-- Name: ->%; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ->% (
    PROCEDURE = field_date,
    LEFTARG = jsonb,
    RIGHTARG = integer
);


--
-- Name: ->%%; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ->%% (
    PROCEDURE = field_timestamp,
    LEFTARG = jsonb,
    RIGHTARG = text
);


--
-- Name: ->%%; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ->%% (
    PROCEDURE = field_timestamp,
    LEFTARG = jsonb,
    RIGHTARG = integer
);


--
-- Name: ->>>; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ->>> (
    PROCEDURE = field_varchar,
    LEFTARG = jsonb,
    RIGHTARG = integer
);


--
-- Name: ->>>; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ->>> (
    PROCEDURE = field_varchar,
    LEFTARG = jsonb,
    RIGHTARG = text
);


--
-- Name: ->?; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ->? (
    PROCEDURE = field_boolean,
    LEFTARG = jsonb,
    RIGHTARG = text
);


--
-- Name: ->?; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ->? (
    PROCEDURE = field_boolean,
    LEFTARG = jsonb,
    RIGHTARG = integer
);


--
-- Name: ->|; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ->| (
    PROCEDURE = field_bit,
    LEFTARG = jsonb,
    RIGHTARG = integer
);


--
-- Name: ->|; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ->| (
    PROCEDURE = field_bit,
    LEFTARG = jsonb,
    RIGHTARG = text
);


--
-- Name: ===; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR === (
    PROCEDURE = element_equals,
    LEFTARG = anyelement,
    RIGHTARG = anyelement
);


--
-- Name: |||; Type: OPERATOR; Schema: lib; Owner: -
--

CREATE OPERATOR ||| (
    PROCEDURE = concat,
    LEFTARG = anyelement,
    RIGHTARG = anyelement
);


SET search_path = pg_catalog;

--
-- Name: CAST (integer AS uuid); Type: CAST; Schema: pg_catalog; Owner: -
--

CREATE CAST (integer AS uuid) WITH FUNCTION lib.to_uuid(anyelement);


--
-- Name: CAST (numeric AS uuid); Type: CAST; Schema: pg_catalog; Owner: -
--

CREATE CAST (numeric AS uuid) WITH FUNCTION lib.to_uuid(anyelement);


SET search_path = colaborador, pg_catalog;

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


SET search_path = ggviario, pg_catalog;

--
-- Name: distrito; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE distrito (
    distrito_id smallint NOT NULL,
    distrito_nome character varying(32)
);


--
-- Name: fornecedor; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE fornecedor (
    fornecedor_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    fornecedor_distrito_id smallint,
    fornecedor_colaborador_id uuid NOT NULL,
    fornecedor_colaborador_atualizacao uuid,
    fornecedor_nome character varying(32) NOT NULL,
    fornecedor_nif character varying(9) DEFAULT NULL::character varying,
    fornecedor_telefone character varying(15) DEFAULT NULL::character varying,
    fornecedor_telemovel character varying(15) DEFAULT NULL::character varying,
    fornecedor_mail character varying(32) DEFAULT NULL::character varying,
    fornecedor_local character varying(48) DEFAULT NULL::character varying,
    fornecedor_estado smallint DEFAULT 0 NOT NULL,
    fornecedor_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    fornecedor_dataatualizacao timestamp without time zone,
    CONSTRAINT ck_fornecedor_local_is_normalized CHECK (lib.is_normalized((fornecedor_local)::text)),
    CONSTRAINT ck_fornecedor_mail_is_normalized CHECK ((lib.is_normalized((fornecedor_mail)::text) AND (lower((fornecedor_mail)::text) = (fornecedor_mail)::text))),
    CONSTRAINT ck_fornecedor_nif_is_normalized CHECK (lib.is_normalized((fornecedor_nif)::text)),
    CONSTRAINT ck_fornecedor_nif_is_valid CHECK (((fornecedor_nif IS NULL) OR lib.is_valid_nif((fornecedor_nif)::text))),
    CONSTRAINT ck_fornecedor_nome_is_normalized CHECK (lib.is_normalized((fornecedor_nome)::text)),
    CONSTRAINT ck_fornecedor_telefone_is_normalized CHECK (lib.is_normalized((fornecedor_telefone)::text)),
    CONSTRAINT ck_fornecedor_telemovel_is_normalized CHECK (lib.is_normalized((fornecedor_telemovel)::text))
);


--
-- Name: producao; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE producao (
    producao_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    producao_produto_id uuid NOT NULL,
    producao_setor_id uuid NOT NULL,
    producao_colaborador_id uuid NOT NULL,
    producao_colaborador_atualizacao uuid,
    producao_quantidade numeric,
    producao_data date,
    producao_estado smallint DEFAULT 1 NOT NULL,
    producao_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    producao_dataatualizacao timestamp without time zone
);


--
-- Name: seq_faturanumero; Type: SEQUENCE; Schema: ggviario; Owner: -
--

CREATE SEQUENCE seq_faturanumero
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: sexo; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE sexo (
    sexo_id smallint NOT NULL,
    sexo_desc character varying(10) NOT NULL
);


--
-- Name: tipoconta; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE tipoconta (
    tconta_id smallint NOT NULL,
    tconta_desc character varying(32) NOT NULL
);


--
-- Name: tipodocumento; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE tipodocumento (
    tdocumento_id smallint NOT NULL,
    tdocumento_desc character varying(32) NOT NULL
);


--
-- Name: tipomovimento; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE tipomovimento (
    tmovimento_id smallint NOT NULL,
    tmovimento_desc character varying(32) NOT NULL
);


SET search_path = colaborador, pg_catalog;

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
d455fafa-a1e7-43b9-a4ee-b0d0ca97660d	00000000-0000-0000-0000-000000000001	\N	1	costa.xdaniel@gmail.com	4586e377a9e59fec53fd0f4a073da501	Daniel	Costa	109000001	1994-12-01	1	2	2018-02-11 10:14:03.690435	JJENDQJLZUBPFOXK	f	\N	2018-02-11 10:14:03.690435
00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	\N	transpax.system@jigahd.com	fa1fa6c024302268077f8063f7146540	Transpax	System data	#########	\N	1	2	2018-01-30 13:35:10.43203	\N	t	\N	2018-01-30 13:35:10.43203
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


SET search_path = ggviario, pg_catalog;

--
-- Data for Name: categoria; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY categoria (categoria_id, categoria_colaborador_id, categoria_colaborador_atualizacao, categoria_categoria_id, categoria_nome, categoria_posisao, categoria_estado, categoria_dataregito, categoria_dataatualizacao) FROM stdin;
00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	\N	Aviario	\N	1	2018-02-09 18:46:15.84334	\N
\.


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY cliente (cliente_id, cliente_sexo_id, cliente_distrito_id, cliente_tdocumento_id, cliente_colaborador_id, cliente_colaborador_atualizacao, cliente_documentonumero, cliente_nome, cliente_apelido, cliente_telefone, cliente_telemovel, cliente_mail, cliente_morada, cliente_estado, cliente_dataregisto, cliente_dataatualizacao, cliente_localtrabalho, cliente_datanascimento) FROM stdin;
00000000-0000-0000-0000-000000000001	\N	\N	\N	00000000-0000-0000-0000-000000000001	\N	\N	Anonimo	\N	\N	\N	\N	\N	1	2018-02-11 14:08:24.702639	\N	\N	\N
839ea817-c822-4c86-8e40-712ecb7f4b3c	2	3	2	00000000-0000-0000-0000-000000000001	\N	27838L	Ana Maria	Maria Cristovão	\N	\N	\N	Almerin	1	2018-02-21 10:15:27.582571	\N	\N	\N
32927a25-e62f-4c8c-b53e-72f61579cdda	\N	\N	\N	00000000-0000-0000-0000-000000000001	\N	\N	Asd	sew	\N	\N	\N	\N	1	2018-02-21 10:30:56.443303	\N	\N	\N
b277fd04-c573-45f3-9ee8-9710e176864a	\N	\N	\N	00000000-0000-0000-0000-000000000001	\N	\N	Lucas	Simedo	\N	\N	\N	\N	1	2018-02-21 10:32:44.707108	\N	\N	\N
a8de6d12-7c91-4f96-b4d1-8149c2789acf	\N	\N	\N	00000000-0000-0000-0000-000000000001	\N	\N	Lucia Amado	Assução Crista	\N	\N	\N	\N	1	2018-02-21 10:57:05.446577	\N	\N	\N
475560ba-209a-4e12-a844-19885f34e518	\N	\N	\N	00000000-0000-0000-0000-000000000001	\N	\N	Antonio	Filipe coria do amparo	\N	\N	\N	\N	1	2018-02-21 11:34:53.23103	\N	\N	2018-01-01
91abee08-9c8f-4244-96cc-93daa94fc5f8	\N	\N	\N	00000000-0000-0000-0000-000000000001	\N	\N	Ana Maria	sdks	\N	\N	\N	\N	1	2018-02-21 11:46:35.502301	\N	\N	2018-03-10
ff1662e2-37f1-4ddb-8371-d631a51c68c1	\N	\N	\N	00000000-0000-0000-0000-000000000001	\N	\N	Ola Bem	Olamd	\N	\N	\N	\N	1	2018-02-21 11:50:16.195488	\N	\N	2019-01-29
b36fbf35-7542-40a6-83e5-7552adb61047	\N	\N	\N	00000000-0000-0000-0000-000000000001	\N	\N	Amadeus	\N	\N	\N	\N	\N	1	2018-02-21 12:03:53.542021	\N	\N	\N
\.


--
-- Data for Name: compra; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY compra (compra_id, compra_produto_id, compra_unidade_id, compra_cliente_id, compra_tcompra_id, compra_colaborador_id, compra_colaborador_atualizacao, compra_faturanumero, compra_quantidade, compra_quantidadeproduto, compra_custounitario, compra_custobruto, compra_custodesconto, compra_custopagar, compra_custoamortizado, compra_data, compra_datafinalizar, compra_datafim, compra_dataultimamovimentacao, compra_estado, compra_dataregisto, compra_dataatualizacao) FROM stdin;
\.


--
-- Data for Name: conta; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY conta (conta_id, conta_conta_id, conta_tconta_id, conta_colaborador_id, conta_colaborador_atualizacao, conta_numero, conta_numerobancario, conta_nome, conta_credito, conta_debito, conta_saldo, conta_dataultimamovimentacao, conta_estado, conta_dataregisto, conta_dataatualizacao) FROM stdin;
00000000-0000-0000-0000-000000000001	\N	1	00000000-0000-0000-0000-000000000001	\N	1	1	GGVIARIO	0	0	0	\N	1	2018-02-11 18:24:18.266768	\N
\.


--
-- Data for Name: despesa; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY despesa (despesa_id, despesa_fornecedor_id, despesa_produto_id, despesa_unidade_id, despesa_colaborador_id, despesa_colaborador_atualizacao, despesa_data, despesa_numerofatura, despesa_quatidade, despesa_quantidadeproduto, despesa_custounitario, despesa_custototal, despesa_custoamortizado, despesa_dataultimamovimento, despesa_dataregisto, despesa_dataatualizacao, despesa_estado) FROM stdin;
c08f53a1-4cc8-4f33-9515-1ee6dbab4f42	9875c79e-d638-4125-aeee-bcd61629a73f	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	2018-02-11	\N	10	10	100	1000	0	\N	2018-02-11 13:39:58.815835	\N	1
cb2e6d37-ac73-4892-ad09-b4639d2fc6a6	9875c79e-d638-4125-aeee-bcd61629a73f	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	2018-02-11	\N	10	10	100	1000	0	\N	2018-02-11 13:40:02.068816	\N	1
\.


--
-- Data for Name: distrito; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY distrito (distrito_id, distrito_nome) FROM stdin;
1	Água Grande
2	Cantagalo
3	Caué
4	Lembá
5	Mé-Zóchi
6	RAP
7	Lobata
\.


--
-- Data for Name: equivalencia; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY equivalencia (equivalencia_id, equivalencia_produto_id, equivalencia_unidade_id, equivalencia_colaborador_id, equivalencia_colaborador_atualizacao, equivalencia_quantidade, equivalencia_estado, equivalencia_dataregisto, equivalencia_dataatualizacao) FROM stdin;
4f874b3d-050d-4eb5-801f-d0cf22b6fc4d	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	1	1	2018-02-11 13:39:28.175768	\N
\.


--
-- Data for Name: fornecedor; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY fornecedor (fornecedor_id, fornecedor_distrito_id, fornecedor_colaborador_id, fornecedor_colaborador_atualizacao, fornecedor_nome, fornecedor_nif, fornecedor_telefone, fornecedor_telemovel, fornecedor_mail, fornecedor_local, fornecedor_estado, fornecedor_dataregisto, fornecedor_dataatualizacao) FROM stdin;
9875c79e-d638-4125-aeee-bcd61629a73f	\N	00000000-0000-0000-0000-000000000001	\N	Poto Poto	109029372	\N	\N	\N	\N	0	2018-02-11 12:44:15.148861	\N
5bde9bae-eae1-47b4-9d08-26a310e62fd2	1	00000000-0000-0000-0000-000000000001	\N	Rock	109836397	222222	999999	cost@cost.st	Madre de deus	0	2018-02-11 12:49:12.065628	\N
\.


--
-- Data for Name: movimento; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY movimento (movimento_id, movimento_conta_id, movimento_tmovimento_id, movimento_colaborador_id, movimento_colaborador_atualizacao, movimento_movimento_id, movimento_compra_id, movimento_despeda_id, movimento_data, movimento_documento, movimento_montante, movimento_libele, movimento_transferencianumero, movimento_devolucao, movimento_devolucaoultimadada, movimento_devolucamontantedevolvido, movimento_estado, movimento_dataregisto, movimento_dataatualizacao) FROM stdin;
7415f960-655f-4a23-8b11-8d9cae4d2dff	00000000-0000-0000-0000-000000000001	1	00000000-0000-0000-0000-000000000001	\N	\N	aa863518-aa81-4f6c-9f75-2aed1bfa5afc	\N	2018-02-11	3243	100	Pagamento tatata	\N	f	\N	0	0	2018-02-11 18:26:18.901833	2018-02-11 18:26:18.901833
e2f0571d-cad4-4223-9a4f-fc17f6cb6e90	00000000-0000-0000-0000-000000000001	1	00000000-0000-0000-0000-000000000001	\N	\N	aa863518-aa81-4f6c-9f75-2aed1bfa5afc	\N	2018-02-11	3243	100	Pagamento tatata	\N	f	\N	0	0	2018-02-11 18:26:26.549562	2018-02-11 18:26:26.549562
\.


--
-- Data for Name: preco; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY preco (preco_id, preco_produto_id, preco_unidade_id, preco_colaborador_id, preco_colaborador_atualizacao, preco_valor, preco_estado, preco_dataregisto, preco_dataatualizacao) FROM stdin;
\.


--
-- Data for Name: producao; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY producao (producao_id, producao_produto_id, producao_setor_id, producao_colaborador_id, producao_colaborador_atualizacao, producao_quantidade, producao_data, producao_estado, producao_dataregisto, producao_dataatualizacao) FROM stdin;
aaddd356-b9f4-4de6-85f3-cecc39f736b8	00000000-0000-0000-0000-000000000001	ad80e6fd-d55f-469c-9008-828dc4586b73	00000000-0000-0000-0000-000000000001	\N	10	2018-02-11	1	2018-02-11 11:51:23.94328	\N
4be7d524-15d5-4fe9-979e-69d22458c145	00000000-0000-0000-0000-000000000001	2d6bf7b9-64b8-4f9c-bbde-be85aaf46136	00000000-0000-0000-0000-000000000001	\N	10	2018-02-11	1	2018-02-11 11:53:27.65714	\N
dc39ac15-4d9c-4166-9156-02ef30c81538	00000000-0000-0000-0000-000000000001	2d6bf7b9-64b8-4f9c-bbde-be85aaf46136	00000000-0000-0000-0000-000000000001	\N	15	2018-02-11	1	2018-02-11 11:53:45.003157	\N
\.


--
-- Data for Name: produto; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY produto (produto_id, produto_categoria_id, produto_colaborador_id, produto_colaborador_atualizacao, produto_nome, produto_stock, produto_stockminimo, produto_servicocliente, produto_servicofornecedor, produto_servicoproducao, produto_servicostockdinamico, produto_servicostockdinamiconegativo, produto_estado, produto_dataregisto, produto_dataatualizacao) FROM stdin;
00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	Ovos	52	0	t	t	t	t	f	1	2018-02-09 18:48:34.984037	2018-02-09 18:48:34.984037
\.


--
-- Data for Name: setor; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY setor (setor_id, setor_setor_id, setor_colaborador_id, setor_colaborador_atualizacao, setor_nome, setor_posicao, setor_totalsubsetores, setor_quantidadetotalproduzida, setor_estado, setor_dataregisto, setor_dataatualizacao) FROM stdin;
ad80e6fd-d55f-469c-9008-828dc4586b73	00965dcc-125c-4cc9-a6d0-cccf0a4c8ecb	00000000-0000-0000-0000-000000000001	\N	Velha de nova	\N	0	10	1	2018-02-11 11:43:43.607595	\N
00965dcc-125c-4cc9-a6d0-cccf0a4c8ecb	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	Velha	\N	2	10	2	2018-02-11 11:42:52.031581	\N
2d6bf7b9-64b8-4f9c-bbde-be85aaf46136	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	Nova	\N	0	25	1	2018-02-11 11:43:07.640961	\N
44870327-9e59-4bc1-9a9d-aeab2d48ef51	00965dcc-125c-4cc9-a6d0-cccf0a4c8ecb	00000000-0000-0000-0000-000000000001	\N	Velha de velha	\N	0	0	1	2018-02-11 11:43:36.86362	\N
00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	AVIARIO	\N	2	35	2	2018-02-11 11:41:46.814429	2018-02-11 12:20:44.189013
\.


--
-- Data for Name: sexo; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY sexo (sexo_id, sexo_desc) FROM stdin;
1	Masculino
2	Feminino
\.


--
-- Data for Name: tipocompra; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY tipocompra (tcompra_id, tcompra_desc) FROM stdin;
1	Compra
2	Divida
\.


--
-- Data for Name: tipoconta; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY tipoconta (tconta_id, tconta_desc) FROM stdin;
1	Caixa
2	Caixa diaria
3	Conta contabil
4	Conta bancaria
\.


--
-- Data for Name: tipodocumento; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY tipodocumento (tdocumento_id, tdocumento_desc) FROM stdin;
1	B.I
2	PASS
3	CARTÃO RESIDENCIA
\.


--
-- Data for Name: tipomovimento; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY tipomovimento (tmovimento_id, tmovimento_desc) FROM stdin;
1	CREDITO
2	DEBITO
\.


--
-- Data for Name: unidade; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY unidade (unidade_id, unidade_colaborador_id, unidade_colaborador_atualizacao, unidade_nome, unidade_codigo, unidade_estado, unidade_dataregisto, unidade_dataatualizacao) FROM stdin;
00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	Unidade	Unit	1	2018-02-09 19:19:02.755139	\N
\.


--
-- Name: seq_faturanumero; Type: SEQUENCE SET; Schema: ggviario; Owner: -
--

SELECT pg_catalog.setval('seq_faturanumero', 15, true);


SET search_path = colaborador, pg_catalog;

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


SET search_path = ggviario, pg_catalog;

--
-- Name: movimento ck_movimato_source_is_valid; Type: CHECK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE movimento
    ADD CONSTRAINT ck_movimato_source_is_valid CHECK (rule.movimento_check_source(movimento.*));


--
-- Name: categoria pk_categoria_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT pk_categoria_id PRIMARY KEY (categoria_id);


--
-- Name: cliente pk_cliente_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT pk_cliente_id PRIMARY KEY (cliente_id);


--
-- Name: compra pk_compra_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT pk_compra_id PRIMARY KEY (compra_id);


--
-- Name: conta pk_conta_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY conta
    ADD CONSTRAINT pk_conta_id PRIMARY KEY (conta_id);


--
-- Name: despesa pk_despesa_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY despesa
    ADD CONSTRAINT pk_despesa_id PRIMARY KEY (despesa_id);


--
-- Name: distrito pk_distrito_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY distrito
    ADD CONSTRAINT pk_distrito_id PRIMARY KEY (distrito_id);


--
-- Name: equivalencia pk_equivalencia_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY equivalencia
    ADD CONSTRAINT pk_equivalencia_id PRIMARY KEY (equivalencia_id);


--
-- Name: fornecedor pk_fornecedor_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY fornecedor
    ADD CONSTRAINT pk_fornecedor_id PRIMARY KEY (fornecedor_id);


--
-- Name: movimento pk_movimento_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY movimento
    ADD CONSTRAINT pk_movimento_id PRIMARY KEY (movimento_id);


--
-- Name: preco pk_preco_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY preco
    ADD CONSTRAINT pk_preco_id PRIMARY KEY (preco_id);


--
-- Name: producao pk_produca_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY producao
    ADD CONSTRAINT pk_produca_id PRIMARY KEY (producao_id);


--
-- Name: produto pk_produto_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT pk_produto_id PRIMARY KEY (produto_id);


--
-- Name: setor pk_setor_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY setor
    ADD CONSTRAINT pk_setor_id PRIMARY KEY (setor_id);


--
-- Name: sexo pk_sexo_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY sexo
    ADD CONSTRAINT pk_sexo_id PRIMARY KEY (sexo_id);


--
-- Name: tipocompra pk_tcompra_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY tipocompra
    ADD CONSTRAINT pk_tcompra_id PRIMARY KEY (tcompra_id);


--
-- Name: tipoconta pk_tconta_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY tipoconta
    ADD CONSTRAINT pk_tconta_id PRIMARY KEY (tconta_id);


--
-- Name: tipodocumento pk_tdocumento_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY tipodocumento
    ADD CONSTRAINT pk_tdocumento_id PRIMARY KEY (tdocumento_id);


--
-- Name: tipomovimento pk_tmovimento_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY tipomovimento
    ADD CONSTRAINT pk_tmovimento_id PRIMARY KEY (tmovimento_id);


--
-- Name: unidade pk_unidade_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY unidade
    ADD CONSTRAINT pk_unidade_id PRIMARY KEY (unidade_id);


--
-- Name: tipodocumento qu_tdocumento_desc; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY tipodocumento
    ADD CONSTRAINT qu_tdocumento_desc UNIQUE (tdocumento_desc);


--
-- Name: categoria uq_categoria_nome; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT uq_categoria_nome UNIQUE (categoria_nome);


--
-- Name: cliente uq_cliente_documento; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT uq_cliente_documento UNIQUE (cliente_tdocumento_id, cliente_documentonumero);


--
-- Name: cliente uq_cliente_mails; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT uq_cliente_mails UNIQUE (cliente_mail);


--
-- Name: unidade uq_codigo_nome; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY unidade
    ADD CONSTRAINT uq_codigo_nome UNIQUE (unidade_codigo);


--
-- Name: compra uq_compra_fatura; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT uq_compra_fatura UNIQUE (compra_faturanumero);


--
-- Name: distrito uq_distrito_nome; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY distrito
    ADD CONSTRAINT uq_distrito_nome UNIQUE (distrito_nome);


--
-- Name: fornecedor uq_fornecedor_mail; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY fornecedor
    ADD CONSTRAINT uq_fornecedor_mail UNIQUE (fornecedor_mail);


--
-- Name: fornecedor uq_fornecedor_nif; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY fornecedor
    ADD CONSTRAINT uq_fornecedor_nif UNIQUE (fornecedor_nif);


--
-- Name: fornecedor uq_fornecedor_nome; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY fornecedor
    ADD CONSTRAINT uq_fornecedor_nome UNIQUE (fornecedor_nome);


--
-- Name: produto uq_produto_nome; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT uq_produto_nome UNIQUE (produto_nome);


--
-- Name: setor uq_setor_nome; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY setor
    ADD CONSTRAINT uq_setor_nome UNIQUE (setor_nome);


--
-- Name: sexo uq_sexo_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY sexo
    ADD CONSTRAINT uq_sexo_id UNIQUE (sexo_desc);


--
-- Name: tipocompra uq_tcompra_desc; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY tipocompra
    ADD CONSTRAINT uq_tcompra_desc UNIQUE (tcompra_desc);


--
-- Name: tipoconta uq_tconta_desc; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY tipoconta
    ADD CONSTRAINT uq_tconta_desc UNIQUE (tconta_desc);


--
-- Name: tipomovimento uq_tmovimento_desc; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY tipomovimento
    ADD CONSTRAINT uq_tmovimento_desc UNIQUE (tmovimento_desc);


--
-- Name: unidade uq_unidade_nome; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY unidade
    ADD CONSTRAINT uq_unidade_nome UNIQUE (unidade_nome);


SET search_path = colaborador, pg_catalog;

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


SET search_path = ggviario, pg_catalog;

--
-- Name: despesa tg_despesa_after_insert_update_stock; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_despesa_after_insert_update_stock AFTER INSERT ON despesa FOR EACH ROW EXECUTE PROCEDURE rule.functg_despesa_after_insert_update_stock();


--
-- Name: compra tg_despesa_after_insert_update_stock; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_despesa_after_insert_update_stock AFTER INSERT ON compra FOR EACH ROW EXECUTE PROCEDURE rule.functg_despesa_after_insert_update_stock();


--
-- Name: movimento tg_movimento_after_insert_update_conta; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_movimento_after_insert_update_conta AFTER INSERT ON movimento FOR EACH ROW EXECUTE PROCEDURE rule.functg_movimento_after_insert_update_conta();


--
-- Name: movimento tg_movimento_after_insert_update_itemcompra; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_movimento_after_insert_update_itemcompra AFTER INSERT ON movimento FOR EACH ROW EXECUTE PROCEDURE rule.functg_movimento_after_insert_update_itemcompra();


--
-- Name: movimento tg_movimento_after_insert_update_movimento_devolucao; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_movimento_after_insert_update_movimento_devolucao AFTER INSERT ON movimento FOR EACH ROW EXECUTE PROCEDURE rule.functg_movimento_after_insert_update_movimento_devolucao();


--
-- Name: producao tg_producao_after_insert_movement_sector_and_product; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_producao_after_insert_movement_sector_and_product AFTER INSERT ON producao FOR EACH ROW EXECUTE PROCEDURE rule.functg_producao_after_insert_movement_sector_and_product();


--
-- Name: setor tg_setor_after_insert_protect_parent; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_setor_after_insert_protect_parent AFTER INSERT ON setor FOR EACH ROW EXECUTE PROCEDURE rule.functg_setor_after_insert_protect_parent();


--
-- Name: setor tg_setor_after_update_update_cascade; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_setor_after_update_update_cascade AFTER UPDATE ON setor FOR EACH ROW EXECUTE PROCEDURE rule.functg_setor_after_update_update_cascade();


SET search_path = colaborador, pg_catalog;

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


SET search_path = ggviario, pg_catalog;

--
-- Name: categoria fk_categoria_to_categoria; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT fk_categoria_to_categoria FOREIGN KEY (categoria_categoria_id) REFERENCES categoria(categoria_id);


--
-- Name: categoria fk_categoria_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT fk_categoria_to_colaborador FOREIGN KEY (categoria_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: categoria fk_categoria_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT fk_categoria_to_colaborador_atualizacao FOREIGN KEY (categoria_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: cliente fk_cliente_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_to_colaborador FOREIGN KEY (cliente_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: cliente fk_cliente_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_to_colaborador_atualizacao FOREIGN KEY (cliente_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: cliente fk_cliente_to_distrito; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_to_distrito FOREIGN KEY (cliente_distrito_id) REFERENCES distrito(distrito_id);


--
-- Name: cliente fk_cliente_to_sexo; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_to_sexo FOREIGN KEY (cliente_sexo_id) REFERENCES sexo(sexo_id);


--
-- Name: cliente fk_cliente_to_tipodocumento; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente_to_tipodocumento FOREIGN KEY (cliente_tdocumento_id) REFERENCES tipodocumento(tdocumento_id);


--
-- Name: compra fk_compra_to_cliente; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_to_cliente FOREIGN KEY (compra_cliente_id) REFERENCES cliente(cliente_id);


--
-- Name: compra fk_compra_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_to_colaborador FOREIGN KEY (compra_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: compra fk_compra_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_to_colaborador_atualizacao FOREIGN KEY (compra_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: compra fk_compra_to_produto; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_to_produto FOREIGN KEY (compra_produto_id) REFERENCES produto(produto_id);


--
-- Name: compra fk_compra_to_tipocompra; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_to_tipocompra FOREIGN KEY (compra_tcompra_id) REFERENCES tipocompra(tcompra_id);


--
-- Name: compra fk_compra_to_unidade; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_to_unidade FOREIGN KEY (compra_unidade_id) REFERENCES unidade(unidade_id);


--
-- Name: conta fk_conta_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY conta
    ADD CONSTRAINT fk_conta_to_colaborador FOREIGN KEY (conta_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: conta fk_conta_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY conta
    ADD CONSTRAINT fk_conta_to_colaborador_atualizacao FOREIGN KEY (conta_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: conta fk_conta_to_contao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY conta
    ADD CONSTRAINT fk_conta_to_contao FOREIGN KEY (conta_conta_id) REFERENCES conta(conta_id);


--
-- Name: conta fk_conta_to_tipoconta; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY conta
    ADD CONSTRAINT fk_conta_to_tipoconta FOREIGN KEY (conta_tconta_id) REFERENCES tipoconta(tconta_id);


--
-- Name: despesa fk_despesa_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY despesa
    ADD CONSTRAINT fk_despesa_to_colaborador FOREIGN KEY (despesa_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: despesa fk_despesa_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY despesa
    ADD CONSTRAINT fk_despesa_to_colaborador_atualizacao FOREIGN KEY (despesa_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: despesa fk_despesa_to_fornecedor; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY despesa
    ADD CONSTRAINT fk_despesa_to_fornecedor FOREIGN KEY (despesa_fornecedor_id) REFERENCES fornecedor(fornecedor_id);


--
-- Name: despesa fk_despesa_to_produto; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY despesa
    ADD CONSTRAINT fk_despesa_to_produto FOREIGN KEY (despesa_produto_id) REFERENCES produto(produto_id);


--
-- Name: despesa fk_despesa_to_unidade; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY despesa
    ADD CONSTRAINT fk_despesa_to_unidade FOREIGN KEY (despesa_unidade_id) REFERENCES unidade(unidade_id);


--
-- Name: equivalencia fk_equivalencia_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY equivalencia
    ADD CONSTRAINT fk_equivalencia_to_colaborador FOREIGN KEY (equivalencia_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: equivalencia fk_equivalencia_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY equivalencia
    ADD CONSTRAINT fk_equivalencia_to_colaborador_atualizacao FOREIGN KEY (equivalencia_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: equivalencia fk_equivalencia_to_produto; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY equivalencia
    ADD CONSTRAINT fk_equivalencia_to_produto FOREIGN KEY (equivalencia_produto_id) REFERENCES produto(produto_id);


--
-- Name: equivalencia fk_equivalencia_to_unidade; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY equivalencia
    ADD CONSTRAINT fk_equivalencia_to_unidade FOREIGN KEY (equivalencia_unidade_id) REFERENCES unidade(unidade_id);


--
-- Name: fornecedor fk_fornecedor_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY fornecedor
    ADD CONSTRAINT fk_fornecedor_to_colaborador FOREIGN KEY (fornecedor_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: fornecedor fk_fornecedor_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY fornecedor
    ADD CONSTRAINT fk_fornecedor_to_colaborador_atualizacao FOREIGN KEY (fornecedor_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: fornecedor fk_fornecedor_to_distrito; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY fornecedor
    ADD CONSTRAINT fk_fornecedor_to_distrito FOREIGN KEY (fornecedor_distrito_id) REFERENCES distrito(distrito_id);


--
-- Name: movimento fk_movimento_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY movimento
    ADD CONSTRAINT fk_movimento_to_colaborador FOREIGN KEY (movimento_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: movimento fk_movimento_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY movimento
    ADD CONSTRAINT fk_movimento_to_colaborador_atualizacao FOREIGN KEY (movimento_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: movimento fk_movimento_to_conta; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY movimento
    ADD CONSTRAINT fk_movimento_to_conta FOREIGN KEY (movimento_conta_id) REFERENCES conta(conta_id);


--
-- Name: movimento fk_movimento_to_despesa; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY movimento
    ADD CONSTRAINT fk_movimento_to_despesa FOREIGN KEY (movimento_despeda_id) REFERENCES despesa(despesa_id);


--
-- Name: movimento fk_movimento_to_movimento_itemdevolucao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY movimento
    ADD CONSTRAINT fk_movimento_to_movimento_itemdevolucao FOREIGN KEY (movimento_movimento_id) REFERENCES movimento(movimento_id);


--
-- Name: movimento fk_movimento_to_tipomovimento; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY movimento
    ADD CONSTRAINT fk_movimento_to_tipomovimento FOREIGN KEY (movimento_tmovimento_id) REFERENCES tipomovimento(tmovimento_id);


--
-- Name: preco fk_preco_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY preco
    ADD CONSTRAINT fk_preco_to_colaborador FOREIGN KEY (preco_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: preco fk_preco_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY preco
    ADD CONSTRAINT fk_preco_to_colaborador_atualizacao FOREIGN KEY (preco_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: preco fk_preco_to_produto; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY preco
    ADD CONSTRAINT fk_preco_to_produto FOREIGN KEY (preco_produto_id) REFERENCES produto(produto_id);


--
-- Name: preco fk_preco_to_unidade; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY preco
    ADD CONSTRAINT fk_preco_to_unidade FOREIGN KEY (preco_unidade_id) REFERENCES unidade(unidade_id);


--
-- Name: producao fk_producao_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY producao
    ADD CONSTRAINT fk_producao_to_colaborador FOREIGN KEY (producao_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: producao fk_producao_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY producao
    ADD CONSTRAINT fk_producao_to_colaborador_atualizacao FOREIGN KEY (producao_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: producao fk_producao_to_produto; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY producao
    ADD CONSTRAINT fk_producao_to_produto FOREIGN KEY (producao_produto_id) REFERENCES produto(produto_id);


--
-- Name: producao fk_producao_to_setor; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY producao
    ADD CONSTRAINT fk_producao_to_setor FOREIGN KEY (producao_setor_id) REFERENCES setor(setor_id);


--
-- Name: produto fk_produto_to_categoria; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT fk_produto_to_categoria FOREIGN KEY (produto_categoria_id) REFERENCES categoria(categoria_id);


--
-- Name: produto fk_produto_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT fk_produto_to_colaborador FOREIGN KEY (produto_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: produto fk_produto_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT fk_produto_to_colaborador_atualizacao FOREIGN KEY (produto_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: setor fk_setor_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY setor
    ADD CONSTRAINT fk_setor_to_colaborador FOREIGN KEY (setor_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: setor fk_setor_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY setor
    ADD CONSTRAINT fk_setor_to_colaborador_atualizacao FOREIGN KEY (setor_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: setor fk_setor_to_setor; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY setor
    ADD CONSTRAINT fk_setor_to_setor FOREIGN KEY (setor_setor_id) REFERENCES setor(setor_id);


--
-- Name: unidade fk_unidade_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY unidade
    ADD CONSTRAINT fk_unidade_to_colaborador FOREIGN KEY (unidade_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: unidade fk_unidade_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY unidade
    ADD CONSTRAINT fk_unidade_to_colaborador_atualizacao FOREIGN KEY (unidade_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: -
--

GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

