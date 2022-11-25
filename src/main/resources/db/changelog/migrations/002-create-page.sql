CREATE TABLE page(
                    id INT AUTO_INCREMENT NOT NULL,
                    site_id INT NOT NULL,
                    `path`  TEXT NOT NULL,
                    code INT NOT NULL,
                    content MEDIUMTEXT NOT NULL,
                    CONSTRAINT pk_page PRIMARY KEY (id)
)