package org.example;

public class ConfigurationMediatheque {
    private int dureeEmprunt = 21;   // en jours
    private int maxEmprunts = 5;

    private ConfigurationMediatheque() { }

    // Idiome du holder : paresseux ET thread-safe sans verrou.
    // La JVM garantit l'initialisation unique de la classe interne,
    // declenchee seulement au premier appel de getInstance().
    private static class Holder {
        static final ConfigurationMediatheque INSTANCE = new ConfigurationMediatheque();
    }

    public static ConfigurationMediatheque getInstance() {
        return Holder.INSTANCE;
    }

    public int getDureeEmprunt() { return dureeEmprunt; }

    public void setDureeEmprunt(int jours) { this.dureeEmprunt = jours; }

    public int getMaxEmprunts() { return maxEmprunts; }
}