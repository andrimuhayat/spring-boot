-- CREATE DATABASE IF NOT EXISTS postgres;

CREATE TABLE IF NOT EXISTS users (
                       id SERIAL PRIMARY KEY,
                       username character varying(25),
                       password character varying(25),
                       created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
                       updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,

--                        CONSTRAINT username UNIQUE (users)
    );

GRANT ALL PRIVILEGES ON DATABASE postgres to "postgres";