package mg.itu.temoin.repository.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class GenericRepository<T,I> {

    private final Class<T> entityClass;

    public List<T> findRequest(String jpql,ParameterQuery parameterQuery){
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            return findRequest(jpql,emf,parameterQuery);
        }
    }

    public List<T> findRequest(String jpql){
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")) {
            return findRequest(jpql, emf, typedQuery -> {});
        }
    }

    public List<T> findRequest(String jpql,EntityManagerFactory emf,ParameterQuery parameterQuery)throws RuntimeException{

        // 1. Obtenir l'EntityManager
        EntityManager em = emf.createEntityManager();

        List<T> models=null;
        try {
            // 2. Commencer une transaction
            em.getTransaction().begin();

            // 3. Écrire une requête JPQL
            TypedQuery<T> query = em.createQuery(jpql, this.entityClass);
            parameterQuery.setParameter(query);
            //query.setParameter("email", "example@example.com");

            // 4. Exécuter la requête
            models = query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            // 8. Fermer l'EntityManager
            em.close();
            emf.close();
        }
        return models;
    }

    // Méthode pour trouver une entité par son ID
    public Optional<T> findById(I id) {
        // Créer l'EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        T entity = null;
        try {
            // Rechercher l'entité par son ID
            entity = em.find(entityClass, id);
        } finally {
            em.close();
            emf.close();
        }

        // Retourner l'entité dans un Optional
        return Optional.ofNullable(entity);
    }

    // Méthode pour insérer une entité
    public void save(T entity) {
        // Créer l'EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        try {
            // Commencer la transaction
            em.getTransaction().begin();

            this.save(entity,em);

            // Committer la transaction
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Si une erreur se produit, annuler la transaction
            em.getTransaction().rollback();
        } finally {
            // Fermer l'EntityManager
            em.close();
            emf.close();
        }
    }

    public void save(T entity,EntityManager em)throws RuntimeException {
        em.persist(entity);
    }
}
