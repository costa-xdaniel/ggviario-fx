--
-- PostgreSQL database dump
--

-- Dumped from database version 10.2
-- Dumped by pg_dump version 10.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = ggviario, pg_catalog;

ALTER TABLE IF EXISTS ONLY ggviario.venda DROP CONSTRAINT IF EXISTS fk_venda_to_unidade;
ALTER TABLE IF EXISTS ONLY ggviario.venda DROP CONSTRAINT IF EXISTS fk_venda_to_tipovenda;
ALTER TABLE IF EXISTS ONLY ggviario.venda DROP CONSTRAINT IF EXISTS fk_venda_to_produto;
ALTER TABLE IF EXISTS ONLY ggviario.venda DROP CONSTRAINT IF EXISTS fk_venda_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.venda DROP CONSTRAINT IF EXISTS fk_venda_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.venda DROP CONSTRAINT IF EXISTS fk_venda_to_cliente;
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
ALTER TABLE IF EXISTS ONLY ggviario.localproducao DROP CONSTRAINT IF EXISTS fk_localproducao_to_setor;
ALTER TABLE IF EXISTS ONLY ggviario.localproducao DROP CONSTRAINT IF EXISTS fk_localproducao_to_produto;
ALTER TABLE IF EXISTS ONLY ggviario.localproducao DROP CONSTRAINT IF EXISTS fk_localproducao_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.localproducao DROP CONSTRAINT IF EXISTS fk_localproducao_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS fk_fornecedor_to_distrito;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS fk_fornecedor_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS fk_fornecedor_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS fk_despesa_to_unidade;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS fk_despesa_to_produto;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS fk_despesa_to_fornecedor;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS fk_despesa_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS fk_despesa_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.conta DROP CONSTRAINT IF EXISTS fk_conta_to_tipoconta;
ALTER TABLE IF EXISTS ONLY ggviario.conta DROP CONSTRAINT IF EXISTS fk_conta_to_contao;
ALTER TABLE IF EXISTS ONLY ggviario.conta DROP CONSTRAINT IF EXISTS fk_conta_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.conta DROP CONSTRAINT IF EXISTS fk_conta_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS fk_cliente_to_tipodocumento;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS fk_cliente_to_sexo;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS fk_cliente_to_distrito;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS fk_cliente_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS fk_cliente_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.categoria DROP CONSTRAINT IF EXISTS fk_categoria_to_colaborador_atualizacao;
ALTER TABLE IF EXISTS ONLY ggviario.categoria DROP CONSTRAINT IF EXISTS fk_categoria_to_colaborador;
ALTER TABLE IF EXISTS ONLY ggviario.audit DROP CONSTRAINT IF EXISTS fk_audit_to_colaborador;
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

DROP TRIGGER IF EXISTS tg_venda_intert_update_produto_stock ON ggviario.venda;
DROP TRIGGER IF EXISTS tg_setor_after_updade_disable_disable_children ON ggviario.setor;
DROP TRIGGER IF EXISTS tg_setor_after_insert_protect_parent ON ggviario.setor;
DROP TRIGGER IF EXISTS tg_producao_after_insert_movement_sector_and_product ON ggviario.producao;
DROP TRIGGER IF EXISTS tg_movimento_after_insert_update_movimento_devolucao ON ggviario.movimento;
DROP TRIGGER IF EXISTS tg_movimento_after_insert_update_itemcompra ON ggviario.movimento;
DROP TRIGGER IF EXISTS tg_movimento_after_insert_update_despesa ON ggviario.movimento;
DROP TRIGGER IF EXISTS tg_movimento_after_insert_update_conta ON ggviario.movimento;
DROP TRIGGER IF EXISTS tg_despesa_after_insert_update_stock ON ggviario.despesa;
DROP INDEX IF EXISTS ggviario.setor_setor_codigo_uindex;
DROP INDEX IF EXISTS ggviario.producao_producao_codigo_uindex;
DROP INDEX IF EXISTS ggviario.idx_venda_unidade_id;
DROP INDEX IF EXISTS ggviario.idx_venda_produto_id;
DROP INDEX IF EXISTS ggviario.idx_venda_estado;
DROP INDEX IF EXISTS ggviario.idx_venda_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_venda_datafinalizar;
DROP INDEX IF EXISTS ggviario.idx_venda_datafim;
DROP INDEX IF EXISTS ggviario.idx_venda_data;
DROP INDEX IF EXISTS ggviario.idx_venda_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_venda_cliente_id;
DROP INDEX IF EXISTS ggviario.idx_unidade_estado;
DROP INDEX IF EXISTS ggviario.idx_unidade_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_unidade_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_setor_setor_id;
DROP INDEX IF EXISTS ggviario.idx_setor_posicao;
DROP INDEX IF EXISTS ggviario.idx_setor_estado;
DROP INDEX IF EXISTS ggviario.idx_setor_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_setor_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_produto_servioproducao;
DROP INDEX IF EXISTS ggviario.idx_produto_servicovenda;
DROP INDEX IF EXISTS ggviario.idx_produto_servicostockdinamico;
DROP INDEX IF EXISTS ggviario.idx_produto_servicocompra;
DROP INDEX IF EXISTS ggviario.idx_produto_estado;
DROP INDEX IF EXISTS ggviario.idx_produto_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_produto_categoria;
DROP INDEX IF EXISTS ggviario.idx_producao_setor_id;
DROP INDEX IF EXISTS ggviario.idx_producao_produto_id;
DROP INDEX IF EXISTS ggviario.idx_producao_estado;
DROP INDEX IF EXISTS ggviario.idx_producao_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_producao_data;
DROP INDEX IF EXISTS ggviario.idx_producao_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_preco_unidade_id;
DROP INDEX IF EXISTS ggviario.idx_preco_produto_id;
DROP INDEX IF EXISTS ggviario.idx_preco_estado;
DROP INDEX IF EXISTS ggviario.idx_preco_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_preco_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_preco_base;
DROP INDEX IF EXISTS ggviario.idx_movimento_venda_id;
DROP INDEX IF EXISTS ggviario.idx_movimento_tmovimento_id;
DROP INDEX IF EXISTS ggviario.idx_movimento_movimento_id;
DROP INDEX IF EXISTS ggviario.idx_movimento_estado;
DROP INDEX IF EXISTS ggviario.idx_movimento_documento;
DROP INDEX IF EXISTS ggviario.idx_movimento_devolucaoultimadata;
DROP INDEX IF EXISTS ggviario.idx_movimento_devolucaomontantedevolvido;
DROP INDEX IF EXISTS ggviario.idx_movimento_devolucao;
DROP INDEX IF EXISTS ggviario.idx_movimento_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_movimento_data;
DROP INDEX IF EXISTS ggviario.idx_movimento_conta_id;
DROP INDEX IF EXISTS ggviario.idx_movimento_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_localproducao_setor_id;
DROP INDEX IF EXISTS ggviario.idx_localproducao_produto_id;
DROP INDEX IF EXISTS ggviario.idx_localproducao_estado;
DROP INDEX IF EXISTS ggviario.idx_localproducao_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_localproducao_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_localproducao_colaborador_atualizacao;
DROP INDEX IF EXISTS ggviario.idx_fornecedor_nome;
DROP INDEX IF EXISTS ggviario.idx_fornecedor_estado;
DROP INDEX IF EXISTS ggviario.idx_fornecedor_distrito_id;
DROP INDEX IF EXISTS ggviario.idx_fornecedor_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_fornecedor_codigo;
DROP INDEX IF EXISTS ggviario.idx_despesa_unidade_id;
DROP INDEX IF EXISTS ggviario.idx_despesa_produto_id;
DROP INDEX IF EXISTS ggviario.idx_despesa_numerofatura;
DROP INDEX IF EXISTS ggviario.idx_despesa_fornecedor_id;
DROP INDEX IF EXISTS ggviario.idx_despesa_estado;
DROP INDEX IF EXISTS ggviario.idx_despesa_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_despesa_datafim;
DROP INDEX IF EXISTS ggviario.idx_despesa_data;
DROP INDEX IF EXISTS ggviario.idx_despesa_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_despesa_codigo;
DROP INDEX IF EXISTS ggviario.idx_conta_tconta_id;
DROP INDEX IF EXISTS ggviario.idx_conta_numero;
DROP INDEX IF EXISTS ggviario.idx_conta_nome;
DROP INDEX IF EXISTS ggviario.idx_conta_estado;
DROP INDEX IF EXISTS ggviario.idx_conta_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_conta_conta_id;
DROP INDEX IF EXISTS ggviario.idx_conta_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_cliente_sexo_id;
DROP INDEX IF EXISTS ggviario.idx_cliente_nome;
DROP INDEX IF EXISTS ggviario.idx_cliente_estado;
DROP INDEX IF EXISTS ggviario.idx_cliente_distrito_id;
DROP INDEX IF EXISTS ggviario.idx_cliente_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_cliente_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_cliente_apelido;
DROP INDEX IF EXISTS ggviario.idx_categoria_posicao;
DROP INDEX IF EXISTS ggviario.idx_categoria_nivel;
DROP INDEX IF EXISTS ggviario.idx_categoria_estado;
DROP INDEX IF EXISTS ggviario.idx_categoria_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_categoria_colaborador_id;
DROP INDEX IF EXISTS ggviario.idx_categoria_colaborador_atualizacao;
DROP INDEX IF EXISTS ggviario.idx_categoria_categoraia_id;
DROP INDEX IF EXISTS ggviario.idx_audit_title;
DROP INDEX IF EXISTS ggviario.idx_audit_key;
DROP INDEX IF EXISTS ggviario.idx_audit_dataregisto;
DROP INDEX IF EXISTS ggviario.idx_audit_colaborador_id;
DROP INDEX IF EXISTS ggviario.despesa_despesa_codigo_uindex;
DROP INDEX IF EXISTS ggviario.cliente_cliente_codigo_uindex;
DROP INDEX IF EXISTS ggviario.categoria_categorai_codigo_uindex;
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

ALTER TABLE IF EXISTS ONLY ggviario.venda DROP CONSTRAINT IF EXISTS uq_venda_faturanumero;
ALTER TABLE IF EXISTS ONLY ggviario.unidade DROP CONSTRAINT IF EXISTS uq_unidade_nome;
ALTER TABLE IF EXISTS ONLY ggviario.tipomovimento DROP CONSTRAINT IF EXISTS uq_tmovimento_desc;
ALTER TABLE IF EXISTS ONLY ggviario.tipoconta DROP CONSTRAINT IF EXISTS uq_tconta_desc;
ALTER TABLE IF EXISTS ONLY ggviario.tipovenda DROP CONSTRAINT IF EXISTS uq_tcompra_desc;
ALTER TABLE IF EXISTS ONLY ggviario.sexo DROP CONSTRAINT IF EXISTS uq_sexo_id;
ALTER TABLE IF EXISTS ONLY ggviario.setor DROP CONSTRAINT IF EXISTS uq_setor_nome;
ALTER TABLE IF EXISTS ONLY ggviario.produto DROP CONSTRAINT IF EXISTS uq_produto_nome;
ALTER TABLE IF EXISTS ONLY ggviario.produto DROP CONSTRAINT IF EXISTS uq_produto_codigo;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS uq_fornecedor_nome;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS uq_fornecedor_nif;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS uq_fornecedor_mail;
ALTER TABLE IF EXISTS ONLY ggviario.distrito DROP CONSTRAINT IF EXISTS uq_distrito_nome;
ALTER TABLE IF EXISTS ONLY ggviario.unidade DROP CONSTRAINT IF EXISTS uq_codigo_nome;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS uq_cliente_mails;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS uq_cliente_documento;
ALTER TABLE IF EXISTS ONLY ggviario.categoria DROP CONSTRAINT IF EXISTS uq_categoria_nome;
ALTER TABLE IF EXISTS ONLY ggviario.tipodocumento DROP CONSTRAINT IF EXISTS qu_tdocumento_desc;
ALTER TABLE IF EXISTS ONLY ggviario.venda DROP CONSTRAINT IF EXISTS pk_venda_id;
ALTER TABLE IF EXISTS ONLY ggviario.unidade DROP CONSTRAINT IF EXISTS pk_unidade_id;
ALTER TABLE IF EXISTS ONLY ggviario.tipovenda DROP CONSTRAINT IF EXISTS pk_tvenda_id;
ALTER TABLE IF EXISTS ONLY ggviario.tipomovimento DROP CONSTRAINT IF EXISTS pk_tmovimento_id;
ALTER TABLE IF EXISTS ONLY ggviario.tipodocumento DROP CONSTRAINT IF EXISTS pk_tdocumento_id;
ALTER TABLE IF EXISTS ONLY ggviario.tipoconta DROP CONSTRAINT IF EXISTS pk_tconta_id;
ALTER TABLE IF EXISTS ONLY ggviario.sexo DROP CONSTRAINT IF EXISTS pk_sexo_id;
ALTER TABLE IF EXISTS ONLY ggviario.setor DROP CONSTRAINT IF EXISTS pk_setor_id;
ALTER TABLE IF EXISTS ONLY ggviario.produto DROP CONSTRAINT IF EXISTS pk_produto_id;
ALTER TABLE IF EXISTS ONLY ggviario.producao DROP CONSTRAINT IF EXISTS pk_produca_id;
ALTER TABLE IF EXISTS ONLY ggviario.preco DROP CONSTRAINT IF EXISTS pk_preco_id;
ALTER TABLE IF EXISTS ONLY ggviario.movimento DROP CONSTRAINT IF EXISTS pk_movimento_id;
ALTER TABLE IF EXISTS ONLY ggviario.localproducao DROP CONSTRAINT IF EXISTS pk_localproducao_id;
ALTER TABLE IF EXISTS ONLY ggviario.fornecedor DROP CONSTRAINT IF EXISTS pk_fornecedor_id;
ALTER TABLE IF EXISTS ONLY ggviario.distrito DROP CONSTRAINT IF EXISTS pk_distrito_id;
ALTER TABLE IF EXISTS ONLY ggviario.despesa DROP CONSTRAINT IF EXISTS pk_despesa_id;
ALTER TABLE IF EXISTS ONLY ggviario.conta DROP CONSTRAINT IF EXISTS pk_conta_id;
ALTER TABLE IF EXISTS ONLY ggviario.codigo DROP CONSTRAINT IF EXISTS pk_codigo_unico;
ALTER TABLE IF EXISTS ONLY ggviario.cliente DROP CONSTRAINT IF EXISTS pk_cliente_id;
ALTER TABLE IF EXISTS ONLY ggviario.categoria DROP CONSTRAINT IF EXISTS pk_categoria_id;
ALTER TABLE IF EXISTS ONLY ggviario.audit DROP CONSTRAINT IF EXISTS pk_audith_id;
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
DROP TABLE IF EXISTS ggviario.producao;
DROP TABLE IF EXISTS ggviario.localproducao;
DROP TABLE IF EXISTS ggviario.distrito;
DROP TABLE IF EXISTS ggviario.codigo;
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

DROP FUNCTION IF EXISTS rule.venda_insert(arg_colaborador_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_tvenda_id smallint, arg_venda_quantidade numeric, arg_venda_montanteunitario numeric, arg_venda_montantebruto numeric, arg_venda_montantedesconto numeric, arg_venda_montantepagar numeric, arg_venda_data date, arg_venda_datafinalizar date);
DROP FUNCTION IF EXISTS rule.venda_fatura_generatenext(_tipocompra ggviario.tipovenda);
DROP FUNCTION IF EXISTS rule.venda_estado_desc(_venda ggviario.venda, _const constant);
DROP FUNCTION IF EXISTS rule.setor_estado_desc(_setor ggviario.setor, _const constant);
DROP FUNCTION IF EXISTS rule.select_setor_cascade_witch_raiz(arg_setor_id uuid, part_lenght integer);
DROP FUNCTION IF EXISTS rule.select_setor_cascade(arg_setor_id uuid);
DROP FUNCTION IF EXISTS rule.produto_estado_desc(_produto ggviario.produto, _const constant);
DROP FUNCTION IF EXISTS rule.produto_estado(_produto ggviario.produto, _const constant);
DROP FUNCTION IF EXISTS rule.movimento_insert(arg_colaborador_id uuid, arg_conta_id uuid, arg_tmovimento_id smallint, arg_movimento_documento character varying, arg_movimento_data date, arg_movimento_montante numeric, arg_movimento_libele character varying, arg_venda_id uuid, arg_despesa_id uuid, arg_movimento_id uuid, arg_movimento_devolucao boolean, arg_movimento_transferencianumero integer);
DROP FUNCTION IF EXISTS rule.movimento_estado_desc(_movimento ggviario.movimento, _const constant);
DROP FUNCTION IF EXISTS rule.movimento_check_source(_movimento ggviario.movimento);
DROP FUNCTION IF EXISTS rule.functg_venda_intert_update_produto_stock();
DROP FUNCTION IF EXISTS rule.functg_setor_after_updade_disable_disable_children();
DROP FUNCTION IF EXISTS rule.functg_setor_after_insert_protect_parent();
DROP FUNCTION IF EXISTS rule.functg_producao_after_insert_movement_produto();
DROP FUNCTION IF EXISTS rule.functg_movimento_after_insert_update_venda();
DROP FUNCTION IF EXISTS rule.functg_movimento_after_insert_update_movimento_devolucao();
DROP FUNCTION IF EXISTS rule.functg_movimento_after_insert_update_despesa();
DROP FUNCTION IF EXISTS rule.functg_movimento_after_insert_update_conta();
DROP FUNCTION IF EXISTS rule.functg_despesa_after_insert_update_stock();
DROP FUNCTION IF EXISTS rule.fornecedor_estado_desc(_fornecedor ggviario.fornecedor, _const constant);
SET search_path = ggviario, pg_catalog;

DROP TABLE IF EXISTS ggviario.fornecedor;
SET search_path = rule, pg_catalog;

DROP FUNCTION IF EXISTS rule.despesa_estado_desc(_despesa ggviario.despesa, _const constant);
DROP FUNCTION IF EXISTS rule.codigo_next(character, smallint);
DROP FUNCTION IF EXISTS rule.codigo_generate(arg_codigo_letra character, arg_codigo_digitos smallint);
DROP FUNCTION IF EXISTS rule.codigo(arg_codigo_letra character, arg_codigo_ano smallint, arg_codigo_digitos smallint);
DROP FUNCTION IF EXISTS rule.cliente_estado_desc(_cliente ggviario.cliente, _const constant);
SET search_path = ggviario, pg_catalog;

DROP TABLE IF EXISTS ggviario.cliente;
SET search_path = rule, pg_catalog;

DROP FUNCTION IF EXISTS rule.categoria_estado_desc(_categoria ggviario.categoria, _const constant);
DROP FUNCTION IF EXISTS rule.audit_insert(arg_colaborador_id uuid, arg_audit_key character varying, arg_audit_title character varying, arg_audit_object jsonb, arg_audit_message character varying);
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

