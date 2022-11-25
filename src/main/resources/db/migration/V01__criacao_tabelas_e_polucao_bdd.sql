-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.30 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura para tabela api-noticias.tb_etiqueta
CREATE TABLE IF NOT EXISTS `tb_etiqueta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `numero_acessos` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela api-noticias.tb_etiqueta: ~3 rows (aproximadamente)
INSERT INTO `tb_etiqueta` (`id`, `nome`, `numero_acessos`) VALUES
	(1, 'brasil', 0),
	(2, 'euro', 0),
	(3, 'dolar', 0);

-- Copiando estrutura para tabela api-noticias.tb_historico
CREATE TABLE IF NOT EXISTS `tb_historico` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_acesso` varchar(255) DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  `nome_etiqueta` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela api-noticias.tb_historico: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela api-noticias.tb_perfil
CREATE TABLE IF NOT EXISTS `tb_perfil` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela api-noticias.tb_perfil: ~2 rows (aproximadamente)
INSERT INTO `tb_perfil` (`id`, `nome`) VALUES
	(1, 'ADM'),
	(2, 'USUARIO');

-- Copiando estrutura para tabela api-noticias.tb_usuario
CREATE TABLE IF NOT EXISTS `tb_usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `perfil_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK49avuiu7e76c5xiivsek3yut4` (`perfil_id`),
  CONSTRAINT `FK49avuiu7e76c5xiivsek3yut4` FOREIGN KEY (`perfil_id`) REFERENCES `tb_perfil` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela api-noticias.tb_usuario: ~2 rows (aproximadamente)
INSERT INTO `tb_usuario` (`id`, `email`, `senha`, `perfil_id`) VALUES
	(1, 'adm@gft.com', '$2a$10$3hSmDUT9KL6j.CAOFG1EO.HHcvFlugQ2STxGo9T2hmrBVhu9UB51u', 1),
	(2, 'usuario@gft.com', '$2a$10$3hSmDUT9KL6j.CAOFG1EO.HHcvFlugQ2STxGo9T2hmrBVhu9UB51u', 2);

-- Copiando estrutura para tabela api-noticias.tb_usuario_etiqueta
CREATE TABLE IF NOT EXISTS `tb_usuario_etiqueta` (
  `usuario_id` bigint NOT NULL,
  `etiqueta_id` bigint NOT NULL,
  KEY `FKb2dvvgp55g6ypsw7phto7csk0` (`etiqueta_id`),
  KEY `FK6pfatqsnehtgmydxfo2t4eb2n` (`usuario_id`),
  CONSTRAINT `FK6pfatqsnehtgmydxfo2t4eb2n` FOREIGN KEY (`usuario_id`) REFERENCES `tb_usuario` (`id`),
  CONSTRAINT `FKb2dvvgp55g6ypsw7phto7csk0` FOREIGN KEY (`etiqueta_id`) REFERENCES `tb_etiqueta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela api-noticias.tb_usuario_etiqueta: ~3 rows (aproximadamente)
INSERT INTO `tb_usuario_etiqueta` (`usuario_id`, `etiqueta_id`) VALUES
	(2, 2),
	(2, 3),
	(2, 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
