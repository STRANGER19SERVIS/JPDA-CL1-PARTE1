package pe.edu.i202215458.utiles;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import pe.edu.i202215458.crud.City;
import pe.edu.i202215458.crud.Country;

import java.util.List;

public class JPAFind {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("world");
        EntityManager em = emf.createEntityManager();


        try {
            Country country = em.find(Country.class, "PE");

            if (country != null) {
                String jpql = "SELECT c FROM City c WHERE c.country.code = :countryCode AND c.Population > 700000";
                TypedQuery<City> query = em.createQuery(jpql, City.class);
                query.setParameter("countryCode", "PE");

                List<City> cities = query.getResultList();

                cities.stream()
                        .map(City::getName)
                        .forEach(System.out::println);
            } else {
                System.out.println("no se encontro el codigo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}

