
--ALTER TABLE subjects DROP COLUMN genre_id;
--ALTER TABLE topics RENAME category_id TO subject_id;

---ALTER TABLE topics DROP CONSTRAINT fk_topic_category_id;
--alter table topics drop column subject_id


--alter table topics
--add column subject_id int references subjects(id);
--alter table questions drop column title;

--alter table questions rename description to content;