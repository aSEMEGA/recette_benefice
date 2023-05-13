-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Sam 20 Juin 2020 à 11:52
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `dbcommerce`
--

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `codeprod` varchar(20) NOT NULL,
  `prix_achat` int(11) NOT NULL,
  `prix_vente` int(11) NOT NULL,
  PRIMARY KEY (`codeprod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `produit`
--

INSERT INTO `produit` (`codeprod`, `prix_achat`, `prix_vente`) VALUES
('Ordinateur', 195000, 235000),
('Smartphone', 95000, 115000),
('Tablette', 105000, 130000);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `produit_mois`
--
CREATE TABLE IF NOT EXISTS `produit_mois` (
`annee` smallint(6)
,`mois` varchar(15)
,`produit` varchar(20)
,`snombre` decimal(27,0)
,`srecette` decimal(37,0)
,`sbenefice` decimal(38,0)
);
-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

CREATE TABLE IF NOT EXISTS `vente` (
  `annee` smallint(6) NOT NULL,
  `mois` varchar(15) NOT NULL,
  `jour` smallint(6) NOT NULL,
  `produit` varchar(20) NOT NULL,
  `nombre` smallint(6) NOT NULL,
  PRIMARY KEY (`annee`,`mois`,`jour`,`produit`),
  KEY `fk` (`produit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `vente`
--

INSERT INTO `vente` (`annee`, `mois`, `jour`, `produit`, `nombre`) VALUES
(2020, 'Fevrier', 4, 'Ordinateur', 2),
(2020, 'Fevrier', 4, 'Smartphone', 8),
(2020, 'Fevrier', 4, 'Tablette', 6),
(2020, 'Fevrier', 6, 'Ordinateur', 3),
(2020, 'Fevrier', 6, 'Smartphone', 15),
(2020, 'Fevrier', 6, 'Tablette', 9),
(2020, 'Janvier', 3, 'Ordinateur', 5),
(2020, 'Janvier', 3, 'Smartphone', 12),
(2020, 'Janvier', 3, 'Tablette', 7),
(2020, 'Janvier', 4, 'Ordinateur', 2),
(2020, 'Janvier', 4, 'Tablette', 1);

-- --------------------------------------------------------

--
-- Structure de la vue `produit_mois`
--
DROP TABLE IF EXISTS `produit_mois`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `produit_mois` AS select `vente`.`annee` AS `annee`,`vente`.`mois` AS `mois`,`vente`.`produit` AS `produit`,sum(`vente`.`nombre`) AS `snombre`,sum((`produit`.`prix_vente` * `vente`.`nombre`)) AS `srecette`,(sum((`produit`.`prix_vente` * `vente`.`nombre`)) - sum((`produit`.`prix_achat` * `vente`.`nombre`))) AS `sbenefice` from (`vente` join `produit` on((`vente`.`produit` = `produit`.`codeprod`))) group by `vente`.`annee`,`vente`.`mois`,`vente`.`produit`;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `vente`
--
ALTER TABLE `vente`
  ADD CONSTRAINT `fk` FOREIGN KEY (`produit`) REFERENCES `produit` (`codeprod`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
