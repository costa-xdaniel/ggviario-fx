drop FUNCTION ggviario.get_tipovenda(arg_tvenda_id smallint);
CREATE FUNCTION ggviario.get_tipovenda(arg_tvenda_id smallint)
  RETURNS tipovenda
LANGUAGE SQL
AS $$
select * from ggviario.tipovenda tco where tco.tvenda_id = arg_tvenda_id;
$$;

