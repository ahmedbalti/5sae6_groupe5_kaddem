package tn.esprit.spring.kaddem.services;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;


@Service
public class UniversiteServiceImpl implements IUniversiteService{
@Autowired
    UniversiteRepository universiteRepository;
@Autowired
    DepartementRepository departementRepository;





    public UniversiteServiceImpl() {
        // TODO Auto-generated constructor stub
    }
  public   List<Universite> retrieveAllUniversites(){
return (List<Universite>) universiteRepository.findAll();
    }

 public    Universite addUniversite (Universite  u){
return  (universiteRepository.save(u));
    }

 public    Universite updateUniversite (Universite  u){
     return  (universiteRepository.save(u));
    }

  public Universite retrieveUniversite (Integer idUniversite){
Universite u = universiteRepository.findById(idUniversite).get();
return  u;
    }
    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement){
        Universite u= universiteRepository.findById(idUniversite).orElse(null);
        Departement d= departementRepository.findById(idDepartement).orElse(null);
        u.getDepartements().add(d);
        universiteRepository.save(u);
    }

    public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite){
Universite u=universiteRepository.findById(idUniversite).orElse(null);
return u.getDepartements();
    }

    private static final Logger logger = LogManager.getLogger(UniversiteServiceImpl.class);

    public void someServiceMethod() {
        // Log d'une information
        logger.info("Un message d'information depuis le service.");

        // Log d'un message de débogage
        logger.debug("Un message de débogage depuis le service.");

        // Log d'une erreur avec une exception
        try {
            // Code susceptible de générer une exception
        } catch (Exception e) {
            logger.error("Une erreur s'est produite dans le service.", e);
        }
    }
}
