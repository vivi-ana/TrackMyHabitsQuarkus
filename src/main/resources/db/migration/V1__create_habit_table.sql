CREATE TABLE IF NOT EXISTS habit (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    frequency VARCHAR(50) NOT NULL,
    local_date DATE NOT NULL
);