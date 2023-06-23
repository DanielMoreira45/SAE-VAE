public class VerificateurMDP {

    /**
     * 
     * Vérifie si le mot de passe contient au moins 8 caractères.
     * 
     * @param mdp Le mot de passe à vérifier.
     * @return {@code true} si le mot de passe contient au moins 8 caractères, sinon
     *         {@code false}.
     */
    public static boolean contientAuMoins8Car(String mdp) {
        return mdp.length() >= 8;
    }

    /**
     * 
     * Vérifie si le mot de passe ne contient pas de répétitions successives de
     * caractères.
     * 
     * @param mdp Le mot de passe à vérifier.
     * @return  true si le mot de passe ne contient pas de répétitions
     *         successives, sinon false
     */
    public static boolean pasRepetitionSuccessive(String mdp) {
        for (int i = 1; i < mdp.length(); i++) {
            if (mdp.charAt(i - 1) == mdp.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * Vérifie si le mot de passe contient au moins une lettre majuscule.
     * 
     * @param mdp Le mot de passe à vérifier.
     * @return  true si le mot de passe contient au moins une majuscule,
     *         sinon  false
     */
    public static boolean auMoinsUneMaj(String mdp) {
        int nbMaj = 0;
        for (char c : mdp.toCharArray()) {
            if (Character.isUpperCase(c)) {
                nbMaj++;
            }
        }
        return nbMaj > 0;
    }

    /**
     * 
     * Vérifie si le mot de passe est valide en respectant toutes les règles.
     * 
     * @param mdp Le mot de passe à vérifier.
     * @throws FormatMotDePasseException Si le mot de passe ne respecte pas les
     *                                   règles de validation.
     */
    public static void estValide(String mdp) throws FormatMotDePasseException {
        if (!contientAuMoins8Car(mdp)) {
            throw new FormatMotDePasseException("Il doit y avoir au moins 8 caractères");
        }
        if (!pasRepetitionSuccessive(mdp)) {
            throw new FormatMotDePasseException("Il ne doit pas y avoir deux caractères identiques successifs");
        }
        if (!auMoinsUneMaj(mdp)) {
            throw new FormatMotDePasseException("Il doit y avoir au moins une majuscule");
        }
    }

    /**
     * 
     * Vérifie si le mot de passe de confirmation correspond au mot de passe
     * initial.
     * 
     * @param mdp             Le mot de passe initial.
     * @param mdpConfirmation Le mot de passe de confirmation.
     * @return true si le mot de passe de confirmation est valide
     */
    public static boolean mdpConfirmationValide(String mdp, String mdpConfirmation) {
        return mdp.equals(mdpConfirmation);
    }

    /**
     * 
     * Vérifie si le mot de passe est valide en respectant toutes les règles.
     * 
     * @param mdp Le mot de passe à vérifier.
     * @return si le mot de passe est valide
     */
    public static boolean validar(String mdp) {
        return contientAuMoins8Car(mdp) && pasRepetitionSuccessive(mdp) && auMoinsUneMaj(mdp);
    }
}