package future.SAE.api.controller;

import future.SAE.domain.model.Utilisateur;
import future.SAE.infrastructure.mapping.UtilisateurMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UtilisateurContoller
{
    private final UtilisateurService utilisateurService;
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurContoller(UtilisateurService service, UtilisateurMapper mapper)
    {
        this.utilisateurService = service;
        this.utilisateurMapper = mapper;
    }

    @GetMapping("/")
    public String getAllUsers()
    {
        Utilisateur user = this.utilisateurService.getAllUsers();
        UtilisateurMapper userMapper = this.utilisateurMapper.toDto(id);
        return "Liste de tous les utilisateurs";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id)
    {
        Utilisateur user = utilisateurService.getUserById(id);
        UtilisateurMapper userMapper = utilisateurMapper.toDto(id);
        return "Details de l'utilisateur avec l'id : " + id;
    }

    @PostMapping("/")
    public String createUser(@RequestBody String userData)
    {
        Utilisateur user = utilisateurService.createUser(userData);
        UtilisateurMapper userMapper = utilisateurMapper.toDto(id);
        return "Utilisateur cree avec les données : " + userData;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        Utilisateur user = utilisateurService.deleteUser(id);
        UtilisateurMapper userMapper = utilisateurMapper.toDto(id);
        return "L'utilisateur avec l'id : " + id + " est supprime.";
    }

    @PutMapping
    public String updateUser(@PathVariable Long id, @RequestBody String userData)
    {
        Utilisateur user = utilisateurService.updateUser(id, userData);
        UtilisateurMapper userMapper = utilisateurMapper.toDto(id);
        return "L'utilisateur avec l'id : " + id + " a ete mise a jour avec les donnees suivantes " + userData;
    }
}
