package utility;

import com.google.gson.Gson;
import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.PersonFacade;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseInit {

    private EntityManagerFactory emf;
    private EntityManager em;

    private List<CityInfo> cities = new ArrayList();
    private List<Hobby> hobbies = new ArrayList();
    private List<String> fName = new ArrayList();
    private List<String> lName = new ArrayList();
    private List<String> address = new ArrayList();
    private List<String> addDescrip = new ArrayList();
    private List<String> email = new ArrayList();
    private List<String> domain = new ArrayList();

    public static void main(String[] args) {

        DatabaseInit bdI = new DatabaseInit();

        bdI.initiateSystem();
        bdI.populateLists();

        bdI.populateTablesWithPersons(100);

    }

    public void initiateSystem() {

        Persistence.generateSchema("pu", null);
        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();

    }

    public void populateLists() {

        //Stuff we don't need in the database
        fName.add("Christian");
        fName.add("Bjørn");
        fName.add("Hannibal");
        fName.add("Martin");
        fName.add("Menja");
        fName.add("Niki");
        fName.add("Scarlet");
        fName.add("Chuck");
        fName.add("Anna");
        fName.add("Nathan");
        fName.add("Donald");
        fName.add("Mickey");
        fName.add("Daisy");
        fName.add("Rikke");
        fName.add("Bruce");
        fName.add("Elena");
        fName.add("Lara");
        fName.add("Evie");
        fName.add("Jessica");
        fName.add("Bill");
        fName.add("Steve");
        fName.add("Alan");
        fName.add("Linus");
        fName.add("Indiana");

        lName.add("Drake");
        lName.add("Wake");
        lName.add("Wayne");
        lName.add("Croft");
        lName.add("Olsen");
        lName.add("Gates");
        lName.add("Jobs");
        lName.add("Hansen");
        lName.add("Kristiansen");
        lName.add("Skriver");
        lName.add("Malcom");
        lName.add("Petersen");
        lName.add("Knoop");
        lName.add("Kjær");
        lName.add("Johansen");
        lName.add("Bale");
        lName.add("Jørgensen");
        lName.add("Duck");
        lName.add("Mouse");
        lName.add("Jones");
        lName.add("Boss");

        email.add("wakaman" + randInt(1, 99));
        email.add("bk" + randInt(1, 99));
        email.add("ceo" + randInt(1, 99));
        email.add("deluxemail" + randInt(1, 99));
        email.add("ludoman" + randInt(1, 99));
        email.add("bigboss" + randInt(1, 99));
        email.add("pizzalover" + randInt(1, 99));
        email.add("deeznuts" + randInt(1, 99));
        email.add("youwutmate" + randInt(1, 99));
        email.add("fabulous" + randInt(1, 99));
        email.add("bingomail" + randInt(1, 99));
        email.add("gotmail" + randInt(1, 99));

        domain.add("apple.com");
        domain.add("gmail.com");
        domain.add("hotmail.com");
        domain.add("microsoft.com");
        domain.add("macroumors.com");
        domain.add("yahoo.com");
        domain.add("live.dk");
        domain.add("live.com");
        domain.add("cphbusiness.dk");
        domain.add("fog.dk");
        domain.add("sony.com");
        domain.add("nintendo.com");
        domain.add("kotaku.com");
        domain.add("eurogamer.net");
        domain.add("ign.com");

        address.add("Kongevejen " + randInt(1, 200));
        address.add("Knud den storevej " + randInt(1, 200));
        address.add("Nordrefrihanvs gade " + randInt(1, 200));
        address.add("Jernbanegade " + randInt(1, 200));
        address.add("Hermosavej " + randInt(1, 200));
        address.add("Firskovsvej " + randInt(1, 200));
        address.add("Bellisvej " + randInt(1, 200));
        address.add("Anemonevej " + randInt(1, 200));
        address.add("Eriksvej " + randInt(1, 200));
        address.add("Algade " + randInt(1, 200));
        address.add("Lærkevej " + randInt(1, 200));
        address.add("Ludovej " + randInt(1, 200));
        address.add("Hovedgaden " + randInt(1, 200));
        address.add("Kompostvej " + randInt(1, 200));
        address.add("Ved kirkebjerg " + randInt(1, 200));
        address.add("Pandekagevej " + randInt(1, 200));
        address.add("Nyhavn " + randInt(1, 200));
        address.add("Papirhaven " + randInt(1, 200));
        address.add("Odinsvej " + randInt(1, 200));
        address.add("Thorsvej " + randInt(1, 200));
        address.add("Frejasvej " + randInt(1, 200));
        address.add("Lokesvej " + randInt(1, 200));

        addDescrip.add("Big house.");
        addDescrip.add("Very expensive.");
        addDescrip.add("Dodgy-looking place.");
        addDescrip.add("Very stylish looking.");
        addDescrip.add("Has a big garden.");
        addDescrip.add("Brand new roof.");
        addDescrip.add("Made of briks!");
        addDescrip.add("Brand new house.");
        addDescrip.add("Just been build.");
        addDescrip.add("Could use a helping hand.");
        addDescrip.add("Fixxer-upper.");
        addDescrip.add("All your base are belong to us.");
        addDescrip.add("Comes with a big garden.");
        addDescrip.add("Very grand house.");
        addDescrip.add("Has a lot of rooms.");
        addDescrip.add("Comes with its own elevator.");
        addDescrip.add("Powered by solarpanels.");
        addDescrip.add("Green energy.");
        addDescrip.add("Shared apartment.");
        addDescrip.add("Has build-in Mountain Dew dispenser.");
        addDescrip.add("Walls are decorated with gold.");
        addDescrip.add("Equiped with a killer rabbit to take care of intruders.");

        //Stuff we need to have in the database
        em.getTransaction().begin();

        cities.add(new CityInfo(3480, "Fredensborg"));
        cities.add(new CityInfo(6880, "Tarm"));
        cities.add(new CityInfo(4000, "Roskilde"));
        cities.add(new CityInfo(2860, "Søborg"));
        cities.add(new CityInfo(2605, "Brøndby Vester"));
        cities.add(new CityInfo(2800, "Lyngby"));
        cities.add(new CityInfo(3400, "Hillerød"));
        cities.add(new CityInfo(3450, "Allerød"));
        cities.add(new CityInfo(3000, "Helsingør"));
        cities.add(new CityInfo(2770, "Kastrup"));
        cities.add(new CityInfo(2730, "Herlev"));
        cities.add(new CityInfo(2670, "Greve"));

        for (CityInfo city : cities) {

            em.persist(city);

        }

        hobbies.add(new Hobby("Gaming", "Playing video games."));
        hobbies.add(new Hobby("BMX", "Trashing tracks on a sick bike yo."));
        hobbies.add(new Hobby("Geocaching", "Finding hidden treasures in the nature."));
        hobbies.add(new Hobby("Figure collecting", "Collecting figures from series."));
        hobbies.add(new Hobby("Photografy", "Taking pictures with a camera."));
        hobbies.add(new Hobby("Playing ludo", "50 shades of Ludo'ing"));
        hobbies.add(new Hobby("Stalking", "A game of not being seen."));
        hobbies.add(new Hobby("Racetrack driving", "Driving on the racetrack."));
        hobbies.add(new Hobby("Running", "Running to get fit AF."));
        hobbies.add(new Hobby("Eating", "Gotta get thoose frikadeller."));
        hobbies.add(new Hobby("Sleeping", "Sleeping for fun"));
        hobbies.add(new Hobby("Streaking", "Running around with no pants on."));

        for (Hobby hobby : hobbies) {

            em.persist(hobby);

        }

        em.getTransaction().commit();

    }

    public List<Phone> getRandomPhone() {

        List<Phone> phone = new ArrayList();

        int luckyNumber = randInt(1, 3);

        switch (luckyNumber) {
            case 1:
                phone.add(new Phone(randInt(12345678, 99999999), "home"));
                break;
            case 2:
                phone.add(new Phone(randInt(12345678, 99999999), "home"));
                phone.add(new Phone(randInt(12345678, 99999999), "cell"));
                break;
            default:
                phone.add(new Phone(randInt(12345678, 99999999), "home"));
                phone.add(new Phone(randInt(12345678, 99999999), "cell"));
                phone.add(new Phone(randInt(12345678, 99999999), "work"));
                break;
        }

        return phone;

    }

    public String getRandomMail() {

        return email.get(randInt(0, email.size() - 1)) + "@" + domain.get(randInt(0, domain.size() - 1));

    }

    public void populateTablesWithPersons(int amount) {

        em.getTransaction().begin();

        for (int i = 0; i < amount; i++) {

            Person person = new Person(fName.get(randInt(0, fName.size() - 1)), lName.get(randInt(0, lName.size() - 1)), getRandomMail());

            //Adding hobbies to the person
            
            //Get three unique numbers
            final Random random = new Random();
            final Set<Integer> intSet = new HashSet<>();
            
            while (intSet.size() < randInt(1, 5)) {
                
                intSet.add(random.nextInt(hobbies.size()));
            }
            
            final int[] ints = new int[intSet.size()];
            final Iterator<Integer> iter = intSet.iterator();
            
            for (int m = 0; iter.hasNext(); ++m) {
                
                ints[m] = iter.next();
                
            }

            for (int j = 0; j < ints.length; j++) {

                person.addHobby(hobbies.get(ints[j]));

            }

            //Adding phone(s) to the person
            List<Phone> phones = getRandomPhone();

            for (Phone phone : phones) {

                person.addPhone(phone);

            }

            //Add address
            Address adr = new Address(address.get(randInt(0, address.size() - 1)), addDescrip.get(randInt(0, addDescrip.size() - 1)));
            adr.setCityInfo(cities.get(randInt(0, cities.size() - 1)));

            person.setAddress(adr);

            em.persist(person);

        }

        em.getTransaction().commit();

        em.close();
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

    /**
     * Returns a random number between min and max, inclusive. The difference
     * between min and max can be at most <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value. Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
