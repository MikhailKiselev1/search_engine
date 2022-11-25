CREATE TABLE lemma(
                    id INT AUTO_INCREMENT NOT NULL,
                    site_id INT NOT NULL,
                    lemma VARCHAR(255) NOT NULL,
                    frequency INT NOT NULL,
                    CONSTRAINT pk_lemma PRIMARY KEY (id)

                    )