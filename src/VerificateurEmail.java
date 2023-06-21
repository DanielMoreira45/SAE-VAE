public class VerificateurEmail {
    private VerificateurEmail() {}

    public static void estValide(String email) throws EmailInvalideException {
        if (email == null) {
            throw new EmailInvalideException();
        }

        // Vérifier la présence d'un '@'
        int indexArrobase = email.indexOf('@');
        if (indexArrobase == -1) {
            throw new EmailInvalideException();
        }

        // Vérifier la présence d'un '.'
        int indexPoint = email.indexOf('.', indexArrobase);
        if (indexPoint == -1) {
            throw new EmailInvalideException();
        }

        // Vérifier qu'il y a du texte avant le '@' et avant et après le '.'
        String texteAvantArrobase = email.substring(0, indexArrobase);
        String texteAvantPoint = email.substring(indexArrobase + 1, indexPoint);
        String texteApresPoint = email.substring(indexPoint + 1);

        if (texteAvantArrobase.isEmpty() || texteAvantPoint.isEmpty() || texteApresPoint.isEmpty()) throw new EmailInvalideException();
    }
}
