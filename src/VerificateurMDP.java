public class VerificateurMDP {
    public static boolean contientAuMoins8Car(String mdp) {
        return mdp.length() >= 8;
    }

    public static boolean pasRepetitionSuccessive(String mdp) {
        for (int i = 1; i < mdp.length(); i++) {
            if (mdp.charAt(i-1) == mdp.charAt(i)) return false;
        }
        return true;
    }

    public static boolean auMoinsUneMaj(String mdp) {
        int nbMaj = 0;
        for (char c : mdp.toCharArray()) {
            if (Character.isUpperCase(c)) nbMaj++;
        }
        return nbMaj > 0;
    }

    public static void estValide(String mdp) throws FormatMotDePasseException {
        if (!contientAuMoins8Car(mdp)) throw new FormatMotDePasseException("Il doit y avoir au moins 8 caractères");        
        if (!pasRepetitionSuccessive(mdp)) throw new FormatMotDePasseException("Il ne doit pas y avoir deux caractères identiques successif");
        if (!auMoinsUneMaj(mdp)) throw new FormatMotDePasseException("Il doit y avoir au moins une majuscule");
    }

    public static boolean mdpConfirmationValide(String mdp, String mdpConfirmation) { 
        return mdp.equals(mdpConfirmation);
    }
}
