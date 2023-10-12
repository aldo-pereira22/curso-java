package curso.service;

import curso.entities.User;
import curso.repositories.UserRepository;
import curso.resources.exceptions.DataBaseException;
import curso.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow( () -> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }

//    public void delete (Long id){
//        try {
//            userRepository.deleteById(id);
//        }catch (EmptyResultDataAccessException e){
//            throw new ResourceNotFoundException(id);
//        }
//    }

//    public void delete(Long id) {
//        try {
//            if (userRepository.existsById(id)) {
//                userRepository.deleteById(id);
//            } else {
//                throw new ResourceNotFoundException(id);
//            }
//        } catch (DataIntegrityViolationException e) {
//            e.printStackTrace();
////            throw new DatabaseException(e.getMessage());
//        }
//    }

    public void delete(Long id){
        try {
            if(!userRepository.existsById(id)) throw new ResourceNotFoundException(id);
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e ){
            throw new DataBaseException(e.getMessage());
        }
    }
    public User update(Long id, User obj){

        try {
            User entity = userRepository.getReferenceById(id);
            updateData(entity, obj);
            return userRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
