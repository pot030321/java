package dajava.dacs.service;



import dajava.dacs.model.Role;
import dajava.dacs.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private IRoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public List<Role> getAllRolesByIds(List<Long> roleIds) {
        return roleRepository.findAllById(roleIds);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

}
