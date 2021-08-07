
--better & faster way to get row count in SQL Postgres

--select table_schema,
--       table_name,
--       (xpath('/row/cnt/text()', xml_count))[1]::text::int as row_count
--from (
--  select table_name, table_schema,
--         query_to_xml(format('select count(*) as cnt from %I.%I', table_schema, table_name), false, true, '') as xml_count
--  from information_schema.tables
--  where table_schema = 'exam' and table_name = 'users'--<< change here for the schema you want
--) t
