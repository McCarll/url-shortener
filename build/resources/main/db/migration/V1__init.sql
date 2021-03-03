
/* Autogenerated Sequences */

CREATE schema url;
CREATE SEQUENCE url.url_id_seq INCREMENT BY 1 START 1;

CREATE TABLE url.urlStorage
(
    url_id BIGINT DEFAULT nextval('url.url_id_seq'::regclass),
    short_url varchar not null,
    url_key varchar not null
);
