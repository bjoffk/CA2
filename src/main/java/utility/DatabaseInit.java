package utility;

import com.google.gson.Gson;
import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.PersonFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseInit {

    private EntityManagerFactory emf;
    private EntityManager em;

    public static void main(String[] args) {

        DatabaseInit bdI = new DatabaseInit();

//        bdI.initiateSystem();
//
//        bdI.testAdd();

        PersonFacade f = new PersonFacade();
        f.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
        Gson gson = new Gson();
        System.out.println(gson.toJson(f.getPerson(1)));
    }

    public void initiateSystem() {

        Persistence.generateSchema("pu", null);

        emf = Persistence.createEntityManagerFactory("pu");

        em = emf.createEntityManager();

    }

    public void testAdd() {

        try {

            em.getTransaction().begin();
            
            CityInfo city = new CityInfo(3480, "Fredensborg");
            
            em.persist(city);
            
            

            Person person = new Person("Ulla", "Jensen", "test@mail.dk");

            person.addHobby(new Hobby("Racing", "Drifting around"));

            person.addPhone(new Phone(87676543, "Home"));
            
            Address adr = new Address("Kongevejen", "Stort hus");
            adr.setCityInfo(city);
                    
            
            person.setAddress(adr);
            
            

            em.persist(person);

            em.getTransaction().commit();

            //If something goes wrong, use getTransaction().rollback();
        } finally {

            em.close();

        }

    }

}
