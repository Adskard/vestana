package cz.cvut.fel.nss.vestana;

import cz.cvut.fel.nss.vestana.dto.ClothingArticleDto;
import cz.cvut.fel.nss.vestana.model.Admin;
import cz.cvut.fel.nss.vestana.model.ClothingArticle;
import cz.cvut.fel.nss.vestana.model.Employee;
import cz.cvut.fel.nss.vestana.service.interfaces.ClothingService;
import cz.cvut.fel.nss.vestana.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final Random random = new Random();

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ClothingService clothingService;

    @Override
    public void run(String...args) throws Exception {
        Director director = new Director();
        AdminBuilder adminBuilder = new AdminBuilder();
        NormalEmployeeBuilder normalEmployeeBuilder = new NormalEmployeeBuilder();

        try {
            employeeService.findByUsername("Admin");
        } catch (Exception e) {
            director.setEmployeeBuilder(adminBuilder);
            director.buildNewEmployee();
            Employee employee = adminBuilder.getEmployee();
            employeeService.register(employee);
        }

        try {
            employeeService.findByUsername("NormalEmployee");
        } catch (Exception e) {
            director.setEmployeeBuilder(normalEmployeeBuilder);
            director.buildNewEmployee();
            Employee employee = normalEmployeeBuilder.getEmployee();
            employeeService.register(employee);
        }

        addArticles();
    }

    public void addArticles() {
        ClothingArticle article1 = new ClothingArticle("shirt", "blue", "c/data/pictures", 52, 500.00);
        clothingService.save(article1);

        ClothingArticle article2 = new ClothingArticle("pants", "green", "c/data/pictures", 46, 1000.00);
        clothingService.save(article2);

        ClothingArticle article3 = new ClothingArticle("jacket", "black", "c/data/pictures", 58, 2000.00);
        clothingService.save(article3);

        ClothingArticle article4 = new ClothingArticle("dress", "violet", "c/data/pictures", 48, 3000.00);
        clothingService.save(article4);

        ClothingArticle article5 = new ClothingArticle("skirt", "red", "c/data/pictures", 44, 1500.00);
        clothingService.save(article5);
    }

    abstract class EmployeeBuilder {
        protected Employee employee;
        protected String name;
        protected String password;

        public Employee getEmployee() { return employee; }

        public abstract void createNewEmployee();
        public abstract void buildName();
        public abstract void buildPassword();
    }

    class AdminBuilder extends EmployeeBuilder {
        public void createNewEmployee() {
            employee = new Admin(name, password);
        }
        public void buildName() {
            name = "Admin";
        }
        public void buildPassword() {
            password = "AdminPassword" + random.nextInt();
        }
    }

    class NormalEmployeeBuilder extends EmployeeBuilder {
        public void createNewEmployee() {
            employee = new Employee(name, password);
        }
        public void buildName() {
            name = "NormalEmployee";
        }
        public void buildPassword() {
            password = "EmployeePassword" + random.nextInt();
        }
    }

    class Director {
        private EmployeeBuilder employeeBuilder;

        public void setEmployeeBuilder (EmployeeBuilder builder) { employeeBuilder = builder; }
        public Employee getEmployee() { return employeeBuilder.getEmployee(); }

        public void buildNewEmployee() {
            employeeBuilder.buildName();
            employeeBuilder.buildPassword();
            employeeBuilder.createNewEmployee();
        }
    }
}
