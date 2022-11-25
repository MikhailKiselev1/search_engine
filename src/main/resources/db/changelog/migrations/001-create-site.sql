CREATE TABLE site(
                    id INT AUTO_INCREMENT NOT NULL,
                    status ENUM('INDEXING', 'INDEXED', 'FAILED') NOT NULL,
                    status_time DATETIME NOT NULL,
                    last_error TEXT,
                    url VARCHAR(255) NOT NULL,
                    name VARCHAR(255) NOT NULL,
                    CONSTRAINT pk_site PRIMARY KEY (id)
)