package com.LicuadoraProyectoEcommerce.seeder;
import com.LicuadoraProyectoEcommerce.model.manager.Manager;
import com.LicuadoraProyectoEcommerce.model.seller.Seller;
import com.LicuadoraProyectoEcommerce.model.userAuth.Role;
import com.LicuadoraProyectoEcommerce.model.userAuth.User;
import com.LicuadoraProyectoEcommerce.repository.UserAuth.UserRepository;
import com.LicuadoraProyectoEcommerce.repository.manager.ManagerRepository;
import com.LicuadoraProyectoEcommerce.repository.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findAll().isEmpty()){
            List<User> userList = userRepository.saveAll(List.of(
            new User(null, "Andres Manager", "rodrigueza.federacion@gmail.com", passwordEncoder.encode("12345678"), Role.MANAGER),
            new User(null, "Victor Seller", "victor@gmail.com", passwordEncoder.encode("12345678"), Role.SELLER),
            new User(null, "Admin", "admin@gmail.com", passwordEncoder.encode("12345678"), Role.ADMIN)
            ));
            managerRepository.save(new Manager(userList.get(0)));
            sellerRepository.save(new Seller(userList.get(1)));
        }
    }
}
