package pe.edu.i202215458.utiles;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.edu.i202215458.crud.Country;

public class JPARemove {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("world");
        EntityManager em = emf.createEntityManager();


        try {
            em.getTransaction().begin();

            Country country = em.find(Country.class, "BA");

            if (country != null) {
                em.remove(country);
                System.out.println("País " + country.getName() + "eliminado.");
            } else {
                System.out.println("País Eliminado.");
            }

            em.getTransaction().commit();

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
