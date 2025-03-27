public class TypeCanard {
    public enum typeCanard {
        CanardEau,
        CanardFeu,
        CanardGlace,
        CanardVent
    }

    // Multiplicateurs : 
        // x 1.5 :
            // EAU > FEU
            // FEU > GLACE
            // GLACE > VENT
            // VENT > EAU

        // x 0.5 
            // FEU > EAU
            // GLACE > FEU
            // VENT > GLACE
            // EAU > VENT

        // x 1 sinon.

        /**
         * 
         * 
         * @param attaquant : Le canard attaquant.
         * @param defenseur : Le canard défenseur.
         * @return          : La valeur du multiplicateur.
         */
        public static double getMultiplicateur(typeCanard attaquant, typeCanard defenseur) {
            // Cas : attaquant fort contre défenseur
            if ((attaquant == typeCanard.CanardEau && defenseur == typeCanard.CanardFeu) ||
                    (attaquant == typeCanard.CanardFeu && defenseur == typeCanard.CanardGlace) ||
                    (attaquant == typeCanard.CanardGlace && defenseur == typeCanard.CanardVent) ||
                    (attaquant == typeCanard.CanardVent && defenseur == typeCanard.CanardEau)) {
                return 1.5;
            }
            // Cas : attaquant faible contre défenseur (inverse)
            else if ((attaquant == typeCanard.CanardFeu && defenseur == typeCanard.CanardEau) ||
                    (attaquant == typeCanard.CanardGlace && defenseur == typeCanard.CanardFeu) ||
                    (attaquant == typeCanard.CanardVent && defenseur == typeCanard.CanardGlace) ||
                    (attaquant == typeCanard.CanardEau && defenseur == typeCanard.CanardVent)) {
                return 0.5;
            }
            // Sinon : neutre
            else {
                return 1.0;
            }
        }
    }
