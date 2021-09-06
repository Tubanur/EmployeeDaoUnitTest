package springexamples.unittesting.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springexamples.unittesting.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository <Employee, Long>
{

}
