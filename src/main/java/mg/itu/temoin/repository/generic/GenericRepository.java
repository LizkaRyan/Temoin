package mg.itu.temoin.repository.generic;

import jakarta.persistence.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class GenericRepository<T,I> {

    private final Class<T> entityClass;

    public GenericRepository(){
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            this.entityClass = (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
        } else {
            throw new IllegalArgumentException("Main must be subclassed to determine T");
        }
    }

    protected List<T> findRequest(String jpql,ParameterQuery parameterQuery){
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");EntityManager em = emf.createEntityManager()){
            return findRequest(jpql,em,parameterQuery);
        }
    }

    protected List<T> findRequest(String jpql){
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit"); EntityManager em=emf.createEntityManager()) {
            return findRequest(jpql, em, typedQuery -> {});
        }
    }

    protected List<T> findRequest(String jpql,EntityManager em,ParameterQuery parameterQuery)throws RuntimeException{

        List<T> models=null;
        try {
            // 1. Écrire une requête JPQL
            TypedQuery<T> query = em.createQuery(jpql, this.entityClass);
            parameterQuery.setParameter(query);
            //query.setParameter("email", "example@example.com");

            // 2. Exécuter la requête
            models = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return models;
    }

    protected List<T> findRequest(String jpql,EntityManager em)throws RuntimeException{
        List<T> models=null;
        try {

            // 3. Écrire une requête JPQL
            TypedQuery<T> query = em.createQuery(jpql, this.entityClass);
            //query.setParameter("email", "example@example.com");

            // 4. Exécuter la requête
            models = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return models;
    }

    // Méthode pour trouver une entité par son ID
    protected Optional<T> findById(I id) {
        // Créer l'EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        try {
            // Rechercher l'entité par son ID
            return findById(id,em);
        } finally {
            em.close();
            emf.close();
        }
    }

    protected Optional<T> findById(I id,EntityManager em) {
        T entity = null;
        entity = em.find(entityClass, id);
        // Retourner l'entité dans un Optional
        return Optional.ofNullable(entity);
    }

    protected Optional<T> findOnlyOne(String jpql,ParameterQuery parameterQuery,EntityManager em) {
        TypedQuery<T> query = em.createQuery(jpql, this.entityClass);
        parameterQuery.setParameter(query);
        try{
            return Optional.ofNullable(query.getSingleResult());
        }
        catch (NoResultException ex){
            return Optional.empty();
        }
    }

    protected Optional<T> findOnlyOne(String jpql,EntityManager em) {
        TypedQuery<T> query = em.createQuery(jpql, this.entityClass);
        try{
            return Optional.ofNullable(query.getSingleResult());
        }
        catch (NoResultException ex){
            return Optional.empty();
        }
    }

    protected Optional<T> findOnlyOne(String jpql,ParameterQuery parameterQuery) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        try {
            // Rechercher l'entité par son ID
            return findOnlyOne(jpql,parameterQuery,em);
        } finally {
            em.close();
            emf.close();
        }
    }

    protected Optional<T> findOnlyOne(String jpql) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        try {
            // Rechercher l'entité par son ID
            return findOnlyOne(jpql,typedQuery -> {},em);
        } finally {
            em.close();
            emf.close();
        }
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
