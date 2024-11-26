package pe.edu.i202215458.utiles;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.edu.i202215458.crud.Continent;
import pe.edu.i202215458.crud.Country;

public class JPAPersist {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("world");
        EntityManager em = emf.createEntityManager();

        // Registrar los países
        try {
            em.getTransaction().begin();

            // Define países
            Country gotica = new Country(
                    "BA", "Jerusalen", Continent.Asia, "Malasia", 790000.0, 2024, 6565000,
                    80.0, 25000.0, 95000.0, "FREFIRE", "INDIA", "Dorian larco Herrera", 1, "CP1");

            Country azafran = new Country(
                    "BB", "Miami", Continent.ANTARCTICA, "peruvian", 700000.0, 2024, 5500000,
                    60.0, 32000.0, 12000.0, "Larco Herrera", "Arabia Saudita", "Jhon Burguer", 2, "CP2");

            Country wakanda = new Country(
                    "BC", "Cielo", Continent.Asia, "SELVA", 320000.0, 2024, 5400000,
                    10.0, 1200.0, 15500.0, "Wakanda FOREVER", "AVENGERS END GAME", "KINGLEON", 3, "CP3");

            // Persiste países
            em.persist(gotica);
            em.persist(azafran);
            em.persist(wakanda);

            em.getTransaction().commit();

            System.out.println("Países persistidos con éxito.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
