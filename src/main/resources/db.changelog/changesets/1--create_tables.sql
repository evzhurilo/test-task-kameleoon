-- Создание таблицы "profiles"
CREATE TABLE IF NOT EXISTS profiles
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(255),
    email            VARCHAR(255),
    date_of_creation TIMESTAMP
);

-- Создание таблицы "quote"
CREATE TABLE IF NOT EXISTS quotes
(
    id               SERIAL PRIMARY KEY,
    content          TEXT      NOT NULL,
    date_of_creation TIMESTAMP NOT NULL,
    update_date      TIMESTAMP,
    user_id          INTEGER REFERENCES profiles (id) ON DELETE CASCADE
);

-- Создание таблицы "vote"
CREATE TABLE IF NOT EXISTS votes
(
    id       SERIAL PRIMARY KEY,
    type     VARCHAR(255) NOT NULL,
    quote_id INTEGER REFERENCES quotes (id) ON DELETE CASCADE
);
