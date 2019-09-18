alter table reports drop column last_modified_date;
alter table reports drop column created_date;
alter table reports_aud drop column created_date;
alter table reports_aud drop column last_modified_date;
alter table indicators drop column created_date;
-- alter table indicators drop column last_modified_date;
alter table reports_aud add column user_name varchar (100);

create table indicators_aud

(
        id          INTEGER,
        value       VARCHAR(100),
        rev         INTEGER,
        rev_type    INTEGER,
        user_name   VARCHAR(100),
        ind_type   VARCHAR(100),
        timestamp   TIMESTAMP
);
