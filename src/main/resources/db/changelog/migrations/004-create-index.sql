CREATE TABLE `index` (
                        id INT AUTO_INCREMENT NOT NULL,
                        page_id INT NOT NULL,
                        lemma_id INT NOT NULL,
                        `rank` FLOAT NOT NULL,
                        CONSTRAINT pk_index PRIMARY KEY (id)

)
