package springexamples.unittesting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import springexamples.unittesting.model.Employee;
import springexamples.unittesting.model.Employees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = UnitTestingApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Sql({ "classpath:schema.sql", "classpath:data.sql" })
    @Test
    public void testAllEmployees()
    {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/employees", Employees.class)
                        .getEmployeeList().size() == 3);
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee("Lokesh", "Gupta", "howtodoinjava@gmail.com");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/employees", employee, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }
}
