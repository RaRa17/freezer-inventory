CREATE TABLE IF NOT EXISTS frozen_item
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  quantity INT,
  frozen_at DATE,
  PRIMARY KEY (id)
);
