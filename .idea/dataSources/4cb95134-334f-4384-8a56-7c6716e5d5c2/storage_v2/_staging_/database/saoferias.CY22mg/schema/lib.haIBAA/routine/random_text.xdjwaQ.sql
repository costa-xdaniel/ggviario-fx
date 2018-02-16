CREATE or replace FUNCTION random_text(text_lenght numeric)
  RETURNS text
LANGUAGE SQL
AS $$
select
        array_to_string(
            array(
                select
                    chr((65 + round(random() * 25)) :: integer )
                  from generate_series(1, text_lenght::integer )
            ), ''
        );
$$;