DROP FUNCTION IF EXISTS ggviario.get_venda(arg_venda_id uuid);
DROP FUNCTION IF EXISTS ggviario.get_unidade(arg_unidade_id uuid);
DROP TABLE IF EXISTS ggviario.unidade;
DROP FUNCTION IF EXISTS ggviario.get_tipovenda(arg_tvenda_id smallint);
DROP TABLE IF EXISTS ggviario.tipovenda;
DROP FUNCTION IF EXISTS ggviario.get_setor(arg_setor_id uuid);
DROP FUNCTION IF EXISTS ggviario.get_produto(arg_produto_id uuid);
DROP TABLE IF EXISTS ggviario.produto;
DROP FUNCTION IF EXISTS ggviario.get_preco(arg_preco_id uuid);
DROP TABLE IF EXISTS ggviario.preco;
DROP FUNCTION IF EXISTS ggviario.get_movimento(arg_movimento_id uuid);
DROP TABLE IF EXISTS ggviario.movimento;
DROP FUNCTION IF EXISTS ggviario.get_despesa(arg_despesa_id uuid);
DROP TABLE IF EXISTS ggviario.despesa;
DROP FUNCTION IF EXISTS ggviario.get_conta(arg_conta_id uuid);
DROP TABLE IF EXISTS ggviario.conta;
DROP FUNCTION IF EXISTS ggviario.get_categoria(arg_categoria_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_reg_venda_venda(arg_colaborador_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_conta_id uuid, arg_venda_quantidade numeric, arg_venda_montanteunitario numeric, arg_venda_montantebruto numeric, arg_venda_montantedesconto numeric, arg_venda_montantepagar numeric, arg_venda_data date);
DROP FUNCTION IF EXISTS ggviario.funct_reg_venda_divida(arg_colaborador_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_venda_quantidade numeric, arg_venda_montanteunitario numeric, arg_venda_montantebruto numeric, arg_venda_montantedesconto numeric, arg_venda_montantepagar numeric, arg_venda_data date, arg_venda_datafinalizar date);
DROP FUNCTION IF EXISTS ggviario.funct_reg_unidade(arg_colaborador_id uuid, arg_unidade_nome character varying, arg_unidade_codigo character varying);
DROP FUNCTION IF EXISTS ggviario.funct_reg_setor(arg_colaborador_id uuid, arg_setor_setor_id uuid, arg_setor_nome character varying);
DROP FUNCTION IF EXISTS ggviario.funct_reg_produto(arg_colaborador_id uuid, arg_categoria_id uuid, arg_produto_nome character varying, arg_produto_servicovenda boolean, arg_produdo_servicocompra boolean, arg_produto_servicoproducao boolean, arg_produto_servicostockminimo boolean, arg_produto_stockminimo numeric, options jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_reg_producao(arg_colaborador_id uuid, arg_producao_data date, arg_producao_quantidadetotal numeric, arg_producao_quantidadecomerciavel numeric, arg_producao_quantidadedefeituosa numeric, arg_produto_id uuid, arg_setor_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_reg_preco(arg_colaborador_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_preco_custounidade numeric, arg_preco_quantiadeproduto numeric, arg_preco_base boolean);
DROP FUNCTION IF EXISTS ggviario.funct_reg_movimento_amortizacao_venda(arg_colaborador_id uuid, arg_venda_id uuid, arg_conta_id uuid, arg_movimento_montante numeric, arg_movimento_data date, arg_movimento_documento character varying);
DROP FUNCTION IF EXISTS ggviario.funct_reg_movimento_amortizacao_despesa(arg_colaborador_id uuid, arg_despesa_id uuid, arg_conta_id uuid, arg_movimento_montante numeric, arg_movimento_data date, arg_movimento_documento character varying);
DROP FUNCTION IF EXISTS ggviario.funct_reg_localproducao(arg_colaborador_id uuid, arg_produto_id uuid, arg_setor_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_reg_fornecedor(arg_colaborador_id uuid, arg_fornecedor_nome character varying, arg_fornecedor_nif character varying, arg_fornecedor_telefone character varying, arg_fornecedor_telemovel character varying, arg_fornecedor_mail character varying, arg_fornecedor_local character varying, arg_distrito_id smallint);
DROP FUNCTION IF EXISTS ggviario.funct_reg_equivalencia(arg_colaborador_id uuid, arg_produto_id uuid, arg_equivalencias jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_reg_despesa(arg_colaborador_id uuid, arg_fornecedor_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_despesa_quantidade numeric, arg_despesa_custounitario numeric, arg_despesa_custototal numeric, arg_despesa_data date, arg_despesa_numerofatura character varying, arg_despesa_paga boolean, arg_conta_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_reg_cliente(arg_colaborador_id uuid, arg_sexo_id smallint, arg_distrito_id smallint, arg_tdocumenti_id smallint, arg_cliente_documentonumero character varying, arg_cliente_nome character varying, arg_cliente_apelido character varying, arg_cliente_datanascimento date, arg_cliente_telemovel character varying, arg_cliente_telefone character varying, arg_cliente_mail character varying, arg_cliente_morada character varying, arg_cliente_localtrabalho character varying);
DROP FUNCTION IF EXISTS ggviario.funct_reg_categoria(arg_colaborador_id uuid, arg_categoria_super uuid, arg_categoria_nome character varying);
DROP FUNCTION IF EXISTS ggviario.funct_load_venda_venda(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_venda_divida(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_unidade_simple_by_produto(arg_produto_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_load_unidade(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_tipodocumento(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_setor_by_localproducao_to_producao(arg_produto_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_load_setor_all(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_setor(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_produto_venda(arg_data date);
DROP FUNCTION IF EXISTS ggviario.funct_load_produto_unidades(arg_produto_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_load_produto_producao(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_produto_despesa(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_produto(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_producao_vista_setor(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_producao_vista_produto(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_producao_data(arg_produto_id uuid, arg_setor_id uuid, arg_producao_data date);
DROP FUNCTION IF EXISTS ggviario.funct_load_producao(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_movimento_venda(arg_venda_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_load_movimento_venda(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_localproducao(arg_produto_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_load_fornecedor(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_distrito(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_despesa(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_cliente_venda_venda(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_cliente_venda_divida(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_cliente(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_categoria_simple(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_load_categoria(filter jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_change_venda_anular(arg_colaborador_id uuid, arg_venda_id uuid, arg_textoconfirmacao text);
DROP FUNCTION IF EXISTS ggviario.funct_change_unidade(arg_colaborador_id uuid, arg_unidade_id uuid, arg_unidade_nome character varying, arg_unidade_codigo character varying);
DROP FUNCTION IF EXISTS ggviario.funct_change_setor_estado_mode(arg_colaborador_id uuid, arg_setor_id uuid, arg_estado_modo boolean);
DROP FUNCTION IF EXISTS ggviario.funct_change_setor(arg_colaborador_id uuid, arg_setor_id uuid, arg_setor_data jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_change_sector_structure();
DROP TABLE IF EXISTS ggviario.setor;
DROP FUNCTION IF EXISTS ggviario.funct_change_produto_setoptions(arg_colaborador_id uuid, arg_produto_id uuid, arg_produto_servicovenda boolean, arg_produto_servicocompra boolean, arg_produto_servicoproducao boolean, arg_produto_servicostockdinamico boolean, options jsonb);
DROP FUNCTION IF EXISTS ggviario.funct_change_produto_estado(arg_colaborador_id uuid, arg_produto_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_change_produto_data(arg_colaborador_id uuid, arg_produto_id uuid, arg_categoria_id uuid, arg_produto_nome character varying, arg_produto_servicovenda boolean, arg_produdo_servicocompra boolean, arg_produto_servicoproducao boolean, arg_produto_servicostockminimo boolean, arg_produto_stockminimo numeric);
DROP FUNCTION IF EXISTS ggviario.funct_change_preco_close(arg_colaborador_id uuid, arg_preco_id uuid, arg_conformacao_mensage character varying);
DROP FUNCTION IF EXISTS ggviario.funct_change_locaoproducao_disable(arg_colaborador_id uuid, arg_localproducao_id uuid);
DROP FUNCTION IF EXISTS ggviario.funct_change_categoria_structure();
DROP TABLE IF EXISTS ggviario.categoria;
SET search_path = lib, pg_catalog;

DROP FUNCTION IF EXISTS lib.is_normalized(text);
SET search_path = ggviario, pg_catalog;

DROP FUNCTION IF EXISTS ggviario.funct_change_categoria(arg_colaborador_id uuid, arg_categoria_id uuid, arg_categoria_nome character varying);
DROP FUNCTION IF EXISTS ggviario.compra_estado_desc(_compra venda, _const rule.constant);
DROP TABLE IF EXISTS ggviario.venda;
SET search_path = rule, pg_catalog;

DROP FUNCTION IF EXISTS rule.constant_init();
SET search_path = ggviario, pg_catalog;

DROP FUNCTION IF EXISTS ggviario.audit_insert(arg_colaborador_id uuid, arg_audit_key character varying, arg_audit_title character varying, arg_audit_object jsonb, arg_audit_message character varying);
DROP TABLE IF EXISTS ggviario.audit;
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
	preco_estado_ativo smallint,
	preco_estado_fechado smallint,
	setor_estado_ativo smallint,
	setor_estado_fechado smallint,
	setor_estado_protegido smallint,
	produto_estado_ativo smallint,
	produto_estado_fechado smallint,
	venda_estado_pendente smallint,
	venda_estado_empagamento smallint,
	venda_estado_pago smallint,
	venda_estado_anulado smallint,
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
	tipovenda_divida smallint,
	tipovenda_venda smallint,
	cliente_estado_ativo smallint,
	cliente_estado_fechado smallint,
	codigo_amortizacao_letra character(1),
	codigo_amortizacao_digitos smallint,
	codigo_venda_letra character varying,
	codigo_venda_digitos smallint,
	codigo_divida_letra character varying,
	codigo_divida_digitos smallint,
	codigo_despesa_letra character(1),
	codigo_despesa_digitos smallint,
	codigo_cliente_letra character(1),
	codigo_cliente_digitos smallint,
	categoria_estado_ativo smallint,
	categoria_estado_fechado smallint,
	codigo_setor_letra character(1),
	codigo_setor_digitos smallint,
	codigo_producao_letra character(1),
	codigo_producao_digitos smallint,
	codigo_produto_digitos smallint,
	codigo_produto_letra character(1),
	produto_estado_stock_minimo smallint,
	produto_estado_stock_vazio smallint,
	produto_estado_stock_ok smallint,
	produto_estado_ok smallint,
	localproducao_estado_ativo smallint,
	localproducao_estado_fechado smallint,
	localproducao_estado_herdado smallint
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


SET search_path = ggviario, pg_catalog;

--
-- Name: audit; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE audit (
    audit_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    audit_colaborador_id uuid,
    audit_key character varying(64) NOT NULL,
    audit_title character varying(64) NOT NULL,
    audit_message character varying(256) NOT NULL,
    audit_object jsonb,
    audit_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


--
-- Name: audit_insert(uuid, character varying, character varying, jsonb, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION audit_insert(arg_colaborador_id uuid, arg_audit_key character varying, arg_audit_title character varying, arg_audit_object jsonb, arg_audit_message character varying) RETURNS audit
    LANGUAGE sql
    AS $$
insert into ggviario.audit(
  audit_colaborador_id,
  audit_title,
  audit_key,
  audit_object,
  audit_message
) values (
  arg_colaborador_id,
  arg_audit_title,
  arg_audit_key,
  arg_audit_object,
  arg_audit_message
) returning * ;
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

  --// Estdao de preco
  _const.preco_estado_ativo := 1;
  _const.preco_estado_fechado := 0;

  --//Estado de setor
  _const.setor_estado_protegido := 2;
  _const.setor_estado_ativo := 1;
  _const.setor_estado_fechado := 0;

  --//Estado de produto
  _const.produto_estado_stock_vazio := 3;
  _const.produto_estado_stock_minimo := 2;
  _const.produto_estado_ok := 1;
  _const.produto_estado_ativo := 1;
  _const.produto_estado_fechado := 0;

  --//Estados de venda
  _const.venda_estado_pendente := 2;
  _const.venda_estado_empagamento := 1;
  _const.venda_estado_pago := 0;
  _const.venda_estado_anulado := -1;

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

  --//Tipo de venda
  _const.tipovenda_venda := 1;
  _const.tipovenda_divida := 2;

  --//Esatdo dos cliente
  _const.cliente_estado_ativo := 1;
  _const.cliente_estado_fechado := 1;

  --//Estado de categoria
  _const.categoria_estado_ativo := 1;
  _const.categoria_estado_fechado := 0;


  --//codigo
  --reserved
  _const.codigo_venda_letra := 'V';
  _const.codigo_divida_letra := 'D';

  _const.codigo_amortizacao_letra := 'A';
  _const.codigo_despesa_letra := 'G';
  _const.codigo_cliente_letra := 'C';
  _const.codigo_setor_letra := 'S';
  _const.codigo_producao_letra := 'E';
  _const.codigo_produto_letra := 'P';

  _const.codigo_venda_digitos := 5;
  _const.codigo_divida_digitos := 5;
  _const.codigo_amortizacao_digitos := 6;
  _const.codigo_despesa_digitos := 5;
  _const.codigo_cliente_digitos := 4;
  _const.codigo_setor_digitos := 2;
  _const.codigo_producao_digitos := 5;
  _const.codigo_produto_digitos := 2;

  _const.localproducao_estado_ativo := 1;
  _const.localproducao_estado_fechado := 0;
  _const.localproducao_estado_herdado := 2;




  return _const;
end;
$$;


SET search_path = ggviario, pg_catalog;

--
-- Name: venda; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE venda (
    venda_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    venda_produto_id uuid NOT NULL,
    venda_unidade_id uuid NOT NULL,
    venda_cliente_id uuid NOT NULL,
    venda_tvenda_id smallint NOT NULL,
    venda_colaborador_id uuid NOT NULL,
    venda_colaborador_atualizacao uuid,
    venda_faturanumero character varying(16) NOT NULL,
    venda_quantidade numeric NOT NULL,
    venda_quantidadeproduto numeric NOT NULL,
    venda_montanteunitario numeric NOT NULL,
    venda_montantebruto numeric NOT NULL,
    venda_montantedesconto numeric NOT NULL,
    venda_montantepagar numeric NOT NULL,
    venda_montanteamortizado numeric DEFAULT 0 NOT NULL,
    venda_data date,
    venda_datafinalizar date NOT NULL,
    venda_datafim timestamp without time zone,
    venda_dataultimamovimentacao date,
    venda_observacao character varying(128),
    venda_estado smallint DEFAULT 3 NOT NULL,
    venda_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    venda_dataatualizacao timestamp without time zone
);


--
-- Name: COLUMN venda.venda_observacao; Type: COMMENT; Schema: ggviario; Owner: -
--

COMMENT ON COLUMN venda.venda_observacao IS 'Observação da venda';


--
-- Name: compra_estado_desc(venda, rule.constant); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION compra_estado_desc(_compra venda, _const rule.constant DEFAULT rule.constant_init()) RETURNS character varying
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
-- Name: funct_change_categoria(uuid, uuid, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_categoria(arg_colaborador_id uuid, arg_categoria_id uuid, arg_categoria_nome character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
  _categoria ggviario.categoria;
begin
  _const := rule.constant_init();

  update ggviario.categoria
    set categoria_nome = arg_categoria_nome,
        categoria_colaborador_atualizacao = arg_colaborador_id,
        categoria_dataatualizacao = current_timestamp
    where categoria_id = arg_categoria_id
    returning * into _categoria;

  return lib.result_true(
    jsonb_build_object(
      'categoria', _categoria
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
    categoria_codigo character varying(6),
    categoria_nome character varying(32) NOT NULL,
    categoria_posisao smallint,
    categoria_nivel smallint DEFAULT 0 NOT NULL,
    categoria_estado smallint DEFAULT 1 NOT NULL,
    categoria_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    categoria_dataatualizacao timestamp without time zone,
    CONSTRAINT ck_categoria_nome_is_normalized CHECK (lib.is_normalized((categoria_nome)::text))
);


--
-- Name: funct_change_categoria_structure(); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_categoria_structure() RETURNS SETOF categoria
    LANGUAGE plpgsql
    AS $$
declare
  _data record;
  _categoria ggviario.categoria;
  increment int default 0;
begin
  for _data in (
    with recursive rel_tree as (
      select cat.*, array[ cat.categoria_nome::text::character varying ] as path_order
      from ggviario.categoria cat
      where cat.categoria_categoria_id is null
      union all
      select  child.*, parent.path_order||child.categoria_nome as path_order
      from ggviario.categoria child
        join rel_tree parent on parent.categoria_id = child.categoria_categoria_id
    )
    select row_number() OVER () -1 as position, tree.*
    from rel_tree tree
    order by path_order
  ) loop
    update ggviario.categoria
      set categoria_posisao = increment
      where categoria_id = _data.categoria_id
      returning * into _categoria;

    increment := increment +1;
    return next _categoria;
  end loop;
end;
$$;


--
-- Name: funct_change_locaoproducao_disable(uuid, uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_locaoproducao_disable(arg_colaborador_id uuid, arg_localproducao_id uuid) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
  declare 
    _const rule.constant;
    _localproducao ggviario.localproducao;
  begin 
    _const := rule.constant_init();
    update ggviario.localproducao
      set localproducao_estado = _const.localproducao_estado_fechado,
          localproducao_dataatualizacao = current_timestamp,
          localproducao_colaborador_atualizacao = arg_colaborador_id
      where localproducao_id = arg_localproducao_id
        and localproducao_estado = _const.localproducao_estado_ativo
      returning * into _localproducao 
    ;
    
    if _localproducao.localproducao_id is null then
      return lib.result_false(
        'Não pode desitivar esse local de produção!'
      );
    end if;
    
    return lib.result_true(
      jsonb_build_object(
        'localproducao', _localproducao
      )
    );
  end;
$$;


--
-- Name: funct_change_preco_close(uuid, uuid, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_preco_close(arg_colaborador_id uuid, arg_preco_id uuid, arg_conformacao_mensage character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _preco ggviario.preco;
  _const rule.constant;
begin
  _const := rule.constant_init();
  _preco := ggviario.get_preco( arg_preco_id );
  arg_conformacao_mensage := lib.normalize( arg_conformacao_mensage );
  if _preco.preco_estado = _const.preco_estado_fechado then
    return lib.result_false ( 'Não pode mais fechar esse preco!' );
  end if;

  update ggviario.preco
    set preco_estado = _const.preco_estado_fechado,
      preco_colaborador_atualizacao = arg_colaborador_id,
      preco_dataatualizacao =  current_timestamp
    where preco_id = arg_preco_id
    returning * into _preco
  ;

  if arg_conformacao_mensage is not null then
    perform rule.audit_insert( 
        arg_colaborador_id,
        'ggviario.preco.disable',
        'Fecho do preco',
        jsonb_build_object(
          'preco_id', _preco.preco_id
        ),
        arg_conformacao_mensage
    );
  end if;

  return lib.result_true(
    jsonb_build_object(
      'preco', _preco
    )
  );
end;
$$;


--
-- Name: funct_change_produto_data(uuid, uuid, uuid, character varying, boolean, boolean, boolean, boolean, numeric); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_produto_data(arg_colaborador_id uuid, arg_produto_id uuid, arg_categoria_id uuid, arg_produto_nome character varying, arg_produto_servicovenda boolean, arg_produdo_servicocompra boolean, arg_produto_servicoproducao boolean, arg_produto_servicostockminimo boolean, arg_produto_stockminimo numeric) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _produto ggviario.produto;
  _const rule.constant;
begin
  _const := rule.constant_init();
  if (
       select count( * )
        from ggviario.produto pro
        where lower( pro.produto_nome ) = lower( arg_produto_nome )
          and pro.produto_id != arg_produto_id
     ) > 0  then
    return lib.result_false(
        'Existe outro produto com esse nome!'
    );
  end if;

  update produto
    set produto_nome = arg_produto_nome,
        produto_categoria_id = arg_categoria_id,
        produto_stockminimo = arg_produto_stockminimo,
        produto_servicovenda = arg_produto_servicovenda,
        produto_servicocompra = arg_produdo_servicocompra,
        produto_servicoproducao = arg_produto_servicoproducao,
        produto_servicostockdinamico = arg_produto_servicostockminimo,
        produto_colaborador_atualizacao = arg_colaborador_id,
        produto_dataatualizacao = current_timestamp
    where produto_id = arg_produto_id
    returning * into _produto;

  return lib.result_true(
      jsonb_build_object(
          'produto', _produto
      )
  );
end;
$$;


--
-- Name: funct_change_produto_estado(uuid, uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_produto_estado(arg_colaborador_id uuid, arg_produto_id uuid) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
    _produto ggviario.produto;
    _const rule.constant;
  begin
    _produto := ggviario.get_produto( arg_produto_id );
    _const := rule.constant_init();

    if _produto.produto_estado = _const.produto_estado_ativo then
      _produto.produto_estado = _const.produto_estado_fechado;
    elsif _produto.produto_estado = _const.produto_estado_fechado then
      _produto.produto_estado = _const.produto_estado_ativo;
    END IF;

    update ggviario.produto
      set produto_estado = _produto.produto_estado,
          produto_colaborador_atualizacao = arg_colaborador_id,
          produto_dataatualizacao = current_timestamp
      where produto_id = _produto.produto_id
      returning * into _produto;

    _produto.produto_estado = rule.produto_estado( _produto, _const );
    

    return lib.result_true(
      jsonb_build_object(
        'produto', _produto
      )
    );
  end;
$$;


--
-- Name: funct_change_produto_setoptions(uuid, uuid, boolean, boolean, boolean, boolean, jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_produto_setoptions(arg_colaborador_id uuid, arg_produto_id uuid, arg_produto_servicovenda boolean, arg_produto_servicocompra boolean, arg_produto_servicoproducao boolean, arg_produto_servicostockdinamico boolean, options jsonb) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _produto ggviario.produto;
begin

  _produto := ggviario.get_produto( arg_produto_id );
  update ggviario.produto
  set produto_colaborador_atualizacao = arg_colaborador_id,
    produto_dataatualizacao = current_timestamp,
    produto_servicovenda = arg_produto_servicovenda,
    produto_servicocompra = arg_produto_servicocompra,
    produto_servicoproducao = arg_produto_servicoproducao,
    produto_servicostockdinamico = arg_produto_servicostockdinamico
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
-- Name: setor; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE setor (
    setor_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    setor_setor_id uuid,
    setor_colaborador_id uuid NOT NULL,
    setor_colaborador_atualizacao uuid,
    setor_codigo character varying(6) NOT NULL,
    setor_nome character varying(32) NOT NULL,
    setor_posicao smallint,
    setor_nivel smallint DEFAULT 0 NOT NULL,
    setor_totalsubsetores smallint DEFAULT 0 NOT NULL,
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
-- Name: funct_change_sector_structure(); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_sector_structure() RETURNS SETOF setor
    LANGUAGE plpgsql
    AS $$
declare
    _data record;
    _setor ggviario.setor;
  increment int default 0;
  begin
    for _data in (
      with recursive rel_tree as (
        select se.*, array[ setor_nome::text::character varying ] as path_order
        from setor se
        where se.setor_setor_id is null
        union all
        select  child.*, parent.path_order||child.setor_nome as path_order
        from setor child
          join rel_tree parent on parent.setor_id = child.setor_setor_id
      )
      select row_number() OVER () -1 as position, tree.*
      from rel_tree tree
      order by path_order
    ) loop
      update ggviario.setor
        set setor_posicao = increment
        where setor_id = _data.setor_id
        returning * into _setor;

      increment := increment +1;
      return next _setor;
    end loop;
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

  if arg_setor_id = lib.to_uuid( 1 ) then
    return lib.result_false( 'Não pode alterar o estado do setor padrão!' );
  end if;

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
-- Name: funct_change_unidade(uuid, uuid, character varying, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_unidade(arg_colaborador_id uuid, arg_unidade_id uuid, arg_unidade_nome character varying, arg_unidade_codigo character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
  _unidade ggviario.unidade;
begin
  _const := rule.constant_init();

  update ggviario.unidade
    set unidade_nome = arg_unidade_nome,
        unidade_codigo = arg_unidade_codigo,
        unidade_colaborador_atualizacao = arg_colaborador_id,
        unidade_dataatualizacao = current_timestamp
    where unidade_id = arg_unidade_id
    returning * into _unidade;

  return lib.result_true(
    jsonb_build_object(
      'unidade', _unidade
    )
  );
end;

$$;


--
-- Name: funct_change_venda_anular(uuid, uuid, text); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_change_venda_anular(arg_colaborador_id uuid, arg_venda_id uuid, arg_textoconfirmacao text) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
  _venda record;
  tvenda character varying;
begin
  _const := rule.constant_init();
  select * into _venda
    from ggviario.venda ve
      inner join ggviario.tipovenda tv on ve.venda_tvenda_id = tv.tvenda_id
    where ve.venda_id = arg_venda_id;

  if _venda.venda_estado = _const.venda_estado_anulado then
    return lib.result_false( 'Não pode anular uma '||_venda.tvenda_desc||' no estado anulado!' );
  end if;

  if _venda.tvenda_id = _const.tipovenda_divida
    and _venda.venda_estado < _const.venda_estado_pendente then
    return lib.result_false( 'Não pode mais anular essa divida!' );
  end if;

  tvenda := _venda.tvenda_desc;

  update ggviario.venda
    set venda_estado = _const.venda_estado_anulado,
        venda_colaborador_atualizacao = arg_colaborador_id,
        venda_dataatualizacao = current_timestamp,
        venda_observacao = arg_textoconfirmacao
    where venda_id = arg_venda_id
    returning * into _venda;

  perform rule.audit_insert(
    arg_colaborador_id,
    'ggviario.venda.anular',
    'Anulação de '||tvenda,
    jsonb_build_object( 'venda_id', arg_venda_id ),
    arg_textoconfirmacao
  );

  return lib.result_true(
    jsonb_build_object(
      'venda', _venda
    )
  );
end;
$$;


--
-- Name: funct_load_categoria(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_categoria(filter jsonb) RETURNS TABLE(categoria_id uuid, categoria_codigo character varying, categoria_nome character varying, categoria_nivel smallint, categoria_dataregisto timestamp without time zone, categoria_dataatualizacao timestamp without time zone, categoria_estado smallint, categoria_estadodesc character varying, categoria_super jsonb, categoria_colaborador jsonb, categoria_colaboradoratualizacao jsonb)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query
  select
    cat.categoria_id,
    cat.categoria_codigo,
    cat.categoria_nome,
    cat.categoria_nivel,
    cat.categoria_dataregisto,
    cat.categoria_dataatualizacao,
    cat.categoria_estado,
    rule.categoria_estado_desc( cat, _const ),
    to_jsonb( super ) || jsonb_build_object(
      'categoria_nome',  coalesce( super.categoria_nome, '<<Indisponivél>>' )
    ),
    to_jsonb( col ) - 'colaborador_senha',
    to_jsonb( colup ) - 'colaborador_senha'
  from ggviario.categoria cat
    inner join ggviario.colaborador.colaborador col on cat.categoria_colaborador_id = col.colaborador_id
    left join ggviario.categoria super on cat.categoria_categoria_id = super.categoria_id
    left join ggviario.colaborador.colaborador colup on cat.categoria_colaborador_atualizacao = colup.colaborador_id

  order by cat.categoria_posisao;
end;
$$;


--
-- Name: funct_load_categoria_simple(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_categoria_simple(filter jsonb) RETURNS TABLE(categoria_id uuid, categoria_nome character varying, categoria_estado smallint)
    LANGUAGE plpgsql
    AS $$
declare 
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query
    select cat.categoria_id,
        cat.categoria_nome,
        cat.categoria_estado
      from ggviario.categoria cat 
      where cat.categoria_estado = _const.categoria_estado_ativo 
      order by cat.categoria_posisao asc;
end;
$$;


--
-- Name: funct_load_cliente(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_cliente(filter jsonb) RETURNS TABLE(cliente_id uuid, cliente_codigo character varying, cliente_nome character varying, cliente_apelido character varying, cliente_datanascimento date, cliente_documentonumero character varying, cliente_mail character varying, cliente_morada character varying, cliente_telefone character varying, cliente_telemovel character varying, cliente_localtrabalho character varying, cliente_dataregisto timestamp without time zone, cliente_estado smallint, cliente_estadodesc character varying, sexo_id smallint, sexo_desc character varying, distrito_id smallint, distrito_nome character varying, tdocumento_id smallint, tdocumento_desc character varying, cliente_montentecompras numeric, cliente_montentedividas numeric, cliente_montantetatal numeric, cliente_montanteamortizado numeric, cliente_montantependente numeric)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query select
      cli.cliente_id,
      cli.cliente_codigo,
      cli.cliente_nome,
      cli.cliente_apelido,
      cli.cliente_datanascimento,
      cli.cliente_documentonumero,
      cli.cliente_mail,
      cli.cliente_morada,
      cli.cliente_telefone,
      cli.cliente_telemovel,
      cli.cliente_localtrabalho,
      cli.cliente_dataregisto,
      cli.cliente_estado,
      rule.cliente_estado_desc( cli, _const ),
      se.sexo_id,
      se.sexo_desc,
      dist.distrito_id,
      dist.distrito_nome,
      tdoc.tdocumento_id,
      tdoc.tdocumento_desc,
      sum( cp.venda_montantepagar ) filter ( where cp.venda_tvenda_id = _const.tipovenda_venda ) as cliente_montentecompras,
      sum( cp.venda_montantepagar ) filter ( where cp.venda_tvenda_id = _const.tipovenda_divida ) as cliente_montentedividas,
      sum( cp.venda_montantepagar ) as cliente_montantetatal,
      sum( cp.venda_montanteamortizado ) as cliente_montanteamortizado,
      sum( cp.venda_montantepagar - cp.venda_montanteamortizado ) as cliente_montantependente
    from cliente cli
      left join ggviario.sexo se on cli.cliente_sexo_id = se.sexo_id
      left join ggviario.distrito dist on cli.cliente_distrito_id = dist.distrito_id
      left join ggviario.tipodocumento tdoc on cli.cliente_tdocumento_id = tdoc.tdocumento_id
      left join ggviario.venda cp on cli.cliente_id = cp.venda_cliente_id
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
-- Name: funct_load_cliente_venda_divida(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_cliente_venda_divida(filter jsonb) RETURNS TABLE(cliente_id uuid, cliente_nome character varying, cliente_apelido character varying, cliente_datanascimento date, cliente_documentonumero character varying, cliente_mail character varying, cliente_morada character varying, cliente_telefone character varying, cliente_telemovel character varying, cliente_localtrabalho character varying, cliente_estado smallint, cliente_estadodesc character varying, sexo_id smallint, sexo_desc character varying, cliente_montantetatal numeric, cliente_montantependente numeric)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
  arg_cliente_anonimo_id uuid default lib.to_uuid( 1 );
begin
  _const := rule.constant_init();
  return query
  select
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
      sum( vd.venda_montantepagar ) as cliente_montantetatal,
      sum( vd.venda_montantepagar - vd.venda_montanteamortizado ) as cliente_montantependente
    from cliente cli
      left join ggviario.sexo se on cli.cliente_sexo_id = se.sexo_id
      left join ggviario.venda vd on cli.cliente_id = vd.venda_cliente_id
    where cli.cliente_id != arg_cliente_anonimo_id
    group by cli.cliente_id,
      se.sexo_id
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
-- Name: funct_load_cliente_venda_venda(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_cliente_venda_venda(filter jsonb) RETURNS TABLE(cliente_id uuid, cliente_nome character varying, cliente_apelido character varying, cliente_datanascimento date, cliente_documentonumero character varying, cliente_mail character varying, cliente_morada character varying, cliente_telefone character varying, cliente_telemovel character varying, cliente_localtrabalho character varying, cliente_estado smallint, cliente_estadodesc character varying, sexo_id smallint, sexo_desc character varying, cliente_montantetatal numeric, cliente_montantependente numeric)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query
  select
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
      sum( vd.venda_montantepagar ) as cliente_montantetatal,
      sum( vd.venda_montantepagar - vd.venda_montanteamortizado ) as cliente_montantependente
    from cliente cli
      left join ggviario.sexo se on cli.cliente_sexo_id = se.sexo_id
      left join ggviario.venda vd on cli.cliente_id = vd.venda_cliente_id
    group by cli.cliente_id,
      se.sexo_id
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
-- Name: funct_load_despesa(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_despesa(filter jsonb) RETURNS TABLE(despesa_id uuid, despesa_codigo character varying, despesa_data date, despesa_numerofatura character varying, despesa_quatidade numeric, despesa_quantidadeproduto numeric, despesa_montanteunitario numeric, despesa_montantetotal numeric, despesa_montanteamortizado numeric, despesa_dataultimamovimento date, despesa_datafim timestamp without time zone, despesa_dataregisto timestamp without time zone, despesa_dataatualizacao timestamp without time zone, despesa_estado smallint, despesa_estadodesc character varying, fornecedor_id uuid, fornecedor_nome character varying, fornecedor_local character varying, fornecedor_telemovel character varying, fornecedor_telefone character varying, fornecedor_montantetotal numeric, fornecedor_montantepago numeric, fornecedor_montantependente numeric, produto_id uuid, produto_nome character varying, produto_codigo character varying, unidade_id uuid, unidade_nome character varying, unidade_codigo character varying, colaborador jsonb, colaborador_atualizacao jsonb)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query
    select de.despesa_id,
        de.despesa_codigo,
        de.despesa_data,
        de.despesa_numerofatura,
        de.despesa_quatidade,
        de.despesa_quantidadeproduto,
        de.despesa_montanteunitario,
        de.despesa_montantetotal,
        de.despesa_montanteamortizado,
        de.despesa_dataultimamovimento,
        de.despesa_datafim,
        de.despesa_dataregisto,
        de.despesa_dataatualizacao,
        de.despesa_estado,
        rule.despesa_estado_desc( de, _const ),
        fo.fornecedor_id,
        fo.fornecedor_nome,
        fo.fornecedor_local,
        fo.fornecedor_telemovel,
        fo.fornecedor_telefone,
        sum( alloffornecedor.despesa_montantetotal ),
        sum( alloffornecedor.despesa_montanteamortizado ),
        coalesce( sum( alloffornecedor.despesa_montantetotal ), 0 ) - coalesce( sum( alloffornecedor.despesa_montanteamortizado ), 0 ),
        pr.produto_id,
        pr.produto_nome,
        pr.produto_codigo,
        un.unidade_id,
        un.unidade_nome,
        un.unidade_codigo,
        to_jsonb( col ),
        to_jsonb( up )
      from ggviario.despesa de
        inner join ggviario.produto pr on de.despesa_produto_id = pr.produto_id
        inner join ggviario.unidade un on de.despesa_unidade_id = un.unidade_id
        inner join ggviario.fornecedor fo on de.despesa_fornecedor_id = fo.fornecedor_id
        inner join ggviario.colaborador.colaborador col on de.despesa_colaborador_id = col.colaborador_id
        left join colaborador.colaborador up on de.despesa_colaborador_atualizacao = up.colaborador_id
        left join ggviario.despesa alloffornecedor on fo.fornecedor_id = alloffornecedor.despesa_fornecedor_id
      group by de.despesa_id,
        pr.produto_id,
        un.unidade_id,
        fo.fornecedor_id,
        col.colaborador_id,
        up.colaborador_id
      order by
        case
          when current_date - de.despesa_data::date <= 2 then 1
          else 2
        end asc,
        case
          when de.despesa_estado = _const.despesa_estado_pendente then 1
          when de.despesa_estado = _const.despesa_estado_empagamento then 2
          when de.despesa_estado = _const.despesa_estado_pago then 3
          when de.despesa_estado = _const.despesa_estado_anulado then 4
        end asc,
        de.despesa_data desc,
        fo.fornecedor_nome
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
-- Name: funct_load_fornecedor(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_fornecedor(filter jsonb) RETURNS TABLE(fornecedor_id uuid, fornecedor_nome character varying, fornecedor_nif character varying, fornecedor_telefone character varying, fornecedor_telemovel character varying, fornecedor_mail character varying, fornecedor_local character varying, fornecedor_dataregisto timestamp without time zone, fornecedor_estado smallint, fornecedor_estadodesc character varying, fornecedor_compras numeric, fornecedor_compraspagas numeric, fornecedor_compraspendentes numeric, distrito_id smallint, distrito_nome character varying, colaborador_id uuid, colaborador_nome character varying, colaborador_apelido character varying)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query
    select fo.fornecedor_id,
        fo.fornecedor_nome,
        fo.fornecedor_nif,
        fo.fornecedor_telefone,
        fo.fornecedor_telemovel,
        fo.fornecedor_mail,
        fo.fornecedor_local,
        fo.fornecedor_dataregisto,
        fo.fornecedor_estado,
        rule.fornecedor_estado_desc( fo, _const ),
        sum( des.despesa_montantetotal ) as compras,
        sum( des.despesa_montantetotal ) filter ( where des.despesa_estado = _const.despesa_estado_pago ) as compras_pagas,
        sum( des.despesa_montantetotal ) filter ( where des.despesa_estado in ( _const.despesa_estado_pendente, _const.despesa_estado_empagamento ) ) compras_pendentes,
        dis.distrito_id,
        dis.distrito_nome,
        co.colaborador_id,
        co.colaborador_nome,
        co.colaborador_apelido
      from ggviario.fornecedor fo
        inner join ggviario.colaborador.colaborador co  on fo.fornecedor_colaborador_id = co.colaborador_id
        left join ggviario.distrito dis on fo.fornecedor_distrito_id = dis.distrito_id
        left join ggviario.despesa des on fo.fornecedor_id = des.despesa_fornecedor_id
      group by fo.fornecedor_id,
        co.colaborador_id,
        dis.distrito_id
      order by compras_pendentes desc,
        fo.fornecedor_nome asc
      ;
end;
$$;


--
-- Name: funct_load_localproducao(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_localproducao(arg_produto_id uuid) RETURNS TABLE(localproducao_id uuid, localproducao_estado smallint, localproducao_dataregisto timestamp without time zone, localproducao_dataatualizacao timestamp without time zone, setor_id uuid, localproducao_quantidade numeric, setor jsonb, setor_super jsonb, colaborador jsonb, colaborador_atualizacao jsonb)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query
  select
    loc.localproducao_id,
    case
    when loc.localproducao_setor_id = se.setor_id then 1
    else 2
    end::int2 as estado,
    loc.localproducao_dataregisto,
    loc.localproducao_dataatualizacao,
    loc.localproducao_setor_id,
    sum( porc.producao_quantidadetotal ),
    to_jsonb( se ),
    to_jsonb( sesudo ),
    to_jsonb( col ),
    to_jsonb( coluop )
  from ggviario.localproducao loc
    inner join rule.select_setor_cascade( loc.localproducao_setor_id ) se on true
    inner join ggviario.colaborador.colaborador col on loc.localproducao_colaborador_id = col.colaborador_id
    left join ggviario.colaborador.colaborador coluop on loc.localproducao_colaborador_atualizacao = coluop.colaborador_id
    left join ggviario.setor sesudo on se.setor_setor_id = sesudo.setor_id
    left join ggviario.producao porc on se.setor_id = porc.producao_setor_id
  where loc.localproducao_produto_id = arg_produto_id
        and loc.localproducao_estado = _const.localproducao_estado_ativo
  group by
    loc.localproducao_id,
    sesudo.setor_id,
    se.setor_id,
    se.setor_posicao,
    to_jsonb( se ),
    col.colaborador_id,
    coluop.colaborador_id
  order by estado asc,
    se.setor_posicao asc
  ;
end;
$$;


--
-- Name: funct_load_movimento_venda(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_movimento_venda(filter jsonb) RETURNS TABLE(movimento_id uuid, movimento_codigo character varying, movimento_data date, movimento_documento character varying, movimento_montante numeric, movimento_dataregisto timestamp without time zone, movimento_libele character varying, movimento_estado smallint, movimento_estadodesc character varying)
    LANGUAGE plpgsql
    AS $$
declare
  arg_venda_id uuid default filter->>'venda_id';
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query
    select
        mv.movimento_id,
        mv.movimento_codigo,
        mv.movimento_data,
        mv.movimento_documento,
        mv.movimento_montante,
        mv.movimento_dataregisto,
        mv.movimento_libele,
        mv.movimento_estado,
        rule.movimento_estado_desc( mv, _const )
      from ggviario.movimento mv
      where mv.movimento_venda_id = arg_venda_id;
end;
$$;


--
-- Name: funct_load_movimento_venda(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_movimento_venda(arg_venda_id uuid) RETURNS TABLE(movimento_id uuid, movimento_codigo character varying, movimento_data date, movimento_documento character varying, movimento_montante numeric, movimento_dataregisto timestamp without time zone, movimento_libele character varying, movimento_estado smallint, movimento_estadodesc character varying)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query
    select
        mv.movimento_id,
        mv.movimento_codigo,
        mv.movimento_data,
        mv.movimento_documento,
        mv.movimento_montante,
        mv.movimento_dataregisto,
        mv.movimento_libele,
        mv.movimento_estado,
        rule.movimento_estado_desc( mv, _const )
      from ggviario.movimento mv
      where mv.movimento_venda_id = arg_venda_id;
end;
$$;


--
-- Name: funct_load_producao(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_producao(filter jsonb) RETURNS TABLE(producao_data date, producao_quantidade numeric, producao_lancamento bigint, produto_id uuid, produto_nome character varying, produto_codigo character varying, setor_id uuid, setor_nome character varying, setor_codigo character varying)
    LANGUAGE sql
    AS $$
select
  pro.producao_data,
  sum( pro.producao_quantidade ),
  count( * ),
  prod.produto_id,
  prod.produto_nome,
  prod.produto_codigo,
  se.setor_id,
  se.setor_nome,
  se.setor_codigo
from ggviario.producao pro
  inner join ggviario.produto prod on pro.producao_produto_id = prod.produto_id
  inner join ggviario.setor se on pro.producao_setor_id = se.setor_id
group by pro.producao_data,
  prod.produto_id,
  se.setor_id
$$;


--
-- Name: funct_load_producao_data(uuid, uuid, date); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_producao_data(arg_produto_id uuid, arg_setor_id uuid, arg_producao_data date) RETURNS TABLE(producao_dia numeric, producao_setor numeric)
    LANGUAGE sql
    AS $$
select 
     coalesce(  sum( pr.producao_quantidadetotal ) filter ( where pr.producao_produto_id = arg_produto_id ), 0 ),
     coalesce( sum( pr.producao_quantidadetotal ) filter ( where pr.producao_produto_id = arg_produto_id  and pr.producao_setor_id = arg_setor_id ), 0 )
    from ggviario.producao pr 
    where pr.producao_data = arg_producao_data;
$$;


--
-- Name: funct_load_producao_vista_produto(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_producao_vista_produto(filter jsonb) RETURNS TABLE(producao_data date, producao_quantidadetotal numeric, producao_quantidadecomerciavel numeric, producao_quantidadedefeituosa numeric, producao_montanteprevisto numeric, producao_lancamentos bigint, produto jsonb, categoria jsonb)
    LANGUAGE plpgsql
    AS $$
declare
  DATE_FORMAT_DAY character varying default 'day';
  DATE_FORMAT_MONTH character varying default 'month';
  DATE_FORMAT_YEAR character varying default 'year';
  arg_format character varying;
  arg_format_use character varying default filter->>'format_type';
begin
  if arg_format_use = DATE_FORMAT_DAY then arg_format := 'yyyy-mm-dd'; end if;
  if arg_format_use = DATE_FORMAT_MONTH then arg_format := 'yyyy-mm'; end if;
  if arg_format_use = DATE_FORMAT_YEAR then arg_format := 'yyyy'; end if;

  return query
    select
        to_date( to_char( prodc.producao_data, arg_format ), arg_format ) date,
        sum( prodc.producao_quantidadetotal ),
        sum( prodc.producao_quantidadecomerciavel ),
        sum( prodc.producao_quantidadedefeituosa ),
        sum( prodc.producao_montanteprevisto ),
        count( prodc.producao_id ),
        to_jsonb( pro ),
        to_jsonb( cat )
      from ggviario.producao prodc
        inner join ggviario.produto pro on prodc.producao_produto_id = pro.produto_id
        inner join ggviario.categoria cat on pro.produto_categoria_id = cat.categoria_id
      group by to_date( to_char( prodc.producao_data, arg_format ), arg_format ),
        pro.produto_id,
        cat.categoria_id
      order by date desc;
end;
$$;


--
-- Name: funct_load_producao_vista_setor(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_producao_vista_setor(filter jsonb) RETURNS TABLE(producao_data date, producao_quantidadetotal numeric, producao_quantidadecomerciavel numeric, producao_quantidadedefeituosa numeric, producao_montanteprevisto numeric, producao_lancamentos bigint, setor jsonb, setor_super jsonb)
    LANGUAGE plpgsql
    AS $$
declare
  DATE_FORMAT_DAY character varying default 'day';
  DATE_FORMAT_MONTH character varying default 'month';
  DATE_FORMAT_YEAR character varying default 'year';
  arg_format character varying;
  arg_format_use character varying default filter->>'format_type';
begin
  if arg_format_use = DATE_FORMAT_DAY then arg_format := 'yyyy-mm-dd'; end if;
  if arg_format_use = DATE_FORMAT_MONTH then arg_format := 'yyyy-mm'; end if;
  if arg_format_use = DATE_FORMAT_YEAR then arg_format := 'yyyy'; end if;
  return query
    select
        to_date( to_char( prodc.producao_data, arg_format ), arg_format ) date,
        sum( prodc.producao_quantidadetotal ),
        sum( prodc.producao_quantidadecomerciavel ),
        sum( prodc.producao_quantidadedefeituosa ),
        sum( prodc.producao_montanteprevisto ),
        count( prodc.producao_id ),
        to_jsonb( se ),
        to_jsonb( sesudu )
      from ggviario.producao prodc
        inner join ggviario.setor se on prodc.producao_setor_id = se.setor_id
        left join ggviario.setor sesudu on se.setor_setor_id = sesudu.setor_id
      group by to_date( to_char( prodc.producao_data, arg_format ), arg_format ),
        se.setor_id,
        sesudu.setor_id
      order by date desc;
end;
$$;


--
-- Name: funct_load_produto(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_produto(filter jsonb) RETURNS TABLE(produto_id uuid, produto_codigo character varying, produto_nome character varying, produto_stock numeric, produto_stockminimo numeric, produto_servicovenda boolean, produto_servicocompra boolean, produto_servicoproducao boolean, produto_servicostockdinamico boolean, produto_dataregisto timestamp without time zone, produto_dataatualizacao timestamp without time zone, produto_estado smallint, produto_estadodesc character varying, produto_montantevendas numeric, produto_montantevendadividas numeric, produto_montantevendavendas numeric, produto_montantevendapagas numeric, produto_montantevendapendentes numeric, produto_montantecompras numeric, produto_montantecomprapagos numeric, produto_montantecomprapendentes numeric, categoria jsonb, preco jsonb, unidade jsonb, colaborador jsonb, colaboradoratualizacao jsonb)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
  arg_ovo_id uuid default lib.to_uuid( 1 );
begin
  _const := rule.constant_init();
  return query
    with vendapruduto as (
        select
          ve.venda_produto_id,
          sum( coalesce( ve.venda_montantepagar, 0 ) ) produto_montantevendas,
          sum( coalesce( ve.venda_montantepagar, 0 ) ) filter ( where ve.venda_tvenda_id = _const.tipovenda_divida ) produto_montantevendadividas,
          sum( coalesce( ve.venda_montantepagar, 0 ) ) filter ( where ve.venda_tvenda_id = _const.tipovenda_venda ) produto_montantevendavendas,
          sum( coalesce( ve.venda_montantepagar, 0 ) ) filter ( where ve.venda_estado = _const.venda_estado_pago ) produto_montantevendapagas,
          sum( coalesce( ve.venda_montantepagar, 0 ) ) filter ( where ve.venda_estado in ( _const.venda_estado_pendente, _const.venda_estado_empagamento ) ) produto_montantevendapendentes
        from ggviario.venda ve
        group by ve.venda_produto_id
    ) select
        pro.produto_id,
        pro.produto_codigo,
        pro.produto_nome,
        pro.produto_stock,
        pro.produto_stockminimo,
        pro.produto_servicovenda,
        pro.produto_servicocompra,
        pro.produto_servicoproducao,
        pro.produto_servicostockdinamico,
        pro.produto_dataregisto,
        pro.produto_dataatualizacao,
        rule.produto_estado( pro, _const ) esatdo,
        rule.produto_estado_desc( pro, _const ) as produto_estadodesc,
        v.produto_montantevendas,
        v.produto_montantevendadividas,
        v.produto_montantevendavendas,
        v.produto_montantevendapagas,
        v.produto_montantevendapendentes,
        sum( de.despesa_montantetotal ),
        sum( de.despesa_montanteamortizado ),
        coalesce( sum( de.despesa_montantetotal ), 0 ) - coalesce( sum( de.despesa_montanteamortizado ), 0 ),
        to_jsonb( cat ),
        to_jsonb( p ),
        to_jsonb( u ),
        to_jsonb( col ),
        to_jsonb( colup )
      from  ggviario.produto pro
        inner join ggviario.categoria cat on pro.produto_categoria_id = cat.categoria_id
        inner join colaborador.colaborador col on pro.produto_colaborador_id = col.colaborador_id
        left join colaborador.colaborador colup on pro.produto_colaborador_atualizacao = colup.colaborador_id
        left join preco p ON pro.produto_id = p.preco_produto_id
          and p.preco_estado = 1
          and p.preco_base
        left join unidade u ON p.preco_unidade_id = u.unidade_id
        left join vendapruduto v on pro.produto_id = v.venda_produto_id
        left join ggviario.despesa de on pro.produto_id = de.despesa_produto_id
      group by pro.produto_id,
        cat.categoria_id,
        col.colaborador_id,
        colup.colaborador_id,
        p.preco_id,
        u.unidade_id,
        v.produto_montantevendas,
        v.produto_montantevendadividas,
        v.produto_montantevendavendas,
        v.produto_montantevendapagas,
        v.produto_montantevendapendentes
      order by pro.produto_id = arg_ovo_id desc,
            esatdo desc,
        pro.produto_nome asc
  ;
end;
$$;


--
-- Name: funct_load_produto_despesa(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_produto_despesa(filter jsonb) RETURNS TABLE(produto_id uuid, produto_codigo character varying, produto_nome character varying, produto_stock numeric, produto_stockminimo numeric, produto_servicovenda boolean, produto_servicocompra boolean, produto_servicoproducao boolean, produto_servicostockdinamico boolean, produto_estado smallint, produto_estadodesc character varying, produto_dataregisto timestamp without time zone, produto_unidades jsonb)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query
  select
    prod.produto_id,
    prod.produto_codigo,
    prod.produto_nome,
    prod.produto_stock,
    prod.produto_stockminimo,
    prod.produto_servicovenda,
    prod.produto_servicocompra,
    prod.produto_servicoproducao,
    prod.produto_servicostockdinamico,
    prod.produto_estado,
    rule.produto_estado_desc( prod, _const ),
    prod.produto_dataregisto,
    lib.agg_jsonb_collect(
        to_jsonb( pre )||to_jsonb( un )
    )
  from ggviario.produto prod
    inner join ggviario.preco pre on prod.produto_id = pre.preco_produto_id
    inner join ggviario.unidade un on pre.preco_unidade_id = un.unidade_id
  where pre.preco_estado = _const.preco_estado_ativo
        and pre.preco_quantidadeproduto is not null
        and prod.produto_estado = _const.produto_estado_ativo
        and prod.produto_servicocompra
  group by prod.produto_id
  ;
end;
$$;


--
-- Name: funct_load_produto_producao(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_produto_producao(filter jsonb) RETURNS TABLE(produto_id uuid, produto_codigo character varying, produto_nome character varying, produto_stock numeric, produto_stockminimo numeric)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query
    select pro.produto_id,
        pro.produto_codigo,
        pro.produto_nome,
        pro.produto_stock,
        pro.produto_stockminimo
      from ggviario.produto pro
      where pro.produto_estado = _const.produto_estado_ativo
        and pro.produto_servicoproducao;
end;
$$;


--
-- Name: funct_load_produto_unidades(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_produto_unidades(arg_produto_id uuid) RETURNS TABLE(preco_id uuid, preco_custounidade numeric, preco_quantidadeproduto numeric, preco_base boolean, unidade_id uuid, unidade_nome character varying, unidade_codigo character varying)
    LANGUAGE plpgsql
    AS $$
declare 
  _const rule.constant;
begin
  _const := rule.constant_init();
  
  return query 
    select
        pre.preco_id,
        pre.preco_custounidade,
        pre.preco_quantidadeproduto,
        pre.preco_base,
        u.unidade_id,
        u.unidade_nome,
        u.unidade_codigo
      from ggviario.preco pre
        inner join unidade u ON pre.preco_unidade_id = u.unidade_id
      where pre.preco_produto_id = arg_produto_id
        and pre.preco_estado = _const.preco_estado_ativo
    ;
end;
$$;


--
-- Name: funct_load_produto_venda(date); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_produto_venda(arg_data date) RETURNS TABLE(produto_id uuid, produto_nome character varying, produto_codigo character varying, produto_stock numeric, produto_precos jsonb, categoria_id uuid, categoria_nome character varying)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();

  return query
    with precodata as (
        select
          pre.*,
          rank() over ( partition by pre.preco_produto_id, pre.preco_unidade_id order by pre.preco_dataregisto desc ) as rank
        from ggviario.preco pre
        where ( arg_data = current_date and pre.preco_estado = _const.preco_estado_ativo )
              or  pre.preco_dataregisto::date <= arg_data
    )
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
    inner join precodata prec on pro.produto_id = prec.preco_produto_id
    inner join unidade u on prec.preco_unidade_id = u.unidade_id
  where prec.rank = 1
  group by pro.produto_id,
    cat.categoria_id
  ;
end;
$$;


--
-- Name: funct_load_setor(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_setor(filter jsonb) RETURNS TABLE(setor_id uuid, setor_codigo character varying, setor_nome character varying, setor_nivel smallint, setor_totalsubsetores smallint, setor_posicao smallint, setor_dataregisto timestamp without time zone, setor_dataatualizacao timestamp without time zone, setor_estado smallint, setor_estadodesc character varying, setor_super jsonb, setor_colaborador jsonb, setor_colaboradoratualizacao jsonb)
    LANGUAGE plpgsql
    AS $$
declare
    _const rule.constant;
  begin
    _const := rule.constant_init();
    return query
      select
          se.setor_id,
          se.setor_codigo,
          se.setor_nome,
          se.setor_nivel,
          se.setor_totalsubsetores,
          se.setor_posicao,
          se.setor_dataregisto,
          se.setor_dataatualizacao,
          se.setor_estado,
          rule.setor_estado_desc( se, _const ),
          to_jsonb( super ),
          to_jsonb( co ),
          to_jsonb( coup )
        from ggviario.setor se
          inner join ggviario.colaborador.colaborador co on se.setor_colaborador_id = co.colaborador_id
          left join ggviario.setor super on se.setor_setor_id = super.setor_id
          left join ggviario.colaborador.colaborador coup on se.setor_colaborador_atualizacao = coup.colaborador_id
        where se.setor_estado in ( _const.setor_estado_ativo, _const.setor_estado_protegido )
      order by se.setor_posicao asc;
  end;
$$;


--
-- Name: funct_load_setor_all(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_setor_all(filter jsonb) RETURNS TABLE(setor_id uuid, setor_codigo character varying, setor_nome character varying, setor_nivel smallint, setor_totalsubsetores smallint, setor_posicao smallint, setor_dataregisto timestamp without time zone, setor_dataatualizacao timestamp without time zone, setor_estado smallint, setor_estadodesc character varying, setor_super jsonb, setor_colaborador jsonb, setor_colaboradoratualizacao jsonb)
    LANGUAGE plpgsql
    AS $$
declare
    _const rule.constant;
  begin
    _const := rule.constant_init();
    return query
      select
          se.setor_id,
          se.setor_codigo,
          se.setor_nome,
          se.setor_nivel,
          se.setor_totalsubsetores,
          se.setor_posicao,
          se.setor_dataregisto,
          se.setor_dataatualizacao,
          se.setor_estado,
          rule.setor_estado_desc( se, _const ),
          to_jsonb( super ),
          to_jsonb( co ),
          to_jsonb( coup )
        from ggviario.setor se
          inner join ggviario.colaborador.colaborador co on se.setor_colaborador_id = co.colaborador_id
          left join ggviario.setor super on se.setor_setor_id = super.setor_id
          left join ggviario.colaborador.colaborador coup on se.setor_colaborador_atualizacao = coup.colaborador_id
      order by se.setor_posicao asc;
  end;
$$;


--
-- Name: funct_load_setor_by_localproducao_to_producao(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_setor_by_localproducao_to_producao(arg_produto_id uuid) RETURNS TABLE(setor_id uuid, setor_nome character varying, setor_codigo character varying, setor_estado smallint, setor_nivel smallint, setor_dataregisto timestamp without time zone, setor_super jsonb)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query 
    select 
        se.setor_id,
        se.setor_nome,
        se.setor_codigo,
        se.setor_estado,
        se.setor_nivel,
        se.setor_dataregisto,
        to_jsonb( sesup )
      from ggviario.localproducao loc 
        inner join rule.select_setor_cascade_witch_raiz( loc.localproducao_setor_id ) se on se.setor_estado = _const.setor_estado_ativo
        left join ggviario.setor sesup on se.setor_setor_id =sesup.setor_id
      where loc.localproducao_estado = _const.localproducao_estado_ativo
        and loc.localproducao_produto_id = arg_produto_id
      order by se.setor_posicao asc
  ;
end;
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
-- Name: funct_load_unidade(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_unidade(filter jsonb) RETURNS TABLE(unidade_id uuid, unidade_nome character varying, unidade_codigo character varying, unidade_dataregisto timestamp without time zone, unidade_dataatualizacao timestamp without time zone, unidade_estado smallint, colaborador jsonb, colaborador_atualizacao jsonb)
    LANGUAGE sql
    AS $$
  select un.unidade_id,
      un.unidade_nome,
      un.unidade_codigo,
      un.unidade_dataregisto,
      un.unidade_dataatualizacao,
      un.unidade_estado,
      to_jsonb( col ),
      to_jsonb( colup )
    from unidade un
      inner join colaborador.colaborador col on un.unidade_colaborador_id = col.colaborador_id
      left join colaborador.colaborador colup on un.unidade_colaborador_atualizacao = colup.colaborador_id
$$;


--
-- Name: funct_load_unidade_simple_by_produto(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_unidade_simple_by_produto(arg_produto_id uuid) RETURNS TABLE(unidade_id uuid, unidade_nome character varying, unidade_codigo character varying, preco_id uuid, preco_custounidade numeric, preco_quantidadeproduto numeric, preco_base boolean, preco_estado smallint)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
begin
  _const := rule.constant_init();
  return query
  select
    un.unidade_id,
    un.unidade_nome,
    un.unidade_codigo,
    pre.preco_id,
    pre.preco_custounidade,
    pre.preco_quantidadeproduto,
    pre.preco_base,
    pre.preco_estado
  from ggviario.unidade un
    left join ggviario.preco pre on un.unidade_id = pre.preco_unidade_id
      and pre.preco_estado = _const.preco_estado_ativo
      and pre.preco_produto_id = arg_produto_id
  ;
end;
$$;


--
-- Name: funct_load_venda_divida(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_venda_divida(filter jsonb) RETURNS TABLE(venda_id uuid, venda_faturanumero character varying, venda_quantidade numeric, venda_quantidadeproduto numeric, venda_montanteunitario numeric, venda_montantebruto numeric, venda_montantedesconto numeric, venda_montantepagar numeric, venda_montanteamortizado numeric, venda_data date, venda_datafinalizar date, venda_datafim timestamp without time zone, venda_dataultimamovimentacao date, venda_dataregisto timestamp without time zone, venda_estado smallint, venda_estadodesc character varying, tvenda_id smallint, tvenda_desc character varying, produto_id uuid, produto_nome character varying, produto_codigo character varying, unidade_id uuid, unidade_nome character varying, unidade_codigo character varying, cliente_id uuid, cliente_nome character varying, cliente_apelido character varying, cliente_montentecompras numeric, cliente_montentedividas numeric, cliente_montantetatal numeric, cliente_montanteamortizado numeric, cliente_montantependente numeric)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
  _tipovenda ggviario.tipovenda;
begin
  _const := rule.constant_init();
  _tipovenda := ggviario.get_tipovenda( _const.tipovenda_divida );
  return query
  select
    ve.venda_id,
    ve.venda_faturanumero,
    ve.venda_quantidade,
    ve.venda_quantidadeproduto,
    ve.venda_montanteunitario,
    ve.venda_montantebruto,
    ve.venda_montantedesconto,
    ve.venda_montantepagar,
    ve.venda_montanteamortizado,
    ve.venda_data,
    ve.venda_datafinalizar,
    ve.venda_datafim,
    ve.venda_dataultimamovimentacao,
    ve.venda_dataregisto,
    ve.venda_estado,
    rule.venda_estado_desc( ve, _const ),
    _tipovenda.tvenda_id,
    _tipovenda.tvenda_desc,
    pr.produto_id,
    pr.produto_nome,
    pr.produto_codigo,
    un.unidade_id,
    un.unidade_nome,
    un.unidade_codigo,
    cli.cliente_id,
    cli.cliente_nome,
    cli.cliente_apelido,
    sum( allVenda.venda_montantepagar ) filter ( where allVenda.venda_tvenda_id = _const.tipovenda_venda ) as cliente_montentecompras,
    sum( allVenda.venda_montantepagar ) filter ( where allVenda.venda_tvenda_id = _const.tipovenda_divida ) as cliente_montentedividas,
    sum( allVenda.venda_montantepagar ) as cliente_montantetatal,
    sum( allVenda.venda_montanteamortizado ) as cliente_montanteamortizado,
    sum( allVenda.venda_montantepagar - allVenda.venda_montanteamortizado ) as cliente_montantependente
  from ggviario.venda ve
    inner join ggviario.produto pr on ve.venda_produto_id = pr.produto_id
    inner join ggviario.unidade un on ve.venda_unidade_id = un.unidade_id
    inner join ggviario.cliente cli on ve.venda_cliente_id = cli.cliente_id
    left join ggviario.venda allVenda on cli.cliente_id = allVenda.venda_cliente_id
  where ve.venda_tvenda_id = _const.tipovenda_divida
  group by ve.venda_id,
    pr.produto_id,
    un.unidade_id,
    cli.cliente_id
  order by
    ve.venda_estado desc,
    ve.venda_data desc,
    cli.cliente_nome asc
  ;
end;
$$;


--
-- Name: funct_load_venda_venda(jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_load_venda_venda(filter jsonb) RETURNS TABLE(venda_id uuid, venda_faturanumero character varying, venda_quantidade numeric, venda_quantidadeproduto numeric, venda_montanteunitario numeric, venda_montantebruto numeric, venda_montantedesconto numeric, venda_montantepagar numeric, venda_montanteamortizado numeric, venda_data date, venda_datafinalizar date, venda_datafim timestamp without time zone, venda_dataultimamovimentacao date, venda_dataregisto timestamp without time zone, venda_estado smallint, venda_estadodesc character varying, tvenda_id smallint, tvenda_desc character varying, produto_id uuid, produto_nome character varying, produto_codigo character varying, unidade_id uuid, unidade_nome character varying, unidade_codigo character varying, cliente_id uuid, cliente_nome character varying, cliente_apelido character varying, cliente_montentecompras numeric, cliente_montentedividas numeric, cliente_montantetatal numeric, cliente_montanteamortizado numeric, cliente_montantependente numeric)
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
  _tipovenda ggviario.tipovenda;
begin
  _const := rule.constant_init();
  _tipovenda := ggviario.get_tipovenda( _const.tipovenda_venda );
  return query
  select
    ve.venda_id,
    ve.venda_faturanumero,
    ve.venda_quantidade,
    ve.venda_quantidadeproduto,
    ve.venda_montanteunitario,
    ve.venda_montantebruto,
    ve.venda_montantedesconto,
    ve.venda_montantepagar,
    ve.venda_montanteamortizado,
    ve.venda_data,
    ve.venda_datafinalizar,
    ve.venda_datafim,
    ve.venda_dataultimamovimentacao,
    ve.venda_dataregisto,
    ve.venda_estado,
    rule.venda_estado_desc( ve, _const ),
    _tipovenda.tvenda_id,
    _tipovenda.tvenda_desc,
    pr.produto_id,
    pr.produto_nome,
    pr.produto_codigo,
    un.unidade_id,
    un.unidade_nome,
    un.unidade_codigo,
    cli.cliente_id,
    cli.cliente_nome,
    cli.cliente_apelido,
    sum( allVenda.venda_montantepagar ) filter ( where allVenda.venda_tvenda_id = _const.tipovenda_venda ) as cliente_montentecompras,
    sum( allVenda.venda_montantepagar ) filter ( where allVenda.venda_tvenda_id = _const.tipovenda_divida ) as cliente_montentedividas,
    sum( allVenda.venda_montantepagar ) as cliente_montantetatal,
    sum( allVenda.venda_montanteamortizado ) as cliente_montanteamortizado,
    sum( allVenda.venda_montantepagar - allVenda.venda_montanteamortizado ) as cliente_montantependente
  from ggviario.venda ve
    inner join ggviario.produto pr on ve.venda_produto_id = pr.produto_id
    inner join ggviario.unidade un on ve.venda_unidade_id = un.unidade_id
    inner join ggviario.cliente cli on ve.venda_cliente_id = cli.cliente_id
    left join ggviario.venda allVenda on cli.cliente_id = allVenda.venda_cliente_id
  where ve.venda_tvenda_id = _const.tipovenda_venda
  group by ve.venda_id,
    pr.produto_id,
    un.unidade_id,
    cli.cliente_id
  order by ve.venda_data desc,
    cli.cliente_nome asc
  ;
end;
$$;


--
-- Name: funct_reg_categoria(uuid, uuid, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_categoria(arg_colaborador_id uuid, arg_categoria_super uuid, arg_categoria_nome character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _categoria ggviario.categoria;
  _categoria_super ggviario.categoria;
  _const rule.constant;
begin
  _categoria_super := ggviario.get_categoria( arg_categoria_super );
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
    categoria_nome,
    categoria_nivel,
    categoria_codigo
  ) values (
    arg_colaborador_id,
    _categoria_super.categoria_id,
    arg_categoria_nome,
    _categoria_super.categoria_nivel +1,
    rule.codigo_next( 'A', 2::int2  )
  ) returning * into _categoria;

  perform ggviario.funct_change_categoria_structure();
  _categoria := ggviario.get_categoria( _categoria.categoria_id );
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
  _cod record;
  _const rule.constant;
begin
  _const := rule.constant_init();
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

  select * into _cod
    from rule.codigo_generate( _const.codigo_cliente_letra, _const.codigo_cliente_digitos );


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
    cliente_datanascimento,
    cliente_codigo
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
    arg_cliente_datanascimento,
    _cod.codigo_codigo
  ) returning * into _cliente ;

  return lib.result_true(
    jsonb_build_object(
      'cliente', _cliente
    )
  );
end;

$$;


--
-- Name: funct_reg_despesa(uuid, uuid, uuid, uuid, numeric, numeric, numeric, date, character varying, boolean, uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_despesa(arg_colaborador_id uuid, arg_fornecedor_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_despesa_quantidade numeric, arg_despesa_custounitario numeric, arg_despesa_custototal numeric, arg_despesa_data date, arg_despesa_numerofatura character varying, arg_despesa_paga boolean, arg_conta_id uuid) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _despesa ggviario.despesa;
  _produto ggviario.produto;
  _preco ggviario.preco;
  arg_totalprodutoincrementar numeric;
  _const rule.constant;
  _code record;
begin
  _const := rule.constant_init();
  arg_totalprodutoincrementar := arg_despesa_quantidade;
  _produto := ggviario.get_produto( arg_produto_id );
  if arg_despesa_paga is null then arg_despesa_paga := null; end if;

  if _produto.produto_servicostockdinamico then

    select * into _preco
    from ggviario.preco equ
    where equ.preco_estado = _const.preco_estado_ativo
          and equ.preco_produto_id = arg_produto_id
          and equ.preco_unidade_id = arg_unidade_id
    ;

    if _preco is null then
      return lib.result_false( 'Não foi encontrado equivalencia da unidade para o produto!' );
    end if;

    arg_totalprodutoincrementar := arg_despesa_quantidade * _preco.preco_quantidadeproduto;

  end if;


  select cod.* into _code
  from rule.codigo_generate( _const.codigo_despesa_letra, _const.codigo_despesa_digitos )cod
  ;

  insert into ggviario.despesa(
    despesa_colaborador_id,
    despesa_codigo,
    despesa_fornecedor_id,
    despesa_produto_id,
    despesa_unidade_id,
    despesa_quatidade,
    despesa_montanteunitario,
    despesa_montantetotal,
    despesa_data,
    despesa_numerofatura,
    despesa_quantidadeproduto,
    despesa_estado
  ) values (
    arg_colaborador_id,
    _code.codigo_codigo,
    arg_fornecedor_id,
    arg_produto_id,
    arg_unidade_id,
    arg_despesa_quantidade,
    arg_despesa_custounitario,
    arg_despesa_custototal,
    arg_despesa_data,
    arg_despesa_numerofatura,
    arg_totalprodutoincrementar,
    _const.despesa_estado_pendente
  ) returning * into _despesa;
  
  if not arg_despesa_paga then 
    return lib.result_true(
        jsonb_build_object(
            'despesa', _despesa
        )
    );
  else
    return funct_reg_movimento_amortizacao_despesa( 
      arg_colaborador_id,
      _despesa.despesa_id,
      arg_conta_id,
      _despesa.despesa_montantetotal,
      _despesa.despesa_data,
      _despesa.despesa_codigo
    );
  end if;
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
-- Name: funct_reg_localproducao(uuid, uuid, uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_localproducao(arg_colaborador_id uuid, arg_produto_id uuid, arg_setor_id uuid) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _localproducao ggviario.localproducao;
  _const rule.constant;
  _setor ggviario.setor;
begin
  _const := rule.constant_init();
  if(
    select count( * )
      from ggviario.localproducao loc
        inner join rule.select_setor_cascade( loc.localproducao_setor_id ) se on true
      where loc.localproducao_produto_id = arg_produto_id
        and loc.localproducao_estado = _const.localproducao_estado_ativo
        and se.setor_id = arg_setor_id
  ) > 0 then 
    return lib.result_false( 'Setor em utilização pelo produto!' ); 
  end if;

  -- Fechaor o local de producao filho desse novo setor
  for _setor in (
    select *
    from rule.select_setor_cascade( arg_setor_id )
  ) loop
    update ggviario.localproducao loc
      set localproducao_estado = _const.localproducao_estado_fechado,
          localproducao_dataatualizacao = current_timestamp,
          localproducao_colaborador_atualizacao = arg_colaborador_id
      where localproducao_produto_id = arg_produto_id
        and localproducao_setor_id = _setor.setor_id
        and localproducao_estado = _const.localproducao_estado_ativo
    ;
  end loop;
  
  insert into ggviario.localproducao(
    localproducao_produto_id,
    localproducao_setor_id,
    localproducao_colaborador_id
  ) values (
    arg_produto_id,
    arg_setor_id,
    arg_colaborador_id
  ) returning * into _localproducao;



  return lib.result_true(
    jsonb_build_object(
      'localproducao', _localproducao
    )
  );
end;
$$;


--
-- Name: funct_reg_movimento_amortizacao_despesa(uuid, uuid, uuid, numeric, date, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_movimento_amortizacao_despesa(arg_colaborador_id uuid, arg_despesa_id uuid, arg_conta_id uuid, arg_movimento_montante numeric, arg_movimento_data date, arg_movimento_documento character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  /**
   Amortizao de uma divida em um movimento de credito para a empresa
   */
  _movimento ggviario.movimento;
  _despesa ggviario.despesa;
  _const rule.constant;
begin

  _const := rule.constant_init();
  _despesa := ggviario.get_despesa( arg_despesa_id );

  if _despesa.despesa_estado not in ( _const.despesa_estado_pendente, _const.despesa_estado_empagamento ) then
    return lib.result_false( format( 'Não pode amortizar uma despasa no estado %I!', rule.despesa_estado_desc(  _despesa, _const ) ) );
  end if;

  if _despesa.despesa_montanteamortizado + arg_movimento_montante > _despesa.despesa_montantetotal then
    return lib.result_false( 'O valor da pagar ultrapassa o valor total da venda!' );
  end if;

  arg_movimento_documento := coalesce( lib.normalize( arg_movimento_documento ), _despesa.despesa_codigo );

  _movimento := rule.movimento_insert(
      arg_colaborador_id,
      arg_conta_id,
      _const.tipomovimento_credito,
      arg_movimento_documento,
      arg_movimento_data,
      arg_movimento_montante,
      format(
          'Amortizacao da despesa numero %s da fatura %s',
          _despesa.despesa_codigo,
          _despesa.despesa_numerofatura
      ),
      null,
      _despesa.despesa_id
  );

  _despesa := get_despesa( arg_despesa_id );

  return lib.result_true(
      jsonb_build_object(
          'movimento', _movimento,
          'despesa', _despesa
      )
  );

end;
$$;


--
-- Name: funct_reg_movimento_amortizacao_venda(uuid, uuid, uuid, numeric, date, character varying); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_movimento_amortizacao_venda(arg_colaborador_id uuid, arg_venda_id uuid, arg_conta_id uuid, arg_movimento_montante numeric, arg_movimento_data date, arg_movimento_documento character varying) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  /**
   Amortizao de uma divida em um movimento de credito para a empresa
   */
  _movimento ggviario.movimento;
  _venda record;
  _const rule.constant;
begin

  _const := rule.constant_init();

  select * into _venda
  from ggviario.venda vd
    inner join tipovenda tc on vd.venda_tvenda_id = tc.tvenda_id
  where vd.venda_id = arg_venda_id
  ;

  if _venda.venda_estado not in ( _const.venda_estado_pendente, _const.venda_estado_empagamento ) then
    return lib.result_false( format( 'Não pode amortizar uma venda no estado %I!', rule.venda_estado_desc( _venda, _const ) ) );
  end if;

  if _venda.venda_montanteamortizado + arg_movimento_montante > _venda.venda_montantepagar then
    return lib.result_false( 'O valor da pagar ultrapassa o valor total da venda!' );
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
          _venda.tvenda_desc,
          _venda.venda_faturanumero
      ),
      _venda.venda_id
  );

  _venda := get_venda( arg_venda_id );

  return lib.result_true(
      jsonb_build_object(
          'movimento', _movimento,
          'venda', _venda
      )
  );

end;
$$;


--
-- Name: funct_reg_preco(uuid, uuid, uuid, numeric, numeric, boolean); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_preco(arg_colaborador_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_preco_custounidade numeric, arg_preco_quantiadeproduto numeric, arg_preco_base boolean) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare

  _preco ggviario.preco;
  _preco_old ggviario.preco;
  _const rule.constant;
begin
  _const := rule.constant_init();
  update ggviario.preco
    set preco_estado = _const.preco_estado_fechado,
        preco_colaborador_atualizacao = arg_colaborador_id,
        preco_dataatualizacao = current_timestamp
    where preco_unidade_id = arg_unidade_id
      and preco_produto_id = arg_produto_id
      and preco_estado = _const.preco_estado_ativo
    returning * into _preco_old
  ;

  if arg_preco_base then
    update preco
      set preco_base = false,
          preco_colaborador_atualizacao = arg_colaborador_id,
          preco_dataatualizacao = current_timestamp
      where preco_base
        and preco_produto_id = arg_produto_id
        and preco_estado = _const.preco_estado_ativo
    ;
  end if;

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
    arg_unidade_id,
    arg_preco_custounidade,
    arg_preco_quantiadeproduto,
    arg_preco_base
  ) returning * into _preco;

  return lib.result_true(
      jsonb_build_object(
          'preco', _preco,
          'preco_old', _preco_old
      )
  );
end;
$$;


--
-- Name: funct_reg_producao(uuid, date, numeric, numeric, numeric, uuid, uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_producao(arg_colaborador_id uuid, arg_producao_data date, arg_producao_quantidadetotal numeric, arg_producao_quantidadecomerciavel numeric, arg_producao_quantidadedefeituosa numeric, arg_produto_id uuid, arg_setor_id uuid) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _producao ggviario.producao;
  _setor ggviario.setor;
  _produto ggviario.produto;
  _const rule.constant;
  _preco ggviario.preco;
  arg_producao_montanteprevisto numeric;
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

  select * into _preco
    from ggviario.preco pre
    where pre.preco_estado = _const.preco_estado_ativo
      and pre.preco_produto_id = arg_produto_id
    order by pre.preco_base desc
    limit 1
  ;

  arg_producao_montanteprevisto := arg_producao_quantidadecomerciavel * coalesce(  _preco.preco_custounidade, 0 ) / _preco.preco_quantidadeproduto;

  insert into ggviario.producao (
    producao_colaborador_id,
    producao_produto_id,
    producao_setor_id,
    producao_quantidadetotal,
    producao_quantidadecomerciavel,
    producao_quantidadedefeituosa,
    producao_montanteprevisto,
    producao_data,
    producao_codigo
  ) values(
    arg_colaborador_id,
    arg_produto_id,
    arg_setor_id,
    arg_producao_quantidadetotal,
    arg_producao_quantidadecomerciavel,
    arg_producao_quantidadedefeituosa,
    arg_producao_montanteprevisto,
    arg_producao_data,
    rule.codigo_next( _const.codigo_producao_letra, _const.codigo_producao_digitos )
  ) returning * into _producao;

  return lib.result_true(
      jsonb_build_object(
          'producao', _producao
      )
  );
end;
$$;


--
-- Name: funct_reg_produto(uuid, uuid, character varying, boolean, boolean, boolean, boolean, numeric, jsonb); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_produto(arg_colaborador_id uuid, arg_categoria_id uuid, arg_produto_nome character varying, arg_produto_servicovenda boolean, arg_produdo_servicocompra boolean, arg_produto_servicoproducao boolean, arg_produto_servicostockminimo boolean, arg_produto_stockminimo numeric, options jsonb) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _produto ggviario.produto;
  _const rule.constant;
begin
  _const := rule.constant_init();
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
    produto_nome,
    produto_codigo,
    produto_servicovenda,
    produto_servicocompra,
    produto_servicoproducao,
    produto_servicostockdinamico,
    produto_stockminimo
  ) values (
    arg_categoria_id,
    arg_colaborador_id,
    arg_produto_nome,
    rule.codigo_next( _const.codigo_produto_letra, _const.codigo_produto_digitos ),
    arg_produto_servicovenda,
    arg_produdo_servicocompra,
    arg_produto_servicoproducao,
    arg_produto_servicostockminimo,
    arg_produto_stockminimo
  ) returning * into _produto;

  return lib.result_true(
      jsonb_build_object(
          'produto', _produto
      )
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
  iterator int2 default 0;
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
    setor_nome,
    setor_codigo,
    setor_nivel
  ) values (
    arg_colaborador_id,
    arg_setor_setor_id,
    arg_setor_nome,
    rule.codigo_next( _const.codigo_setor_letra, _const.codigo_setor_digitos ),
    _setor_parent.setor_nivel +1
  ) returning * into _setor;

  perform ggviario.funct_change_sector_structure();
  
  _setor := ggviario.get_setor( _setor.setor_id );
  
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


--
-- Name: funct_reg_venda_divida(uuid, uuid, uuid, uuid, numeric, numeric, numeric, numeric, numeric, date, date); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_venda_divida(arg_colaborador_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_venda_quantidade numeric, arg_venda_montanteunitario numeric, arg_venda_montantebruto numeric, arg_venda_montantedesconto numeric, arg_venda_montantepagar numeric, arg_venda_data date, arg_venda_datafinalizar date) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _res lib.result;
  _tipovenda ggviario.tipovenda;
  _const rule.constant;
begin
  _const := rule.constant_init();
  _tipovenda := ggviario.get_tipovenda( _const.tipovenda_divida );

  if arg_venda_montanteunitario is null then raise exception 'Para aqui estado nullo'; end if;
  _res := rule.venda_insert(
      arg_colaborador_id,
      arg_produto_id,
      arg_unidade_id,
      arg_cliente_id,
      _tipovenda.tvenda_id,
      arg_venda_quantidade,
      arg_venda_montanteunitario,
      arg_venda_montantebruto,
      arg_venda_montantedesconto,
      arg_venda_montantepagar,
      arg_venda_data,
      arg_venda_datafinalizar
  );

  return _res;
end
$$;


--
-- Name: funct_reg_venda_venda(uuid, uuid, uuid, uuid, uuid, numeric, numeric, numeric, numeric, numeric, date); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION funct_reg_venda_venda(arg_colaborador_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_conta_id uuid, arg_venda_quantidade numeric, arg_venda_montanteunitario numeric, arg_venda_montantebruto numeric, arg_venda_montantedesconto numeric, arg_venda_montantepagar numeric, arg_venda_data date) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  _res lib.result;
  _tipovenda ggviario.tipovenda;
  _venda ggviario.venda;
  _const rule.constant;
begin
  _const := rule.constant_init();
  _tipovenda := ggviario.get_tipovenda( _const.tipovenda_venda );
  _res := rule.venda_insert(
      arg_colaborador_id,
      arg_produto_id,
      arg_unidade_id,
      arg_cliente_id,
      _tipovenda.tvenda_id,
      arg_venda_quantidade,
      arg_venda_montanteunitario,
      arg_venda_montantebruto,
      arg_venda_montantedesconto,
      arg_venda_montantepagar,
      arg_venda_data,
      arg_venda_data
  );

  if not _res.result then
    return _res;
  end if;

  _venda := ggviario.get_venda(
      ( _res.message->'venda'->>'venda_id' )::uuid
  );


  _res := funct_reg_movimento_amortizacao_venda(
      arg_colaborador_id,
      _venda.venda_id,
      arg_conta_id,
      arg_venda_montantepagar,
      arg_venda_data,
      _venda.venda_faturanumero
  );

  return _res;

end
$$;


--
-- Name: get_categoria(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_categoria(arg_categoria_id uuid) RETURNS categoria
    LANGUAGE sql
    AS $$ select * from ggviario.categoria where categoria_id = arg_categoria_id; $$;


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
    despesa_codigo character varying(16) NOT NULL,
    despesa_data date NOT NULL,
    despesa_numerofatura character varying(32),
    despesa_quatidade numeric NOT NULL,
    despesa_quantidadeproduto numeric NOT NULL,
    despesa_montanteunitario numeric NOT NULL,
    despesa_montantetotal numeric NOT NULL,
    despesa_montanteamortizado numeric DEFAULT 0 NOT NULL,
    despesa_dataultimamovimento date,
    despesa_datafim timestamp without time zone,
    despesa_estado smallint DEFAULT 2 NOT NULL,
    despesa_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    despesa_dataatualizacao timestamp without time zone
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
-- Name: movimento; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE movimento (
    movimento_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    movimento_conta_id uuid,
    movimento_tmovimento_id smallint NOT NULL,
    movimento_colaborador_id uuid NOT NULL,
    movimento_colaborador_atualizacao uuid,
    movimento_movimento_id uuid,
    movimento_venda_id uuid,
    movimento_despeda_id uuid,
    movimento_codigo character varying(16),
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
    preco_custounidade numeric,
    preco_quantidadeproduto numeric,
    preco_base boolean DEFAULT false NOT NULL,
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
    produto_codigo character varying(8),
    produto_nome character varying(32) NOT NULL,
    produto_stock numeric DEFAULT 0 NOT NULL,
    produto_stockminimo numeric DEFAULT 0 NOT NULL,
    produto_servicovenda boolean DEFAULT true NOT NULL,
    produto_servicocompra boolean DEFAULT true NOT NULL,
    produto_servicoproducao boolean DEFAULT true NOT NULL,
    produto_servicostockdinamico boolean DEFAULT true NOT NULL,
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
-- Name: tipovenda; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE tipovenda (
    tvenda_id smallint NOT NULL,
    tvenda_desc character varying(32) NOT NULL,
    tvenda_letra character(1) NOT NULL,
    tvenda_ditigos smallint DEFAULT 5 NOT NULL,
    CONSTRAINT fk_tcompra_desc_is_normalized CHECK (lib.is_normalized((tvenda_desc)::text))
);


--
-- Name: get_tipovenda(smallint); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_tipovenda(arg_tvenda_id smallint) RETURNS tipovenda
    LANGUAGE sql
    AS $$
  select * from ggviario.tipovenda where tvenda_id = arg_tvenda_id;
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


--
-- Name: get_venda(uuid); Type: FUNCTION; Schema: ggviario; Owner: -
--

CREATE FUNCTION get_venda(arg_venda_id uuid) RETURNS venda
    LANGUAGE sql
    AS $$
select * from ggviario.venda where venda_id = arg_venda_id;
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


SET search_path = rule, pg_catalog;

--
-- Name: audit_insert(uuid, character varying, character varying, jsonb, character varying); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION audit_insert(arg_colaborador_id uuid, arg_audit_key character varying, arg_audit_title character varying, arg_audit_object jsonb, arg_audit_message character varying) RETURNS ggviario.audit
    LANGUAGE sql
    AS $$
insert into ggviario.audit(
  audit_colaborador_id,
  audit_title,
  audit_key,
  audit_object,
  audit_message
) values (
  arg_colaborador_id,
  arg_audit_title,
  arg_audit_key,
  arg_audit_object,
  arg_audit_message
) returning * ;
$$;


--
-- Name: categoria_estado_desc(ggviario.categoria, constant); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION categoria_estado_desc(_categoria ggviario.categoria, _const constant) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
  begin
    return case
        when _categoria.categoria_estado = _const.categoria_estado_ativo then 'Ativo'
        when _categoria.categoria_estado = _const.categoria_estado_fechado then 'Fechado'
      end;
  end;
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
    cliente_codigo character varying(16) NOT NULL,
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
-- Name: COLUMN cliente.cliente_datanascimento; Type: COMMENT; Schema: ggviario; Owner: -
--

COMMENT ON COLUMN cliente.cliente_datanascimento IS 'A data em que o cliente nasceu';


--
-- Name: COLUMN cliente.cliente_localtrabalho; Type: COMMENT; Schema: ggviario; Owner: -
--

COMMENT ON COLUMN cliente.cliente_localtrabalho IS 'O local em que o cliente trabalha';


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
-- Name: codigo(character, smallint, smallint); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION codigo(arg_codigo_letra character, arg_codigo_ano smallint, arg_codigo_digitos smallint) RETURNS TABLE(codigo_letra character, codigo_digito smallint, codigo_ano smallint, codigo_numero integer, codigo_codigo character varying)
    LANGUAGE plpgsql
    AS $$
declare
  _codigo ggviario.codigo;
  arg_codigo character varying ;
  arg_length_ano int2;
begin
  arg_codigo_letra := upper( arg_codigo_letra );
  arg_length_ano := length( arg_codigo_ano::text );

  update ggviario.codigo cod
    set codigo_numero = cod.codigo_numero +1
    where cod.codigo_ano = arg_codigo_ano
      and cod.codigo_letra = arg_codigo_letra
      and length( ( cod.codigo_numero +1)::text ) < cod.codigo_digitos + 3
      and cod.codigo_digitos = arg_codigo_digitos
    returning * into _codigo;



  if _codigo is null and arg_length_ano = 2 then
    insert into ggviario.codigo(
      codigo_letra,
      codigo_digitos,
      codigo_ano,
      codigo_numero
    ) values (
      arg_codigo_letra,
      arg_codigo_digitos,
      arg_codigo_ano,
      1
    ) returning * into _codigo;
  end if;

  if _codigo is not null then
    arg_codigo := _codigo.codigo_letra;
    arg_codigo := arg_codigo || lpad( _codigo.codigo_numero::text, _codigo.codigo_digitos, '0' );
    arg_codigo := arg_codigo || '/';
    arg_codigo := arg_codigo || _codigo.codigo_ano::text;
  else
    raise exception 'Não cosegue gerar um proximo com as expecificações definida expecificações: %', json_build_object(
      'codigo_letra', arg_codigo_letra,
      'codigo_ano', arg_codigo_ano,
      'codigo_digitos', arg_codigo_digitos
    );
  end if;

  return query
    select _codigo.codigo_letra,
      _codigo.codigo_digitos,
      _codigo.codigo_ano,
      _codigo.codigo_numero,
      arg_codigo
  ;
end;
$$;


--
-- Name: codigo_generate(character, smallint); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION codigo_generate(arg_codigo_letra character, arg_codigo_digitos smallint) RETURNS TABLE(codigo_letra character, codigo_digito smallint, codigo_ano smallint, codigo_numero integer, codigo_codigo character varying)
    LANGUAGE sql
    AS $$
;
  select *
    from rule.codigo(
      arg_codigo_letra,
      to_char( current_date, 'yy' )::int2,
      arg_codigo_digitos
    )
$$;


--
-- Name: codigo_next(character, smallint); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION codigo_next(character, smallint) RETURNS character varying
    LANGUAGE sql
    AS $_$select codigo_codigo from rule.codigo_generate( $1, $2 )$_$;


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


SET search_path = ggviario, pg_catalog;

--
-- Name: fornecedor; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE fornecedor (
    fornecedor_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    fornecedor_distrito_id smallint,
    fornecedor_colaborador_id uuid NOT NULL,
    fornecedor_colaborador_atualizacao uuid,
    fornecedor_codigo character varying(6),
    fornecedor_nome character varying(32) NOT NULL,
    fornecedor_nif character varying(9) DEFAULT NULL::character varying,
    fornecedor_telefone character varying(15) DEFAULT NULL::character varying,
    fornecedor_telemovel character varying(15) DEFAULT NULL::character varying,
    fornecedor_mail character varying(32) DEFAULT NULL::character varying,
    fornecedor_local character varying(48) DEFAULT NULL::character varying,
    fornecedor_estado smallint DEFAULT 1 NOT NULL,
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


SET search_path = rule, pg_catalog;

--
-- Name: fornecedor_estado_desc(ggviario.fornecedor, constant); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION fornecedor_estado_desc(_fornecedor ggviario.fornecedor, _const constant) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
  begin 
    if _const is null then _const := rule.constant_init(); end if;
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
-- Name: functg_movimento_after_insert_update_despesa(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_movimento_after_insert_update_despesa() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
  _new ggviario.movimento;
  _const rule.constant;
  _depesa ggviario.despesa;

begin
  _const := rule.constant_init();
  _new := new;

  if _new.movimento_despeda_id is not null then

    _depesa := ggviario.get_despesa( _new.movimento_despeda_id );
    _depesa.despesa_estado := _const.despesa_estado_empagamento;

    if _depesa.despesa_montanteamortizado + _new.movimento_montante >= _depesa.despesa_montantetotal then
      _depesa.despesa_estado := _const.despesa_estado_pago;
      _depesa.despesa_datafim = current_timestamp;
    end if;

    if coalesce( _depesa.despesa_dataultimamovimento, _new.movimento_data - interval '1' day ) < _new.movimento_data then
      _depesa.despesa_dataultimamovimento := _new.movimento_data;
    end if;


    update ggviario.despesa
      set despesa_estado = _depesa.despesa_estado,
        despesa_dataultimamovimento = _depesa.despesa_dataultimamovimento,
        despesa_montanteamortizado = despesa_montanteamortizado + _new.movimento_montante,
        despesa_colaborador_atualizacao = _new.movimento_colaborador_id,
        despesa_dataatualizacao = current_timestamp,
        despesa_datafim = _depesa.despesa_datafim
      where despesa_id = _depesa.despesa_id;
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
-- Name: functg_movimento_after_insert_update_venda(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_movimento_after_insert_update_venda() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
  _new ggviario.movimento;
  _const rule.constant;
  _venda ggviario.venda;

begin
  _const := rule.constant_init();
  _new := new;

  if _new.movimento_venda_id is not null then
    
    _venda := ggviario.get_venda( _new.movimento_venda_id );
    _venda.venda_estado := _const.venda_estado_empagamento;
    
    if _venda.venda_montanteamortizado + _new.movimento_montante >= _venda.venda_montantepagar then
      _venda.venda_estado := _const.venda_estado_pago;
      _venda.venda_datafim = current_timestamp;
    end if;
    
    if coalesce( _venda.venda_dataultimamovimentacao, _new.movimento_data - interval '1' day ) < _new.movimento_data then
      _venda.venda_dataultimamovimentacao := _new.movimento_data;
    end if;

    
    update ggviario.venda
      set venda_estado = _venda.venda_estado,
          venda_dataultimamovimentacao = _venda.venda_dataultimamovimentacao,
          venda_montanteamortizado = venda_montanteamortizado + _new.movimento_montante,
          venda_colaborador_atualizacao = _new.movimento_colaborador_id,
          venda_dataatualizacao = current_timestamp,
          venda_datafim = _venda.venda_datafim
      where venda_id = _venda.venda_id;
    
  end if;

  return null;
end;
$$;


--
-- Name: functg_producao_after_insert_movement_produto(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_producao_after_insert_movement_produto() RETURNS trigger
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
        set produto_stock = produto_stock + _new.producao_quantidadecomerciavel,
            produto_colaborador_atualizacao = _new.producao_colaborador_id,
            produto_dataatualizacao = _new.producao_dataregisto
        where produto_id = _new.producao_produto_id
      ;
    end if;

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
-- Name: functg_setor_after_updade_disable_disable_children(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_setor_after_updade_disable_disable_children() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
  _new ggviario.setor;
  _old ggviario.setor;
  _const rule.constant;
begin
  _new := new;
  _old := old;
  _const := rule.constant_init();

  if _new.setor_estado != _old.setor_estado  and _new.setor_estado = _const.setor_estado_fechado then
    -- Fechar todos os setores filhos a esse setor
    update ggviario.setor
      set setor_estado = _const.setor_estado_fechado,
          setor_colaborador_atualizacao = _new.setor_colaborador_atualizacao,
          setor_dataatualizacao = current_timestamp
      where setor_setor_id = _old.setor_id;
  end if;
  return null;
end;
$$;


--
-- Name: functg_venda_intert_update_produto_stock(); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION functg_venda_intert_update_produto_stock() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
    _new ggviario.venda;
    _const rule.constant;
    _produto ggviario.produto;
  begin
    _const := rule.constant_init();
    _new := new;
    -- marcar os itens da venda como feito!
    update ggviario.produto
      set produto_stock = produto_stock - _new.venda_quantidadeproduto ,
          produto_colaborador_atualizacao = _new.venda_colaborador_id ,
          produto_dataatualizacao = current_timestamp
      where produto_id = _new.venda_produto_id
    ;
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
-- Name: movimento_estado_desc(ggviario.movimento, constant); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION movimento_estado_desc(_movimento ggviario.movimento, _const constant DEFAULT constant_init()) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare
  
begin
  if _const is null then _const := rule.constant_init(); end if;
  return 'Ativo';
end;
$$;


--
-- Name: movimento_insert(uuid, uuid, smallint, character varying, date, numeric, character varying, uuid, uuid, uuid, boolean, integer); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION movimento_insert(arg_colaborador_id uuid, arg_conta_id uuid, arg_tmovimento_id smallint, arg_movimento_documento character varying, arg_movimento_data date, arg_movimento_montante numeric, arg_movimento_libele character varying, arg_venda_id uuid DEFAULT NULL::uuid, arg_despesa_id uuid DEFAULT NULL::uuid, arg_movimento_id uuid DEFAULT NULL::uuid, arg_movimento_devolucao boolean DEFAULT false, arg_movimento_transferencianumero integer DEFAULT NULL::integer) RETURNS ggviario.movimento
    LANGUAGE plpgsql
    AS $$
declare
  _const rule.constant;
  _codigo record;
  _movimento ggviario.movimento;
begin
  _const := rule.constant_init();
  select cod.* into _codigo
    from rule.codigo_generate( _const.codigo_amortizacao_letra, _const.codigo_amortizacao_digitos ) cod;


  insert into ggviario.movimento(
    movimento_colaborador_id,
    movimento_conta_id,
    movimento_tmovimento_id,
    movimento_documento,
    movimento_data,
    movimento_montante,
    movimento_libele,
    movimento_venda_id,
    movimento_despeda_id,
    movimento_movimento_id,
    movimento_devolucao,
    movimento_transferencianumero,
    movimento_codigo
  ) values (
    arg_colaborador_id,
    arg_conta_id,
    arg_tmovimento_id,
    arg_movimento_documento,
    arg_movimento_data,
    arg_movimento_montante,
    arg_movimento_libele,
    arg_venda_id,
    arg_despesa_id,
    arg_movimento_id,
    arg_movimento_devolucao,
    arg_movimento_transferencianumero,
    _codigo.codigo_codigo
  ) returning * into _movimento;

  return _movimento;
end;


$$;


--
-- Name: produto_estado(ggviario.produto, constant); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION produto_estado(_produto ggviario.produto, _const constant) RETURNS smallint
    LANGUAGE sql
    AS $$
select case
      when _produto.produto_estado = _const.produto_estado_fechado then _const.produto_estado_fechado

      when _produto.produto_estado = _const.produto_estado_ativo and _produto.produto_servicostockdinamico
            and _produto.produto_stock < 1 then _const.produto_estado_stock_vazio

      when _produto.produto_estado = _const.produto_estado_ativo and _produto.produto_servicostockdinamico
        and coalesce( _produto.produto_stockminimo, 0 ) > _produto.produto_stock then _const.produto_estado_stock_minimo
  
      when _produto.produto_estado = _const.produto_estado_ativo then _const.produto_estado_ok
    end;
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
-- Name: select_setor_cascade(uuid); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION select_setor_cascade(arg_setor_id uuid) RETURNS SETOF ggviario.setor
    LANGUAGE sql
    AS $$
    with recursive rel_tree as (
        select se.*
          from setor se
          where se.setor_id = arg_setor_id
      union all
        select  child.*
        from setor child
          join rel_tree parent on parent.setor_id = child.setor_setor_id
    )
    select *
      from rel_tree tree
      order by tree.setor_posicao;
$$;


--
-- Name: select_setor_cascade_witch_raiz(uuid, integer); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION select_setor_cascade_witch_raiz(arg_setor_id uuid, part_lenght integer DEFAULT 3) RETURNS TABLE(setor_id uuid, setor_setor_id uuid, setor_colaborador_id uuid, setor_colaborador_atualizacao uuid, setor_codigo character varying, setor_nome character varying, setor_posicao smallint, setor_nivel smallint, setor_totalsubsetores smallint, setor_estado smallint, setor_dataregisto timestamp without time zone, setor_dataatualizacao timestamp without time zone, setor_raiz character varying)
    LANGUAGE sql
    AS $$
with recursive rel_tree as (
    select se.*, lpad( se.setor_posicao::character varying, part_lenght, '0' ) as setor_raiz
      from setor se
      where se.setor_id = arg_setor_id
  union all
    select  child.*, parent.setor_raiz||'.'||lpad( child.setor_posicao::character varying, part_lenght, '0')
                                                   from setor child
      join rel_tree parent on parent.setor_id = child.setor_setor_id
)
  select *
    from rel_tree tree
    order by tree.setor_posicao;
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


--
-- Name: venda_estado_desc(ggviario.venda, constant); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION venda_estado_desc(_venda ggviario.venda, _const constant DEFAULT constant_init()) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare
begin
  if _const is null then _const := rule.constant_init(); end if;
  return case
         when _venda.venda_estado = _const.venda_estado_pendente then 'Pendente'
         when _venda.venda_estado = _const.venda_estado_empagamento then 'Em pagamento'
         when _venda.venda_estado = _const.venda_estado_pago then 'Pago'
         when _venda.venda_estado = _const.venda_estado_anulado then 'Anulado'
  end;
end;
$$;


--
-- Name: venda_fatura_generatenext(ggviario.tipovenda); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION venda_fatura_generatenext(_tipocompra ggviario.tipovenda) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare
begin
  return nextval( 'ggviario.seq_faturanumero' )::character varying;
end;
$$;


--
-- Name: venda_insert(uuid, uuid, uuid, uuid, smallint, numeric, numeric, numeric, numeric, numeric, date, date); Type: FUNCTION; Schema: rule; Owner: -
--

CREATE FUNCTION venda_insert(arg_colaborador_id uuid, arg_produto_id uuid, arg_unidade_id uuid, arg_cliente_id uuid, arg_tvenda_id smallint, arg_venda_quantidade numeric, arg_venda_montanteunitario numeric, arg_venda_montantebruto numeric, arg_venda_montantedesconto numeric, arg_venda_montantepagar numeric, arg_venda_data date, arg_venda_datafinalizar date) RETURNS lib.result
    LANGUAGE plpgsql
    AS $$
declare
  arg_faturanumero character varying;
  arg_venda_quantidadeproduto numeric;
  _venda ggviario.venda;
  _const rule.constant;
  _preco ggviario.preco;
  _tipovenda ggviario.tipovenda;
  _produto ggviario.produto;
begin
  _const := rule.constant_init();
  _tipovenda := ggviario.get_tipovenda( arg_tvenda_id );
  _produto := ggviario.get_produto( arg_produto_id );

  if arg_venda_montanteunitario is null then raise notice 'Para Aqui arg_venda_montanteunitario esta null';  end if;



  select * into _preco
  from ggviario.preco pre
  where pre.preco_produto_id = arg_produto_id
        and pre.preco_unidade_id = arg_unidade_id
        and pre.preco_estado = _const.preco_estado_ativo
  order by coalesce( pre.preco_dataatualizacao, pre.preco_dataregisto ) desc
  ;

  if _preco.preco_quantidadeproduto is null then
    return lib.result_false( 'Não foi encontrado nenhuma equivalencia do produto para a unidade!'  );
  end if;

  if _preco is null then
    return lib.result_false( 'Não foi encontrado nenhum preço ativo para a unidade do produto!');
  end if;

  select codigo_codigo into arg_faturanumero
    from rule.codigo_generate( _tipovenda.tvenda_letra, _tipovenda.tvenda_ditigos );


  arg_venda_quantidadeproduto := arg_venda_quantidade * _preco.preco_quantidadeproduto;
  if coalesce( _produto.produto_stock, 0 ) < arg_venda_quantidadeproduto then
    return lib.result_false(
      'Não existe produto disponivel no stock para essa operação!'
    );
  end if;

  insert into ggviario.venda(
    venda_produto_id,
    venda_unidade_id,
    venda_cliente_id,
    venda_tvenda_id,
    venda_colaborador_id,
    venda_faturanumero,
    venda_quantidade,
    venda_quantidadeproduto,
    venda_montanteunitario,
    venda_montantebruto,
    venda_montantedesconto,
    venda_montantepagar,
    venda_data,
    venda_datafinalizar,
    venda_estado
  ) values (
    arg_produto_id,
    arg_unidade_id,
    arg_cliente_id,
    arg_tvenda_id,
    arg_colaborador_id,
    arg_faturanumero,
    arg_venda_quantidade,
    arg_venda_quantidadeproduto,
    arg_venda_montanteunitario,
    arg_venda_montantebruto,
    arg_venda_montantedesconto,
    arg_venda_montantepagar,
    arg_venda_data,
    arg_venda_datafinalizar,
    _const.venda_estado_pendente
  ) returning * into _venda;

  return lib.result_true(
      jsonb_build_object(
          'venda', _venda
      )
  );
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
    previlegio_dataatualizacao timestamp without time zone
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
-- Name: codigo; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE codigo (
    codigo_letra character(1) NOT NULL,
    codigo_digitos smallint NOT NULL,
    codigo_ano smallint DEFAULT (substr((date_part('year'::text, CURRENT_DATE))::text, 1, 2))::smallint NOT NULL,
    codigo_numero integer DEFAULT 1 NOT NULL
);


--
-- Name: distrito; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE distrito (
    distrito_id smallint NOT NULL,
    distrito_nome character varying(32)
);


--
-- Name: localproducao; Type: TABLE; Schema: ggviario; Owner: -
--

CREATE TABLE localproducao (
    localproducao_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    localproducao_produto_id uuid NOT NULL,
    localproducao_setor_id uuid NOT NULL,
    localproducao_colaborador_id uuid NOT NULL,
    localproducao_colaborador_atualizacao uuid,
    localproducao_estado smallint DEFAULT 1 NOT NULL,
    localproducao_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    localproducao_dataatualizacao timestamp without time zone
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
    producao_codigo character varying(9) NOT NULL,
    producao_data date,
    producao_quantidadetotal numeric NOT NULL,
    producao_quantidadecomerciavel numeric DEFAULT 0 NOT NULL,
    producao_quantidadedefeituosa numeric DEFAULT 0 NOT NULL,
    producao_montanteprevisto numeric DEFAULT 0 NOT NULL,
    producao_estado smallint DEFAULT 1 NOT NULL,
    producao_dataregisto timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    producao_dataatualizacao timestamp without time zone
);


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

COPY previlegio (previlegio_id, previlegio_perfil_id, previlegio_menu_id, previlegio_colaborador_id, previlegio_colaborador_atualizacao, previlegio_tipo, previlegio_estado, previlegio_dataregisto, previlegio_dataatualizacao) FROM stdin;
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
-- Data for Name: audit; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY audit (audit_id, audit_colaborador_id, audit_key, audit_title, audit_message, audit_object, audit_dataregisto) FROM stdin;
\.


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY categoria (categoria_id, categoria_colaborador_id, categoria_colaborador_atualizacao, categoria_categoria_id, categoria_codigo, categoria_nome, categoria_posisao, categoria_nivel, categoria_estado, categoria_dataregisto, categoria_dataatualizacao) FROM stdin;
5b64a898-7b6c-4085-94cc-8686f1c648b1	00000000-0000-0000-0000-000000000001	\N	00000000-0000-0000-0000-000000000001	A02/18	Aviario	1	1	1	2018-03-04 19:08:12.562172	\N
00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	\N	A01/18	Padrão	0	0	1	2018-02-09 18:46:15.84334	\N
\.


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY cliente (cliente_id, cliente_sexo_id, cliente_distrito_id, cliente_tdocumento_id, cliente_colaborador_id, cliente_colaborador_atualizacao, cliente_codigo, cliente_documentonumero, cliente_nome, cliente_apelido, cliente_datanascimento, cliente_telefone, cliente_telemovel, cliente_mail, cliente_morada, cliente_localtrabalho, cliente_estado, cliente_dataregisto, cliente_dataatualizacao) FROM stdin;
00000000-0000-0000-0000-000000000001	\N	\N	\N	00000000-0000-0000-0000-000000000001	\N	C0001/18	\N	Anonimo	\N	\N	\N	\N	\N	\N	\N	1	2018-02-28 22:12:01.703349	\N
69564f90-f967-4325-a75f-673791f62dd2	\N	\N	\N	00000000-0000-0000-0000-000000000001	\N	C0002/18	\N	Lesineide	\N	\N	\N	\N	\N	\N	\N	1	2018-03-21 14:13:19.129667	\N
\.


--
-- Data for Name: codigo; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY codigo (codigo_letra, codigo_digitos, codigo_ano, codigo_numero) FROM stdin;
P	2	18	2
A	2	18	2
S	2	18	10
C	4	18	2
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

COPY despesa (despesa_id, despesa_fornecedor_id, despesa_produto_id, despesa_unidade_id, despesa_colaborador_id, despesa_colaborador_atualizacao, despesa_codigo, despesa_data, despesa_numerofatura, despesa_quatidade, despesa_quantidadeproduto, despesa_montanteunitario, despesa_montantetotal, despesa_montanteamortizado, despesa_dataultimamovimento, despesa_datafim, despesa_estado, despesa_dataregisto, despesa_dataatualizacao) FROM stdin;
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
-- Data for Name: fornecedor; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY fornecedor (fornecedor_id, fornecedor_distrito_id, fornecedor_colaborador_id, fornecedor_colaborador_atualizacao, fornecedor_codigo, fornecedor_nome, fornecedor_nif, fornecedor_telefone, fornecedor_telemovel, fornecedor_mail, fornecedor_local, fornecedor_estado, fornecedor_dataregisto, fornecedor_dataatualizacao) FROM stdin;
00000000-0000-0000-0000-000000000001	\N	00000000-0000-0000-0000-000000000001	\N	\N	Ambulante	\N	\N	\N	\N	\N	1	2018-02-11 12:44:15.148861	\N
\.


--
-- Data for Name: localproducao; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY localproducao (localproducao_id, localproducao_produto_id, localproducao_setor_id, localproducao_colaborador_id, localproducao_colaborador_atualizacao, localproducao_estado, localproducao_dataregisto, localproducao_dataatualizacao) FROM stdin;
b70688c2-9373-49dd-b55d-32bc1db68dd9	00000000-0000-0000-0000-000000000002	14a6ce8d-b9d6-4675-becd-60dcbb1f35cb	00000000-0000-0000-0000-000000000001	\N	1	2018-03-21 14:04:20.912359	\N
891ca99a-9a8d-4e5e-9ea6-4b95b132bb38	00000000-0000-0000-0000-000000000001	0fbc236e-8ac6-4e14-a1bd-e555a10e4f52	00000000-0000-0000-0000-000000000001	\N	1	2018-03-21 14:09:53.502588	\N
\.


--
-- Data for Name: movimento; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY movimento (movimento_id, movimento_conta_id, movimento_tmovimento_id, movimento_colaborador_id, movimento_colaborador_atualizacao, movimento_movimento_id, movimento_venda_id, movimento_despeda_id, movimento_codigo, movimento_data, movimento_documento, movimento_montante, movimento_libele, movimento_transferencianumero, movimento_devolucao, movimento_devolucaoultimadada, movimento_devolucamontantedevolvido, movimento_estado, movimento_dataregisto, movimento_dataatualizacao) FROM stdin;
\.


--
-- Data for Name: preco; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY preco (preco_id, preco_produto_id, preco_unidade_id, preco_colaborador_id, preco_colaborador_atualizacao, preco_custounidade, preco_quantidadeproduto, preco_base, preco_estado, preco_dataregisto, preco_dataatualizacao) FROM stdin;
e8fd650d-ac42-4218-8b24-9579575976e2	00000000-0000-0000-0000-000000000001	8c4c991c-4a24-419e-8c24-ad995e92a57c	00000000-0000-0000-0000-000000000001	\N	5.0	1.0	t	1	2018-03-21 14:18:02.834377	\N
c65a8956-a291-4e5f-86de-399ad64254a3	00000000-0000-0000-0000-000000000001	1ee5d326-d122-46c0-aaf9-b54940b629cd	00000000-0000-0000-0000-000000000001	\N	130.0	30.0	f	1	2018-03-21 14:19:05.689613	\N
3b4313bd-3055-4967-a7b4-c9d08cf2a41b	00000000-0000-0000-0000-000000000001	f5ad39ec-2f29-4d61-a70b-b1f102bc71d3	00000000-0000-0000-0000-000000000001	\N	65.0	15.0	f	1	2018-03-21 14:19:30.896518	\N
\.


--
-- Data for Name: producao; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY producao (producao_id, producao_produto_id, producao_setor_id, producao_colaborador_id, producao_colaborador_atualizacao, producao_codigo, producao_data, producao_quantidadetotal, producao_quantidadecomerciavel, producao_quantidadedefeituosa, producao_montanteprevisto, producao_estado, producao_dataregisto, producao_dataatualizacao) FROM stdin;
\.


--
-- Data for Name: produto; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY produto (produto_id, produto_categoria_id, produto_colaborador_id, produto_colaborador_atualizacao, produto_codigo, produto_nome, produto_stock, produto_stockminimo, produto_servicovenda, produto_servicocompra, produto_servicoproducao, produto_servicostockdinamico, produto_estado, produto_dataregisto, produto_dataatualizacao) FROM stdin;
00000000-0000-0000-0000-000000000001	5b64a898-7b6c-4085-94cc-8686f1c648b1	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	P01/18	Ovos	0	12.0	t	t	t	t	1	2018-02-09 18:48:34.984037	2018-03-20 13:36:01.623268
00000000-0000-0000-0000-000000000002	5b64a898-7b6c-4085-94cc-8686f1c648b1	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	P02/18	Goiaba	0	12.0	t	t	t	t	1	2018-02-09 18:48:34.984037	2018-03-20 13:36:01.623268
\.


--
-- Data for Name: setor; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY setor (setor_id, setor_setor_id, setor_colaborador_id, setor_colaborador_atualizacao, setor_codigo, setor_nome, setor_posicao, setor_nivel, setor_totalsubsetores, setor_estado, setor_dataregisto, setor_dataatualizacao) FROM stdin;
00000000-0000-0000-0000-000000000001	\N	00000000-0000-0000-0000-000000000001	\N	S01/18	PADRÃO	0	0	14	2	2018-02-11 11:41:46.814429	2018-03-21 14:02:13.424349
0fbc236e-8ac6-4e14-a1bd-e555a10e4f52	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	S06/18	AVIARIO	1	1	3	2	2018-03-21 14:02:13.424349	2018-03-21 14:03:08.436129
670916b4-960d-46aa-a634-67b48779bec1	0fbc236e-8ac6-4e14-a1bd-e555a10e4f52	00000000-0000-0000-0000-000000000001	\N	S09/18	NOVA	2	2	0	1	2018-03-21 14:03:08.436129	\N
48461498-018d-4a1f-b930-519f5094e235	0fbc236e-8ac6-4e14-a1bd-e555a10e4f52	00000000-0000-0000-0000-000000000001	\N	S07/18	VELHA	3	2	0	1	2018-03-21 14:02:32.177849	\N
17ee7c2c-c240-494f-80c2-fe4ea4bb8b76	0fbc236e-8ac6-4e14-a1bd-e555a10e4f52	00000000-0000-0000-0000-000000000001	\N	S08/18	VELHA DE NOVA	4	2	0	1	2018-03-21 14:02:55.071683	\N
bb641c4f-5f4c-4396-86bc-ec52d4f65982	00000000-0000-0000-0000-000000000001	00000000-0000-0000-0000-000000000001	\N	S02/18	Buraco	5	1	2	2	2018-03-21 13:58:30.404519	2018-03-21 14:01:48.402471
14a6ce8d-b9d6-4675-becd-60dcbb1f35cb	bb641c4f-5f4c-4396-86bc-ec52d4f65982	00000000-0000-0000-0000-000000000001	\N	S03/18	Campo	6	2	2	2	2018-03-21 13:59:00.900675	2018-03-21 14:06:55.725693
4f706789-c828-4db6-b822-c063cfb47179	14a6ce8d-b9d6-4675-becd-60dcbb1f35cb	00000000-0000-0000-0000-000000000001	\N	S04/18	Buraco de buraco	7	3	0	1	2018-03-21 14:00:50.589399	\N
74584c43-7bc7-4d97-8c43-4e60626c645f	14a6ce8d-b9d6-4675-becd-60dcbb1f35cb	00000000-0000-0000-0000-000000000001	\N	S10/18	Campo plano	8	3	0	1	2018-03-21 14:06:55.725693	\N
4c4651e0-a6f3-4b9c-aaee-f2777e4fe572	bb641c4f-5f4c-4396-86bc-ec52d4f65982	00000000-0000-0000-0000-000000000001	\N	S05/18	Percipicio	9	2	0	1	2018-03-21 14:01:48.402471	\N
\.


--
-- Data for Name: sexo; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY sexo (sexo_id, sexo_desc) FROM stdin;
1	Masculino
2	Feminino
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
-- Data for Name: tipovenda; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY tipovenda (tvenda_id, tvenda_desc, tvenda_letra, tvenda_ditigos) FROM stdin;
2	Divida	D	5
1	Venda	V	5
\.


--
-- Data for Name: unidade; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY unidade (unidade_id, unidade_colaborador_id, unidade_colaborador_atualizacao, unidade_nome, unidade_codigo, unidade_estado, unidade_dataregisto, unidade_dataatualizacao) FROM stdin;
8c4c991c-4a24-419e-8c24-ad995e92a57c	00000000-0000-0000-0000-000000000001	\N	Unidade	Qt	1	2018-03-21 14:16:22.407942	\N
1ee5d326-d122-46c0-aaf9-b54940b629cd	00000000-0000-0000-0000-000000000001	\N	Cartão	CT	1	2018-03-21 14:16:41.121493	\N
f5ad39ec-2f29-4d61-a70b-b1f102bc71d3	00000000-0000-0000-0000-000000000001	\N	Meio Cartão	MCT	1	2018-03-21 14:17:17.734521	\N
\.


--
-- Data for Name: venda; Type: TABLE DATA; Schema: ggviario; Owner: -
--

COPY venda (venda_id, venda_produto_id, venda_unidade_id, venda_cliente_id, venda_tvenda_id, venda_colaborador_id, venda_colaborador_atualizacao, venda_faturanumero, venda_quantidade, venda_quantidadeproduto, venda_montanteunitario, venda_montantebruto, venda_montantedesconto, venda_montantepagar, venda_montanteamortizado, venda_data, venda_datafinalizar, venda_datafim, venda_dataultimamovimentacao, venda_observacao, venda_estado, venda_dataregisto, venda_dataatualizacao) FROM stdin;
\.


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
-- Name: audit pk_audith_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY audit
    ADD CONSTRAINT pk_audith_id PRIMARY KEY (audit_id);


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
-- Name: codigo pk_codigo_unico; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY codigo
    ADD CONSTRAINT pk_codigo_unico PRIMARY KEY (codigo_letra, codigo_ano, codigo_digitos);


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
-- Name: fornecedor pk_fornecedor_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY fornecedor
    ADD CONSTRAINT pk_fornecedor_id PRIMARY KEY (fornecedor_id);


--
-- Name: localproducao pk_localproducao_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY localproducao
    ADD CONSTRAINT pk_localproducao_id PRIMARY KEY (localproducao_id);


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
-- Name: tipovenda pk_tvenda_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY tipovenda
    ADD CONSTRAINT pk_tvenda_id PRIMARY KEY (tvenda_id);


--
-- Name: unidade pk_unidade_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY unidade
    ADD CONSTRAINT pk_unidade_id PRIMARY KEY (unidade_id);


--
-- Name: venda pk_venda_id; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT pk_venda_id PRIMARY KEY (venda_id);


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
-- Name: produto uq_produto_codigo; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT uq_produto_codigo UNIQUE (produto_codigo);


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
-- Name: tipovenda uq_tcompra_desc; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY tipovenda
    ADD CONSTRAINT uq_tcompra_desc UNIQUE (tvenda_desc);


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


--
-- Name: venda uq_venda_faturanumero; Type: CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT uq_venda_faturanumero UNIQUE (venda_faturanumero);


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
-- Name: categoria_categorai_codigo_uindex; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE UNIQUE INDEX categoria_categorai_codigo_uindex ON categoria USING btree (categoria_codigo);


--
-- Name: cliente_cliente_codigo_uindex; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE UNIQUE INDEX cliente_cliente_codigo_uindex ON cliente USING btree (cliente_codigo);


--
-- Name: despesa_despesa_codigo_uindex; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE UNIQUE INDEX despesa_despesa_codigo_uindex ON despesa USING btree (despesa_codigo);


--
-- Name: idx_audit_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_audit_colaborador_id ON audit USING btree (audit_colaborador_id);


--
-- Name: idx_audit_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_audit_dataregisto ON audit USING btree (audit_dataregisto);


--
-- Name: idx_audit_key; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_audit_key ON audit USING btree (audit_key);


--
-- Name: idx_audit_title; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_audit_title ON audit USING btree (audit_title);


--
-- Name: idx_categoria_categoraia_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_categoria_categoraia_id ON categoria USING btree (categoria_categoria_id);


--
-- Name: idx_categoria_colaborador_atualizacao; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_categoria_colaborador_atualizacao ON categoria USING btree (categoria_colaborador_atualizacao);


--
-- Name: idx_categoria_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_categoria_colaborador_id ON categoria USING btree (categoria_colaborador_id);


--
-- Name: idx_categoria_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_categoria_dataregisto ON categoria USING btree (categoria_dataregisto);


--
-- Name: idx_categoria_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_categoria_estado ON categoria USING btree (categoria_estado);


--
-- Name: idx_categoria_nivel; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_categoria_nivel ON categoria USING btree (categoria_nivel);


--
-- Name: idx_categoria_posicao; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_categoria_posicao ON categoria USING btree (categoria_posisao);


--
-- Name: idx_cliente_apelido; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_cliente_apelido ON cliente USING btree (cliente_apelido);


--
-- Name: idx_cliente_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_cliente_colaborador_id ON cliente USING btree (cliente_colaborador_id);


--
-- Name: idx_cliente_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_cliente_dataregisto ON cliente USING btree (cliente_dataregisto);


--
-- Name: idx_cliente_distrito_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_cliente_distrito_id ON cliente USING btree (cliente_distrito_id);


--
-- Name: idx_cliente_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_cliente_estado ON cliente USING btree (cliente_estado);


--
-- Name: idx_cliente_nome; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_cliente_nome ON cliente USING btree (cliente_nome);


--
-- Name: idx_cliente_sexo_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_cliente_sexo_id ON cliente USING btree (cliente_sexo_id);


--
-- Name: idx_conta_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_conta_colaborador_id ON conta USING btree (conta_colaborador_id);


--
-- Name: idx_conta_conta_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_conta_conta_id ON conta USING btree (conta_conta_id);


--
-- Name: idx_conta_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_conta_dataregisto ON conta USING btree (conta_dataregisto);


--
-- Name: idx_conta_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_conta_estado ON conta USING btree (conta_estado);


--
-- Name: idx_conta_nome; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_conta_nome ON conta USING btree (conta_nome);


--
-- Name: idx_conta_numero; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_conta_numero ON conta USING btree (conta_numero);


--
-- Name: idx_conta_tconta_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_conta_tconta_id ON conta USING btree (conta_tconta_id);


--
-- Name: idx_despesa_codigo; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_despesa_codigo ON despesa USING btree (despesa_codigo);


--
-- Name: idx_despesa_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_despesa_colaborador_id ON despesa USING btree (despesa_colaborador_id);


--
-- Name: idx_despesa_data; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_despesa_data ON despesa USING btree (despesa_data DESC);


--
-- Name: idx_despesa_datafim; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_despesa_datafim ON despesa USING btree (despesa_datafim DESC);


--
-- Name: idx_despesa_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_despesa_dataregisto ON despesa USING btree (despesa_dataregisto DESC);


--
-- Name: idx_despesa_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_despesa_estado ON despesa USING btree (despesa_estado);


--
-- Name: idx_despesa_fornecedor_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_despesa_fornecedor_id ON despesa USING btree (despesa_fornecedor_id);


--
-- Name: idx_despesa_numerofatura; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_despesa_numerofatura ON despesa USING btree (despesa_numerofatura);


--
-- Name: idx_despesa_produto_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_despesa_produto_id ON despesa USING btree (despesa_produto_id);


--
-- Name: idx_despesa_unidade_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_despesa_unidade_id ON despesa USING btree (despesa_unidade_id);


--
-- Name: idx_fornecedor_codigo; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_fornecedor_codigo ON fornecedor USING btree (fornecedor_codigo);


--
-- Name: idx_fornecedor_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_fornecedor_colaborador_id ON fornecedor USING btree (fornecedor_colaborador_id);


--
-- Name: idx_fornecedor_distrito_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_fornecedor_distrito_id ON fornecedor USING btree (fornecedor_distrito_id);


--
-- Name: idx_fornecedor_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_fornecedor_estado ON fornecedor USING btree (fornecedor_estado);


--
-- Name: idx_fornecedor_nome; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_fornecedor_nome ON fornecedor USING btree (fornecedor_nome);


--
-- Name: idx_localproducao_colaborador_atualizacao; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_localproducao_colaborador_atualizacao ON localproducao USING btree (localproducao_colaborador_atualizacao);


--
-- Name: idx_localproducao_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_localproducao_colaborador_id ON localproducao USING btree (localproducao_colaborador_id);


--
-- Name: idx_localproducao_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_localproducao_dataregisto ON localproducao USING btree (localproducao_dataregisto);


--
-- Name: idx_localproducao_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_localproducao_estado ON localproducao USING btree (localproducao_estado);


--
-- Name: idx_localproducao_produto_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_localproducao_produto_id ON localproducao USING btree (localproducao_produto_id);


--
-- Name: idx_localproducao_setor_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_localproducao_setor_id ON localproducao USING btree (localproducao_setor_id);


--
-- Name: idx_movimento_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_colaborador_id ON movimento USING btree (movimento_colaborador_id);


--
-- Name: idx_movimento_conta_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_conta_id ON movimento USING btree (movimento_conta_id);


--
-- Name: idx_movimento_data; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_data ON movimento USING btree (movimento_data DESC);


--
-- Name: idx_movimento_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_dataregisto ON movimento USING btree (movimento_dataregisto);


--
-- Name: idx_movimento_devolucao; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_devolucao ON movimento USING btree (movimento_devolucao);


--
-- Name: idx_movimento_devolucaomontantedevolvido; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_devolucaomontantedevolvido ON movimento USING btree (movimento_devolucamontantedevolvido);


--
-- Name: idx_movimento_devolucaoultimadata; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_devolucaoultimadata ON movimento USING btree (movimento_devolucaoultimadada DESC);


--
-- Name: idx_movimento_documento; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_documento ON movimento USING btree (movimento_documento);


--
-- Name: idx_movimento_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_estado ON movimento USING btree (movimento_estado);


--
-- Name: idx_movimento_movimento_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_movimento_id ON movimento USING btree (movimento_movimento_id);


--
-- Name: idx_movimento_tmovimento_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_tmovimento_id ON movimento USING btree (movimento_tmovimento_id);


--
-- Name: idx_movimento_venda_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_movimento_venda_id ON movimento USING btree (movimento_venda_id);


--
-- Name: idx_preco_base; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_preco_base ON preco USING btree (preco_base);


--
-- Name: idx_preco_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_preco_colaborador_id ON preco USING btree (preco_colaborador_id);


--
-- Name: idx_preco_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_preco_dataregisto ON preco USING btree (preco_dataregisto DESC);


--
-- Name: idx_preco_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_preco_estado ON preco USING btree (preco_estado);


--
-- Name: idx_preco_produto_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_preco_produto_id ON preco USING btree (preco_produto_id);


--
-- Name: idx_preco_unidade_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_preco_unidade_id ON preco USING btree (preco_unidade_id);


--
-- Name: idx_producao_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_producao_colaborador_id ON producao USING btree (producao_colaborador_id);


--
-- Name: idx_producao_data; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_producao_data ON producao USING btree (producao_data DESC);


--
-- Name: idx_producao_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_producao_dataregisto ON producao USING btree (producao_dataregisto DESC);


--
-- Name: idx_producao_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_producao_estado ON producao USING btree (producao_estado);


--
-- Name: idx_producao_produto_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_producao_produto_id ON producao USING btree (producao_produto_id);


--
-- Name: idx_producao_setor_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_producao_setor_id ON producao USING btree (producao_setor_id);


--
-- Name: idx_produto_categoria; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_produto_categoria ON produto USING btree (produto_categoria_id);


--
-- Name: idx_produto_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_produto_dataregisto ON produto USING btree (produto_dataregisto DESC);


--
-- Name: idx_produto_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_produto_estado ON produto USING btree (produto_estado);


--
-- Name: idx_produto_servicocompra; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_produto_servicocompra ON produto USING btree (produto_servicocompra);


--
-- Name: idx_produto_servicostockdinamico; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_produto_servicostockdinamico ON produto USING btree (produto_servicostockdinamico);


--
-- Name: idx_produto_servicovenda; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_produto_servicovenda ON produto USING btree (produto_servicovenda);


--
-- Name: idx_produto_servioproducao; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_produto_servioproducao ON produto USING btree (produto_servicoproducao);


--
-- Name: idx_setor_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_setor_colaborador_id ON setor USING btree (setor_colaborador_id);


--
-- Name: idx_setor_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_setor_dataregisto ON setor USING btree (setor_dataregisto DESC);


--
-- Name: idx_setor_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_setor_estado ON setor USING btree (setor_estado);


--
-- Name: idx_setor_posicao; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_setor_posicao ON setor USING btree (setor_posicao);


--
-- Name: idx_setor_setor_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_setor_setor_id ON setor USING btree (setor_setor_id);


--
-- Name: idx_unidade_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_unidade_colaborador_id ON unidade USING btree (unidade_colaborador_id);


--
-- Name: idx_unidade_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_unidade_dataregisto ON unidade USING btree (unidade_dataregisto);


--
-- Name: idx_unidade_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_unidade_estado ON unidade USING btree (unidade_estado);


--
-- Name: idx_venda_cliente_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_venda_cliente_id ON venda USING btree (venda_cliente_id);


--
-- Name: idx_venda_colaborador_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_venda_colaborador_id ON venda USING btree (venda_colaborador_id);


--
-- Name: idx_venda_data; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_venda_data ON venda USING btree (venda_data DESC);


--
-- Name: idx_venda_datafim; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_venda_datafim ON venda USING btree (venda_datafim DESC);


--
-- Name: idx_venda_datafinalizar; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_venda_datafinalizar ON venda USING btree (venda_datafinalizar DESC);


--
-- Name: idx_venda_dataregisto; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_venda_dataregisto ON venda USING btree (venda_dataregisto DESC);


--
-- Name: idx_venda_estado; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_venda_estado ON venda USING btree (venda_estado DESC);


--
-- Name: idx_venda_produto_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_venda_produto_id ON venda USING btree (venda_produto_id);


--
-- Name: idx_venda_unidade_id; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE INDEX idx_venda_unidade_id ON venda USING btree (venda_unidade_id);


--
-- Name: producao_producao_codigo_uindex; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE UNIQUE INDEX producao_producao_codigo_uindex ON producao USING btree (producao_codigo);


--
-- Name: setor_setor_codigo_uindex; Type: INDEX; Schema: ggviario; Owner: -
--

CREATE UNIQUE INDEX setor_setor_codigo_uindex ON setor USING btree (setor_codigo);


--
-- Name: despesa tg_despesa_after_insert_update_stock; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_despesa_after_insert_update_stock AFTER INSERT ON despesa FOR EACH ROW EXECUTE PROCEDURE rule.functg_despesa_after_insert_update_stock();


--
-- Name: movimento tg_movimento_after_insert_update_conta; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_movimento_after_insert_update_conta AFTER INSERT ON movimento FOR EACH ROW EXECUTE PROCEDURE rule.functg_movimento_after_insert_update_conta();


--
-- Name: movimento tg_movimento_after_insert_update_despesa; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_movimento_after_insert_update_despesa AFTER INSERT ON movimento FOR EACH ROW EXECUTE PROCEDURE rule.functg_movimento_after_insert_update_despesa();


--
-- Name: movimento tg_movimento_after_insert_update_itemcompra; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_movimento_after_insert_update_itemcompra AFTER INSERT ON movimento FOR EACH ROW EXECUTE PROCEDURE rule.functg_movimento_after_insert_update_venda();


--
-- Name: movimento tg_movimento_after_insert_update_movimento_devolucao; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_movimento_after_insert_update_movimento_devolucao AFTER INSERT ON movimento FOR EACH ROW EXECUTE PROCEDURE rule.functg_movimento_after_insert_update_movimento_devolucao();


--
-- Name: producao tg_producao_after_insert_movement_sector_and_product; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_producao_after_insert_movement_sector_and_product AFTER INSERT ON producao FOR EACH ROW EXECUTE PROCEDURE rule.functg_producao_after_insert_movement_produto();


--
-- Name: setor tg_setor_after_insert_protect_parent; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_setor_after_insert_protect_parent AFTER INSERT ON setor FOR EACH ROW EXECUTE PROCEDURE rule.functg_setor_after_insert_protect_parent();


--
-- Name: setor tg_setor_after_updade_disable_disable_children; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_setor_after_updade_disable_disable_children AFTER UPDATE ON setor FOR EACH ROW EXECUTE PROCEDURE rule.functg_setor_after_updade_disable_disable_children();


--
-- Name: venda tg_venda_intert_update_produto_stock; Type: TRIGGER; Schema: ggviario; Owner: -
--

CREATE TRIGGER tg_venda_intert_update_produto_stock AFTER INSERT ON venda FOR EACH ROW EXECUTE PROCEDURE rule.functg_venda_intert_update_produto_stock();


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
-- Name: audit fk_audit_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY audit
    ADD CONSTRAINT fk_audit_to_colaborador FOREIGN KEY (audit_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


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
-- Name: localproducao fk_localproducao_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY localproducao
    ADD CONSTRAINT fk_localproducao_to_colaborador FOREIGN KEY (localproducao_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: localproducao fk_localproducao_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY localproducao
    ADD CONSTRAINT fk_localproducao_to_colaborador_atualizacao FOREIGN KEY (localproducao_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: localproducao fk_localproducao_to_produto; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY localproducao
    ADD CONSTRAINT fk_localproducao_to_produto FOREIGN KEY (localproducao_produto_id) REFERENCES produto(produto_id);


--
-- Name: localproducao fk_localproducao_to_setor; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY localproducao
    ADD CONSTRAINT fk_localproducao_to_setor FOREIGN KEY (localproducao_setor_id) REFERENCES setor(setor_id);


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
-- Name: venda fk_venda_to_cliente; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT fk_venda_to_cliente FOREIGN KEY (venda_cliente_id) REFERENCES cliente(cliente_id);


--
-- Name: venda fk_venda_to_colaborador; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT fk_venda_to_colaborador FOREIGN KEY (venda_colaborador_id) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: venda fk_venda_to_colaborador_atualizacao; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT fk_venda_to_colaborador_atualizacao FOREIGN KEY (venda_colaborador_atualizacao) REFERENCES colaborador.colaborador(colaborador_id);


--
-- Name: venda fk_venda_to_produto; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT fk_venda_to_produto FOREIGN KEY (venda_produto_id) REFERENCES produto(produto_id);


--
-- Name: venda fk_venda_to_tipovenda; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT fk_venda_to_tipovenda FOREIGN KEY (venda_tvenda_id) REFERENCES tipovenda(tvenda_id);


--
-- Name: venda fk_venda_to_unidade; Type: FK CONSTRAINT; Schema: ggviario; Owner: -
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT fk_venda_to_unidade FOREIGN KEY (venda_unidade_id) REFERENCES unidade(unidade_id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: -
--

GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

