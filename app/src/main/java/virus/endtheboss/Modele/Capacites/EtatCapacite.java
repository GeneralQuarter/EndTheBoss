package virus.endtheboss.Modele.Capacites;

import java.io.Serializable;

/**
 * Created by Quentin Gangler on 13/02/2016.
 * Représente les différents états d'une capacité
 */
public enum EtatCapacite implements Serializable{
    PEUX_LANCER_CAPACITE,
    MONTRE_PORTE_CAPACITE,
    MONTRE_IMPACT_CAPACITE,
    NE_PEUX_PAS_LANCER_CAPACITE
}
