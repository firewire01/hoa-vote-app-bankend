package ch.ilv.voteapp;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DatabaseTest {

//    @Autowired
//    private PersonRepository personRepository;
//
//    @Test
//    void insertPerson() {
//        Person personMale = new Person("John", "Doe", true, Sex.MALE, 24, "Lombard Street 150", "San Francisco", "94123");
//        Assertions.assertNotNull(personMale.getFirstName());
//        Person personFemale = new Person("Jane", "Doe", true, Sex.FEMALE, 23, "Lombard Street 150", "San Francisco", "94123");
//        Assertions.assertNotNull(personFemale.getFirstName());
//    }

}
